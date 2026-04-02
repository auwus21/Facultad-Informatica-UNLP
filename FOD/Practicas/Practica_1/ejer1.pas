{ Realizar un algoritmo que cree un archivo binario de números enteros no ordenados y permita 
incorporar  datos  al  archivo. Los números son ingresados desde el teclado. La carga finaliza 
cuando se ingresa el número 30000, que no debe incorporarse al archivo. El nombre del archivo 
debe ser proporcionado por el usuario desde el teclado}

program ejercicio1;
type
    archivo_enteros = file of integer;
var
    numeros : integer;
    nombre :String;
    enteros : archivo_enteros;
begin
    writeln('Ingrese el nombre del archivo: ');
    readln(nombre);
    assign(enteros, nombre);
    rewrite(enteros);
    reset(enteros);
    writeln('Ingrese un entero: ');
    read(numeros);
    while(numeros<>30000)do begin
        write(enteros,numeros);
        writeln('Ingrese un entero: ');
        read(numeros);
    end;
    close(enteros);
end.
