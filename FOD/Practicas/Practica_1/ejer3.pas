{Realizar un programa que presente un menú con opciones para: 

a.  Crear un archivo binario de registros no ordenados de empleados y completarlo con 
datos ingresados desde teclado. De cada empleado se registra: número de empleado, 
apellido, nombre, edad y DNI. Algunos empleados pueden ingresan el DNI con valor 0, lo 
que significa que al momento de la carga puede no tenerlo. La carga finaliza cuando se 

ingresa el String ‘fin’ como apellido. 
b.  Abrir el archivo anteriormente generado y 
i.  Listar  en  pantalla  los  datos  de  empleados  que  tengan  un  nombre  o  apellido 
determinado, el cual se proporciona desde el teclado. 
ii.  Listar en pantalla los empleados de a uno por línea.  
iii.  Listar en pantalla los empleados mayores de 70 años, próximos a jubilarse. 
NOTA: El nombre del archivo a crear o utilizar debe ser proporcionado por el usuario.}

program ejer3;

type
    empleado = record
        id : integer;
        apellido : string;
        nombre : string;
        edad : integer;
        dni:integer;
    end;
    archivo_empleados = file of empleado;

procedure  cargarEmpleados(var arch:archivo_empleados);
var
    emp:empleado;
begin
    rewrite(arch);
    reset(arch);
    writeln('Ingrese el apellido: ');
    readln(emp.apellido);
    while (emp.apellido<>'fin') do begin
        writeln('Ingrese el ID del empleado: ');
        readln(emp.id);
        writeln('Ingrese el nombre: ');
        readln(emp.nombre);
        writeln('Ingrese el edad: ');
        readln(emp.edad);
        writeln('Ingrese el D.N.I: ');
        readln(emp.dni);
        writeln('---------------------------------');
        writeln('Ingrese el apellido: ');
        readln(emp.apellido);
    end;
    write(arch,emp);
end;

var
  nombre_fisico: string;
  opcion: integer;
  apellido: string;
  arch_empleados: archivo_empleados;

begin
  writeln('Ingrese el nombre del archivo: ');
  readln(nombre_fisico);
  
  assign(arch_empleados, nombre_fisico);

  repeat
    writeln;
    writeln('----- Menu Interactivo -----');
    writeln('Seleccione una Opcion');
    writeln('1 : Crear Archivo Binario');
    writeln('2 : Leer Archivo Binario');
    writeln('0 : Salir Del menu');
    writeln('----------------------------');
    readln(opcion);
    

    case opcion of
      1: cargarEmpleados(arch_empleados);
      
      2: 
        begin
          writeln;
          writeln('----- Leer Archivo Binario -----');
          writeln('1 : Listar Por Apellido ');
          writeln('2 : Listar Empleados ');
          writeln('3 : Listar Empleados A Jubilarse');
          writeln('----------------------------');
          readln(opcion);
          
          case opcion of 
            1: 
              begin
                writeln('Ingrese un Apellido: ');
                readln(apellido);
                //imprimirPorApellido(arch_empleados, apellido);
              end;
            2: write('test 2') ; //listarEmpleados(arch_empleados);
            3: write('test 3'); //listarAJubilarse(arch_empleados);
          else 
            writeln('Opcion invalida del submenu. Volviendo al menu principal...');
          end;
          
        end;
        
      0: writeln('Cerrando el programa... ¡Exitos!');
      
    else 
      writeln('Error: Ingrese una opcion valida (1, 2 o 0 para salir).');
    end;

  until opcion = 0; 

end.