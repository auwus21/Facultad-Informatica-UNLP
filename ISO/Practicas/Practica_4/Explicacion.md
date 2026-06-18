# 📖 Explicación Teórico-Práctica: Práctica 4 (E/S - Discos e I-Nodos)

Este documento resume los conceptos clave de las diapositivas de explicación para la **Práctica 4** de Introducción a los Sistemas Operativos (ISO).

---

<details>
<summary><b>🔌 1. Administración de E/S — Discos Rígidos (HDD)</b></summary>

## 🏗️ Organización Física de un HDD
Un disco rígido magnético convencional está constituido por:
*   **Platos:** Discos metálicos magnéticos apilados.
*   **Caras:** Lados útiles de los platos donde se graban los datos (2 caras por plato).
*   **Pistas:** Anillos concéntricos en la superficie de cada cara.
*   **Cilindros:** Conjunto de todas las pistas que ocupan la misma posición vertical en todas las caras (las $N$-ésimas pistas).
*   **Sectores:** Subdivisiones de una pista. Es la unidad física mínima de almacenamiento en disco (habitualmente 512 bytes o 4 KB).

### 📊 Capacidad de un HDD (Fórmula)
La capacidad de almacenamiento de un disco rígido está determinada por el producto de sus componentes:
$$\text{Capacidad Total} = \text{Caras} \times \text{Pistas por Cara} \times \text{Sectores por Pista} \times \text{Tamaño de Sector}$$

---

## ⏱️ Tiempos de Acceso a un HDD
Para acceder a un sector físico y realizar una operación de lectura/escritura, se consumen tres tiempos sucesivos:

1.  **Seek Time (Tiempo de Posicionamiento - $T_{Seek}$):** Tiempo requerido para mover mecánicamente el cabezal de lectura hasta situarse sobre el cilindro/pista correspondiente.
2.  **Latency Time (Tiempo de Latencia - $T_{Lat}$):** Tiempo que tarda el disco en girar sobre su eje hasta que el sector deseado pase por debajo del cabezal.
    *   *Nota:* Si no se especifica el tiempo exacto de latencia, se calcula estadísticamente como el tiempo que tarda el disco en dar **media vuelta** ($0.5$ vueltas).
3.  **Transfer Time (Tiempo de Transferencia - $T_{Trans}$):** Tiempo necesario para transmitir físicamente los datos desde el sector del disco hacia la memoria RAM o viceversa, dependiendo de la velocidad del bus/interfaz.

$$\text{Tiempo de Acceso} = T_{Seek} + T_{Lat} + T_{Trans}$$

### 🔄 Fórmulas de Ocupación Temporal en Transferencias
*   **Almacenamiento Secuencial (Contiguo):** El cabezal se posiciona una sola vez y lee los bloques consecutivos:
    $$\text{Tiempo Total} = T_{Seek} + T_{Lat} + (T_{Trans\_Bloque} \times \text{Cantidad de Bloques})$$
*   **Almacenamiento Aleatorio (Disperso):** El cabezal debe buscar y posicionarse por cada bloque individual:
    $$\text{Tiempo Total} = (T_{Seek} + T_{Lat} + T_{Trans\_Bloque}) \times \text{Cantidad de Bloques}$$

---

## 📝 Ejercicios Resueltos de Ejemplo

### 🧠 Ejercicio 1: Cálculo de Capacidad
*   **Enunciado:** Supongamos un disco con 6 platos (12 caras útiles), 1500 pistas por cara y 700 sectores por pista de 256 bytes cada uno.
*   **Cálculo:**
    $$\text{Tamaño} = 12 \text{ caras} \times 1500 \text{ pistas} \times 700 \text{ sectores} \times 256 \text{ bytes}$$
    $$\text{Tamaño} = 3.225.600.000 \text{ bytes} \approx 3,004 \text{ GiB}$$

