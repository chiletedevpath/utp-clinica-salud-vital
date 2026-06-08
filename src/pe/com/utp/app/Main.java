package pe.com.utp.app;

import pe.com.utp.estructuras.colas.ColaPacientes;
import pe.com.utp.estructuras.listas.ListaPacientes;
import pe.com.utp.estructuras.matrices.MatrizHorarios;
import pe.com.utp.estructuras.pilas.PilaHistorial;
import pe.com.utp.modelo.cita.Cita;
import pe.com.utp.modelo.enums.Especialidad;
import pe.com.utp.modelo.enums.EstadoCita;
import pe.com.utp.modelo.enums.TurnoConsultaAmbulatoria;
import pe.com.utp.modelo.persona.Doctor;
import pe.com.utp.modelo.persona.Paciente;
import pe.com.utp.servicios.CitaService;
import pe.com.utp.servicios.PacienteService;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * Clase principal del sistema.
 * Reune pruebas simples de pacientes, citas, matriz, cola y pila.
 * La idea es mostrar las estructuras vistas en clase con datos de la clinica.
 */

public class Main {

    public static void main(String[] args) {

        // =============================
        // 1. Pacientes en arreglo
        // =============================

        // Servicio que guarda pacientes en un arreglo unidimensional.
        PacienteService pacienteService = new PacienteService();

        // Datos de prueba usados durante la ejecucion del sistema.
        Paciente paciente1 = new Paciente("PAC-001", "73748381", "Adrian", "Pisco", "945834043", "adrian.pisco@gmail.com", 28);
        Paciente paciente2 = new Paciente("PAC-002", "25830900", "Wilmer", "Soto", "985000677", "wilmer.soto@gmail.com", 20);
        Paciente paciente3 = new Paciente("PAC-003", "20005900", "Yenifer", "Alcantara", "983330677", "yenifer.nera@gmail.com", 17);
        Paciente paciente4 = new Paciente("PAC-004", "21001010", "Xiomara", "Rojas", "983741677", "xiomara.nera@gmail.com", 21);

        // Cada registro ocupa la siguiente posicion disponible del arreglo.
        System.out.println("\n==== REGISTRO DE PACIENTES =====");
        pacienteService.registrarPaciente(paciente1);
        pacienteService.registrarPaciente(paciente2);
        pacienteService.registrarPaciente(paciente3);
        System.out.println("=======================\n");

        // Recorrido del arreglo hasta la cantidad real de pacientes registrados.
        pacienteService.mostrarPacientes();

        // =============================
        // 2. Lista enlazada de pacientes
        // =============================

        // La lista enlazada trabaja con nodos y no depende del arreglo anterior.
        ListaPacientes listaPacientes = new ListaPacientes();

        // Cada paciente se inserta al final de la lista.
        listaPacientes.insertarPaciente(paciente1);
        listaPacientes.insertarPaciente(paciente2);
        listaPacientes.insertarPaciente(paciente3);
        listaPacientes.insertarPaciente(paciente4);

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

        // =============================
        // 3. Busqueda lineal
        // =============================

        // Caso 1: DNI que si existe dentro del arreglo.
        String dniABuscar = "73748381";
        Paciente pacienteEncontrado = pacienteService.buscarPacientePorDni(dniABuscar);

        System.out.println("\n==== BUSQUEDA DE PACIENTES POR DNI ====");
        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado con éxito");
            pacienteEncontrado.mostrarDatos();
        } else {
            System.out.println("No se encontró ningún paciente con el DNI: " + dniABuscar);
        }

        // Caso 2: DNI que no existe. Sirve para validar el retorno null.
        String dniNoEncontrado = "00520052";
        Paciente pacienteNoEncontrado = pacienteService.buscarPacientePorDni(dniNoEncontrado);

        if (pacienteNoEncontrado != null) {
            System.out.println("Paciente encontrado con éxito");
            pacienteNoEncontrado.mostrarDatos();
        } else {
            System.out.println("No se encontró ningún paciente con el DNI: " + dniNoEncontrado);
        }

