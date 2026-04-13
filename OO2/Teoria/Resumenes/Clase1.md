# 📘 Clase 1: Introducción a Refactoring

**Materia:** Orientación a Objetos 2 (OO2) — UNLP 2026  
**Docente:** Dra. Alejandra Garrido  
**Tema Central:** La evolución del software, las Leyes de Lehman, definición formal de Refactoring y primeros refactorings del catálogo.

---

## 🎯 ¿Por qué cambia el Software?

El software **no es estático**. Por más bien diseñado que esté, inevitablemente va a cambiar. Esto fue formalizado por Meir M. Lehman en sus famosas leyes:

### Leyes de Lehman (Evolución del Software)

| Ley | Año | Enunciado |
|---|---|---|
| **Continuing Change** | 1974 | Los sistemas deben adaptarse continuamente o se vuelven progresivamente **menos satisfactorios**. |
| **Continuing Growth** | 1991 | La funcionalidad de un sistema debe ser **incrementada continuamente** para mantener la satisfacción del cliente. |
| **Increasing Complexity** | 1974 | A medida que un sistema evoluciona, su complejidad se **incrementa**, a menos que se trabaje activamente para evitarlo. |
| **Declining Quality** | 1996 | La calidad de un sistema va a ir **declinando** a menos que se haga un mantenimiento riguroso. |

> **En criollo:** Si no cuidás tu código activamente, se pudre solo. No alcanza con que funcione hoy.

---

## 💰 El Costo del Mantenimiento

El mantenimiento del software tiene distintas categorías:
- **Correctivo:** Arreglar bugs.
- **Evolutivo:** Agregar funcionalidad nueva.
- **Adaptativo:** Adaptar a nuevos entornos (librerías, OS, etc.).
- **Perfectivo:** Mejorar rendimiento o legibilidad.
- **Preventivo:** Limpiar código para evitar problemas futuros.

> **Dato clave del PDF:** Entender código existente consume el **50% del tiempo de mantenimiento**. Por eso escribir código legible no es un lujo, es una necesidad económica.

---

## 🍝 El Problema: Big Ball of Mud

Cuando nadie cuida la arquitectura del software, se termina generando lo que Brian Foote y Joe Yoder llamaron un **"Big Ball of Mud"** (Gran Bola de Barro): un sistema sin estructura reconocible, donde todo depende de todo y cualquier cambio rompe otra cosa.

### Diseñar es difícil

- Los elementos distintivos de la arquitectura **no surgen hasta después de tener código que funciona**.
- No se trata solo de agregar, sino de **adaptar, transformar, mejorar**.
- Construir el sistema perfecto **es imposible**.
- Los errores y el cambio **son inevitables** → hay que aprender del feedback.

> *"Reusable software is the result of many design iterations. Some of these iterations occur after the software has been reused"* — Bill Opdyke, 1992.

---

## 🔧 Refactoring: Definición Formal

### Como sustantivo:
> **Refactoring** es una transformación que se realiza en la estructura interna del software para hacerlo **fácil de entender** y **más barato de modificar**, y que **preserva el comportamiento observable**.

Cada refactoring tiene:
- Un **nombre** específico con el que está catalogado.
- Una secuencia de **pasos ordenados** ("mecánica").

### Como proceso:
Es el proceso a través del cual se cambia un sistema de software:
- Para mejorar la organización, legibilidad, adaptabilidad y mantenibilidad del código **luego de que fue escrito**.
- Que **NO altera el comportamiento externo** del sistema.

### Regla de oro:
> Si el comportamiento cambia (no compila, no pasa los tests), entonces el cambio **NO fue un refactoring**.

---

## ⚙️ Estructura de un Refactoring

Todo refactoring del catálogo tiene dos componentes fundamentales:

| Componente | Descripción |
|---|---|
| **Precondiciones** | Condiciones que se verifican **antes** de aplicar el cambio para asegurar que es seguro hacerlo. |
| **Mecánica** | Pasos ordenados que cuidan la preservación del comportamiento. Después de cada paso: **compilar y testear**. |

---

## 📦 Ejemplo del PDF: Jerarquía Product (HotelStay / CarRental)

El PDF presenta una jerarquía con `Product`, `HotelStay` y `CarRental` para descubrir y aplicar los primeros refactorings paso a paso.

