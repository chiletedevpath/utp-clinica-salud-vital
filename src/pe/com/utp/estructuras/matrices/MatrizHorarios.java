package pe.com.utp.estructuras.matrices;

import pe.com.utp.modelo.persona.Doctor;

/*
 * Matriz de horarios de la clinica.
 * Cada fila representa un doctor y cada columna representa un dia laboral.
 */
public class MatrizHorarios {

    private static final int FILAS = 3;        // Tres doctores para la prueba.
    private static final int COLUMNAS = 5;     // Cinco dias laborales.

    private int[][] matrizCitas;
    private Doctor[] medicosAsignados;

    /*
     * Cada posicion de medicosAsignados corresponde a una fila de matrizCitas.
     * Ejemplo: medicosAsignados[1] trabaja con matrizCitas[1][...].
     */
    public MatrizHorarios(Doctor medicoFila1, Doctor medicoFila2, Doctor medicoFila3) {
        matrizCitas = new int[FILAS][COLUMNAS];
        this.medicosAsignados = new Doctor[FILAS];
        medicosAsignados[0] = medicoFila1;
        medicosAsignados[1] = medicoFila2;
        medicosAsignados[2] = medicoFila3;
    }

    public int buscarFilaPorCodigoMedico(String codigo) {
        // Se busca al doctor para conocer que fila ocupa en la matriz.
        for (int i = 0; i < medicosAsignados.length; i++) {
            if (medicosAsignados[i].getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }

        // -1 indica que el doctor no pertenece a esta agenda.
        return -1;
    }

    // Muestra la matriz como tabla numerica.
    public void mostrarMatriz() {
        System.out.println("\n MATRIZ DE CITAS MEDICAS ");
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(matrizCitas[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Suma por filas para obtener el total de citas por doctor.
    public void mostrarTotalPorDoctor() {
        System.out.println("\n TOTAL DE CITAS POR DOCTOR ");
        for (int i = 0; i < FILAS; i++) {
            int suma = 0;
            for (int j = 0; j < COLUMNAS; j++) {
                suma += matrizCitas[i][j];
            }
            System.out.println("Doctor " + (i + 1) + ": " + suma + " citas");
        }
    }

    // Suma por columnas para obtener el total de citas por dia.
    public void mostrarTotalPorDia() {
        System.out.println("\n TOTAL DE CITAS POR DIA ");
        for (int j = 0; j < COLUMNAS; j++) {
            int suma = 0;

            for (int i = 0; i < FILAS; i++) {
                suma += matrizCitas[i][j];
            }
            System.out.println("Dia " + (j + 1) + ": " + suma + " citas");
        }
    }

    public void registrarCitaEnMatriz(int filaDoctor, int columnaDia) {
        // Se valida la posicion antes de modificar la matriz.
        if (filaDoctor >= 0 && filaDoctor < FILAS && columnaDia >= 0 && columnaDia < COLUMNAS) {
            matrizCitas[filaDoctor][columnaDia]++;
        } else {
            System.out.println("\n Error: indices de matriz fuera de rango.");
        }
    }

    public boolean esPosicionValida(int filaDoctor, int columnaDia) {
        return filaDoctor >= 0 && filaDoctor < FILAS && columnaDia >= 0 && columnaDia < COLUMNAS;
    }
}
