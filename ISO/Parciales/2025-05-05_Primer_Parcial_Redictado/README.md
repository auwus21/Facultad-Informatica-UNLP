# Primer Parcial - Primera Fecha (Redictado)

**Fecha:** 05/05/2025

---

## Parte A — GNU / Linux

**1) ¿Cuál de las siguientes configuraciones de discos es válida en BIOS/MBR?**
- [ ] a) 4 particiones primarias
- [ ] b) 3 primarias y 1 extendida
- [ ] c) 1 primaria y 2 extendidas
- [ ] d) 4 particiones primarias y 1 lógica
- [ ] e) 3 primarias y 1 lógica
- [x] f) a y b son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: F**
> ✅ **Correcto (1/1):** MBR soporta hasta 4 particiones primarias o 3 primarias y 1 extendida.

**2) ¿Cuál de las siguientes opciones es correcta acerca del intérprete de comandos en GNU/Linux?**
- [ ] a) En Linux el intérprete de comandos predeterminado es Bash y no puede cambiarse
- [ ] b) En Linux podemos elegir un intérprete de comandos distinto para cada usuario
- [ ] c) Solo el usuario root puede utilizar el intérprete de comandos
- [ ] d) El intérprete de comandos nos permite interactuar con el kernel del SO
- [ ] e) a y b son correctas
- [x] f) b y d son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: F**
> ✅ **Correcto (1/1):** En Linux podemos cambiar el intérprete por usuario, y este es el encargado de interactuar con el Kernel.

**3) ¿Cuál de las siguientes opciones es correcta acerca del manejo de permisos en UNIX?**
- [ ] a) Se pueden asignar permisos de lectura, escritura y ejecución
- [ ] b) Si un archivo tiene permisos 666 puede ser ejecutado
- [ ] c) El comando chmod modifica permisos de archivos
- [ ] d) El comando chown permite cambiar el propietario de un archivo
- [x] e) a y c son correctas
- [ ] f) a, b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: E**
> ✅ **Correcto (1/1):** Ojo, la afirmación "d" también es verdadera (el comando `chown` cambia el propietario). Como no había opción "a, c y d", la "E" engloba las más básicas.

**4) ¿Cuál de las siguientes opciones es correcta acerca del manejo de usuarios en GNU/Linux?**
- [ ] a) El archivo /etc/passwd almacena la ruta donde se guardan las claves
- [ ] b) En /etc/passwd se almacenan encriptadas las passwords
- [ ] c) En /etc/shadow se almacena información del intérprete de comandos del usuario
- [x] d) En /etc/shadow se almacenan las claves de cada usuario
- [ ] e) b y d son correctas
- [ ] f) c y d son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: D**
> ✅ **Correcto (1/1):** Efectivamente la única afirmación verdadera es la D (En `/etc/shadow` se guardan las claves encriptadas).

**5) ¿Cuál de las siguientes opciones es correcta acerca del manejo de procesos en GNU/Linux?**
- [ ] a) Cada proceso es identificado unívocamente a través de su PID
- [ ] b) Si al ejecutar un proceso se agrega & al final, se ejecuta en background
- [ ] c) El comando ps permite ver información sobre los procesos en ejecución
- [ ] d) El comando kill permite terminar un proceso
- [ ] e) a y b son correctas
- [ ] f) a, b y c son correctas
- [x] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: G**
> ✅ **Correcto (1/1):** Todas las descripciones sobre PIDs, procesos en background (`&`), `ps` y `kill` son ciertas.

**6) ¿Cuál de las siguientes opciones es correcta acerca del manejo de procesos / redirecciones?**
- [ ] a) Mediante pipe (|) podemos comunicar procesos
- [ ] b) Mediante > podemos redirigir la salida de un proceso a un archivo
- [ ] c) Mediante | podemos redirigir la salida de un proceso a otro proceso
- [ ] d) Mediante > o >> podemos comunicar procesos
- [ ] e) a y b son correctas
- [x] f) a, b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: F**
> ✅ **Correcto (1/1):** El pipe `|` comunica procesos y el `>` redirige la salida a un archivo. (La "d" era falsa porque `>` y `>>` no comunican procesos entre sí).

**7) ¿Cuál de las siguientes opciones es correcta acerca de las licencias del kernel de GNU/Linux?**
- [x] a) Se distribuye bajo licencia GPL
- [ ] b) Se distribuye bajo licencia GPL pero no puede modificarse
- [ ] c) Se distribuye bajo licencia FOSS
- [ ] d) Se distribuye libremente
- [ ] e) a y c son correctas
- [ ] f) a, c y d son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: A**
> ❌ **Incorrecto (0/1):** Elegiste la D. El kernel de Linux se distribuye específicamente bajo la **Licencia Pública General de GNU (GPL)**. La "D" es demasiado genérica y omite el concepto fundamental de la licencia GPL que siempre se evalúa.

**8) ¿Cuáles de las siguientes afirmaciones son correctas acerca de comandos de shell en GNU/Linux?**
- [ ] a) El comando mv lista los archivos ejecutables
- [ ] b) El comando ls lista los procesos en ejecución
- [ ] c) El comando ls lista archivos y directorios
- [ ] d) El comando cat /etc/passwd muestra el contenido del archivo
- [ ] e) El comando echo /etc/passwd muestra el contenido del archivo
- [x] f) c y d son correctas
- [ ] g) b y c son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: F**
> ✅ **Correcto (1/1):** `ls` lista directorios y `cat` muestra el contenido de archivos.

