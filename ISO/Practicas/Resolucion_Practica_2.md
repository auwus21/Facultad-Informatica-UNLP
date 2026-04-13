# 💻 Resolución Práctica 2: Planificación y Memoria

**Materia**: Introducción a los Sistemas Operativos (ISO)

*Respuestas directas, resumidas y en formato de lista para facilitar un estudio dinámico.*

---

## 1. Algoritmos de Scheduling (Planificación)

### a. Funcionamiento mediante un ejemplo
- **FCFS (*First Come First Served*)**: Atiende a los procesos secuencialmente por estricto orden de llegada a la cola, sin echarlos a la fuerza (No apropiativo).
  - *Ejemplo:* Llega P1 (exige 10ms) y luego P2 (exige 5ms). La CPU atiende primero a P1 entero, y una vez terminado a los 10ms arranca P2.
- **SJF (*Shortest Job First*)**: Ejecuta primero el proceso que posea la ráfaga de CPU más corta o que menor tiempo de trabajo necesite.
  - *Ejemplo:* Llegan a la vez P1 (10ms) y P2 (5ms). Aunque sean las mismas tareas de antes, ahora la CPU arranca y finaliza primero a P2 por ser más corto, y recién después sigue con P1.
- **Round Robin (RR)**: Reparte el uso de CPU equitativamente e iterando entre procesos mediante una fracción de tiempo forzada (*Quantum*).
  - *Ejemplo:* P1, P2 y P3 llegan listos. El *Quantum* es 2ms. P1 recibe 2ms y queda pausado; el turno gira y P2 recibe 2ms; el turno gira y P3 recibe 2ms. Finalmente vuelve a P1, dando la ilusión geométrica de rueda.
- **Prioridades**: A cada proceso se le asigna un número o valor categorizado, atendiéndose primero a aquel que goce del número de de estatus más alto/vital.
  - *Ejemplo:* P1 (prioridad baja estándar) y P2 (prioridad muy alta/root). El kernel detendrá a P1 y obligará a cederle el tiempo a P2 por ser tarea de mayor urgencia.

### b. Parámetros explícitos de funcionamiento
Sí, varios algoritmos requieren datos o configuraciones extras para funcionar:
- **Round Robin**: Exige definir estáticamente un tamaño de **Quantum (q)** (franja de tiempo de CPU prestado). Opcionalmente también requiere determinar un tiempo de sobrecarga de sistema (overhead de *Context Switch*).
- **SJF**: Requiere conocer la predicción de la **Ráfaga estimada de CPU** a futuro del proceso.
- **Prioridades**: Requiere explícitamente fijar la variable de **Número de prioridad** en el sistema para cada bloque de control.

### c. Adecuación según el tipo de procesos
- **FCFS**: Bueno para los procesos base clásicos por *Lotes (Batch)* donde solo importa terminar el bloque y no hay interactividad ni usuarios apurando del otro lado.
- **SJF**: Ideal para procesos por *Lotes* rápidos cuando la meta final de la compañía/servidor es maximizar el número de planificaciones por hora y bajar los tiempos estadísticos muertos a tope.
- **Round Robin**: Es el rey predilecto de los procesos *Interactivos* para sistemas multiusuario contemporáneos (logra repartir la respuesta rápido para simular paralelismo sin congelar ventanas).
- **Prioridades**: Usado en sistemas interactivos robustos combinados, ó sistemas especializados de *Tiempo Real (RTOS)* (equipos médicos / naves aéreas) donde tareas sumamente vitales frenan todo el resto en seco.

### d. Ventajas y Desventajas de los Modelos
- **FCFS**:
   - ✔️ **V**: Es increíblemente sencillo de programar. Justo socialmente y no genera inanición en ningún proceso (todos llegan de la fila algún día).
   - ❌ **D**: Genera el "Efecto Convoy". Un proceso masivo atora la fila impidiendo el paso al resto de programitas menores e inútilmente haciéndolos esperar.
- **SJF**:
   - ✔️ **V**: Brinda probabilísticamente el **menor tiempo promedio de espera matemático** en un sistema en lote.
   - ❌ **D**: En la práctica es imposible adivinar el tamaño exacto de la ráfaga requerida. Peligro de *Starvation (Inanición temporal)* donde los procesos largos pesados nunca se ejecuten porque una y otra vez arriban procecitos más cortos.
- **Round Robin**:
   - ✔️ **V**: Gran tiempo de respuesta de reacción, los programas reaccionan ágilmente, se comporta democráticamente sin acaparamientos pesados.
   - ❌ **D**: Genera constante "Overhead" o penalidad en la CPU ya que forzar tantos saltitos de Cambio de Contexto requiere gastar mucha potencia pura de la máquina en trámites internos.
- **Prioridades**:
   - ✔️ **V**: Separa hábilmente lo crítico del hardware o servidores del montón inútil del software de los usuarios promedios.
   - ❌ **D**: Peligro gravísimo de inanición si a procesos bajos se los ignora perpetuamente frente a los de alta prioridad. *(Se corrige con técnicas de envejecimiento 'Aging'* dando puntos de lástima acorde a la paciencia).

### e. TR y TE (Cálculos de un Proceso Único)
- **TE (Tiempo de Espera)**: Lapso total de los pedacitos fragmentados que el proceso pasó estacionado ociosamente en la "Cola de Listos" anhelando hardware.
   - *Fórmula directa:* `Tiempo de Retorno (TR) - Sumatoria total de ráfagas propias que le tomaron ejecutarse en la CPU`.
- **TR (Tiempo de Retorno)**: Distancia total en tiempo desde el segundo de entrada crudo que entró el programa a la RAM , hasta su defunción terminada.
   - *Fórmula directa:* `Instante de Finalización (Muerte) - Instante de Llegada (Nacimiento)`.

### f. TPE y TPR (Cálculos para Lotes/Sistemas Totales)
- **TPE (Tiempo Promedio de Espera)**: La métrica reina en el rendimiento paralelo. Promedia lo que esperó la masa en total.
   - *Fórmula:* Sumatoria estricta de todos los TEs individuales de cada sistema, dividida por el `Número de Procesos` totales sumados . `(TE1 + TE2 + ..TEn) / N`.
- **TPR (Tiempo Promedio de Retorno)**: Promedia cuánto demora en responder un centro de la computadora dando salida a un paquete nuevo.
   - *Fórmula:* Idénticamente a la anterior, `(TR1 + TR2 + TR3.. TRX) / Número N de procs en lote`.

### g. Tiempo de Respuesta
- Es el tiempo mágico recorrido **desde el mismisimo instante originario** en que el nuevo proceso ingresó virgen a la Cola, hasta el primer microsegundo **donde se le obsequia la CPU `por 1era vez`**. Define que el programa te muestra que "está intentando accionar" o dio el destello UI inicial a un usuario, no que lo finalizó en sí mismo.
