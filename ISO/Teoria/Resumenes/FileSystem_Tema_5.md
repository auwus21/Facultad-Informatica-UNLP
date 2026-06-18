# 📘 Tema 5: Administración de Sistemas de Archivos (File Systems)

**Materia:** Introducción a los Sistemas Operativos (ISO) — UNLP 2026  
**Temas:** Concepto de Archivo, Atributos y Directorios, Seguridad y Protección, Estructura del Volumen UNIX (I-Nodos), Windows FAT (FAT12/16/32) y NTFS.

---

<details>
<summary><b>📂 Parte 1: Fundamentos de Sistemas de Archivos (Conceptos, Atributos, Rutas y Permisos)</b></summary>

## 🎯 ¿Por qué necesitamos archivos?
La memoria RAM es volátil y limitada. Los archivos solucionan tres problemas clave del almacenamiento:
1. **Gran capacidad**: Permiten guardar volúmenes masivos de datos que no entrarían en la RAM.
2. **Persistencia (Largo plazo)**: La información sobrevive cuando los procesos terminan y cuando la computadora se apaga.
3. **Compartición**: Permiten que múltiples procesos accedan de forma concurrente o diferida al mismo conjunto de información.

---

## 🧱 ¿Qué es un Archivo?
Un archivo es una **entidad abstracta con nombre**, creada por el usuario, que representa un **espacio lógico continuo y direccionable** en almacenamiento secundario. El SO se encarga de abstraer los detalles físicos (sectores, platos, pistas) y mostrarlo como una estructura limpia.

### 🔍 Puntos de Vista
*   **Del Usuario (Abstracción):** Le interesa cómo nombrar al archivo, qué operaciones puede hacer (`read`, `write`, `delete`), cómo protegerlo contra accesos indebidos y cómo compartirlo, sin preocuparse por la disposición física en el disco.
*   **Del Diseño (Implementación):** Se enfoca en cómo estructurar los archivos en disco, cómo mapear directorios, cómo administrar el espacio libre y ocupado de manera eficiente y cómo garantizar que no se pierdan datos.

---

## 🏗️ Sistema de Manejo de Archivos (FMS)
Es el conjunto de módulos de software del SO que gestiona el almacenamiento secundario y provee los servicios para manipular archivos:
*   **Operaciones básicas:** `Crear`, `Borrar`, `Buscar`, `Copiar`, `Leer`, `Escribir`, `Renombrar`.
*   **Objetivos del SO:**
    *   Facilitar el acceso abstrayendo las llamadas de bajo nivel.
    *   Minimizar o eliminar la pérdida o destrucción de datos (garantizar la **integridad**).
    *   Optimizar la performance de Entrada/Salida.
    *   Brindar soporte uniforme para distintos tipos de dispositivos de almacenamiento.

---

## 🏷️ Atributos de un Archivo
Son los metadatos que describen al archivo. El SO los almacena en estructuras del File System (no dentro del contenido del archivo):
*   **Nombre:** Única información legible por humanos.
*   **Identificador (ID):** Número único de control del SO (ej: el número de I-Nodo).
*   **Tipo:** Identifica si es archivo de texto, binario, directorio, etc.
*   **Localización:** Punteros a los bloques del dispositivo físico donde se guardan los datos.
*   **Tamaño:** Dimensión actual en bytes o bloques.
*   **Protección y Seguridad:** Permisos de acceso (quién puede leer/escribir/ejecutar) y ACLs (Access Control Lists).
*   **Fechas y Tiempos:** Registro de creación, última modificación y último acceso.

### 📝 Clasificación de Archivos
1.  **Archivos Regulares:** 
    *   *Texto plano:* Legibles (código fuente, scripts).
    *   *Binarios:* Estructurados (ejecutables, objetos comprimidos, imágenes).
2.  **Directorios:** Archivos especiales que estructuran y organizan el File System.

---

## 📁 Directorios y Rutas
Un directorio es, en sí mismo, un **archivo especial** que contiene referencias a otros archivos y carpetas. Facilita la localización rápida, la organización lógica y permite que diferentes usuarios usen los mismos nombres sin colisiones.

