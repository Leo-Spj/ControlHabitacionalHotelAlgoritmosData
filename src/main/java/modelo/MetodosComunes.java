package modelo;

import algoritmos.Busqueda;
import algoritmos.Ordenamiento;


public abstract class MetodosComunes<T> {

    private Ordenamiento<T> ordenamiento = new Ordenamiento<>();
    private Busqueda<T> busqueda = new Busqueda<>();

    public Ordenamiento<T> getOrdenamiento() {
        return ordenamiento;
    }

    public Busqueda<T> getBusqueda() {
        return busqueda;
    }

}
