package pe.com.utp.interfaces;

import pe.com.utp.modelo.persona.Paciente;

/*
 * TAD para una cola de pacientes.
 * Define las operaciones minimas que debe cumplir una estructura FIFO.
 */
public interface TADColaPacientes {

    void encolar(Paciente paciente);

    Paciente desencolar();

    Paciente verFrente();

    boolean estaVacia();

    void mostrarCola();

}
