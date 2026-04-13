# 📘 Árboles Generales

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Árboles k-arios (Generales), Terminología, Cuantificación de Nodos, Representaciones en Memoria, Recorridos

---

# Parte A: Definiciones Formales

## 🎯 ¿Qué es un Árbol General?

Un árbol general (o árbol *k-ario*) es una colección de nodos tal que:
- Puede estar vacía.
- Puede estar formada por un nodo distinguido `R` (Raíz) y un conjunto de árboles `T1, T2... Tk` (Subárboles), donde la raíz de cada subárbol está conectada a `R` por una arista.

A diferencia del binario, un nodo puede tener **cualquier cantidad de hijos**, por ende las estrategias de almacenamiento y recorrido varían substancialmente.

### 📊 Conceptos Clave

| Término | Definición en Árbol General |
|---|---|
| **Grado de un nodo** | Cantidad de hijos *directos* que posee ese nodo. |
| **Grado del árbol** | El grado máximo entre todos los nodos que lo componen (el nodo más "poblado"). |
| **Altura (h)** | Longitud del camino más largo desde la raíz hasta la hoja más profunda. (Nota: Las hojas tienen altura 0, y la altura del árbol es la altura respecto de la raíz). |
| **Profundidad / Nivel** | Longitud del camino **desde la raíz** hasta el nodo `ni` en particular. |

---

## ⚙️ Árbol Lleno y Completo (Fórmulas para Grado K)

En un árbol binario sabíamos que cada nodo interno tenía 2 hijos. En un árbol general, un árbol "lleno" tiene siempre exactamente `K` hijos en cada nodo interno.

### 1. Árbol Lleno de Grado `K`

Cada nodo interno tiene grado `k` y todas las hojas están en el mismo nivel `h`.
Esto genera una **serie geométrica** de nodos por nivel (`k^0` en la raíz, `k^1` en el nivel uno... `k^h` en las hojas). 

> **Fórmula de Nodos (Árbol Lleno):**  
> `N = (k^(h+1) - 1) / (k-1)`

### 2. Árbol Completo de Grado `K`

Es lleno hasta el nivel `h-1`, y el nivel `h` se llena estrictamente de izquierda a derecha.

> **Rango de Nodos (Árbol Completo):**  
> Varía entre:  `(k^h + k - 2) / (k-1)` *(Mínimo, 1 hoja en nivel h)*  
> y: `(k^(h+1) - 1) / (k-1)` *(Máximo, es lleno)*

En criollo: Para grado `k=3` (ternario), la progresión de nodos por piso sería 1 ➔ 3 ➔ 9 ➔ 27... El árbol será "lleno" solo si ninguna rama se corta antes de tiempo (todos llegan al piso inferior con 3 hijos cada uno).

---
---

# Parte B: Representación en la Computadora

Como un nodo ya no tiene a priori solo 2 referencias (izquierdo y derecho), ¿cómo los modelamos en memoria?

## 1. Con Arreglos
Cada nodo guarda el dato y un `Array` de punteros a sus hijos.
- ❌ **La Gran Contra:** Desperdicio de espacio bestial si el árbol tiene grado máximo 100, pero muchísimos nodos son hojas vacías o tienen apenas 2 o 3 hijos.

## 2. Con Listas Enlazadas (La Recomendada `java.util.List`)
Cada nodo contiene la información y una lista de crecimiento elástico conteniendo referencias directas a todos sus hijos.
- ✅ **Ventaja:** Crecimiento dinámico y sin desperdicio masivo de RAM.

## 3. Hijo Más Izquierdo, Hermano Derecho
Se mapea de facto como si fuera un árbol binario.
- `leftChild`: Referencia explícita *sólicamente* al primer hijo nacido (hijo de más a la izquierda).
- `rightChild`: Referencia al "próximo hermano" que comparte su mismo padre.

---

# Parte C: Recorridos Genéricos

Dado que no hay ni "izquierdo" ni "derecho", el recorrido Inorden no es comúnmente viable a menos que definamos heurísticas arbitrarias en el grupo de hijos. Los reinantes son:

### ⚙️ 1. Recorrido Pre_Orden (Raíz, luego hijos)
Muy útil. Por ejemplo: Para listar el contenido de un **Sistema de Archivos** o Directorio. Primero listamos el directorio que nos pasan (Raíz), luego abrimos sus subdirectorios de a uno (recursión secuencial).

```java
public void preOrden() {
    System.out.println(this.dato); // Proceso la carpeta donde estoy parado

    List<ArbolGeneral<T>> hijos = this.getHijos();
    for (ArbolGeneral<T> hijo : hijos) {
        hijo.preOrden(); // Entro recursivamente a cada subcarpeta
    }
}
```

### ⚙️ 2. Recorrido Post_Orden (Hijos, luego Raíz)
Esencial para cálculos compuestos desde abajo hacia arriba.
Ejemplo: Calcular el **peso en bytes ocupado de un Directorio**. Necesito medir primero lo que pesa todo adentro, sumar mis propios bytes de metadata y recién ahí decirle a mi Padre el gran total.

```java
public void postOrden() {
    List<ArbolGeneral<T>> hijos = this.getHijos();
    for (ArbolGeneral<T> hijo : hijos) {
        hijo.postOrden();
    }
    System.out.println(this.dato); // Proceso yo al final
}
```

### ⚙️ 3. Por Niveles (En Amplitud)
Se procesan en abanico (Nivel 0 entero, Nivel 1 entero, Nivel 2 entero), requiriendo ineludiblemente de una **Cola** para la memoria temporal.

```java
public void porNiveles(ArbolGeneral<T> raiz) {
    Cola<ArbolGeneral<T>> cola = new Cola<ArbolGeneral<T>>();
    cola.encolar(raiz);
    
    while (!cola.esVacia()) {
        ArbolGeneral<T> nodo = cola.desencolar();
        System.out.println(nodo.getDato());
        
        List<ArbolGeneral<T>> hijos = nodo.getHijos();
        for (ArbolGeneral<T> hijo : hijos) {
            cola.encolar(hijo);
        }
    }
}
```

---

## 📚 Recursos y Referencias

- **Cátedra:** *Algoritmos y Estructuras de Datos* — UNLP. 2026.
- PDFs elaborados por Prof. Alejandra Schiavoni y Prof. Catalina Mostaccio.
