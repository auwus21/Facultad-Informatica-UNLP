<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:1B4F72,50:2874A6,100:148F77&height=200&section=header&text=Programación%20Concurrente&fontSize=38&fontColor=FFFFFF&fontAlignY=35&desc=Sincronización%20·%20Memoria%20Compartida%20·%20Pasaje%20de%20Mensajes&descSize=16&descAlignY=55&animation=twinkling" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Universidad-UNLP-1B4F72?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Memoria_Compartida-🧠-2874A6?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Pasaje_de_Mensajes-✉️-148F77?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Cursando_2026-✅-1ABC9C?style=for-the-badge" />
</p>

<p align="center">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=20&pause=1000&color=2874A6&center=true&vCenter=true&repeat=true&width=620&height=45&lines=Concurrencia+y+Sincronizaci%C3%B3n;Sem%C3%A1foros+%7C+Monitores+%7C+Locks;Pasaje+de+Mensajes+Sincr%C3%B3nico+y+Asincr%C3%B3nico;Productor-Consumidor+%7C+Lectores-Escritores+%7C+Fil%C3%B3sofos" />
</p>

---

Repositorio de estudio personal para la materia **Programación Concurrente (PC)**, correspondiente al 3er año (2do semestre) de la carrera Licenciatura en Sistemas / Licenciatura en Informática / Analista en TIC (UNLP).  
**Docentes:** Cátedra de la Facultad de Informática UNLP

<br>

## 📖 Resúmenes de Teoría (Organizados por Ejes Temáticos)

Cada resumen incluye explicaciones detalladas, algoritmos de sincronización, pseudocódigo formal y trazas de ejecución.

### 🧠 Eje 1: Memoria Compartida (Shared Memory)
| Unidad | Tema Principal | Conceptos Clave | Link |
|:---:|---|---|:---:|
| **1** | Fundamentos de Concurrencia y Atomicidad | Concurrencia vs Paralelismo · UMA/NUMA · Sentencias Guardadas · Atomicidad de Grano Fino · ASV · Notación `⟨await⟩` · Safety & Liveness · Fairness | [📄](Teoria/Resumenes/Clase1_Fundamentos_Concurrencia.md) |
| **2** | Semáforos | Primitivas `P()` (wait) y `V()` (signal) · Semáforos Generales y Binarios (Mutex) · Sincronización y Barreras | 📄 *Próximamente* |
| **3** | Monitores | Encapsulamiento · Variables de Condición (`wait`, `signal`, `signal_all`) · Semánticas de Señalización (Hoare vs Mesa/Java) | 📄 *Próximamente* |

### ✉️ Eje 2: Pasaje de Mensajes (Message Passing / Memoria Distribuida)
| Unidad | Tema Principal | Conceptos Clave | Link |
|:---:|---|---|:---:|
| **4** | Mensajes Asincrónicos (PMA) | Canales y Mailboxes · `send()` no bloqueante y `receive()` bloqueante · Topologías y protocolos | 📄 *Próximamente* |
| **5** | Mensajes Sincrónicos (PMS) | Comunicación directa y simétrica/asimétrica · Rendezvous · Sentencias selectivas con guarda (`select` / `alt`) | 📄 *Próximamente* |
| **6** | Invocación Remota (RPC / Rendezvous Avanzado) | Llamada a Procedimiento Remoto (RPC) · Modelos Cliente-Servidor distribuidos | 📄 *Próximamente* |

### 🧩 Eje 3: Problemas Clásicos de Concurrencia
| Problema | Descripción | Técnicas de Solución | Link |
|:---:|---|---|:---:|
| **Productor - Consumidor** | Acceso coordinado a un buffer acotado (*bounded buffer*) | Semáforos contadores · Monitores con variables de condición · Canales de mensajes | 📄 *Próximamente* |
| **Lectores - Escritores** | Acceso concurrente a base de datos / recursos compartidos | Prioridad a Lectores vs Prioridad a Escritores · Exclusión mutua selectiva | 📄 *Próximamente* |
| **Filósofos Comensales** | Manejo de recursos múltiples y prevención de interbloqueos | Prevención de Deadlock · Rompimiento de simetría · Asignación atómica de recursos | 📄 *Próximamente* |
| **Barbero Dormilón** | Coordinación entre clientes y servidor con sala de espera | Semáforos de sincronización y conteo de turnos | 📄 *Próximamente* |

