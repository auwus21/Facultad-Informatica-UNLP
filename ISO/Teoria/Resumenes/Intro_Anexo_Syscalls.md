# 📘 Tema 1 — Anexo: Llamadas al Sistema en Detalle (Syscalls)

**Materia:** Introducción a los Sistemas Operativos (ISO) — UNLP 2026  
**Temas:** Programación directa de syscalls, Hello World en ASM, Diferencias x86 vs x86-64

---

## 🎯 Objetivo

Programar un llamado a una **System Call** de manera directa, sin utilizar ninguna librería. Considerar las diferencias al realizarlo en arquitecturas de **32 bits** y **64 bits**.

---

## 📦 Ejemplo: Hello World en Assembler

Para programar el clásico "Hello World" sin librerías se necesitan como mínimo **2 system calls**:
- **`write`**: Para escribir un mensaje en pantalla.
- **`exit`**: Para terminar la ejecución del proceso.

Los manuales del sistema (`man 2 write`, `man exit`) indican los parámetros necesarios.

---

## ⚙️ Números de Syscalls

Para indicarle al SO lo que queremos hacer, necesitamos saber el **número asociado** a cada syscall. Este número puede ser **distinto en distintas arquitecturas**.

| Syscall | x86 (32 bits) | x86-64 (64 bits) |
|---|---|---|
| **`write`** | `4` | `1` |
| **`exit`** | `1` | `60` |

> 🔗 Fuente oficial (repo de Linus Torvalds):
> - [syscall_32.tbl](https://github.com/torvalds/linux/blob/master/arch/x86/entry/syscalls/syscall_32.tbl)
> - [syscall_64.tbl](https://github.com/torvalds/linux/blob/master/arch/x86/entry/syscalls/syscall_64.tbl)

---

## 📊 Pasaje de Parámetros por Arquitectura

| Característica | x86 (32 bits) | x86-64 (64 bits) |
|---|---|---|
| **Instrucción que activa la syscall** | `int 0x80` (interrupción por software) | `syscall` (*fast system call*) |
| **Registro para el nro de syscall** | `EAX` | `RAX` |
| **1er parámetro** | `EBX` | `RDI` |
| **2do parámetro** | `ECX` | `RSI` |
| **3er parámetro** | `EDX` | `RDX` |
| **4to parámetro** | `ESI` | `R10` |
| **5to parámetro** | `EDI` | `R8` |
| **6to parámetro** | — | `R9` |

---

## 📦 Código Hello World

**x86 (32 bits):**
<img src="./images/t1_helloworld_32bit.png" alt="Hello World en Assembler x86 32bit" width="650"/>

**x86-64 (64 bits):**
<img src="./images/t1_helloworld_64bit.png" alt="Hello World en Assembler x86-64 64bit" width="650"/>

---

## 🧠 Resumen de Diferencias

- Los procesadores de **32 bits** y **64 bits** usan un esquema de **registros diferentes**.
- Usan una **instrucción distinta** para activar las syscalls:
  - 32 bits: `int 80h`
  - 64 bits: `syscall`

> 💡 **Observación:** Los procesadores modernos de 64 bits usan una instrucción nativa `syscall` en vez de tratar las peticiones como interrupciones generalizadas (`int 80h`), logrando **mayor velocidad** al cambiar menos registros y evitar el overhead del mecanismo de interrupciones.

---

## 📚 Recursos y Referencias

- Hello World en x86 32bit y 64bit: http://shmaxgoods.blogspot.com.ar/2013/09/assembly-hello-world-in-linux.html
- Stack Overflow — x86-64 Hello World: https://stackoverflow.com/questions/19743373/linux-x86-64-hello-world-and-register-usage-for-parameters
- Repo de Torvalds: https://github.com/torvalds/linux
- Referencia de syscalls: https://syscalls.kernelgrok.com/
- Tabla x86_64: http://blog.rchapman.org/posts/Linux_System_Call_Table_for_x86_64/
