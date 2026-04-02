# 📝 Clase 1: Repaso de Conceptos Básicos de JAVA

Esta clase introductoria repasa los conceptos fundamentales de la Programación Orientada a Objetos (POO) y su implementación en el lenguaje Java.

## 🏗️ La Plataforma JAVA

En Java, el código fuente se escribe en archivos con extensión `.java`. Luego, el compilador transforma este código en **bytecodes**, que es el lenguaje comprensible por la Máquina Virtual de Java (JVM).
* **`javac.exe`**: Es el compilador. Genera un archivo `.class`.
* **`java.exe`**: Es el intérprete que ejecuta los bytecodes contenidos en el archivo `.class`.

---

## 🧩 Programación Orientada a Objetos

Los programas están compuestos por varios **objetos** que se comunican entre sí mediante el envío de mensajes.
Cuando el programa se ejecuta, estos objetos se van creando dinámicamente en la memoria **HEAP**.

* **Objeto:** Es una entidad que combina un **estado** (datos/atributos) y un **comportamiento** (métodos/acciones).
* **Clase:** Es el "molde" o plantilla a partir del cual se instancian o crean los objetos.

### Declaración de una Clase
Un archivo de origen Java debe coincidir en nombre con la clase que define, respetando mayúsculas y minúsculas. 

```java
package whatsapp;
import java.awt.Image;

public class Contacto {
    // 1. Estado (Variables de instancia)
    private String nombre;
    private Image imagen;
    private String estado;
    private int id;
    
    // 2. Comportamiento (Métodos)
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
```

---

## 📦 Tipos de Datos en Java

Java clasifica los tipos de datos en dos grandes categorías:

1. **Tipos Primitivos:** Mantienen valores simples por eficiencia y no son objetos. Existen 8:
   * **Enteros:** `byte`, `short`, `int`, `long`
   * **Flotantes:** `float`, `double`
   * **Caracteres:** `char`
   * **Lógicos:** `boolean`
   * *Valores por defecto:* Numéricos inician en `0`, booleanos en `false`.

2. **Tipos Referenciales (Objetos):** Las variables de este tipo contienen la **dirección de memoria** (referencia) al objeto real creado en la memoria HEAP.
   * *Valor por defecto:* Inician en `null`.

### Clases Wrappers (Envolventes)
Para poder tratar a los tipos primitivos como objetos, Java proporciona sus respectivas clases **wrappers** (ej. `Integer`, `Double`, `Character`, `Boolean`). Estas clases son **inmutables**.

* **Autoboxing:** Conversión automática de un primitivo a su clase wrapper. Ejemplo: `Integer i = 7;`
* **Unboxing:** Conversión automática de wrapper a primitivo. Ejemplo: `int num = i;`

---

## 🛠️ Creación de Objetos y Constructores

El operador **`new`** se encarga de crear el objeto, reservando memoria en la HEAP e invocando a su **constructor**. 

### Constructores
* Tienen exactamente el **mismo nombre que la clase**.
* **No retornan valor** (ni siquiera `void`).
* Son llamados automáticamente al instanciar el objeto.
* Si no declaras ningún constructor, Java inserta automáticamente uno _por defecto_ (vacío).

### Sobrecarga de Constructores
Permite construir objetos de distintas maneras, creando varios constructores con **distinta cantidad y/o tipo de parámetros**.

```java
public class Vehiculo {
    private String marca;
    private double precio;
    
    // Constructor sin parámetros
    public Vehiculo() {}
    
    // Constructor 1 (Sobrecarga)
    public Vehiculo(String marca) {
        this.marca = marca;
    }
    
    // Constructor 2 (Sobrecarga)
    public Vehiculo(String marca, double precio) {
        this.marca = marca;
        this.precio = precio;
    }
}
```

---

## 🎯 Alcance de las Variables (Scope)

1. **Variables de Instancia:** Definidas dentro de la clase, pero fuera de cualquier método. Representan el estado del objeto. Existen mientras el objeto exista en la memoria HEAP.
2. **Variables Locales:** Definidas dentro de un método (incluyendo parámetros). Se guardan en la memoria **STACK**. Existen estrictamente durante la ejecución del método y deben ser inicializadas a mano.

---

## 🌟 La Palabra Clave `static`

El modificador `static` asocia variables o métodos **a la clase misma**, no a una instancia particular.

* **Variables estáticas:** Son compartidas por todas las instancias de la clase.
* **Métodos estáticos:** Pueden invocarse apuntando directamente a la clase (Ej: `SumaEnteros.sumaValores()`) sin necesidad de intanciar el objeto. *Atención: los métodos estáticos no pueden usar variables o atributos de instancia.*

---

## 📚 Arreglos (Arrays)

Los arreglos manipulan un conjunto de datos (primitivos u objetos) del **mismo tipo** bajo un único nombre en posiciones contiguas.
 Tienen un tamaño **fijo** que, una vez creado el arreglo, no puede alterarse.

### Declaración e Inicialización
```java
// Forma 1: Declarar y crear especificando tamaño
int[] intArray = new int[5];  
intArray[0] = 6;

// Forma 2: Un solo paso infiriendo el tamaño por los elementos
int[] numeros = {6, 3, 2, 4, 9};

// Arreglos de Objetos
Cliente[] clientes = {new Cliente(), new Cliente()};
```

### Recorrido de Arreglos
Podemos recorrer arreglos iterando por sus índices, o usando un **for-each** para mayor legibilidad:

```java
// Recorrido Tradicional
for (int i = 0; i < datos.length; i++) {
    System.out.println(datos[i]);
}

// Recorrido For-Each (Lee: Para cada elemento de la colección "datos")
for (int elto : datos) {
    System.out.println(elto);
}
```

---

## 🔄 Pasaje de Parámetros en Java

En Java, **TODO EL PASAJE DE PARÁMETROS ES POR VALOR.** Lo que esto significa depende del tipo de dato:

1. **Tipos Primitivos:** Se pasa una **copia** del valor exacto. Alterarlo dentro del método no afecta a la variable original.
2. **Wrappers y Strings:** Como son inmutables, cualquier cambio genera una nueva variable dentro del método, sin modificar la variable referenciada desde donde se llamó.
3. **Tipos Referenciales (Objetos):** Se pasa una **copia de la dirección de memoria**. ¡Cuidado! Si dentro del método usas esa dirección para alterar el estado del objeto (ej. `miObjeto.setNombre("Nuevo")`), este cambio **SÍ se reflejará exteriormente**, porque tanto la variable original como la copia referencian al mismo objeto de la HEAP. 

Sin embargo, si a la _referencia_ interna se le asigna un `new Objeto()`, se rompe el vínculo con la variable externa.
