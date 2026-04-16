# 📘 Clase 2: Algoritmia Clásica de Archivos

**Materia:** Fundamentos de Organización de Datos (FOD) — UNLP 2026  
**Temas:** Algoritmia Clásica, Maestro-Detalle, Corte de Control, Merge de múltiples fuentes.

---

## Parte A: Fundamentación General

### 🎯 La "Algoritmia Clásica"

Manejar streams de bases de datos mediante buffers secuenciales demanda que el programador sincronice muy bien varias lecturas al mismo tiempo. Al presentarse infinidad de veces el mismo problema de negocios (juntar ventas diarias con el stock mensual, agrupar censos por ciudades, etc.), se elaboraron esquemas estructurados de rigurosa exactitud llamados **Algoritmia Clásica**. Dominar estos esquemas permite solucionar problemas de persistencia eficientemente y evitar bugs de des-sincronización de punteros. 

### ⚙️ Agregado Ciego de Datos (Append)

Si queremos seguir operando sobre un archivo que ya fue creado, usaríamos `Reset`. El problema es que el puntero mágico empezará en `FilePos=0`. Para hacer "Append", forzamos el puntero al último pixel disponible usando `FileSize` (que retorna el N de bytes que casualmente equivale al primer índice vacío al no haber pos=0).

```pascal
Procedure agregar(Var Emp: Empleados); 
var E: registro;
begin
    reset(Emp); 
    seek(Emp, filesize(Emp)); { Salto inmediato a la zona inexplorada }
    
    leer(E); 
    while E.nombre <> ' ' do begin
        write(Emp, E);     
        leer(E); 
    end;
    close(Emp);
end;
```

---

## Parte B: El Patrón "Maestro-Detalle"

Este problema se centra en tomar de base un archivo central gigantesco y veraz (el **Maestro**) y actualizarlo utilizando las "novedades" recientes acumuladas en archivos temporales (el **Detalle**).

**Precondiciones Vitales de Arquitectura:**
1. Ambos archivos siempre deben estar **ordenados por la misma clave maestra** de cotejo.
2. Todo producto, persona o ente figurado en el Detalle, debe pre-existir teóricamente en el Maestro para que pueda ser actualizado. 

### 📦 Variante 1: Un Maestro - Un Detalle Estricto
Caso ideal. En el archivo detalle figura, a lo sumo, una sola compra de cada cliente al día.  
Simplemente iteramos, buscamos la coincidencia y actualizamos in-situ.

```pascal
reset (mae1); reset (det1);
while (not eof(det1)) do begin
    read(det1, regd);
    read(mae1, regm);
    
    { Iteración inactiva: avanzamos el Maestro hasta que empate con el Detalle }
    while (regm.nombre <> regd.nombre) do
        read (mae1, regm);
        
    { Coinciden. Alteramos y guardamos usando Seek-1 }
    regm.cht := regm.cht + regd.cht;
    seek (mae1, filepos(mae1)-1);
    write(mae1, regm);
end;
```

### 📦 Variante 2: Un Maestro - Un Detalle con Multiplicidades
Realidad de negocios. Un mismo alfajor en el supermercado maestro puede haber sido comprado 500 veces a lo largo del mismo día (figurando 500 registros apilados en el Detalle). No podemos re-escribir el disco por cada compra: se hace un mega-ciclo totalizador interno. 

```pascal
{ Usamos valoralto '9999' en un procedure especial leer(archivo, registro) }
leer(det1, regd); 

while (regd.cod <> valoralto) do begin
    read(mae1, regm);
    
    while (regm.cod <> regd.cod) do
        read (mae1, regm);
        
    { Mientras compartan el código, NO guardamos a disco, sino que achicamos el Detalle }
    while (regm.cod = regd.cod) do begin
        regm.cant := regm.cant - regd.cv;        
        leer(det1, regd);          
    end;
    
    { Salió del ciclo. Terminaron todas sus compras. Ahora grabamos una ÚNICA vez }
    seek (mae1, filepos(mae1)-1);
    write(mae1, regm);
end;
```

