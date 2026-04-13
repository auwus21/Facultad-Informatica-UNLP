# 📘 Árboles Binarios y de Expresión en JAVA

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Implementación de BinaryTree, Conversión a Árboles de Expresión, Problemas de aplicación (Valencia)

---

# Parte A: Implementación en Java de `BinaryTree`

## 🎯 Estructura Base

En el enfoque estructurado en Java, un árbol binario delega cada nodo a ser una instancia de la clase genérica `BinaryTree<T>`.

### 📦 Código: Clase BinaryTree

```java
public class BinaryTree<T> {
    private T data;
    private BinaryTree<T> leftChild;   
    private BinaryTree<T> rightChild;
    
    // Constructores
    public BinaryTree() { super(); }
    public BinaryTree(T data) { this.data = data; }

    // Setters / Getters básicos (getData, getLeftChild, etc.)
    // ...

    public void addLeftChild(BinaryTree<T> child) { this.leftChild = child; }
    public void addRightChild(BinaryTree<T> child) { this.rightChild = child; }

    public boolean isLeaf() {
        return (!this.hasLeftChild() && !this.hasRightChild());
    }

    public boolean hasLeftChild() { return this.leftChild != null; }
    public boolean hasRightChild() { return this.rightChild != null; }
}
```

> 💡 **Nota:** Antes de invocar métodos sobre un hijo (como `hijoIzquierdo.preorden()`), **siempre** hay que preguntar si el hijo existe (`hasLeftChild()`).

---

## ⚙️ ¿Cómo devolver un recorrido en una Lista?

A veces no alcanza con imprimir el recorrido (`System.out.println()`), necesitamos recuperar una `List<T>` con los nodos. Como las recursiones son acumulativas, usamos un **método privado auxiliar**.

```java
public List<T> preorden(BinaryTree<T> ab) {
    List<T> result = new LinkedList<T>();
    this.preorden_private(ab, result);
    return result;
}

private void preorden_private(BinaryTree<T> ab, List<T> result) {
    result.add(ab.getData()); // RAIZ

    if (ab.hasLeftChild()) {
        preorden_private(ab.getLeftChild(), result); // IZQUIERDA
    }
    if (ab.hasRightChild()) {
        preorden_private(ab.getRightChild(), result); // DERECHA
    }
}
```

---
---

# Parte B: Conversión Sistemática de Expresiones

Para poder aplicar cálculos a strings algebraicos, los transformamos en un `BinaryTree`.

## ⚙️ 1. Construcción desde Posfija (Estrategia de Pila)
Si la entrada es `ab+c*de+/`, usamos un iterador de izquierda a derecha. Como los operandos ya están agrupados de antemano antes de los operadores, la táctica es **mandar hijos a la pila**.

```java
public BinaryTree<Character> convertirPostfija(String exp) {
  Stack<BinaryTree<Character>> p = new Stack<>();
  
  for (int i = 0; i < exp.length(); i++) {
     Character c = exp.charAt(i);
     BinaryTree<Character> result = new BinaryTree<>(c);
     
     if ((c == '+') || (c == '-') || (c == '/') || (c == '*')) {
         // Es operador! Desapilamos al revés de como entran
         result.addRightChild(p.pop());
         result.addLeftChild(p.pop());
     }
     p.push(result);
  }
  return p.pop(); // La raíz queda como último habitante de la pila
}
```

## ⚙️ 2. Construcción desde Prefija (Estrategia Recursiva)
En notación prefija (ej: `/*+abc+de`), los **operadores aparecen antes que los operandos**. Cada vez que vemos un operador, consumimos recursivamente recursos del String restante.

En criollo: Si leo un `+`, digo: "Bueno, armo este nodo de suma. Ahora voy a decirle a la llamada recursiva que trate de construir todo mi brazo izquierdo consumiendo lo que sigue de la palabra, y después que mi brazo derecho coma lo que sobra."

## ⚙️ 3. Construcción desde Infija (Estrategia Compleja)
Para expresiones con paréntesis como `(a+b)*c`, la manera idónea para la computadora es:
1. Parsear a Posfija usando una pila para jerarquizar operadores y purgar paréntesis.
2. Ejecutar la **Estrategia 1** (Construcción desde Posfija).

---
---

# Parte C: Ejercicio Práctico — "Valencia Total"

## 🎯 Problema
Un árbol químico completo baja como un arreglo (un *stream* con tamaño en `stream[0]`). Hay que calcular la valencia total (sumatoria de los valores de las **hojas**).

### 🏗️ Creación Iterativa (Por Niveles)
Dado que está completo desde arriba hacia abajo, una **Cola** es ideal para inyectar cada hijo.

```java
private static BinaryTree<Integer> createBinaryTreeIte(int[] stream) {
    if (stream.length <= 1) return null;
    
    BinaryTree<Integer> root = new BinaryTree<>(stream[1]);
    Queue<BinaryTree<Integer>> queue = new Queue<>();
    queue.enqueue(root);
    
    int i = 2; // arranco por el primer hijo
    while (i < stream.length) {
        BinaryTree<Integer> current = queue.dequeue();
        
        // Hijo Izquierdo
        current.addLeftChild(new BinaryTree<>(stream[i++]));
        queue.enqueue(current.getLeftChild());
        
        // Hijo Derecho
        if (i < stream.length) {
            current.addRightChild(new BinaryTree<>(stream[i++]));
            queue.enqueue(current.getRightChild());
        }
    }
    return root;
}
```

### 🏗️ Generar Sumatoria en las Hojas
Recorrido adaptado (Postorden o Preorden es lo mismo, estamos sumando).

```java
public static int calcularValencia(BinaryTree<Integer> arbol) {
    if (arbol.isLeaf()) {
        return arbol.getData(); // caso base: es hoja, aporta sumar
    }
    int suma = 0;
    if (arbol.hasLeftChild()) {
        suma += calcularValencia(arbol.getLeftChild());
    }
    if (arbol.hasRightChild()) {
        suma += calcularValencia(arbol.getRightChild());
    }
    return suma;
}
```

---

## 📚 Recursos y Referencias

- **Cátedra:** *Algoritmos y Estructuras de Datos* — UNLP. 2026.
- PDFs elaborados por Prof. Alejandra Schiavoni y Prof. Catalina Mostaccio.
