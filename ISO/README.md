<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:F39C12,50:E67E22,100:D35400&height=200&section=header&text=Introducci%C3%B3n%20a%20los%20Sistemas%20Operativos&fontSize=38&fontColor=FFFFFF&fontAlignY=35&desc=Licenciatura%20en%20Sistemas%20%7C%20Analista%20en%20TIC&descSize=16&descAlignY=55&animation=twinkling" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Universidad-UNLP-D35400?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Linux-🐧-E67E22?style=for-the-badge&logo=linux&logoColor=white" />
  <img src="https://img.shields.io/badge/Bash-🖥️-D35400?style=for-the-badge&logo=gnubash&logoColor=white" />
  <img src="https://img.shields.io/badge/Cursando_2026-✅-1ABC9C?style=for-the-badge" />
</p>

<p align="center">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=20&pause=1000&color=E67E22&center=true&vCenter=true&repeat=true&width=600&height=45&lines=Procesos+y+Planificaci%C3%B3n;Administraci%C3%B3n+de+Memoria;Sistemas+de+Archivos+y+E%2FS;Comandos+Linux+y+Shell" />
</p>

---

Repositorio de estudio personal para la materia **Introducción a los Sistemas Operativos (ISO)**, correspondiente a la carrera Licenciatura en Sistemas / Analista en TIC (UNLP).  
**Docentes:** Cátedra de la Facultad de Informática UNLP

<br>

## 📖 Resúmenes de Teoría

Cada resumen incluye explicaciones estructuradas, diagramas extraídos de las diapositivas y conceptos clave del Sistema Operativo. Hacé click en cualquier tema para abrirlo.

<br>

<!-- ═══════════════ TEMA 1 ═══════════════ -->

<table>
  <tr>
    <td width="900">
      <h3>📄 <a href="Teoria/Resumenes/Introduccion_Tema_1.md">Tema 1: Introducción a los Sistemas Operativos</a></h3>
      <blockquote>Definición, perspectivas, componentes, servicios, arquitectura, modos de ejecución y syscalls.</blockquote>
      <p>
        <img src="https://img.shields.io/badge/Definición_de_SO-2C3E50?style=flat-square" />
        <img src="https://img.shields.io/badge/Arquitectura-2C3E50?style=flat-square" />
        <img src="https://img.shields.io/badge/Modos_y_Protección-E67E22?style=flat-square" />
        <img src="https://img.shields.io/badge/Syscalls-E67E22?style=flat-square" />
      </p>
    </td>
  </tr>
</table>

<!-- ═══════════════ PROCESOS ═══════════════ -->

<table>
  <tr>
    <td width="900">
      <h3>📄 <a href="Teoria/Resumenes/Procesos_Tema_2.md">Tema 2: Procesos</a></h3>
      <blockquote>Concepto de proceso, PCB, planificación, schedulers, estados, jerarquía, fork y exec.</blockquote>
      <p>
        <img src="https://img.shields.io/badge/PCB-2C3E50?style=flat-square" />
        <img src="https://img.shields.io/badge/Schedulers-2C3E50?style=flat-square" />
        <img src="https://img.shields.io/badge/Estados-E67E22?style=flat-square" />
        <img src="https://img.shields.io/badge/fork_y_exec-E67E22?style=flat-square" />
      </p>
    </td>
  </tr>
</table>

<!-- ═══════════════ CONTEXT SWITCH ═══════════════ -->

<table>
  <tr>
    <td width="900">
      <h3>📄 <a href="Teoria/Resumenes/Context_Switch_Round_Robin.md">Cambio de Contexto en Round Robin</a></h3>
      <blockquote>Análisis detallado de qué ocurre durante un context switch en un algoritmo Round Robin.</blockquote>
      <p>
        <img src="https://img.shields.io/badge/Context_Switch-2C3E50?style=flat-square" />
        <img src="https://img.shields.io/badge/Round_Robin-E67E22?style=flat-square" />
        <img src="https://img.shields.io/badge/Quantum-E67E22?style=flat-square" />
      </p>
    </td>
  </tr>
</table>

<br>

> 📂 **Material oficial de cátedra (PDFs):** [Abrir directorio](Teoria/Material_Original/)

---

<br>

## 💻 Prácticas Resueltas

| # | Tema | Contenido | Link |
|:-:|---|---|:-:|
| **1** | Comandos Básicos de Linux | GNU/Linux, Shell, File System, procesos, permisos, scripting | [📁](Practicas/Practica_1/) |
| **2** | Planificación y Memoria | Algoritmos de scheduling (FCFS, SJF, RR, Prioridades), TR, TE, TPR, TPE | [📁](Practicas/Practica_2/) |

---

<br>

## 🗺️ Mapa de Temas del SO

```mermaid
graph TD
    SO["🐧 Sistema Operativo"]
    SO --> PROC["⚙️ Procesos"]
    SO --> MEM["🧠 Memoria"]
    SO --> FS["📂 Sistemas de Archivos"]
    SO --> IO["🔌 Entrada / Salida"]

    PROC --> PLAN["Planificación de CPU"]
    PROC --> ESTADOS["Estados y Transiciones"]
    PROC --> SYSCALLS["fork / exec / wait / exit"]

    PLAN --> FCFS["FCFS"]
    PLAN --> SJF["SJF"]
    PLAN --> RR["Round Robin"]
    PLAN --> PRIO["Prioridades"]

    MEM --> PART["Particiones"]
    MEM --> PAG["Paginación"]
    MEM --> SEG["Segmentación"]

    style SO fill:#F39C12,color:#fff
    style PROC fill:#E67E22,color:#fff
    style MEM fill:#E67E22,color:#fff
    style FS fill:#D35400,color:#fff
    style IO fill:#D35400,color:#fff
```

---

<br>

## 📝 Evaluaciones

Material de preparación extra, simulacros y resolución de exámenes pasados.

📁 [Abrir directorio de Evaluaciones](Evaluaciones/)

---

<br>

## 🛠️ Stack Tecnológico

<p align="center">
  <img src="https://skillicons.dev/icons?i=linux,bash,vim,git,github" height="45" />
</p>

<p align="center">
  <sub><b>Linux</b> · <b>Bash</b> · <b>Vim</b> · <b>Git & GitHub</b></sub>
</p>

---

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:F39C12,50:E67E22,100:D35400&height=100&section=footer" />
</p>

<p align="center">
  <sub>Repositorio de uso personal y académico · Material de cátedra © sus respectivos autores</sub>
  <br>
  <sub>Hecho con 🧡 por <a href="https://github.com/auwus21">@auwus21</a></sub>
</p>
