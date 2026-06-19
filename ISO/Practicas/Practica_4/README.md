# 💻 Resolución Práctica 4: Memoria Virtual, Entrada/Salida y Sistemas de Archivos

**Materia:** Introducción a los Sistemas Operativos (ISO) — UNLP 2026

---

## Ejercicio 1: Traducción de Direcciones con Paginación por Demanda

### Tabla de Páginas:
| Página | Bit V | Bit R | Bit M | Marco |
| :---: | :---: | :---: | :---: | :---: |
| **0** | 1 | 1 | 0 | **4** |
| **1** | 1 | 1 | 1 | **7** |
| **2** | 0 | 0 | 0 | **-** |
| **3** | 1 | 0 | 0 | **2** |
| **4** | 0 | 0 | 0 | **-** |
| **5** | 1 | 0 | 1 | **0** |

*   **Tamaño de Página:** 512 Bytes
*   **Fórmula:** 
    *   $\text{Página} = \text{Dir. Virtual} \div \text{Tamaño de Página}$
    *   $\text{Desplazamiento} = \text{Dir. Virtual} \pmod{\text{Tamaño de Página}}$
    *   $\text{Dir. Física} = (\text{Marco} \times \text{Tamaño de Página}) + \text{Desplazamiento}$

### Respuestas:

*   **a. Dirección Virtual 1052:**
    *   $\text{Página} = 1052 \div 512 = 2$
    *   $\text{Desplazamiento} = 1052 \pmod{512} = 28$
    *   **Traducción:** La página 2 tiene el bit de validez $V = 0$ (no está en RAM). Se produce un **Fallo de Página (Page Fault)**, por lo que no existe dirección física correspondiente.
*   **b. Dirección Virtual 2221:**
    *   $\text{Página} = 2221 \div 512 = 4$
    *   $\text{Desplazamiento} = 2221 \pmod{512} = 173$
    *   **Traducción:** La página 4 tiene el bit de validez $V = 0$. Se produce un **Fallo de Página (Page Fault)**, por lo que no existe dirección física correspondiente.
*   **c. Dirección Virtual 5499:**
    *   $\text{Página} = 5499 \div 512 = 10$
    *   $\text{Desplazamiento} = 5499 \pmod{512} = 379$
    *   **Traducción:** La página 10 está fuera de los límites de la tabla de páginas (que solo tiene mapeo hasta la página 5). Esto produce un **Error de Segmentación (Segmentation Fault)** o acceso indebido a memoria, por lo que no existe dirección física correspondiente.
*   **d. Dirección Virtual 3101:**
    *   $\text{Página} = 3101 \div 512 = 6$
    *   $\text{Desplazamiento} = 3101 \pmod{512} = 29$
    *   **Traducción:** La página 6 está fuera de los límites de la tabla de páginas (que solo tiene mapeo hasta la página 5). Al igual que en el caso anterior, esto produce un **Error de Segmentación (Segmentation Fault)** o acceso indebido a memoria, por lo que no existe dirección física correspondiente.

---

## Ejercicio 2: Asignación de marcos a un proceso (Working Set)

### a. Descripción de Políticas de Asignación:
*   **Asignación Fija:**
    Al crearse un proceso, el sistema operativo le asigna un número de marcos de memoria física predeterminado y fijo. Este límite no cambia durante la vida del proceso, independientemente de si tiene un comportamiento con alta tasa de fallos de página (necesita más) o si está desperdiciando memoria (usa menos de lo asignado).
*   **Asignación Dinámica:**
    La cantidad de marcos asignados a un proceso fluctúa dinámicamente a lo largo de su ejecución en base a sus necesidades reales. El SO monitorea parámetros como la tasa de fallos de página; si un proceso sufre muchos fallos, se le asignan más marcos (robándoselos a otros o de una lista de libres), y si se estabiliza con pocos fallos, se reduce su número de marcos asignados.

