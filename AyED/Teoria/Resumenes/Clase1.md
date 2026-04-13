# 📘 Repaso de conceptos básicos de JAVA

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Clases e instancias, Tipos de datos, Variables estáticas, Arreglos, Pasaje de parámetros

---

## 🎯 Programación Orientada a Objetos (POO)

Los programas orientados a objetos están compuestos por varios objetos. Estos objetos se comunican entre ellos mediante el envío de mensajes. Cuando un programa se está ejecutando, los objetos necesarios se van creando en la memoria **HEAP**.

> *"Cada uno de estos objetos es una entidad de software que combina un estado o datos y comportamiento o métodos. Estos objetos se crean a partir de un molde o clase."*

En criollo: Una **clase** es como el plano de una casa (define cómo va a ser), y un **objeto** (o instancia) es la casa ya construida en la memoria, lista para ser usada.

---

## ⚙️ Declaración de una Clase en JAVA

Una clase describe el estado (variables de instancia) y el comportamiento (métodos de instancia) que tendrán los objetos que con ella se creen.

**Reglas principales:**
1. En la primera línea: palabra clave `package` seguida del nombre del paquete.
2. Palabra clave `class` seguida del nombre de la clase.
3. El archivo origen debe guardarse con el mismo nombre que la clase y extensión `.java`.

### 📦 Ejemplo: Clase Contacto

```java
package whatsapp;
import java.awt.Image;

public class Contacto {
 private String nombre;
 private Image imagen;
 private String estado;
 private int id;
 
 public String getNombre() {
  return nombre;
 }
 
 public void setNombre(String nombre) {
  this.nombre = nombre;
 }
 // ...
}
```

---

## 📊 Tipos de datos en Java

En Java hay dos categorías de tipos de datos: **tipo primitivo** y **tipo referencial**.

| Tipo | Descripción | Ejemplo de declaración |
|---|---|---|
| **Primitivos** | Mantienen valores simples y NO son objetos. Por razones de eficiencia. | `int hora = 12;` |
| **Referenciales** | Mantienen la dirección de memoria de objetos (instancias de clases). | `Cliente cli = new Cliente();` |

### Valores por Defecto e Inicialización

Si la definición de una clase no inicializa variables de instancia, toman los siguientes valores por defecto:

| Tipo | Valor por defecto |
|---|---|
| `boolean` | `false` |
| `char` | `\u0000` (nulo) |
| `byte` / `short` / `int` / `long` | `0` |
| `float` / `double` | `0.0` |
| Tipos referenciales | `null` |

> 💡 **Nota:** Las **variables locales** (declaradas dentro de un método) deben inicializarse explícitamente antes de usarse, ya que NO toman valores por defecto.

---

## 🎯 Clases Wrappers y Autoboxing

Java proporciona clases "wrappers" (envoltorios) en el paquete `java.lang` para manipular a los datos primitivos como si fueran objetos. Las clases wrapper y la clase `String` son **inmutables**.

| Tipo primitivo | Clase Wrapper correspondiente |
|---|---|
| `char` | `Character` |
| `boolean` | `Boolean` |
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |

### Autoboxing y Unboxing

- **Autoboxing:** Conversión automática que realiza el compilador entre los tipos primitivos y sus clases wrappers. (Ej: `Integer i = 7;`).
- **Unboxing:** Conversión inversa, de un wrapper a un tipo primitivo. (Ej: `int i1 = i;`).

---

## ⚙️ Creación e Instanciación de Objetos

Para instanciar una clase (crear un objeto), se usa el operador `new`.

**Pasos de instanciación:**
1. Se aloca espacio para la variable.
2. Se aloca espacio para el objeto en la **HEAP** y se inicializan los atributos con valores por defecto.
3. Se inicializan explícitamente los atributos del objeto.
4. Se ejecuta el **constructor**.
5. Se asigna la referencia del nuevo objeto a la variable en el **STACK**.

### 🏗️ Constructores

Son piezas de código que permiten definir un estado inicial al momento de crear el objeto. 
- Deben llamarse **igual** que la clase (empiezan con mayúscula).
- **No retornan** valor (ni siquiera `void`).
- Son invocados **automáticamente** por el operador `new`.

```java
public class Vehiculo {
  private String marca;
  private double precio;

  // Constructor con parámetros
  public Vehiculo(String marca, double precio) {
     this.marca = marca;
     this.precio = precio;
  } 
}
```

