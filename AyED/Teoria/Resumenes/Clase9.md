# 📘 Clase 9: Análisis de Algoritmos y Tiempos de Ejecución

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Análisis de algoritmos, eficiencia temporal y espacial, notación Big-Oh ($\mathcal{O}$), algoritmos iterativos, algoritmos recursivos, ecuaciones de recurrencia, y Teorema Maestro.

---

> 💡 **¿Por qué analizamos algoritmos?**  
> Para poder comparar diferentes soluciones a un mismo problema sin depender del hardware (procesador, memoria), del compilador, o del lenguaje de programación. Evaluamos cómo escala el tiempo de ejecución (o el espacio de memoria) a medida que el tamaño de la entrada ($N$) crece hacia el infinito.

---
---

# Parte A: Conceptos Básicos y Notación Asintótica

## 🎯 Eficiencia de un Algoritmo
La eficiencia se mide en dos dimensiones:
1. **Eficiencia Temporal:** Tiempo que tarda un algoritmo en ejecutarse (representado por el número de operaciones básicas ejecutadas).
2. **Eficiencia Espacial:** Cantidad de memoria auxiliar que requiere el algoritmo para resolver el problema.

### Caso Peor, Caso Mejor y Caso Promedio
Dado que el tiempo puede variar según los datos específicos de entrada:
* **Caso Peor ($T_{peor}(N)$):** El número máximo de operaciones que realiza el algoritmo para cualquier entrada de tamaño $N$. **Es la métrica más importante** porque garantiza un límite superior del tiempo.
* **Caso Mejor ($T_{mejor}(N)$):** El número mínimo de operaciones que realiza el algoritmo para cualquier entrada de tamaño $N$.
* **Caso Promedio ($T_{promedio}(N)$):** El comportamiento esperado sobre todas las entradas posibles. Suele requerir análisis probabilístico complejo.

---

## ⚙️ La Notación Asintótica Big-Oh ($\mathcal{O}$)

La notación $\mathcal{O}$ describe el comportamiento de una función a medida que $N$ tiende a infinito ($N \to \infty$). Es una cota superior asintótica.

### 📜 Definición Formal de $\mathcal{O}(f(N))$
> Decimos que $T(N) = \mathcal{O}(f(N))$ si existen constantes positivas $c$ y $n_0$ tales que:
> $$T(N) \le c \cdot f(N) \quad \text{para todo } N \ge n_0$$

* **En criollo:** A partir de un tamaño de entrada $n_0$, la función $T(N)$ nunca supera a $f(N)$ multiplicada por una constante $c$. Nos permite "ignorar" las constantes y los términos de menor orden.

### 📊 Jerarquía de Complejidades Comunes (De mejor a peor)
1. **$\mathcal{O}(1)$**: Constante (ej. acceder a un elemento en un arreglo por índice).
2. **$\mathcal{O}(\log N)$**: Logarítmica (ej. búsqueda binaria).
3. **$\mathcal{O}(N)$**: Lineal (ej. búsqueda secuencial, recorrer un arreglo).
4. **$\mathcal{O}(N \log N)$**: Casi lineal (ej. MergeSort, QuickSort promedio, HeapSort).
5. **$\mathcal{O}(N^2)$**: Cuadrática (ej. InsertionSort, BubbleSort, bucles anificados simples).
6. **$\mathcal{O}(N^3)$**: Cúbica (ej. multiplicación de matrices clásica).
7. **$\mathcal{O}(2^N)$**: Exponencial (ej. Torres de Hanói, subconjuntos de un conjunto).
8. **$\mathcal{O}(N!)$**: Factorial (ej. problema del viajante por fuerza bruta).

```text
  Tiempo
    ▲                                           / O(2^N)
    │                                          /
    │                                         /
    │                                       /   / O(N^2)
    │                                      /   /
    │                                     /   /   / O(N log N)
    │                                    /   /   /
    │                                   /   /   /   / O(N)
    │                                  /   /   /   /
    │                                 /   /   /   /   / O(log N)
    │                                /   /   /   /   /
    │───────────────────────────────/───/───/───/───/─── O(1)
    └──────────────────────────────────────────────────────────► Tamaño de Entrada (N)
```

---
---

# Parte B: Análisis de Algoritmos Iterativos

Para calcular el tiempo de ejecución de algoritmos con bucles, usamos reglas algebraicas simples:

## 📐 Reglas Prácticas
1. **Secuencia de Sentencias:** Se suma el costo de cada una.
   $$T_1(N) + T_2(N) = \max(\mathcal{O}(f_1(N)), \mathcal{O}(f_2(N)))$$