### b. Cálculo de Repartos (Fija, 40 marcos):
*   **1. Reparto Equitativo:**
    Se reparte la memoria en partes iguales entre los 4 procesos:
    $$\text{Marcos por Proceso} = \frac{40}{4} = 10 \text{ marcos}$$
    *   **Proceso 1:** 10 marcos (requiere 15 páginas)
    *   **Proceso 2:** 10 marcos (requiere 20 páginas)
    *   **Proceso 3:** 10 marcos (requiere 20 páginas)
    *   **Proceso 4:** 10 marcos (requiere 8 páginas)
*   **2. Reparto Proporcional:**
    Se calcula en base al total de páginas requeridas ($S = 15 + 20 + 20 + 8 = 63$):
    *   **Proceso 1:** $\frac{15}{63} \times 40 = 9.52 \approx \mathbf{9 \text{ marcos}}$ (ajustado para sumar 40)
    *   **Proceso 2:** $\frac{20}{63} \times 40 = 12.70 \approx \mathbf{13 \text{ marcos}}$
    *   **Proceso 3:** $\frac{20}{63} \times 40 = 12.70 \approx \mathbf{13 \text{ marcos}}$
    *   **Proceso 4:** $\frac{8}{63} \times 40 = 5.08 \approx \mathbf{5 \text{ marcos}}$
    *   *Suma de comprobación:* $9 + 13 + 13 + 5 = 40$ marcos.

### c. Análisis de Eficiencia:
El **Reparto Proporcional** es el más eficiente por las siguientes razones:
1.  **Evita el desperdicio de memoria:** En el reparto equitativo, al Proceso 4 se le asignan 10 marcos, pero como solo requiere 8 páginas en total, hay **2 marcos que se desperdician por completo** (ya que nunca los utilizará y ningún otro proceso puede tomarlos en un esquema de asignación fija).
2.  **Mitiga la tasa de fallos de página en procesos grandes:** En el reparto equitativo, los procesos 2 y 3 (que requieren 20 páginas cada uno) solo reciben 10 marcos (el 50% de sus datos), lo que provocará una tasa de fallos de página extremadamente alta. En el reparto proporcional, reciben 13 marcos, aliviando la presión sobre su memoria física.

---

## Ejercicio 3: Algoritmos de Reemplazo de Páginas

### a. Clasificación de Peor a Mejor (Tasa de Fallos):
$$\text{FIFO} \quad < \quad \text{Segunda Chance (Reloj)} \quad < \quad \text{LRU} \quad < \quad \text{Óptimo (OPT)}$$

1.  **FIFO:** Es el peor. No tiene en cuenta el uso reciente ni la frecuencia; puede desalojar páginas de uso continuo solo porque llegaron primero. Además, sufre de la *Anomalía de Belady*.
2.  **Segunda Chance:** Mejora a FIFO introduciendo un bit de referencia ($R$). Evita desalojar páginas activas dándoles una segunda oportunidad.
3.  **LRU:** Excelente rendimiento en la práctica porque se basa en el pasado reciente (localidad temporal).
4.  **OPT:** El mejor teóricamente (tasa de fallos mínima absoluta), pero imposible de implementar de forma real porque requiere conocer los accesos futuros del proceso.

### b. Funcionamiento e Implementación:
*   **FIFO (First-In, First-Out):**
    *   *Funcionamiento:* Reemplaza la página que lleva más tiempo en memoria.
    *   *Implementación:* Se mantiene una cola simple (FIFO) de las páginas en memoria. La víctima se toma de la cabeza de la cola, y las nuevas páginas ingresan por la cola.
*   **Segunda Chance (Reloj):**
    *   *Funcionamiento:* Variante de FIFO. Si la página más antigua tiene el bit de referencia $R = 1$, se limpia el bit ($R \leftarrow 0$) y se la mueve al final de la cola (se le da otra oportunidad), pasando a evaluar la siguiente. Si $R = 0$, es elegida víctima.
    *   *Implementación:* Lista circular (como las agujas de un reloj) con un puntero que avanza buscando un $R = 0$.
*   **LRU (Least Recently Used):**
    *   *Funcionamiento:* Reemplaza la página que no ha sido utilizada por el período de tiempo más largo.
    *   *Implementación:* Requiere soporte de hardware. Se puede implementar con un **contador/reloj de sistema** (guardando el timestamp del último acceso en el descriptor de cada página) o mediante una **pila** en hardware de números de página (donde cada acceso mueve la página al tope de la pila; la víctima está siempre en el fondo).