<br>

> 📂 **Material oficial de cátedra (PDFs originales):** [Abrir directorio](Teoria/Material_Original/)

---

<br>

## 💻 Prácticas Resueltas

| # | Tema | Herramientas / Lenguajes | Link |
|:-:|---|---|:-:|
| **1** | Variables Compartidas y Algoritmos de Exclusión Mutua | Pseudocódigo / Pascal-FC / C | [📁](Practicas/Practica_1/) |
| **2** | Semáforos | Pascal-FC / C (POSIX Threads / Semaphores) | [📁](Practicas/Practica_2/) |
| **3** | Monitores | Pascal-FC / Java (synchronized, ReentrantLock) | [📁](Practicas/Practica_3/) |
| **4** | Pasaje de Mensajes Asincrónico (PMA) | PMS/PMA Pseudocódigo | [📁](Practicas/Practica_4/) |
| **5** | Pasaje de Mensajes Sincrónico (PMS) y Rendezvous | CSP / Ada | [📁](Practicas/Practica_5/) |

---

<br>

## 🗺️ Mapa Conceptual de Concurrencia

```mermaid
graph TD
    PC["⚡ Programación Concurrente"]
    
    PC --> MC["🧠 Memoria Compartida"]
    PC --> PM["✉️ Pasaje de Mensajes"]

    MC --> EM["Exclusión Mutua"]
    EM --> HW["Hardware / Atómicas (Test&Set)"]
    EM --> SEM["Semáforos (P / V)"]
    EM --> MON["Monitores (CondVars)"]

    PM --> PMA["Asincrónico (Mailboxes / Canales)"]
    PM --> PMS["Sincrónico (Rendezvous / CSP)"]
    PM --> RPC["RPC (Remote Procedure Call)"]

    SEM --> PROB["🧩 Problemas Clásicos"]
    MON --> PROB
    PMA --> PROB
    PMS --> PROB

    PROB --> PC1["Productor - Consumidor"]
    PROB --> LE["Lectores - Escritores"]
    PROB --> FC["Filósofos Comensales"]
    PROB --> BD["Barbero Dormilón"]

    style PC fill:#1B4F72,stroke:#154360,color:#fff
    style MC fill:#2874A6,stroke:#1B4F72,color:#fff
    style PM fill:#148F77,stroke:#117A65,color:#fff
    style SEM fill:#2980B9,stroke:#1F618D,color:#fff
    style MON fill:#2980B9,stroke:#1F618D,color:#fff
    style PMA fill:#16A085,stroke:#117A65,color:#fff
    style PMS fill:#16A085,stroke:#117A65,color:#fff
    style PROB fill:#E67E22,stroke:#D35400,color:#fff
```

---

<br>

## 📝 Evaluaciones

Material de preparación, exámenes parciales y finales anteriores resueltos.

📁 [Abrir directorio de Evaluaciones](Evaluaciones/)

---

<br>

## 🛠️ Stack Tecnológico

<p align="center">
  <img src="https://skillicons.dev/icons?i=c,cpp,java,linux,git,github" height="45" />
</p>

<p align="center">
  <sub><b>C / Pthreads</b> · <b>Java Concurrency</b> · <b>POSIX</b> · <b>Linux</b> · <b>Git & GitHub</b></sub>
</p>

---

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:1B4F72,50:2874A6,100:148F77&height=100&section=footer" />
</p>

<p align="center">
  <sub>Repositorio de uso personal y académico · Material de cátedra © sus respectivos autores</sub>
  <br>
  <sub>Hecho con 💙 por <a href="https://github.com/auwus21">@auwus21</a></sub>
</p>
