# 💻 Resolución Práctica 1: Comandos Básicos de Linux

**Materia**: Introducción a los Sistemas Operativos (ISO)

Esta guía contiene la resolución breve, concisa y directa de los 25 ejercicios para facilitar el estudio.

---

### **1. Características de GNU/Linux**
**a.** **Características:** Es Software Libre, Multiusuario, Multitarea y Seguro.
**b.** **Otros SO:** Windows y macOS son pagos, privativos y menos personalizables.
**c.** **GNU:** "GNU's Not Unix", un sistema operativo libre que aportó el entorno y utilidades que interactúan con el kernel Linux.
**d.** **Historia:** Iniciado por Richard Stallman (1983) para reemplazar Unix con software libre. En 1991, Linus Torvalds creó el Kernel Linux que se unió al sistema GNU.
**e.** **Multitarea:** Capacidad de ejecutar múltiples procesos simultáneamente. GNU/Linux es multitarea preferente.
**f.** **POSIX:** Estándar que permite a los sistemas operativos tipo Unix ser compatibles (útil para portar programas).

### **2. Distribuciones de GNU/Linux**
**a.** **Distribución:** Una variante que incluye el kernel Linux y paquetes de GNU. Ejemplo: Ubuntu (fácil), Debian (estable), Arch Linux (actualización constante o "rolling release"), CentOS/RHEL (servidores empresariales).
**b.** **Diferencias:** Difieren en el gestor de paquetes (APT, Pacman, Yum), ciclo de actualizaciones y público objetivo.
**c.** **Debian:** SO fundado por Ian Murdock. Su objetivo es ser 100% libre, gratuito y sumamente estable.

### **3. Estructura de GNU/Linux**
**a.** Componentes: **Hardware**, **Kernel**, **Shell / Aplicaciones**.
**b.** El **Kernel** se comunica con el Hardware, gestionando recursos. El **Shell** interpreta comandos de los usuarios. Las **Aplicaciones** proporcionan funcionalidad a los usuarios.

### **4. Kernel**
**a.** **Funciones:** Gestiona CPU, acceso a memoria, sistemas de archivos, red y periféricos.
**b.** **Versión actual:** ~6.x. Antes de 2.6: número intermedio par era versión estable y número impar era de desarrollo. Hoy no se usa esa distinción de pares/impares.
**c.** Sí, a través del gestor de arranque (ej: GRUB) es posible instalar varios y elegir en el inicio.
**d.** En el directorio `/boot` u oculto en la partición EFI.

### **5. Intérprete de comandos (Shell)**
**a.** **Función:** Es la interfaz de texto que interpreta comandos ingresados y los envía al kernel.
**b.** Bash (defecto y ubicuo), Zsh (personalizable), Fish (autocompletado predictivo).
**c.** En los directorios de binarios indicados por la variable `$PATH` (ej: `/bin`, `/usr/bin`).
**d.** Porque es una aplicación más ejecutándose a nivel usuario y puede cerrarse/modificarse sin alterar el sistema operativo.
**e.** Sí, se puede usar `chsh` para modificar la asignación del intérprete guardado en `/etc/passwd`.

### **6. El Sistema de Archivos (File System)**
**a.** Estructura lógica empleada para almacenar, organizar y recuperar archivos en los medios de almacenamiento.
**b.** Es un **árbol invertido** que inicia en `/`. **FHS:** "Filesystem Hierarchy Standard", estandariza los directorios (ej: `/etc` para config, `/home` archivos personales, `/var` logs y variables).
**c.** ext4, btrfs, xfs, reiserfs.
**d.** Sí, mediante módulos o herramientas adicionales como `ntfs-3g`.

### **7. Particiones**
**a.** Divisiones lógicas del disco. Tipos: Primarias (máx 4 o 3+1), Extendidas (contiene lógicas), Lógicas.
**b.** Identificación: IDE (`/dev/hda`), SCSI/SATA/USB (`/dev/sda`).
**c.** Mínimo 2: Raíz `/` (ej. ext4) y Swap (intercambio). Puede instalarse solo en 1 combinando Swap file.
**d.** En Servidores conviene separar `/var` (por logs) y `/home` para respaldos si el SO colapsa.
**e.** `fdisk` (consola), `parted` (consola), `gparted` (entorno gráfico).

### **8. Arranque de SO**
**a.** **BIOS:** Basic Input/Output System. Chequea el hardware en el encendido.
**b.** **UEFI:** Reemplazo de BIOS. Más moderna, permite uso de mouse e internet, y tiempos de inico menores.
**c.** **MBR:** Master Boot Record (primer sector disco). **MBC:** Master Boot Code, el código ejecutable del MBR.
**d.** **GPT:** GUID Partition Table. Sustituye MBR, soporta discos de más de 2 TB y particiones ilimitadas.
**e.** **Gestor Arranque:** Carga el SO en memoria (GRUB). Se instala en el MBR o partición EFI.
**f.** Proceso: BIOS/UEFI → MBR/GPT → Bootloader (GRUB) → Kernel → Init/Systemd.
**g.** En Linux: El kernel arranca componentes centrales y le cede el paso al PID 1 (systemd o init).
**h.** Shutdown envía `SIGTERM`/`SIGKILL` a todos los procesos, desmonta particiones y apaga el equipo.
**i.** Sí. "Dual Boot" (El GRUB nos permite elegir qué SO iniciar).

