# Primer Parcial - Primera Fecha

**Fecha:** 11/10/2025

---

## Parte A — GNU / Linux

**1) ¿Cuál de las siguientes afirmaciones acerca del arranque basado en BIOS/MBR es válida?**
- [ ] a) El MBR ocupa un sector de 512 bytes
- [ ] b) El MBR se ubica en el sector 0, cabeza 0, sector 1
- [ ] c) El código de arranque ocupa 446 bytes del MBR
- [ ] d) La tabla de particiones ocupa 64 bytes del MBR
- [ ] e) a, c y d son correctas
- [ ] f) b y d son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**2) ¿Cuál de las siguientes opciones es correcta acerca del intérprete de comandos en GNU/Linux?**
- [ ] a) En Linux el intérprete de comandos predeterminado es el mismo para todos los usuarios
- [ ] b) En Linux podemos elegir un intérprete de comandos distinto para cada usuario
- [ ] c) No puede haber usuarios del sistema que no posean ningún intérprete de comandos
- [ ] d) a, b y c son correctas
- [ ] e) a y b son correctas
- [ ] f) Todas las opciones anteriores son correctas
- [ ] g) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**3) ¿Cuál de las siguientes opciones es correcta acerca del manejo de permisos en UNIX?**
- [ ] a) Solo se pueden asignar permisos de lectura y escritura
- [ ] b) Los permisos se asignan a los archivos
- [ ] c) Los permisos se asignan a los directorios
- [ ] d) El comando chown permite cambiar el propietario de un archivo
- [ ] e) a y c son correctas
- [ ] f) a, b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**4) ¿Cuál de las siguientes opciones es correcta acerca del manejo de usuarios en GNU/Linux?**
- [ ] a) En el archivo /bin/passwd se almacenan las contraseñas encriptadas
- [ ] b) En /etc/shadow se almacenan las contraseñas encriptadas
- [ ] c) El archivo /etc/group almacena información acerca de las cuentas de usuarios
- [ ] d) El archivo /etc/passwd almacena información de usuarios en texto plano
- [ ] e) b es correcta
- [ ] f) a, b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**5) ¿Cuál de las siguientes opciones es correcta acerca del manejo de procesos en GNU/Linux?**
- [ ] a) Cada proceso es identificado unívocamente por su PID
- [ ] b) Podemos terminar un proceso mediante el comando kill
- [ ] c) El comando top permite ver información sobre los procesos en ejecución
- [ ] d) Todos los procesos tienen al menos 3 archivos abiertos (stdin, stdout y stderr)
- [ ] e) a y d son correctas
- [ ] f) a, b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**6) ¿Cuál de las siguientes opciones es correcta acerca de redirecciones en GNU/Linux?**
- [ ] a) Mediante | podemos redirigir la salida de un proceso a otro
- [ ] b) Mediante > podemos redirigir la salida de un proceso a un archivo
- [ ] c) Mediante >> podemos redirigir la salida de un proceso agregando al archivo
- [ ] d) Mediante | podemos redirigir la salida de un proceso a un archivo
- [ ] e) a y b son correctas
- [ ] f) a, b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**7) ¿Cuál de las siguientes opciones es correcta acerca de comandos en GNU/Linux?**
- [ ] a) El comando ls permite listar el contenido de un directorio
- [ ] b) El comando ln -s permite crear un enlace simbólico
- [ ] c) El comando find permite buscar texto dentro de un archivo
- [ ] d) El comando cat permite listar el contenido de un directorio
- [ ] e) a y b son correctas
- [ ] f) b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**8) ¿Cuál de las siguientes opciones es correcta acerca de UEFI y GPT?**
- [ ] a) UEFI es el sucesor de BIOS
- [ ] b) UEFI es propiedad del UEFI Forum
- [ ] c) UEFI utiliza GPT como mecanismo de particionado
- [ ] d) UEFI es compatible con el MBR tradicional
- [ ] e) a, b y c son correctas
- [ ] f) a y d son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**9) ¿Cuál de las siguientes opciones es correcta acerca del proceso de arranque en BIOS/System V?**
- [ ] a) El orden de booteo es: Se ejecuta el código de la BIOS, El hardware lee el sector de arranque, Se carga el gestor de arranque, Se carga el kernel
- [ ] b) El firmware del BIOS facilita la lectura de file systems
- [ ] c) System V maneja distintos runlevels
- [ ] d) System permite iniciar servicios de manera paralela
- [ ] e) a y c son correctas
- [ ] f) b y d son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta:**
>
> 

