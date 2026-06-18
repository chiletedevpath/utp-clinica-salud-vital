package pe.com.utp.algoritmos.ordenamiento;

import java.util.Comparator;

public class QuickSort {

    /*
     * QuickSort reutilizable para arreglos de objetos.
     * El criterio de ordenamiento llega por Comparator.
     */

    public <T> void ordenar(T[] arreglo, int inicio, int fin, Comparator<T> comparador) {
        if (arreglo == null || comparador == null) {
            return;
        }

        if (inicio < fin) {
            int posicionPivote = particionar(arreglo, inicio, fin, comparador);

            ordenar(arreglo, inicio, posicionPivote - 1, comparador);
            ordenar(arreglo, posicionPivote + 1, fin, comparador);
        }
    }

    private <T> int particionar(T[] arreglo, int inicio, int fin, Comparator<T> comparador) {
        T pivote = arreglo[fin];
        int indiceMenor = inicio - 1;

        for (int indiceActual = inicio; indiceActual < fin; indiceActual++) {
            if (comparador.compare(arreglo[indiceActual], pivote) <= 0) {
                indiceMenor++;
                intercambiar(arreglo, indiceMenor, indiceActual);
            }
        }

        intercambiar(arreglo, indiceMenor + 1, fin);
        return indiceMenor + 1;
    }

    private <T> void intercambiar(T[] arreglo, int posicionA, int posicionB) {
        T auxiliar = arreglo[posicionA];

        arreglo[posicionA] = arreglo[posicionB];
        arreglo[posicionB] = auxiliar;
    }
}
