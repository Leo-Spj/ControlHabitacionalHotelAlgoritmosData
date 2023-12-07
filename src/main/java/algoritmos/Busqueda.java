package algoritmos;

import java.util.Comparator;

public class Busqueda <T> {

    public int secuencial(ListaDoblePilaCola<T> lista, T dato, Comparator<T> criterio) {
        for (int i = 0; i < lista.getTamanio(); i++) {
            if (criterio.compare(lista.obtenerPorIndice(i), dato) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int secuencialReversa(ListaDoblePilaCola<T> lista, T dato, Comparator<T> criterio) {
        for (int i = lista.getTamanio() - 1; i >= 0; i--) {
            if (criterio.compare(lista.obtenerPorIndice(i), dato) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int binaria(ListaDoblePilaCola<T> lista, T dato, Comparator<T> criterio) {
        int inicio = 0;
        int fin = lista.getTamanio() - 1;
        int medio;

        while (inicio <= fin) {
            medio = (inicio + fin) / 2;
            if (criterio.compare(lista.obtenerPorIndice(medio), dato) == 0) {
                return medio;
            } else if (criterio.compare(lista.obtenerPorIndice(medio), dato) > 0) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }
        return -1;
    }

}
