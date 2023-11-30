package algoritmos;

public class NodoLista<T> {

    private T dato;
    private NodoLista<T> siguiente;
    private NodoLista<T> anterior;

    public NodoLista(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoLista<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoLista<T> anterior) {
        this.anterior = anterior;
    }

}
