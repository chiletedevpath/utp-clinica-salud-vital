package pe.com.utp.app;

import pe.com.utp.algoritmos.busqueda.BusquedaDoctor;
import pe.com.utp.algoritmos.ordenamiento.BubbleSortPacientes;
import pe.com.utp.algoritmos.ordenamiento.MergeSortPagos;
import pe.com.utp.algoritmos.ordenamiento.QuickSort;
import pe.com.utp.algoritmos.recursividad.ReporteRecursivo;
import pe.com.utp.estructuras.arboles.ArbolDoctoresBusqueda;
import pe.com.utp.estructuras.colas.ColaPacientes;
import pe.com.utp.estructuras.listas.ListaPacientes;
import pe.com.utp.estructuras.matrices.MatrizHorarios;
import pe.com.utp.estructuras.pilas.PilaHistorialCitas;
import pe.com.utp.modelo.cita.Cita;
import pe.com.utp.modelo.enums.Especialidad;
import pe.com.utp.modelo.enums.EstadoCita;
import pe.com.utp.modelo.enums.TipoPago;
import pe.com.utp.modelo.enums.TurnoConsultaAmbulatoria;
import pe.com.utp.modelo.pago.Boleta;
import pe.com.utp.modelo.pago.Factura;
import pe.com.utp.modelo.pago.Pago;
import pe.com.utp.modelo.persona.Doctor;
import pe.com.utp.modelo.persona.Paciente;
import pe.com.utp.modelo.tratamiento.Receta;
import pe.com.utp.modelo.tratamiento.Tratamiento;
import pe.com.utp.persistencia.ArchivoCita;
import pe.com.utp.persistencia.ArchivoPaciente;
import pe.com.utp.persistencia.ArchivoPago;
import pe.com.utp.servicios.CitaService;
import pe.com.utp.servicios.PagoService;
import pe.com.utp.servicios.PacienteService;
import pe.com.utp.servicios.ReporteService;
import pe.com.utp.util.Consola;
import pe.com.utp.util.GeneradorCodigo;
import pe.com.utp.util.UtilidadesFecha;
import pe.com.utp.util.Validador;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * Clase principal del sistema.
 * Reune pruebas simples de pacientes, citas, matriz, cola y pila.
 * La idea es mostrar las estructuras vistas en clase con datos de la clinica.
 */

public class Main {

