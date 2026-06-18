# 📖 Explicación Teórico-Práctica: Práctica 2 (Discos, Usuarios y Planificación de CPU)

Este documento resume los conceptos clave de las diapositivas de explicación para la **Práctica 2** de Introducción a los Sistemas Operativos (ISO).

---

<details>
<summary><b>💾 1. Configuración de Discos, Usuarios y Permisos</b></summary>

## 📂 Nomenclatura de Discos en GNU/Linux
Históricamente, los discos se nombraban según su interfaz física:
*   **Discos IDE (obsoletos):** 
    *   `/dev/hda`: Master en el primer bus IDE.
    *   `/dev/hdb`: Slave en el primer bus IDE.
    *   `/dev/hdc`: Master en el segundo bus IDE.
    *   `/dev/hdd`: Slave en el segundo bus IDE.
*   **Discos SCSI / SATA / USB:**
    *   Se basan en buses seriales: `/dev/sda`, `/dev/sdb`, `/dev/sdc`...
    *   *Nota:* En distribuciones modernas (Debian/Squeeze en adelante), todos los discos (incluidos los IDE) adoptaron la nomenclatura `sdX` gestionada por `udev`.

### 🗂️ Numeración de Particiones:
*   **Particiones Primarias:** Numeradas estrictamente del **1 al 4** (solo una de ellas se marca como activa/booteable).
*   **Particiones Lógicas:** Contenidas dentro de una partición extendida, se numeran estrictamente a partir del **5 en adelante** (ej: `/dev/sda5`).

### 🏷️ Persistent Device Naming (udev)
El demonio `udev` gestiona los archivos dentro de `/dev` dinámicamente según el hardware conectado. Para evitar que un disco cambie de nombre entre reinicios, se usan:
*   **UUID (Universal Unique Identifier):** Un identificador único de la partición (ej: `/dev/disk/by-uuid/2d781b26...`).
*   **Labels (Etiquetas):** Nombres asignados manualmente al formatear (ej: `/dev/disk/by-label/data`).

---

## 👥 Administración de Usuarios y Grupos
La información de las cuentas reside en tres archivos principales de configuración:
1.  `/etc/passwd`: Almacena información pública de usuarios (nombre de usuario, UID, GID, home directory, y shell preferida).
2.  `/etc/shadow`: Almacena las contraseñas cifradas y políticas de expiración (solo accesible por root).
3.  `/etc/group`: Almacena la definición de los grupos y sus miembros.

### 🛠️ Comandos de Administración:
*   `useradd -m <usuario>`: Crea una cuenta de usuario y su carpeta `/home/<usuario>` (equivalente moderno: `adduser`).
*   `passwd <usuario>`: Asigna o cambia la contraseña (modifica `/etc/shadow`).
*   `usermod`: Modifica propiedades (ej: `-g` cambia grupo primario, `-G` agrega a grupos secundarios).
*   `userdel -r <usuario>`: Elimina la cuenta y borra recursivamente su directorio personal.
*   `groupdel <grupo>`: Elimina un grupo.

---

## 🔐 Modos de Permisos (Representación Octal)
Los permisos sobre archivos y directorios se estructuran para el **Dueño (U)**, el **Grupo (G)** y **Otros (O)**. Se calculan sumando los siguientes valores de base 8:
*   **Read (R):** valor **4**
*   **Write (W):** valor **2**
*   **Execute (X):** valor **1**

*Ejemplo:* Permisos `rw-r-x---` se traducen como:
*   Dueño: R + W (4 + 2) = **6**
*   Grupo: R + X (4 + 1) = **5**
*   Otros: Ninguno (0) = **0**
*   Comando: `chmod 650 archivo`

</details>

<br>

<details>
<summary><b>⚙️ 2. Planificación de CPU (Scheduling)</b></summary>

El Planificador de CPU decide qué proceso en estado **Listo (Ready)** toma el control del procesador.

