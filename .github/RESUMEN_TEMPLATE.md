# 📐 Plantilla para Resúmenes de Teoría (PDFs → Markdown)

Este archivo define las **instrucciones, reglas de formato y estilo** que debe seguir cualquier resumen de clase teórica generado a partir de PDFs de cátedra, para **cualquier materia** del monorepo.

---

## 🚀 Cómo Usarla

Para generar un resumen nuevo, decile al asistente:

> *"Haceme el resumen de {MATERIA} clase {N} siguiendo la plantilla de `.github/RESUMEN_TEMPLATE.md`"*

Para regenerar uno existente:

> *"Rehaceme el resumen de {ARCHIVO} siguiendo las instrucciones de `.github/RESUMEN_TEMPLATE.md`"*

---

## 📋 Flujo de Trabajo

### Paso 1: Extraer el texto del PDF
- Usar `pypdf` (Python) para extraer el contenido del PDF a un `.txt` temporal.
- Comando: `python3 -c "from pypdf import PdfReader; r=PdfReader('{ruta}'); [print(p.extract_text()) for p in r.pages]" > /tmp/output.txt`

### Paso 2: Leer el contenido completo
- Leer el `.txt` generado para entender TODO el contenido antes de empezar a escribir.
- Identificar la estructura lógica: temas, subtemas, ejemplos, código, diagramas.

### Paso 3: Escribir el resumen
- Seguir las reglas de formato y estructura definidas en este documento.
- Guardar en: `{MATERIA}/Teoria/Resumenes/{Nombre}.md`

### Paso 4: Actualizar el README de la materia
- Agregar una nueva tarjeta (card) en la sección de teoría del `{MATERIA}/README.md`
- Seguir el formato definido en `.github/README_TEMPLATE.md`

---

## 📝 Estructura del Resumen

Todo resumen debe tener exactamente esta estructura:

### 1. Header (siempre igual)

```markdown
# 📘 {Título Descriptivo del Tema o Clase}

**Materia:** {Nombre Completo} ({Sigla}) — UNLP 2026  
**Temas:** {Lista de temas separados por coma}

---
```

### 2. Cuerpo del Resumen

El cuerpo se divide en **Partes** (con `# Parte X: Título`) si hay múltiples temas grandes, o directamente en secciones `##` si el tema es uno solo.

### 3. Cierre (referencias)

```markdown
---

## 📚 Recursos y Referencias

- **{Autor}:** *"{Título}"* — {Editorial}. {Año}.
- {Links relevantes mencionados en el PDF}
```

---

## 🎨 Reglas de Formato

### Encabezados y Jerarquía

| Nivel | Uso | Ejemplo |
|---|---|---|
| `#` | Título del documento / Partes principales | `# Parte A: Introducción` |
| `##` | Secciones dentro de una parte | `## 🎯 Definición` |
| `###` | Subsecciones y ejemplos | `### Situación Inicial` |
| `####` | Elementos individuales dentro de una lista o categoría | `#### Tipo A` |

### Emojis para Secciones (usar consistentemente)

| Emoji | Uso |
|---|---|
| 📘 | Título principal del documento |
| 🎯 | Propósito / Definición central de un concepto |
| 📦 | Ejemplo concreto extraído del PDF |
| 🏗️ | Estructura, arquitectura o esquema general |
| 👥 | Participantes, roles o actores |
| ✅ | Ventajas, consecuencias positivas o comparaciones |
| 🔗 | Relaciones entre conceptos |
| 🧠 | Tips para estudiar / cosas a recordar |
| 📚 | Referencias bibliográficas |
| 📊 | Tablas comparativas / clasificaciones |
| 🏛️ | Contexto histórico |
| ⚙️ | Mecánica, procedimiento o pasos a seguir |
| 💡 | Observaciones importantes o insights |

### Separadores

- Usar `---` para separar secciones del mismo nivel.
- Usar `---` doble (`---\n---`) para separar **Partes** principales (temas grandes independientes).

---

## 📊 Elementos Visuales Obligatorios

### Tablas
Usar tablas para **cualquier** información que tenga estructura comparativa o listable:
- Clasificaciones y categorías
- Comparaciones entre conceptos, técnicas o herramientas
- Pros y contras
- Listas con nombre + descripción
- Pasos con sus efectos

```markdown
| Concepto | Descripción |
|---|---|
| **Nombre** | Explicación breve |
```

### Diagramas (Mermaid)
Obligatorios cuando el PDF presenta un diagrama visual (de clases, de flujo, de estados, de secuencia, etc.). Elegir el tipo de diagrama Mermaid que mejor represente el contenido:

- **`classDiagram`** → Para jerarquías de clases, relaciones entre entidades.
- **`graph TD/LR`** → Para flujos de procesos, pipelines, árboles de decisión.
- **`sequenceDiagram`** → Para interacciones paso a paso entre componentes.
- **`stateDiagram-v2`** → Para máquinas de estados, ciclos de vida.
- **`gantt`** → Para líneas de tiempo, cronogramas.
- **`pie`** → Para distribuciones porcentuales.

```markdown
​```mermaid
graph TD
    A[Paso 1] --> B[Paso 2]
    B --> C{¿Condición?}
    C -->|Sí| D[Resultado A]
    C -->|No| E[Resultado B]
​```
```

**Reglas para diagramas:**
- Incluir labels descriptivos en cada nodo.
- Etiquetar las relaciones/flechas cuando aporten claridad.
- Usar estilos (`style`) para resaltar elementos importantes si es necesario.