*   **OPT (Óptimo):**
    *   *Funcionamiento:* Reemplaza la página que tardará más tiempo en ser usada en el futuro.
    *   *Implementación:* No es implementable en la práctica. Se usa ejecutando el programa una primera vez para registrar la traza de accesos y simularlo en una segunda ejecución como parámetro de referencia (benchmark).

### c. Detección de Página Modificada (Bit M) y Acciones:
1.  **Detección:** El hardware de la MMU y el Kernel detectan si una página fue modificada consultando el **Bit M (Modified o "Dirty Bit")** de la entrada correspondiente en la Tabla de Páginas. Este bit se pone en `1` automáticamente por hardware cuando se realiza cualquier operación de escritura en esa zona de memoria.
2.  **Acciones del Kernel:**
    *   **Si el Bit M = 1 (Página Modificada):** El Kernel **no puede** simplemente sobrescribir el marco, ya que se perderían los cambios realizados por el proceso. Primero debe **escribir la página víctima de regreso al disco (área de Swap o archivo original)**. Una vez finalizada la transferencia a disco, el Bit M vuelve a `0` y el marco queda libre para ser sobrescrito con la nueva página.
    *   **Si el Bit M = 0 (Página NO Modificada):** Como los datos en memoria son idénticos a los del disco, el Kernel **no realiza ninguna escritura a disco**. Simplemente invalida la entrada de la tabla de páginas y sobrescribe el marco directamente con la nueva página. Esto ahorra una operación física de Entrada/Salida muy costosa.

---

## Ejercicio 4: Simulación de Algoritmos de Reemplazo

*   **Marcos disponibles:** 5
*   **Secuencia de referencias:** `1, 2, 15, 4, 6, 2, 1, 5, 6, 10, 4, 6, 7, 9, 1, 6, 12, 11, 12, 2, 3, 1, 8, 1, 13, 14, 15, 3, 8`
*   **Asignación:** Dinámica, Reemplazo Global.
*   **Costo de Fallo de Página:** 0.1 segundos por fallo.

### a. Simulación y Conteo de Fallos de Página:

#### i. Segunda Chance (Reloj)
*   **Traza del estado de marcos (las páginas con `*` indican Bit de Referencia $R=1$):**
    1.  Ref 1: `[1, -, -, -, -]` (Fallo)
    2.  Ref 2: `[1, 2, -, -, -]` (Fallo)
    3.  Ref 15: `[1, 2, 15, -, -]` (Fallo)
    4.  Ref 4: `[1, 2, 15, 4, -]` (Fallo)
    5.  Ref 6: `[1, 2, 15, 4, 6]` (Fallo)
    6.  Ref 2: `[1, 2*, 15, 4, 6]` (Acierto)
    7.  Ref 1: `[1*, 2*, 15, 4, 6]` (Acierto)
    8.  Ref 5: `[5, 2, 15, 4, 6]` -> *Fallo.* (1 y 2 salvan sus vidas pero pierden el bit $R$, 15 es reemplazado).
    9.  Ref 6: `[5, 2, 15, 4, 6*]` (Acierto)
    10. Ref 10: `[5, 10, 15, 4, 6*]` -> *Fallo.* (4 es reemplazado).
    11. Ref 4: `[4, 10, 15, 4, 6]` -> *Fallo.* (6 pierde bit $R$, 1 es reemplazado. Marcos reales: `[4, 2, 5, 10, 6]`).
    12. Ref 6: `[4, 2, 5, 10, 6*]` (Acierto)
    13. Ref 7: `[4, 7, 5, 10, 6*]` -> *Fallo.* (2 es reemplazado).
    14. Ref 9: `[4, 7, 9, 10, 6*]` -> *Fallo.* (5 es reemplazado).
    15. Ref 1: `[4, 7, 9, 1, 6*]` -> *Fallo.* (10 es reemplazado).
    16. Ref 6: `[4, 7, 9, 1, 6*]` (Acierto)
    17. Ref 12: `[12, 7, 9, 1, 6]` -> *Fallo.* (6 pierde bit $R$, 4 es reemplazado).
    18. Ref 11: `[12, 11, 9, 1, 6]` -> *Fallo.* (7 es reemplazado).
    19. Ref 12: `[12*, 11, 9, 1, 6]` (Acierto)
    20. Ref 2: `[12*, 11, 2, 1, 6]` -> *Fallo.* (9 es reemplazado).
    21. Ref 3: `[12*, 11, 2, 3, 6]` -> *Fallo.* (1 es reemplazado).
    22. Ref 1: `[12*, 11, 2, 3, 1]` -> *Fallo.* (6 es reemplazado).
    23. Ref 8: `[12, 8, 2, 3, 1]` -> *Fallo.* (12 pierde bit $R$, 11 es reemplazado).
    24. Ref 1: `[12, 8, 2, 3, 1*]` (Acierto)
    25. Ref 13: `[12, 8, 13, 3, 1*]` -> *Fallo.* (2 es reemplazado).
    26. Ref 14: `[12, 8, 13, 14, 1*]` -> *Fallo.* (3 es reemplazado).
    27. Ref 15: `[15, 8, 13, 14, 1]` -> *Fallo.* (1 pierde bit $R$, 12 es reemplazado).
    28. Ref 3: `[15, 3, 13, 14, 1]` -> *Fallo.* (8 es reemplazado).
    29. Ref 8: `[15, 3, 8, 14, 1]` -> *Fallo.* (13 es reemplazado).