2. **Condicionales (If-Else):** Se toma el peor caso entre las dos ramas.
   $$\mathcal{O}(\max(\text{Rama If}, \text{Rama Else}))$$
3. **Bucles Simples (For/While):** Se multiplica el número de iteraciones por el costo de las instrucciones dentro del bucle.
4. **Bucles Anificados:** Se analiza de adentro hacia afuera, usando sumatorias si los límites del bucle interno dependen del externo.

---

## 📦 Ejemplo 1: Bucle anidado independiente
```java
int suma = 0; // O(1)
for (int i = 0; i < N; i++) { // N iteraciones
    for (int j = 0; j < N; j++) { // N iteraciones
        suma += i * j; // O(1)
    }
}
```
* **Análisis:** El bucle interno corre $N$ veces y hace trabajo constante $\mathcal{O}(1)$. El bucle externo corre $N$ veces.  
  $$T(N) = \sum_{i=0}^{N-1} \sum_{j=0}^{N-1} \mathcal{O}(1) = N \cdot N \cdot \mathcal{O}(1) = \mathcal{O}(N^2)$$

---

## 📦 Ejemplo 2: Bucle anidado dependiente
```java
int suma = 0;
for (int i = 0; i < N; i++) {
    for (int j = 0; j < i; j++) { // j va de 0 a i-1
        suma += j;
    }
}
```
* **Análisis:** El bucle interno depende del valor de `i`.
  * Cuando `i = 0`, el bucle interno hace `0` iteraciones.
  * Cuando `i = 1`, hace `1` iteración.
  * Cuando `i = k`, hace `k` iteraciones.
* Matemáticamente, esto es la suma de los primeros $N-1$ números enteros:
  $$T(N) = \sum_{i=0}^{N-1} i = \frac{(N-1) \cdot N}{2} = \frac{N^2 - N}{2} = \mathcal{O}(N^2)$$

---

