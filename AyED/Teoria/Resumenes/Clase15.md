# 📘 Clase 15: Árbol Abarcador Mínimo (Prim y Kruskal)

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Árbol Generador (Abarcador), Árbol Generador Mínimo (MST), Algoritmo de Prim, Algoritmo de Kruskal, y análisis de complejidades.

---

> 💡 **El Problema del Tendido de Cables**  
> Imagina que debes conectar $V$ ciudades con fibra óptica de forma que todas estén comunicadas entre sí (directa o indirectamente) y quieres minimizar el costo total del cable.  
> La solución a este problema es un **Árbol Generador Mínimo (MST - Minimum Spanning Tree)**.

---
---

# Parte A: Concepto de Árbol Generador Mínimo (MST)

Dado un grafo conexo, no dirigido y pesado $G = (V, E)$:
* Un **Árbol Generador (Abarcador)** es un subgrafo de $G$ que contiene **todos** los vértices de $V$, es conexo y **no tiene ciclos** (es un árbol).
* Un **Árbol Generador Mínimo (MST)** es aquel árbol generador cuya **suma de pesos de sus aristas es la mínima posible** entre todos los árboles generadores válidos.

## 📐 Propiedades del MST
1. Contiene exactamente **$V$ vértices**.
2. Contiene exactamente **$V - 1$ aristas**. (Si agregas una arista más, se forma un ciclo; si quitas una, se desconecta).
3. Puede no ser único (si existen múltiples aristas con el mismo peso en el grafo).

---
---

# Parte B: Algoritmo de Prim (Enfoque por Vértices)

El algoritmo de Prim construye el MST de forma **ávida (greedy)** haciendo crecer el árbol nodo a nodo a partir de un vértice inicial cualquiera.

## ⚙️ Idea del Algoritmo
1. Seleccionamos un vértice inicial arbitrario para empezar a construir el árbol.
2. Mantenemos dos conjuntos de nodos: **nodos en el árbol** ($MST$) y **nodos fuera del árbol**.
3. En cada paso:
   * Buscamos la arista con el **menor peso** que conecte un nodo del $MST$ con un nodo fuera de él.
   * Agregamos esa arista y el nuevo nodo al $MST$.
4. Repetimos hasta que todos los vértices del grafo estén en el $MST$.

```text
Grafo con vértices {A, B, C, D}. Empezamos en A.
1. MST = {A}. Aristas salientes de A: (A,B):4, (A,C):1.
2. Elegimos la menor: (A,C):1. MST = {A, C}.
3. Aristas salientes de {A, C} a nodos externos: (A,B):4, (C,B):2, (C,D):6.
4. Elegimos la menor: (C,B):2. MST = {A, C, B}.
5. Aristas salientes a {D}: (B,D):5, (C,D):6.
6. Elegimos la menor: (B,D):5. MST = {A, C, B, D}. ¡Finalizado!
```

## 📊 Complejidad de Prim
* **Versión Clásica:** **$\mathcal{O}(V^2)$** (óptimo para grafos muy densos).
* **Con Cola de Prioridad (Min-Heap):** **$\mathcal{O}(E \log V)$** (óptimo para grafos dispersos).

---
---

# Parte C: Algoritmo de Kruskal (Enfoque por Aristas)

Kruskal toma una perspectiva distinta: en lugar de hacer crecer una única componente conexa, procesa las aristas del grafo directamente en orden de costo.

## ⚙️ Idea del Algoritmo
1. Creamos un bosque (un conjunto de árboles aislados, donde cada vértice empieza siendo un árbol independiente).
2. Obtenemos **todas las aristas del grafo** y las **ordenamos de menor a mayor** según su peso.
3. Iteramos por la lista de aristas ordenadas:
   * Para la arista actual $(u, v)$:
     * Si $u$ y $v$ pertenecen a **árboles distintos** (no forman un ciclo al conectarse):
       * Agregamos la arista al MST.
       * **Unimos** los dos árboles en uno solo.
     * Si pertenecen al mismo árbol: descartamos la arista (porque formaría un ciclo).
4. El proceso termina cuando hemos agregado exactamente $V - 1$ aristas.