*   **Total de fallos (Segunda Chance):** 22
*   **Tiempo total de atención:** $22 \times 0.1 \text{ s} = \mathbf{2.2 \text{ segundos}}$

#### ii. FIFO (First-In, First-Out)
*   **Traza del estado de marcos:**
    1.  Ref 1: `[1, -, -, -, -]` (Fallo)
    2.  Ref 2: `[1, 2, -, -, -]` (Fallo)
    3.  Ref 15: `[1, 2, 15, -, -]` (Fallo)
    4.  Ref 4: `[1, 2, 15, 4, -]` (Fallo)
    5.  Ref 6: `[1, 2, 15, 4, 6]` (Fallo)
    6.  Ref 2: `[1, 2, 15, 4, 6]` (Acierto)
    7.  Ref 1: `[1, 2, 15, 4, 6]` (Acierto)
    8.  Ref 5: `[5, 2, 15, 4, 6]` -> *Fallo.* (1 es reemplazado).
    9.  Ref 6: `[5, 2, 15, 4, 6]` (Acierto)
    10. Ref 10: `[5, 10, 15, 4, 6]` -> *Fallo.* (2 es reemplazado).
    11. Ref 4: `[5, 10, 15, 4, 6]` (Acierto)
    12. Ref 6: `[5, 10, 15, 4, 6]` (Acierto)
    13. Ref 7: `[5, 10, 7, 4, 6]` -> *Fallo.* (15 es reemplazado).
    14. Ref 9: `[5, 10, 7, 9, 6]` -> *Fallo.* (4 es reemplazado).
    15. Ref 1: `[5, 10, 7, 9, 1]` -> *Fallo.* (6 es reemplazado).
    16. Ref 6: `[6, 10, 7, 9, 1]` -> *Fallo.* (5 es reemplazado).
    17. Ref 12: `[6, 12, 7, 9, 1]` -> *Fallo.* (10 es reemplazado).
    18. Ref 11: `[6, 12, 11, 9, 1]` -> *Fallo.* (7 es reemplazado).
    19. Ref 12: `[6, 12, 11, 9, 1]` (Acierto)
    20. Ref 2: `[6, 12, 11, 2, 1]` -> *Fallo.* (9 es reemplazado).
    21. Ref 3: `[6, 12, 11, 2, 3]` -> *Fallo.* (1 es reemplazado).
    22. Ref 1: `[1, 12, 11, 2, 3]` -> *Fallo.* (6 es reemplazado).
    23. Ref 8: `[1, 8, 11, 2, 3]` -> *Fallo.* (12 es reemplazado).
    24. Ref 1: `[1, 8, 11, 2, 3]` (Acierto)
    25. Ref 13: `[1, 8, 13, 2, 3]` -> *Fallo.* (11 es reemplazado).
    26. Ref 14: `[1, 8, 13, 14, 3]` -> *Fallo.* (2 es reemplazado).
    27. Ref 15: `[1, 8, 13, 14, 15]` -> *Fallo.* (3 es reemplazado).
    28. Ref 3: `[3, 8, 13, 14, 15]` -> *Fallo.* (1 es reemplazado).
    29. Ref 8: `[3, 8, 13, 14, 15]` (Acierto)