## 📦 Ejemplo 3: Optimización práctica (El caso del Banco)
En el ejercicio de la [Práctica 5 (Banco)](file:///c:/Users/aguso/Projects/Facultad/Facultad-Informatica-UNLP/AyED/Practicas/Practica-5-Analisis-De-Algoritmos-Tiempos-De-Ejecucion/Ejercicio_1/TiempoDeEjecucion/src/Banco.java), tenemos:
* $N$ cuentas y $M$ consultas de tipo "sumar un valor en el rango `[desde, hasta]`".

### Versión Naive (No optimizada):
```java
for (int i = 0; i < consultas.length; i++) { // M consultas
    Consulta c = consultas[i];
    for (int j = c.getDesde(); j <= c.getHasta(); j++) { // En el peor de los casos, recorre N cuentas
        cuentas[j] += c.getValor(); // O(1)
    }
}
```
* **Complejidad Peor Caso:** $\mathcal{O}(M \cdot N)$. Si $M = N$, esto es $\mathcal{O}(N^2)$ (cuadrático).

### Versión Optimizada (Arreglo de Diferencias):
```java
// 1. Marcar extremos en O(1) por consulta
for (int i = 0; i < consultas.length; i++) { // M consultas
    Consulta c = consultas[i];
    aux[c.getDesde()] += c.getValor(); // O(1)
    aux[c.getHasta() + 1] -= c.getValor(); // O(1)
}
// 2. Suma acumulada (Prefix Sum) en O(N)
for (int i = 0; i < cuentas.length; i++) { // N cuentas
    if (i > 0) aux[i] += aux[i-1];
    cuentas[i] += aux[i];
}
```
* **Complejidad Peor Caso:** $\mathcal{O}(M + N)$. Si $M = N$, esto es $\mathcal{O}(N)$ (lineal). ¡Una optimización enorme para $N$ grande!

---
---

# Parte C: Análisis de Algoritmos Recursivos

Los algoritmos recursivos no se pueden analizar con sumatorias simples porque la función se llama a sí misma. Para analizarlos, definimos una **Ecuación de Recurrencia**.

Una relación de recurrencia define $T(N)$ en términos de valores más pequeños de $N$ y un caso base:
$$T(N) = \begin{cases} \mathcal{O}(1) & \text{si } N = 1 \\ a \cdot T(N/b) + f(N) & \text{si } N > 1 \end{cases}$$
Donde:
* $a$: cantidad de subproblemas recursivos creados.
* $N/b$: tamaño de cada subproblema ($b$ es el factor de división).
* $f(N)$ o $D(N)$: costo de dividir el problema y combinar las soluciones de los subproblemas de forma no recursiva.

---

## ⚙️ El Teorema Maestro

El Teorema Maestro es una "receta" automática para resolver recurrencias de la forma:
$$T(N) = a \cdot T(N/b) + \mathcal{O}(N^k)$$
Donde $a \ge 1$, $b > 1$, y $k \ge 0$.

Se compara el costo de las llamadas recursivas ($a$) con el factor de división elevado a la complejidad del paso de combinación ($b^k$):

| Comparación | Complejidad de $T(N)$ | Explicación sencilla |
|---|---|---|
| **$a > b^k$** | **$\mathcal{O}(N^{\log_b a})$** | Las llamadas recursivas dominan el tiempo total. |
| **$a = b^k$** | **$\mathcal{O}(N^k \log N)$** | El trabajo recursivo y el trabajo de combinación están balanceados. |
| **$a < b^k$** | **$\mathcal{O}(N^k)$** | El trabajo de combinación/división domina el tiempo total. |

---

## 📦 Ejemplos Prácticos del Teorema Maestro

### Ejemplo A: Búsqueda Binaria (Dicotómica)
En cada paso dividimos el arreglo a la mitad ($b = 2$), hacemos **una sola** llamada recursiva ($a = 1$), y hacemos una comparación constante $\mathcal{O}(1) = \mathcal{O}(N^0)$ ($k = 0$).
* **Ecuación:** $T(N) = 1 \cdot T(N/2) + \mathcal{O}(1)$
* **Identificamos:** $a = 1$, $b = 2$, $k = 0$.
* **Comparamos:** $b^k = 2^0 = 1$. Como $a = b^k$ ($1 = 1$), aplica el **Caso 2**.
* **Resultado:** $T(N) = \mathcal{O}(N^0 \log N) = \mathcal{O}(\log N)$.

### Ejemplo B: Ordenación por Mezcla (MergeSort)
Dividimos el arreglo a la mitad ($b = 2$), hacemos **dos** llamadas recursivas ($a = 2$), y combinamos las dos mitades en tiempo lineal $\mathcal{O}(N)$ ($k = 1$).
* **Ecuación:** $T(N) = 2 \cdot T(N/2) + \mathcal{O}(N)$
* **Identificamos:** $a = 2$, $b = 2$, $k = 1$.
* **Comparamos:** $b^k = 2^1 = 2$. Como $a = b^k$ ($2 = 2$), aplica el **Caso 2**.
* **Resultado:** $T(N) = \mathcal{O}(N^1 \log N) = \mathcal{O}(N \log N)$.

### Ejemplo C: Multiplicación de Matrices de Strassen
Divide las matrices en submatrices de tamaño $N/2$ ($b = 2$), realiza $7$ multiplicaciones recursivas ($a = 7$), y suma las submatrices en tiempo cuadrático $\mathcal{O}(N^2)$ ($k = 2$).
* **Ecuación:** $T(N) = 7 \cdot T(N/2) + \mathcal{O}(N^2)$
* **Identificamos:** $a = 7$, $b = 2$, $k = 2$.
* **Comparamos:** $b^k = 2^2 = 4$. Como $a > b^k$ ($7 > 4$), aplica el **Caso 1**.
* **Resultado:** $T(N) = \mathcal{O}(N^{\log_2 7}) \approx \mathcal{O}(N^{2.81})$ (más rápido que el método tradicional $\mathcal{O}(N^3)$).

---

## ⚠️ ¿Cuándo NO se puede aplicar el Teorema Maestro?
1. **Si $a$ no es constante** (ej. $T(N) = N \cdot T(N/2) + N$).
2. **Si el decrecimiento no es una división** (ej. fibonacci o factorial: $T(N) = T(N-1) + \mathcal{O}(1)$). En este caso se usa expansión o sustitución directa.
3. **Si $f(N)$ no es polinómica** (ej. contiene términos trigonométricos o logaritmos complejos que no encajen en $N^k$).

---
---

## 🧠 Tips clave para rendir el Parcial

* **No te asustes con las sumatorias:** Recuerda las fórmulas básicas:
  $$\sum_{i=1}^{N} c = c \cdot N \quad \text{y} \quad \sum_{i=1}^{N} i = \frac{N(N+1)}{2} \approx \mathcal{O}(N^2)$$
* **Si el bucle divide la variable:** Si en un bucle la variable hace `i = i / 2` o `i = i * 2`, la complejidad de ese bucle es logarítmica: $\mathcal{O}(\log N)$.
* **Atención a la recursión lineal:** Para ecuaciones como $T(N) = T(N-1) + \mathcal{O}(1)$, el árbol de recursión tiene profundidad $N$. Su costo total será $\mathcal{O}(N)$. Si es $T(N) = T(N-1) + \mathcal{O}(N)$, el costo es $\mathcal{O}(N^2)$.

---
*Próxima Clase: [Clase 10: Grafos - Definición y Representaciones](Clase10.md)*
