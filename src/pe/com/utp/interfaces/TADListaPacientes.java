package pe.com.utp.interfaces;

import pe.com.utp.modelo.persona.Paciente;

/*
 * TAD para una estructura de pacientes.
 * Define las operaciones minimas que debe cumplir la lista enlazada.
 */
public interface TADListaPacientes {

    void insertarPaciente(Paciente paciente);

    Paciente buscarPacientePorDni(String dni);

    void eliminarPaciente(String dni);

    boolean estaVacia();

    int contarPacientes();

    void mostrarPacientes();
}
