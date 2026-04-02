# 📚 Fundamentos de Organización de Datos (FOD) - Clase 2

> **Resumen de Algoritmia Clásica con Archivos** 🚀  
> Este apunte está diseñado para que entiendas la lógica de cada operación **paso a paso**. Aquí repasamos cómo manipular archivos de Pascal mediante ejemplos prácticos extraídos de la materia.

La algoritmia clásica asume que **físicamente no podemos cargar todo un archivo en memoria**. Por eso se procesan registro a registro, controlando punteros.

---

## 1️⃣ Agregar Datos a un Archivo Existente (Ejemplo 4)
Este es el caso más sencillo. Tomamos un archivo que ya tiene datos y simplemente queremos agregar al final nuevos registros.

### 💡 Lógica explicada fácil:
En vez de machacar los datos que tenemos al inicio (`rewrite(archivo)`), tenemos que:
1. Abrir para lectura/escritura con `reset`.
2. Mover el puntero explícitamente al final del archivo usando `seek()`.
3. Pedir datos y escribirlos.

```pascal
Procedure agregar (Var Emp: Empleados); 
var 
    E: registro;
begin
    reset(Emp); // Abre el archivo existente
    seek(Emp, filesize(Emp)); // Salta hasta la posición final (donde está eof)
    
    leerDato(E); // Función que carga el registro desde teclado 
    while E.nombre <> ' ' do begin
        write(Emp, E); // Graba el dato agregado
        leerDato(E); 
    end;
    
    close(Emp);
end;
```

---

## 2️⃣ Actualización Maestro - Detalle
Se usa cuando tenés un archivo principal (**Maestro**) con la info general (ej. Catálogo de productos y stock), y recibís novedades en otro archivo (**Detalle**) que indican cómo cambiar al maestro (ej. Ventas del día).

> ⚠️ **REGLA DE ORO:** Ambos archivos deben estar **ordenados** por el mismo campo clave (ej. CódigoProducto). Y en el Detalle solo aparecen cosas que ya existen en el Maestro.

### Variante A: 1 Maestro con 1 Detalle (SIN repetición) (Ejemplo 5)
Cada registro del Maestro, a lo sumo, tiene una modificación única en el Detalle. (Ejemplo: le sumamos horas trabajadas).

**La lógica:** Como están ordenados secuencialmente, avanzamos en ambos a la par hasta encontrar el mismo código. Hacemos la suma, rebobinamos el maestro y grabamos directo.

```pascal
reset(mae); reset(det);
while not eof(det) do begin
    read(det, reg_det);
    read(mae, reg_mae); // Trae info del maestro actual
    
    // Mientras no alcancemos en el Maestro al que nos pide el Detalle... lo salteamos
    while (reg_mae.codigo <> reg_det.codigo) do
        read(mae, reg_mae);
        
    // Cuando lo encontramos (salen del while): actualizamos
    reg_mae.horas := reg_mae.horas + reg_det.horas;
    
    // 🔴 OJO ACÁ: Volvemos un paso atrás en el maestro porque el `read(mae)` adelantó el puntero
    seek(mae, filepos(mae)-1);
    write(mae, reg_mae); // Pisamos el viejo registro con el nuevo total
end;
```

### Variante B: 1 Maestro con 1 Detalle (CON repetición) (Ejemplo 6)
Ahora un mismo producto se pudo vender varias veces en el día. El Detalle tiene varios registros del mismo código, y **NO** podemos grabarlo en el maestro hasta sumar el total primero.

**La lógica:** Hacemos lo mismo hasta que los códigos coincidan. Cuando coinciden, aplicamos un "micro corte de control": seguimos leyendo del detalle mientras el código no cambie, acumulando. Cuando cambia, RECIÉN AHÍ rebobinamos y grabamos en el maestro.

```pascal
// (Aparece el concepto de ValorAlto (='9999') para no romper el algoritmo cuando un archivo se queda sin datos).
// Asumimos un procedimiento leer(archivo_detalle, registro_detalle) que asigna '9999' en el eof.

leer(det, reg_det); 
while (reg_det.codigo <> valorAlto) do begin
    read(mae, reg_mae);
    
    while (reg_mae.codigo <> reg_det.codigo) do 
        read(mae, reg_mae);
    
    // Acá viene el corte temporal (por código):
    while (reg_mae.codigo = reg_det.codigo) do begin
        reg_mae.stock := reg_mae.stock - reg_det.cantVendida; // Restamos stock
        leer(det, reg_det); // Avanza SOLO el detalle
    end;
    
    // Terminé de descontar TODO lo de este producto en el detalle. Guardo final.
    seek(mae, filepos(mae)-1);
    write(mae, reg_mae);
end;
```

### Variante C: 1 Maestro con N Detalles (Ejemplo 7)
Generalizamos el problema anterior. Por ejemplo: actualizar un sistema con las ventas que mandan 3 o más sucursales distintas al final del día.

**La lógica:** Ya no consultamos un solo archivo detalle, ahora comparamos varios detalles a la vez mediante una función vital: **MÍNIMO**.

¿Qué hace la función Mínimo? 
Mira a los registros actuales que están asomando en los 3 detalles, se fija cuál es el *más chico* de todos (ya que están ordenados), te devuelve ESE registro, y avanza la lectura únicamente del archivo al que le robamos ese registro.

