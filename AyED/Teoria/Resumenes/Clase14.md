# 📘 Clase 14: Caminos Mínimos (Dijkstra y Floyd)

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Problema de caminos mínimos, Algoritmo de Dijkstra (origen único, pesos no negativos), Algoritmo de Floyd-Warshall (todos los pares), relajación de aristas, y análisis de complejidades.

---

> 💡 **¿Qué busca el camino mínimo?**  
> Encontrar la ruta entre dos vértices tal que la **suma de los pesos de las aristas** que la componen sea la **mínima posible**.  
> * **Atención:** Si el grafo no tiene pesos (o todos los pesos son 1), se debe usar **BFS**. Si el grafo es pesado, debemos usar **Dijkstra** o **Floyd**.

---
---

# Parte A: Algoritmo de Dijkstra (Caminos Mínimos desde Origen Único)

Resuelve el problema de encontrar los caminos más cortos desde un único vértice origen $s$ hacia todos los demás vértices del grafo.

## ⚠️ Requisito Fundamental
* **Los pesos de las aristas deben ser no negativos ($w(e) \ge 0$).**
* **¿Por qué?** Dijkstra es un algoritmo **ávido (greedy)**. Una vez que marca un nodo como visitado (cerrado), asume que ya encontró su distancia mínima definitiva. Si existieran pesos negativos, dar una "vuelta más larga" podría terminar reduciendo el costo, invalidando la decisión ávida ya tomada.

---

## ⚙️ Idea del Algoritmo
1. Inicializamos un arreglo `distancia` de tamaño $V$ con $\infty$ (infinito), excepto `distancia[origen] = 0`.
2. Inicializamos un arreglo `padre` para reconstruir los caminos, y un conjunto de `visitados` vacío.
3. Mientras queden nodos sin visitar:
   * Seleccionamos el vértice $u$ **no visitado** que tenga la **menor distancia** en el arreglo `distancia`.
   * Marcamos $u$ como **visitado**.
   * **Relajación de Aristas:** Para cada vecino $v$ de $u$:
     * Si `distancia[u] + peso(u, v) < distancia[v]`, actualizamos:
       $$\text{distancia}[v] = \text{distancia}[u] + \text{peso}(u, v)$$
       $$\text{padre}[v] = u$$

---

## 💻 Código en Java (Versión Clásica $\mathcal{O}(V^2)$)

Esta versión es óptima para grafos densos y no requiere estructuras auxiliares complejas de prioridad.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.Edge;

public class CaminoMinimoDijkstra {

    public static <T> List<T> dijkstra(Graph<T> grafo, T datoOrigen, T datoDestino) {
        int n = grafo.getSize();
        int[] distancias = new int[n];
        int[] padres = new int[n];
        boolean[] visitados = new boolean[n];

        // 1. Inicialización
        for (int i = 0; i < n; i++) {
            distancias[i] = Integer.MAX_VALUE;
            padres[i] = -1;
        }

        Vertex<T> origen = grafo.search(datoOrigen);
        Vertex<T> destino = grafo.search(datoDestino);
        if (origen == null || destino == null) return new ArrayList<>();

        distancias[origen.getPosition()] = 0;

        // 2. Bucle Principal
        for (int count = 0; count < n - 1; count++) {
            // Buscamos el vértice no visitado con distancia mínima
            int u = minDistancia(distancias, visitados);
            if (u == -1) break; // Nodos restantes inalcanzables

            visitados[u] = true;
            Vertex<T> verticeU = grafo.getVertex(u);

            // Relajamos las aristas del vértice seleccionado
            for (Edge<T> arista : grafo.getEdges(verticeU)) {
                Vertex<T> v = arista.getTarget();
                int posV = v.getPosition();
                int peso = arista.getWeight();

                if (!visitados[posV] && distancias[u] != Integer.MAX_VALUE 
                        && distancias[u] + peso < distancias[posV]) {
                    distancias[posV] = distancias[u] + peso;
                    padres[posV] = u;
                }
            }
        }

        return reconstruirCamino(grafo, destino.getPosition(), padres);
    }

