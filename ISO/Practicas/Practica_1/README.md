# 💻 Resolución Práctica 1: Comandos Básicos de Linux

**Materia**: Introducción a los Sistemas Operativos (ISO)

*Respuestas directas, resumidas y en formato de lista para facilitar un estudio dinámico.*

---

## 1. Características de GNU/Linux
- **a. Características:** Libre, Multiusuario, Multitarea y Seguro.
- **b. Otros SO:** Windows y macOS en contraste son pagos y privativos.
- **c. GNU:** "GNU's Not Unix", un sistema operativo libre que aportó las herramientas de sistema.
- **d. Historia:** Iniciado por Richard Stallman (1983). Linus Torvalds sumó el Kernel en 1991.
- **e. Multitarea:** Puede ejecutar muchos procesos a la vez. GNU/Linux sí la hace y de forma nativa.
- **f. POSIX:** Estándar que permite compatibilidad de programas entre sistemas tipo Unix (ej: Linux, BSD, macOS).

## 2. Distribuciones
- **a. Distribución:** Es el Kernel Linux unido a paquetes GNU. (Ej: Ubuntu, Debian, Arch Linux, Fedora).
- **b. Diferencias:** Gestor de paquetes (apt, dnf, pacman), frecuencia de actualización y qué traen preinstalado.
- **c. Debian:** Fundada por Ian Murdock. Objetivo: Ser un sistema universal, muy estable y 100% libre.

## 3. Estructura
- **a. Componentes:** Hardware, Kernel, Shell / Aplicaciones.
- **b. Funcionamiento:** El Kernel administra al hardware; el Shell obedece e interpreta al usuario; las Aplicaciones brindan las herramientas de trabajo.

## 4. Kernel
- **a. Funciones:** Gestiona CPU, procesos, memoria y todo el hardware/drivers.
- **b. Versión:** Actual ~6.x. Antes de la 2.6: la versión par era estable y la impar en desarrollo. Ya se discontinuó esa norma.
- **c. Varios kernels:** Sí, se instalan juntos y se escoge en el menú de encendido (GRUB).
- **d. Ubicación:** En el directorio `/boot`.

## 5. Shell
- **a. Función:** Interfaz de texto que oficia de intermediario entre comandos y el núcleo.
- **b. Opciones:** Bash (más común), Zsh (personalizable), Fish (autocompletado predictivo).
- **c. Comandos:** En `$PATH` habitualmente (directorios `/bin`, `/usr/bin`).
- **d. Separación:** El shell es solo una "app". Por ende si falla y se cierra, el sistema operativo no colapsa.
- **e. Personalizar:** Sí. Se guarda en `/etc/passwd`. Cada usuario configura el suyo con el comando `chsh`.

## 6. File System
- **a. Qué es:** Metodología y estructura lógica de cómo se guarda la información en el disco plano.
- **b. FHS:** Estándar de árbol de directorios que empieza en raíz `/` (Ej: `/etc` config, `/var` variables/logs).
- **c. Extensiones:** ext4, xfs, btrfs.
- **d. Otras(FAT/NTFS):** Linux las puede montar y leer usando drivers del Kernel (ej. módulo `ntfs-3g`).

## 7. Particiones
- **a. Tipos:** Primarias (hasta 4 en MBR), Extendidas (Aloja más sub-particiones), Lógicas.
- **b. Nombres:** IDE antiguos (`/dev/hda`), USB/SATA (`/dev/sda`, `sdb`).
- **c. Mínimo:** 2 particiones (La Raíz `/` y habitualmente la memoria de sobreesfuerzo `Swap`).
- **d. Perfil Servidor:** Conviene poner en particiones aisladas a `/var` para que los logs no coman capacidad de la raíz.
- **e. Softwares:** `fdisk` (CLI), `parted` (CLI), `gparted` (Interfaz Gráfica).

