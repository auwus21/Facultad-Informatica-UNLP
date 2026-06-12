# 📘 Resolución del Ejercicio 4 — Notación Asintótica Big-Oh

**Materia:** Algoritmos y Estructuras de Datos (AyED) — UNLP 2026  
**Tema:** Demostración formal de pertenencia a cotas de complejidad utilizando la definición de Big-Oh ($\mathcal{O}$).

---

## 📜 Definición Formal de Big-Oh ($\mathcal{O}$)
> Decimos que $T(n) \in \mathcal{O}(f(n))$ si y solo si existen constantes positivas $c > 0$ y $n_0 \ge 1$ tales que:
> $$T(n) \le c \cdot f(n) \quad \forall n \ge n_0$$

A continuación se presenta la resolución de cada inciso de la práctica de forma formal, ideal para examen escrito.

---

### 🔍 Inciso a: $3^n$ es de $\mathcal{O}(2^n)$
* **Respuesta: FALSO.**
* **Demostración por el absurdo:**
  Supongamos que la sentencia es verdadera. Por la definición formal de Big-Oh, deben existir constantes $c > 0$ y $n_0 \ge 1$ tales que:
  $$3^n \le c \cdot 2^n \quad \forall n \ge n_0$$
  Como $2^n > 0$, dividimos en ambos miembros por $2^n$:
  $$\frac{3^n}{2^n} \le c \implies \left(\frac{3}{2}\right)^n \le c \implies (1.5)^n \le c \quad \forall n \ge n_0$$
  Sin importar cuán grande elijamos la constante $c$, la función $(1.5)^n$ crece de forma exponencial tendiendo a infinito cuando $n \to \infty$. Esto hace imposible acotar superiormente la función por una constante fija $c$, lo cual contradice el supuesto inicial.
* **Conclusión:** $3^n \notin \mathcal{O}(2^n)$. Las funciones exponenciales de base mayor crecen asintóticamente más rápido.

---

### 🔍 Inciso b: $n + \log_2(n)$ es de $\mathcal{O}(n)$
* **Respuesta: VERDADERO.**
* **Justificación formal (Método de acotación término a término):**
  Debemos demostrar que existen $c > 0$ y $n_0 \ge 1$ tales que:
  $$n + \log_2(n) \le c \cdot n \quad \forall n \ge n_0$$
  Analizamos cada término individualmente respecto a la cota lineal $n$:
  1. Para el término lineal: $n \le 1 \cdot n \quad \forall n \ge 1 \implies c_1 = 1$, con $n_{0_1} = 1$.
  2. Para el término logarítmico: $\log_2(n) \le 1 \cdot n \quad \forall n \ge 1 \implies c_2 = 1$, con $n_{0_2} = 1$.
  
  Sumando ambas desigualdades término a término:
  $$n + \log_2(n) \le (c_1 + c_2) \cdot n$$
  $$n + \log_2(n) \le 2n \quad \forall n \ge 1$$
* **Conclusión:** Se cumple la desigualdad formal eligiendo la constante **$c = 2$** y el umbral de entrada **$n_0 = 1$**.

---

### 🔍 Inciso c: $n^{1/2} + 10^{20}$ es de $\mathcal{O}(n^{1/2})$
* **Respuesta: VERDADERO.**
* **Justificación formal (Método de acotación término a término):**
  Debemos demostrar que existen $c > 0$ y $n_0 \ge 1$ tales que:
  $$n^{1/2} + 10^{20} \le c \cdot n^{1/2} \quad \forall n \ge n_0$$
  Analizamos los términos respecto a la cota raíz $n^{1/2}$:
  1. Para el primer término: $n^{1/2} \le 1 \cdot n^{1/2} \quad \forall n \ge 1 \implies c_1 = 1$, con $n_{0_1} = 1$.
  2. Para la constante: $10^{20} \le 10^{20} \cdot n^{1/2} \quad \forall n \ge 1$ (ya que para $n \ge 1 \implies n^{1/2} \ge 1$, por lo tanto multiplicar por $n^{1/2}$ agranda o mantiene el término de la derecha) $\implies c_2 = 10^{20}$, con $n_{0_2} = 1$.
  
  Sumando ambas desigualdades:
  $$n^{1/2} + 10^{20} \le (1 + 10^{20}) \cdot n^{1/2} \quad \forall n \ge 1$$
* **Conclusión:** Se cumple la definición eligiendo la constante **$c = 10^{20} + 1$** y el umbral **$n_0 = 1$**.

