package pe.com.utp.interfaces;

import pe.com.utp.modelo.cita.Cita;

/*
 * TAD para una pila de citas recientes.
 * Define las operaciones minimas de una estructura LIFO.
 * Se usa como historial tecnico, no como orden de atencion de pacientes.
 */
public interface TADPilaHistorialCitas {

    void apilar(Cita cita);

    Cita desapilar();

    Cita verCima();

    boolean estaVacia();

    void mostrarPila();

}