> 🛠️ **Nota de implementación:** Para saber si dos nodos están en el mismo árbol de forma eficiente, se utiliza la estructura de datos **Union-Find (o Conjuntos Disjuntos - DSU)**.

---

## 💻 Código Conceptual de Kruskal en Java

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.Edge;

public class MSTKruskal {

    // Clase auxiliar para representar aristas completas (Origen -> Destino con Peso)
    private static class AristaCompleta<T> {
        Vertex<T> origen;
        Vertex<T> destino;
        int peso;

        public AristaCompleta(Vertex<T> origen, Vertex<T> destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }
    }

    public static <T> List<Edge<T>> kruskal(Graph<T> grafo) {
        List<AristaCompleta<T>> todasLasAristas = new ArrayList<>();
        int V = grafo.getSize();

        // 1. Recolectar todas las aristas del grafo
        for (Vertex<T> u : grafo.getVertices()) {
            for (Edge<T> e : grafo.getEdges(u)) {
                todasLasAristas.add(new AristaCompleta<>(u, e.getTarget(), e.getWeight()));
            }
        }

        // 2. Ordenar las aristas por peso
        Collections.sort(todasLasAristas, Comparator.comparingInt(a -> a.peso));

        // 3. Inicializar Union-Find
        UnionFind uf = new UnionFind(V);
        List<Edge<T>> aristasMST = new ArrayList<>();

        int aristasAgregadas = 0;
        for (AristaCompleta<T> arista : todasLasAristas) {
            int rootU = uf.find(arista.origen.getPosition());
            int rootV = uf.find(arista.destino.getPosition());

            // Si pertenecen a componentes distintas, no hay ciclo
            if (rootU != rootV) {
                uf.union(rootU, rootV);
                // Agregamos la arista al MST
                aristasMST.add(new tp5.ejercicio1.listaAdy.AdjListEdge<>(arista.destino, arista.peso));
                aristasAgregadas++;
                
                if (aristasAgregadas == V - 1) break; // MST completo
            }
        }
        return aristasMST;
    }

    // Estructura auxiliar para controlar ciclos eficientemente
    private static class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]); // Compresión de caminos
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }
}
```

## 📊 Complejidad de Kruskal
* **Temporal:** **$\mathcal{O}(E \log E)$** o **$\mathcal{O}(E \log V)$** (debido al ordenamiento de las aristas). Las operaciones de Union-Find son casi constantes $\mathcal{O}(\alpha(V))$ por paso.
* **Espacial:** **$\mathcal{O}(V + E)$** para almacenar la lista de aristas y el Union-Find.

---
---

# 📊 Tabla Comparativa: Prim vs. Kruskal

| Criterio | Algoritmo de Prim | Algoritmo de Kruskal |
|---|---|---|
| **Enfoque** | Crece desde un nodo central (por vértices) | Une componentes aisladas (por aristas) |
| **Operación Clave** | Selección del vecino más barato del árbol | Ordenamiento de todas las aristas |
| **Estructuras Auxiliares** | Arreglo de distancias / Min-Heap | Estructura Union-Find / DSU |
| **Grafos Densos ($E \approx V^2$)** | ✅ **Más eficiente** ($\mathcal{O}(V^2)$ versión clásica) | Más lento ($\mathcal{O}(E \log E)$ requiere ordenar muchas aristas) |
| **Grafos Dispersos ($E \ll V^2$)**| Eficiente ($\mathcal{O}(E \log V)$ con Heap) | ✅ **Muy eficiente** ($\mathcal{O}(E \log E)$ al haber pocas aristas) |

---
## 🧠 Tips clave de MST para el Parcial
* Si el grafo tiene **pesos todos iguales**, cualquier árbol generador es un MST.
* El MST no contiene ciclos. Si en un ejercicio te proponen "conectar todos los puntos al menor costo posible", te están pidiendo el peso total del MST.
* **Kruskal** es excelente si te dan las aristas ya ordenadas por peso (el algoritmo pasa a ser casi lineal).

---
*Fin del Módulo de Teoría de AyED. ¡Éxitos en tu parcial del Sábado 13!*
