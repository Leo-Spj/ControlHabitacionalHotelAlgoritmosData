package algoritmos;

import java.util.Comparator;

public class Ordenamiento<T> {

    public ListaDoblePilaCola<T> porInsercion(ListaDoblePilaCola<T> lista, Comparator<T> criterio) {

        ListaDoblePilaCola<T> listaAuxiliar = new ListaDoblePilaCola<>();

        while (!lista.estaVacia()) {

            T dato = lista.getPrimero();
            lista.eliminarAlInicio();

            while (!listaAuxiliar.estaVacia() && criterio.compare(dato, listaAuxiliar.getUltimo()) < 0) {
                lista.insertarAlInicio(listaAuxiliar.getUltimo());
                listaAuxiliar.eliminarAlFinal();
            }

            listaAuxiliar.insertarAlFinal(dato);
        }

        while (!listaAuxiliar.estaVacia()) {
            lista.insertarAlInicio(listaAuxiliar.getUltimo());
            listaAuxiliar.eliminarAlFinal();
        }

        return lista;
    }

    public ListaDoblePilaCola<T> deBurbuja(ListaDoblePilaCola<T> lista, Comparator<T> criterio) {

        ListaDoblePilaCola<T> listaAuxiliar = new ListaDoblePilaCola<>();

        while (!lista.estaVacia()) {

            T dato = lista.getPrimero();
            lista.eliminarAlInicio();

            while (!listaAuxiliar.estaVacia() && criterio.compare(dato, listaAuxiliar.getUltimo()) < 0) {
                lista.insertarAlInicio(listaAuxiliar.getUltimo());
                listaAuxiliar.eliminarAlFinal();
            }

            listaAuxiliar.insertarAlFinal(dato);
        }

        while (!listaAuxiliar.estaVacia()) {
            lista.insertarAlInicio(listaAuxiliar.getUltimo());
            listaAuxiliar.eliminarAlFinal();
        }

        return lista;
    }

}