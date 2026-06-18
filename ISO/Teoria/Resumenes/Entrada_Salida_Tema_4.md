# 📘 Tema 4: Subsistema de Entrada / Salida (E/S)

**Materia:** Introducción a los Sistemas Operativos (ISO) — UNLP 2026  
**Temas:** Clasificación de dispositivos, Capas de software de E/S, Metas del subsistema, Drivers, Ciclo de vida de una petición, Optimización de performance, DMA, Buffering, Spooling.

---

<details>
<summary><b>🔌 Parte 1: Entrada / Salida (Conceptos, Estructura, Drivers y Ciclo de Peticiones)</b></summary>

## 🎯 Responsabilidades del SO en E/S

El subsistema de Entrada/Salida es el encargado de comunicar al procesador y la memoria con el mundo exterior (periféricos). Sus responsabilidades principales son:
- **Controlar los dispositivos físicos**: Generar comandos de bajo nivel, procesar interrupciones de hardware y gestionar errores de transferencia.
- **Proporcionar una interfaz limpia y abstracta**: Ocultar la complejidad de los controladores al programador de aplicaciones.

### ⚠️ Problemas de la E/S
El SO debe lidiar con:
1. **Heterogeneidad extrema**: Un teclado funciona de manera totalmente diferente a un disco rígido o una placa de red.
2. **Diferencias de velocidad**: La CPU corre a nanosegundos; los dispositivos mecánicos o de red pueden tardar milisegundos.
3. **Evolución constante**: El soporte para nuevos tipos de hardware debe ser flexible y modular.

---

## 🏗️ Clasificación y Aspectos de los Dispositivos de E/S

Los periféricos se catalogan según varios atributos físicos y operacionales:

### 1️⃣ Unidad de Transferencia
- **Dispositivos por Bloques (ej: Discos Rígidos, SSDs)**:
  - Almacenan información en bloques de tamaño fijo.
  - Permiten lecturas, escrituras y búsquedas (`Read`, `Write`, `Seek`) de bloques específicos de forma independiente.
- **Dispositivos por Carácter (ej: Teclados, Mouses, Puertos Serie)**:
  - Envían o reciben un flujo continuo de caracteres, sin estructura de bloques.
  - No son direccionables ni admiten búsquedas. Sus operaciones principales son leer/escribir caracteres (`get`, `put`).

### 2️⃣ Forma de Acceso
- **Secuencial**: Los datos deben leerse en orden (ej: cintas magnéticas de backup).
- **Aleatorio (Random)**: Se puede acceder a cualquier posición directamente (ej: discos).

### 3️⃣ Compartición y Permisos
- **Tipo de acceso**:
  - *Acceso Compartido*: Varios procesos leen/escriben concurrentemente (ej: Disco Rígido).
  - *Acceso Exclusivo*: Solo un proceso puede controlarlo a la vez (ej: Impresora).
- **Permisos**:
  - *Read Only* (Solo lectura): CD-ROM.
  - *Write Only* (Solo escritura): Pantalla / Impresora.
  - *Read/Write* (Lectura y Escritura): Discos.

### 4️⃣ Velocidades de Transferencia
La diferencia de velocidad entre periféricos y CPU es masiva, abarcando varios órdenes de magnitud:

<img src="./images/T4IO_Slide_7.png" alt="Velocidades de Dispositivos de E/S" width="650"/>

---

## 🎯 Metas y Servicios del Subsistema de E/S

El SO implementa servicios para unificar y optimizar el acceso al hardware:

### 1. Generalidad e Interfaz Uniforme
Es deseable que el desarrollador no sepa cómo funciona el motor del disco o la lógica del teclado. Las rutinas de bajo nivel deben abstraer los detalles específicos exponiendo una interfaz estandarizada con llamadas uniformes: `open()`, `close()`, `read()`, `write()`, `lock()`, `unlock()`.

### 2. Eficiencia (Multiprogramación)
Dado que los dispositivos son lentos, el SO implementa multiprogramación para suspender los procesos que esperan operaciones de E/S, liberando la CPU para ejecutar otros hilos de ejecución activos.

### 3. Planificación (Scheduling)
El SO reordena la cola de peticiones de E/S pendientes para optimizar el rendimiento del dispositivo (por ejemplo, los algoritmos de planificación de disco que minimizan el movimiento del cabezal).

