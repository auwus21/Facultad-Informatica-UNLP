# Clase 1: Introducción a Refactoring

## Evolución del Software
Diseñar un buen software la primera vez es extremadamente difícil. A medida que los requerimientos cambian, la calidad interna del sistema inevitablemente se degrada progresivamente si no trabajamos para evitarlo.

### 📜 Las Leyes de Lehman (Evolución de Sistemas)
*   **Continuing Change (Cambio Continuo):** Los sistemas deben adaptarse continuamente a su contexto cambiante o se volverán insatisfactorios.
*   **Continuing Growth (Crecimiento Continuo):** La funcionalidad y componentes siempre se incrementan para mantener la satisfacción del cliente a lo largo del tiempo.
*   **Increasing Complexity (Complejidad Creciente):** A medida que un sistema evoluciona, su complejidad se incrementa a menos que se trabaje activamente en solucionarlo.
*   **Declining Quality (Calidad Menguante):** La calidad interna declinará a menos que se aplique un mantenimiento riguroso preventivo/correctivo.

## ♻️ ¿Qué es el Refactoring?
> **Refactoring (Sustantivo):** Es una transformación que se realiza en la estructura interna del código para hacerlo más fácil de entender y más barato de modificar, pero que **preserva el comportamiento observable**.

**Reglas de Oro:**
- Si tras los cambios el sistema falla, no compila o no pasan los tests de unidad correspondientes, **NO fue un refactoring**. Es fundamental tener tests funcionando de antemano.
- Los beneficios y metas de esto implican eliminar duplicaciones, simplificar la lógica muy compleja, clarificar el código y hacer predecible al sistema para facilitar la detección de bugs.

## 👃 Bad Smells (Malos Olores o "Code Smells")
Son síntomas en el código (indicios en la superficie) de que existe un problema profundo de diseño.
*   **Código Duplicado (*Duplicated Code*):** La misma porción de instrucciones/lógica en varios lugares. Dificulta de manera muy obvia los cambios (un bug-fix debe necesariamente propagarse como copia a todas las réplicas).
*   **Clase Grande (*Large Class*):** Una clase intenta tener responsabilidades de más y hacer demasiado, con variables y métodos excesivamente amontonados (violando el principio de cohesión).
*   **Método Largo (*Long Method*):** Acumula mucha lógica en decenas de líneas, ocultando intenciones e impidiendo modularizar el flujo.
*   **Envidia de Atributo (*Feature Envy*):** Un método que se pasa constantemente consultando datos a otra clase diferente a la suya para poder realizar un trabajo u operación.
*   **Sentencias Condicionales (*Switch Statements*):** Estructuras en cadena `if/else` preguntando manualmente por el tipo, nombre u origen del objeto en lugar de aprovechar de primera mano las ventajas del polimorfismo.
*   **Clase de Datos (*Data Class*):** Clases u objetos huecos que solo tienen y aglomeran `getters/setters` pasivos y carecen de comportamientos reales.
*   **Extensa lista de parámetros (*Long Parameter List*):** Métodos que toman interminables colas de atributos, haciendo pesada la recolección, envío y comprensión de los requisitos.

### Ejemplo Evolutivo: Desglose y Generalización 
Si dos ramas, clases o partes paralelas en un mismo estrato tienen lógica calcada que se solapa (por ejemplo, `HotelStay` y `CarRental` definiendo por duplicado atributos y calculos calcados como la duración, fechas y un método de costo fijo): debemos **"empujarla hacia arriba" (*Pull Up*)** concentrándola un escalafón más arriba en su superclase común (*e.g., abstract* `Product`).
*   **Pull Up Field:** Subimos el atributo duplicado y sus asignaciones a `protected` en la superclase.
*   **Pull Up Method:** Subimos el método compartiendo la misma firma y sintaxis exacta sin necesidad de rescribirlo todas las veces.
