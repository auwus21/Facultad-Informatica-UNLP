# 📝 Tema 2: Procesos

**Materia:** Introducción a los Sistemas Operativos (ISO)
**Contenido:** Resumen de las Partes I, II y III (Clase 2)

---

## 🔹 I. Conceptos Básicos de un Proceso

### 1. Programa vs. Proceso
* **Programa:** Es estático (vive en disco), no tiene Program Counter y existe hasta que se lo borra.
* **Proceso:** Es dinámico (es un programa en ejecución), posee Program Counter y su ciclo de vida abarca desde que se solicita su ejecución hasta que termina.
* *Nota:* Tarea, Job y Proceso se usan como sinónimos.

El sistema utiliza **multiprogramación**: varios procesos simulando ejecución concurrente, aunque en un procesador single-core solo haya un proceso activo a la vez.

### 2. Componentes de un Proceso
Un proceso en ejecución consta, como mínimo, de:
1. **Sección de Código (Text):** Las instrucciones del ejecutable.
2. **Sección de Datos:** Variables globales.
3. **Stack(s) (Pila):** Datos temporales como parámetros de rutinas, variables locales y direcciones de retorno. Su tamaño se ajusta en *run-time* (tiempo de ejecución) y operan mediante *push* (apilar) y *pop* (desapilar).

### 3. PCB (Process Control Block)
Es la estructura de datos que representa al proceso en el Sistema Operativo (una por proceso). Es lo primero que se crea y lo último en borrarse.
**Contiene:** 
* Identificadores: PID (Process ID) y PPID (Padre).
* Contexto y CPU: Valores de los registros de CPU (como el Program Counter).
* Información de Planificación: Estado actual, prioridad, tiempo consumido.
* Gestión de Memoria y operaciones de Entrada/Salida pendientes.

### 4. Espacio de Direcciones y Contexto
* **Espacio de Direcciones:** Es el conjunto de direcciones de memoria RAM que abarca el proceso (Stack + Texto + Datos). Ojo: *El PCB NO está en el espacio de usuario*, solo en modo kernel se puede acceder a otros espacios o al PCB.
* **Contexto:** Es TODA la información que el Kernel y el Hardware precisan para que el proceso funcione fluidamente (Estado de CPU, PCB, espacio de memoria).
* **Cambio de Contexto (Context Switch):** Ocurre cuando la CPU pasa de ejecutar un Proceso A al Proceso B. Se debe "guardar" el contexto del A y "cargar" el contexto del B. *Es tiempo no productivo de CPU.*

### 5. ¿El Kernel es un proceso?
No. El concepto de proceso se asocia al usuario. Hay dos enfoques de diseño:
1. **Entidad Independiente:** El kernel corre separado de todo, con su propia memoria y stack. Cuando hay una llamada, el control pasa enteramente al Kernel.
2. **El Kernel "dentro" del Proceso:** El software del SO reside en el espacio de memoria de todos los procesos. Cuando ocurre una interrupción, el proceso pasa de "Modo Usuario" a "Modo Kernel" para ejecutar las rutinas sin necesidad de realizar un Cambio de Contexto pesado.

---

## 🔹 II. Planificación y Estados (Scheduling)

### 1. Estados de un Proceso
* **New (Nuevo):** Recién creado. Generando sus estructuras.
* **Ready (Listo):** En RAM, listo para ejecutarse pero esperando que le den tiempo de CPU.
* **Running (Ejecución):** Haciendo uso de la CPU.
* **Waiting / Blocked (Dormido / En espera):** El proceso está pausado a la espera de un evento asíncrono (ej. que termine de leerse un archivo del disco o espere señal de red).
* **Zombie (Terminado / Exit):** Ejecutó una salida pero su PCB sigue existiendo hasta que el padre recolecte sus datos.
* **Swapping:** Estados donde el proceso está pausado pero fue movido al disco duro temporalmente (Swap) para liberar memoria principal.

### 2. Schedulers (Planificadores)
Módulos del Kernel (software) que deciden cuestiones de procesos.
* **Long Term (Largo Plazo):** Controla la cantidad total de procesos concurrentes en el sistema (multiprogramación). Decide quién entra a la memoria. 
* **Short Term (Corto Plazo):** Es el más rápido. Toma los procesos de la cola *Ready* y elige cuál ejecutará ahora en base a un algoritmo.
* **Medium Term (Medio Plazo/Swapping):** Expulsa procesos de la RAM temporalmente (Swap-Out) si hay sobrecarga y luego los vuelve a traer (Swap-In).
* **Dispatcher y Loader:** El Loader carga el programa a la RAM. El Dispatcher realiza físicamente el Cambio de Contexto y da el "salto de instrucción" que le ordena el Short Term.

### 3. Comportamiento y Algoritmos
Los procesos pueden ser **CPU-Bound** (usan la CPU al máximo calculando) o **I/O-Bound** (usan muy poca CPU porque viven esperando lecturas de disco, teclado o red). Siendo eficientes, debe priorizarse el I/O-Bound para no encolar hardware periférico lento.

* **No Apropiativos (Non-Preemptive):** Un proceso no suelta la CPU hasta que termina voluntariamente o pide I/O. Usado en sistemas interactivos viejos o cerrados (Bach/Lotes como: *FCFS*, *SJF*).
* **Apropiativos (Preemptive):** El SO puede expulsarlo a la fuerza a la mitad (por cuotas de tiempo o prioridades mayores). Necesario para uso Interactivo y Servidores moderno (Ej: *Round Robin*, *Colas*, *SRTF*).

**Política vs Mecanismo:** El Kernel te da el "Mecanismo" (ej. un planificador), pero deja que los usuarios/admin definan la "Política" (ej: usando `nice` puedes acomodar tu prioridad personalmente).

---

## 🔹 III. Creación y Ramificación

### 1. Dinámica Padre e Hijo
* El primer proceso lo crea el kernel y ese crea a todos los subsiguientes como si fuera un "árbol".
* **Ejecución:** El padre puede ejecutar a la vez que su hijo, o quedarse dormido (`wait`) esperando a que el hijo termine su trabajo.
* **Direcciones:** En UNIX el espacio de memoria del hijo nace como un CLON idéntico del padre. En Windows el espacio del hijo nace en blanco con el programa inyectado directamente.

### 2. System Calls en UNIX
La creación en base POSIX se divide en 2 pasos:
1. `fork()`: Clonado literal. Parte al proceso actual a la mitad creando un "Hijo" gemelo.
    * Si retorna un valor de ID > 0, sabe que él es el Padre (le retorna el ID del bebe recién llegado).
    * Si retorna 0, esa bifurcación sabe que es el Hijo.
    * Si da < 0 es un Error.
2. `execve()`: El Hijo clónico suele llamar a esto luego para "destruir" su propia cabeza prestada por el padre e inyectarse con el binario del programa nuevo que se quería iniciar realmente.

### 3. Finalización
* `exit()`: El proceso emite un grito final informando por qué murió para que el So le comunique el estatus.
* `wait()`: El padre "escucha" y recolecta lo que devuelve el exit de un hijo.
* `kill()`: Si un padre se harta, puede enviarle señales al hijo obligándolo a suicidarse. En algunos casos como en Unix, a veces la muerte del padre impone la "Muerte en cascada" de sus sub-hijos.
