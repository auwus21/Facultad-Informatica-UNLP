# 📝 Clase 3: Árboles Binarios y de Expresión

Esta clase avanza sobre las estructuras de datos no lineales, ingresando al mundo de los Árboles Binarios.

---

## 🌳 Árboles Binarios (Teoría y Conceptos)

Un árbol binario es una colección de nodos que puede estar vacía o formada por un nodo distinguido (la **raíz**) y dos subárboles (izquierdo y derecho) que a su vez son también árboles binarios.

### Terminología Principal
* **Hojas:** Nodos que no tienen ningún hijo (su grado es 0).
* **Hermanos:** Nodos que comparten el mismo nodo padre.
* **Profundidad de un nodo:** La longitud del único camino desde la raíz hasta dicho nodo. La raíz tiene profundidad 0.
* **Altura de un árbol:** La longitud del camino más largo desde la raíz hasta la hoja más lejana. Las hojas tienen altura 0.
* **Grado de un nodo:** La cantidad de hijos que posee (en un árbol binario puede ser 0, 1 o 2).

### Tipos Especiales de Árboles Binarios

1. **Árbol Binario Lleno:**
   Aquel donde TODOS los nodos internos tienen exactamente 2 hijos, y todas las hojas se encuentran en el mismo nivel (la altura máxima del árbol).
   * Tiene un máximo de nodos definido como: `N = 2^(h+1) - 1` (donde `h` es la altura).

2. **Árbol Binario Completo:**
   Aquel que esta totalmente lleno en todos sus niveles hasta la altura `h-1`, y el último nivel `h` se va llenando estrictamente de **izquierda a derecha**, sin saltear espacios.

---

## 🔄 Recorridos de Árboles Binarios

Un árbol no se puede recorrer con un simple "for" iterando desde `i=0` a `len` como un arreglo. Existen algoritmos estandarizados denominados **recorridos** (usualmente recursivos).

1. **Preorden:** `Raíz -> Hijo Izquierdo -> Hijo Derecho`
   * Útil para copiar árboles o guardar estructura.
2. **Inorden:** `Hijo Izquierdo -> Raíz -> Hijo Derecho`
   * En árboles ordenados, este recorrido visita los nodos en orden ascendente (de menor a mayor).
3. **Postorden:** `Hijo Izquierdo -> Hijo Derecho -> Raíz`
   * Se procesan primero los hijos y de último los padres. Usado para eliminar el árbol de memoria.
4. **Por Niveles (BFS - Breadth First Search):**
   * Visita el nivel 0 entero, luego el nivel 1, etc. Se implementa iterativamente utilizando una **Cola** extra de apoyo y no recursividad.

```java
// Ejemplo de recorrido Inorden recursivo
public void inorden() {
    if (this.tieneHijoIzquierdo()) { this.hijoIzquierdo.inorden(); }
    System.out.println(this.dato); // Procesa la raíz (en el medio)
    if (this.tieneHijoDerecho()) { this.hijoDerecho.inorden(); }
}
```

---

## 🧮 Árboles de Expresión

Un caso de uso práctico de un árbol binario para representar expresiones aritméticas y matemáticas, evitando la ambigüedad que causan los operadores si no se usan paréntesis.

* **Nodos Internos:** Representan los **operadores** (Ej: `+`, `-`, `*`, `/`).
* **Nodos Externos (Hojas):** Representan los **operandos** (las variables o números).

### Leyendo las expresiones
Dependiendo del recorrido que se le aplique al árbol, obtendremos notaciones útiles:
* Recorrido Inorden: Produce una expresión Infija (la usual: `(a + b) * c`). Requiere meter paréntesis.
* Recorrido Preorden: Produce una expresión Prefija (ej: `* + a b c`).
* Recorrido Postorden: Produce una expresión Postfija (ej: `a b + c *`).

### Construcción y Evaluación
* Para evaluar una **expresión postfija** e ir produciendo un resultado, el algoritmo clásico consume los caracteres uno a uno apoyándose en una estructura clásica de **PILA** (`Stack`):
  * Mientas haya elementos, se leen.
  * Si es un número (operando), se mete (`push`) a la pila.
  * Si es un símbolo (operador), se extraen (`pop`) de la pila los **dos topes**, se aplica la operación sobre ellos, y el resultado se vuelve a meter a la pila. 
  * Al concluir la lectura, el resultado global será simplemente el último valor restante en la cima de la Pila.
