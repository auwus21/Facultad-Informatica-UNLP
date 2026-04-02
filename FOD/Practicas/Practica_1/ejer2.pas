{Realizar un algoritmo, que utilizando el archivo de números enteros no ordenados creado en el 
ejercicio 1, informe por pantalla cantidad de números menores a 15000 y el promedio de los 
números ingresados. El nombre del archivo a procesar debe ser proporcionado por el usuario 
una única vez.  Además, el algoritmo deberá listar el contenido del archivo en pantalla. Resolver 
el ejercicio realizando un único recorrido del archivo}

program ejer2;

type
    archivo_enteros = file of integer;

var
    nombre_fisico : String;
    arch_enteros : archivo_enteros;
    num : integer;
    promedio: real;
    cant_menores : integer;
    cant_num: integer;
begin
    cant_menores := 0;
    promedio := 0;
    cant_num := 0;
    writeln('Ingrese el nombre del Archivo');
    readln(nombre_fisico);
    assign(arch_enteros,nombre_fisico);
    reset(arch_enteros);
    while not eof(arch_enteros) do begin
        read(arch_enteros,num);
        writeln(num);
        if (num < 15000) then begin
            cant_menores:= cant_menores +1;
        end;
        cant_num:= cant_num +1;
        promedio:= promedio + num;
    end;
    close(arch_enteros);
    promedio:= promedio / cant_num;
    writeln('La cantidad de Ingresados son: ',cant_num);
    writeln('El promedio de los numeros son: ',promedio:0:2);
end.