**9) ¿Cuál de las siguientes opciones es correcta acerca del arranque?**
- [ ] a) El orden de booteo es: Se ejecuta el código de la BIOS, El hardware lee el sector de arranque, Se carga el kernel, Se carga el gestor de arranque
- [ ] b) El firmware del BIOS facilita la lectura de file systems
- [x] c) UEFI utiliza GPT como mecanismo de particionado
- [ ] d) UEFI no es compatible con MBR tradicional
- [ ] e) a y b son correctas
- [ ] f) b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: C**
> ❌ **Incorrecto (0/1):** Elegiste la F. La afirmación "b" (El firmware del BIOS facilita la lectura de file systems) es **FALSO**. La clásica BIOS no entiende de Sistemas de Archivos, solo lee sectores crudos del disco (MBR). UEFI sí es capaz de entender sistemas de archivos (FAT32).

**10) ¿Cuál de las siguientes opciones es correcta acerca de comandos en GNU/Linux?**
- [ ] a) El comando kill permite terminar un proceso
- [ ] b) El comando ps permite ver procesos
- [ ] c) El comando top permite ver procesos en ejecución
- [ ] d) El comando htop permite ver procesos
- [ ] e) a y b son correctas
- [ ] f) a, b y c son correctas
- [x] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: G**
> ✅ **Correcto (1/1):** `kill`, `ps`, `top` y `htop` son todos comandos válidos para el manejo de procesos.

---

## Parte B - Práctica de Procesos e Introducción a Memoria

**1) (3pts)** Suponga que se tiene la siguiente tabla de procesos a ser ejecutados.

| JOB | Inst. Llegada | CPU | E/S (recur,inst,dur) |
| :---: | :-------------: | :---: | :--------------------: |
| **1** | 0             | 5   | (R1, 2, 3)           |
| **2** | 0             | 5   | (R2, 3, 2)           |
| **3** | 3             | 3   |                      |

Dado el algoritmo: **RR Q=3 TV**

**a) (2pts)** Realice el diagrama de Gantt.
> **Respuesta:**
> 
> ```
> (Espacio para el diagrama de Gantt)
> ```

**b) (1pt)** Calcule el TPR y el TPE.
> **Respuesta:**
> 
> 


**2) (2pts)** Suponga un SO que utiliza el siguiente algoritmo para la planificación de procesos:
*"Se trata de un algoritmo de dos colas. Una de ellas tiene mayor prioridad. El SO selecciona los procesos analizando las colas comenzando desde la de mayor prioridad. Cada cola es administrada por un algoritmo de RR con un Quantum de 4 unidades y TV. Cuando un proceso que se está ejecutando abandona la CPU antes de que agote su quantum es movido a la cola de menor prioridad; mientras que si un proceso en ejecución completa su quantum es movido a la cola de mayor prioridad. Todos los procesos nuevos son colocados en la cola de mayor prioridad."*

**a)** ¿A qué tipo de proceso (carga de E/S o carga de CPU) beneficia el algoritmo? Justifique.
> **Respuesta:**
> 
> 

**b)** ¿El algoritmo puede provocar inanición de procesos? Justifique.
> **Respuesta:**
> 
> 


**3) (2.5pts)** Dado un esquema donde cada dirección hace referencia a 1 byte, con páginas de 2 KiB (KibiBytes), donde el frame 0 se encuentra en la dirección física 0. Con las siguientes primeras entradas de la tabla de páginas de un proceso, traduzca las direcciones lógicas indicadas a direcciones físicas:

| Página | Marco |
| :----: | :---: |
| 0      | 16    |
| 1      | 13    |
| 2      | 9     |
| 3      | 2     |
| 4      | 0     |

**Direcciones lógicas a traducir:**
- a) 5120:
- b) 3242:
- c) 1578:
- d) 2048:
- e) 8191:

> **Respuesta y Cálculos:**
> 
> 


**4) (2.5pts)** Suponiendo que se dispone de un espacio de direcciones virtuales de 32 bits, donde cada dirección referencia 1 byte; suponiendo además que el tamaño de página utilizado es de 1024 bytes:

**a)** ¿Cuál sería el tamaño máximo de un proceso?
> **Respuesta:**
> 
> 

**b)** ¿Cuántas páginas puede tener un proceso?
> **Respuesta:**
> 
> 

**c)** ¿Si cada entrada en la tabla de páginas es de 2 bytes, cuál sería el tamaño máximo que podría alcanzar la misma?
> **Respuesta:**
> 
> 

**d)** ¿Cuántos marcos tendrá la memoria física si disponemos de 16 GiB?
> **Respuesta:**
> 
> 

**e)** ¿Si el proceso necesitará 5450 bytes para sus datos, cuántas páginas se necesitan para almacenarlos?
> **Respuesta:**
> 
> 

***)** Si consideramos páginas de tamaño 2048 bytes, en lugar de 1024 ¿cuantas entradas tendrá cada tabla de páginas de cada proceso?
> **Respuesta:**
> 
> 

---

## Parte C — Verdadero o Falso

Indicar si las siguientes afirmaciones son verdaderas o falsas:

- [ ] El modo de ejecución (Kernel y Usuario) delimita qué pueden hacer los procesos de usuario
- [ ] Cada vez que un proceso invoca una system call, pasa a modo kernel para ejecutar el código correspondiente
- [ ] La PCB (Process Control Block) mantiene la información de contexto de un proceso
- [ ] El espacio de direcciones de un proceso incluye su PCB
- [ ] En algoritmos de planificación no apropiativos, los procesos se ejecutan hasta liberar la CPU
- [ ] La MMU (Memory Management Unit) es el dispositivo de hardware encargado de la gestión de memoria
- [ ] En segmentación paginada puede existir fragmentación interna y externa
- [ ] En paginación, la tabla de páginas depende de la cantidad de memoria que requiere el proceso
- [ ] Existe una única cola de procesos en el sistema
- [ ] En segmentación existe una tabla por proceso

> **Respuesta (justificaciones):**
> 
> 
