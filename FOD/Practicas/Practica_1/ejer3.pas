program GestionEmpleados;

type
  empleado = record
    nro: integer;
    apellido: string[25];
    nombre: string[25];
    edad: integer;
    dni: string[10];
  end;

var
  arch: file of empleado;
  e: empleado;
  opcion: integer;
  nombreArchivo, busqueda: string;

procedure crearArchivo;
begin
  write('Ingrese nombre del archivo: ');
  readln(nombreArchivo);
  assign(arch, nombreArchivo);
  rewrite(arch);

  writeln('Ingrese los datos del empleado (apellido "fin" para terminar)');
  write('Apellido: ');
  readln(e.apellido);
  while (e.apellido <> 'fin') do
  begin
    write('Nombre: '); readln(e.nombre);
    write('Número de empleado: '); readln(e.nro);
    write('Edad: '); readln(e.edad);
    write('DNI: '); readln(e.dni);
    write(arch, e);
    writeln('---');
    write('Apellido: ');
    readln(e.apellido);
  end;
  close(arch);
end;

procedure buscarPorNombreApellido;
begin
  write('Ingrese nombre o apellido a buscar: ');
  readln(busqueda);
  assign(arch, nombreArchivo);
  reset(arch);
  while not eof(arch) do
  begin
    read(arch, e);
    if (e.nombre = busqueda) or (e.apellido = busqueda) then
    begin
      writeln('Empleado: ', e.nro, ', ', e.apellido, ', ', e.nombre, ', Edad: ', e.edad, ', DNI: ', e.dni);
    end;
  end;
  close(arch);
end;

procedure listarTodos;
begin
  assign(arch, nombreArchivo);
  reset(arch);
  while not eof(arch) do
  begin
    read(arch, e);
    writeln(e.nro, ' - ', e.apellido, ', ', e.nombre, ' - Edad: ', e.edad, ' - DNI: ', e.dni);
  end;
  close(arch);
end;

procedure listarJubilables;
begin
  assign(arch, nombreArchivo);
  reset(arch);
  while not eof(arch) do
  begin
    read(arch, e);
    if e.edad > 70 then
      writeln('Jubilable: ', e.nro, ' - ', e.apellido, ', ', e.nombre, ' - Edad: ', e.edad);
  end;
  close(arch);
end;

begin
  repeat
    writeln;
    writeln('MENU');
    writeln('1. Crear archivo de empleados');
    writeln('2. Buscar por nombre o apellido');
    writeln('3. Listar todos los empleados');
    writeln('4. Listar empleados mayores de 70 años');
    writeln('0. Salir');
    write('Opción: ');
    readln(opcion);

    case opcion of
      1: crearArchivo;
      2: buscarPorNombreApellido;
      3: listarTodos;
      4: listarJubilables;
    end;
  until opcion = 0;
end.