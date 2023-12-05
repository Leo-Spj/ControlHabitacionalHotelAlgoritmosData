package algoritmos;

import java.util.Comparator;

public class Ordenamiento<T> {

    public ListaDoblePilaCola<T> porInsercion(ListaDoblePilaCola<T> lista, Comparator<T> criterio){
        return porInsercionRecursiva(lista, criterio, 0);
    }
    private ListaDoblePilaCola<T> porInsercionRecursiva(ListaDoblePilaCola<T> lista, Comparator<T> criterio, int indice){
        if (indice < lista.getTamanio()) {
            T datoMenor = lista.obtenerIndice(indice);
            for (int i = indice; i < lista.getTamanio(); i++) {
                if (criterio.compare(lista.obtenerIndice(i), datoMenor) < 0) {
                    datoMenor = lista.obtenerIndice(i);
                    lista.eliminarPorIndice(i);
                    lista.insertarAlInicio(datoMenor);
                }
            }
            return porInsercionRecursiva(lista, criterio, indice + 1);
        } else {
            return lista;
        }
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