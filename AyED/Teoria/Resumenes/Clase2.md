# 📘 Herencia, Clases Abstractas y Estructuras Lineales

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Herencia, Clases Abstractas, Pilas, Colas, Listas, Framework de Colecciones

---

# Parte A: Herencia y Clases Abstractas

## 🎯 Herencia (`extends`)

La herencia permite a las clases expresar similitudes entre objetos que tienen características y comportamiento común. Tomar una clase existente y construir una versión especializada promueve la **reusabilidad de código**.

- **Superclase / Clase base:** La clase de la cual se hereda (ej. `Vehiculo`).
- **Subclase / Clase derivada:** La clase que hereda, extiende atributos y métodos (ej. `Camion`).

> *"La herencia puede agregar atributos y comportamiento e incluso puede reemplazar el comportamiento heredado (sobrescritura)."*

En criollo: Herencia es agarrar un molde que ya sirve (como un "Vehículo") y hacer un molde más específico (como "Camión"), sin tener que volver a programar cómo arrancar, acelerar o frenar.

### 📦 Ejemplo: Uso de herencia y sobrescritura

```java
public class Vehiculo {
   private String marca;
   private double precio;

   public String detalles() {
     return "Vehiculo marca: " + getMarca() + "\nPrecio: " + getPrecio();
   } 
}

public class Camion extends Vehiculo {
   private boolean tieneDobleCaja;
   private int cargaMaxima;
   
   // Sobrescritura (Override) del método de la superclase
   public String detalles() {
     // Usamos 'super' para invocar el método original detallando Vehiculo
     return super.detalles() + "\ncarga máxima:" + getCargaMaxima();
   } 
}
```

---

## 🎯 Upcasting y Downcasting

Tratar a una referencia de la clase derivada como una referencia de la clase base se llama **upcasting**. Moverse hacia abajo en la jerarquía (para recuperar el tipo específico) se llama **downcasting**.

| Tipo | Mapeo en Memoria | Seguridad |
|---|---|---|
| **Upcasting** | Hacia arriba en la jerarquía (`Vehiculo vc = new Camion();`) | ✅ Seguro, porque un Camión siempre es un Vehículo. |
| **Downcasting** | Hacia abajo en la jerarquía (`Camion c = (Camion) vc;`) | ❌ Inseguro. Suele requerir chequear con `instanceof` para evitar errores `ClassCastException`. |

### Binding Dinámico
Si hacemos `Vehiculo vc = new Camion(); vc.detalles()`, ¿qué método se ejecuta? El de `Camion`. 
En la POO, la decisión sobre qué versión del método invocar se toma en tiempo de ejecución basándose en el objeto real instanciado, no en el tipo de la variable de referencia. **Esto es el binding dinámico.**

---

## ⚙️ La clase Object (equals y toString)

La clase `Object` (de `java.lang`) es la raíz de todas las clases en Java. Cuando omitimos la palabra clave `extends`, el compilador agrega implícitamente `extends Object`.

Dos de sus métodos más sobrescritos son:
- `equals(Object obj)`: Su versión en `Object` compara con `==` (direcciones de memoria). Solemos sobrescribirlo para comparar el *contenido* lógico del objeto.
- `toString()`: Su original devuelve una representación fea con la dirección. Lo sobrescribimos para dar una representación textual legible de la instancia.

```java
public boolean equals(Object o){
  boolean result = false;
  // Validamos si es no nulo y del mismo tipo
  if ((o != null) && (o instanceof Fecha)){
   Fecha f = (Fecha)o;
   // Comparamos el contenido
   if (f.getDia() == this.getDia() && f.getMes() == this.getMes() && f.getAño() == this.getAño()) {
       result = true;
   }
  }
  return result;
}
```

---

## 🏛️ Clases y Métodos Abstractos

Conceptos que no tiene sentido instanciar directamente se modelan mejor con clases abstractas.

- **Método Abstracto:** Es un método **sin cuerpo** cuya firma tiene la palabra clave `abstract`. Obliga a que cualquier subclase concreta lo sobrescriba.
- **Clase Abstracta:** Cualquier clase que posea al menos un método abstracto *debe* ser abstracta. No permite crear instancias (`new`). Sin embargo, pueden tener métodos concretos.

En criollo: Una "Figura Geométrica" abstracta te obliga a implementar cómo se dibuja y cómo calcula su área, porque ella sola no sabe cómo, pero exige que todos sus "hijos" (Círculos, Rectángulos) sí tengan esa capacidad.

---
---

