# 💻 Resolución Práctica 1: Comandos Básicos de Linux

**Materia**: Introducción a los Sistemas Operativos (ISO)

Esta guía resume las soluciones a los ejercicios principales de la **Práctica 1**, enfocada en comandos de consola, gestión de archivos, permisos y procesos.

---

## 1. Gestión de Archivos y Directorios

| Comando | Descripción de Uso Práctico | Ejemplo de la Práctica |
|---------|-----------------------------|------------------------|
| `mkdir` | Crea un nuevo directorio. | `mkdir iso` (crea la carpeta iso) y `mkdir -p dir1/dir2` (crea recursivamente). |
| `cd` | Cambia el directorio actual de trabajo. | `cd /etc` (va a la raíz etc) o `cd ~` (va al home del usuario). |
| `ls` | Lista el contenido de un directorio. | `ls -l` (formato largo con permisos) o `ls -ld f2` (muestra info del directorio f2, no de su contenido). |
| `touch` | Crea un archivo vacío o actualiza su fecha. | `touch dir` (crea el archivo vacío llamado "dir"). |
| `cp` | Copia archivos o directorios. | `cp -r /var/log /tmp/logs` (copia recursiva de un directorio). |
| `mv` | Mueve o renombra archivos y directorios. | `mv f3 /home/usuario/` (mueve f3) o `mv f1 archivo` (renombra f1 como archivo). |
| `rm` | Elimina archivos o directorios. | `rm -r /tmp/logs` (elimina la carpeta logs con todo su contenido). |

## 2. Permisos en Linux (`chmod`)
Los permisos se dividen en **Usuario (u)**, **Grupo (g)**, y **Otros (o)**. Tienen valores numéricos:
- **Lectura (r)** = 4
- **Escritura (w)** = 2
- **Ejecución (x)** = 1

### Ejemplos del Trabajo Práctico:
- *Otorgar al dueño lectura/escritura (6), al grupo ejecución (1) y a otros todo (7):*
  ```bash
  chmod 617 archivo
  ```
- *Otorgar ningún permiso al dueño (0), escritura al grupo (2) y escritura/ejecución a otros (3):*
  ```bash
  chmod 023 f3.exe f4.exe
  ```
- *Otorgar permisos recursivos a una carpeta:*
  ```bash
  chmod -R 723 directorio/
  ```

## 3. Búsqueda de Archivos (`find` y `grep`)

- **Búsqueda por nombre EXACTO:** 
  Buscar un archivo llamado `passwd` en todo el sistema (`/`).
  ```bash
  find / -name "passwd"
  ```
- **Búsqueda por EXTENSIÓN:** 
  Buscar todos los archivos `.so` y guardar el resultado en un archivo:
  ```bash
  find / -name "*.so" > ~/ejercicioF
  ```
- **Búsqueda de texto dentro de archivos (`grep`):**
  Cuenta cuántos procesos tienen la palabra "ps":
  ```bash
  ps | grep 'ps' | wc -l
  ```

## 4. Gestión de Procesos y Usuarios

- **Ver procesos activos del usuario:** `ps`
- **Ver todos los procesos del sistema:** `ps -fea` o `ps aux`
- **Ver cantidad de usuarios conectados:** `who -q` o `users | wc -w`
- **Enviar mensajes entre terminales:** `write nombre_usuario` (el usuario debe estar logueado).
- **Apagar el sistema:** `shutdown -h now` o `poweroff` (requiere permisos de root).

## 5. Empaquetado y Compresión (`tar` y `gzip`)

En Linux, empaquetar (juntar archivos) es distinto a comprimir (reducir tamaño).
- **Empaquetar un directorio (`.tar`):**
  ```bash
  tar -cvf misLogs.tar /tmp/logs/
  ```
- **Empaquetar y Comprimir un directorio (`.tar.gz`):**
  ```bash
  tar -czvf misLogs.tar.gz /tmp/logs/
  ```
- **Desempaquetar un archivo (`.tar`):**
  ```bash
  tar -xvf misLogs.tar -C /destino/
  ```

## 6. Redirecciones (`>` y `>>`)
- `>` : Redirige la salida estándar a un archivo, **sobrescribiéndolo** (Ej: `ls > f1`).
- `>>` : Redirige la salida estándar a un archivo, **añadiéndolo** al final (Ej: `pwd >> f3`).
