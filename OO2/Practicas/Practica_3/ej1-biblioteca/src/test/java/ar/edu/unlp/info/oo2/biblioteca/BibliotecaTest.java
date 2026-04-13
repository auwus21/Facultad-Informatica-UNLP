package ar.edu.unlp.info.oo2.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {
	
	private Biblioteca biblioteca;
	private Socio arya;
	private Socio tyron;

	@BeforeEach
	void setUp() {
		biblioteca = new Biblioteca();
		arya = new Socio("Arya Stark", "needle@stark.com", "5234-5");
		tyron = new Socio("Tyron Lannister", "tyron@thelannisters.com", "2345-2");
	}

	@Test
	void testExportarBibliotecaVacia() {	
		assertEquals("[]", biblioteca.exportarSocios());
	}

	@Test
	void testExportarUnSocio() {
		biblioteca.agregarSocio(arya);
		
		String separator = System.lineSeparator();
		String expectedOutput = "[" + separator +
				"\t{" + separator +
				"\t\t\"nombre\": \"Arya Stark\"," + separator +
				"\t\t\"email\": \"needle@stark.com\"," + separator +
				"\t\t\"legajo\": \"5234-5\"" + separator +
				"\t}" + separator +
				"]";
				
		assertEquals(expectedOutput, biblioteca.exportarSocios());
	}

	@Test
	void testExportarMultiplesSocios() {
		biblioteca.agregarSocio(arya);
		biblioteca.agregarSocio(tyron);

		String separator = System.lineSeparator();
		String expectedOutput = "[" + separator +
				"\t{" + separator +
				"\t\t\"nombre\": \"Arya Stark\"," + separator +
				"\t\t\"email\": \"needle@stark.com\"," + separator +
				"\t\t\"legajo\": \"5234-5\"" + separator +
				"\t}," + separator +
				"\t{" + separator +
				"\t\t\"nombre\": \"Tyron Lannister\"," + separator +
				"\t\t\"email\": \"tyron@thelannisters.com\"," + separator +
				"\t\t\"legajo\": \"2345-2\"" + separator +
				"\t}" + separator +
				"]";

		assertEquals(expectedOutput, biblioteca.exportarSocios());
	}
}
