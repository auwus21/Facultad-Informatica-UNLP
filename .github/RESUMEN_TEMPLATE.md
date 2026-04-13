# 📐 Plantilla para Resúmenes de Teoría (PDFs → Markdown)

Este archivo define las **instrucciones, reglas de formato y estilo** que debe seguir cualquier resumen de clase teórica generado a partir de PDFs de cátedra.

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
- Seguir las reglas de formato y estructura definidas abajo.
- El archivo se guarda en `{MATERIA}/Teoria/Resumenes/{Nombre}.md`

### Paso 4: Actualizar el README de la materia
- Agregar una nueva tarjeta (card) en la sección de teoría del `{MATERIA}/README.md`
- Seguir el formato definido en `.github/README_TEMPLATE.md`

---

## 📝 Estructura del Resumen

Todo resumen debe tener exactamente esta estructura:

### 1. Header (siempre igual)

```markdown
# 📘 Clase {N}: {Título Descriptivo}

**Materia:** {Nombre Completo} ({Sigla}) — UNLP 2026  
**Docente:** {Nombre del Docente}  
**Temas:** {Lista de temas separados por coma}

---
```

### 2. Cuerpo del Resumen

El cuerpo se divide en **Partes** (con `# Parte X: Título`) si hay múltiples temas grandes, o directamente en secciones `##` si el tema es uno solo.

### 3. Cierre (referencias)

```markdown
---

## 📚 Recursos y Referencias del PDF

- **{Autor}:** *"{Título}"* — {Editorial}. {Año}.
- {Links relevantes mencionados en el PDF}
```

---

## 🎨 Reglas de Formato

### Encabezados y Jerarquía

| Nivel | Uso | Ejemplo |
|---|---|---|
| `#` | Título del documento / Partes principales | `# Parte A: Introducción` |
| `##` | Secciones dentro de una parte | `## 🎯 Propósito` |
| `###` | Subsecciones y ejemplos | `### Situación Inicial` |
| `####` | Técnicas individuales (refactorings, métodos) | `#### Extract Method` |

### Emojis para Secciones (usar consistentemente)

| Emoji | Uso |
|---|---|
| 📘 | Título principal del documento |
| 🎯 | Propósito / Intención de un patrón o concepto |
| 📦 | Ejemplo concreto del PDF |
| 🏗️ | Estructura genérica de un patrón |
| 👥 | Participantes de un patrón |
| ✅ | Consecuencias (pros y contras) |
| 🔗 | Relaciones entre conceptos o patrones |
| 🧠 | Tips para estudiar / recordar |
| 📚 | Referencias bibliográficas |
| 📊 | Tablas comparativas / clasificaciones |
| 👃 | Code Smells |
| 🏛️ | Contexto histórico |

### Separadores

- Usar `---` para separar secciones de igual nivel.
- Usar `---` doble (`---\n---`) para separar **Partes** principales (temas grandes independientes).

---

## 📊 Elementos Visuales Obligatorios

### Tablas
Usar tablas para **cualquier** información que tenga estructura comparativa:
- Participantes de un patrón
- Pros y contras
- Clasificaciones
- Comparaciones entre conceptos
- Listas de técnicas con sus motivaciones

```markdown
| Concepto | Descripción |
|---|---|
| **Nombre** | Explicación breve |
```

### Diagramas UML (Mermaid)
Obligatorios cuando el PDF presenta un diagrama de clases. Usar `classDiagram` de Mermaid:

```markdown
​```mermaid
classDiagram
    class NombreClase {
        <<abstract>>
        -atributoPrivado: Tipo
        +metodoPublico(): Tipo
        +metodoAbstracto()* Tipo
    }
    ClasePadre <|-- ClaseHija
    ClaseA --> ClaseB : relación
​```
```

**Reglas para diagramas Mermaid:**
- Siempre incluir atributos y métodos relevantes.
- Usar `<<abstract>>` para clases abstractas.
- Usar `*` para indicar métodos abstractos.
- Usar `#` para métodos protegidos.
- Incluir cardinalidades cuando sean relevantes (`"*"`, `"1"`, `"2"`).
- Etiquetar las relaciones cuando aporten claridad.

### Bloques de Código
Obligatorios cuando el PDF presenta código. Siempre con el lenguaje especificado:

```markdown
​```java
// Código extraído del PDF
public class Ejemplo {
    // ...
}
​```
```

**Reglas para código:**
- Incluir **todo** el código relevante del PDF, no resumirlo.
- Mantener la indentación correcta (4 espacios).
- Agregar comentarios inline (`//`) cuando ayuden a la comprensión.
- Si el PDF muestra un "antes y después", incluir ambos.

### Blockquotes
Usar para citas textuales del PDF o definiciones formales:

