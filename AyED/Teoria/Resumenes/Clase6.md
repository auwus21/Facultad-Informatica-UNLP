# 📘 Colas de Prioridad (Heap)

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Cola de Prioridad, Heap Binaria, Implementación sobre Arreglos, Inserción y Filtrados, Percolate Up/Down

---

# Parte A: La Cola de Prioridad

## 🎯 Definición

Una **cola de prioridad** es una estructura de datos abstracta que garantiza siempre un orden en la salida, independientemente del orden de llegada. Requiere al menos dos operaciones elementales:
- `Insert(x)`: Inserta un elemento en la estructura.
- `DeleteMin()` (o `DeleteMax`): Encuentra, devuelve y **elimina** el elemento más chico (o más grande) de la cola.

**Aplicaciones reales:** Cola de impresión de un OS, calendarización de hilos (Thread Scheduling), resolución de grafos (Dijkstra), etc.

### 📊 ¿Cómo implementarla? (Alternativas)

| Estrategia de Almacenamiento | Rendimiento del `Insert` | Rendimiento del `DeleteMin` |
|---|---|---|
| **Lista Ordenada** | `O(N)` (Lento, debe buscar su lugar exacto desplazando elementos). | `O(1)` (Rapidísimo, simplemente saco la cabeza de la lista). |
| **Lista Desordenada** | `O(1)` (Rapidísimo, lo meto suelto al fondo). | `O(N)` (Lento, debo revisar toda la lista para encontrar al menor y matarlo). |
| **Árbol Binario Búsqueda** | `O(log N)` promedio. | `O(log N)` promedio. |
| **Heap Binary** | **`O(log N)`** en el peor caso. | **`O(log N)`** en el peor caso. *No usa punteros*. |

---

# Parte B: Heap Binaria (La solución ideal)

Es la implementación predilecta porque balancea perfectamente el costo logarítmico para inserciones y extracciones. Debe cumplir simultáneamente con dos sacramentos sagrados: *Propiedad Estructural* y *Propiedad de Orden*.

## ⚙️ 1. Propiedad Estructural (Árbol Completo sin Punteros)
Una Heap es obligatoriamente un **Árbol Binario Completo**. Se llena sistemáticamente de izquierda a derecha sin dejar huecos en el último nivel.

Dado que su crecimiento es predecible y no tiene anomalías asimétricas, **evitamos usar punteros (`leftChild`, `rightChild`)** y podemos meter el árbol directamente *planchado* en un **Arreglo (Array)**.

### 📍 Posicionamientos Clave en el Arreglo:
La raíz inicia en la celda `1` (dejamos el índice `0` inhabilitado o de centinela).
Para cualquier elemento en la posición `i`:
- Su **Hijo Izquierdo** caerá mágicamente en la posición: `2 * i`
- Su **Hijo Derecho** caerá mágicamente en la posición: `(2 * i) + 1`
- Su **Padre** se ubicará mágicamente en la división entera: `⌊i / 2⌋`

## ⚙️ 2. Propiedad de Orden
Es la restricción de soberanía entre el padre y el hijo:
- **MinHeap:** El dato almacenado en cualquier nodo es **menor o igual** al almacenado en sus descendientes. Consecuencia directa: el elemento más chiquito de todos está en la cima celular absoluta (la raíz, posición `1`).
- **MaxHeap:** Viceversa (el mayor está en la raíz).

---
---

# Parte C: Las Operaciones en MinHeap

## 🏗️ Inserción (`insert` con Percolate Up)

**Filosofía:** Inserto al final del arreglo, y luego subo el elemento a los codazos ("burbujeo") hasta restaurar el orden general.
1. Incremento el tamaño de la Heap. Lo inyecto como hijo final (la primera celda disponible del último nivel estructural).
2. Eso **viola la propiedad de orden**, entonces, comparo al recién llegado con su **Padre** (haciendo uso de `⌊i / 2⌋`). Si el hijo es menor al padre, swap (los intercambio).
3. Repito la operación hacia arriba escalando jerárquicamente hasta encontrar a un padre más chico que él. 

*(Costo tiempo O(log N): ya que el algoritmo escala hacia arriba por la altura del árbol).*

## 🏗️ Extracción (`deleteMin` con Percolate Down)

**Filosofía:** Extraigo el elemento rey (la raíz). Muevo la basura del último eslabón forzadamente al trono vacío general, y luego lo hundo hacia el abismo (hacia las hojas) haciendo comparaciones de peso.
1. Me guardo el `dato` de la raíz (que es mi mínimo garantizado) en una variable de retorno auxiliar.
2. Agarro el *último* nodo estructural que tenga en la Heap, lo quito y lo tiro de prepo al asiento vacante de la Raíz. Decremento el tamaño registrado.
3. Eso causa que estemos liderados por un nodo súper "pesado" que va a **violar el orden hacia abajo**.
4. Ahora empieza el proceso de **hundimiento**: Miro a los dos hijos del nodo intruso. Determino cuál de los dos es el **más chico de los hermanos**, y hago un swap.
5. Repito hacia abajo recorriendo la profundidad de árbol hasta hundirlo en una zona de paz y armonía sagrada.

---

## 📚 Recursos y Referencias

- **Cátedra:** *Algoritmos y Estructuras de Datos* — UNLP. 2026.
- PDFs elaborados por Prof. Alejandra Schiavoni y Prof. Catalina Mostaccio.