> 💡 **Sobrecarga de constructores:** Se pueden definir múltiples constructores que difieran en la cantidad o tipo de sus parámetros. Si la clase NO tiene constructores, Java le inserta uno "default" sin parámetros. Si le definís uno, el compilador NO inserta el default.

---

## 🎯 Variables de instancia vs locales

| Ubicación | Nombre | Ciclo de vida |
|---|---|---|
| Fuera de cualquier método | **Variable de instancia** | Existen mientras exista el objeto al que pertenecen. |
| Dentro de un método o parámetros | **Variable local** | Existen solo durante la ejecución de ese método (se destruyen al finalizarla). |

---

## 🎯 La palabra clave `static` (Variables y Métodos de Clase)

La palabra clave `static` asocia atributos o métodos directamente **con la clase**, en lugar de asociarlos a una instancia (objeto) particular.

- **Variables estáticas:** Son compartidas por *todas* las instancias de la clase.
- **Métodos estáticos:** Pueden invocarse mediante el nombre de la clase sin crear un objeto (ej: `Contacto.getUltCont()`).
  - ❌ Los métodos de clase **no tienen acceso** a las variables de instancia (no pueden usar `this`). Podrían usar solo las variables estáticas y las variables locales.

### 📦 Ejemplo: Atributo estático

```java
public class Contacto {
 private static int ultCont = 0; // Variable compartida de clase
 private int id;
 
 public Contacto() {
  ultCont++;
  this.id = ultCont;
 }
}
```

---

## 📊 Arreglos en JAVA

Un arreglo es un objeto de tamaño **fijo** que agrupa un conjunto de valores del mismo tipo en memoria contigua. La dimensión la conocemos a través de la propiedad `.length`.

### 📦 Ejemplo: Declaración, Creación e Inicialización

```java
// Varias líneas
int[] intArray; 
intArray = new int[5]; // Se crea con ceros por defecto
intArray[0] = 6;
// ...

// Todo en un paso:
int[] arrayDirecto = {6, 3, 2, 4, 9};
Cliente[] cliArray = {new Cliente(), new Cliente(), new Cliente()};
```

### Recorridos
Se pueden recorrer con el `for` tradicional, o con el `for-each` (mucho más legible):

```java
// Bucle for-each
for (int elto : arrayDirecto) {
    result = result + elto; 
}
```

### Matrices (Arreglos Multidimensionales)

En Java las matrices son esencialmente "arreglos de arreglos".

```java
int[][] notas = {
    {66, 78, 78}, // Fila 0
    {76, 80, 80}  // Fila 1
};

// Recorrido multidimensional con for-each
for (int[] fila : notas) {
    for (int elto : fila) {
        System.out.print(elto);
    }
}
```

---

## ⚙️ Pasaje de parámetros en Java

En Java **los parámetros siempre se pasan por valor**, es decir, se envía una *copia*. La clave y confusión suelen venir con el tipo de dato que se está copiando.

| Tipo de parámetro | ¿Qué se copia? | ¿Afecta al parámetro original? |
|---|---|---|
| **Primitivos (`int`, `float`, etc.)** | Se pasa una copia del valor numérico/lógico. | ❌ No. Las modificaciones del valor ocurren solo en la copia local al método. |
| **Clases Wrapper y `String`** | Se pasa una copia de la referencia. Como son inmutables, la reasignación crea un objeto nuevo. | ❌ No afecta al original por inmutabilidad. |
| **Objetos (Tipos Referenciales)** | Se pasa una **copia de la referencia** (de la flecha a la HEAP). | ✅ Sí se puede alterar su **estado** interno mediante sus métodos (Ej: `c.setNombre("Pilar")`). Pero NO se puede hacer que la variable original apunte a otro objeto con un `new`. |

En criollo: Si pasás un objeto a una función, le estás dando al método una copia de las "llaves" de la misma casa. Si desde esa función repintan paredes (cambian el estado de sus propiedades), vos vas a ver esos cambios en tu casa. Pero si ellos tiran sus llaves y compran una casa nueva adentro del método (`o = new Objeto()`), tus llaves originales van a seguir abriendo la casa vieja.

---

## 📚 Recursos y Referencias

- **Cátedra:** *Algoritmos y Estructuras de Datos* — UNLP. 2026.
- PDFs elaborados por Prof. Alejandra Schiavoni y equipo.
