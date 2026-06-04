# UTP Clinica Salud Vital

Sistema academico en Java para gestionar pacientes, citas, pagos, horarios y reportes dentro de una clinica.

## Autor

- Chilete DevPath
- Estudiante de Ingenieria de Sistemas e Informatica

## Contexto academico

- Institucion: UTP
- Ciclo: 05
- Curso: Algoritmos y Estructuras de Datos
- Tipo: Proyecto academico

## Por que se hizo

Este proyecto se desarrollo para aplicar programacion orientada a objetos, estructuras de datos, algoritmos de busqueda y ordenamiento, matrices, pilas, colas, listas y persistencia mediante archivos.

El caso trabajado simula operaciones de una clinica: pacientes, doctores, citas, horarios, pagos y reportes.

## Tecnologias usadas

- Java
- Programacion orientada a objetos
- Listas enlazadas
- Colas
- Pilas
- Matrices
- Busqueda lineal
- Algoritmos de ordenamiento
- Persistencia mediante archivos

## Que contiene el repositorio

```txt
utp-clinica-salud-vital/
|-- src/pe/com/utp/
|   |-- algoritmos/
|   |-- app/
|   |-- estructuras/
|   |-- interfaces/
|   |-- modelo/
|   |-- persistencia/
|   |-- servicios/
|   `-- util/
|-- .gitignore
`-- README.md
```

## Funcionalidades principales

- Registro de pacientes.
- Registro y busqueda de citas.
- Gestion de matriz de horarios por doctor y dia.
- Reporte de citas por dia.
- Reporte de citas por doctor.
- Modelado de pagos con boleta y factura.
- Uso de estructuras de datos propias.
- Aplicacion de busqueda y ordenamiento.

## Punto de entrada

```txt
src/pe/com/utp/app/Main.java
```

El `Main` actual ejecuta un flujo academico de demostracion con pacientes, doctores, citas, matriz de horarios y busqueda de cita por codigo.

## Ejecucion local

Desde la raiz del repositorio, compilar los archivos Java hacia una carpeta de salida:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName
```

Ejecutar la clase principal:

```powershell
java -cp out pe.com.utp.app.Main
```

## Estado del proyecto

| Punto | Estado |
|---|---|
| Modelo de pacientes, doctores y citas | Implementado |
| Matriz de horarios | Implementada |
| Servicios de citas, pacientes, pagos y reportes | Implementados |
| Estructuras de datos academicas | Implementadas |
| Flujo demostrativo en consola | Implementado |
| Documentacion de casos de uso | Pendiente |
| Pruebas automatizadas | Pendiente |

## Aprendizajes aplicados

- Diseno de clases y paquetes en Java.
- Herencia, abstraccion e interfaces.
- Uso de estructuras de datos propias.
- Relacion entre objetos de dominio y servicios.
- Aplicacion de algoritmos de busqueda y ordenamiento.
- Organizacion de un proyecto Java academico por capas.

## Pendientes

- Documentar todos los casos de uso del sistema.
- Agregar ejemplos de ejecucion en consola.
- Confirmar version de Java usada en clase.
- Agregar pruebas o escenarios de validacion.

## Relacion con Chilete DevPath

Este proyecto forma parte del registro academico de Chilete DevPath:

- [academia](https://github.com/chiletedevpath/academia)
- [aprendizaje](https://github.com/chiletedevpath/aprendizaje)
- [chiletedevpath](https://github.com/chiletedevpath/chiletedevpath)
