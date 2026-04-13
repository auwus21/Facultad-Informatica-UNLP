# 💻 Resolución Práctica 2: Planificación y Memoria

**Materia**: Introducción a los Sistemas Operativos (ISO)

---

## 1. Algoritmos de Scheduling (Planificación)

**a. Funcionamiento básico**
- **FCFS**: Atiende por estricto orden de llegada. No apropiativo.
- **SJF**: Ejecuta primero el proceso con la ráfaga de CPU más corta.
- **Round Robin (RR)**: Reparte equitativamente el tiempo en turnos forzados (*Quantum*).
- **Prioridades**: Ejecuta primero el proceso con el nivel de prioridad asignado más alto.

**b. Parámetros explícitos**
- **Round Robin**: Exige deﬁnir el tamaño del **Quantum (q)**.
- **SJF**: Requiere conocer la predicción futura de la **Ráfaga de CPU**.
- **Prioridades**: Requiere asignar fijamente un **Número de prioridad**.

**c. Adecuación según procesos**
- **FCFS / SJF**: Excelentes para sistemas por Lotes (*Batch*) que buscan volumen sin apuro.
- **Round Robin**: Ideal para sistemas interactivos donde el usuario espera respuesta rápida.
- **Prioridades**: Usado en sistemas de Tiempo Real (RTOS) donde hay tareas críticas ineludibles.

**d. Ventajas y Desventajas**
- **FCFS**: ✔️ Sencillo, sin inanición. ❌ Sufre el "Efecto Convoy" (los lentos traban la cola).
- **SJF**: ✔️ Garantiza matemáticas de menor espera. ❌ Posible inanición de trabajos largos.
- **RR**: ✔️ Ágil e interactivo. ❌ Exige mucho tiempo de la CPU a nivel interno (*overhead*).
- **Prioridades**: ✔️ Respeta prioridades corporativas o de hardware. ❌ Genera inanición si no hay "envejecimiento".

**e. TR y TE (Para un proceso)**
- **TE (Tiempo de Espera)**: Tiempo ocioso sumado dentro de la Cola de Listos.
- **TR (Tiempo de Retorno)**: Tiempo completo, vida del proceso desde que llega de disco hasta que termina.

**f. TPR y TPE (Para el Lote de Procesos)**
- **TPR/TPE (Tiempo Promedio de Retorno/Espera)**: Las sumatorias de los TR/TE divididos por la cantidad de procesos totales.

**g. Tiempo de Respuesta**
- Tiempo vital transcurrido desde que un proceso nace en la cola hasta que recibe la CPU **por primera vez**.

---

## 3. Resolución Ejercicio 3: Cálculo de Algoritmos

### a. Diagramas de Gantt
*(Las siguientes gráficas representan el flujo del lote evaluado).*

**i. FCFS (First Come First Served)**
<img src="./P2-ejer3-i.png" width="650"/>

**ii. SJF (Shortest Job First)**
<img src="./P2-ejer3-ii.png" width="650"/>

**iii. Round Robin (q=1)**
<img src="./P2-ejer3-iii.png" width="650"/>

**iv. Round Robin (q=6)**
<img src="./P2-ejer3-iv.png" width="650"/>

---

### b. Tiempos Calculados (TR y TE)

| Algoritmo | J1 *(TR/TE)* | J2 *(TR/TE)* | J3 *(TR/TE)* | J4 *(TR/TE)* | J5 *(TR/TE)* | **TPR** | **TPE** |
|---|---|---|---|---|---|---|---|
| **FCFS** | 4 / 0 | 8 / 2 | 11 / 7 | 13 / 8 | 13 / 11 | **9.8** | **5.6** |
| **SJF** | 4 / 0 | 19 / 13 | 5 / 1 | 9 / 4 | 2 / 0 | **7.8** | **3.6** |
| **RR (q=1)** | 5 / 1 | 18 / 12 | 12 / 8 | 15 / 10 | 8 / 6 | **11.6** | **7.4** |
| **RR (q=6)** | 4 / 0 | 8 / 2 | 11 / 7 | 13 / 8 | 13 / 11 | **9.8** | **5.6** |

### c. Comparación de algoritmos
- **SJF** logra matemáticamente los promedios más bajos debido a que despacha los cortos enseguida (pero arrincona y condena al pesado J2).
- **RR (q=6)** obtiene aquí exactamente los *mismos valores y ordenamiento* que FCFS, puesto que el quantum=6 es lo suficientemente enorme como para alojar íntegramente de corrido a todas las tareas de la cola sin expulsarlas.
- **RR (q=1)** brinda un tiempo de espera estadístico pobrísimo con el TPR y TPE más altos por estirar absurdamente la vida del proceso con miles de expulsiones diminutas y *overhead* inútil de contexto.

### d. Conclusión sobre el Quantum
La dinámica de Round Robin reside ciegamente en qué tan atinado sea su valor `q`. Si el quantum es excesivamente gigante de tamaño frente al promedio de tiempo útil de las tareas, termina rompiendo el modelo circular y comportándose meramente como de **FCFS**. Si es microscópico, quema tiempo del hardware gestionándose a sí mismo en detrimetro del rendimiento general.

### e. ¿Cuándo usar un Quantum Alto? (Ventaja/Desventaja)
- **Cuándo usar:** Preferible utilizar en sistemas de cómputos pesados de "Batch", sin ninguna intervención en tiempo real, donde lo que le interesa a los administradores es fulminar el trabajo general lo antes posible.
- **✔️ Ventajas:** Minimiza casi por completo a 0 la sobrecarga técnica u `overhead` generada por tener que "mudar contextos y pilas de memoria" ante cada interrupción del Reloj, es decir: destina su 99% a laburar datos.
- **❌ Desventajas:** Fulmina el nivel o rango de respuesta interactiva para el usuario al obligar a todas las aplicaciones menores a sufrir el "Efecto Convoy" de tener por delante un proceso mamut interminable.