```pascal
// Pseudo función del Maestro actualizado a partir de la magia de "minimo"
leer(det1, r1); leer(det2, r2); leer(det3, r3); // Sacamos la punta del ovillo a todos

minimo(r1, r2, r3, min); // La función mínimo determina quién es el más picante y avanza ese archivo

while (min.codigo <> valorAlto) do begin
    read(mae, reg_mae);
    while (reg_mae.codigo <> min.codigo) do
        read(mae, reg_mae);
        
    while (reg_mae.codigo = min.codigo) do begin // Corte de control con el min
        reg_mae.stock := reg_mae.stock - min.cantVendida;
        minimo(r1, r2, r3, min); // Vuelve a traer el más chico disponible
    end;
    
    seek(mae, filepos(mae)-1);
    write(mae, reg_mae);
end;
```

---

## 3️⃣ Corte de Control (Ejemplo 8)
Se usa para imprimir reportes agrupando subtotales jerárquicos (Ejemplo: Provincias -> Partidos -> Ciudades). 

> ⚠️ **Requisito vital:** El archivo tiene que estar ORDENADO por las claves del nivel exterior al interior. (es decir, todas las info de una provincia junta, adentro los partidos juntos, etc).

### 💡 Lógica explicada fácil:
Recorremos desde lo más general (Afuera: Provincia) hasta lo más particular (Adentro: Datos puros). Definimos "Viejos/Anteriores" guardando en una variable la provincia o partido para ir comparando iteración a iteración. Cuando ya no coinciden, "Corta" ✂️ e imprimimos el total acumulado y ponemos el acumulador a 0.

```pascal
leer(Censo, R);
Total_General := 0;

while (R.provincia <> valorAlto) do begin
    ProvActual := R.provincia;
    Total_Prov := 0;

    // Corte a nivel Provincia:
    while (ProvActual = R.provincia) do begin
        PartActual := R.partido;
        Total_Part := 0;
        
        // Corte a nivel Partido:
        while (ProvActual = R.provincia) and (PartActual = R.partido) do begin // Chequeamos todo el árbol viejo
            writeLn(R.ciudad, ' - Varones: ', R.cant_v, ' - Mujeres: ', R.cant_m);
            // Acumulamos en el nivel más interno:
            Total_Part := Total_Part + R.cant_v + R.cant_m; 
            leer(Censo, R); 
        end;

        writeln(' Total del partido: ', Total_Part);
        Total_Prov := Total_Prov + Total_Part; // Subimos el acumulado de jerarquía
    end;

    writeln('--- Total Provincia: ', Total_Prov, ' ---');
    Total_General := Total_General + Total_Prov; 
end;
```

---

## 4️⃣ Merge (Fusión de Archivos)
Tomamos múltiples archivos que tienen estructura idéntica para combinarlos en un archivo definitivo 0km nuevo (`rewrite(nuevo_maestro)`). 

La magia aquí también recae en la valiosa función **MÍNIMO** que vimos en maestro detalle. Como todos los detalles están ordenados igual, vamos agarrando el más chiquito, y lo tiramos adentro del nuevo archivo para que nazca ordenado.

### Variante A: Merge sin repetición y con repetición (Ejemplos 9 y 10)
Funciona idéntico a una mezcla de maestro-detalle:
- **Sin repetición:** Agarras `min` y le haces `write(maestroNuevo, min)`.  
- **Con repetición (Corte temporal):** Antes de grabar la venta del vendedor "X", un ciclo while se fija si el siguiente `min` sigue siendo del mismo vendedor, para acoplar o totalizar los datos y escupir 1 solo resultado en el maestro nuevo.

### Variante B: Merge N Archivos (Ejemplo 11)
Hacemos la genialidad de declarar arreglos para dejar de hacerlo a mano con `det1`, `det2` y `det3`.

```pascal
type
    detalle = file of ventas;
    vector_archivos = array[1..N] of detalle;
    vector_registros = array[1..N] of ventas; // Guarda el registro actual sacado de cada archivo
```

La función `minimo` evoluciona: En lugar de usar `if r1 < r2`, ahora usa un `FOR` iterando por `vector_registros` desde 1 a `N` para encontrar cuál está vivo (`<> valoralto`) y es el más bajo:

```pascal
// Pseudo-código de la función Minimo para N Archivos
Procedure Minimo(var v_reg: vector_registros; var min: ventas; var v_arc: vector_archivos);
var i, posMin: integer;
begin
    // Asumimos un elemento enorme
    min.cod := valorAlto;
    posMin := -1;

    for i := 1 to N do begin
        if (v_reg[i].cod < min.cod) then begin
            min := v_reg[i]; 
            posMin := i;      // Me anoto quién ganó la pulseada
        end;
    end;

    // Ya sé cuál fue el ganador, tengo que avanzar un lugarcito solo de ESTE archivo
    if (posMin <> -1) then 
        leer(v_arc[posMin], v_reg[posMin]); 
end;
```

---
*✨ Material completamente estructurado para estudiar fácil. Tip: repasá bien cómo se comporta el puntero al hacer reads.*