**10) ¿Cuáles de los siguientes comandos podrían ser utilizados para buscar contenido dentro de un archivo de texto en GNU/Linux?**
- [ ] a) cat
- [ ] b) grep
- [ ] c) ps
- [ ] d) a y b son correctas
- [ ] e) b y c son correctas
- [ ] f) Todas son correctas
- [ ] g) Ninguna de las anteriores es correcta

> **Respuesta:**
>
> 

---

## Parte B - Práctica de Procesos e Introducción a Memoria

**1. (3.5pts)** Suponga que se tiene el siguiente lote de procesos a ser ejecutados. Dado el Algoritmo Round Robin con Quantum de 3

| JOB | Inst. Llegada | CPU | E/S (recur,inst,dur) |
| :---: | :-------------: | :---: | :--------------------: |
| **1** | 0             | 11  | (R1, 5, 3)           |
| **2** | 2             | 9   | (R2, 7, 2)           |
| **3** | 3             | 12  |                      |

**a) (2.5pts)** Realice el diagrama de Gantt correspondiente al lote.

> **Respuesta:**
> 
> ```
> (Espacio para el diagrama de Gantt)
> ```

**b) (1pt)** Calcule el TPR y el TPE (incluir los cálculos realizados para llegar los valores).

> **Respuesta:**
> 
> 


**2. (1.5pts)** Dado el siguiente pseudo-código. ¿Cuántas veces se imprime en pantalla el contenido del directorio actual del proceso?

```c
x = fork()
execv("ls")
exit(0)
```
- [ ] a) 1 vez
- [ ] b) 2 veces
- [ ] c) Ninguna

> **Respuesta:**
> 
> 


**3. (2.5pts)** Dado un esquema de paginación, donde cada dirección hace referencia a 1 byte, con un tamaño de página de 2 KiB (KibiBytes, siendo 1 KiB=1024 bytes) y donde el frame 0 se comienza en la dirección física 0.

Dadas las siguientes primeras entradas de la tabla de páginas de un proceso, traduzca las direcciones lógicas indicadas a direcciones físicas.

| # de Página | # de Frame |
| :-----------: | :----------: |
| 0           | 3          |
| 1           | 5          |
| 2           | 10         |
| 3           | 13         |
| 4           | 8          |

*(Incluir los cálculos realizados para cada inciso)*

**Direcciones lógicas a traducir:**
- a) 7250
- b) 1919
- c) 5000
- d) 8192
- e) 6143

> **Respuesta y Cálculos:**
> 
> 


**4. (2.5 pts)** Suponiendo que se dispone de un espacio de direcciones virtuales de 32 bits, donde cada dirección referencia 1 byte y se utilizan 18 bits de la dirección para indicar página y 14 bits para indicar desplazamiento dentro de la página (en cada inciso se deben incluir los cálculos realizados para obtener el resultado).

**a)** ¿Cuántas páginas puede tener un proceso?
> **Respuesta:**
> 
> 

**b)** ¿Cuál es el tamaño de una página? (indicar el resultado en bytes)
> **Respuesta:**
> 
> 

**c)** Si se utiliza una tabla de páginas de 1 nivel y cada entrada en la tabla de páginas es de 2 bytes ¿cuál sería el tamaño máximo que podría alcanzar la misma? (indicar el resultado en bytes)
> **Respuesta:**
> 
> 

**d)** Si el proceso necesitará 13798 bytes para sus datos ¿cuántas páginas se requerirán para almacenarlos?
> **Respuesta:**
> 
> 

**e)** Si el proceso necesitará 84542 bytes para su código ¿cuánto sería la fragmentación interna que se producirá?
> **Respuesta:**
> 
> 

---

## Parte C - Conceptos teóricos (1pto. cada inciso)

**Verdadero o Falso**

Indicar si las siguientes afirmaciones son verdaderas o falsas:

- [ ] El modo de ejecución (Kernel y Usuario) delimita qué pueden hacer los procesos de usuario
- [ ] Cada vez que un proceso invoca una system call, pasa a modo kernel
- [ ] La PCB mantiene la información de contexto de un proceso
- [ ] El espacio de direcciones de un proceso incluye su PCB
- [ ] En algoritmos no apropiativos, los procesos se ejecutan hasta liberar la CPU
- [ ] La MMU es el hardware encargado de la gestión de memoria
- [ ] En segmentación paginada puede haber fragmentación interna y externa
- [ ] En paginación, la tabla depende de la memoria que requiere el proceso
- [ ] Existe una única cola de procesos en el sistema
- [ ] En segmentación existe una tabla por proceso

> **Respuesta (justificaciones):**
>
> 