## 8. Arranque
- **a. BIOS:** Firmware en la mother. Inicializa y comprueba fallos del hardware.
- **b. UEFI:** Nuevo BIOS. Soporta gráficos ricos, mouse, internet pre-sistema y más rapidez.
- **c. MBR/MBC:** El MBR es el 1er sector físico del disco, el MBC es el código grabado adentro que activa el arranque.
- **d. GPT:** Nuevo formato de particiones (soporta > 2TB y particiones primarias ilimitadas).
- **e. Gestor de Arranque:** Da poder de selección al usuario para cargar SO. Ejemplo de oro: **GRUB**.
- **f. Pasos:** BIOS → MBR/GPT → GRUB → Carga Kernel → Mando al Proceso Padre 1 (Systemd).
- **h. Parada (Shutdown):** Orden de frenado enviando señales SIGTERM a procesos y apaga hardware seguro.
- **i. Dual Boot:** Sí. Se guarda en el GRUB qué SO (Windows/Ubuntu) prender.

## 9. Archivos
- **a. Caso:** Es case-sensitive. Importa la mayúscula o la minúscula (`Archivo` != `archivo`).
- **b. Manipuladores:** `vim` o `nano` (editar), `cat` o `less` (visualizar sin editar).
- **d. Utilidad `file`:** Descubre si un archivo es pdf o foto analizando el código dentro, sin importar su extensión ".txt".
- **e. Listado Express:** `cd` (navegar), `mkdir`/`rmdir` (poner/quitar directorios), `ln` (acceso directo virtual), `cp`/`mv` (copia/mueve).

## 10. Comandos Prácticos y Básicos
- **a.** Crear carpeta: `mkdir ISOCSO`
- **b.** Acceder: `cd ISOCSO`
- **c.** Crear vacío: `touch isocso.txt isocso.csv`
- **d.** Mostrame qué hay: `ls`
- **e.** En dónde estoy hoy: `pwd`
- **f.** Buscar prefijo "iso": `find . -name "iso*"`
- **g.** Disco real ocupado: `df -h`
- **h.** ¿Quién nos usa?: `who`
- **i.** Editar texto: `nano isocso.txt`
- **j.** Leer lo de abajo de todo: `tail isocso.txt`

## 11. ¿Cuál es su acción?
- `man`: Llama a los manuales para instruirte.
- `shutdown` / `reboot`: Apagar / Reiniciar.
- `uname`: Dice datos técnicos del SO actual (arquitectura).
- `dmesg`: Ver mensajes log de qué ocurre en el kernel.
- `lspci`: Mira qué placas y ranuras PCI están puestas.
- `at`: Agendar de forma asíncrona un comando en un futuro.
- `netstat`: Qué puertos y conexiones TCP/UDP están actuando.
- `head` / `tail`: Muestran líneas iniciales (head) o finales (tail) de un archivo largo.

## 12. Procesos
- **a. Proceso:** Programa ya en RAM en estado de ejecución. **PID:** Process ID (Identificador). **PPID:** El PID del Proceso Padre. Todo proceso en Linux posee ambos.
- **b. Visualizadores rápidos:** `top`/`htop` (para ver en vivo), `ps` / `pstree ` (foto al instante y árbol familiar), `kill` (Finalizar usando el PID).

## 13. SystemV
- **a. Ruta de vida:** HW Check -> Gestor Arranque -> Kernel Memoria -> Lanzamiento Init.
- **b. Init:** EL primer programa user-space. Lee `/etc/inittab`.
- **c. RunLevels:** Fases de encendido del sistema operativo clásico. 0 y 6 son apagado o reinicio de emergencia. 3 es CLI, 5 es con interfaz Gráfica Completa X11.
- **f. Cambiar Modo:** `init X` (sirve momentáneamente dentro de la ejecución hasta apagar).
- **g. RC Scripts:** Instrucciones "S" (Start) y "K" (Kill) que encienden y apagan periféricos en fila.