### Bloques de Código
Obligatorios cuando el PDF presenta código, comandos, scripts o configuraciones. Siempre con el lenguaje especificado:

```markdown
​```{lenguaje}
// Código extraído del PDF
​```
```

**Lenguajes comunes:** `java`, `python`, `bash`, `pascal`, `c`, `sql`, `pseudocode`, `text`

**Reglas para código:**
- Incluir **todo** el código relevante del PDF, no resumirlo.
- Mantener la indentación correcta.
- Agregar comentarios inline cuando ayuden a la comprensión.
- Si el PDF muestra un "antes y después", incluir ambos bloques.

### Blockquotes
Usar para citas textuales del PDF, definiciones formales, o frases destacadas:

```markdown
> *"Cita textual del PDF o del autor referenciado"*  
> — Autor, Año
```

---

## 🗣️ Estilo de Escritura

### Tono
- **Técnico pero accesible.** Como si le explicaras a un compañero de cursada.
- Usar **"En criollo:"** antes de una explicación informal de un concepto teórico denso.
- Usar **negritas** para resaltar conceptos clave dentro de los párrafos.
- Usar *itálicas* solo para nombres de libros, términos en otro idioma, y citas textuales.

### Explicaciones "en criollo"
Después de una definición formal, agregar siempre una explicación informal y directa:

```markdown
> **Definición formal del PDF**

En criollo: {explicación simple que cualquier estudiante entienda rápido}
```

### Pasos y Procedimientos
Cuando el PDF describe pasos ordenados, usar **listas numeradas**:

```markdown
**Procedimiento:**
1. Primer paso.
2. Segundo paso.
3. Tercer paso.
```

### Pros y Contras / Ventajas y Desventajas
Usar tablas con ✅ y ❌:

```markdown
| | Descripción |
|---|---|
| ✅ | Ventaja o aspecto positivo. |
| ❌ | Desventaja o aspecto negativo. |
```

---

## 🧩 Templates por Tipo de Contenido

Dependiendo de la materia y el tema, usar el template que mejor aplique:

### Template A: Concepto Teórico General

```markdown
## 🎯 {Nombre del Concepto}

{Explicación del concepto con negritas en términos clave}

> *"Cita textual del PDF si la hay"*

En criollo: {explicación informal}

{Tabla, diagrama o lista según corresponda}
```

### Template B: Procedimiento / Algoritmo / Técnica

```markdown
## ⚙️ {Nombre de la Técnica}

**Motivación / ¿Cuándo se usa?:** {Contexto del problema}

**Precondiciones:** (si aplica)
1. {Condición 1}
2. {Condición 2}

**Pasos:**
1. {Paso 1}
2. {Paso 2}
...

**Ejemplo:**
​```{lenguaje}
// Código o pseudocódigo del ejemplo
​```
```

### Template C: Comparación / Clasificación

```markdown
## 📊 {Nombre de la Clasificación}

| Categoría | Descripción | Ejemplos |
|---|---|---|
| **Tipo A** | {Descripción} | {Ejemplos} |
| **Tipo B** | {Descripción} | {Ejemplos} |
```

### Template D: Ejemplo Concreto del PDF

```markdown
## 📦 Ejemplo: {Nombre del Ejemplo}

### Situación Inicial
{Descripción o código del estado inicial}

### El Problema
{Qué falla o por qué no es suficiente}

### La Solución
{Código, diagrama o explicación de la solución}

### Resultado
{Estado final, diagrama o conclusión}
```

### Template E: Estructura / Arquitectura / Modelo

```markdown
## 🏗️ {Nombre de la Estructura}

{Descripción breve de qué representa}

​```mermaid
{Diagrama apropiado}
​```

### Componentes

| Componente | Rol / Responsabilidad |
|---|---|
| **{Nombre}** | {Descripción} |
```

---

## ⚠️ Reglas Estrictas

1. **NO inventar contenido.** Todo debe salir del PDF. Si algo no está claro, marcarlo con `[?]`.
2. **NO omitir código.** Si el PDF tiene código, el resumen lo tiene.
3. **NO omitir diagramas.** Si el PDF tiene un diagrama, el resumen lo tiene en Mermaid.
4. **NO usar heading `#` para más de un título.** El `#` es solo para el título del documento y para las Partes principales.
5. **Incluir TODOS los ejemplos del PDF.** Cada ejemplo concreto debe tener su propia sección.
6. **Mantener las referencias.** Si el PDF cita libros, papers o URLs, incluirlos al final.
7. **Ser exhaustivo.** Un buen resumen es aquel donde **no necesitás volver a abrir el PDF**. Alguien que lea solo el resumen debería poder entender y estudiar todo el tema.
8. **URLs sin tildes ni caracteres especiales.** Si un texto va dentro de una URL (como typing SVG o badges), reemplazar caracteres con tilde por su versión sin tilde.
9. **Genérico.** Esta plantilla aplica para CUALQUIER materia: OO2, ISO, FOD, AyED, etc. Adaptar los templates al dominio del PDF, no al revés.

---

## 📏 Largo Esperado

| Tipo de PDF | Largo esperado del resumen |
|---|---|
| PDF corto (< 15 slides) | 100-200 líneas |
| PDF medio (15-30 slides) | 200-350 líneas |
| PDF largo (> 30 slides) | 350-500 líneas |
| PDF con mucho código/diagramas | +50-100 líneas extras |

> **Principio rector:** El resumen debe ser **completo, no resumido**. La idea es que **reemplace al PDF para estudiar**, no que sea un índice del PDF.
