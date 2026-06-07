package pe.com.utp.servicios;

import pe.com.utp.modelo.persona.Paciente;

// Servicio que administra pacientes usando un arreglo de tamaño fijo.
public class PacienteService {

    // Limite maximo de pacientes que se pueden guardar en memoria.
    private static final int CAPACIDAD = 100;

    // Arreglo principal donde se almacenan los objetos Paciente.
    private Paciente[] pacientes;

    // Cantidad real de posiciones ocupadas dentro del arreglo.
    private int totalPacientes;

    // Al iniciar el servicio, el arreglo esta vacio y el contador queda en cero.
    public PacienteService() {
        pacientes = new Paciente[CAPACIDAD];
        totalPacientes = 0;
    }

    // Guarda un paciente en la siguiente posicion libre del arreglo.
    public void registrarPaciente(Paciente paciente) {
        // Si el arreglo esta lleno, no se puede registrar otro paciente.
        if (totalPacientes >= CAPACIDAD) {
            System.out.println("No hay espacio disponible para registrar más pacientes");
            return;
        }

        // totalPacientes indica la proxima posicion disponible.
        pacientes[totalPacientes] = paciente;

        // Luego del registro, aumenta la cantidad real de pacientes.
        totalPacientes++;

        System.out.println("Paciente registrado correctamente");
    }

    // Muestra solo las posiciones que tienen pacientes registrados.
    public void mostrarPacientes() {

        // Si el contador esta en cero, el arreglo no tiene datos utiles.
        if (totalPacientes == 0) {
            System.out.println("No existen pacientes registrados");
            return;
        }
        System.out.println("==== LISTA DE PACIENTES ====");

        // El recorrido termina antes de las posiciones vacias del arreglo.
        for (int i = 0; i < totalPacientes; i++) {
            System.out.println();
            pacientes[i].mostrarDatos();
        }
    }

    // Busca un paciente por DNI usando recorrido lineal.
    public Paciente buscarPacientePorDni(String dni) {

        // Solo se revisan las posiciones ocupadas.
        for (int i = 0; i < totalPacientes; i++) {

            // El DNI permite identificar al paciente dentro del arreglo.
            if (pacientes[i].getDni().equals(dni)) {
                return pacientes[i];
            }
        }

        // null indica que no se encontro ninguna coincidencia.
        return null;
    }

    // Actualiza telefono y correo despues de ubicar al paciente por DNI.
    public void actualizarPaciente(String dni, String nuevoTelefono, String nuevoCorreo) {

        // Se reutiliza la busqueda para no duplicar el recorrido.
        Paciente pacienteEncontrado = buscarPacientePorDni(dni);

        // Si no existe, no hay objeto que modificar.
        if (pacienteEncontrado == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        // Se modifican los datos del mismo objeto guardado en el arreglo.
        pacienteEncontrado.setTelefono(nuevoTelefono);
        pacienteEncontrado.setCorreo(nuevoCorreo);

        System.out.println("Paciente actualizado correctamente");
    }

    // Elimina un paciente por DNI y compacta el arreglo.
    public void eliminarPaciente(String dni) {
        int posicion = -1;

        // Primero se busca la posicion donde esta el paciente.
        for (int i = 0; i < totalPacientes; i++) {
            if (pacientes[i].getDni().equals(dni)) {
                posicion = i;
                break;
            }
        }

        // Si la posicion sigue en -1, el paciente no fue encontrado.
        if (posicion == -1) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        // Los elementos posteriores avanzan una posicion hacia la izquierda.
        for (int i = posicion; i < totalPacientes - 1; i++) {
            pacientes[i] = pacientes[i + 1];
        }

        // La ultima posicion logica queda libre despues del desplazamiento.
        pacientes[totalPacientes - 1] = null;

        // Se reduce la cantidad real de pacientes registrados.
        totalPacientes--;

        System.out.println("Paciente eliminado correctamente.");
    }
}