## ⏱️ Tiempos de los Procesos (Fórmulas Clave)
*   **Tiempo de CPU ($T_{CPU}$):** Tiempo total que el proceso requiere ejecutarse directamente en la CPU.
*   **Tiempo de Retorno ($TR$):** Tiempo total que pasa desde que el proceso arriba al sistema hasta que finaliza por completo.
    $$TR = T_{Fin} - T_{Llegada}$$
*   **Tiempo de Espera ($TE$):** Tiempo que el proceso pasa en la cola de listos esperando por la CPU sin ejecutar.
    $$TE = TR - T_{CPU}$$
*   **Promedios ($TPR$ y $TPE$):** Suma de los tiempos individuales dividida la cantidad de procesos.
    $$TPR = \frac{\sum TR}{N}, \quad TPE = \frac{\sum TE}{N}$$

---

## 🚦 Algoritmos de Planificación

### 1️⃣ FIFO / FCFS (First In, First Out)
*   No apropiativo. Atiende estrictamente por orden de llegada.
*   *Desventaja:* Padece de **Efecto Convoy** (procesos largos de CPU bloquean a procesos cortos que llegaron un instante después).

### 2️⃣ SJF (Shortest Job First)
*   No apropiativo. Selecciona el proceso con la ráfaga de CPU más corta de la cola de listos.
*   *Desventaja:* Puede causar **Inanición (Starvation)** en procesos de larga duración si llegan constantemente procesos cortos.

### 3️⃣ SRTF (Shortest Remaining Time First)
*   **Apropiativo (Preemptive)**. Versión apropiativa de SJF.
*   Si llega un proceso con un tiempo restante de CPU menor que el que se está ejecutando, el SO suspende al actual y le otorga la CPU al nuevo.
*   Favorece fuertemente a procesos I/O Bound.

### 4️⃣ Round Robin (RR)
*   Apropiativo. Basado en turnos de tiempo fijo denominados **Quantum (Q)**.
*   **Timer Variable:** Cuando un proceso toma la CPU, el contador se inicializa en $Q$. Si consume su $Q$ antes de finalizar, es expulsado al final de la cola de listos y se selecciona al siguiente (FIFO circular).
*   *Quantum Chico:* Demasiado cambio de contexto (*overhead*).
*   *Quantum Grande:* Se comporta exactamente como un FIFO.

### 5️⃣ Planificación por Prioridades
*   Asigna la CPU al proceso con mayor prioridad (menor valor numérico = mayor prioridad).
*   Puede ser apropiativo o no apropiativo.
*   *Inanición:* Procesos de baja prioridad pueden quedarse sin CPU indefinidamente.
*   *Solución:* **Envejecimiento (Aging)**, donde la prioridad del proceso aumenta a medida que pasa tiempo esperando en la cola.

---

## ⚡ Planificación con Operaciones de E/S Concurrentes
Los procesos realizan ciclos alternados de **CPU + E/S**.
*   Se asume que la E/S se realiza de manera independiente de la CPU (por DMA o controladoras dedicadas), permitiendo que un proceso use la CPU mientras otro realiza E/S de forma simultánea.
*   **Criterios de desempate en colas de listos:** 
    1. Orden de arribo temporal a la cola.
    2. Menor número de PID.

---

## ⛓️ Colas Multinivel con Retroalimentación (MLQ)
La cola de listos se subdivide en varias colas con prioridades distintas y diferentes algoritmos:
*   **Planificador Horizontal:** El algoritmo que corre dentro de una misma cola (ej: RR en la cola superior, FIFO en la inferior).
*   **Planificador Vertical:** El algoritmo que decide qué cola atender (ej: prioridad absoluta de la cola superior sobre las inferiores).
*   **Retroalimentación:** Un proceso puede cambiar de cola según su comportamiento:
    *   *Ejemplo clásico:* Un proceso entra a $Q_0$ (RR, q=8). Si consume todo su quantum sin terminar, desciende a $Q_1$ (RR, q=16). Si vuelve a agotar su tiempo, desciende a $Q_2$ (FIFO). Esto beneficia a procesos cortos e interactivos (I/O bound) y penaliza a los procesos de cálculo pesado (CPU bound).

</details>
