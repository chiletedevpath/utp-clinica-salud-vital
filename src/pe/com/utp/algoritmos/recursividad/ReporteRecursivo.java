package pe.com.utp.algoritmos.recursividad;

import pe.com.utp.modelo.cita.Cita;
import pe.com.utp.modelo.persona.Paciente;

public class ReporteRecursivo {

    /*
     * Reporte recursivo sobre arreglos.
     * Cada metodo tiene caso base y llamada recursiva para avanzar de posicion.
     */

    // Recorre pacientes sin usar estructuras repetitivas.
    public void mostrarPacientes(Paciente[] pacientes, int posicion, int totalPacientes) {
        if (posicion >= totalPacientes) {
            return;
        }

        System.out.println();
        pacientes[posicion].mostrarDatos();

        mostrarPacientes(pacientes, posicion + 1, totalPacientes);
    }

    // Recorre citas sin usar estructuras repetitivas.
    public void mostrarCitas(Cita[] citas, int posicion, int totalCitas) {
        if (posicion >= totalCitas) {
            return;
        }

        System.out.println();
        citas[posicion].mostrarDatos();

        mostrarCitas(citas, posicion + 1, totalCitas);
    }

    // Cuenta elementos avanzando hasta el caso base.
    public int contarElementos(int posicion, int totalElementos) {
        if (posicion >= totalElementos) {
            return 0;
        }

        return 1 + contarElementos(posicion + 1, totalElementos);
    }
}
