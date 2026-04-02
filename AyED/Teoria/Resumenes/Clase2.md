# 📝 Clase 2: Herencia, Clases Abstractas y Estructuras Lineales

Esta clase introduce conceptos avanzados de Programación Orientada a Objetos en Java y el estudio de Tipos de Datos Abstractos (TDA) lineales y el framework de colecciones de Java.

---

## 🧬 Parte 1: Herencia y Clases Abstractas

### Herencia (`extends`)
La herencia permite a una nueva clase (subclase) heredar los atributos y métodos de una clase existente (superclase), promoviendo la **reusabilidad de código**.
Java permite múltiples niveles de herencia, pero **la herencia es simple** (una clase solo puede heredar directamente de una superclase).

```java
public class Vehiculo {
    private String marca;
    public void setMarca(String marca) { this.marca = marca; }
    public String getMarca() { return marca; }
}

// Camion hereda de Vehiculo usando la palabra clave 'extends'
public class Camion extends Vehiculo {
    private int cargaMaxima;
    public void setCargaMaxima(int c) { this.cargaMaxima = c; }
}
```

### Sobreescritura (Override) y la palabra clave `super`
Una subclase puede reemplazar (sobreescribir) el comportamiento de un método heredado de su superclase, manteniendo el mismo nombre, retorno y parámetros.
Si se necesita invocar a la versión original de la superclase dentro de la subclase, se usa la palabra clave **`super`**.

```java
@Override
public String detalles() {
    return super.detalles() + "\nCarga Máxima: " + cargaMaxima;
}
```

### Upcasting y Downcasting
* **Upcasting:** Tratar a un objeto de una subclase como si fuera de su superclase (es seguro porque la subclase tiene todo lo de la super). Pierde acceso temporalmente a sus métodos específicos. Ej: `Vehiculo v = new Camion();`
* **Downcasting:** Recuperar el tipo específico partiendo de una referencia general. Conlleva casteos.

### La clase Cósmica `Object`
Todas las clases en Java heredan implícita o explícitamente de la clase `java.lang.Object`.
Dos métodos fundamentales que es buena práctica sobreescribir:
1. **`equals(Object o)`:** Compara lógicamente el contenido de dos objetos, en vez de su referencia en memoria (que es lo que hace `==`).
2. **`toString()`:** Produce una representación legible y textual del contenido del objeto.

### Clases y Métodos Abstractos
Definen conceptos abstractos que proveen un molde general, pero que son muy vagos para ser instanciados.
* No se puede instanciar (hacer `new`) una clase abstracta.
* Sus **métodos abstractos** carecen de cuerpo y fuerzan a sus subclases a sobreescribirlos e implementarlos.
* Si una clase tiene al menos un método abstracto, debe ser declarada abstracta.

```java
public abstract class FiguraGeometrica {
    // Las subclases están obligadas a implementar este método
    public abstract double getArea(); 
}
```

---

## 🧱 Parte 2: TDA Listas, Pilas y Colas

Un TDA es definido en términos de las operaciones que soporta, sin saber cómo está implementado en el backend.

### 1. Lista (List)
Secuencia lineal libre. Se pueden agregar, eliminar u obtener variables en cualquier posición.
* Operaciones: `add(e)`, `get(pos)`, `remove(pos)`, `size()`, `isEmpty()`.

### 2. Pila (Stack)
Estructura LIFO (Last-In First-Out). El último elemento insertado es el primero en ser retirado.
* Operaciones: `push(e)` (inserta en el tope), `pop()` (quita y retorna del tope), `top()` (solo lee el tope).

### 3. Cola (Queue)
Estructura FIFO (First-In First-Out). El primer elemento que ingresa es el primero en salir.
* Operaciones: `enqueue()` (inserta al final), `dequeue()` (elimina del frente), `head()` (lee del frente).

---

## ☕ Java Collections Framework

Provee implementaciones optimizadas para los TDA usando distintas tecnologías: arreglos, listas enlazadas, árboles o HashMaps (`java.util`).

### `ArrayList` vs `LinkedList`
| Característica | `ArrayList` | `LinkedList` |
| --- | --- | --- |
| **Backend** | Arreglo dinámico redimensionable | Nodos doblemente enlazados |
| **Acceso a índice (`get`)** | **O(1)** muy rápido (memoria contigua) | O(n) lento (recorre la lista) |
| **Insertar/Borrar elementos medios** | O(n) más lento por corrimientos | O(n) pero suele ser más rápido en la práctica |
| **Insertar extremos (`add`)** | Amortizado O(1) pero O(n) si requiere recargar | **O(1)** muy rápido |

### Tipos Genéricos (`<T>`)
Para evitar los dolores de cabeza de usar `Object` y castear las cosas manualmente todo el tiempo, Java permite definir colecciones "Genéricas", restringiendo qué tipos de datos puede guardar en *tiempo de compilación*.

```java
// Lista que SOLO admite objetos Vino
List<Vino> listaVinos = new ArrayList<>();
listaVinos.add(new Vino("Malbec"));
```

### Iteradores (Iterator y For-each)
Un `Iterator` ayuda a recorrer de manera segura cualquier colección sin conocer su implementación subyacente. Usualmente se usa internamente gracias a los bucles **for-each**.

```java
// Forma con iterador explícito
Iterator<Vino> it = listaVinos.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// Forma moderna con for-each (Por detrás usa Iterator)
for (Vino v : listaVinos) {
    System.out.println(v);
}
```
