package ar.edu.unlp.info.oo2.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BibliotecaTest {
	
	private Biblioteca biblioteca;
	private Socio arya;
	private Socio tyron;

	@BeforeEach
	void setUp() {
		biblioteca = new Biblioteca();
		arya = new Socio("Arya Stark", "needle@stark.com", "5234-5");
		tyron = new Socio("Tyron Lannister", "tyron@thelannisters.com",  "2345-2");
	}
	
	@Test
	void testExportarBibliotecaVacia() {
		assertEquals("[]",biblioteca.exportarSocios());
	}
	
	@Test
	void testExportarUnSocio() {
		biblioteca.agregarSocio(arya);
	    String esperado = "[{\"nombre\":\"Arya Stark\",\"email\":\"needle@stark.com\",\"legajo\":\"5234-5\"}]";
	    assertEquals(esperado, biblioteca.exportarSocios());

		
	}

	
}
