# 📘 Clase 3: Arquitectura, Tipos y Eliminación de Archivos

**Materia:** Fundamentos de Organización de Datos (FOD) — UNLP 2026  
**Temas:** El viaje de un Byte, Estructuras y Campos Fijos/Variables, Claves y Performance, Bajas y Fragmentación.

---

## 🚀 El Viaje de un Byte

**Motivación:** La memoria RAM es rápida e inmediata para acceder pero cuesta más dinero, siendo escasa y muy volátil. El almacenamiento Secundario (Discos) soluciona los problemas de volatilización pero su acceso es un cuello de botella. ¿Qué sucede, entonces, cuando Pascal usa `Write(ar, 'P')` para escribir datos?

### Participantes del ecosistema de transferencia

| Componente | Rol / Responsabilidad |
|---|---|
| **Sistema Operativo** | Le transfiere el trabajo al "Administrador de archivos". |
| **Administrador de Archivos** | Capa procedimental de S.O. Busca el archivo físico global en la FAT, verifica propiedades y obtiene el número del sector real del disco. Manda instrucciones sobre la dirección. |
| **Buffer de E/S** | Bloque de RAM en tránsito. Administrar buffers significa trabajar con una agrupación grande en RAM y luego transmitir juntos de un golpe en disco para "ganar" tiempo al cuello de botella. |
| **Procesador de E/S** | Hardware muy independiente a la que la CPU delega la gestión de las señales hacia los pines del puerto (transmite a disco liberando la CPU principal). |
| **Controlador de Disco** | Entidad de silicio final responsable de encender cabezales: se *Posiciona en Pista*, *Posiciona en Sector* y *Transfiere* físicamente al cilindro final. |

```mermaid
graph LR
    A[Programa Usuario\nvar='P'] --> B[SysCall al\nSist. Operativo]
    B --> C[Administrador\nde Archivos]
    C --> D[Buffer en RAM\n'.....P....']
    D --> E[Procesador E/S]
    E --> F[Controlador HD\nMovimiento Cabezal]
```

---

## 🏗️ Archivos Estructurados e Identidad de Campos

Un archivo puede ser simplemente una **Secuencia de bytes** (Archivo de texto o sin formato estricto). Sin embargo, necesitamos estructuras definidas que puedan parsearse y recuperarse adecuadamente.

Cuando usamos registros que guardan campos, hay dos grandes mundos según el pre-conocimiento de longitud:

### Longitud Fija (Predecible)

*   Sabemos que un Nombre siempre ocupará 30 caracteres.
*   ✅ Permite calcular rápido la memoria u usar la dirección como un vector gigante para accesos directos.
*   ❌ Desperdicio de espacio (si el nombre es solo "Ana", derrochamos 27 bytes adicionales).

### Longitud Variable (Sin Predictibilidad)

*   Los campos se guardan uno tras otro.
*   No desperdiciamos memoria, ¡pero el límite entre ellos se borronea!

**Tipos comunes de límites de Longitud Variable:**
1.  **Indicador de Longitud:** Al inicio de cada campo se usan un byte o entero escondido marcando cuántos bytes lo siguen (Ej: `05_Pablo`).
2.  **Delimitador:** Un carácter especial de ASCII al final del campo, generalmente ignorado por el dominio (Ej: `Pablo$#@`).
3.  **Archivo Offset:** Un segundo mini-archivo que se guarda en paralelo y posee como apuntadores el byte de inicio exacto del archivo maestro.

En criollo: Si hacemos espacio delimitado es como un casillero fijo. Si hacemos longitud dinámica o variable es como un acordeón; el sistema tiene que leer sobre la marcha e ir deteniéndose cada vez que vea un carácter trampa o un número que él mismo contó al principio.

---

## 🔑 Claves y Performance

Una **Clave** identifica al elemento. Las claves definen qué búsquedas haremos y las estrategias para resolver un archivo.

*   **Clave Única/Primaria:** Identifica a un registro unívoco.
*   **Clave Secundaria:** Atributo para buscar agrupaciones no-unívocas (Ej: "Buscame en todos los empleados a los de 'Neuquén'").
*   **Clave Canónica:** Una clave estandarizada obligatoria de validación. Ej: Si ingreso `aBc de FG`, la aplicamos bajo regla para lograr `ABCDEFG` en disco. Siempre buscaremos contra la clave canónica en el motor.

