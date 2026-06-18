# 📖 Explicación Teórico-Práctica: Práctica 3 (Shell Scripting en Bash)

Este documento resume los conceptos clave de las diapositivas de explicación para la **Práctica 3** de Introducción a los Sistemas Operativos (ISO).

---

<details>
<summary><b>🐚 1. Fundamentos y Conceptos Básicos de Bash</b></summary>

## 🎯 ¿Qué es una Shell y un Shell Script?
*   **Shell:** Un intérprete de comandos interactivo que actúa como interfaz entre el usuario y el kernel del SO. En UNIX, es configurable por usuario.
*   **Shell Script:** Un archivo de texto que contiene una secuencia de comandos estructurados con lógica de programación (bucles, condicionales) para ser ejecutados por la shell.
*   **Casos de uso:** Automatización de tareas repetitivas, administración del sistema y creación de herramientas interactivas.

### ❓ ¿Por qué usar Shell Scripting frente a lenguajes compilados?
*   Extremadamente simple para manipular archivos y directorios.
*   Facilidad nativa para crear procesos, encadenarlos y redirigir sus entradas y salidas.
*   Independiente de la plataforma (funciona en cualquier entorno tipo UNIX: Linux, macOS).
*   Permite probar comandos de forma interactiva en vivo antes de escribirlos en el script.

---

## 🔗 Repaso de Conceptos Core: Descriptores y Tuberías
Todo proceso en ejecución abre por defecto tres flujos de archivos identificados por descriptores:
*   `0`: **stdin** (entrada estándar - teclado).
*   `1`: **stdout** (salida estándar - pantalla).
*   `2`: **stderr** (error estándar - pantalla).

### 🔄 Redirecciones
*   `comando > archivo`: Redirección destructiva de stdout (sobrescribe).
*   `comando >> archivo`: Redirección no destructiva de stdout (anexa al final).
*   `comando 2> archivo`: Redirige errores (stderr).
*   `comando < archivo`: Toma el contenido del archivo como stdin.

### ⛓️ Pipes (Tuberías `|`)
Conectan stdout de un comando directamente a stdin de otro comando.
*   *Ejemplo:* `cat /etc/passwd | cut -d: -f1 | grep adm` (Lee el archivo de usuarios, extrae los nombres y filtra los que contienen "adm").

---

## 🏷️ Variables en Bash
*   Los nombres son *case sensitive*.
*   **Asignación:** `VARIABLE="valor"` (⚠️ **IMPORTANTE:** No debe haber espacios antes ni después del signo `=`).
*   **Lectura:** Se usa el símbolo `$` (ej: `echo $VARIABLE`).
*   **Ámbito:** Por defecto son **globales** al script. Para hacerlas locales a una función, se antepone `local`.
*   **Exportación:** `export VARIABLE` expone la variable como variable de entorno a los procesos hijos.

### 📊 Arreglos (Arrays)
*   **Declaración:** `arreglo=(val1 val2 val3)` o vacío `arreglo=()`.
*   **Asignación:** `arreglo[index]=valor` (indexación base 0).
*   **Lectura:** `echo ${arreglo[index]}` (las llaves `{}` son obligatorias).
*   **Ver todos los elementos:** `echo ${arreglo[@]}` o `${arreglo[*]}`.
*   **Longitud del arreglo:** `echo ${#arreglo[@]}`.
*   **Eliminar un elemento:** `unset arreglo[index]` (deja la posición vacía, no desplaza los índices).

---

## 💬 El uso de Comillas y Sustitución
*   **Comillas Dobles (`"`):** Permiten la expansión de variables (evalúa `$var`) y la sustitución de comandos.
*   **Comillas Simples (`'`):** Tratan todo como texto literal (ej: `'$var'` se imprime como el texto literal `$var`).
*   **Sustitución de Comandos:** Permite guardar la salida de un comando en una variable. Formas:
    *   `variable=$(comando)` (Recomendado, permite anidamientos sencillos).
    *   `variable=\`comando\`` (Sintaxis antigua con comillas invertidas).

</details>

<br>

<details>
<summary><b>🛠️ 2. Programación de Scripts: Sintaxis y Estructuras de Control</b></summary>

## 🚀 Creación y Ejecución de un Script
1.  Crear el archivo e indicar el intérprete en la primera línea (**Shebang**):
    ```bash
    #!/bin/bash
    ```
2.  Otorgar permisos de ejecución en la terminal:
    ```bash
    chmod +x mi_script.sh
    ```