### Situación Inicial

```java
// HotelStay
public double cost;   // Variable pública repetida
startDate() { return timePeriod.start; }
endDate()   { return timePeriod.end; }
price()     { return timePeriod.duration() * hotel.nightPrice() * hotel.discountRate(); }
priceFactor() { return cost / this.price(); }

// CarRental
public double cost;   // Variable pública repetida
startDate() { return timePeriod.start; }
endDate()   { return timePeriod.end; }
price()     { return company.price() * company.promotionRate(); }
cost()      { return cost; }
```

### Code Smells detectados:
1. La variable `cost` está declarada como **pública** → rompe el **encapsulamiento**.
2. La variable `cost` está **repetida** en ambas subclases → **código duplicado**.
3. Los métodos `startDate()` y `endDate()` son **idénticos** en ambas → **código duplicado**.

---

## 📚 Catálogo: Primeros Refactorings Presentados

### 1. Encapsulate Field (p.206)

**Problema:** Variable de instancia pública.

**Mecánica:**
1. Si no existen referencias externas a la variable → cambiar a `private` directamente.
2. Si existen referencias → crear getters/setters, cambiar todas las referencias externas por invocaciones a los métodos, y luego cambiar la visibilidad a `private`.
3. Compilar y testear.

---

### 2. Pull Up Field (p.320)

**Problema:** La misma variable de instancia está repetida en varias subclases.

**Precondiciones:**
- La variable se usa **de la misma manera** en las subclases (semánticamente representan lo mismo).
- La variable comparte el **mismo nombre y tipo** en las subclases (si no, renombrar antes).
- La variable con ese nombre **no debe existir** ya en la superclase.

**Mecánica:**
1. Crear una nueva variable de instancia en la superclase.
2. Si la variable en las subclases es `private`, declararla como `protected` para que las subclases puedan referenciarla.
3. Borrar la variable de las subclases.
4. Compilar y testear.

---

### 3. Pull Up Method (p.322)

**Problema:** El mismo método está repetido en varias subclases.

**Precondiciones:**
- El cuerpo de los métodos debe ser **idéntico**.
- La signatura debe ser **idéntica** (si no, renombrar antes).
- Debe haber una **superclase común**.
- No debe haber **conflicto** con métodos de la superclase.
- Todos los elementos a los que accede el método deben ser **accesibles desde la superclase**.

**Mecánica:**
1. Si el método referencia variables que aún no están en la superclase → aplicar `Pull Up Field` primero.
2. Crear un método en la superclase (`Product`) y copiar el cuerpo.
3. Borrar el método de una de las subclases.
4. Compilar y testear.
5. Repetir para cada subclase hasta que solo quede el método en la superclase.

---

## 🧠 Características del Refactoring (Resumen)

### ¿Qué implica?
- Eliminar duplicaciones.
- Simplificar lógicas complejas.
- Clarificar códigos oscuros.

### ¿Cuándo aplicarlo?
- ✅ Una vez que tengo **código que funciona y pasa los tests**.
- ✅ Cuando encuentro **código difícil de entender** (ugly code).
- ✅ Cuando tengo que hacer un cambio y **necesito reorganizar primero**.

### ¿Cómo ayuda?
- Introduce mecanismos que solucionan problemas de diseño.
- A través de **cambios pequeños** (muchos cambios pequeños son más seguros que un gran cambio).
- Cada pequeño cambio pone en evidencia **otros cambios necesarios**.

---

## 👃 Bad Smells (Code Smells)

Son indicios de problemas en el código que requieren la aplicación de refactorings. Listado inicial presentado en esta clase:

| Code Smell | Descripción breve |
|---|---|
| **Romper encapsulamiento** | Variables públicas expuestas. |
| **Código duplicado** | El mismo código aparece en muchos lugares. |
| **Clase larga** | Una clase intenta hacer demasiado trabajo. |
| **Método largo** | Un método tiene demasiadas líneas. |
| **Switch statements** | Condicionales que discriminan tipos de objetos. |
| **Clase de datos** | Solo tiene variables y getters/setters. |
| **No "es un"** | Herencia mal aplicada. |

> El catálogo completo de Code Smells y sus refactorings asociados se desarrolla en profundidad en la **Clase 2**.