### Reflexión de Performance en Secuencial vs Directo

| Concepto | Fórmula |
|---|---|
| Búsqueda Promedio Secuencial | O(n) iteraciones en disco (Promedio: N/2 asumiendo un solo match). |
| Búsqueda Directa por Clave Offset | O(1) pero sólo viable en registros de longitud fija (Dirección real en byte = `NRR` * `Tamano del Registro`). |

> *"El acceso directo es preferible sólo cuando se necesitan pocos registros precisos, NO cuando necesitamos la lectura sistemática para una extracción analítica gigante."*

---

## 🗑️ Eliminación de Archivos y Fragmentación

Los sistemas de bases de datos tienen dos formas grandes e históricas de manejar una petición de eliminación:

### Baja Lógica

Las **bajas lógicas** ocurren cuando no se extrae físicamente información almacenada sino que incluimos código invisible en el primer byte del string marcándolo con un carácter trampa o asterísco especial (`*`).

*   ✅ Es sumamente rápido, requiere una sobreescritura cortita.
*   ❌ No nos deshacemos de su espacio real originando derroche de espacio y pérdida de performance si iteramos con frecuencia.

### Baja Física - Compactación

La **baja física** requiere mover información en los discos alterando toda la memoria para destruir todo tipo de rastros. Al originar inestabilidad extrema, la aproximación se realiza clonando un archivo gigante pasandole o "mergenando" únicamente cosas que NO fueron eliminadas y luego pisando o linkeando al clon. Es muy lenta.

### Recuperación de Espacios con Cabeceras Lista/Pilas

Podemos mezclar la técnica de Baja Lógica con re-utilización futura. El sistema mantiene en el **Header** (Registro NRR 0 típicamente, al principio del archivo cabecera) un puntero hacia el primer registro eliminado disponible. Si un registro se vuelve a reusar o crear, aprovechamos esa posición libre (que marcaba otra). Esto forma el arquetipo de una "lista eslabonada" inserta en las propias entrañas del archivo en forma de pila.

## 📊 Problema de Espacio: Fragmentaciones

La longitud variable nos otorga un problema único: al rehusar con Cabeceras, necesitamos ver si el "nuevo dato" CABE en el viejo huequito de la Baja de un registro variable viejo. Si "más o menos" entra o sobra un poco, tendremos la famosa **Fragmentación**.

| Tipo de Fragmentación | Descripción |
|---|---|
| ✅ **Interna** | El sistema derrochó un poco pero el espacio es inservible e internalizado en su casillero (Aplica más para registros de longitud Fija). |
| ❌ **Externa** | Espacio libre desperdiciado en medio del archivo al que no le cabe NADA de casualidad, fragmentando todo inútilmente y volviéndose innacesible. |

### Algoritmos de Resolución (Para Long. Variable)

Al insertar algo y poseer la lista de bajas, podemos utilizar **Ajustes**:

1.  **Primer Ajuste:** Barremos la lista de bajados enlazados y al primer bloque en el que el nuevo registro entre en su tamaño, lo metemos como sea y lo sellamos. Originando de peaje fuerte Fragmentación Interna pero excelente ganancia de tiempo sin buscar más nada.
2.  **Mejor Ajuste:** Barremos TODA la lista disponible buscando aquel espacio libre que se acerque de forma ultra-precisa a los bytes requeridos (se desperdiciará un hilo cortito, y perderemos mucho tiempo en buscar).
3.  **Peor Ajuste:** Agarraremos siempre de forma forzada el segmento de bajado libre más bestialmente inmenso que tenga el archivo entero... para cortar sus partes usables y fraccionar un nuevo bloque vacío independiente y útil. Produce la tan temida **Fragmentación Externa** y nula Interna.

---

## 📚 Recursos y Referencias

- **Cátedra FOD (UNLP):** *"Organización de Datos - Clase 3"*. Semestre 2026.
- Folk-Zoellick: *"Estructuras de Archivos"* (Aspectos teóricos de offset de bajados).
