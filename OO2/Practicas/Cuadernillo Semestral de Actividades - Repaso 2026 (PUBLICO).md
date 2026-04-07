# Orientación a Objetos 2 

# Cuadernillo Semestral de Actividades

\- Ejercitación preliminar \- 

**Actualizado: 5 de marzo de 2026**

El presente cuadernillo **estará en elaboración** durante el semestre y tendrá un compilado con todos los ejercicios que se usarán durante la asignatura. Se irán agregando ejercicios al final del cuadernillo para poder poner en práctica los contenidos que se van viendo en la materia.

Cada semana les indicaremos cuáles son los ejercicios en los que deberían enfocarse para estar al día y algunos de ellos serán discutidos en la explicación de práctica.

**Recomendación importante:**   
Los contenidos de la materia se incorporan y fijan mejor cuando uno intenta aplicarlos \- no alcanza con ver un ejercicio resuelto por alguien más. Para sacar el máximo provecho de los ejercicios, es importante asistir a las consultas de práctica habiendo intentado resolverlos (tanto como sea posible). De esa manera, las consultas estarán más enfocadas y el docente podrá dar un mejor feedback.

# Ejercicio 1: Red social

Se quiere programar en objetos una versión simplificada de una red social parecida a Twitter. Este servicio debe permitir a los usuarios registrados postear y leer mensajes de hasta 280 caracteres. Ud. debe modelar e implementar parte del sistema donde nos interesa que quede claro lo siguiente:

* Cada usuario conoce todos los Tweets que hizo.  
* Un tweet puede ser re-tweet de otro, y este tweet debe conocer a su tweet de origen.  
* Twitter debe conocer a todos los usuarios del sistema.  
* Los tweets de un usuario se deben eliminar cuando el usuario es eliminado. No existen tweets no referenciados por un usuario.  
* Los usuarios se identifican por su screenName.  
* No se pueden agregar dos usuarios con el mismo screenName.  
* Los tweets deben tener un texto de 1 carácter como mínimo y 280 caracteres como máximo.  
* Un re-tweet no tiene texto adicional.




Tareas:

Su tarea es diseñar y programar en Java lo que sea necesario para ofrecer la funcionalidad antes descrita. Se espera que entregue los siguientes productos.

1. Diagrama de clases UML.  
2. Implementación en Java de la funcionalidad requerida.  
3. Implementar los tests (JUnit) que considere necesarios.  
   

**Nota**: para crear el proyecto Java, lea el material llamado “Trabajando en OO2 con proyectos Maven”.

# Ejercicio 2: Piedra Papel o Tijera

Se quiere programar en objetos una versión del juego Piedra Papel o Tijera. En este juego dos jugadores eligen entre tres opciones: piedra, papel o tijera. La piedra aplasta la tijera, la tijera corta el papel, y el papel envuelve la piedra. Los jugadores eligen una opción y se determina un ganador según las reglas: 

|  | Piedra | Papel | Tijera |
| ----- | ----- | ----- | ----- |
| **Piedra** | Empate | Papel | Piedra |
| **Papel** | Papel | Empate | Tijera |
| **Tijera** | Piedra | Tijera | Empate |

Tareas:

1. Diseñe e implemente una solución a este problema, de forma tal que dadas dos opciones, determine cuál fue la ganadora, o si hubo empate  
2. Se desea extender al juego a una versión más equitativa que integre a lagarto y Spock, con las siguientes reglas:  
   1. Piedra aplasta tijera y aplasta lagarto.  
   2. Papel cubre piedra y desaprueba Spock.  
   3. Tijera corta papel y decapita lagarto.  
   4. Lagarto come papel y envenena Spock.  
   5. Spock rompe tijera y vaporiza piedra.

   ¿Qué cambios se necesitan agregar?

3. Agregue los cambios a la solución anterior.