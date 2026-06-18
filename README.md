# UTP Clinica Salud Vital

Sistema academico desarrollado en Java para simular procesos basicos de una clinica y demostrar la aplicacion de estructuras de datos, algoritmos y Programacion Orientada a Objetos.

El proyecto corresponde al curso **Algoritmos y Estructuras de Datos** de la UTP y se ejecuta mediante un menu por consola orientado a sustentacion.

## Contexto academico

- Institucion: Universidad Tecnologica del Peru
- Curso: Algoritmos y Estructuras de Datos
- Ciclo: V
- Tipo: Proyecto final academico
- Caso de estudio: Clinica Salud Vital

## Objetivo

Desarrollar una aplicacion en Java que permita representar procesos clinicos y administrativos, como gestion de pacientes, citas medicas, sala de espera, historial reciente, doctores, tratamientos, pagos, reportes y exportacion CSV.

El objetivo principal no es reemplazar un sistema clinico real, sino demostrar de forma practica los temas del curso mediante un caso aplicado.

## Temas aplicados

- Programacion Orientada a Objetos
- Encapsulamiento, herencia, abstraccion, interfaces y polimorfismo
- Arreglos unidimensionales
- Matrices
- Listas enlazadas
- Colas FIFO
- Pilas LIFO
- Arbol binario de busqueda
- Busqueda lineal
- Recursividad
- Bubble Sort
- QuickSort generico con `Comparator`
- MergeSort
- Persistencia simple mediante archivos CSV

## Estructura del proyecto

```txt
utp-clinica-salud-vital/
|-- src/pe/com/utp/
|   |-- app/
|   |-- algoritmos/
|   |   |-- busqueda/
|   |   |-- ordenamiento/
|   |   `-- recursividad/
|   |-- estructuras/
|   |   |-- arboles/
|   |   |-- colas/
|   |   |-- listas/
|   |   |-- matrices/
|   |   `-- pilas/
|   |-- interfaces/
|   |-- modelo/
|   |   |-- cita/
|   |   |-- enums/
|   |   |-- pago/
|   |   |-- persona/
|   |   `-- tratamiento/
|   |-- persistencia/
|   |-- servicios/
|   `-- util/
|-- README.md
`-- .gitignore
```

## Modulos principales

| Modulo | Descripcion |
|---|---|
| Pacientes | Registra, muestra, busca, actualiza y elimina pacientes usando arreglos y busqueda lineal. |
| Citas y horarios | Registra citas y contabiliza horarios mediante una matriz de doctores por dias. |
| Sala de espera | Usa una cola de pacientes para demostrar FIFO mediante `encolar`, `verFrente` y `desencolar`. |
| Historial reciente | Usa una pila de citas para demostrar LIFO mediante `apilar`, `verCima` y `desapilar`. |
| Doctores | Organiza doctores mediante un arbol binario de busqueda con insercion, busqueda, eliminacion y recorridos. |
| Ordenamientos | Aplica Bubble Sort, QuickSort y MergeSort sobre datos del sistema. |
| Recursividad | Recorre arreglos y cuenta elementos mediante llamadas recursivas. |
| Pagos | Modela pagos, boletas y facturas aplicando herencia y polimorfismo. |
| Persistencia | Exporta pacientes, citas y pagos a archivos CSV. |

## Menu de sustentacion

El punto de entrada del sistema es:

```txt
src/pe/com/utp/app/Main.java
```

Al ejecutar el programa se muestra un menu por consola:

```txt
1. Presentacion del sistema y datos base
2. Gestion de pacientes
3. Gestion de citas y horarios
4. Sala de espera de pacientes
5. Historial reciente y seguimiento clinico
6. Busquedas y ordenamientos
7. Arbol binario de doctores
8. Tratamientos, recetas y pagos
9. Persistencia, reportes y utilidades
10. Demostracion completa AED
0. Salir
```

Cada opcion muestra el concepto aplicado antes de ejecutar el proceso, lo que facilita la sustentacion oral del proyecto.

## Ejecucion local

Desde la raiz del repositorio, compilar los archivos Java:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName
```

Ejecutar la clase principal:

```powershell
java -cp out pe.com.utp.app.Main
```

Para ejecutar la demostracion completa, seleccionar la opcion `10` en el menu.

## Estado del proyecto

| Punto | Estado |
|---|---|
| Modelos principales | Implementado |
| Servicios de pacientes, citas, pagos y reportes | Implementado |
| Menu por consola | Implementado |
| Arreglos y matrices | Implementado |
| Listas enlazadas | Implementado |
| Cola de pacientes | Implementado |
| Pila de historial de citas | Implementado |
| Arbol binario de doctores | Implementado |
| Busqueda lineal | Implementado |
| Bubble Sort, QuickSort y MergeSort | Implementado |
| Recursividad | Implementado |
| Interfaces y TAD | Implementado |
| Exportacion CSV | Implementado |
| Validacion manual por menu | Implementado |

## Alcance

El sistema trabaja con datos base cargados en memoria al iniciar la aplicacion. La persistencia implementada es basica y consiste en exportar informacion a archivos CSV.

El proyecto no incluye base de datos, interfaz grafica ni autenticacion de usuarios, porque su alcance esta centrado en demostrar estructuras de datos y algoritmos del curso.

## Aprendizajes aplicados

- Organizacion de un proyecto Java por paquetes.
- Separacion entre modelos, servicios, estructuras, algoritmos, interfaces, persistencia y utilidades.
- Aplicacion de TAD mediante interfaces.
- Uso de estructuras lineales y no lineales.
- Relacion entre procesos clinicos y estructuras de datos.
- Explicacion tecnica de algoritmos para sustentacion academica.

## Mejoras futuras opcionales

- Agregar pruebas automatizadas si el alcance del curso lo permite.
- Permitir ingreso de datos por consola en lugar de trabajar solo con datos base.
- Incorporar lectura de archivos CSV para recuperar informacion exportada.
- Ampliar validaciones de datos en registros de pacientes, citas y pagos.

## Relacion con Chilete DevPath

Este proyecto forma parte del registro academico de Chilete DevPath:

- [academia](https://github.com/chiletedevpath/academia)
- [aprendizaje](https://github.com/chiletedevpath/aprendizaje)
- [chiletedevpath](https://github.com/chiletedevpath/chiletedevpath)
