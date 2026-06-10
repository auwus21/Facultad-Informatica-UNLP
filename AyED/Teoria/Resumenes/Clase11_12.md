# 📘 Clase 11 y 12: Recorridos de Grafos en JAVA (DFS y BFS)

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Recorrido en Profundidad (DFS), Recorrido en Anchura (BFS), manejo de grafos inconexos, control de visitados, y plantillas estándar de programación con la API de la Cátedra.

---

> 💡 **La Regla de Oro de los Recorridos**  
> A diferencia de los árboles, los grafos pueden tener **ciclos** (caminos cerrados) o ser **inconexos** (nodos aislados). Para evitar bucles infinitos al recorrerlos, **es obligatorio mantener un registro de qué nodos ya hemos visitado** (usualmente un arreglo o una lista de booleanos).

---
---

# Parte A: Recorrido en Profundidad (DFS - Depth-First Search)

DFS funciona de forma **recursiva** (o utilizando una pila). Explora un camino lo más profundo posible antes de retroceder (backtracking) para explorar otros caminos.

## ⚙️ Funcionamiento Conceptual
1. Empezamos en un vértice inicial, lo marcamos como **visitado**.
2. Para cada vértice adyacente que **no** haya sido visitado, llamamos recursivamente a la función DFS.
3. Si llegamos a un nodo sin adyacentes no visitados, el algoritmo retrocede (vuelve a la llamada anterior en la pila de ejecución).

## 📊 Complejidad de DFS
* **En Listas de Adyacencia:** $\mathcal{O}(V + E)$ — visitamos cada nodo una vez y recorremos todas las aristas.
* **En Matrices de Adyacencia:** $\mathcal{O}(V^2)$ — para cada nodo debemos buscar sus adyacentes recorriendo toda su fila de tamaño $V$.

---

## 💻 Código Plantilla DFS (Usando la API de la Cátedra)

Esta plantilla muestra cómo recorrer **todo el grafo** (incluso si tiene componentes desconectadas) imprimiendo los datos de sus nodos:

```java
import java.util.List;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.Edge;

public class RecorridosGrafos {

    public static <T> void dfs(Graph<T> grafo) {
        if (grafo == null || grafo.isEmpty()) return;

        // Arreglo para registrar visitados. Cada vértice tiene un índice único de 0 a Size-1
        boolean[] visitados = new boolean[grafo.getSize()];

        // Recorremos todos los vértices por si el grafo está desconectado
        for (Vertex<T> v : grafo.getVertices()) {
            int pos = v.getPosition();
            if (!visitados[pos]) {
                dfsHelper(grafo, v, visitados);
            }
        }
    }

    private static <T> void dfsHelper(Graph<T> grafo, Vertex<T> v, boolean[] visitados) {
        // Marcamos el nodo actual como visitado
        visitados[v.getPosition()] = true;
        System.out.println("Visitado: " + v.getData());

        // Obtenemos las aristas que salen de v
        List<Edge<T>> aristas = grafo.getEdges(v);
        for (Edge<T> arista : aristas) {
            Vertex<T> destino = arista.getTarget();
            int posDestino = destino.getPosition();

            // Si el nodo de destino no ha sido visitado, hacemos la llamada recursiva
            if (!visitados[posDestino]) {
                dfsHelper(grafo, destino, visitados);
            }
        }
    }
}
```

---
---

# Parte B: Recorrido en Anchura (BFS - Breadth-First Search)

BFS explora el grafo **por niveles**. Primero visita a todos los vecinos directos del nodo inicial, luego a los vecinos de sus vecinos, y así sucesivamente. Utiliza una **Cola (Queue)** para gestionar el orden de visita.

## ⚙️ Funcionamiento Conceptual
1. Empezamos en un vértice inicial, lo marcamos como **visitado** y lo metemos en una Cola.
2. Mientras la cola no esté vacía:
   * Desencolamos un nodo $u$.
   * Para cada vecino $v$ de $u$ que **no** haya sido visitado: lo marcamos como visitado y lo encolamos.
3. Si queremos recorrer componentes desconectadas, repetimos el proceso para cualquier nodo no visitado.

## 📊 Complejidad de BFS
Al igual que DFS:
* **En Listas de Adyacencia:** $\mathcal{O}(V + E)$
* **En Matrices de Adyacencia:** $\mathcal{O}(V^2)$

---

## 💻 Código Plantilla BFS (Usando la API de la Cátedra)

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.Edge;

public class RecorridosGrafosBFS {

