# 🔄 Cambio de Contexto (Context Switch) en Round Robin: De P1 a P2

**Materia**: Introducción a los Sistemas Operativos (ISO)
---
## Explicado en Criollo
Se acaba el tiempo (Interrupción): El reloj avisa que P1 consumió su quantum. El Sistema Operativo frena a P1 y toma el control.

Guardar a P1: El SO anota exactamente en qué línea y con qué datos se quedo P1, y guarda esa informacion en su archivo personal (en la PCB).

Turno del siguiente (Planificador): El SO mira la cola de espera y, como es Round Robin, llama al que sigue en la ronda, que en este caso es P2.

Cargar a P2: El SO busca el archivo (PCB) de P2, lee dónde se había quedado la última vez y carga esos datos en el procesador.

A correr de nuevo: El SO reinicia el reloj (quantum) y le da play a P2 para que siga trabajando.

---

## 🕒 Desarrollo Paso a Paso (IA)

En un algoritmo de planificación **Round Robin**, cada proceso recibe un intervalo de tiempo fijo de CPU llamado **quantum**. Cuando el proceso en ejecución (llamémoslo **P1**) consume todo su *quantum* y debe cederle el lugar al proceso siguiente en la cola (llamémoslo **P2**), ocurre el siguiente procedimiento a muy bajo nivel:

### 1. Interrupción por Reloj (*Timer Interrupt*)
El reloj de hardware del sistema operativo decrece constantemente. Al llegar a cero (es decir, el *quantum* de P1 se agotó), el hardware genera inmediatamente una interrupción. Esto fuerza a la CPU a pausar la ejecución de las instrucciones de P1.

### 2. Cambio a Modo Kernel
El procesador cambia automáticamente de **Modo Usuario** a **Modo Privilegiado o Kernel**. El control del sistema se transfiere inmediatamente a la rutina de atención de interrupciones del **Sistema Operativo**.

### 3. Resguardo de Contexto de P1 (*Save State*)
El Sistema Operativo guarda el contexto exacto en el que fue interrumpido **P1**. Esto es crítico para no perder el progreso. Se guarda principalmente:
- El valor del **Contador de Programa (PC)**, apuntando a la siguiente instrucción que P1 debía ejecutar.
- Los valores de los **Registros de la CPU** (Stack Pointer, registros de datos, flags).
Toda esta información se almacena de forma segura en el **PCB (Process Control Block)** de P1.

### 4. Actualización del Estado de P1
El SO toma el PCB de P1 y actualiza su estado, pasándolo de **En Ejecución (Running)** a **Listo (Ready)**. Acto seguido, encola a P1 al final de la **Cola de Listos (Ready Queue)** para que espere de nuevo su turno de procesador.

### 5. Llamada al Planificador (*Scheduler/Dispatcher*)
La CPU está libre. Ahora se invoca al código del **Planificador de Corto Plazo (Short-Term Scheduler)**. 
Como se utiliza *Round Robin*, el planificador extrae al primer proceso que está esperando en la cabeza de la *Cola de Listos*. En nuestro ejemplo, el nuevo proceso seleccionado es **P2**.

### 6. Restauración del Contexto de P2 (*Restore State*)
El SO busca el PCB de **P2**. Extrae todo el contexto (su Contador de Programa, Stack Pointer, registros, etc.) guardado cuando P2 fue interrumpido temporalmente en el pasado.
El SO vuelca toda esta información a los registros físicos de la CPU.

### 7. Actualizar el Estado de P2
El SO actualiza el campo de estado dentro del PCB de P2, cambiándolo de **Listo (Ready)** a **En Ejecución (Running)**.

### 8. Reinicio del Timer y Retorno a Modo Usuario
El SO reinicia el temporizador de hardware (*Timer*) configurándolo nuevamente con el *quantum* asignado, esta vez para otorgárselo a P2. 
Finalmente, el procesador vuelve a **Modo Usuario** (*Context Restore/IRET*), devolviendo el control al Contador de Programa que ahora apunta a la instrucción de P2.

---

### 🚀 Resultado: P2 está en ejecución
A partir de este microsegundo, las instrucciones procesadas en la CPU pertenecen a **P2**. El proceso P2 retoma su trabajo como si no hubiera pasado nada (ilusión de procesamiento continuo).

> [!NOTE]
> Todo este proceso intermedio se conoce como **Cambio de Contexto** (*Context Switch*). Durante el cambio de contexto, no se procesan instrucciones útiles de ningún programa de usuario, sino puro trabajo de organización administrativa. Por este motivo se dice que un cambio de contexto representa una sobrecarga (*overhead*) para el funcionamiento del reloj del sistema.