---

### 🔍 Inciso d: $\frac{n}{\log_2(n)}$ tiene orden lineal
* **Respuesta: VERDADERO (bajo la definición de Big-Oh).**
* **Justificación formal:**
  "Tener orden lineal" significa que la función pertenece a la familia $\mathcal{O}(n)$. Debemos demostrar que existen $c > 0$ y $n_0 \ge 1$ tales que:
  $$\frac{n}{\log_2(n)} \le c \cdot n \quad \forall n \ge n_0$$
  Dividiendo por $n$ en ambos miembros (asumiendo $n > 0$):
  $$\frac{1}{\log_2(n)} \le c \quad \forall n \ge n_0$$
  Si elegimos $n_0 = 2$, entonces para todo $n \ge 2 \implies \log_2(n) \ge 1$, lo que implica:
  $$\frac{1}{\log_2(n)} \le 1 \quad \forall n \ge 2$$
* **Conclusión:** Eligiendo **$c = 1$** y **$n_0 = 2$**, se cumple formalmente que $\frac{n}{\log_2(n)} \le 1 \cdot n$, por lo que pertenece a $\mathcal{O}(n)$ (su crecimiento está acotado superiormente por una función lineal).
* *Nota conceptual de examen:* Si la cátedra pregunta si pertenece a $\Theta(n)$ (cota ajustada exacta), la respuesta es Falsa, ya que $\lim_{n \to \infty} \frac{n/\log_2(n)}{n} = \lim_{n \to \infty} \frac{1}{\log_2(n)} = 0$, lo que demuestra que la función crece estrictamente más lento que una función lineal. Pero para Big-Oh (cota superior) es **Verdadero**.

---

### 🔍 Inciso e: Mostrar que $p(n) = 3n^5 + 8n^4 + 2n + 1$ es de $\mathcal{O}(n^5)$
* **Demostración formal término a término:**
  Debemos demostrar que existen $c > 0$ y $n_0 \ge 1$ tales que $p(n) \le c \cdot n^5$ para todo $n \ge n_0$.
  1. $3n^5 \le 3n^5 \quad \forall n \ge 1 \implies c_1 = 3$
  2. $8n^4 \le 8n^5 \quad \forall n \ge 1 \implies c_2 = 8$
  3. $2n \le 2n^5 \quad \forall n \ge 1 \implies c_3 = 2$
  4. $1 \le 1n^5 \quad \forall n \ge 1 \implies c_4 = 1$
  
  Sumando los cuatro términos:
  $$3n^5 + 8n^4 + 2n + 1 \le (3 + 8 + 2 + 1)n^5$$
  $$p(n) \le 14n^5 \quad \forall n \ge 1$$
* **Conclusión:** Se cumple la definición formal eligiendo la constante **$c = 14$** y el umbral de entrada **$n_0 = 1$**.

---

### 🔍 Inciso f: Si $p(n)$ es un polinomio de grado $k$, entonces $p(n)$ es de $\mathcal{O}(n^k)$
* **Demostración formal general:**
  Sea $p(n) = a_k n^k + a_{k-1} n^{k-1} + \dots + a_1 n + a_0$ un polinomio de grado $k$ con coeficientes reales. Queremos probar que existe una constante $c > 0$ y un $n_0 \ge 1$ tal que $|p(n)| \le c \cdot n^k$ para todo $n \ge n_0$.
  
  Aplicando la desigualdad triangular al valor absoluto:
  $$|p(n)| \le |a_k| n^k + |a_{k-1}| n^{k-1} + \dots + |a_1| n + |a_0|$$
  Dado que para todo $n \ge 1$ y para cualquier exponente $i < k$ se cumple que $n^i \le n^k$, podemos acotar superiormente cada término del polinomio:
  $$|p(n)| \le |a_k| n^k + |a_{k-1}| n^k + \dots + |a_1| n^k + |a_0| n^k$$
  Sacando factor común $n^k$:
  $$|p(n)| \le \left( |a_k| + |a_{k-1}| + \dots + |a_1| + |a_0| \right) \cdot n^k \quad \forall n \ge 1$$
* **Conclusión:** Definimos la constante **$c = \sum_{j=0}^{k} |a_j|$** (la suma de los valores absolutos de todos los coeficientes) y el umbral **$n_0 = 1$**. Así queda demostrado formalmente que cualquier polinomio de grado $k$ es de orden $\mathcal{O}(n^k)$.
