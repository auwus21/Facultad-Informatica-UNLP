package ar.edu.unlp.info.oo2.todoitem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoItemTest {
    private ToDoItem item;

    @BeforeEach
    void setUp() {
        item = new ToDoItem("Implementar patrón State");
    }

    @Test
    void testEstadoInicialPending() {
        assertEquals("Implementar patrón State", item.getName());
        assertTrue(item.getComments().isEmpty());

        // Verificar que en Pending, workedTime() lanza error
        RuntimeException exceptionWorkedTime = assertThrows(RuntimeException.class, () -> {
            item.workedTime();
        });
        assertEquals("El objeto No se a Iniciado", exceptionWorkedTime.getMessage());

        // Verificar que en Pending, togglePause() lanza error
        RuntimeException exceptionPause = assertThrows(RuntimeException.class, () -> {
            item.togglePause();
        });
        assertEquals("El objeto ToDoItem no se encuentra en pause o in-progress", exceptionPause.getMessage());

        // Verificar que se pueden agregar comentarios
        item.addComment("Primer comentario");
        assertEquals(1, item.getComments().size());
        assertEquals("Primer comentario", item.getComments().get(0));

        // Verificar que finish() en Pending no hace transicionar ni iniciar
        item.finish();
        assertThrows(RuntimeException.class, () -> item.workedTime());
    }

    @Test
    void testStartTransition() {
        item.start();
        
        // No debería lanzar error al consultar el tiempo trabajado
        Duration time = item.workedTime();
        assertNotNull(time);
        assertFalse(time.isNegative());

        // Verificar que agregar comentario en InProgress funciona
        item.addComment("En progreso");
        assertEquals(1, item.getComments().size());
    }

    @Test
    void testTogglePause() {
        item.start();
        
        // De InProgress a Paused
        item.togglePause();
        // Debería permitir consultar el tiempo trabajado
        assertNotNull(item.workedTime());

        // De Paused a InProgress
        item.togglePause();
        assertNotNull(item.workedTime());
    }

    @Test
    void testFinishFromInProgress() throws InterruptedException {
        item.start();
        Thread.sleep(10); // Dormir 10ms para asegurar transcurso de tiempo
        item.finish();

        Duration worked = item.workedTime();
        assertTrue(worked.toMillis() >= 10);

        // Ya finalizado, no debería agregar más comentarios
        item.addComment("Comentario tardío");
        assertTrue(item.getComments().isEmpty()); // Debería seguir vacío (el anterior test no agregó comentarios)

        // Ya finalizado, togglePause lanza error
        assertThrows(RuntimeException.class, () -> item.togglePause());
    }

    @Test
    void testFinishFromPaused() throws InterruptedException {
        item.start();
        Thread.sleep(5);
        item.togglePause(); // Pausado
        Thread.sleep(5);
        item.finish(); // Finalizado desde pausa

        Duration worked = item.workedTime();
        assertTrue(worked.toMillis() >= 10);
    }
}
