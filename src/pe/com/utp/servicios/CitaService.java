package pe.com.utp.servicios;

import pe.com.utp.estructuras.matrices.MatrizHorarios;
import pe.com.utp.modelo.cita.Cita;

public class CitaService {

    // Limite maximo de citas guardadas en el arreglo.
    private static final int CAPACIDAD = 20;

    private Cita[] citas;
    private MatrizHorarios matrizHorarios;

    // Cantidad real de citas registradas.
    private int totalCitas;

    /*
     * La matriz llega configurada con sus doctores.
     * Asi las citas y los reportes de horarios trabajan sobre la misma agenda.
     */
    public CitaService(MatrizHorarios matrizHorarios) {
        this.citas = new Cita[CAPACIDAD];
        this.matrizHorarios = matrizHorarios;
        this.totalCitas = 0;
    }

    // Registra una cita y suma su posicion dentro de la matriz.
    public void registrarCita(Cita nuevaCita, int columnaDia) {

        // El doctor de la cita determina la fila dentro de la matriz.
        String codigoDoctor = nuevaCita.getDoctor().getCodigo();
        int filaEncontrada = matrizHorarios.buscarFilaPorCodigoMedico(codigoDoctor);

        // Si el doctor no esta en la matriz, la cita no se registra.
        if (filaEncontrada == -1) {
            System.out.println("\nMedico no asignado");
            return;
        }

        // Primero se valida que el arreglo todavia tenga espacio.
        if (totalCitas < CAPACIDAD) {

            if (matrizHorarios.esPosicionValida(filaEncontrada, columnaDia - 1)) {
                citas[totalCitas] = nuevaCita;
                totalCitas++;

                // La matriz cuenta la cita en la fila del doctor y columna del dia.
                matrizHorarios.registrarCitaEnMatriz(filaEncontrada, columnaDia - 1);
                System.out.println("\nCita registrada correctamente");
            } else {
                System.out.println("\nPosicion incorrecta");
            }
        } else {
            System.out.println("\nNo hay espacio para mas citas");
        }
    }

    public Cita buscarCitaPorCodigo(String codigoABuscar) {
        // Busqueda lineal sobre las citas realmente registradas.
        for (int i = 0; i < totalCitas; i++) {
            if (citas[i].getCodigo().equalsIgnoreCase(codigoABuscar)) {
                return citas[i];
            }
        }
        System.out.println("\nLa cita con codigo " + codigoABuscar + " no ha sido encontrada");
        return null;
    }

    // Cancela la cita encontrada cambiando solo su estado.
    // Por ahora no descuenta la cita dentro de la matriz de horarios.
    public Cita cancelarCita(String codigoABuscar) {
        for (int i = 0; i < totalCitas; i++) {
            if (citas[i].getCodigo().equalsIgnoreCase(codigoABuscar)) {
                citas[i].cancelar();
                System.out.println("Su cita ha sido cancelada");
                return citas[i];
            }
        }

        System.out.println("No se encontro la cita con codigo: " + codigoABuscar);
        return null;
    }

    public void mostrarCitas() {
        if (totalCitas == 0) {
            System.out.println("No hay citas registradas");
            return;
        }

        for (int i = 0; i < totalCitas; i++) {
            citas[i].mostrarDatos();
            System.out.println("-------------------");
        }
    }

    // Reportes delegados a la matriz de horarios.
    public void mostrarMatrizHorarios() {
        matrizHorarios.mostrarMatriz();
    }

    public void mostrarTotalCitasPorDoctor() {
        matrizHorarios.mostrarTotalPorDoctor();
    }

    public void mostrarTotalCitasPorDia() {
        matrizHorarios.mostrarTotalPorDia();
    }
}
