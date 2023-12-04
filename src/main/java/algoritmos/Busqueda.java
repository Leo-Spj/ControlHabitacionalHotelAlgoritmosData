package algoritmos;

public class Busqueda <T> {

    public int secuencial(ListaDoblePilaCola<T> lista, T dato) {
        for (int i = 0; i < lista.getTamanio(); i++) {
            if (lista.obtenerIndice(i).equals(dato)) {
                return i;
            }
        }
        return -1;
    }

    public int binaria(ListaDoblePilaCola<T> lista, T dato) {
        int inicio = 0;
        int fin = lista.getTamanio() - 1;
        int medio;
        while (inicio <= fin) {
            medio = (inicio + fin) / 2;
            if (lista.obtenerIndice(medio).equals(dato)) {
                return medio;
            } else if (lista.obtenerIndice(medio).hashCode() < dato.hashCode()) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1;
    }

}
