# 📘 Clase 9: Análisis de Algoritmos y Tiempos de Ejecución

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Temas:** Análisis de algoritmos, cálculo formal del $T(n)$ en algoritmos iterativos (método de las constantes y sumatorias), cálculo del $T(n)$ en algoritmos recursivos (método de expansión/sustitución y Teorema Maestro), y demostración de cotas usando definición formal de Big-Oh ($\mathcal{O}$).

---

> 💡 **Enfoque Teórico (A Priori):**  
> Consiste en calcular matemáticamente la función de tiempo $T(n)$ en el peor de los casos, donde $n$ representa el tamaño de la entrada de datos. Nos permite medir la eficiencia del algoritmo independientemente del hardware, lenguaje de programación o compilador.

---
---

# Parte A: Cálculo Formal de $T(n)$ en Algoritmos Iterativos

La cátedra utiliza un método riguroso basado en **constantes de tiempo ($cte_1, cte_2, \dots$)** para las instrucciones de tiempo constante, y **sumatorias ($\sum$)** para modelar la ejecución de los bucles.

## 📐 Reglas de Traducción a Sumatorias
1. **Instrucciones Secuenciales:** Se les asigna una constante de costo.
2. **Bucle `for` (de $1$ a $n$ con pasos de a $1$):** Se traduce como una sumatoria:
   $$\sum_{i=1}^{n} cte_{bucle}$$
3. **Bucles Anidados:** Se representan como sumatorias anidadas.

---

## 📦 Ejemplo 1: Bucle Simple (Lineal)
```java
int sum = 0;
for (int i = 1; i <= n; i++) {
    sum += a[i];
}
```
### Planteo de la Cátedra:
* $cte_1$: Costo de la inicialización de variables.
* $cte_2$: Costo del control de bucle (comparación, incremento) y la suma interna.

$$T(n) = cte_1 + \sum_{i=1}^{n} cte_2$$
$$T(n) = cte_1 + n \cdot cte_2 \implies \mathcal{O}(n)$$

---

## 📦 Ejemplo 2: Dos Bucles Anidados Independientes (Cuadrático)
```java
int sum = 0;
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
        sum += a[i][j];
    }
}
```
### Planteo de la Cátedra:
* $cte_1$: Declaración e inicialización.
* $cte_2$: Cuerpo del bucle interno.

$$T(n) = cte_1 + \sum_{i=1}^{n} \sum_{j=1}^{n} cte_2$$
$$T(n) = cte_1 + \sum_{i=1}^{n} (n \cdot cte_2)$$
$$T(n) = cte_1 + n \cdot n \cdot cte_2 = cte_1 + n^2 \cdot cte_2 \implies \mathcal{O}(n^2)$$

---

## 📦 Ejemplo 3: Bucle Anidado Dependiente (Cuadrático)
```java
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= i; j++) {
        s[i] += a[j];
    }
}
```
### Planteo de la Cátedra:
* $cte_1$: Asignaciones iniciales.
* $cte_2$: Costo del bucle externo.
* $cte_3$: Costo del bucle interno (depende de `i`).

$$T(n) = cte_1 + \sum_{i=1}^{n} cte_2 + \sum_{i=1}^{n} \sum_{j=1}^{i} cte_3$$
$$T(n) = cte_1 + n \cdot cte_2 + \sum_{i=1}^{n} (i \cdot cte_3)$$
$$T(n) = cte_1 + n \cdot cte_2 + cte_3 \cdot \sum_{i=1}^{n} i$$

Aplicando la propiedad de sumatoria de enteros consecutivos $\sum_{i=1}^{n} i = \frac{n(n+1)}{2}$:
$$T(n) = cte_1 + n \cdot cte_2 + cte_3 \cdot \frac{n(n+1)}{2}$$
$$T(n) = cte_1 + n \cdot cte_2 + \frac{cte_3}{2} n^2 + \frac{cte_3}{2} n \implies \mathcal{O}(n^2)$$

---

## 📦 Ejemplo 4: Bucle con incremento no unitario (Lineal)
```java
int i = 1;
while (i <= n) {
    x = x + 1;
    i = i + 2; // Incremento de a 2
}
```
### Planteo de la Cátedra:
El bucle no se ejecuta $n$ veces, sino exactamente $\frac{n+1}{2}$ veces.
$$T(n) = cte_1 + \sum_{i=1}^{\frac{n+1}{2}} cte_2$$
$$T(n) = cte_1 + \frac{cte_2}{2} (n + 1) \implies \mathcal{O}(n)$$

---

## 📦 Ejemplo 5: Bucle Multiplicativo (Logarítmico)
```java
int x = 1;
while (x < n) {
    x = 2 * x; // Duplica x
}
```
### Planteo de la Cátedra:
El valor de $x$ en la iteración $k$ es $2^k$. El bucle termina cuando $2^k \ge n$, es decir, $k = \log_2(n)$ iteraciones.
$$T(n) = cte_1 + cte_2 \cdot \log_2(n) \implies \mathcal{O}(\log n)$$
*(Nota: Si $n$ no es potencia de 2, realiza $\lfloor\log_2(n)\rfloor + 1$ iteraciones).*

---
---

# Parte B: Demostración Formal de la cota Big-Oh ($\mathcal{O}$)

En los exámenes te pedirán demostrar que una función $T(n)$ pertenece a un orden de complejidad $\mathcal{O}(f(n))$ encontrando las constantes $c > 0$ y $n_0$ tales que:
$$T(n) \le c \cdot f(n) \quad \text{para todo } n \ge n_0$$