```markdown
> *"Cita textual del PDF o del autor referenciado"*  
> — Autor, Año
```

---

## 🗣️ Estilo de Escritura

### Tono
- **Técnico pero accesible.** Como si le explicaras a un compañero de cursada.
- Usar **"En criollo:"** antes de una explicación informal de un concepto formal.
- Usar **negritas** para resaltar conceptos clave dentro de los párrafos.
- Usar *itálicas* solo para nombres de libros, términos en inglés, y citas.

### Explicaciones "en criollo"
Después de una definición formal o una cita textual, agregar siempre una explicación informal:

```markdown
> **Definición formal:** "Refactoring es una transformación que preserva el comportamiento..."

En criollo: si el código funciona igual después del cambio, fue un refactoring. Si no, rompiste algo.
```

### Bullet Points para Mecánicas y Pasos
Cuando el PDF describe pasos de una mecánica, usar **listas numeradas**:

```markdown
**Mecánica:**
1. Crear un nuevo método cuyo nombre explique su propósito.
2. Copiar el código a extraer al nuevo método.
3. Compilar y testear.
```

### Consecuencias y Pros/Contras
Usar tablas con ✅ y ❌:

```markdown
| | Descripción |
|---|---|
| ✅ | Ventaja o consecuencia positiva. |
| ❌ | Desventaja o consecuencia negativa. |
```

---

## 🧩 Estructura por Tipo de Contenido

### Para Patrones de Diseño (GoF)

Cada patrón debe documentarse con esta estructura exacta:

```markdown
## 🎯 Propósito
> **Definición formal del GoF en blockquote.**

**Aplicabilidad:** Cuándo usarlo (en bullet points).

En criollo: {explicación informal}

---

## 📦 Ejemplo del PDF: {Nombre del Ejemplo}

### Situación Inicial
{Código o descripción del problema}

### El Problema
{Por qué la solución actual no sirve}

### La Solución: Patrón {Nombre}
{Código refactorizado + diagrama Mermaid}

---

## 🏗️ Estructura Genérica del Patrón
{Diagrama Mermaid con nombres genéricos: AbstractClass, ConcreteClass, etc.}

## 👥 Participantes
{Tabla con cada participante y su responsabilidad}

---

## ✅ Consecuencias
{Tabla con ✅ y ❌}
```

### Para Refactorings

Cada refactoring debe documentarse así:

```markdown
#### {Nombre del Refactoring}
**Motivación:** {Por qué se aplica}

**Precondiciones:**
1. {Condición 1}
2. {Condición 2}

**Mecánica:**
1. {Paso 1}
2. {Paso 2}
...
N. Compilar y testear.
```

### Para Code Smells

```markdown
### {Nombre en Español} (*{Nombre en Inglés}*)
- {Descripción del problema}
- **Problema:** {Por qué es malo}
- **Refactorings sugeridos:** {Lista de refactorings que lo resuelven}
```

### Para Conceptos Teóricos Generales

```markdown
## 🧠 {Nombre del Concepto}

{Explicación del concepto con negritas en términos clave}

> *"Cita textual del PDF si la hay"*

{Tabla, diagrama o lista según corresponda}
```

---

## ⚠️ Reglas Estrictas

1. **NO inventar contenido.** Todo debe salir del PDF. Si algo no está claro, marcarlo con `[?]`.
2. **NO omitir código.** Si el PDF tiene código, el resumen lo tiene.
3. **NO omitir diagramas.** Si el PDF tiene un diagrama UML, el resumen lo tiene en Mermaid.
4. **NO usar heading `#` para más de un título.** El `#` es solo para el título del documento y para las Partes principales.
5. **Incluir TODOS los ejemplos del PDF.** Cada ejemplo concreto debe tener su sección `### Ejemplo del PDF:`.
6. **Mantener las referencias.** Si el PDF cita libros, papers o URLs, incluirlos al final.
7. **Ser exhaustivo.** Un buen resumen es aquel donde no necesitás volver a abrir el PDF. Alguien que lea solo el resumen debería poder entender y estudiar todo el tema.
8. **URLs sin tildes ni caracteres especiales.** Si un texto va dentro de una URL (como typing SVG), reemplazar caracteres con tilde por su versión sin tilde.

---

## 📏 Largo Esperado

| Tipo de PDF | Largo esperado del resumen |
|---|---|
| PDF corto (< 15 slides) | 100-200 líneas |
| PDF medio (15-30 slides) | 200-350 líneas |
| PDF largo (> 30 slides) | 350-500 líneas |
| PDF con mucho código | +50-100 líneas extras por los bloques de código |

> El resumen debe ser **completo, no resumido**. La idea es que reemplace al PDF para estudiar, no que sea un índice del PDF.
