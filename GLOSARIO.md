# Glosario Tecnico

Este glosario resume los conceptos principales aplicados en el proyecto **Clinica Salud Vital**. Su objetivo es apoyar la sustentacion academica y facilitar la lectura tecnica del codigo.

## Conceptos de Programacion Orientada a Objetos

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Clase | Plantilla que define atributos y comportamientos de un objeto. | `Paciente`, `Doctor`, `Cita`, `Pago`, `Tratamiento`. |
| Objeto | Instancia concreta de una clase. | Cada paciente, cita, doctor o pago cargado en memoria. |
| Encapsulamiento | Protege los atributos y permite acceder a ellos mediante metodos. | Modelos con atributos privados y metodos `get` / `set`. |
| Herencia | Permite que una clase reutilice atributos y comportamientos de otra. | `Paciente`, `Doctor` y `Administrador` heredan de `Persona`. |
| Abstraccion | Representa lo esencial de una entidad sin exponer todos sus detalles internos. | `Persona` concentra datos comunes de las personas del sistema. |
| Polimorfismo | Permite tratar objetos relacionados mediante una misma referencia o comportamiento comun. | `Boleta` y `Factura` especializan el comportamiento de `Pago`. |
| Interfaz | Contrato que define operaciones que una clase debe implementar. | `Registrable`, `Pagable`, `Reportable` y los TAD del proyecto. |

## Estructuras de datos

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Arreglo | Estructura de tamano fijo que almacena elementos en posiciones contiguas. | Base de pacientes, citas y pagos en los servicios principales. |
| Matriz | Arreglo bidimensional organizado en filas y columnas. | `MatrizHorarios` relaciona doctores con dias de atencion. |
| Nodo | Unidad que almacena un dato y una referencia hacia otro elemento. | `NodoPaciente`, `NodoCita` y `NodoDoctor`. |
| Lista enlazada | Estructura dinamica formada por nodos conectados. | `ListaPacientes` y `ListaCitas`. |
| Cola | Estructura FIFO: el primero en entrar es el primero en salir. | `ColaPacientes` simula la sala de espera. |
| Pila | Estructura LIFO: el ultimo en entrar es el primero en salir. | `PilaHistorialCitas` gestiona citas recientes. |
| Arbol binario de busqueda | Estructura no lineal que organiza datos para facilitar busquedas. | `ArbolDoctoresBusqueda` organiza doctores por criterio de busqueda. |
| TAD | Tipo Abstracto de Datos que define operaciones sin imponer una implementacion concreta. | Interfaces `TADListaPacientes`, `TADColaPacientes`, `TADPilaHistorialCitas` y `TADArbolDoctores`. |

## Algoritmos

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Busqueda lineal | Recorre elementos uno por uno hasta encontrar una coincidencia. | Busqueda de pacientes y doctores. |
| Recursividad | Tecnica donde un metodo se llama a si mismo para resolver una tarea. | `ReporteRecursivo` recorre y cuenta elementos. |
| Bubble Sort | Ordenamiento por comparacion de elementos vecinos. | `BubbleSortPacientes` ordena pacientes. |
| QuickSort | Ordenamiento que usa pivote y divide el problema en partes menores. | `QuickSort` ordena datos usando `Comparator`. |
| MergeSort | Ordenamiento que divide, ordena y fusiona subconjuntos. | `MergeSortPagos` ordena pagos por monto. |
| Comparator | Interfaz de Java que define el criterio de comparacion entre objetos. | Permite ordenar objetos por atributos especificos. |

## Conceptos del dominio clinico

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Paciente | Persona que recibe atencion medica. | Entidad principal para registros, busquedas y reportes. |
| Doctor | Profesional medico asociado a especialidades y atenciones. | Se organiza mediante el arbol binario de busqueda. |
| Cita | Registro de una atencion programada. | Relaciona paciente, doctor, fecha, hora y estado. |
| Horario | Distribucion de atenciones por doctor y dia. | Se representa mediante una matriz. |
| Sala de espera | Orden de atencion de pacientes antes de una consulta. | Se simula con una cola FIFO. |
| Historial reciente | Registro temporal de citas o atenciones consultadas recientemente. | Se simula con una pila LIFO. |
| Tratamiento | Indicacion medica asociada a una atencion. | Modelado dentro del dominio clinico. |
| Receta | Documento simple con indicaciones o medicamentos. | Relacionada con el tratamiento del paciente. |
| Pago | Registro economico asociado a una atencion. | Base para boletas y facturas. |

## Persistencia y ejecucion

| Concepto | Explicacion | Aplicacion en el proyecto |
|---|---|---|
| Persistencia | Conservacion de datos fuera de la ejecucion del programa. | Exportacion simple a archivos CSV. |
| CSV | Archivo de texto con valores separados por comas. | Salida generada para pacientes, citas y pagos. |
| Consola | Interfaz textual para interactuar con el programa. | Menu principal de sustentacion. |
| Datos en memoria | Informacion que existe mientras el programa esta en ejecucion. | Datos base cargados al iniciar el sistema. |
| Fuera de alcance | Elementos que el proyecto no implementa por decision academica. | Base de datos, interfaz grafica, autenticacion y lectura completa desde archivos. |
