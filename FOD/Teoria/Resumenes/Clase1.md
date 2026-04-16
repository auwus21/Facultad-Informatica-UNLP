# 📘 Clase 1: Archivos y Operaciones Básicas

**Materia:** Fundamentos de Organización de Datos (FOD) — UNLP 2026  
**Temas:** Persistencia de datos, Archivos físicos y lógicos, Conceptos Básicos de BD, Acceso a Archivos, Operaciones Básicas Pascal

---

## 🎯 Conceptos Básicos de Base de Datos

Una **Base de Datos** es una colección de archivos diseñados para servir a múltiples aplicaciones. Es una colección coherente de datos relacionados con significados inherentes (no es un conjunto aleatorio de datos). Representa aspectos del mundo real y está diseñada para un propósito específico.

En criollo: Una base de datos no es meter información suelta en una carpeta, sino organizar información con sentido para que un programa pueda consultarla y modificarla con un propósito claro.

> *"Una BD está sustentada físicamente en archivos en dispositivos de almacenamiento persistente de datos."*

## 🏗️ Archivos y Organización

Un **archivo** es una colección de registros que abarcan entidades con un aspecto común guardados en almacenamiento secundario.

### Organización Lógica

*   **Campo:** Unidad lógica más pequeña y significativa de un archivo.
*   **Registro:** Conjunto de campos agrupados que definen un elemento del archivo.

```mermaid
classDiagram
    Archivo "1" *-- "n" Registro : Contiene
    Registro "1" *-- "n" Campo : Se divide en
```

---

## ⚙️ Tipos de Acceso y Organización

Dependiendo de cómo se leen o escriben los datos, existen distintas estrategias:

| Forma de Acceso | Descripción |
|---|---|
| **Serie** | Cada registro es accesible sólo luego de procesar su antecesor. Acceso secuencial físico. |
| **Secuencial (Indizado)** | Los registros son accesibles en el orden de alguna clave. Acceso secuencial lógico (ej. índice de un libro). |
| **Directo** | Se accede a un registro determinado sin necesidad de pasar por los anteriores. |

---

## 💻 Operaciones Básicas con Archivos en Pascal

Todo archivo tiene **dos niveles**: el Físico (en el disco) y el Lógico (en nuestro programa). El Sistema Operativo se encarga de usar **Buffers** (memoria intermedia en RAM) para agilizar el proceso de lectura/escritura y reducir los accesos al disco rígido.

### 1. Declaración y Enlace Físico-Lógico
Pascal necesita que declaremos la variable de archivo y la enlacemos a un nombre en el disco mediante la operación `Assign`.

```pascal
Type 
    emple = record
        nombre: string [20];
        edad: integer;
    end;
    empleado = file of emple;

Var 
    arch_emp: empleado;
Begin
    Assign( arch_emp, 'Personas_empleados.dat' );
End;
```

### 2. Apertura y Creación
Antes de leer o escribir, debemos abrir el archivo.

| Comando | Acción |
|---|---|
| `Rewrite(archivo)` | Crea el archivo lógico nuevo en disco. Si ya existe, lo pisa (solo escritura inicial). |
| `Reset(archivo)` | Abre un archivo existente para operaciones de Lectura y Escritura. |
| `Close(archivo)` | Cierra el archivo y le coloca la marca de **EOF (End Of File)**. |

### 3. Lectura y Escritura de datos
Estas operaciones leen y escriben a través del Buffer, y no directamente sobre el disco inmediatamente. La variable usada para leer o escribir debe coincidir con el tipo base del archivo (ej. el registro `emple`).

*   `Read(archivo, variable)`
*   `Write(archivo, variable)`

### 4. Operaciones Adicionales y Funciones útiles
El Sistema Operativo nos permite navegar dentro del archivo. Se trata al archivo como un vector de registros de tamaño variable donde la primera posición es 0.

*   `EOF(archivo)`: Devuelve *True* si llegamos al final del archivo. ¡Siempre hay que preguntar primero antes de leer!
*   `FileSize(archivo)`: Devuelve el tamaño del archivo (cantidad total de registros).
*   `FilePos(archivo)`: Retorna la posición relativa en la que nos encontramos (empieza en 0).
*   `Seek(archivo, posición)`: Procedimiento que mueve el puntero lógico hacia una posición específica dentro del archivo, útil para el **acceso directo**.

---

## 📦 Ejemplo 1: Crear un Archivo

### Situación Inicial
Tenemos variables definidas en el programa, pero queremos guardar esa información en disco duro. Tenemos un archivo numérico, `archivo = file of integer`.

### La Solución
Pedimos los números y guardamos de manera secuencial hasta que se ingrese un 0.

```pascal
Program Generar_Archivo;
type 
    archivo = file of integer; 
var 
    arc_logico: archivo;        
    nro: integer;               
    arc_fisico: string[12];     
begin
    write( 'Ingrese el nombre del archivo:' );
    read( arc_fisico );
    assign( arc_logico, arc_fisico );
    
    rewrite( arc_logico ); { se crea el archivo vacío }
    
    read( nro ); { se obtiene de teclado el primer valor }
    while nro <> 0 do begin
        write( arc_logico, nro ); { escribe en el archivo }
        read( nro );
    end;
    close( arc_logico );  { guardamos el EOF y cerramos }
end.
```

## 📦 Ejemplo 2: Actualización in situ (Modificación)

### El Problema
Queremos aumentarle el salario a todos los empleados guardados en el archivo un 10%. Para actualizar, debemos leer el registro, alterarlo en RAM, y volver a re-escribirlo en la MISMA posición del archivo.

### La Solución
Leemos con secuencialidad, modificamos, y usamos un `Seek` al elemento anterior (porque al hacer el `read` inicial el puntero del SO ya avanzó uno).

```pascal
Procedure actualizar (Var Emp: empleados);
var E: registro;
begin
    Reset( Emp ); { Abrimos en lectura/escritura }
    while not eof( Emp ) do begin
        Read( Emp, E); { el puntero avanza de i a i+1 }
        
        { Actualizamos la variable local en RAM }
        E.salario := E.salario * 1.1;    
        
        { Volvemos el puntero hacia atrás de i+1 a i }
        Seek( Emp,  filepos(Emp) - 1 ); 
        
        { Reescribimos y pisamos el viejo valor. El puntero vuelve a avanzar. }
        Write( Emp, E ); 
    end;
    close( Emp );
end;
```

---

## 📚 Recursos y Referencias

- **Cátedra FOD (UNLP):** *"Organización de Datos - Clase 1: Archivos y Básica"*. 2026.
- Oficial UNLP (Descargas): Modificadores, apuntes y PDFs extra de `https://asignaturas.info.unlp.edu.ar`.
