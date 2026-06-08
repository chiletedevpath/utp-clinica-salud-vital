package pe.com.utp.interfaces;

import pe.com.utp.modelo.cita.Cita;

/*
 * TAD para una pila de historial de citas.
 * Define las operaciones minimas de una estructura LIFO.
 */
public interface TADPilaHistorial {

    void apilar(Cita cita);

    Cita desapilar();

    Cita verCima();

    boolean estaVacia();

    void mostrarPila();

}