## 14. SystemD
- **a. Evolución:** Reemplazo reciente de SystemV. No va en serie, enciende múltiples procesos paralelos achicando tiempos boot.
- **b. Unit:** Bloque funcional del sistema (Ej. una unidad `.service` que controla el Apache).
- **d. Target:** El homónimo de SystemD para los "RunLevels". (`graphical.target` reemplaza runlevel 5).
- **e. Vistas pstree:** La cabeza del pulpo es `systemd` que toma el id = 1 en lugar exclusivo de `init`.

## 15. Usuarios
- **a. Bases:** Guardado en `/etc/passwd`. Las contraseñas en Hash viven en `/etc/shadow`.
- **b. Identificadores:** **UID** (Del usuario) y **GID** (De Grupo). El root debe ser estrictamente exclusivo = `0`.
- **d. Altas y bajas:** `useradd -m -d /home/isocso isocso` (crea y aloja entorno) | `userdel -r isocso` (lo extirpa completamente del log).
- **e. Comandos directos:** `usermod` (hace un tuneo de un user existente), `su` (suplantar de cuenta), `passwd` (reformula contraseña).

## 16. Permisos (File System)
- **a. 3 Capas de usuarios:** Dueño(U), Grupo(G), Global Externo(O). | **3 Poderes:** R(Lectura), W(Escritura), X(Ejecución).
- **b. Poderes C-Line:** `chmod` (cambia letras de permiso), `chown` (roba/regala al dueño), `chgrp` (modifica el grupo al cual abarca).
- **c. Modos Octales:** Traducen las letras matemáticas a base 8. `R=4 | W=2 | X=1`. Explicación: `chmod 750` -> U tiene 4+2+1(Full), G tiene 4+1(RX) y Global tiene 0(Nada).
- **e. Relativo vs Absoluto:** Relativo trabaja sabiendo sobre donde estamos parados hoy: `cd ../yo`. Absoluto exige la ruta perfecta desde el principio del disco: `cd /opt/bin/etc/yo`.
- **g. Montajes:** Dispositivo Nuevo requiere pegarse virtualmente al árbol. `mount /dev/sdb1 /mnt` pega un USB a la carpeta MNT. `df` monitorea lo ocupado.

## 17. Background y Tuberías
- **a. Tipos Ejecución:** Todo script interactivo frena tu terminal en Foreground. Las tareas silenciosas en Background no estorban interactuando en segundo plano logueando callados.
- **b. Manipulado:** Terminación de un comando con ampersand (`comando &`) lo clava en segundo plano. Volverlo adelante es comando `fg`. Frizarlo con `Ctrl+Z` le permite re-empujar atrás con `bg`.
- **c. Pipes `|` (Tuberías):** Transportan texto crudo que resulta de la salida de un programa de un lado y se inyecta como escritura en el entrante de otro. Ejemplo `ls -all | less`.
- **d. Redirecciones `>`, `>>` :** Sirven para atrapar salidas y escribir bitácoras (`> ` crea de cero / `>>` apila texto sobre la vieja versión). 

## 18. Empaquetar No Es Comprimir
- **a. Diferencia:** `tar` junta un puñado de archivos como si fuera una bolsa plástica; NO achica el espacio final de byte ocupado, son el ensamble idéntico de lo que contienen.
- **c. Acción unida:** Se puede combinar "GZIP" sobre "TAR" si a los parámetros comunes se les suma bandera "Z". Comando Maestro: `tar -czvf caja_pesada.tar.gz fotos_vacaciones/*`
- **e. Extras Unix:** Compresores base es `gzip`. Buscador de texto nativo universal poderoso es `grep`. Comando WordCount para contar letras/líneas es `wc`.