    private static int minDistancia(int[] distancias, boolean[] visitados) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < distancias.length; v++) {
            if (!visitados[v] && distancias[v] <= min) {
                min = distancias[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static <T> List<T> reconstruirCamino(Graph<T> grafo, int destino, int[] padres) {
        List<T> camino = new ArrayList<>();
        int actual = destino;
        while (actual != -1) {
            camino.add(grafo.getVertex(actual).getData());
            actual = padres[actual];
        }
        Collections.reverse(camino);
        return camino;
    }
}
```

## 📊 Complejidad de Dijkstra
* **Clásica (Búsqueda Lineal):** **$\mathcal{O}(V^2)$**. Excelente para grafos densos ($E \approx V^2$).
* **Con Cola de Prioridad (Min-Heap):** **$\mathcal{O}((V + E) \log V)$**. Excelente para grafos dispersos ($E \ll V^2$), que son la mayoría.

---
---

# Parte B: Algoritmo de Floyd-Warshall (Caminos Mínimos entre Todos los Pares)

Encuentra las distancias más cortas entre **todos** los pares de vértices en un grafo dirigido y pesado. Puede trabajar con pesos negativos (pero sin ciclos de costo negativo).

## ⚙️ Concepto de Programación Dinámica
El algoritmo evalúa sistemáticamente si pasar por un vértice intermedio $k$ ofrece una ruta más corta entre un nodo $i$ y un nodo $j$ que la que ya conocemos.

* **Fórmula de Recurrencia:**
  $$D^k[i][j] = \min\left(D^{k-1}[i][j], \; D^{k-1}[i][k] + D^{k-1}[k][j]\right)$$

---

## ⚙️ Algoritmo Paso a Paso
1. Creamos una matriz de distancias $D$ de tamaño $V \times V$.
2. **Inicialización ($D^0$):**
   * $D[i][i] = 0$
   * $D[i][j] = \text{peso}(i, j)$ si existe arista.
   * $D[i][j] = \infty$ si no existe arista.
3. **Triple bucle anidado:**
   * Probamos cada vértice $k$ de $0$ a $V-1$ como posible **nodo intermedio**.
   * Para cada par de origen $i$ y destino $j$, actualizamos la matriz:
```java
for (int k = 0; k < V; k++) {
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            if (D[i][k] + D[k][j] < D[i][j]) {
                D[i][j] = D[i][k] + D[k][j];
            }
        }
    }
}
```

## 📊 Complejidad de Floyd
* **Temporal:** **$\mathcal{O}(V^3)$** (debido al triple bucle anidado independiente de las aristas).
* **Espacial:** **$\mathcal{O}(V^2)$** (para almacenar la matriz de distancias).

---
---

# 📊 Tabla Comparativa: Dijkstra vs. Floyd-Warshall

| Característica | Dijkstra | Floyd-Warshall |
|---|---|---|
| **Propósito** | Origen Único $\to$ Todos los destinos | Todos los orígenes $\to$ Todos los destinos |
| **Pesos Negativos** | ❌ No los soporta | ✅ Sí (si no hay ciclos negativos) |
| **Enfoque** | Ávido (Greedy) | Programación Dinámica |
| **Complejidad Temporal** | $\mathcal{O}(V^2)$ o $\mathcal{O}((V+E)\log V)$ | $\mathcal{O}(V^3)$ |
| **Complejidad Espacial** | $\mathcal{O}(V)$ | $\mathcal{O}(V^2)$ |
| **¿Cuándo usarlo?** | Si solo nos interesa salir desde un nodo origen específico. | Si necesitamos consultar distancias entre muchos pares distintos repetidamente. |

---
*Próxima Clase: [Clase 15: Árbol Abarcador Mínimo (Prim y Kruskal)](Clase15.md)*
