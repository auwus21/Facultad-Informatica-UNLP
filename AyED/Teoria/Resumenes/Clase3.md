# 📘 Árboles Binarios y de Expresión

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Árbol Binario, Lleno y Completo, Recorridos (Pre, In, Post, Niveles), Árboles de Expresión

---

# Parte A: Árboles Binarios

## 🎯 Definición y Terminología

Un **árbol binario** es una colección de nodos que puede estar vacía o estar formada por un nodo distinguido (la raíz) y dos sub-árboles (izquierdo y derecho) conectados a la raíz por medio de una arista.
Cada nodo puede tener a lo sumo dos nodos hijos.

### Conceptos Fundamentales

| Término | Definición |
|---|---|
| **Hoja** | Un nodo que no tiene ningún hijo (grado 0). |
| **Camino** | Secuencia de nodos desde n1 hasta nk donde cada n es padre del siguiente. La *longitud* del camino es su número de aristas. |
| **Profundidad** | De un nodo n: es la longitud del único camino desde la raíz hasta n. La raíz tiene profundidad cero. |
| **Grado** | Número de hijos de un nodo (en binarios, puede ser 0, 1 o 2). |
| **Altura** | De un nodo n: la longitud del camino *más largo* desde n hasta una hoja. Las hojas tienen altura 0. |

En criollo: La **profundidad** se mide "de arriba para abajo" (qué tan hundido está el nodo debajo de la raíz). La **altura** se mide "de abajo para arriba" (qué tan alto está el nodo respecto a la hoja más lejana debajo de él).

---

## 📊 Clasificación de Árboles Binarios

### 1. Árbol Binario Lleno

Un árbol de altura `h` es **lleno** si cada nodo interno tiene grado 2 y *todas* las hojas están en el mismo nivel `h`.
- Cantidad de nodos exactos: `N = 2^(h+1) - 1`

### 2. Árbol Binario Completo

Un árbol de altura `h` es **completo** si es lleno hasta el nivel `h-1`, y el último nivel `h` se va llenando estrictamente de *izquierda a derecha*.
- Cantidad de nodos varía entre `2^h` (mínimo) y `2^(h+1) - 1` (lleno).

---

## ⚙️ Representación y Recorridos

### 🏗️ Representación en Memoria

Cada nodo guarda su dato y dos punteros o referencias:
- Referencia a su hijo izquierdo.
- Referencia a su hijo derecho.

### ⚙️ Algoritmos de Recorrido

| Recorrido | Orden de Procesamiento | Caso de Uso principal |
|---|---|---|
| **Preorden** | **Raíz** ➔ Hijo Izquierdo ➔ Hijo Derecho | Hacer copias del árbol (clonación). |
| **Inorden** | Hijo Izquierdo ➔ **Raíz** ➔ Hijo Derecho | En árboles de búsqueda, devuelve los elementos ordenados ascendentes. |
| **Postorden** | Hijo Izquierdo ➔ Hijo Derecho ➔ **Raíz** | Cálculos dependientes de los hijos (ej. calcular altura o tamaño total). |
| **Por Niveles** | Nivel 0, luego Nivel 1... (Usando Cola) | Búsquedas horizontales en amplitud (BFS). |

#### 📦 Código: Recorrido por Niveles (BFS)
```java
public void porNiveles(Nodo raiz) {
  Cola cola = new Cola();
  cola.encolar(raiz);
  
  while (!cola.isEmpty()) {
    Nodo v = cola.desencolar();
    System.out.println(v.getDato());
    if (v.tieneHijoIzquierdo()) cola.encolar(v.getHijoIzquierdo());
    if (v.tieneHijoDerecho()) cola.encolar(v.getHijoDerecho());
  }
}
```

---
---

# Parte B: Árboles de Expresión

## 🎯 Definición

Un árbol de expresión es un árbol binario asociado a una expresión matemática o algebraica.
- **Nodos internos:** Representan **operadores** (`+`, `-`, `*`, `/`).
- **Nodos externos (Hojas):** Representan **operandos** (números o variables como `a`, `b`, `5`).

**Ventaja principal:** No necesitan paréntesis temporales para indicar precedencia, la precedencia es inyectada en la misma estructura del árbol (los nodos más abajo se resuelven antes).

---

## ⚙️ Generación de Recorridos y Notaciones

Si agarramos un árbol de expresión matemático y lo recorremos:
- **Inorden:** Obtenemos la notación infija (ej: `(a + b) * c`).
- **Preorden:** Obtenemos la notación prefija (Notación Polaca) (ej: `* + a b c`).
- **Postorden:** Obtenemos la notación postfija (Polaca Inversa) (ej: `a b + c *`).

---

## ⚙️ Construcción desde Expresión Postfija

Para armar el árbol a partir de una cadena "postfija" (ej. `a b d * c + *`), leemos de izquierda a derecha usando una **Pila de Nodos**.

**Algoritmo paso a paso:**
1. Tomar un carácter. Si es operando: crear un nodo `Hoja` y apilarlo.
2. Si es operador:
   - Crear un nodo `R` con ese operador.
   - Desapilar elemento 1: hacer que sea hijo **derecho** de `R`.
   - Desapilar elemento 2: hacer que sea hijo **izquierdo** de `R`.
   - Apilar al nuevo nodo `R`.
3. Repetir hasta terminar la cadena. Lo que queda en la pila (1 solo nodo) es la Raíz del árbol.

---

## ⚙️ Evaluación del Árbol de Expresión

Para evaluar matemáticamente el árbol (calcular el resultado numérico), se hace un recorrido en **Postorden**.

**Algoritmo Recursivo:**
```java
public int evaluarAE(Nodo A) {
  if (A.esHoja()) {
      return A.getDatoNumerico();
  } else {
      int valorIzq = evaluarAE(A.getSubArbolIzquierdo());
      int valorDer = evaluarAE(A.getSubArbolDerecho());
      
      switch (A.getOperador()) {
          case "+": return valorIzq + valorDer;
          case "-": return valorIzq - valorDer;
          case "*": return valorIzq * valorDer;
          case "/": return valorIzq / valorDer;
      }
  }
}
```

En criollo: La función de cálculo mira un nodo y dice: "Si soy un número, lo devuelvo crudo. Si soy un suma (`+`), como no sé todavía mis partes, pido obligatoriamente el resultado numérico calculado de toda mi rama izquierda, luego toda mi rama derecha, y los sumo. Y así sucesivamente hasta abajo."

---

## 📚 Recursos y Referencias

- **Cátedra:** *Algoritmos y Estructuras de Datos* — UNLP. 2026.
- PDFs elaborados por Prof. Alejandra Schiavoni y Prof. Catalina Mostaccio.
