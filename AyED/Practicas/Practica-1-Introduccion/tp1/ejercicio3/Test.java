package tp1.ejercicio3;

public class Test {
	
	public static void main(String[] args) {
	
	Estudiante[] estudiantes = new Estudiante[2];
	Profesor[] profesores = new Profesor[3];
	
	

	estudiantes[0] = new Estudiante();
	estudiantes[1] = new Estudiante();
	
	profesores[0] = new Profesor();
	profesores[1] = new Profesor();
	profesores[2] = new Profesor();

	estudiantes[0].setNombre("Agustin");
	estudiantes[0].setApellido("Olthoff");
	estudiantes[0].setComision("1A");
	estudiantes[0].setEmail("agustin@gmail.com");
	estudiantes[0].setDireccion("Calle 1");
	
	estudiantes[1].setNombre("Pedro");
	estudiantes[1].setApellido("Perez");
	estudiantes[1].setComision("1B");
	estudiantes[1].setEmail("pedro@gmail.com");
	estudiantes[1].setDireccion("Calle 2");
	
	profesores[0].setNombre("Juan");
	profesores[0].setApellido("Perez");
	profesores[0].setEmail("juan@gmail.com");
	profesores[0].setCatedra("1A") ;
	profesores[0].setFacultad("UNLP");
	
	profesores[1].setNombre("Jose");
	profesores[1].setApellido("Paz");
	profesores[1].setEmail("josen@gmail.com");
	profesores[1].setCatedra("1B") ;
	profesores[1].setFacultad("UNLP");
	
	profesores[2].setNombre("Maria");
	profesores[2].setApellido("Gomez");
	profesores[2].setEmail("maria@gmail.com");
	profesores[2].setCatedra("3A") ;
	profesores[2].setFacultad("UCALP");

	
	System.out.println("--------Imprimir Estudiantes--------");
	for(int i=0;i<2;i++) {
		System.out.println("---Estudiantes "+(i+1)+" ---");
		System.out.println(estudiantes[i].tusDatos());
	}
	System.out.println("--------Imprimir Profesores--------");
	for(int i=0;i<3;i++) {
		System.out.println("---Profesor "+(i+1)+" ---");
		System.out.println(profesores[i].tusDatos());
	}
}
}