    public static void main(String[] args) {

        /*
         * 1. Arreglo unidimensional.
         * Se registra, recorre, actualiza y elimina pacientes usando posiciones.
         */

        // Servicio que guarda pacientes en un arreglo unidimensional.
        PacienteService pacienteService = new PacienteService();

        // Datos de prueba usados durante la ejecucion del sistema.
        Paciente paciente1 = new Paciente("PAC-001", "73748381", "Adrian", "Pisco", "945834043", "adrian.pisco@gmail.com", 28);
        Paciente paciente2 = new Paciente("PAC-002", "25830900", "Wilmer", "Soto", "985000677", "wilmer.soto@gmail.com", 20);
        Paciente paciente3 = new Paciente("PAC-003", "20005900", "Yenifer", "Alcantara", "983330677", "yenifer.nera@gmail.com", 17);
        Paciente paciente4 = new Paciente("PAC-004", "21001010", "Xiomara", "Rojas", "983741677", "xiomara.nera@gmail.com", 21);
        Paciente paciente5 = new Paciente("PAC-005", "80701010", "Jorge", "Flores", "999652000", "jorge.flores@gmail.com", 35);

        // Cada registro ocupa la siguiente posicion disponible del arreglo.
        System.out.println("\n==== REGISTRO DE PACIENTES =====");
        pacienteService.registrarPaciente(paciente1);
        pacienteService.registrarPaciente(paciente2);
        pacienteService.registrarPaciente(paciente3);
        pacienteService.registrarPaciente(paciente4);
        pacienteService.registrarPaciente(paciente5);
        System.out.println("=======================\n");

        // Recorrido del arreglo hasta la cantidad real de pacientes registrados.
        pacienteService.mostrarPacientes();

        /*
         * 2. Lista enlazada simple.
         * Cada paciente se guarda en un nodo conectado al siguiente.
         */

        // La lista enlazada trabaja con nodos y no depende del arreglo anterior.
        ListaPacientes listaPacientes = new ListaPacientes();

        // Cada paciente se inserta al final de la lista.
        listaPacientes.insertarPaciente(paciente1);
        listaPacientes.insertarPaciente(paciente2);
        listaPacientes.insertarPaciente(paciente3);
        listaPacientes.insertarPaciente(paciente4);
        listaPacientes.insertarPaciente(paciente5);

        // Se recorre la lista desde el primer nodo hasta llegar a null.
        System.out.println("\n==== LISTA ENLAZADA DE PACIENTES ====");
        listaPacientes.mostrarPacientes();

        // Busqueda dentro de la lista enlazada usando el DNI como criterio.
        Paciente pacienteEnLista = listaPacientes.buscarPacientePorDni(paciente2.getDni());
        if (pacienteEnLista != null) {
            System.out.println("\nPaciente encontrado en lista enlazada:");
            pacienteEnLista.mostrarDatos();
        } else {
            System.out.println("\nPaciente no encontrado en lista enlazada");
        }

        // El contador logico evita recorrer toda la lista solo para saber su tamanio.
        System.out.println("\nTotal de pacientes en lista: " + listaPacientes.contarPacientes());

        // Al eliminar, se actualizan los enlaces entre nodos.
        listaPacientes.eliminarPaciente(paciente4.getDni());
        System.out.println("\n==== LISTA ENLAZADA DESPUES DE ELIMINAR ====");
        listaPacientes.mostrarPacientes();

        /*
         * 3. Busqueda lineal.
         * Se recorre el arreglo desde la primera posicion ocupada hasta encontrar el DNI.
         */

        // Caso 1: DNI que si existe dentro del arreglo.
        String dniABuscar = "73748381";
        Paciente pacienteEncontrado = pacienteService.buscarPacientePorDni(dniABuscar);

        System.out.println("\n==== BUSQUEDA DE PACIENTES POR DNI ====");
        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado con exito");
            pacienteEncontrado.mostrarDatos();
        } else {
            System.out.println("No se encontro ningun paciente con el DNI: " + dniABuscar);
        }

        // Caso 2: DNI que no existe. Sirve para validar el retorno null.
        String dniNoEncontrado = "00520052";
        Paciente pacienteNoEncontrado = pacienteService.buscarPacientePorDni(dniNoEncontrado);

        if (pacienteNoEncontrado != null) {
            System.out.println("Paciente encontrado con exito");
            pacienteNoEncontrado.mostrarDatos();
        } else {
            System.out.println("No se encontro ningun paciente con el DNI: " + dniNoEncontrado);
        }

        /*
         * 4. Operaciones sobre arreglo.
         * La actualizacion modifica el objeto encontrado y la eliminacion compacta posiciones.
         */

        // Primero se confirma la busqueda; luego se modifican los datos del objeto.
        System.out.println("\n==== ACTUALIZACION DE DATOS DE UN PACIENTE ====");

        if (pacienteEncontrado == null) {
            System.out.println("Paciente no encontrado");
        } else {
            // El DNI se usa como criterio para ubicar al paciente a actualizar.
            pacienteService.actualizarPaciente(dniABuscar, "111222333", "nuevo.correo@gmail.com");
            pacienteService.mostrarPacientes();
        }

        // Al eliminar, el arreglo desplaza elementos para no dejar espacios logicos.
        System.out.println("\n==== ELIMINACION DE DATOS DE UN PACIENTE POR DNI ====");
        pacienteService.eliminarPaciente(dniABuscar);
        pacienteService.mostrarPacientes();

        /*
         * 5. Arreglo bidimensional.
         * La matriz relaciona doctores por fila y dias de atencion por columna.
         */

        // Cada doctor queda asociado a una fila de la matriz.
        Doctor doctor1 = new Doctor("DOC-001", "25413651", "Juan Carlos", "Lozano Leon", "925587412", "000555", "doctor1@gmail.com", Especialidad.MEDICINA_GENERAL, TurnoConsultaAmbulatoria.MANANA);
        Doctor doctor2 = new Doctor("DOC-002", "02578933", "Carlos Elvis", "Tapia Silva", "925587412", "000556", "doctor2@gmail.com", Especialidad.DERMATOLOGIA, TurnoConsultaAmbulatoria.TARDE);
        Doctor doctor3 = new Doctor("DOC-003", "73740025", "David", "Moncada Teran", "925357891", "000557", "doctor3@gmail.com", Especialidad.TRAUMATOLOGIA, TurnoConsultaAmbulatoria.MANANA);
        Doctor[] doctores = {doctor1, doctor2, doctor3};