3.  Ejecutar:
    *   `./mi_script.sh` (ejecución estándar).
    *   `bash -x mi_script.sh` (modo **debug**, muestra cada línea y sus variables mientras se ejecutan).

---

## 🔀 Estructuras de Selección

### 1️⃣ Condicional `if-then-elif-else-fi`
```bash
if [ condicion ]
then
    # bloque si se cumple
elif [ otra_condicion ]
then
    # bloque alternativo
else
    # bloque por defecto
fi
```

### 2️⃣ Estructura `case-in-esac`
Equivale al switch-case. Compara una variable contra patrones:
```bash
case $variable in
    "valor1")
        # bloque 1
        ;;
    "valor2"|"valor3")
        # bloque 2 y 3
        ;;
    *)
        # bloque por defecto
        ;;
esac
```

### 3️⃣ Menú Interactivo `select-in-do-done`
Crea automáticamente un menú numérico en pantalla a partir de una lista:
```bash
select opcion in "Nuevo" "Ver" "Salir"
do
    # El valor elegido queda guardado en la variable $opcion
    case $opcion in
        "Salir") exit 0 ;;
    esac
done
```

---

## 🔁 Estructuras de Iteración (Bucles)

### 1️⃣ Bucle `for` (Estilo Foreach y C-Style)
*   **Foreach (Lista de valores):**
    ```bash
    for item in 1 2 3 "hola"
    do
        echo $item
    done
    ```
*   **C-Style:**
    ```bash
    for ((i=0; i<10; i++))
    do
        echo $i
    done
    ```

### 2️⃣ Bucles `while` y `until`
*   `while [ condicion ]`: Itera **mientras** la condición sea verdadera (retorne 0).
*   `until [ condicion ]`: Itera **hasta** que la condición sea verdadera (mientras sea falsa).

### 🛑 Control de Bucles:
*   `break [n]`: Corta la ejecución de $n$ niveles de bucles.
*   `continue [n]`: Salta a la siguiente iteración del $n$-ésimo bucle contenedor.

---

## ⚖️ Evaluación de Condiciones (`test` o `[ ]`)
La condición se encierra en corchetes `[ ]` (debe haber espacios obligatorios adentro de los corchetes).

### 🔤 Comparación de Strings
*   `$var1 = $var2` : Verdadero si son iguales.
*   `$var1 != $var2` : Verdadero si son distintos.
*   `-z $var` : Verdadero si el string está vacío (longitud cero).
*   `-n $var` : Verdadero si el string no está vacío.

### 🔢 Comparación de Números (Enteros)
*   `-eq` : Igual a (Equal).
*   `-ne` : Distinto de (Not Equal).
*   `-gt` : Mayor que (Greater Than).
*   `-ge` : Mayor o igual que (Greater or Equal).
*   `-lt` : Menor que (Less Than).
*   `-le` : Menor o igual que (Less or Equal).

### 📁 Comprobación de Archivos
*   `-e $path` : El archivo/directorio existe.
*   `-f $path` : Existe y es un archivo regular.
*   `-d $path` : Existe y es un directorio.
*   `-r $path` / `-w $path` / `-x $path` : Tiene permisos de lectura / escritura / ejecución.

### 🔗 Condiciones Compuestas
*   `&&` : Operador lógico AND.
*   `||` : Operador lógico OR.
*   *Sintaxis:* `if [ $a -eq 1 ] && [ $b -eq 2 ]; then ...`

---

## 📥 Argumentos y Retorno de un Script
Cuando ejecutamos un script con parámetros (ej: `./script.sh arg1 arg2`), accedemos a ellos mediante variables de control:
*   `$0` : Nombre del script invocado.
*   `$1`, `$2`, `$3`... : Los argumentos en orden.
*   `$#` : Cantidad total de argumentos recibidos.
*   `$*` : Lista de todos los argumentos como una única cadena.
*   `$?` : **Exit status** del último comando o función ejecutada (0 = éxito, 1 a 255 = error).
*   `exit <valor>` : Termina la ejecución del script retornando un código de estado.

---

## 🧩 Funciones
Permiten modularizar código dentro del script:
*   **Declaración:** `nombre_funcion() { bloque }` o `function nombre_funcion { bloque }`.
*   **Argumentos:** Las funciones manejan sus propios parámetros locales en `$1`, `$2`, etc.
*   **Retorno:** Usan `return [0-255]` para devolver un código numérico de estado de ejecución (se lee con `$?` inmediatamente después de invocarla).

</details>