# Parte B: Colecciones y TDA

## 🎯 Tipo de Dato Abstracto (TDA)

Un TDA es un tipo de dato definido solamente en términos de sus **operaciones** y **restricciones**. Oculta cómo están implementados por dentro.

| TDA | Definición | Política |
|---|---|---|
| **Lista (List)** | Secuencia de elementos manipulables en cualquier posición (agregar o eliminar donde sea). | Aleatoria/Flexible |
| **Pila (Stack)** | Secuencia que se actualiza por un solo extremo (tope). | **LIFO** (Último en entrar, primero en salir) |
| **Cola (Queue)** | Secuencia que se actualiza por dos extremos (frente y rabo). | **FIFO** (Primero en entrar, primero en salir) |

---

## 📊 Framework de Colecciones en Java

Java ofrece un amplio ecosistema bajo `java.util` con interfaces (List, Queue, Set, Map) y sus implementaciones (Estructuras de datos que manejan internamente esos interfaces).

### Tecnologías de Almacenamiento

| Tecnología | Pros ✅ | Contras ❌ |
|---|---|---|
| **Arreglo (ArrayList)** | Muy eficiente para lectura y acceso por índices. | Ineficiente al agregar/eliminar en el medio (requiere mover todo). |
| **Lista Enlazada (LinkedList)** | Muy rápida para agregar o eliminar elementos en extremos. | Lenta para lectura y acceso secuencial (hay que recorrer la lista). |
| **Árbol (TreeSet/Map)** | Valores ordenados y permite búsquedas muy rápidas. | Complejidad de balanceo. |
| **Tablas Hash (HashMap)** | Acceso, borrado e inserción en O(1). | No guarda orden y consume memoria extra para claves. |

---

## 🎯 ArrayList vs LinkedList

Ambas implementan la interface `List`.

|  | ArrayList | LinkedList |
|---|---|---|
| **Estructura Interna** | Arreglo dinámico redimensionable | Nodos doblemente enlazados |
| **Acceso Aleatorio (get)** | O(1). Acceso constante muy veloz | O(n). Requiere recorrer desde cabeza o cola |
| **Inserción/Borrado** | O(n). Lento, especialmente si se inserta al comienzo, porque desplaza el resto. | O(1) en los extremos, rápido para manipular la estructura sin redimensionar en memoria contigua. |
| **Cuándo usar** | Búsquedas frecuentes y lectura | Frecuentes modificaciones (add, remove) en las puntas |

---

## ⚙️ Tipos Genéricos (Generics)

Antes de Java Generics (Java 1.5), las colecciones manipulaban objetos de clase `Object`, lo que traía problemas con pérdidas de tipo de dato y errores `ClassCastException` imprevistos.

Con genéricos (`<T>`), le decís a la colección qué tipo exacto va a almacenar, garantizando seguridad en **tiempo de compilación**.

```java
// Sin genéricos (Malo!)
List lista_vinos = new LinkedList();
lista_vinos.add(new Vino("Mendoza"));
Vino v = (Vino) lista_vinos.get(0); // El casteo forzado puede explotar

// Con genéricos (El estándar moderno)
List<Vino> lista_vinos = new ArrayList<>();
lista_vinos.add(new Vino("Mendoza"));
Vino v = lista_vinos.get(0); // Seguro y limpio
```

En criollo: En vez de una caja que puede guardar cualquier cosa y tenés que revisar e "intentar" sacarlo con la forma correcta, una lista genérica es una caja exclusiva que solo acepta (por ejemplo) "Vinos". Si tratás de meter una "Cerveza", salta un error inmediato en el editor antes de compilar.

---

## ⚙️ Iteradores

Un `Iterator` es un patrón de diseño que proporciona un método secuencial en colecciones abstractas sin exponer la estructura interna subyacente.

Métodos clave de `java.util.Iterator`:
- `boolean hasNext()`: Revisa si hay más elementos.
- `E next()`: Retorna el elemento bajo el cursor y avanza.

```java
List<Integer> lista = new ArrayList<>();
// ... agregar datos ...

Iterator<Integer> it1 = lista.iterator();
while (it1.hasNext()) {
    System.out.println(it1.next());
}

// Su azúcar sintáctico moderno en Java: foreach
for (Integer i : lista) {
    System.out.println(i);
}
```

---

## 📚 Recursos y Referencias

- **Cátedra:** *Algoritmos y Estructuras de Datos* — UNLP. 2026.
- PDFs elaborados por Prof. Alejandra Schiavoni y equipo.