### 📦 Variante 3: Un Maestro - N Detalles
Aplicable a múltiples sucursales a la vez. En vez de leer `det1`, sometemos los detalles de todas las terminales a una rutina colosal llamada **`Minimo(r1, r2, r3, min)`**. 
Esta rutina compara en RAM las claves de los 3 registros topes extraídos, elige a la más chiquita de las tres (Ej: Cód. de barra 512, por sobre 800 y 900), carga solo esa y provoca que ese archivo origen en particular lea la siguiente suya. 

---

## Parte C: Generación de Reportes (Corte de Control)

**Motivación:** Extraer totales estadísticos jerárquicos (Ej: Por escuela, luego por municipio, luego por provincia). 

**Precondiciones:**
El archivo ya debe estar hiper-clasificado y ordenado geográficamente en dicho orden específico. 

No requerimos actualizar a disco jamás, es puro Read-and-Print. La herramienta mágica son las `variables old` o `ant_`.  Al almacenar de memoria qué provincia estábamos mirando, cuando nos llega el primer disco con una provincia que cortó esa paridad, sabemos que hay que emitir los balances definitivos acumulados. 

```pascal
while (regm.provincia <> valoralto) do begin
    ant_prov := regm.provincia;
    ant_partido := regm.partido;
    
    while (ant_prov = regm.provincia) and (ant_partido = regm.partido) do begin
        t_varones := t_varones + regm.cant_varones; 
        leer (inst, regm);
    end;
    
    { Discreparon en Pdo. }
    writeln ('Total Pdo: ', t_varones);
    t_prov_var := t_prov_var + t_varones; { Tributamos a la variable padre }
    t_varones := 0; 
    ant_partido := regm.partido;
    
    { Si además la Pcia discrepó: }
    if (ant_prov <> regm.provincia) then begin
        writeln ('Total Provincia ', t_prov_var);
        t_prov_var := 0;
        writeln ('Provincia: ', regm.provincia);
    end;
end;
```

---

## Parte D: Fusiones Totales (Merge)

**Motivación:** Agrupar n-piezas de información cruda recolectada, en un super Archivo Maestro flamante y en blanco. 

**Precondiciones:**
Todos los orígenes poseen la misma estructura idéntica `Type`. Y todos ordenados bajo el mismo criterio único de ordenación.

### 🧩 Merge Convencional de 3 y N Archivos
Nuevamente, el alma mágica recide en crear iteradores infinitos propulsados por una rutina extractora denominada `Minimo()` junto a la barrera tope del `valoralto: '9999'`. 

| Componente Clave | Función |
|---|---|
| **`Minimo()`** | Evalúa los N registros de Buffer traídos. Elige el de código más humilde, transfiere sus datos a variable auxiliar y obliga a sacar uno nuevo para que reemplace ese hueco en RAM. |
| **Bucle Gigante** | Ejecutamos el `write(Maestro_New, variable_min)` compulsivamente hasta que `Minimo` nos retorne `9999` porque se destriparon todas las N cintas a la perfección. |

### 🧩 Arquitectura Extrema: Archivos a N Dimensiones
En Pascal, gestionar 500 sucursales no es declarativo con N variables puras. Se declaran Arrays de punteros y de Buffers de registros para iterar con fors el mínimo!

```pascal
Type
    arc_detalle = array[1..100] of file of vendedor;
    reg_detalle = array[1..100] of vendedor;

Procedure Minimo(var reg_det: reg_detalle; var min:vendedor; var deta:arc_detalle);
var i, indice_del_min: integer;
begin
    { Ciclo FOR que calcula cuál de los 100 reg_det es el ganador en orden alfabético }
    min := reg_det[indice_del_min];
    leer(deta[indice_del_min], reg_det[indice_del_min]);
end;

{ En el Main: Un FOR abría archivos dinámicamente formados por la concat: 'det'+i }
```

El Merge de N archivos con Repetición combinará el truco del Archivo N Dimensiones del for, anexándole una condición agrupadora estática sobre `while (regm.cod = min.cod )` para sumar todo en la misma venta antes del `Write`.

---

## 📚 Recursos y Referencias
- **Cátedra FOD (UNLP):** *"Organización de Datos - Clase 2: Algoritmia Clásica Integral"*. 2026.