### 🧠 Ejercicio 2: Tiempo de Acceso (Latencia por RPM)
*   **Enunciado:** Disco de $12600 \text{ RPM}$ (Revoluciones Por Minuto), con $T_{Seek} = 2 \text{ ms}$ y velocidad de transferencia de $15 \text{ Mib/s}$ ($15 \times 2^{20} \text{ bits/s}$). Queremos calcular el tiempo para transferir $4500$ sectores contiguos de $256 \text{ bytes}$ cada uno.
*   **Cálculo:**
    1.  **Latencia ($T_{Lat}$):** 
        $12600 \text{ vueltas} \rightarrow 60 \text{ segundos} = 60000 \text{ ms}$  
        $1 \text{ vuelta} \approx 4,76 \text{ ms} \quad \rightarrow \quad T_{Lat} (\text{media vuelta}) = 2,38 \text{ ms}$
    2.  **Tiempo de Transferencia ($T_{Trans}$):**
        Velocidad: $15 \text{ Mib/s} = 15.728.640 \text{ bits/s}$  
        Tamaño a transferir: $4500 \text{ sectores} \times 256 \text{ bytes} \times 8 \text{ bits/byte} = 9.216.000 \text{ bits}$  
        $$\text{Tiempo Trans} = \frac{9.216.000 \text{ bits}}{15.728.640 \text{ bits/s}} \approx 0,586 \text{ s} = 586 \text{ ms}$$
    3.  **Tiempo de Acceso Total (Secuencial):**
        $$\text{Tiempo Total} = T_{Seek} + T_{Lat} + T_{Trans} = 2 \text{ ms} + 2,38 \text{ ms} + 586 \text{ ms} = 590,38 \text{ ms}$$

</details>

<br>

<details>
<summary><b>💾 2. Repaso Teórico-Práctico de I-Nodos (UNIX)</b></summary>

## 📌 ¿Qué es un I-Nodo (Index Node)?
Es una estructura auxiliar utilizada en sistemas UNIX que almacena la metainformación de un archivo (permisos, dueño, tiempos, tamaño) y punteros a los bloques de datos donde reside su contenido físico. 
*   **Identificador:** Cada archivo se asocia a un número único de i-nodo.
*   **¡Regla Clave!** El nombre del archivo **no está** en el i-nodo; se almacena en el bloque de datos del directorio asociado.

> ⚠️ **Espacio libre vs. I-Nodos libres:** Un disco puede quedarse sin espacio físico para datos y estar lleno, pero también puede quedarse sin I-Nodos libres (por crear miles de archivos vacíos). En este último caso, el disco tiene megabytes libres de datos pero **no permite crear ningún archivo nuevo** porque no hay estructuras de control disponibles.

---

## 🏗️ Direccionamiento en I-Nodos (Cálculos de Archivo Máximo)
Los inodos utilizan una estructura de punteros indexados para referenciar bloques físicos en disco.

*   **Direccionamiento Directo (DD):** El puntero del i-nodo apunta directo al bloque de datos.
*   **Direccionamiento Indirecto Simple (DIS):** El puntero apunta a un bloque de disco que contiene **direcciones** a otros bloques de datos.
*   **Direccionamiento Indirecto Doble (DID):** El puntero apunta a un bloque de direcciones, donde cada dirección apunta a su vez a otro bloque de direcciones, y estos últimos finalmente apuntan a bloques de datos.

### 🧮 Ejemplo de Ejercicio de Cátedra:
*   **Configuración base:**
    *   Tamaño de bloque: $2 \text{ KiB} = 2048 \text{ bytes}$.
    *   Tamaño de dirección de bloque: $64 \text{ bits} = 8 \text{ bytes}$.
    *   Estructura del I-Nodo: 2 punteros directos (DD), 1 indirecto simple (DIS) y 1 indirecto doble (DID).

*   **Cálculo 1: Cantidad de direcciones por bloque:**
    $$\text{Direcciones por Bloque} = \frac{\text{Tamaño de Bloque}}{\text{Tamaño de Dirección}} = \frac{2048 \text{ bytes}}{8 \text{ bytes}} = 256 \text{ direcciones}$$

*   **Cálculo 2: Capacidad máxima del direccionamiento:**
    *   **Punteros Directos (DD):** 2 punteros $\rightarrow$ mapean $2 \text{ bloques de datos}$.
    *   **Puntero Indirecto Simple (DIS):** 1 puntero $\rightarrow$ apunta a 1 bloque con 256 direcciones $\rightarrow$ mapea $256 \text{ bloques de datos}$.
    *   **Puntero Indirecto Doble (DID):** 1 puntero $\rightarrow$ apunta a 1 bloque con 256 direcciones, cada una apunta a otro bloque con 256 direcciones $\rightarrow$ mapea $256 \times 256 = 65.536 \text{ bloques de datos}$.

*   **Cálculo 3: Cantidad total de bloques direccionados:**
    $$\text{Bloques Totales} = 2 (\text{DD}) + 256 (\text{DIS}) + 65.536 (\text{DID}) = 65.794 \text{ bloques}$$

*   **Cálculo 4: Tamaño Máximo de Archivo Soportado:**
    $$\text{Tamaño Máximo} = 65.794 \text{ bloques} \times 2 \text{ KiB/bloque} = 131.588 \text{ KiB} \approx 128,5 \text{ MiB}$$

</details>