## 19. Estudio de Comandos Inconsistentes
- `chown root:root`: Falla. El usuario corriente no tiene llave maestra de jerarquización.
- `chmod 700 /etc/passwd`: Falla. No tenemos propiedad sobre ese directorio crítico. 
- `passwd root`: Denegado. Solo Root toca claves Root.
- `rm *`: Cuidado de fuego, borra la habitación virtual completa sin asco.
- `cp * /home -R`: Falla de inmediato, nadie corriente puede soltar archivos de manera salvaje en /home/ porque no son el patrón.
- `shutdown`: Falla el permiso orgánico. Solo un superAdmin (sudo) manda apagar la central nuclear.

## 20. Acciones puntuales (Consola Express)
- **a.** Fusil PID: `kill 23`
- **d.** Atrapa estados: `ps aux > ~/procesado`
- **e.** Modif: `chmod 751 ~/archivo1`
- **f.** Modif2: `chmod 650 ~/archivo2`
- **g.** Borrar Tmp: `rm -r /tmp/*` (Se requiere mucha cautela en /tmp).
- **h.** Regalar Archivo: `chown isocso /opt/isodata`
- **i.** Historial con cola progresiva: `pwd >> ~/direcciones_donde_estuvo`

## 21. Ciclo Típico Administrativo
1. Switch `su root`
2. Alta de humano: `useradd asmith; passwd aSmith`
3. Generación entorno y Copia de log: `mkdir /tmp/miCursada` -> `cp -r /var/log/* /tmp/miCursada/`
4. Cambio de Adueñamiento de un folder heredado y aplicación recursiva: `chown -R aolthoff:users /tmp/miCursada` y subsiguiente `chmod -R 723 /tmp/miCursada`
5. Revisar gente metida en la conexión server a hoy: `who -q`
6. Frenado: `shutdown -h now`

## 22. Utilizando Buscadores Core `find`
- Setups: `vi LEAME` tras acceder, modificar a bloqueado `chmod 017 LEAME`.
- Paradoja Mayúsculas: `/etc > ~/leame` guarda información vital ahí mismo (LEAME de readme no colisiona contra leame, están separados intrínsecamente a nivel código binario en Linux).
- Búsquedas: La lógica global es `find <RutaBuscado> -name "<Condicion>"`. Ejemplo: `find / -name "*.so" > ~/ejercicioF`

## 23. Misceláneos en Rutas Locales
- `echo $HOME` va a imprimir tu ruta absoluta privada como `/home/agustin`.
- Sobre permisos de carpetas: La lógica de cruce de carpetas asume que NO es obligada la variable `R`. Si el grupo tiene `WX` (escritura y ejecución número 3), alcanza para viajar en él (`cd`), crear archivos nuevos y modificarlos.
- Path locales vs global en busquedas: `find .` se centra de tu actual para abajo. `find /` rompe el disco para todo lado.

## 24. El Árbol (Refrescando Copy&Move)
- Mover (Traslado Cortante): `mv f3 /home/usuario/`
- Copia Básica: `cp f4 dir11/` y para renombrarlo instantáneo `cp f4 dir11/f7`
- Copia Profunda en bloque Folder entero: `cp -r ~/dir1/* ~/copia/`
- Super Permisos Octal en serie aplicados: `chmod 023 f3.exe f4.exe`

## 25. Secuencia Final (Tarball)
1. Creo y Almaceno cosas dispersas: `mkdir /tmp/logs` -> `cp -r /var/log/* /tmp/logs/`
2. Genero Pack Común sin peso base modificado (TAR): `tar -cvf misLogs.tar /tmp/logs/`
3. Re-genero esta vez empaquetador ZIPeado de alta compresión: `tar -czvf misLogs.tar.gz /tmp/logs/`
4. Eliminación de las cenizas base: `rm -rf /tmp/logs`
5. ReDescomprimir en otro hemisferio personal: `tar -xvf misLogs.tar -C ~/dirX` y el ZIPeado `tar -xzvf misLogs.tar.gz -C ~/dirY`.
