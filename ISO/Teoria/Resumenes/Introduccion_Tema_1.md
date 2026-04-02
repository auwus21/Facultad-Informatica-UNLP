# 🖥️ Tema 1: Introducción a los Sistemas Operativos

**Materia**: Introducción a los Sistemas Operativos (ISO)

---

## 1. ¿Qué es un Sistema Operativo?
El Sistema Operativo (SO) se puede entender desde dos visiones o enfoques principales:
- **Visión Top-Down (Máquina Extendida)**: Presenta al usuario una interfaz amigable, ocultando la complejidad del hardware (los chips, memorias, etc.). Proporciona valiosas abstracciones, como archivos, procesos, y ventanas.
- **Visión Bottom-Up (Administrador de Recursos)**: Administra eficientemente los recursos del hardware de la PC (CPU, Memoria Principal, E/S) multiplexándolos en **tiempo** (ej. repartir los turnos de la CPU entre múltiples procesos) y en **espacio** (ej. dividir la memoria RAM para que convivan varios programas a la vez).

## 2. Elementos Básicos de una Computadora
Según la arquitectura general, toda computadora se fundamenta en cuatro componentes:
1. **Procesador (CPU)**: Ejecuta las instrucciones matemáticas y de control.
2. **Memoria Principal (RAM)**: Almacena datos y código de forma temporal (es memoria *volátil* o *directamente direccionable*, también llamada real).
3. **Dispositivos de E/S (Entrada/Salida)**: Memoria secundaria (discos duros), periféricos de comunicación y de usuario (monitor, teclado, mouse).
4. **Bus del Sistema**: Red de comunicación física que conecta los 3 elementos anteriores para que envíen datos e instrucciones entre sí.

## 3. Registros del Procesador
La CPU contiene memorias internas ultrarrápidas llamadas *Registros*. Se dividen en dos usos lógicos:

### 👤 Visibles por el Usuario
Disponibles a nivel ensamblador para que los programas de usuario guarden datos temporarios útiles para los algoritmos.
- **De Datos**.
- **De Direcciones**: Como el *Index Pointer*, *Segment Pointer* o el *Stack Pointer*.

### ⚙️ De Control y Estado
Utilizados principalmente por las rutinas especiales del SO para controlar el ciclo y estado de los procesos.
- **PC (*Program Counter*)**: Guarda la dirección de memoria de la próxima instrucción a ser ejecutada.
- **IR (*Instruction Register*)**: Almacena la instrucción máquina a punto de decodificarse y ejecutarse.
- **PSW (*Program Status Word*)**: Contiene los famosos *flags* (códigos de condición post-aritméticas, estado general del hardware y qué modo de privilegio tiene actualmente la CPU, sea usuario o supervisor/kernel).

## 4. El Ciclo de Instrucción
El CPU es cíclico; ejecuta programas repitiendo perennemente principalmente dos pasos elementales:
1. **Fetch (Búsqueda)**: La CPU accede a memoria basándose en la dirección del PC e inserta la orden obtenida dentro de su IR. El PC automáticamente luego incrementa para apuntar a la instrucción siguiente (+4 en arquitecturas de 32 bits).
2. **Execute (Ejecución)**: La Unidad de Control interpreta la instrucción en el IR y realiza la operación pertinente.

## 5. Interrupciones (*Interrupts*)
Una interrupción rompe el ciclo normal secuencial del procesador con tal de obligarlo a atender un contexto prioritario, comúnmente disparado por dispositivos de E/S lentos que dicen "¡Ya terminé de leer el disco!".

Cuando ocurre una interrupción, el SO detiene lo que la máquina hacía para correr el **Interrupt Handler** (la subrutina técnica para administrar el hardware en conflicto).

> [!WARNING]
> **Interrupciones Enmascarables vs. No Enmascarables**
> - **No Enmascarables**: Tienen máxima urgencia. El CPU no puede negarse a ellas bajo ninguna circunstancia (Ej. colapso de hardware, fallo de RAM).
> - **Enmascarables**: La CPU posee una compuerta (*mask*) para desactivarlas a conveniencia o dejarlas "esperando". Se usa en tareas del kernel críticas donde un corte repentino arruinaría las estructuras de datos.

### ¿Múltiples Interrupciones a la vez?
Los SO modernos previenen errores de solapamiento utilizando:
- Desactivación momentánea en serie (ninguna segunda interrupción entra si ya atiendo la primera).
- Esquema de **Prioridades** rígidas (una interrupción de nivel 5 puede interrumpir al *Handler* de una interrupción de nivel 2).

<br>

## 6. Anexo: Llamadas al Sistema (System Calls)
Las *System Calls* son utilidades críticas del SO para que un proceso solitario alcance recursos restringidos (imprimir algo en pantalla, cerrar el proceso). 
La magia que hay detrás para mandar los parámetros difieren dependiendo de cuántos *bits* tenga la computadora de destino.

Tomemos como ejemplo cómo el programa más básico, un **Hello World**, manda a imprimir texto a la consola vía el clásico `write` y termina mediante un `exit`:

| Característica | Arquitectura x86 (32 bits) | Arquitectura x86-64 (64 bits) |
|--------------|---------------------------|----------------------|
| **Activador de la Syscall** | Instrucción `int 0x80` (*Interrupción por software*) | Instrucción `syscall` (*Fast system call*) |
| **ID de syscall `write`** | `4` | `1` |
| **ID de syscall `exit`** | `1` | `60` |
| **Registro objetivo de la ID** | Registro principal `EAX` | Registro principal `RAX` |
| **Pasaje de Parámetros** | Se realiza utilizando `EBX`, `ECX` y `EDX` en ese orden particular. | Se aprovechan `RDI`, `RSI` y `RDX`. |

> [!NOTE] 
> Entender ambas arquitecturas permite dilucidar el rol íntimo de la optimización del modo Kernel con el tiempo. Los modernos procesadores de **64 bits usan una instrucción nativa `syscall`** en vez de tratar las peticiones del SO como simples "interrupciones generalizadas" como lo hacía intel hace décadas (`int 80h`), logrando mayor velocidad de respuesta al cambiar menos registros.