        // =============================
        // 4. Actualizacion y eliminacion
        // =============================

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

        // =============================
        // 5. Citas y matriz de horarios
        // =============================

        // Cada doctor queda asociado a una fila de la matriz.
        Doctor doctor1 = new Doctor("DOC-001", "25413651", "Juan Carlos", "Lozano Leon", "925587412", "000555", "doctor1@gmail.com", Especialidad.MEDICINA_GENERAL, TurnoConsultaAmbulatoria.MANANA);
        Doctor doctor2 = new Doctor("DOC-002", "02578933", "Carlos Elvis", "Tapia Silva", "925587412", "000556", "doctor2@gmail.com", Especialidad.DERMATOLOGIA, TurnoConsultaAmbulatoria.TARDE);
        Doctor doctor3 = new Doctor("DOC-003", "73740025", "David", "Moncada Teran", "925357891", "000557", "doctor3@gmail.com", Especialidad.TRAUMATOLOGIA, TurnoConsultaAmbulatoria.MANANA);

        // La matriz trabaja con 3 doctores y 5 dias de atencion.
        MatrizHorarios matrizHorarios = new MatrizHorarios(doctor1, doctor2, doctor3);

        // Cada cita relaciona paciente, doctor, fecha, hora, estado y motivo.
        Cita cita1 = new Cita("CIT-001", paciente2, doctor1, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Dolor de cuello");
        Cita cita2 = new Cita("CIT-002", paciente2, doctor2, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Presencia de fiebre");
        Cita cita3 = new Cita("CIT-003", paciente3, doctor3, LocalDate.now(), LocalTime.now(), EstadoCita.PROGRAMADA, "Control medico");

        // El servicio de citas actualiza tambien la matriz de horarios.
        CitaService citaService = new CitaService(matrizHorarios);

        // La columna indica el dia laboral donde se registra cada cita.
        citaService.registrarCita(cita1, 1);
        citaService.registrarCita(cita2, 5);
        citaService.registrarCita(cita3, 1);

        // Muestra las citas registradas y luego el resumen en matriz.
        citaService.mostrarCitas();

        System.out.println("\n==== MAPA BIDIMENSIONAL DE DISPONIBILIDAD ====");
        matrizHorarios.mostrarMatriz();

        // =============================
        // 6. Cola de espera
        // =============================

        System.out.println("\n==== SALA DE ESPERA DE PACIENTES (ESTRUCTURA COLA) ====");

        // Inicialización de la cola dinámica para el triaje u orden de atención
        ColaPacientes colaPacientes = new ColaPacientes();

        // Encolamiento secuencial de los pacientes disponibles (Orden de llegada)
        colaPacientes.encolar(paciente2);
        colaPacientes.encolar(paciente3);

        System.out.println("\nPacientes en espera:");
        colaPacientes.mostrarCola();

        // Desencolamiento: Simulación de atención al primer elemento de la estructura
        colaPacientes.desencolar();

        System.out.println("\nPacientes en espera despues de atender:");
        colaPacientes.mostrarCola();

        // =============================
        // 7. Pila de historial
        // =============================

        System.out.println("\n==== HISTORIAL DE CITAS (ESTRUCTURA PILA) ====");

        PilaHistorial pilaHistorial = new PilaHistorial();

        pilaHistorial.apilar(cita1);
        pilaHistorial.apilar(cita2);
        pilaHistorial.apilar(cita3);

        System.out.println("\nCitas apiladas desde la mas reciente:");
        pilaHistorial.mostrarPila();

        System.out.println("\nCita ubicada en la cima:");
        Cita citaEnLaCima = pilaHistorial.verCima();
        if (citaEnLaCima != null) {
            citaEnLaCima.mostrarDatos();
        } else {
            System.out.println("Cita no encontrada");
        }

        System.out.println("\nSe retira la cita mas reciente del historial:");
        pilaHistorial.desapilar();

        System.out.println("\nHistorial despues de desapilar:");
        pilaHistorial.mostrarPila();

    }
}
