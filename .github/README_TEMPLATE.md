# 📐 Plantilla README para Materias

Este archivo define el **estilo visual y la estructura** que deben seguir los README.md de cada materia del monorepo. Usalo como referencia para que cualquier asistente (o vos mismo) pueda generar un README profesional y consistente.

---

## 🎨 Paleta de Colores por Materia

Cada materia tiene su propia paleta de 3 colores para el gradiente del header/footer y los badges:

| Materia | Color 1 (inicio) | Color 2 (medio) | Color 3 (fin) | Emoji |
|---|---|---|---|---|
| **OO2** | `E67E22` naranja | `E74C3C` rojo | `8E44AD` violeta | 🧩 |
| **AyED** | `1ABC9C` turquesa | `2ECC71` verde | `27AE60` verde oscuro | 🏛️ |
| **FOD** | `3498DB` azul | `2980B9` azul medio | `1F618D` azul oscuro | 🗄️ |
| **ISO** | `F39C12` amarillo | `E67E22` naranja | `D35400` naranja oscuro | 🐧 |

> Para materias nuevas: elegí 3 colores que vayan en gradiente del más claro al más oscuro. Evitá repetir paletas.

---

## 🏗️ Estructura del README

Cada README de materia debe tener las siguientes secciones **en este orden**:

### 1. Header (capsule-render wave)
```html
<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:{COLOR1},50:{COLOR2},100:{COLOR3}&height=200&section=header&text={NOMBRE_MATERIA_URL_ENCODED}&fontSize=38&fontColor=FFFFFF&fontAlignY=35&desc={SUBTITULO_URL_ENCODED}&descSize=16&descAlignY=55&animation=twinkling" />
</p>
```

### 2. Badges (centrados)
```html
<p align="center">
  <img src="https://img.shields.io/badge/Universidad-UNLP-{COLOR3}?style=for-the-badge" />
  <img src="https://img.shields.io/badge/{LENGUAJE}-{EMOJI}-{COLOR2}?style=for-the-badge&logo={LOGO}&logoColor=white" />
  <!-- Agregar más badges según las herramientas de la materia -->
  <img src="https://img.shields.io/badge/Cursando_2026-✅-1ABC9C?style=for-the-badge" />
</p>
```

### 3. Typing SVG (centrado, rotando temas clave)
```html
<p align="center">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=20&pause=1000&color={COLOR2}&center=true&vCenter=true&repeat=true&width=600&height=45&lines={LINEA1_URL_ENCODED};{LINEA2_URL_ENCODED};{LINEA3_URL_ENCODED}" />
</p>
```

### 4. Descripción corta
```markdown
---

Repositorio de estudio personal para la materia **{NOMBRE_COMPLETO}**, correspondiente a la carrera Licenciatura en Sistemas / Analista en TIC (UNLP).  
**Docentes:** {NOMBRES_DOCENTES}
```

### 5. Resúmenes de Teoría (tarjetas individuales por clase/tema)

Cada clase o tema tiene su propia tarjeta `<table>` con:
- **Título clickeable** (link al .md)
- **Blockquote** con descripción breve de una línea
- **Badges de conceptos** en gris oscuro (`2C3E50`) para temas teóricos
- **Badges de técnicas/patrones/herramientas** en el color principal de la materia

```html
<table>
  <tr>
    <td width="900">
      <h3>📄 <a href="Teoria/Resumenes/{ARCHIVO}.md">{TITULO}</a></h3>
      <blockquote>{DESCRIPCION_BREVE}</blockquote>
      <p>
        <img src="https://img.shields.io/badge/{CONCEPTO_1}-2C3E50?style=flat-square" />
        <img src="https://img.shields.io/badge/{CONCEPTO_2}-2C3E50?style=flat-square" />
        <img src="https://img.shields.io/badge/{TECNICA_1}-{COLOR2}?style=flat-square" />
      </p>
    </td>
  </tr>
</table>
```

> ⚠️ **Importante:** Los nombres de los badges NO llevan espacios. Reemplazar espacios con `_` (guión bajo). Ejemplo: `Code_Smells`, `Pull_Up_Method`.

### 6. Prácticas Resueltas (tabla markdown)

```markdown
## 💻 Prácticas Resueltas

| # | Tema | Contenido | Link |
|:-:|---|---|:-:|
| **1** | {TEMA} | {DESCRIPCION_CORTA} | [📁](Practicas/Practica_1/) |
```

### 7. Sección específica de la materia (opcional)

Si la materia tiene algo particular que merezca un mapa visual, agregarlo con Mermaid.
- OO2: Mapa de patrones de diseño
- AyED: Mapa de estructuras de datos
- ISO: Mapa de temas del SO
- FOD: Mapa de tipos de archivos

### 8. Evaluaciones

```markdown
## 📝 Evaluaciones

Material de preparación extra, simulacros y resolución de exámenes pasados.

📁 [Abrir directorio de Evaluaciones](Evaluaciones/)
```

### 9. Stack Tecnológico

```html
## 🛠️ Stack Tecnológico

<p align="center">
  <img src="https://skillicons.dev/icons?i={ICONOS_SEPARADOS_POR_COMA}" height="45" />
</p>

<p align="center">
  <sub><b>{HERRAMIENTA_1}</b> · <b>{HERRAMIENTA_2}</b> · <b>Git & GitHub</b></sub>
</p>
```

### 10. Footer (capsule-render wave + créditos)

```html
<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:{COLOR1},50:{COLOR2},100:{COLOR3}&height=100&section=footer" />
</p>

<p align="center">
  <sub>Repositorio de uso personal y académico · Material de cátedra © sus respectivos autores</sub>
  <br>
  <sub>Hecho con 🧡 por <a href="https://github.com/auwus21">@auwus21</a></sub>
</p>
```

---

## 📏 Reglas Generales

1. **Consistencia:** Todas las materias deben verse igual en estructura, solo cambian colores, contenido y herramientas.
2. **Badges de conceptos teóricos:** Siempre en gris oscuro (`2C3E50`).
3. **Badges de técnicas/herramientas/patrones:** En el color principal de la materia (`COLOR2`).
4. **Links relativos:** Siempre usar paths relativos (`Teoria/Resumenes/...`), nunca absolutos.
5. **Emojis consistentes:** 📄 para archivos de teoría, 📁 para directorios, 📝 para evaluaciones.
6. **Material oficial:** Siempre agregar un link al directorio `Teoria/Material_Original/` al final de la sección de teoría.
7. **No poner `<br>` excesivos:** Máximo un `<br>` entre secciones principales para dar aire visual.

---

## 🚀 Cómo Usarla

Para aplicar esta plantilla a una materia, simplemente decile al asistente:

> *"Modificame el README.md de la materia {X} siguiendo la plantilla que está en `.github/README_TEMPLATE.md`"*

O para una materia nueva:

> *"Creame el README.md para la materia {X} siguiendo la plantilla que está en `.github/README_TEMPLATE.md`"*