### 4. Buffering (Almacenamiento Intermedio)
Consiste en copiar temporalmente los datos en una zona de memoria RAM (*Buffer*) durante la transferencia.
- **Soluciona diferencias de velocidad**: El productor escribe rápido en el buffer y el consumidor lee a su propio ritmo.
- **Adapta tamaños y formatos**: Junta datos dispersos para enviarlos como un único bloque grande o viceversa.

### 5. Caching
Mantiene copias de datos accedidos recientemente en memoria RAM rápida para evitar lecturas repetitivas al lento almacenamiento secundario.

### 6. Spooling (Simultaneous Peripheral Operations On-Line)
Permite gestionar la concurrencia en dispositivos de **acceso exclusivo** (como impresoras). En lugar de dejar que un proceso bloquee la impresora, el SO intercepta la salida, la guarda temporalmente en un archivo en disco (*Spool*) y una cola del sistema se encarga de enviarla a la impresora de forma secuencial.

### 7. Manejo de Errores
El SO procesa los errores físicos (sectores dañados, pérdidas de conexión) de forma transparente o devuelve códigos de error claros al proceso. También mantiene registros históricos (*Logs*).

---

## 🔄 Formas de realizar la E/S en Software

### 🛑 E/S Bloqueante (Blocking)
El proceso que solicita la E/S es suspendido (pasa al estado *Bloqueado*) hasta que la operación se completa físicamente.
- **Pros**: Fácil de usar y de entender para el programador.
- **Contras**: Detiene la ejecución del proceso por completo.

### ⚡ E/S No Bloqueante (Non-blocking)
La llamada al sistema inicia la E/S y retorna inmediatamente con los datos que estén disponibles (o un indicador de que aún no hay datos).
- **Pros**: Permite que el proceso siga ejecutando código concurrente.
- **Ejemplos**:
  - Interfaz gráfica (GUI) que escucha teclado/mouse en un bucle continuo sin freezarse.
  - Reproductor de video que lee frames de disco mientras renderiza los anteriores en pantalla.

---

## 🧱 Arquitectura y Diseño de E/S en Capas

El software de E/S está estructurado jerárquicamente para maximizar la portabilidad e independencia:

```
+-------------------------------------------------+
|   Software a nivel de usuario (Librerías, demonios) |
+-------------------------------------------------+
|   Software independiente del SO (Buffers, cache) |
+-------------------------------------------------+
|   Controladores de Dispositivos (Drivers)       |
+-------------------------------------------------+
|   Manejadores de Interrupciones                 |
+-------------------------------------------------+
|   Hardware (Controladoras, Dispositivos)         |
+-------------------------------------------------+
```

### 1️⃣ Capa de Usuario
Implementa librerías estándar (ej: `stdio.h` en C con `printf`/`scanf`) y procesos demonio especiales (como el *Spooler* de impresión).

### 2️⃣ Software Independiente del SO
Brinda los servicios generales comunes a todos los dispositivos: interfaz uniforme de llamadas, direccionamiento abstracto, buffering, caché, asignación exclusiva y planificación general.

<img src="./images/T4IO_Slide_20.png" alt="UNIX I/O Kernel Structure" width="650"/>

### 3️⃣ Controladores de Dispositivos (Device Drivers)
Contienen el **código específico y dependiente del hardware** para gestionar un tipo de periférico.
- **Función**: Traducir comandos abstractos del kernel (`read`) en secuencias de comandos de bajo nivel para los registros de la controladora del hardware.
- Se cargan dinámicamente en el espacio del Kernel (como módulos).
- Permiten que fabricantes agreguen nuevo hardware sin necesidad de modificar o recompilar el núcleo del SO.

> 🐧 **En Linux**, existen tres tipos de drivers principales: **Carácter** (I/O serie/teclado), **Bloque** (discos con almacenamiento en búfer) y **Red** (puertos sockets).
> Se implementan como módulos que deben registrar al menos las funciones `init_module` (instalación) y `cleanup_module` (desinstalación), además de exponer operaciones estándar (`open`, `release`, `read`, `write`, `ioctl`). El acceso al hardware se hace leyendo/escribiendo bytes en los puertos indicados en `<asm/io.h>`.

### 4️⃣ Manejadores de Interrupciones (Interrupt Handlers)
Reciben las interrupciones del hardware cuando un dispositivo termina su transferencia. Su tarea es salvar los registros (contexto mínimo), despachar la interrupción al driver correspondiente y despertar al proceso bloqueado.

