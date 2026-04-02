# 📚 Fundamentos de Organización de Datos (FOD) - Clase 1

> **Resumen: Introducción a Archivos en Pascal** 🚀  
> Este apunte resume los conceptos iniciales sobre el manejo de archivos físicos y lógicos, sus tipos y las operaciones fundamentales.

En Pascal, la memoria principal es volátil. Para que la información persista, utilizamos **Archivos** físicos almacenados en disco.

---

## 1️⃣ Tipos de Archivos
Existen principalmente dos formas de manejar archivos en Pascal:

1. **Archivos de Texto (`Text`):** Caracteres estructurados en líneas. Lectura/escritura con conversión automática de tipos (convierte un entero a texto visual por ejemplo). Acceso *exclusivamente secuencial*.
2. **Archivos Tipados / Longitud Fija (`File of <tipo_dato>`):** Almacenan directamente los bytes de una estructura (record, integer, etc.). Permiten leer y escribir bloques exactos de memoria. Tienen acceso secuencial y **directo** (mediante `seek`).

### 💡 Ejemplo de Definición Lógica
```pascal
type
    persona = record
        dni: string[8];
        apellido: string[25];
        nombre: string[25];
        direccion: string[25];
        sexo: char;
    end;

    // Se define el TIPO de archivo físico
    archivo_personas = file of persona;
    archivo_enteros  = file of integer;

var 
    personas: archivo_personas; // Variable lógica para manipular el archivo
    enteros: archivo_enteros;
```

---

## 2️⃣ Operaciones Básicas

Para trabajar con un archivo, el programa debe cumplir este ciclo de vida: **Enlazar -> Abrir -> Procesar -> Cerrar**.

### 🔗 1. Enlace Físico - Lógico (`assign`)
Le decimos al programa qué archivo físico del disco duro corresponde a nuestra variable lógica en Pascal.
```pascal
assign(personas, 'C:\archivos\personas.dat');
assign(enteros, nombre_variable_con_ruta);
```

### 🚪 2. Apertura (`rewrite` / `reset`)
* **`rewrite(nombre_logico)`**: **Crea** un archivo nuevo. Ojo: ¡Si el archivo ya existía y tenía datos, los pisa y lo deja vacío!
* **`reset(nombre_logico)`**: **Abre** un archivo existente preparándolo para lectura/escritura. (Da error si el archivo no existe físicamente).

### 📖 3. Procesamiento (`read` / `write`)
* **`read(nombre_logico, var_dato)`**: Lee del archivo y lo guarda en la variable. El puntero del archivo avanza automáticamente a la siguiente posición.
* **`write(nombre_logico, var_dato)`**: Graba el valor de la variable en la posición actual del puntero, y luego el puntero avanza.

### 🔒 4. Cierre (`close`)
Es **obligatorio** cerrar los archivos al terminar de usarlos. El cierre empaqueta los buffers que quedaron en memoria RAM y los graba de forma definitiva en el disco. Si no haces el `close`, podés perder información.
```pascal
close(personas);
```

---

## 3️⃣ Operaciones Adicionales (Control y Punteros)

* **`eof(nombre_logico)`** *(End Of File)*: Devuelve `TRUE` si el puntero se encuentra al final del archivo (fuera del último dato). Útil para el `while not eof(arch) do`.
* **`fileSize(nombre_logico)`**: Devuelve la **cantidad total de registros** del archivo.
* **`filePos(nombre_logico)`**: Devuelve la **posición actual del puntero** en el archivo. (Las posiciones se numeran desde `0` hasta `N-1`).
* **`seek(nombre_logico, pos)`**: Mueve el puntero internamente a la posición que se indique. Este es el que permite el **acceso directo**.
  * 🌟 *Tip clásico:* `seek(arch, fileSize(arch));` mueve el puntero directo al final del archivo para agregar cosas nuevas.

---

## 💻 4️⃣ Ejemplo Integrador: Creación y Carga de un Archivo
Este es el típico esqueleto de código para pedirle datos al usuario por teclado y crear un archivo nuevo guardándolos secuencialmente.

```pascal
program creacion_archivo;
type 
    persona = record
        dni: string[8];
        apellidoyNombre: string[30];
        direccion: string[40];
        sexo : char;
        salario: real;
    end;
    archivo_personas = file of persona;

var 
    personas: archivo_personas;
    nombre_fisico: string[12];
    per: persona;

begin 
    write('Ingrese el nombre del archivo: ');
    readln(nombre_fisico);
    
    // 1. Enlace
    assign(personas, nombre_fisico);
    
    // 2. Apertura (Crea el archivo en blanco)
    rewrite(personas);

    // 3. Procesamiento interactivo
    write('Ingrese el dni de la persona: ');
    readln(per.dni); 
    
    while (per.dni <> '') do begin
        // Leer el resto del registro...
        write('Ingrese el apellido y nombre: '); readln(per.apellidoyNombre);
        write('Ingrese la dirección: ');         readln(per.direccion);
        write('Ingrese el sexo: ');              readln(per.sexo);
        write('Ingrese el salario: ');           readln(per.salario);
        
        // Escribir físicamente al disco
        write(personas, per);
        
        write('Ingrese otro dni o presione enter para terminar: ');
        readln(per.dni);
    end;
    
    // 4. Cierre vital
    close(personas);
end.
```
