# 📘 Clase 5: Búsqueda de Datos e Índices

**Materia:** Fundamentos de Organización de Datos (FOD) — UNLP 2026  
**Temas:** Índices Primarios, Índices Secundarios, Listas Invertidas, Operaciones ABM con Índices.

---

## Parte A: Índices y Camino de Acceso

### 🎯 Motivación

En archivos grandes, la **Búsqueda Secuencial** es ineficiente y la **Búsqueda Binaria** sobre el archivo completo es costosa por el altísimo precio de mantener el archivo ordenado (muchos desplazamientos de disco). La solución es incorporar **Estructuras Auxiliares**.

> *"El uso de índices proporciona varios caminos de acceso a un archivo de datos masivo, tal cual como el índice temático de un libro permite saltar a una hoja sin tener que leerlo todo."*

### ⚙️ Definición de Índice

Un índice es una herramienta paralela al archivo de datos que consiste formalmente en dos campos:
1. **Campo Llave (Búsqueda):** El atributo que se busca.
2. **Campo de Referencia:** El lugar exacto físico donde hallar el registro.

| Índice Temático (Libro) | Índice Computacional (Archivos) |
|---|---|
| `(Tema, #hoja)` | `(Clave, NRR / Distancia en bytes)` |

La característica fundamental del índice es que **permite imponer un orden en un archivo, sin que realmente el archivo de datos original tenga que reacomodarse**.

---

## Parte B: Tipos de Índices

### 🔑 Índice Primario
Se genera a partir de la **Llave Primaria**, que es un identificador unívoco. 
*   **Procedimiento Clave:** Se pasa la llave primaria por una *Forma Canónica* (ej. todo a mayúsculas, sin espacios extra).
*   **Restricción:** No se puede hacer búsqueda binaria directa en el archivo de datos si este es de longitud variable (porque no podemos calcular el NRR). El índice sí usa longitud fija para permitir búsqueda binaria.

| Ventajas | Desventajas |
|---|---|
| ✅ El archivo índice cabe en Memoria Principal (RAM). | ❌ Si la RAM no alcanza, el índice debe ir a disco. |
| ✅ Habilita Búsqueda Binaria sobre un archivo caótico. | ❌ Es necesario reescribir el índice periódicamente. |
| ✅ Es rapidísimo de consultar. | ❌ Plantea dudas sobre persistencia y resguardo (Crash). |

### 🔍 Índices Secundarios
A un usuario no le es natural buscar un libro por su `ISBN` (Llave primaria). Preferirá buscar por "Autor" o "Título". Estos campos son **Llaves Secundarias**, y a diferencia de las primarias, **se pueden repetir**.

El Índice Secundario no apunta directamente al NRR final de los datos, sino que **relaciona la llave secundaria con la llave primaria**. El acceso es de 2 saltos:
1. Buscar en Índice Secundario por Llave Secundaria 🡪 Devuelve Llave Primaria.
2. Buscar en Índice Primario por Llave Primaria 🡪 Devuelve NRR.

#### El Problema de la Repetición
Dado que un mismo compositor ("Beethoven") tiene múltiples canciones registradas en la base de datos, tener una fila para Beethoven por cada canción desperdicia masivo espacio y satura la RAM.
**Soluciones Posibles:**
1. **Arreglo Fijo (Vector de ocurrencias):** `[BEETHOVEN] -> [ID1, ID2, ID3]`. Problema: O sobra espacio o falta espacio (Fragmentación).
2. **Listas Invertidas:** Es la solución ideal. 

---

## Parte C: Listas Invertidas

Para evitar arrays rígidos en los índices secundarios repetidos, se utilizan **Archivos de Listas Invertidas**. Es un segundo archivo auxiliar que encadena todas las llaves primarias pertenecientes a la misma llave secundaria mediante punteros encadenados.

**Estructura a Tres Bandas:**
1. **Archivo Índice Secundario:** Tiene la Clave ("Beethoven") y un único puntero `NRR` hacia el archivo de Listas.
2. **Archivo de Listas:** Tiene la Llave Primaria del disco ("Sinfonía N9") y otro puntero `NRR` al siguiente disco de Beethoven en esta misma lista. Si es el último, lleva un `-1`.
3. **Archivo Primario / Datos:** Donde reside la información completa.

| Atributo | Análisis de Listas Invertidas |
|---|---|
| ✅ **Ventaja Espacial** | No se pierde espacio. No hay "reservas" especulativas de bytes. |
| ✅ **Ventaja de Modificación** | Borrar o añadir composiciones solo implica cambiar punteros en el archivo de listas en tiempo `O(1)`. El índice secundario no se reordena en disco. |
| ❌ **Desventaja** | Si el archivo de listas no cabe en RAM y hay muchos accesos cruzados, los saltos electromecánicos del cabezal de disco matan el rendimiento general. |

---

## Parte D: Mantenimiento (Implantación de Índices)

El índice reside provisoriamente en memoria cargado en un Array, pero toda adición/eliminación requiere luego **reescritura a disco**.

### 1. Agregar Registros
Se agrega primero al *Archivo de Datos* (al final, `FileSize`). Luego se inserta ordenadamente en el Array del *Índice en Memoria*. Si había un índice secundario con *Listas Invertidas*, solo se añade a la cadena.

### 2. Actualización de Registros
*   **Si el Update NO cambia de tamaño:** Se pisa `in-situ`. El índice no se entera de nada.
*   **Si el Update es mayor (Longitud Variable):** Se elimina el original lógico, se inserta la nueva versión gigante al final del archivo, y **se debe actualizar el Índice** para apuntar al nuevo NRR final.
*   **Si se Modifica la Clave:** Equivalente a lanzar una rutina combinada de `Baja Física` seguida de un `Alta`. 

### 3. Eliminar Registros
En el archivo de datos se hace Baja Lógica/Física, mientras que en el Índice simplemente se elimina la fila en la memoria RAM.

---

## 📚 Recursos y Referencias

- **Cátedra FOD (UNLP):** *"Organización de Datos - Clase 5: Búsqueda de Datos e Índices"*. 2026.
