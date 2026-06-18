package pe.com.utp.app;

import pe.com.utp.algoritmos.busqueda.BusquedaDoctor;
import pe.com.utp.algoritmos.ordenamiento.BubbleSortPacientes;
import pe.com.utp.algoritmos.ordenamiento.MergeSortPagos;
import pe.com.utp.algoritmos.ordenamiento.QuickSort;
import pe.com.utp.algoritmos.recursividad.ReporteRecursivo;
import pe.com.utp.estructuras.arboles.ArbolDoctoresBusqueda;
import pe.com.utp.estructuras.colas.ColaPacientes;
import pe.com.utp.estructuras.listas.ListaCitas;
import pe.com.utp.estructuras.listas.ListaPacientes;
import pe.com.utp.estructuras.matrices.MatrizHorarios;
import pe.com.utp.estructuras.pilas.PilaHistorialCitas;
import pe.com.utp.interfaces.Pagable;
import pe.com.utp.interfaces.Registrable;
import pe.com.utp.interfaces.Reportable;
import pe.com.utp.modelo.cita.Cita;
import pe.com.utp.modelo.cita.HistorialCita;
import pe.com.utp.modelo.enums.Especialidad;
import pe.com.utp.modelo.enums.EstadoCita;
import pe.com.utp.modelo.enums.TipoPago;
import pe.com.utp.modelo.enums.TurnoConsultaAmbulatoria;
import pe.com.utp.modelo.pago.Boleta;
import pe.com.utp.modelo.pago.Factura;
import pe.com.utp.modelo.pago.Pago;
import pe.com.utp.modelo.persona.Administrador;
import pe.com.utp.modelo.persona.Doctor;
import pe.com.utp.modelo.persona.Paciente;
import pe.com.utp.modelo.tratamiento.Receta;
import pe.com.utp.modelo.tratamiento.Tratamiento;
import pe.com.utp.persistencia.ArchivoCita;
import pe.com.utp.persistencia.ArchivoPaciente;
import pe.com.utp.persistencia.ArchivoPago;
import pe.com.utp.servicios.CitaService;
import pe.com.utp.servicios.PacienteService;
import pe.com.utp.servicios.PagoService;
import pe.com.utp.servicios.ReporteService;
import pe.com.utp.util.Consola;
import pe.com.utp.util.GeneradorCodigo;
import pe.com.utp.util.UtilidadesFecha;
import pe.com.utp.util.Validador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/*
 * Clase principal del sistema.
 * Presenta un menu por consola para ejecutar las estructuras y algoritmos AED.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\nCargando datos base del sistema...");
        DatosSistema datos = new DatosSistema();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(scanner);
            ejecutarOpcion(opcion, datos);
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== CLINICA SALUD VITAL ==========");
        System.out.println("1. Gestion de pacientes con arreglo");
        System.out.println("2. Busqueda, actualizacion y eliminacion");
        System.out.println("3. Listas enlazadas de pacientes y citas");
        System.out.println("4. Matriz de horarios y citas medicas");
        System.out.println("5. Busqueda lineal de doctores");
        System.out.println("6. Cola de pacientes");
        System.out.println("7. Pila e historial reciente de citas");
        System.out.println("8. Bubble Sort de pacientes");
        System.out.println("9. Arbol binario de doctores");
        System.out.println("10. Reporte recursivo");
        System.out.println("11. QuickSort de citas");
        System.out.println("12. Tratamiento, receta e historial clinico");
        System.out.println("13. Pagos, herencia y MergeSort");
        System.out.println("14. Persistencia CSV y reportes");
        System.out.println("15. Utilidades y datos administrativos");
        System.out.println("16. Ejecutar demostracion completa");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void ejecutarOpcion(int opcion, DatosSistema datos) {
        switch (opcion) {
            case 1:
                mostrarPacientes(datos);
                break;
            case 2:
                demostrarBusquedaActualizacionEliminacion(datos);
                break;
            case 3:
                demostrarListasEnlazadas(datos);
                break;
            case 4:
                demostrarMatrizYCitas(datos);
                break;
            case 5:
                demostrarBusquedaDoctores(datos);
                break;
            case 6:
                demostrarCola(datos);
                break;
            case 7:
                demostrarPila(datos);
                break;
            case 8:
                demostrarBubbleSort(datos);
                break;
            case 9:
                demostrarArbol(datos);
                break;
            case 10:
                demostrarRecursividad(datos);
                break;
            case 11:
                demostrarQuickSort(datos);
                break;
            case 12:
                demostrarTratamientoReceta(datos);
                break;
            case 13:
                demostrarPagos(datos);
                break;
            case 14:
                demostrarPersistenciaYReportes(datos);
                break;
            case 15:
                demostrarUtilidades(datos);
                break;
            case 16:
                ejecutarDemostracionCompleta(datos);
                break;
            case 0:
                System.out.println("\nSistema finalizado correctamente.");
                break;
            default:
                System.out.println("\nOpcion no valida.");
                break;
        }
    }

    private static void mostrarPacientes(DatosSistema datos) {
        datos.consola.mostrarTitulo("GESTION DE PACIENTES CON ARREGLO");
        datos.pacienteService.mostrarPacientes();
    }

    private static void demostrarBusquedaActualizacionEliminacion(DatosSistema datos) {
        datos.consola.mostrarTitulo("BUSQUEDA, ACTUALIZACION Y ELIMINACION");

        // Se usa una copia temporal para no alterar los datos base del menu.
        PacienteService servicioTemporal = new PacienteService();
        Registrable<Paciente> registroTemporal = servicioTemporal;
        registroTemporal.registrar(datos.paciente1);
        registroTemporal.registrar(datos.paciente2);
        registroTemporal.registrar(datos.paciente3);

        String dniABuscar = "73748381";
        Paciente pacienteEncontrado = servicioTemporal.buscarPacientePorDni(dniABuscar);

        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado con exito");
            pacienteEncontrado.mostrarDatos();
            servicioTemporal.actualizarPaciente(dniABuscar, "111222333", "nuevo.correo@gmail.com");
        } else {
            System.out.println("No se encontro ningun paciente con el DNI: " + dniABuscar);
        }

        servicioTemporal.eliminarPaciente(datos.paciente3.getDni());
        servicioTemporal.mostrarPacientes();
    }

    private static void demostrarListasEnlazadas(DatosSistema datos) {
        datos.consola.mostrarTitulo("LISTAS ENLAZADAS");

        System.out.println("\nLista enlazada de pacientes:");
        datos.listaPacientes.mostrarPacientes();

        System.out.println("\nTotal de pacientes en lista: " + datos.listaPacientes.contarPacientes());

        System.out.println("\nLista enlazada de citas:");
        datos.listaCitas.mostrarCitas();

        Cita citaEncontrada = datos.listaCitas.buscarCitaPorCodigo("CIT-003");
        if (citaEncontrada != null) {
            System.out.println("\nCita encontrada en lista enlazada:");
            citaEncontrada.mostrarDatos();
        }
    }

    private static void demostrarMatrizYCitas(DatosSistema datos) {
        datos.consola.mostrarTitulo("MATRIZ DE HORARIOS Y CITAS");
        datos.citaService.mostrarCitas();
        datos.citaService.mostrarMatrizHorarios();
        datos.citaService.mostrarTotalCitasPorDoctor();
        datos.citaService.mostrarTotalCitasPorDia();
    }

    private static void demostrarBusquedaDoctores(DatosSistema datos) {
        datos.consola.mostrarTitulo("BUSQUEDA LINEAL DE DOCTORES");
        BusquedaDoctor busquedaDoctor = new BusquedaDoctor();
        Doctor doctorEncontrado = busquedaDoctor.buscarPorCodigo(datos.doctores, datos.doctores.length, "DOC-002");

        if (doctorEncontrado != null) {
            System.out.println("Doctor encontrado con exito");
            doctorEncontrado.mostrarDatos();
        } else {
            System.out.println("Doctor no encontrado");
        }
    }

    private static void demostrarCola(DatosSistema datos) {
        datos.consola.mostrarTitulo("SALA DE ESPERA DE PACIENTES");

        System.out.println("\nPacientes en espera:");
        datos.colaPacientes.mostrarCola();

        System.out.println("\nPaciente ubicado al frente:");
        Paciente pacienteFrente = datos.colaPacientes.verFrente();
        if (pacienteFrente != null) {
            pacienteFrente.mostrarDatos();
        }
    }

    private static void demostrarPila(DatosSistema datos) {
        datos.consola.mostrarTitulo("HISTORIAL RECIENTE DE CITAS");

        System.out.println("\nCitas apiladas desde la mas reciente:");
        datos.pilaHistorialCitas.mostrarPila();

        System.out.println("\nCita ubicada en la cima:");
        Cita citaEnLaCima = datos.pilaHistorialCitas.verCima();
        if (citaEnLaCima != null) {
            citaEnLaCima.mostrarDatos();
        }
    }

    private static void demostrarBubbleSort(DatosSistema datos) {
        datos.consola.mostrarTitulo("ORDENAMIENTO POR APELLIDO - BUBBLE SORT");

        System.out.println("\nPacientes antes de ordenar:");
        datos.pacienteService.mostrarPacientes();

        BubbleSortPacientes ordenamiento = new BubbleSortPacientes();
        ordenamiento.ordenarPorApellido(datos.pacienteService.getPacientes(), datos.pacienteService.getTotalPacientes());

        System.out.println("\nPacientes despues de ordenar por apellido:");
        datos.pacienteService.mostrarPacientes();
    }

    private static void demostrarArbol(DatosSistema datos) {
        datos.consola.mostrarTitulo("ARBOL BINARIO DE BUSQUEDA DE DOCTORES");

        System.out.println("\nRecorrido preorden:");
        datos.arbolDoctores.recorrerPreOrden();

        System.out.println("\nRecorrido inorden:");
        datos.arbolDoctores.recorrerInOrden();

        System.out.println("\nRecorrido postorden:");
        datos.arbolDoctores.recorrerPostOrden();

        System.out.println("\nBusqueda en arbol por codigo:");
        Doctor doctorEncontrado = datos.arbolDoctores.buscarDoctorPorCodigo("DOC-002");
        if (doctorEncontrado != null) {
            doctorEncontrado.mostrarDatos();
        }
    }

    private static void demostrarRecursividad(DatosSistema datos) {
        datos.consola.mostrarTitulo("REPORTE RECURSIVO");

        ReporteRecursivo reporteRecursivo = new ReporteRecursivo();

        System.out.println("\nPacientes mostrados con recursion:");
        reporteRecursivo.mostrarPacientes(datos.pacienteService.getPacientes(), 0, datos.pacienteService.getTotalPacientes());

        System.out.println("\nCitas mostradas con recursion:");
        reporteRecursivo.mostrarCitas(datos.citaService.getCitas(), 0, datos.citaService.getTotalCitas());

        System.out.println("\nTotal de pacientes contado con recursion: "
                + reporteRecursivo.contarElementos(0, datos.pacienteService.getTotalPacientes()));
        System.out.println("Total de citas contado con recursion: "
                + reporteRecursivo.contarElementos(0, datos.citaService.getTotalCitas()));
    }

    private static void demostrarQuickSort(DatosSistema datos) {
        datos.consola.mostrarTitulo("ORDENAMIENTO DE CITAS - QUICKSORT");

        Cita[] citasParaOrdenar = {
                datos.cita3,
                datos.cita1,
                datos.cita5,
                datos.cita2,
                datos.cita4
        };

        System.out.println("\nCitas antes de ordenar por codigo:");
        mostrarCodigosCitas(citasParaOrdenar);

        QuickSort quickSort = new QuickSort();
        quickSort.ordenar(
                citasParaOrdenar,
                0,
                citasParaOrdenar.length - 1,
                (citaActual, citaPivote) -> citaActual.getCodigo().compareToIgnoreCase(citaPivote.getCodigo())
        );

        System.out.println("\nCitas despues de ordenar por codigo:");
        mostrarCodigosCitas(citasParaOrdenar);
    }

    private static void mostrarCodigosCitas(Cita[] citas) {
        for (int i = 0; i < citas.length; i++) {
            System.out.println(citas[i].getCodigo());
        }
    }

    private static void demostrarTratamientoReceta(DatosSistema datos) {
        datos.consola.mostrarTitulo("TRATAMIENTO, RECETA E HISTORIAL CLINICO");
        datos.tratamiento1.mostrarDatos();
        System.out.println();
        datos.receta1.mostrarDatos();
        System.out.println();
        datos.historialCita1.mostrarDatos();
    }

    private static void demostrarPagos(DatosSistema datos) {
        datos.consola.mostrarTitulo("PAGOS, HERENCIA Y MERGESORT");

        System.out.println("\nPagos antes de ordenar por monto:");
        datos.pagoService.mostrarPagos();

        MergeSortPagos mergeSortPagos = new MergeSortPagos();
        mergeSortPagos.ordenarPorMonto(datos.pagoService.getPagos(), 0, datos.pagoService.getTotalPagos() - 1);

        System.out.println("\nPagos despues de ordenar por monto:");
        datos.pagoService.mostrarPagos();
    }

    private static void demostrarPersistenciaYReportes(DatosSistema datos) {
        datos.consola.mostrarTitulo("PERSISTENCIA CSV Y REPORTES");

        ArchivoPaciente archivoPaciente = new ArchivoPaciente();
        ArchivoCita archivoCita = new ArchivoCita();
        ArchivoPago archivoPago = new ArchivoPago();

        archivoPaciente.guardarPacientes("out/pacientes.csv", datos.pacienteService.getPacientes(), datos.pacienteService.getTotalPacientes());
        archivoCita.guardarCitas("out/citas.csv", datos.citaService.getCitas(), datos.citaService.getTotalCitas());
        archivoPago.guardarPagos("out/pagos.csv", datos.pagoService.getPagos(), datos.pagoService.getTotalPagos());

        System.out.println("Archivos CSV generados en la carpeta out");

        ReporteService reporteService = new ReporteService();
        Reportable reporteAED = reporteService;
        reporteService.mostrarResumenGeneral(
                datos.pacienteService.getTotalPacientes(),
                datos.citaService.getTotalCitas(),
                datos.pagoService.getTotalPagos()
        );
        reporteAED.generarReporte();
    }

    private static void demostrarUtilidades(DatosSistema datos) {
        datos.consola.mostrarTitulo("UTILIDADES Y DATOS ADMINISTRATIVOS");

        Validador validador = new Validador();
        GeneradorCodigo generadorCodigo = new GeneradorCodigo();
        UtilidadesFecha utilidadesFecha = new UtilidadesFecha();

        System.out.println("DNI valido: " + validador.esDniValido(datos.paciente2.getDni()));
        System.out.println("Monto valido: " + validador.esMontoValido(datos.pago1.getMonto()));
        System.out.println("Texto valido: " + validador.esTextoValido(datos.paciente2.getNombres()));
        System.out.println("Codigo generado: " + generadorCodigo.generar("PAC", 6));
        System.out.println("Fecha formateada: " + utilidadesFecha.formatearFecha(LocalDate.now()));
        System.out.println();
        datos.administrador.mostrarDatos();
    }

    private static void ejecutarDemostracionCompleta(DatosSistema datos) {
        mostrarPacientes(datos);
        demostrarBusquedaActualizacionEliminacion(datos);
        demostrarListasEnlazadas(datos);
        demostrarMatrizYCitas(datos);
        demostrarBusquedaDoctores(datos);
        demostrarCola(datos);
        demostrarPila(datos);
        demostrarBubbleSort(datos);
        demostrarArbol(datos);
        demostrarRecursividad(datos);
        demostrarQuickSort(datos);
        demostrarTratamientoReceta(datos);
        demostrarPagos(datos);
        demostrarPersistenciaYReportes(datos);
        demostrarUtilidades(datos);
    }

    /*
     * Datos base del sistema.
     * Centraliza objetos y estructuras para que el menu trabaje sobre el mismo estado.
     */
    private static class DatosSistema {

        private final Consola consola = new Consola();

        private final Paciente paciente1 = new Paciente("PAC-001", "73748381", "Adrian", "Pisco", "945834043", "adrian.pisco@gmail.com", 28);
        private final Paciente paciente2 = new Paciente("PAC-002", "25830900", "Wilmer", "Soto", "985000677", "wilmer.soto@gmail.com", 20);
        private final Paciente paciente3 = new Paciente("PAC-003", "20005900", "Yenifer", "Alcantara", "983330677", "yenifer.nera@gmail.com", 17);
        private final Paciente paciente4 = new Paciente("PAC-004", "21001010", "Xiomara", "Rojas", "983741677", "xiomara.nera@gmail.com", 21);
        private final Paciente paciente5 = new Paciente("PAC-005", "80701010", "Jorge", "Flores", "999652000", "jorge.flores@gmail.com", 35);

        private final Doctor doctor1 = new Doctor("DOC-001", "25413651", "Juan Carlos", "Lozano Leon", "925587412", "000555", "doctor1@gmail.com", Especialidad.MEDICINA_GENERAL, TurnoConsultaAmbulatoria.MANANA);
        private final Doctor doctor2 = new Doctor("DOC-002", "02578933", "Carlos Elvis", "Tapia Silva", "925587412", "000556", "doctor2@gmail.com", Especialidad.DERMATOLOGIA, TurnoConsultaAmbulatoria.TARDE);
        private final Doctor doctor3 = new Doctor("DOC-003", "73740025", "David", "Moncada Teran", "925357891", "000557", "doctor3@gmail.com", Especialidad.TRAUMATOLOGIA, TurnoConsultaAmbulatoria.MANANA);
        private final Doctor[] doctores = {doctor1, doctor2, doctor3};

        private final Administrador administrador = new Administrador("ADM-001", "46667777", "Lucia", "Ramirez", "987654321", "lucia.ramirez@saludvital.com", "Coordinadora de admision");

        private final Cita cita1 = new Cita("CIT-001", paciente2, doctor1, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Dolor de cuello");
        private final Cita cita2 = new Cita("CIT-002", paciente2, doctor2, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Presencia de fiebre");
        private final Cita cita3 = new Cita("CIT-003", paciente3, doctor3, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");
        private final Cita cita4 = new Cita("CIT-004", paciente4, doctor2, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");
        private final Cita cita5 = new Cita("CIT-005", paciente5, doctor1, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");

        private final Pago pago1 = new Boleta("PAG-001", cita1.getCodigo(), 120.00, TipoPago.EFECTIVO, LocalDate.now(), paciente1.getDni());
        private final Pago pago2 = new Factura("PAG-002", cita2.getCodigo(), 85.50, TipoPago.TARJETA, LocalDate.now(), "20601234567");
        private final Pago pago3 = new Pago("PAG-003", cita3.getCodigo(), 160.00, TipoPago.TRANSFERENCIA, LocalDate.now());
        private final Pago pago4 = new Boleta("PAG-004", cita4.getCodigo(), 70.00, TipoPago.EFECTIVO, LocalDate.now(), paciente4.getDni());
        private final Pago pago5 = new Factura("PAG-005", cita5.getCodigo(), 140.00, TipoPago.TARJETA, LocalDate.now(), "20607654321");

        private final Tratamiento tratamiento1 = new Tratamiento("TRA-001", paciente2.getCodigo(), "Reposo y control por fiebre", 3);
        private final Receta receta1 = new Receta("REC-001", tratamiento1.getCodigo(), "Paracetamol 500 mg", "Tomar cada 8 horas por 3 dias");
        private final HistorialCita historialCita1 = new HistorialCita("HIS-001", paciente2.getCodigo(), "Paciente atendido por fiebre y derivado a tratamiento ambulatorio.");

        private final PacienteService pacienteService = new PacienteService();
        private final MatrizHorarios matrizHorarios = new MatrizHorarios(doctor1, doctor2, doctor3);
        private final CitaService citaService = new CitaService(matrizHorarios);
        private final PagoService pagoService = new PagoService();

        private final ListaPacientes listaPacientes = new ListaPacientes();
        private final ListaCitas listaCitas = new ListaCitas();
        private final ColaPacientes colaPacientes = new ColaPacientes();
        private final PilaHistorialCitas pilaHistorialCitas = new PilaHistorialCitas();
        private final ArbolDoctoresBusqueda arbolDoctores = new ArbolDoctoresBusqueda();

        private DatosSistema() {
            cargarPacientes();
            cargarCitas();
            cargarPagos();
            cargarEstructurasDinamicas();
        }

        private void cargarPacientes() {
            Registrable<Paciente> registroPacientes = pacienteService;
            registroPacientes.registrar(paciente1);
            registroPacientes.registrar(paciente2);
            registroPacientes.registrar(paciente3);
            registroPacientes.registrar(paciente4);
            registroPacientes.registrar(paciente5);
        }

        private void cargarCitas() {
            citaService.registrarCita(cita1, 1);
            citaService.registrarCita(cita2, 5);
            citaService.registrarCita(cita3, 1);
            citaService.registrarCita(cita4, 2);
            citaService.registrarCita(cita5, 3);
        }

        private void cargarPagos() {
            Pagable procesadorPago = pagoService;
            procesadorPago.procesarPago(pago1);
            procesadorPago.procesarPago(pago2);
            procesadorPago.procesarPago(pago3);
            procesadorPago.procesarPago(pago4);
            procesadorPago.procesarPago(pago5);
        }

        private void cargarEstructurasDinamicas() {
            listaPacientes.insertarPaciente(paciente1);
            listaPacientes.insertarPaciente(paciente2);
            listaPacientes.insertarPaciente(paciente3);
            listaPacientes.insertarPaciente(paciente4);
            listaPacientes.insertarPaciente(paciente5);

            listaCitas.insertarCita(cita1);
            listaCitas.insertarCita(cita2);
            listaCitas.insertarCita(cita3);
            listaCitas.insertarCita(cita4);
            listaCitas.insertarCita(cita5);

            colaPacientes.encolar(paciente2);
            colaPacientes.encolar(paciente3);

            pilaHistorialCitas.apilar(cita1);
            pilaHistorialCitas.apilar(cita2);
            pilaHistorialCitas.apilar(cita3);

            arbolDoctores.insertarDoctor(doctor2);
            arbolDoctores.insertarDoctor(doctor1);
            arbolDoctores.insertarDoctor(doctor3);
        }
    }
}
