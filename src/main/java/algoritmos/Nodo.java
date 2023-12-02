package algoritmos;

public class Nodo<T> {

    private T dato;
    private Nodo<T> derecho_siguiente;
    private Nodo<T> izquierdo_anterior;

    public Nodo(T dato) {
        this.dato = dato;
        this.derecho_siguiente = null;
        this.izquierdo_anterior = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getDerecho_siguiente() {
        return derecho_siguiente;
    }

    public void setDerecho_siguiente(Nodo<T> derecho_siguiente) {
        this.derecho_siguiente = derecho_siguiente;
    }

    public Nodo<T> getIzquierdo_anterior() {
        return izquierdo_anterior;
    }

    public void setIzquierdo_anterior(Nodo<T> izquierdo_anterior) {
        this.izquierdo_anterior = izquierdo_anterior;
    }

}