*   **Total de fallos (FIFO):** 21
*   **Tiempo total de atención:** $21 \times 0.1 \text{ s} = \mathbf{2.1 \text{ segundos}}$

#### iii. LRU (Least Recently Used)
*   **Traza del estado de marcos:**
    1.  Ref 1: `[1, -, -, -, -]` (Fallo)
    2.  Ref 2: `[1, 2, -, -, -]` (Fallo)
    3.  Ref 15: `[1, 2, 15, -, -]` (Fallo)
    4.  Ref 4: `[1, 2, 15, 4, -]` (Fallo)
    5.  Ref 6: `[1, 2, 15, 4, 6]` (Fallo)
    6.  Ref 2: `[1, 2, 15, 4, 6]` (Acierto)
    7.  Ref 1: `[1, 2, 15, 4, 6]` (Acierto)
    8.  Ref 5: `[1, 2, 5, 4, 6]` -> *Fallo.* (15 es reemplazado).
    9.  Ref 6: `[1, 2, 5, 4, 6]` (Acierto)
    10. Ref 10: `[1, 2, 5, 10, 6]` -> *Fallo.* (4 es reemplazado).
    11. Ref 4: `[1, 4, 5, 10, 6]` -> *Fallo.* (2 es reemplazado).
    12. Ref 6: `[1, 4, 5, 10, 6]` (Acierto)
    13. Ref 7: `[7, 4, 5, 10, 6]` -> *Fallo.* (1 es reemplazado).
    14. Ref 9: `[7, 4, 9, 10, 6]` -> *Fallo.* (5 es reemplazado).
    15. Ref 1: `[7, 4, 9, 1, 6]` -> *Fallo.* (10 es reemplazado).
    16. Ref 6: `[7, 4, 9, 1, 6]` (Acierto)
    17. Ref 12: `[7, 12, 9, 1, 6]` -> *Fallo.* (4 es reemplazado).
    18. Ref 11: `[11, 12, 9, 1, 6]` -> *Fallo.* (7 es reemplazado).
    19. Ref 12: `[11, 12, 9, 1, 6]` (Acierto)
    20. Ref 2: `[11, 12, 2, 1, 6]` -> *Fallo.* (9 es reemplazado).
    21. Ref 3: `[11, 12, 2, 3, 6]` -> *Fallo.* (1 es reemplazado).
    22. Ref 1: `[11, 12, 2, 3, 1]` -> *Fallo.* (6 es reemplazado).
    23. Ref 8: `[8, 12, 2, 3, 1]` -> *Fallo.* (11 es reemplazado).
    24. Ref 1: `[8, 12, 2, 3, 1]` (Acierto)
    25. Ref 13: `[8, 13, 2, 3, 1]` -> *Fallo.* (12 es reemplazado).
    26. Ref 14: `[8, 13, 14, 3, 1]` -> *Fallo.* (2 es reemplazado).
    27. Ref 15: `[8, 13, 14, 15, 1]` -> *Fallo.* (3 es reemplazado).
    28. Ref 3: `[3, 13, 14, 15, 1]` -> *Fallo.* (8 es reemplazado).
    29. Ref 8: `[3, 13, 14, 15, 8]` -> *Fallo.* (1 es reemplazado).

*   **Total de fallos (LRU):** 22
*   **Tiempo total de atención:** $22 \times 0.1 \text{ s} = \mathbf{2.2 \text{ segundos}}$