        // La matriz trabaja con 3 doctores y 5 dias de atencion.
        MatrizHorarios matrizHorarios = new MatrizHorarios(doctor1, doctor2, doctor3);

        // Cada cita relaciona paciente, doctor, fecha, hora, estado y motivo.
        Cita cita1 = new Cita("CIT-001", paciente2, doctor1, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Dolor de cuello");
        Cita cita2 = new Cita("CIT-002", paciente2, doctor2, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Presencia de fiebre");
        Cita cita3 = new Cita("CIT-003", paciente3, doctor3, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");
        Cita cita4 = new Cita("CIT-004", paciente4, doctor2, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");
        Cita cita5 = new Cita("CIT-005", paciente5, doctor1, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");

        // El servicio de citas actualiza tambien la matriz de horarios.
        CitaService citaService = new CitaService(matrizHorarios);

        // La columna indica el dia laboral donde se registra cada cita.
        citaService.registrarCita(cita1, 1);
        citaService.registrarCita(cita2, 5);
        citaService.registrarCita(cita3, 1);
        citaService.registrarCita(cita4, 2);
        citaService.registrarCita(cita5, 3);

        // Muestra las citas registradas y luego el resumen en matriz.
        citaService.mostrarCitas();

        System.out.println("\n==== MAPA BIDIMENSIONAL DE DISPONIBILIDAD ====");
        matrizHorarios.mostrarMatriz();

        // Busqueda lineal en arreglo de doctores usando el codigo como clave.
        System.out.println("\n==== BUSQUEDA LINEAL DE DOCTORES ====");
        BusquedaDoctor busquedaDoctor = new BusquedaDoctor();
        Doctor doctorEncontrado = busquedaDoctor.buscarPorCodigo(doctores, doctores.length, "DOC-002");

        if (doctorEncontrado != null) {
            System.out.println("Doctor encontrado con exito");
            doctorEncontrado.mostrarDatos();
        } else {
            System.out.println("Doctor no encontrado");
        }

        /*
         * 6. Cola dinamica.
         * Se aplica FIFO: el primer paciente en entrar es el primero en salir.
         */

        System.out.println("\n==== SALA DE ESPERA DE PACIENTES (ESTRUCTURA COLA) ====");

        // La cola representa orden de llegada para triaje o atencion.
        ColaPacientes colaPacientes = new ColaPacientes();

        // Cada paciente entra al final de la cola.
        colaPacientes.encolar(paciente2);
        colaPacientes.encolar(paciente3);

        System.out.println("\nPacientes en espera:");
        colaPacientes.mostrarCola();

        // Se atiende al primer paciente que ingreso.
        colaPacientes.desencolar();

        System.out.println("\nPacientes en espera despues de atender:");
        colaPacientes.mostrarCola();

        /*
         * 7. Pila dinamica.
         * Se aplica LIFO: la ultima cita apilada queda en la cima.
         */

        System.out.println("\n==== HISTORIAL RECIENTE DE CITAS (ESTRUCTURA PILA) ====");

        // La pila no atiende pacientes; solo conserva las citas mas recientes.
        PilaHistorialCitas pilaHistorialCitas = new PilaHistorialCitas();

        pilaHistorialCitas.apilar(cita1);
        pilaHistorialCitas.apilar(cita2);
        pilaHistorialCitas.apilar(cita3);

        System.out.println("\nCitas apiladas desde la mas reciente:");
        pilaHistorialCitas.mostrarPila();

        System.out.println("\nCita ubicada en la cima:");
        Cita citaEnLaCima = pilaHistorialCitas.verCima();
        if (citaEnLaCima != null) {
            citaEnLaCima.mostrarDatos();
        } else {
            System.out.println("Cita no encontrada");
        }

        System.out.println("\nSe retira la cita mas reciente del historial:");
        pilaHistorialCitas.desapilar();

        System.out.println("\nHistorial despues de desapilar:");
        pilaHistorialCitas.mostrarPila();

        /*
         * 8. Ordenamiento por intercambio.
         * Bubble Sort compara pacientes vecinos y los intercambia por apellido.
         */

        System.out.println("\n==== ORDENAMIENTO POR ORDEN ALFABETICO (BUBBLE SORT) ====");

        System.out.println("\nPacientes antes de ordenar:");
        pacienteService.mostrarPacientes();

        BubbleSortPacientes ordenamiento = new BubbleSortPacientes();

        ordenamiento.ordenarPorApellido(pacienteService.getPacientes(), pacienteService.getTotalPacientes());

        System.out.println("\nPacientes despues de ordenar por apellido:");
        pacienteService.mostrarPacientes();

        /*
         * 9. Arbol binario de busqueda.
         * Organiza doctores por codigo y permite insertar, buscar, recorrer y eliminar.
         */

        System.out.println("\n==== ARBOL BINARIO DE BUSQUEDA DE DOCTORES ====");

        // El arbol es una estructura no lineal; organiza doctores por codigo.
        ArbolDoctoresBusqueda arbolDoctores = new ArbolDoctoresBusqueda();

        arbolDoctores.insertarDoctor(doctor2);
        arbolDoctores.insertarDoctor(doctor1);
        arbolDoctores.insertarDoctor(doctor3);

        System.out.println("\nRecorrido preorden del arbol:");
        arbolDoctores.recorrerPreOrden();

        System.out.println("\nRecorrido inorden por codigo de doctor:");
        arbolDoctores.recorrerInOrden();

        System.out.println("\nRecorrido postorden del arbol:");
        arbolDoctores.recorrerPostOrden();

        System.out.println("\nBusqueda en arbol por codigo:");
        Doctor doctorEncontradoEnArbol = arbolDoctores.buscarDoctorPorCodigo("DOC-002");
        if (doctorEncontradoEnArbol != null) {
            doctorEncontradoEnArbol.mostrarDatos();
        } else {
            System.out.println("Doctor no encontrado");
        }

        System.out.println("\nEliminacion de un nodo del arbol:");
        arbolDoctores.eliminarDoctorPorCodigo("DOC-001");

        System.out.println("\nArbol despues de eliminar:");
        arbolDoctores.recorrerInOrden();

        /*
         * 10. Recursividad.
         * El reporte avanza posicion por posicion hasta llegar al caso base.
         */

        System.out.println("\n==== REPORTE RECURSIVO ====");

        ReporteRecursivo reporteRecursivo = new ReporteRecursivo();

        System.out.println("\nPacientes mostrados con recursion:");
        reporteRecursivo.mostrarPacientes(pacienteService.getPacientes(), 0, pacienteService.getTotalPacientes());

        System.out.println("\nCitas mostradas con recursion:");
        reporteRecursivo.mostrarCitas(citaService.getCitas(), 0, citaService.getTotalCitas());

        int totalPacientesRecursivo = reporteRecursivo.contarElementos(0, pacienteService.getTotalPacientes());
        int totalCitasRecursivo = reporteRecursivo.contarElementos(0, citaService.getTotalCitas());

        System.out.println("\nTotal de pacientes contado con recursion: " + totalPacientesRecursivo);
        System.out.println("Total de citas contado con recursion: " + totalCitasRecursivo);

        /*
         * 11. QuickSort generico.
         * El algoritmo ordena arreglos usando el criterio enviado por Comparator.
         */

        System.out.println("\n==== ORDENAMIENTO DE CITAS (QUICKSORT) ====");

        Cita[] citasParaOrdenar = {cita3, cita1, cita5, cita2, cita4};
        QuickSort quickSort = new QuickSort();

        System.out.println("\nCitas antes de ordenar por codigo:");
        for (int i = 0; i < citasParaOrdenar.length; i++) {
            System.out.println(citasParaOrdenar[i].getCodigo());
        }

        quickSort.ordenar(
                citasParaOrdenar,
                0,
                citasParaOrdenar.length - 1,
                (citaActual, citaPivote) -> citaActual.getCodigo().compareToIgnoreCase(citaPivote.getCodigo())
        );

        System.out.println("\nCitas despues de ordenar por codigo:");
        for (int i = 0; i < citasParaOrdenar.length; i++) {
            System.out.println(citasParaOrdenar[i].getCodigo());
        }

        /*
         * 12. Tratamiento y receta.
         * El tratamiento representa la indicacion medica posterior a la cita.
         * La receta queda asociada al codigo del tratamiento.
         */

        System.out.println("\n==== TRATAMIENTO Y RECETA MEDICA ====");

        Tratamiento tratamiento1 = new Tratamiento("TRA-001", paciente2.getCodigo(), "Reposo y control por fiebre", 3);
        Receta receta1 = new Receta("REC-001", tratamiento1.getCodigo(), "Paracetamol 500 mg", "Tomar cada 8 horas por 3 dias");

        tratamiento1.mostrarDatos();
        System.out.println();
        receta1.mostrarDatos();

        /*
         * 13. Pagos, herencia y MergeSort.
         * Boleta y Factura heredan de Pago; el servicio los trata como Pago.
         * Luego MergeSort divide el arreglo y fusiona los tramos ordenados por monto.
         */

        System.out.println("\n==== REGISTRO Y ORDENAMIENTO DE PAGOS (MERGESORT) ====");

        // Polimorfismo: se declaran como Pago, pero el objeto real es Boleta o Factura.
        Pago pago1 = new Boleta("PAG-001", cita1.getCodigo(), 120.00, TipoPago.EFECTIVO, LocalDate.now(), paciente1.getDni());
        Pago pago2 = new Factura("PAG-002", cita2.getCodigo(), 85.50, TipoPago.TARJETA, LocalDate.now(), "20601234567");
        Pago pago3 = new Pago("PAG-003", cita3.getCodigo(), 160.00, TipoPago.TRANSFERENCIA, LocalDate.now());
        Pago pago4 = new Boleta("PAG-004", cita4.getCodigo(), 70.00, TipoPago.EFECTIVO, LocalDate.now(), paciente4.getDni());
        Pago pago5 = new Factura("PAG-005", cita5.getCodigo(), 140.00, TipoPago.TARJETA, LocalDate.now(), "20607654321");

        PagoService pagoService = new PagoService();

        pagoService.registrarPago(pago1);
        pagoService.registrarPago(pago2);
        pagoService.registrarPago(pago3);
        pagoService.registrarPago(pago4);
        pagoService.registrarPago(pago5);

        System.out.println("\nPagos antes de ordenar por monto:");
        pagoService.mostrarPagos();

        MergeSortPagos mergeSortPagos = new MergeSortPagos();
        mergeSortPagos.ordenarPorMonto(pagoService.getPagos(), 0, pagoService.getTotalPagos() - 1);

        System.out.println("\nPagos despues de ordenar por monto:");
        pagoService.mostrarPagos();

        /*
         * 14. Persistencia y reportes.
         * Se guardan arreglos en archivos CSV y se muestra un resumen general.
         */

        Consola consola = new Consola();
        consola.mostrarTitulo("PERSISTENCIA EN ARCHIVOS CSV");

        ArchivoPaciente archivoPaciente = new ArchivoPaciente();
        ArchivoCita archivoCita = new ArchivoCita();
        ArchivoPago archivoPago = new ArchivoPago();

        archivoPaciente.guardarPacientes("out/pacientes.csv", pacienteService.getPacientes(), pacienteService.getTotalPacientes());
        archivoCita.guardarCitas("out/citas.csv", citaService.getCitas(), citaService.getTotalCitas());
        archivoPago.guardarPagos("out/pagos.csv", pagoService.getPagos(), pagoService.getTotalPagos());

        System.out.println("Archivos CSV generados en la carpeta out");

        ReporteService reporteService = new ReporteService();
        reporteService.mostrarResumenGeneral(
                pacienteService.getTotalPacientes(),
                citaService.getTotalCitas(),
                pagoService.getTotalPagos()
        );
        reporteService.mostrarAvanceAED();

        /*
         * 15. Utilidades generales.
         * Se validan datos, se generan codigos y se formatean fechas.
         */

        consola.mostrarTitulo("UTILIDADES DEL SISTEMA");

        Validador validador = new Validador();
        GeneradorCodigo generadorCodigo = new GeneradorCodigo();
        UtilidadesFecha utilidadesFecha = new UtilidadesFecha();

        System.out.println("DNI valido: " + validador.esDniValido(paciente2.getDni()));
        System.out.println("Monto valido: " + validador.esMontoValido(pago1.getMonto()));
        System.out.println("Codigo generado: " + generadorCodigo.generar("PAC", 6));
        System.out.println("Fecha formateada: " + utilidadesFecha.formatearFecha(LocalDate.now()));

    }
}