---

## 🛠️ Ciclo de Atención de una Petición de E/S

Veamos la traza completa desde que un programa solicita leer un archivo del disco hasta que los datos llegan a su memoria:

<img src="./images/T4IO_Slide_29.png" alt="Ciclo de atención de un Requerimiento" width="650"/>

### 📝 Paso a Paso Físico y Lógico
1. El proceso invoca una llamada al sistema bloqueante (`read()`).
2. El **software independiente del SO** mapea la ruta del archivo y delega al filesystem la traducción de nombres a bloques lógicos de disco.
3. El **Driver** traduce estos bloques lógicos a sectores físicos del disco (cilindro, pista, sector) y escribe comandos específicos en los registros del controlador.
4. El proceso es enviado a la cola de **Bloqueados** y la CPU se entrega a otra tarea.
5. El **Controlador del Dispositivo** de disco lee los datos físicamente.
6. Al finalizar la lectura, la placa genera una **Interrupción de Hardware**.
7. El **Manejador de Interrupciones** intercepta la señal, salva registros y cede el control a la rutina del driver.
8. El driver copia los bytes leídos desde los registros/buffers del controlador hacia la memoria RAM (espacio del Kernel y luego de Usuario).
9. El driver marca los datos como disponibles y el planificador mueve al proceso de vuelta a la cola de **Listos** (Ready).
10. El proceso reanuda su ejecución recibiendo el control de la CPU tras retornar de la llamada al sistema.

<img src="./images/T4IO_Slide_31.png" alt="Desde el Requerimiento hasta el Hardware" width="650"/>

---

## 📈 Rendimiento y Optimización de E/S

La Entrada/Salida es el factor que más degrada la performance de un sistema. Consume recursos valiosos debido a:
- El uso intensivo de la CPU para procesar los drivers e interrupciones.
- Los continuos cambios de contexto (*Context Switches*) al bloquear y reactivar procesos.
- La saturación del bus de memoria para copiar datos repetidamente entre las capas de hardware, buffers del kernel y el espacio de usuario.

<img src="./images/T4IO_Slide_32.png" alt="Ciclo de vida de un requerimiento de E/S" width="650"/>

### 🚀 Técnicas de Optimización
1. **Reducir cambios de contexto**: Procesar datos en bloques grandes en lugar de byte por byte.
2. **Minimizar las copias de datos**: Utilizar transferencias directas o mapeo de memoria.
3. **Bajar la frecuencia de interrupciones**:
   - Usar controladoras más inteligentes.
   - Usar transferencias grandes de datos.
   - Emplear **Polling (Espera Activa)** en situaciones de altísima velocidad de red donde sabemos que el dato estará listo inmediatamente, evitando la sobrecarga de la interrupción.
4. **Usar DMA (Direct Memory Access)**: Incorporar un chip inteligente auxiliar que se encarga de transferir datos del periférico directo a la RAM física sin intervención continua de la CPU, interrumpiendo a la CPU únicamente cuando la transferencia completa del bloque ha finalizado.

</details>

<br>

<details>
<summary><b>🔌 Parte 2: Anexo I — Arquitectura de Hardware de E/S (Mapeo de Memoria, Registros y Técnicas de I/O)</b></summary>

## 🏗️ Hardware de Entrada/Salida

La comunicación entre la CPU y los controladores de dispositivos se basa en un conjunto de componentes físicos:
- **Buses**: Canales de comunicación compartidos (de datos, direcciones y control) que interconectan la CPU, la memoria y los controladores de E/S.
- **Controladores (Controladoras)**: Chips o circuitos integrados que actúan como la interfaz lógica del hardware físico. Traducen las órdenes de la CPU en señales eléctricas de bajo nivel específicas del dispositivo.
- **Puertos de E/S / Registros**: Puntos de conexión lógicos formados por un conjunto de registros internos en la controladora (de estado, control, entrada de datos y salida de datos).

---

## 🔄 Comunicación CPU - Controladora

Para ejecutar un comando o transferir datos, la CPU escribe y lee en los registros de la controladora:
1. **Registros de Control**: La CPU escribe en ellos para ordenar acciones (ej: "hacer girar el disco", "escribir bloque").
2. **Registros de Estado**: La CPU lee de ellos para verificar la situación actual (ej: si el dispositivo está ocupado, listo, o si ocurrió un error).
3. **Registros de Datos (Entrada/Salida)**: Se usan para transferir los bytes de información hacia o desde la CPU.

