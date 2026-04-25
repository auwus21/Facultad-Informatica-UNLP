# Primer Parcial - Segunda Fecha

**Fecha:** 19/05/2025  
**Tema:** 1

---

## Parte A — GNU / Linux

**1) ¿Cuál de las siguientes opciones es correcta acerca de los discos en GNU/Linux?**
- [x] a) Para instalar Linux como minimo necesitamos una partición partición raiz (/)
- [ ] b) Mediante UUID debemos indicar qué disco debe ir conectado como master
- [ ] c) Siempre se debe definir un algún punto de montaje para la para el "/home archivo
- [ ] d) No es posible particionar un disco en Linux
- [ ] e) a, b, d son correctas
- [ ] f) a, c, d son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: A**
> ✅ **Correcto (1/1):** Para instalar Linux, lo único estrictamente obligatorio es la partición raíz `/`.

**2) ¿Cuál de las siguientes opciones es correcta acerca de la instalación de Sistemas Operativos?**
- [ ] a) En un mismo equipo pueden existir instalaciones de distintos S.O.
- [ ] b) Es posible tener más de una distribución de Linux instalada en un mismo equipo
- [ ] c) La tabla de particiones se almacena en el MBR después del byte 446
- [ ] d) En una PC estándar se puede instalar cualquier distribución de GNU/Linux de cualquier arquitectura
- [x] e) a, b, c son correctas
- [ ] f) a, b, d son correctas
- [ ] g) Todas las opciones son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: E**
> ❌ **Incorrecto (0/1):** Elegiste la F. La respuesta correcta es la E. La afirmación "d" es falsa porque no se puede instalar "cualquier distribución en cualquier arquitectura" (ej. no podés correr x86 en ARM). La "a", "b" y "c" sí son ciertas.

**3) ¿Cuál de las siguientes opciones es correcta acerca del manejo de permisos en UNIX?**
- [ ] a) Solo se pueden asignar permisos de lectura y escritura
- [x] b) Los permisos se asignan a los archivos y/o carpetas
- [ ] c) Un archivo solo puede ser ejecutado por su dueño.
- [ ] d) El comando chown permite cambiar los permisos de un archivo
- [ ] e) a y c son correctas
- [ ] f) a, b, c son correctas
- [ ] g) Ninguna opción anterior es correcta

> **Respuesta: B**
> ❌ **Incorrecto (0/1):** Elegiste la G. Sin embargo, la afirmación "b" (Los permisos se asignan a los archivos y/o carpetas) es totalmente verdadera (las carpetas son archivos en Unix). Por lo tanto, la opción correcta era la B.

**4) ¿Cuál de las siguientes opciones es correcta acerca del manejo de usuarios en GNU/Linux?**
- [ ] a) En el archivo /etc/passwd se almacena información acerca del nombre de usuario
- [ ] b) En /etc/passwd se almacena información acerca del intérprete de comandos que utilizará el usuario
- [ ] c) El archivo /etc/passwd puede ser editado con un editor de textos
- [ ] d) El archivo /etc/passwd puede ser modificado por cualquier usuario
- [ ] e) a y b son correctas
- [x] f) a, b, c son correctas
- [ ] g) b, c, d son correctas
- [ ] h) Todas las opciones son correctas

> **Respuesta: F**
> ✅ **Correcto (1/1):** Las afirmaciones "a", "b" y "c" referidas a `/etc/passwd` son ciertas. Solo lo puede modificar el root, así que la "d" es falsa.

**5) ¿Cuál de las siguientes opciones es correcta acerca del manejo de procesos en GNU/Linux?**
- [ ] a) Por defecto tienen 1 solo archivo abierto (stdfile)
- [ ] b) El comando bg permite correr un job en background
- [ ] c) El comando fg nos permite correr un job en foreground
- [ ] d) El comando end permite terminar un proceso
- [ ] e) b y d son correctas
- [x] f) b y c son correctas
- [ ] g) Todas las opciones anteriores son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: F**
> ✅ **Correcto (1/1):** `bg` ejecuta procesos parados en background, y `fg` los trae al foreground. "a" y "d" son falsas.

**6) Luego de la ejecución del siguiente comando: `chmod 754` sobre el archivo `file.txt`. ¿Cuál de las siguientes opciones es correcta si listamos el estado del archivo?**
- [ ] a) `-rw-rw-rw- 1 user group 4 may 19 10:30 file.txt`
- [x] b) `-rwxr-xr-- 1 user group 4 may 19 18:30 file.txt`
- [ ] c) `drwxr-xr-- 1 user group 4 may 19 18:30 file.txt`
- [ ] d) `d-rw-rw-r-- 1 user group 4 may 19 18:30 file.txt`

> **Respuesta: B**
> ❌ **Incorrecto (0/1):** Elegiste la C. La C indica un directorio (`d` al principio) pero el nombre indica `file.txt`. Los permisos 754 corresponden a `rwx` (7), `r-x` (5) y `r--` (4) sobre un archivo normal (`-`), por lo tanto la correcta es la B.

**7) ¿Cuál de las siguientes opciones es correcta acerca del Kernel de Linux?**
- [ ] a) Es de tipo monolitico hibrido
- [ ] b) La imagen del kernel se encuentra en /boot
- [ ] c) Es distribuido bajo la licencia GPL (General Public License)
- [ ] d) Su código es privado y no se puede ver
- [ ] e) a y b son correctos
- [ ] f) b y d son correctos
- [x] g) b y c son correctas
- [ ] h) Todas las opciones anteriores son correctas
- [ ] i) Ninguna de las anteriores es correcta

