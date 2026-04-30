# 📘 Clase 6: Árboles y Estructuras Balanceadas

**Materia:** Fundamentos de Organización de Datos (FOD) — UNLP 2026  
**Temas:** Árboles Binarios, AVL, Multicamino, B-Trees, B* y B+.

---

## Parte A: Introducción a los Árboles en Disco

### 🎯 Motivación y Problema de los Índices Simples

En clases anteriores vimos que los índices mejoran drásticamente la búsqueda, pero generan un nuevo cuello de botella: **mantener los índices ordenados linealmente en RAM/Disco es muy costoso** ante inserciones y eliminaciones masivas. 

La solución es utilizar **Árboles**: Estructuras jerárquicas que permiten localizar información de forma logarítmica sin requerir desplazamientos contiguos de datos.

### 1. Árboles Binarios Paginados

En un árbol binario estándar, cada nodo tiene a lo sumo dos sucesores (Hijo Izquierdo e Hijo Derecho).
*   **Problema Intrínsico:** Se **desbalancean fácilmente** dependiendo del orden en que lleguen las claves (pudiendo degenerar en una lista enlazada O(N)).
*   **Problema de Almacenamiento (Disco):** Si cada nodo lógico fuera un acceso al disco, bajar por el árbol implicaría demasiados accesos electromecánicos (altísima latencia).

**Solución -> Paginación:** Se minimizan los accesos a disco empaquetando o "paginando" múltiples nodos lógicos en un único bloque o *página* física del disco. Al cargar la página en RAM, tenemos acceso local a toda esa rama.

![Arbol Binario Paginado](./img/arbol_binario_paginado.png)

**¿Y los Árboles AVL?**
Son árboles binarios con la propiedad estricta de estar *balanceados en altura* (`BA(1)`). Mediante rotaciones aseguran que la rama más larga y la más corta difieran a lo sumo en 1 nivel.
*   *Rendimiento:* Búsqueda en `1.44 log₂(N+2)`.
*   *Inconveniente:* Para Bases de Datos masivas, la profundidad sigue siendo excesiva y las rotaciones causan demasiadas escrituras a disco.

---

## Parte B: Árboles Multicamino y Árboles B

### 1. Árboles Multicamino
Para achicar la altura del árbol (y los saltos de disco), generalizamos el árbol binario. Un nodo ahora tiene **K punteros a hijos** y **K-1 llaves (datos)**. Al ser más "ancho", la profundidad disminuye drásticamente.

![Arbol Multicamino](./img/arbol_multicamino.png)

### 2. Árboles B (Balanceados)

Son árboles multicamino con una construcción especial **Bottom-Up (de abajo hacia arriba)** que garantiza un balance perfecto a bajo costo.

**Propiedades de un Árbol B de Orden M:**
1.  Todo nodo tiene como máximo `M` hijos.
2.  Todo nodo (excepto raíz y hojas) tiene como **mínimo `⌈M/2⌉` hijos** (al menos la mitad de su capacidad).
3.  La raíz tiene al menos 2 hijos.
4.  **Nivelación Perfecta:** Todos los nodos hoja (terminales) están exactamente al mismo nivel.
5.  Un nodo con `K` hijos contiene exactamente `K-1` registros.

![Ejemplo de Arbol B](./img/arbol_b_ejemplo.png)

#### 🚀 Performance Matemática (Cota de H)
¿Por qué son tan dominantes? Si armamos un Árbol B con un Orden de `M = 512`, y tenemos `N = 1.000.000` de registros, la altura máxima teórica es `h <= 3.37`. 
👉 **Solo se requieren 4 lecturas de disco para hallar 1 registro entre un millón.**

#### ⚙️ Operaciones ABM (Alta, Baja, Modificación)

*   **Inserción:**
    *   *Mejor Caso:* El nodo hoja tiene espacio. Costo: `H` lecturas y `1` escritura.
    *   *Peor Caso (Overflow):* El nodo hoja está lleno. Se parte a la mitad (**Split/División**), subiendo el elemento medio al nodo padre. Si el padre también está lleno, la división se propaga hasta la raíz (aumentando la altura del árbol). Costo: `2h+1` escrituras.
*   **Eliminación:**
    *   Siempre se elimina de un nodo terminal. Si el registro está en un nodo interno, se intercambia previamente con su sucesor más cercano en una hoja.
    *   *Mejor Caso:* Sobran elementos en el nodo.
    *   *Peor Caso (Underflow):* El nodo queda con menos de `⌈M/2⌉ - 1` elementos. Existen dos soluciones:
        1.  **Redistribuir:** Pedirle un elemento prestado a un *nodo adyacente hermano*.
        2.  **Concatenar (Merge):** Si los hermanos también están al mínimo, se fusionan dos nodos en uno solo, achicando el árbol.

---

## Parte C: Variantes Avanzadas (B* y B+)

### 🌳 Árboles B* (Mayor Densidad)
Es una variante que optimiza el espacio y demora las operaciones costosas de división.
*   **Regla de Oro:** Cada nodo no está lleno a la mitad (1/2), sino que debe estar **lleno en sus 2/3 partes**.
*   *Inserción:* Al haber overflow, en lugar de dividir el nodo en 2 nodos al 50%, intenta **Redistribuir** con sus vecinos. Solo cuando 3 nodos seguidos están al tope, se dividen creando uno nuevo (llenando todos a 3/4).

### 🌳 Árboles B+ (Bases de Datos Relacionales)
Es el estándar absoluto en bases de datos comerciales. Su diseño resuelve el problema de tener que hacer un recorrido "In-Order" puramente jerárquico para sacar reportes secuenciales.

*   **Diferencia Fundamental:** Todos los registros físicos de datos **SOLO residen en las hojas**. 
*   **Nodos Internos:** Solo contienen "Separadores" (copias de las claves) que actúan como "Roadmaps" o guías de enrutamiento.

![Insercion Arbol B+](./img/arbol_b_plus.png)

#### Conjunto de Secuencias
Las hojas del Árbol B+ no están aisladas. Están **linkeadas físicamente entre sí** de izquierda a derecha (como una lista doblemente enlazada masiva). 
*   Esto permite búsquedas específicas usando la jerarquía (O(log N)).
*   Y permite **procesamientos secuenciales masivos rapidísimos** (como un barrido de tabla) saltando de página en página mediante el puntero horizontal, ignorando la raíz.

#### Árboles B+ de Prefijos Simples
Para que entren más claves en la memoria RAM, los nodos guía no guardan la clave completa (Ej: `GONZALEZ`), sino el **prefijo mínimo** necesario para separar la decisión (Ej: `GON`), ahorrando masivo espacio interno.

### 🆚 Conclusión Comparativa (B vs B+)

| Característica | Árbol B Tradicional | Árbol B+ |
|---|---|---|
| **Ubicación de Datos** | En cualquier nodo (Internos o Hojas) | **SOLO** en Nodos Terminales (Hojas) |
| **Tiempo de Búsqueda** | Logarítmico | Logarítmico |
| **Procesamiento Secuencial** | Lento (Requiere subir y bajar recursivamente) | **Muy Rápido** (Saltos por punteros horizontales) |
| **Mantenimiento (ABM)** | Complejo por underflow interno | Puede requerir un poco más de tiempo por el mantenimiento de la lista secuencial, pero simplifica el código general al estar todo en las hojas. |

---

## 📚 Recursos y Referencias

- **Cátedra FOD (UNLP):** *"Organización de Datos - Clase 6: Árboles y Arboles B/B+"*. 2026.