### ⚙️ Comandos de E/S emitidos por la CPU:
- **Control**: Indican al dispositivo qué tarea realizar (ej: rebobinar cinta, buscar pista).
- **Test**: Comprueban estados específicos (ej: verificar si la impresora tiene papel o corriente).
- **Read / Write**: Inician la transferencia física de información.

---

## 🗺️ Mapeo de E/S: Memory-Mapped I/O vs. Isolated I/O

Existen dos filosofías de diseño de hardware para direccionar y acceder a los registros de las controladoras:

| Atributo | Correspondencia en Memoria (Memory-Mapped I/O) | E/S Aislada (Isolated I/O / Puertos de E/S) |
|---|---|---|
| **Espacio de direcciones** | Compartido. Los registros de la controladora ocupan un rango de direcciones del mismo mapa de memoria física de la RAM. | Separado. Los dispositivos tienen un mapa de direcciones exclusivo e independiente del de la RAM (Puertos de E/S). |
| **Instrucciones de CPU** | Instrucciones estándar de memoria (ej. `MOV` en assembler). No se requieren comandos específicos. | Instrucciones especiales y dedicadas exclusivas de E/S (ej: `IN` y `OUT` en x86). |
| **Líneas de control de bus** | La CPU utiliza las mismas señales de lectura/escritura de memoria. | Se requieren líneas de control físicas adicionales en el bus para indicar si el acceso es a memoria o a E/S. |
| **Pros y Contras** | ✔️ Flexibilidad absoluta (se pueden usar todos los modos de direccionamiento y operaciones de memoria). <br>❌ Consume espacio del mapa de memoria RAM. | ✔️ No desperdicia espacio de direccionamiento de RAM. <br>❌ Conjunto de instrucciones de E/S muy limitado y rígido. |

---

## 🔄 Técnicas de I/O en Hardware

Para gestionar la transferencia de datos entre la memoria principal y el periférico, existen tres técnicas clásicas:

### 1️⃣ E/S Programada (Programmed I/O) y Polling (Espera Activa)
La CPU toma el control directo y total de la operación:
1. La CPU emite un comando de lectura/escritura a la controladora.
2. La CPU entra en un bucle cerrado chequeando repetidamente el registro de estado de la controladora (**Polling / Busy-wait**).
3. Cuando el dispositivo cambia su estado a "Listo", la CPU lee/escribe el dato de la controladora y lo guarda en la RAM.
- **Desventaja**: Desperdicia masivamente ciclos de reloj de la CPU en una espera inactiva.

### 2️⃣ E/S Manejada por Interrupciones (Interrupt-Driven I/O)
Evita que la CPU espere bloqueada al dispositivo lento:
1. La CPU emite el comando a la controladora y continúa ejecutando otros procesos o tareas productivas.
2. Cuando la controladora termina la transferencia del dato, envía una señal física de **Interrupción** a la CPU a través del bus.
3. La CPU detiene temporalmente su ejecución, salva su contexto mínimo, atiende la interrupción (ejecutando el driver del dispositivo para transferir el dato a RAM) y luego retoma su tarea original.
- **Ventaja**: Mucho más eficiente que la E/S programada.
- **Desventaja**: Sigue consumiendo tiempo de CPU para transferir el dato byte por byte (o bloque por bloque) a la memoria RAM.

### 3️⃣ DMA (Direct Memory Access)
Diseñado para transferencias masivas de alta velocidad (como discos rígidos o tarjetas de red), delegando el control en un chip especializado (Controlador DMA):
1. La CPU configura el controlador DMA indicando:
   - El tipo de operación (Lectura/Escritura).
   - La dirección del dispositivo de E/S.
   - La dirección inicial en memoria RAM.
   - La cantidad de bytes/bloques a transferir.
2. El controlador DMA toma el control del bus y realiza la transferencia completa de datos directamente entre el periférico y la memoria principal, **sin intervención de la CPU**.
3. La CPU solo es interrumpida **una única vez** al final, cuando todo el bloque de datos ha terminado de ser transferido a la RAM.
- **Ventaja**: Máxima eficiencia, liberando casi por completo a la CPU del movimiento de datos pesados.

</details>