### **9. Archivos y Editores**
**a.** Caso sensible (diferencia mayúsculas de minúsculas) y por la ruta dentro del árbol de directorios.
**b.** `cat` o `less` para visualizarlos, `nano` o `vim` para modificarlos.
**c.** `vim prueba.exe` ➔ Apretar tecla `i` ➔ Escribir info ➔ Esc ➔ Escribir `:wq`
**d.** `file` inspecciona el archivo basándose en la cabecera del contenido y no en su extensión `.txt/etc.`
**e.** `cd` (cambia ruta), `mkdir`/`rmdir` (crea/borra carpetas), `ln` (accesos directos/enlaces), `tail` (ver fin archivo), `cp`/`mv` (copia, mueve), `find` (busca archivo), `pwd` (ruta actual), `ls` (listar contenido).

### **10. Comandos para acciones concretas**
**a.** `mkdir ISOCSO`
**b.** `cd ISOCSO`
**c.** `touch isocso.txt isocso.csv`
**d.** `ls`
**e.** `pwd`
**f.** `find . -name "iso*"` (Búsqueda por patrón)
**g.** `df -h`
**h.** `who`
**i.** `nano isocso.txt`
**j.** `tail archivo.txt`

### **11. Funcionamientos Comandos**
`man` (manuales), `shutdown` (apagar PC), `reboot` (reiniciar), `halt` (detiene operaciones), `uname` (estado de kernel/sistema), `dmesg` (mensajes de booteo/kernel), `lspci` (detalle placa hardware), `at` (programar tareas), `netstat` (conexiones red), `head`/`tail` (10 líneas superiores/inferiores).

### **12. Procesos**
**a.** Un programa en ejecución. **PID**: ID del proceso. **PPID**: ID del Proceso Padre. Todo proceso de usuario lo tiene.
**b.** `top`/`htop` (monitor procesos real-time), `ps` (muestra procesos puntuales), `pstree` (jerarquía procesos parentales), `kill` / `killall` / `pkill` (mata proceso por ID o Nombre).

### **13. SystemV (Arranque)**
**a.** BIOS -> MBR -> Bootloader -> Kernel -> Proceso Init -> Pantalla de Login.
**b.** Init es el primer proceso y lanza los demás servicios basados en el Runlevel.
**c.** Nivel de Ejecución del sistema. Determina cómo arrancar y qué servicios (0: Apagado, 3: Consola, 5: Gráfico, 6: Reboot).
**d.** En el archivo `/etc/inittab`.
**f.** `init <Y>`. Es temporal (solo esa sesión).
**g.** Scripts que arrancan (S) y detienen (K) servicios. Alfanuméricos indican el orden de arranque (S01...).

### **14. SystemD**
**a.** Nuevo reemplazo de init SystemV. Usa demonios y logra un inicio paralelo.
**b.** Unit: Objeto/recurso como un socket o un servicio (archivo .service).
**c.** `systemctl`: Comando general para revisar estados, reiniciar y habilitar .services.
**d.** Target: Equivale a los viejos Runlevels, se usan para agrupar Units (ej: `graphical.target`).
**e.** En ejecutar `pstree`, se vería que el proceso primigenio PID 1 no es `init`, sino que es `systemd`.

### **15. Usuarios**
**a.** `/etc/passwd` (info general), `/etc/shadow` (contraseñas encriptadas), `/etc/group` (grupos).
**b.** **UID:** User ID. **GID:** Group ID. No deberían coexistir mismos UID (root sería vulnerabilidad).
**c.** Administrador supremo (UID 0).
**d.** `useradd -m -d /home/isocso -g informatica isocso` | Creación de archivo: `touch /home/isocso/temp` | Borrado: `userdel -r isocso` (`-r` borra home personal también).
**e.** `useradd` (crea usuario), `usermod` (modifica usuario), `su` (Switch User), `groupadd` (añade grupo), `passwd` (actualiza clave de usuario).

### **16. FileSystem y Permisos**
**a.** 3 Niveles: Usuario(u), Grupo(g), y Otros(o). 3 Permisos: Lectura(r), Escritura(w) y Ejecución(x).
**b.** `chmod` (permisos), `chown` (Asigna Dueño), `chgrp` (Asigna grupo).
**c.** Lectura = 4, Escritura = 2, Ejecución = 1. `chmod 755` = Dar rwx al Dueño, rx a Grupo/Otros.
**d.** Si intenta abrirlo, recibirá un error clásico "Permission denied".
**e.** Relativo: partiendo de mi dir actual: `cd ../temp`. Absoluto: usa la ruta completa desde raíz `cd /var/www/temp`.
**f.** `pwd`. Y `cd ~` vuelve a casa.
**g.** `mount` (montar medios como USB), `df` (ver ocupación particiones), `du` (peso individual de carpeta/archivo), `fdisk` (gestor de particiones CLI).

