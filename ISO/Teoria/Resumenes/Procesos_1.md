# 📝 Tema 2: Procesos (Parte 1)

**Materia**: Introducción a los Sistemas Operativos (ISO)
**Fuente**: *Sistemas Operativos Modernos* (Tanenbaum) y *Sistemas Operativos* (Stallings)

---

## 1. Definición de Proceso
Un proceso es, fundamentalmente, un **programa en ejecución**. 
A los fines prácticos de la materia, se utilizan como sinónimos los términos **tarea**, **job** y **proceso**.

### Diferencias entre un Programa y un Proceso

| Programa | Proceso |
|----------|---------|
| Es **estático** (código almacenado en disco). | Es **dinámico** (programa ejecutándose en memoria). |
| No tiene *Program Counter* (Contador de Programa). | Tiene *Program Counter*. |
| Existe desde que se edita hasta que se borra. | Su ciclo de vida comprende desde que se solicita ejecutar hasta que termina. |

## 2. El Modelo de Proceso
En un entorno de **multiprogramación**, múltiples procesos pueden residir en memoria simultáneamente.
El modelo conceptual considera a cada proceso como **secuencial e independiente**.
Si se cuenta con una sola CPU, **solo un proceso se encontrará activo** en cualquier instante dado.

## 3. Componentes de un Proceso
Un proceso (para poder ejecutarse) incluye como mínimo las siguientes secciones:
- **Sección de Código (Text)**: Las instrucciones a ejecutar.
- **Sección de Datos**: Las variables globales.
- **Stack(s)**: Datos temporarios como parámetros, variables locales y direcciones de retorno.

### Stacks
- Un proceso cuenta con **uno o más stacks** (generalmente: uno para modo Usuario y otro para modo Kernel).
- Se crean automáticamente y su medida se ajusta en *run-time* (tiempo de ejecución).
- Está formado por **stack frames** que son empujados (*pushed*) al llamar a una rutina y sacados (*popped*) cuando se retorna de ella.
- Cada *stack frame* contiene:
  - Parámetros de la rutina (variables locales).
  - Datos necesarios para recuperar el *stack frame* anterior (Contador de Programa y valor del *Stack Pointer* al momento del llamado).

## 4. Atributos de un Proceso
Cada proceso posee información que lo identifica y define su contexto:
- **Identificación**: ID del proceso (**PID**) y ID del proceso padre (**PPID**).
- **Usuario**: ID del usuario que disparó el proceso.
- **Grupo**: Grupo al que pertenece el usuario.
- **Terminal**: En ambientes multiusuario, desde qué terminal se ejecutó.

## 5. Bloque de Control de Proceso (PCB)
El PCB (*Process Control Block*) es la estructura de datos asociada al proceso. Representa su abstracción para el Sistema Operativo.
- **Existe un PCB por proceso**.
- Es lo primero que se crea cuando nace el proceso, y lo último que se borra cuando termina.
- Contiene toda la información asociada:
  - PID, PPID.
  - Valores de los registros de la CPU (Program Counter, Acumulador, etc.).
  - Planificación (estado, prioridad, tiempo consumido en CPU, etc.).
  - Ubicación en memoria.
  - Accounting (estadísticas de uso).
  - Estado de Entrada/Salida (E/S pendientes).

## 6. Espacio de Direcciones
Es el conjunto de direcciones de memoria que ocupa el proceso (Stack, Text y Datos).
- **Importante**: El espacio de direcciones *no incluye* su PCB o tablas asociadas mantenidas por el SO.
- En **modo usuario**, un proceso solo puede acceder a su propio espacio de direcciones.
- En **modo kernel**, el SO puede acceder a estructuras internas (como el PCB) o a espacios de direcciones de cualquier proceso.

## 7. Contexto y Cambio de Contexto

### Contexto de un Proceso
Incluye **toda la información** que el SO necesita para administrar el proceso, y que la CPU necesita para ejecutarlo correctamente. 
Son parte del contexto: registros de la CPU, Contador de Programa, prioridad, E/S pendientes, entre otros.

### Cambio de Contexto (*Context Switch*)
Se produce cuando la CPU deja de ejecutar un proceso para pasar a ejecutar otro.
1. Se debe **resguardar el contexto** del proceso saliente (que pasa a espera).
2. Se debe **cargar el contexto** del nuevo proceso (se reanuda desde la última instrucción ejecutada guardada en su PC).
*Nota:* El cambio de contexto es **tiempo no productivo** de CPU. El tiempo que consume dependerá del soporte de hardware.

## 8. El Kernel del Sistema Operativo
El Kernel es un conjunto de módulos de software y se ejecuta en el procesador. 
Existen diferentes enfoques de diseño respecto a la relación entre el Kernel y los procesos:

### Enfoque 1: Kernel como Entidad Independiente
- El Kernel se ejecuta **fuera de todo proceso**.
- Posee su propia región de memoria y su propio stack.
- Cuando hay una interrupción o *System Call*, se guarda el contexto del proceso y el control pasa al Kernel.
- Al finalizar, devuelve el control al proceso original o a otro.
- **Aclaración**: El Kernel **NO** es un proceso. Se ejecuta como entidad independiente en modo privilegiado.

### Enfoque 2: Kernel "dentro" del Proceso
- El código del Kernel se encuentra **dentro del espacio de direcciones de cada proceso** (es memoria compartida por todos los procesos).
- El Kernel se ejecuta en el **mismo contexto** del proceso de usuario.
- Dentro del proceso conviven el código de usuario y el código del kernel.
- Se tienen dos stacks por proceso: uno para el *Modo Usuario* y otro para el *Modo Kernel*.
- Las interrupciones son atendidas en el contexto del proceso actual, cambiando a *Modo Kernel*, lo que resulta más económico en términos de rendimiento que un cambio de contexto completo.