## 📐 Método de Acotación Término a Término (Recomendado por la Cátedra)
Para demostrar $T(n) \le \mathcal{O}(f(n))$:
1. Comparamos cada término individual de $T(n)$ contra la función objetivo $f(n)$.
2. Obtenemos constantes individuales $c_i$ y sus respectivos $n_{0_i}$.
3. La constante final $c$ es la suma de las constantes parciales: $c = \sum c_i$.
4. El $n_0$ final es el valor más restrictivo (el máximo de los $n_{0_i}$).

### 📦 Ejemplo de Examen: Demostrar que $T(n) = 5n + 3n^2 + 2n^2 \log_2(n) \le \mathcal{O}(n^2 \log_2(n))$

#### 1. Análisis del primer término: $5n \le c_1 n^2 \log_2(n)$
* Sabemos que $n \le n^2 \log_2(n)$ para todo $n \ge 2$ (ya que en $n=1$, $\log_2(1)=0$ y anula el término).
* Multiplicamos por 5 en ambos miembros: $5n \le 5 n^2 \log_2(n)$.
* Obtenemos: **$c_1 = 5$** válido para **$n_0 \ge 2$**.

#### 2. Análisis del segundo término: $3n^2 \le c_2 n^2 \log_2(n)$
* Sabemos que $n^2 \le n^2 \log_2(n)$ para todo $n \ge 2$ (en $n=1$ vuelve a dar $0$).
* Multiplicamos por 3: $3n^2 \le 3 n^2 \log_2(n)$.
* Obtenemos: **$c_2 = 3$** válido para **$n_0 \ge 2$**.

#### 3. Análisis del tercer término: $2n^2 \log_2(n) \le c_3 n^2 \log_2(n)$
* Es la misma función, por lo que con **$c_3 = 2$** alcanza, y vale para **$n_0 \ge 1$**.

#### 4. Obtención de $c$ y $n_0$ finales:
Sumamos las desigualdades:
$$T(n) = 5n + 3n^2 + 2n^2 \log_2(n) \le (5 + 3 + 2) \cdot n^2 \log_2(n)$$
$$T(n) \le 10 \cdot n^2 \log_2(n)$$
* **Resultado:** $c = 10$, con $n_0 = 2$ (el máximo entre $2$, $2$ y $1$).

---
---

# Parte C: Cálculo de $T(n)$ en Algoritmos Recursivos

Para algoritmos que se llaman a sí mismos, planteamos una **Ecuación de Recurrencia**.

## ⚙️ Resolución por Método de Expansión (Paso a Paso)
Consiste en expandir la ecuación de recurrencia iterativamente hasta encontrar un patrón general y aplicar el caso base.

### 📦 Ejemplo de Examen: Resolver $T(n) = 27 T(n/3) + n^3$, con caso base $T(1) = 3$

* **Ecuación inicial:** $T(n) = 27 T(n/3) + n^3$
* **Paso 1:**
  $$T(n) = 27 T\left(\frac{n}{3}\right) + n^3$$
* **Paso 2:** Reemplazamos $T(n/3)$ en la ecuación:
  $$T(n) = 27 \left[ 27 T\left(\frac{n}{3^2}\right) + \left(\frac{n}{3}\right)^3 \right] + n^3$$
  $$T(n) = 27^2 T\left(\frac{n}{3^2}\right) + 27 \frac{n^3}{27} + n^3$$
  $$T(n) = 27^2 T\left(\frac{n}{3^2}\right) + n^3 + n^3 = 27^2 T\left(\frac{n}{3^2}\right) + 2n^3$$
* **Paso 3:** Reemplazamos $T(n/3^2)$ en la ecuación:
  $$T(n) = 27^2 \left[ 27 T\left(\frac{n}{3^3}\right) + \left(\frac{n}{3^2}\right)^3 \right] + 2n^3$$
  $$T(n) = 27^3 T\left(\frac{n}{3^3}\right) + 27^2 \frac{n^3}{27^2} + 2n^3$$
  $$T(n) = 27^3 T\left(\frac{n}{3^3}\right) + 3n^3$$
* **Paso $i$ (General):**
  $$T(n) = 27^i T\left(\frac{n}{3^i}\right) + i \cdot n^3$$
* **Caso Base:** Buscamos cuándo la variable recursiva llega al caso base $T(1)$:
  $$\frac{n}{3^i} = 1 \implies n = 3^i \implies i = \log_3(n)$$
* **Reemplazo final de $i$:**
  $$T(n) = 27^{\log_3(n)} T(1) + \log_3(n) \cdot n^3$$
  $$T(n) = (3^3)^{\log_3(n)} \cdot 3 + \log_3(n) \cdot n^3$$
  $$T(n) = (3^{\log_3(n)})^3 \cdot 3 + \log_3(n) \cdot n^3$$
  $$T(n) = n^3 \cdot 3 + \log_3(n) \cdot n^3 \implies T(n) = 3n^3 + n^3 \log_3(n)$$
* **Complejidad:** El término dominante es $n^3 \log_3(n)$, por lo tanto:
  $$T(n) = \mathcal{O}(n^3 \log n)$$

---

## ⚙️ Resolución por Teorema Maestro
Es la alternativa automática para ecuaciones de tipo $T(n) = a T(n/b) + \mathcal{O}(n^k)$.
Se comparan los valores de $a$ y $b^k$:
* **Caso 1:** Si $a > b^k \implies T(n) = \mathcal{O}(n^{\log_b a})$
* **Caso 2:** Si $a = b^k \implies T(n) = \mathcal{O}(n^k \log n)$
* **Caso 3:** Si $a < b^k \implies T(n) = \mathcal{O}(n^k)$

---
*Próxima Clase: [Clase 10: Grafos - Conceptos Básicos y Representaciones](Clase10.md)*