#### iv. Óptimo (OPT)
*   **Traza del estado de marcos:**
    1.  Ref 1: `[1, -, -, -, -]` (Fallo)
    2.  Ref 2: `[1, 2, -, -, -]` (Fallo)
    3.  Ref 15: `[1, 2, 15, -, -]` (Fallo)
    4.  Ref 4: `[1, 2, 15, 4, -]` (Fallo)
    5.  Ref 6: `[1, 2, 15, 4, 6]` (Fallo)
    6.  Ref 2: `[1, 2, 15, 4, 6]` (Acierto)
    7.  Ref 1: `[1, 2, 15, 4, 6]` (Acierto)
    8.  Ref 5: `[1, 2, 5, 4, 6]` -> *Fallo.* (15 es reemplazado por 5, ya que es el que más tarde se vuelve a usar).
    9.  Ref 6: `[1, 2, 5, 4, 6]` (Acierto)
    10. Ref 10: `[1, 2, 10, 4, 6]` -> *Fallo.* (5 es reemplazado por 10, ya que nunca vuelve a usarse).
    11. Ref 4: `[1, 2, 10, 4, 6]` (Acierto)
    12. Ref 6: `[1, 2, 10, 4, 6]` (Acierto)
    13. Ref 7: `[1, 2, 7, 4, 6]` -> *Fallo.* (10 es reemplazado por 7, ya que nunca vuelve a usarse).
    14. Ref 9: `[1, 2, 9, 4, 6]` -> *Fallo.* (7 es reemplazado por 9, ya que nunca vuelve a usarse).
    15. Ref 1: `[1, 2, 9, 4, 6]` (Acierto)
    16. Ref 6: `[1, 2, 9, 4, 6]` (Acierto)
    17. Ref 12: `[1, 2, 12, 4, 6]` -> *Fallo.* (9 es reemplazado por 12, ya que nunca vuelve a usarse).
    18. Ref 11: `[1, 2, 12, 11, 6]` -> *Fallo.* (4 es reemplazado por 11, ya que nunca vuelve a usarse).
    19. Ref 12: `[1, 2, 12, 11, 6]` (Acierto)
    20. Ref 2: `[1, 2, 12, 11, 6]` (Acierto)
    21. Ref 3: `[1, 2, 12, 11, 3]` -> *Fallo.* (6 es reemplazado por 3, ya que nunca vuelve a usarse).
    22. Ref 1: `[1, 2, 12, 11, 3]` (Acierto)
    23. Ref 8: `[1, 8, 12, 11, 3]` -> *Fallo.* (2 es reemplazado por 8, ya que nunca vuelve a usarse).
    24. Ref 1: `[1, 8, 12, 11, 3]` (Acierto)
    25. Ref 13: `[1, 8, 13, 11, 3]` -> *Fallo.* (12 es reemplazado por 13).
    26. Ref 14: `[1, 8, 13, 14, 3]` -> *Fallo.* (11 es reemplazado por 14).
    27. Ref 15: `[15, 8, 13, 14, 3]` -> *Fallo.* (1 es reemplazado por 15).
    28. Ref 3: `[15, 8, 13, 14, 3]` (Acierto)
    29. Ref 8: `[15, 8, 13, 14, 3]` (Acierto)

*   **Total de fallos (OPT):** 16
*   **Tiempo total de atención:** $16 \times 0.1 \text{ s} = \mathbf{1.6 \text{ segundos}}$

### b. Tabla Comparativa de Tiempos de Atención (Costo: 0.1 s por fallo):

| Algoritmo de Reemplazo | Fallos de Página (PF) | Tiempo de Atención (segundos) |
| :--- | :---: | :---: |
| **FIFO** | 21 | 2.1 s |
| **Segunda Chance (Reloj)** | 22 | 2.2 s |
| **LRU (Least Recently Used)** | 22 | 2.2 s |
| **Óptimo (OPT)** | **16** | **1.6 s** |

*Nota:* Como se esperaba teóricamente, el algoritmo **Óptimo (OPT)** presenta el menor número de fallos de página y el menor tiempo consumido. En este caso particular, **FIFO** superó levemente a LRU y Segunda Chance debido al orden y re-referencias específicas de la traza (particularidades de la traza de entrada).