    public static <T> void bfs(Graph<T> grafo) {
        if (grafo == null || grafo.isEmpty()) return;

        boolean[] visitados = new boolean[grafo.getSize()];

        for (Vertex<T> v : grafo.getVertices()) {
            if (!visitados[v.getPosition()]) {
                bfsHelper(grafo, v, visitados);
            }
        }
    }

    private static <T> void bfsHelper(Graph<T> grafo, Vertex<T> inicial, boolean[] visitados) {
        Queue<Vertex<T>> cola = new LinkedList<>();
        
        // Encolamos e inicializamos el primer nodo
        cola.add(inicial);
        visitados[inicial.getPosition()] = true;

        while (!cola.isEmpty()) {
            Vertex<T> actual = cola.poll();
            System.out.println("Visitado: " + actual.getData());

            // Recorremos adyacentes
            for (Edge<T> arista : grafo.getEdges(actual)) {
                Vertex<T> destino = arista.getTarget();
                int posDestino = destino.getPosition();

                if (!visitados[posDestino]) {
                    visitados[posDestino] = true;
                    cola.add(destino);
                }
            }
        }
    }
}
```

---
---

# Parte C: Aplicación Típica de Examen — Búsqueda de Caminos

En los exámenes de la UNLP es sumamente común que te pidan:
> *"Encontrar un camino (o el camino que sume menos peso) desde un origen A hasta un destino B sin pasar por ciertos nodos prohibidos."*

Para resolver esto, la técnica estándar es **DFS con Backtracking**.

## ⚙️ Concepto de Backtracking en Grafos
Cuando buscamos un camino específico, **marcamos como visitado un nodo al entrar en él, pero lo desmarcamos (ponemos en false) al salir de la llamada recursiva**.  
¿Por qué? Porque si un nodo no sirvió para el camino actual, debe quedar disponible para ser explorado en una ruta alternativa distinta.

---

## 💻 Código Examen: Encontrar UN camino entre Origen y Destino

Este método busca y retorna una lista con el camino desde `origen` hasta `destino`. Retorna una lista vacía si no existe.

```java
import java.util.ArrayList;
import java.util.List;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.Edge;

public class BuscadorCaminos {

    public static <T> List<T> buscarCamino(Graph<T> grafo, T datoOrigen, T datoDestino) {
        List<T> camino = new ArrayList<>();
        if (grafo == null || grafo.isEmpty()) return camino;

        Vertex<T> origen = grafo.search(datoOrigen);
        Vertex<T> destino = grafo.search(datoDestino);

        if (origen != null && destino != null) {
            boolean[] visitados = new boolean[grafo.getSize()];
            buscarCaminoHelper(grafo, origen, destino, visitados, camino);
        }
        return camino;
    }

    private static <T> boolean buscarCaminoHelper(Graph<T> grafo, Vertex<T> actual, Vertex<T> destino, 
                                                  boolean[] visitados, List<T> camino) {
        
        // 1. Agregar el nodo actual al camino y marcarlo como visitado
        camino.add(actual.getData());
        visitados[actual.getPosition()] = true;

        // Caso Base: llegamos al destino
        if (actual.equals(destino)) {
            return true;
        }

        // 2. Explorar adyacentes recursivamente
        for (Edge<T> arista : grafo.getEdges(actual)) {
            Vertex<T> siguiente = arista.getTarget();
            if (!visitados[siguiente.getPosition()]) {
                // Si el camino recursivo tiene éxito, cortamos la búsqueda y retornamos true
                if (buscarCaminoHelper(grafo, siguiente, destino, visitados, camino)) {
                    return true; 
                }
            }
        }

        // 3. BACKTRACKING: Si no encontramos camino por acá, sacamos el nodo y lo desmarcamos
        camino.remove(camino.size() - 1);
        visitados[actual.getPosition()] = false;

        return false;
    }
}
```

---
---

## 🧠 Resumen de Diferencias: DFS vs BFS

| Criterio | DFS (Profundidad) | BFS (Anchura) |
|---|---|---|
| **Estructura** | Recursión / Pila (Stack) | Cola (Queue) |
| **Búsqueda de Caminos Mínimos** | ❌ No garantiza el camino más corto en aristas | ✅ **Sí** garantiza el camino con menor cantidad de aristas (no pesado) |
| **Uso de Memoria** | $\mathcal{O}(\text{Profundidad máxima})$ | $\mathcal{O}(\text{Ancho máximo del nivel})$ |
| **Aplicaciones** | Detección de ciclos, orden topológico, caminos simples, laberintos | Conectividad, distancias mínimas en grafos sin peso, redes sociales |

---
*Próxima Clase: [Clase 13: Orden Topológico](Clase13.md)*