### 🗺️ Identificación de Rutas
*   **Ruta Absoluta (Full Pathname):** Especifica el camino completo partiendo rigurosamente de la raíz (`/` en UNIX, `C:\` en Windows).
    *   *Ejemplo UNIX:* `/var/www/index.html`
    *   *Ejemplo Windows:* `C:\windows\winhelp.exe`
*   **Ruta Relativa:** Se calcula en base al directorio actual de trabajo (*working directory*). Utiliza referencias especiales como `.` (directorio actual) y `..` (directorio padre).
    *   *Ejemplo:* Si estoy en `/var/spool/mail/`, la ruta relativa a index es: `../../www/index.html`

---

## 🛡️ Seguridad, Compartición y Derechos de Acceso
En sistemas multiusuario, el propietario o administrador debe regular quién accede a la información y qué acciones puede tomar.

### 🔑 Derechos de Acceso Típicos:
*   **Execution (Ejecución):** Correr el programa.
*   **Reading (Lectura):** Ver el contenido del archivo / Listar un directorio.
*   **Appending (Anexar):** Agregar datos al final del archivo, sin modificar la información preexistente.
*   **Updating (Actualización):** Modificar, sobrescribir, agregar o borrar contenido del archivo / Crear y borrar archivos en un directorio.
*   **Changing Protection:** Modificar los derechos de acceso de otros usuarios.
*   **Deletion:** Borrar físicamente el archivo.

### 🐧 Esquema de Permisos en UNIX
Los permisos se dividen en tres clases de usuarios:
1.  **User (u):** Dueño o creador del archivo.
2.  **Group (g):** Grupo de usuarios con privilegios compartidos.
3.  **Other (o):** Todos los demás usuarios del sistema.

Cada clase posee tres bits de permisos fundamentales:
*   **r (Read):** Lectura (valor octal **4**). En directorios: listar contenido (`ls`).
*   **w (Write):** Escritura/Modificación/Borrado (valor octal **2**). En directorios: crear/borrar archivos.
*   **x (Execute):** Ejecución (valor octal **1**). En directorios: atravesar o entrar (`cd`).

> **Representación Octal Combinada:**
> Se suma el valor de los permisos: `rwx` = 4+2+1 = **7**; `rx` = 4+1 = **5**; `r` = **4**.  
> Por ejemplo: `chmod 754 archivo.txt` otorga control total al dueño (7), lectura y ejecución al grupo (5) y solo lectura a los demás (4).

</details>

<br>

<details>
<summary><b>💾 Parte 2: Estructuras Internas (UNIX I-Nodos y Windows FAT/NTFS)</b></summary>

## 🐧 UNIX: Tipos de Archivo y Estructura de Volumen
A diferencia de otros sistemas, UNIX trata a casi todo como un archivo.

### 📂 Tipos de Archivo en UNIX
*   **Archivo común (regular):** Texto o binario.
*   **Directorio:** Tabla de asociación de nombres y números de i-nodos.
*   **Archivos especiales:** Interfaces para hardware en `/dev/` (ej: `/dev/sda` representa al disco físico).
*   **Named pipes (FIFO):** Archivos de comunicación entre procesos.
*   **Hard links (Enlaces duros):** Entradas de directorio que apuntan al **mismo i-nodo** físico. Solo funcionan dentro del mismo sistema de archivos.
*   **Symbolic links (Enlaces simbólicos):** Archivos que contienen la ruta a otro archivo. Tienen su propio i-nodo y pueden cruzar diferentes sistemas de archivos.

---

## 🏗️ UNIX: Estructura de un Volumen (Partición)
Cada partición formateada en UNIX contiene la siguiente distribución de bloques:

```
+---------------+---------------+--------------------+------------------+
|  Boot Block   |  Superblock   |    Inode Table     |   Data Blocks    |
+---------------+---------------+--------------------+------------------+
```

1.  **Boot Block:** Contiene el código de arranque inicial (bootstrap) utilizado para cargar el kernel del SO.
2.  **Superblock:** Almacena los metadatos globales del File System (tamaño del volumen, cantidad de bloques y de i-nodos, lista de bloques y de i-nodos libres).
3.  **Inode Table (Tabla de I-Nodos):** Espacio reservado para almacenar todos los I-Nodos del sistema.
4.  **Data Blocks (Bloques de Datos):** Espacio donde se guardan físicamente los contenidos de los archivos y los directorios.

---

## 📌 El I-NODO (Index Node)
El I-Nodo es la **estructura de datos de control** que contiene toda la información clave de un archivo o directorio.
*   Existe **un i-nodo por archivo**.
*   **¡IMPORTANTE!** El i-nodo **NO almacena el nombre del archivo**.
*   Los nombres de archivos se guardan únicamente en los bloques de datos de los **directorios**, asociados a su correspondiente número de i-nodo.

### 📋 Campos Contenidos en el I-Nodo:
*   Identificador del propietario (UID) y de grupo (GID).
*   Tipo de archivo y permisos de acceso.
*   Tiempos de creación, última modificación y último acceso.
*   Tamaño del archivo en bytes.
*   Número de enlaces (contador de hard links).
*   **Punteros a bloques de datos:** Array de direcciones que mapea dónde están los bloques físicos en el disco (punteros directos, indirectos simples, dobles y triples para soportar archivos de gran tamaño).

### 🔍 Resolución de Nombres (Directorio UNIX)
Un directorio es simplemente una tabla de tuplas del tipo `(Número de i-nodo, Nombre de archivo)`.
*   Para buscar `/usr/ast/mbox`:
    1.  El kernel lee el i-nodo de la raíz `/` (siempre conocido, habitualmente el i-nodo 2).
    2.  Accede a los bloques de datos de `/` y busca la entrada `usr` para obtener su i-nodo.
    3.  Lee el i-nodo de `usr`, va a sus bloques de datos y busca `ast` para obtener su i-nodo.
    4.  Lee el i-nodo de `ast`, va a sus bloques y busca la entrada `mbox` para obtener el i-nodo del archivo final.

---

## 💻 Windows: File Systems Soportados
*   **CDFS (CD-ROM File System):** Específico para discos compactos.
*   **UDF (Universal Disk Format):** Para DVDs y Blu-Rays.
*   **FAT (File Allocation Table):** Diseñado originalmente para DOS y Windows 9x.
*   **NTFS (New Technology File System):** Sistema nativo moderno de Windows.

---

## 📊 Windows: Sistema FAT (File Allocation Table)
El sistema FAT organiza los archivos usando un esquema de **Asignación Encadenada (Linked Allocation)** en el que la tabla de punteros se extrae de los bloques y se consolida al inicio del volumen.

*   **Estructura del volumen FAT:**
    ```
    [ Sector de Boot ] -> [ FAT 1 (Principal) ] -> [ FAT 2 (Duplicado) ] -> [ Directorio Raíz ] -> [ Bloques de Datos ]
    ```
*   La tabla **FAT** posee una entrada por cada *Cluster* (bloque de asignación) en el disco.
*   La entrada en la tabla para el cluster $N$ contiene el **número del siguiente cluster** del archivo. 
*   **Códigos especiales:** Se reservan valores específicos para indicar:
    *   Cluster libre.
    *   Cluster dañado (malo).
    *   Fin de archivo (EOF - End of File).
*   El directorio almacena los nombres de archivos y apunta estrictamente al **primer cluster** del archivo. Para leer el resto, el SO consulta la tabla FAT en cadena.

### ⚙️ Comparativa de Versiones FAT

| Característica | FAT12 | FAT16 | FAT32 |
|---|---|---|---|
| **Bits de direccionamiento** | 12 bits | 16 bits | 32 bits (28 bits efectivos, 4 reservados) |
| **Cantidad de clusters** | Hasta $2^{12} = 4.096$ | Hasta $2^{16} = 65.536$ | Hasta $2^{28} \approx 268.435.456$ |
| **Tamaño de Cluster** | 512 Bytes a 8 KB | 512 Bytes a 64 KB | 512 Bytes a 32 KB |
| **Capacidad Máxima** | 32 MB | 4 GB | 8 TB (teórica), 128 GB (límite práctico de Windows) |
| **Casos de uso** | Disquetes de 3.5", dispositivos legacy muy pequeños. | MS-DOS antiguos, pendrives pequeños. | Sistemas operativos Windows 9x, memorias USB de compatibilidad universal. |

---

## 🚀 Windows: NTFS (New Technology File System)
Introducido con Windows NT, NTFS es un sistema de archivos moderno y robusto de calidad empresarial.

### 🛡️ Características Principales:
*   **Direccionamiento de 64 bits:** Permite direccionar de forma nativa tamaños de volumen de hasta 16 Exabytes (teóricos).
*   **Estructura basada en la MFT (Master File Table):** Todos los datos y metadatos de los archivos (incluyendo su contenido si es muy pequeño) se guardan como atributos dentro de la MFT.
*   **Seguridad avanzada:** Soporte nativo para permisos por usuario y ACLs avanzadas.
*   **Tolerancia a fallos y Transaccionalidad:** Utiliza un sistema de *Journaling* (registro de transacciones) que permite recuperar la consistencia del disco rápidamente tras un corte de luz o fallo de sistema.
*   **Nombres largos:** Permite nombres de hasta 255 caracteres Unicode.

</details>
