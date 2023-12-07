package algoritmos;

import java.util.Comparator;

public class ListaDoblePilaCola<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamanio;

    public ListaDoblePilaCola() {
        this.primero = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    public T getPrimero() {
        return this.primero.getDato();
    }

    public T getUltimo() {
        return this.ultimo.getDato();
    }

    public int getTamanio() {
        return this.tamanio;
    }


    public boolean estaVacia() {
        return this.primero == null;
    }

    public void insertarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setDerecho_siguiente(this.primero);
            this.primero.setIzquierdo_anterior(nuevo);
            this.primero = nuevo;
        }
        this.tamanio++;
    }

    public void eliminarAlInicio() {
        if (!estaVacia()) {
            if (this.primero.getDerecho_siguiente() == null) {
                this.primero = null;
                this.ultimo = null;
            } else {
                this.primero = this.primero.getDerecho_siguiente();
                this.primero.setIzquierdo_anterior(null);
            }
            this.tamanio--;
        }
    }

    public void insertarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setIzquierdo_anterior(this.ultimo);
            this.ultimo.setDerecho_siguiente(nuevo);
            this.ultimo = nuevo;
        }
        this.tamanio++;
    }

    public void eliminarAlFinal() {
        if (!estaVacia()) {
            if (this.primero.getDerecho_siguiente() == null) {
                this.primero = null;
                this.ultimo = null;
            } else {
                this.ultimo = this.ultimo.getIzquierdo_anterior();
                this.ultimo.setDerecho_siguiente(null);
            }
            this.tamanio--;
        }
    }

    public void insertarOrdenado(T dato, Comparator<T> criterio) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            Nodo<T> aux = this.primero;
            while (aux != null && criterio.compare(dato, aux.getDato()) > 0) {
                aux = aux.getDerecho_siguiente();
            }
            if (aux == null) {
                nuevo.setIzquierdo_anterior(this.ultimo);
                this.ultimo.setDerecho_siguiente(nuevo);
                this.ultimo = nuevo;
            } else if (aux == this.primero) {
                nuevo.setDerecho_siguiente(this.primero);
                this.primero.setIzquierdo_anterior(nuevo);
                this.primero = nuevo;
            } else {
                nuevo.setDerecho_siguiente(aux);
                nuevo.setIzquierdo_anterior(aux.getIzquierdo_anterior());
                aux.getIzquierdo_anterior().setDerecho_siguiente(nuevo);
                aux.setIzquierdo_anterior(nuevo);
            }
        }
        this.tamanio++;
    }

    public void actualizar(T dato, Comparator<T> criterioBusqueda){
        Nodo<T> aux = this.primero;
        while (aux != null && criterioBusqueda.compare(dato, aux.getDato()) != 0) {
            aux = aux.getDerecho_siguiente();
        }
        if (aux != null && criterioBusqueda.compare(dato, aux.getDato()) == 0) {
            aux.setDato(dato);
        }
    }

    public T obtenerIndice(int indice) {
        if (indice >= 0 && indice < this.tamanio) {
            Nodo<T> aux = this.primero;
            int contador = 0;
            while (contador < indice) {
                aux = aux.getDerecho_siguiente();
                contador++;
            }
            return aux.getDato();
        }
        return null;
    }

    public void eliminarPorIndice(int indice) {
        if (indice >= 0 && indice < this.tamanio) {
            if (indice == 0) {
                this.primero = this.primero.getDerecho_siguiente();
                this.primero.setIzquierdo_anterior(null);
            } else if (indice == this.tamanio - 1) {
                this.ultimo = this.ultimo.getIzquierdo_anterior();
                this.ultimo.setDerecho_siguiente(null);
            } else {
                Nodo<T> aux = this.primero;
                int contador = 0;
                while (contador < indice) {
                    aux = aux.getDerecho_siguiente();
                    contador++;
                }
                aux.getIzquierdo_anterior().setDerecho_siguiente(aux.getDerecho_siguiente());
                aux.getDerecho_siguiente().setIzquierdo_anterior(aux.getIzquierdo_anterior());
            }
            this.tamanio--;
        }
    }

}