> **Respuesta: G**
> ✅ **Correcto (1/1):** La imagen suele estar en `/boot` y el código está bajo GPL.

**8) El siguiente comando: `ls | wc -l > $HOME/elementos-en-directorio.txt`**
- [ ] a) Retorna la cantidad de usuarios en el sistema
- [x] b) Crea un archivo "elementos-en-directorio.txt" en el directorio home del usuario y guarda la cantidad de elementos del directorio actual
- [ ] c) Crea un archivo "elementos-en-directorio.txt" en el directorio home del usuario y guarda un listado de todos los elementos del directorio actual
- [ ] d) a y c son correctas
- [ ] e) Ninguna opción anterior es correcta

> **Respuesta: B**
> ❌ **Incorrecto (0/1):** Elegiste la C. El comando `wc -l` cuenta la CANTIDAD de líneas (es decir, la cantidad de elementos), no guarda el listado de archivos en sí. Por lo tanto la correcta es la B.

**9) ¿Cuál de las siguientes opciones es correcta acerca de UEFI y GPT?**
- [ ] a) UEFI es el sucesor de BIOS
- [ ] b) UEFI es propiedad del UEFI Forum
- [ ] c) UEFI utiliza GPT como mecanismo de particionado
- [ ] d) UEFI es compatible con el MBR tradicional
- [ ] e) a, b, c son correctas
- [ ] f) a, b, d son correctas
- [x] g) Todas las opciones son correctas
- [ ] h) Ninguna opción anterior es correcta

> **Respuesta: G**
> ❌ **Incorrecto (0/1):** Elegiste la E. UEFI es totalmente compatible con discos MBR tradicionales (mediante su módulo CSM/Legacy Boot), por lo que la "d" también era cierta, haciendo que TODAS sean correctas (G).

**10) ¿Cuáles de los siguientes son considerados editores de texto en GNU/Linux?**
- [ ] a) cat
- [ ] b) vim
- [ ] c) echo
- [ ] d) nano
- [ ] e) ls
- [x] f) b y d son correctas
- [ ] g) b y e son correctas
- [ ] h) todas son correctas
- [ ] i) ninguna es correcta

> **Respuesta: F**
> ✅ **Correcto (1/1):** Tanto `vim` como `nano` son reconocidos editores de texto.

---

## Parte B - Práctica de Procesos e Introducción a Memoria

**1) (3pts)** Suponga que se tiene la siguiente tabla de procesos a ser ejecutados.

| JOB | Inst. Llegada | CPU | E/S (recur,inst,dur) |
| :---: | :-------------: | :---: | :--------------------: |
| **1** | 0             | 8   | (R1, 5, 3)           |
| **2** | 0             | 8   | (R1, 4, 3)           |
| **3** | 2             | 6   | (R1, 4, 3)           |

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


**2) (2.5pts)** Dado un esquema de segmentación donde cada dirección hace referencia a 1 Byte y la siguiente tabla de segmentos de un proceso, traduzca de corresponder, las direcciones lógicas indicadas a direcciones físicas.
*(Dir. Log. representada por: `segmento:desplazamiento`)*

| Segmento | Dir. Base | Tamaño |
| :------: | :-------: | :----: |
| 0        | 102       | 12500  |
| 1        | 28699     | 24300  |
| 2        | 68010     | 15855  |
| 3        | 80001     | 400    |

**Direcciones lógicas a traducir:**
- a) `0000:9001`
- b) `0001:24301`
- c) `0002:5678`
- d) `0001:18976`
- e) `0003:0`

> **Respuesta y Cálculos:**
> 
> 


**3) (2.5pts)** Dado un esquema de paginación puro, donde cada dirección hace referencia a 1 Byte, direcciones de 32 bits y tamaño de página de 2048 Bytes. Suponga además un proceso P1 que necesita 51358 Bytes para su código y 68131 Bytes para sus datos. Incluya los cálculos realizados para llegar a las respuestas.

**a)** ¿Cuántas páginas máximo puede tener el proceso P1?
> **Respuesta:**
> 
> 

**b)** Si el sistema cuenta con 2GiB (Gibibytes) de RAM, ¿cuántos marcos habría disponibles para utilizar?
> **Respuesta:**
> 
> 

**c)** ¿Cuántas páginas necesita P1 para almacenar su código?
> **Respuesta:**
> 
> 

**d)** ¿Cuántas páginas necesita P1 para almacenar sus datos?
> **Respuesta:**
> 
> 

**e)** ¿Cuántos Bytes habría de fragmentación interna entre lo necesario para almacenar su código y sus datos? *Ayuda: Recuerde que en una misma página no pueden convivir código y datos.*
> **Respuesta:**
> 
> 

***)** Si cada entrada en la tabla de páginas es de 3 Bytes, ¿cuál sería el tamaño máximo en Bytes que podría alcanzar la misma?
> **Respuesta:**
> 
> 


**4) (2pts)** Indicar qué imprimiría en pantalla el siguiente pseudo-código. No se preocupe por el orden en que los mensajes serán impresos.

```c
printf("hola")
np = fork()
printf("2da. fecha")
if np == 0 {
    printf("A Rendir")
    execv("ls")
    printf("Termine")
    exit(0)
}
printf("Aprobaste?")
exit(0)
printf("chau")
```

> **Respuesta:**
> 
> 