### **17. Procesos Background y Foreground**
**a.** **Foreground (Primer plano)** bloquea tu terminal hasta que acaba. **Background (Segundo plano)** trabaja ocultamente y te deja introducir más comandos.
**b.** Para lanzarlo añadir `&` -> Ej: `ping 8.8.8.8 &`. Enviar a bg proceso ya abierto: `Ctrl+Z` luego comando `bg`. Traerlo: comando `fg`.
**c.** "Tubería": Agarra un resultado e inyéctalo en otro comando: `ls -la | wc -l`.
**d.** Salidas `>` sobreescribe archivo, y `>>` añade texto al final.

### **18. Empaquetar y Comprimir**
**a.** Empaquetar es "juntar varios archivos en 1 grande" sin reducir el tamaño.
**b.** El tamaño será prácticamente idéntico al que ocupaban por individual.
**c.** Se usa comando directo con la bandera `z` (gzip): `tar -czvf empaquetado.tar.gz a b c d`.
**e.** `tar` (empaqueta), `grep` (filtro/búsqueda regex), `gzip` (famoso compresor GNU), `wc` (Word count, cuenta letras/líneas con parametro -l).

### **19. Análisis de Comandos de Root/Usuario**
Fallos de un usuario estándar detectables a simple vista:
- `chown root:root`: Falla por no tener privilegios root de escalamiento.
- `chmod 700 /etc/passwd`: Falla (no tengo dominio allí, sólo en ~/home).
- `passwd root`: Solicitará de requisito clave actual de root, que desconocemos. Falla.
- `rm *` y `cp * /home -R`: Fallan en carpetas sin permiso de superadministrador.
- `shutdown`: Falla en múltiples sistemas si un usuario sin escalado polkit invoca apagado.

### **20. Comandos requeridos**
**a.** `kill 23`
**b.** Falla, init/systemd (PID 1) ignora las señales para evitar inestabilidad.
**c.** `find /home -name "*.conf"`
**d.** `ps aux > /home/$USER/procesos`
**e.** `chmod 751 /home/$USER/xxxx`
**f.** `chmod 650 /home/$USER/yyyy`
**g.** `rm -r /tmp/*` (Cuidado porque borrarás cosas de otros usuarios si tuvieras el privilegio).
**h.** Falla al usuario común, pero como root: `chown isocso /opt/isodata`.
**i.** `pwd >> /home/$USER/donde` (`>>` concatena en vez de borrar el contenido anterior).

### **21. Acciones Integradas**
**a.** `su -`
**b.** `useradd aolthoff` -> `passwd aolthoff`
**c.** Directorio en `/home`, y se escribieron entradas en `/etc/passwd` y `/etc/shadow`.
**d, e, f, g.**:
`mkdir /tmp/miCursada` -> `cp -r /var/log/* /tmp/miCursada` -> `chown -R aolthoff:users /tmp/miCursada` -> `chmod -R 723 /tmp/miCursada`
**j.** `ps aux | wc -l`
**k.** `who -q`
**l.** `write aolthoff` (Escribe tu mensaje y finaliza con `Ctrl+D`).
**m.** `shutdown -h now`

### **22. Práctico extra y `find`**
**n.** `mkdir 12345/ ; cd 12345/`
**o.** `vi LEAME`
**p.** `chmod 017 LEAME`
**q.** `ls /etc > ~/leame`. Es válido porque Linux diferencía "LEAME" de "leame".
**r.** Función `find`. Por nombre general `find / -name archivo`. Varios usa comodines (`*`).
**s.** `find / -name "*.so" > ~/12345/ejercicioF`

### **23. Lectura de variables Shell**
- `echo $HOME` imprime la ruta de nacimiento (Ej. /home/isocso).
- `chmod 341 f2`: 3 es permiso de 'wx', no de lectura al dueño. Pese a carecer del permiso `r`, para moverse por un directorio (`cd f2`) basta y requiere únicamente la ejecución `x`.

### **24. Estructuras (Esquema PDF)**
**a.** `mv f3 /home/usuario/`
**b.** `cp f4 dir11/`
**c.** `cp f4 dir11/f7`
**d.** `mkdir ~/copia; cp -r ~/dir1/* ~/copia/`
**e.** `mv f1 archivo ; ls -l archivo`
**f.** `chmod 617 archivo`
**g.** `mv f3 f3.exe ; mv f4 f4.exe`
**h.** `chmod 023 f3.exe f4.exe`

### **25. Empaquetado Secuenciado**
**a, b.** `mkdir /tmp/logs` -> `cp -r /var/log/* /tmp/logs/`
**c.** `tar -cvf misLogs.tar /tmp/logs/`
**d.** `tar -czvf misLogs.tar.gz /tmp/logs/`
**e.** `cp misLogs.tar misLogs.tar.gz ~`
**f.** `rm -rf /tmp/logs`
**g.** `mkdir ~/dir1 ~/dir2` -> `tar -xvf misLogs.tar -C ~/dir1` -> `tar -xzvf misLogs.tar.gz -C ~/dir2`

---
> 💡 *Sugerencia*: Usa `man <comando>` siempre que dudes de qué parámetros de letras usar.
