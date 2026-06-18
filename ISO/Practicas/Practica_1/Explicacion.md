# 📖 Explicación Teórico-Práctica: Práctica 1 (Conceptos Generales y GNU/Linux)

Este documento resume los conceptos clave de las diapositivas de explicación para la **Práctica 1** de Introducción a los Sistemas Operativos (ISO).

---

## 🎯 1. ¿Qué es un Sistema Operativo?
Es un programa esencial de cualquier sistema de cómputo que actúa como **intermediario entre el usuario y el hardware**.
*   **Propósito:** Crear un entorno cómodo, seguro y eficiente para la ejecución de programas.
*   **Obligación:** Garantizar el correcto funcionamiento del sistema.
*   **Funciones principales:** Administración de memoria, administración de la CPU y administración de los dispositivos (E/S).

---

## 🐧 2. GNU/Linux y el Software Libre
GNU/Linux es un sistema operativo de tipo Unix, de código abierto, gratuito y de libre distribución.

### 🌐 Orígenes del Proyecto (Fusión Histórica)
*   **Proyecto GNU:** Iniciado por Richard Stallman en 1983. Su fin era crear un Unix 100% libre. Para 1990 ya contaba con compilador (GCC), editor (Emacs) y librerías, pero le faltaba el núcleo (Kernel).
*   **Kernel Linux:** Iniciado por Linus Torvalds en 1991 como un proyecto basado en Minix.
*   **Fusión (1992):** Torvalds y Stallman unen sus desarrollos bajo la licencia GPL, creando **GNU/Linux**.

### ⚖️ Las 4 Libertades del Software Libre (FSF)
Para que un software sea considerado libre, debe garantizar al usuario:
1.  **Libertad 0:** Usar el programa con cualquier propósito.
2.  **Libertad 1:** Estudiar cómo funciona el programa y adaptarlo (requiere acceso al código fuente).
3.  **Libertad 2:** Distribuir copias del programa para ayudar a otros.
4.  **Libertad 3:** Mejorar el programa y publicar las mejoras.

> ⚠️ **Diferencia Clave:** "Software Libre" se refiere a la **libertad** del usuario, no al precio. El software libre puede tener costo, aunque habitualmente es nulo. El "Software Propietario" restringe el uso, distribución y modificación, y su código fuente es cerrado.

### 📜 Licencia GPL (General Public License de GNU)
Creada en 1989 por la Free Software Foundation (FSF). Protege legalmente que el software liberado bajo esta licencia no pueda ser privatizado: cualquier modificación redistribuida de un software GPL debe seguir siendo libre y GPL (*copyleft*).

---

## 🏗️ 3. Arquitectura y Características de GNU/Linux
*   **Desarrollo en Capas:** Permite independencia y modularidad.
*   **Monolítico Hibrido:** El núcleo ejecuta los drivers y el código en modo privilegiado (kernel space), pero es híbrido porque puede cargar y descargar módulos dinámicamente sin reiniciar el sistema.
*   **Multiusuario y Multitarea:** Soporta múltiples usuarios y procesos simultáneos.
*   **Case Sensitive:** Distingue rigurosamente entre mayúsculas y minúsculas en archivos y comandos (ej: `Archivo` != `archivo`).
*   **Todo es un archivo:** UNIX trata a directorios, archivos regulares y dispositivos físicos (teclado, disco) como archivos dentro del árbol de directorios.

---

## 🛠️ 4. Repaso de Comandos Básicos y Tuberías

| Comando | Acción |
|---|---|
| `cat <archivo>` | Muestra el contenido completo de un archivo. |
| `echo "<texto>"` | Imprime una línea de texto en la pantalla. |
| `read <variable>` | Lee una línea desde la entrada estándar y la guarda en una variable. |
| `cut -d<delimitador> -f<campo>` | Recorta columnas de un texto (ej: `cut -d: -f1` toma el primer campo separado por `:`). |
| `wc -l` | Cuenta la cantidad de líneas recibidas en la entrada. |
| `grep <patrón> <ruta>` | Busca líneas que coincidan con un patrón de texto dentro de un archivo o directorio. |
| `find <ruta> -name "<patrón>"` | Busca archivos en el disco que coincidan con el nombre especificado. |
| `tar` | Empaqueta archivos. Ej: `tar -cvf backup.tar arch1 arch2` (empaqueta), `tar -xvf backup.tar` (desempaqueta). |
| `gzip` | Comprime archivos. Ej: `gzip backup.tar` (comprime a `.gz`), `gzip -d backup.tar.gz` (descomprime). |

---

## 🔄 5. Redirecciones y Pipes (Tuberías)
Todo proceso en ejecución cuenta con 3 archivos abiertos (descriptores de archivos):
*   `0`: **stdin** (entrada estándar, teclado).
*   `1`: **stdout** (salida estándar, monitor).
*   `2`: **stderr** (salida de error estándar, monitor).

### 🛠️ Operadores de Redirección
*   `comando > archivo`: Redirecciona **stdout** a un archivo, sobrescribiéndolo si existe (redirección destructiva).
*   `comando >> archivo`: Redirecciona **stdout** al final del archivo, agregando contenido sin borrar (no destructiva).
*   `comando 2> archivo`: Redirecciona la salida de error estándar (**stderr**) a un archivo.
*   `comando < archivo`: Lee la entrada estándar (**stdin**) desde el contenido del archivo en lugar del teclado.

### 🔗 Pipes (Tuberías `|`)
Conectan la salida estándar (**stdout**) de un proceso directamente con la entrada estándar (**stdin**) del siguiente, permitiendo encadenar comandos para realizar tareas complejas:
*   *Ejemplo:* `cat /etc/passwd | cut -d: -f1 | grep a | wc -l` (Muestra usuarios, extrae el nombre, filtra los que contienen la letra 'a', y cuenta la cantidad).
