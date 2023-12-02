package algoritmos;

import java.util.Comparator;

public class ArbolBinario<T> {

    private Nodo<T> raiz;
    private ListaDoblePilaCola<T> lista = new ListaDoblePilaCola<T>();
    private Comparator<T> comparador;

    public ArbolBinario(Comparator<T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    public ListaDoblePilaCola<T> getLista() {
        return this.lista;
    }

    public boolean estaVacio() {
        return this.raiz == null;
    }

    // Insertar en arbol y en lista usando recursividad
    public void insertar(T dato) {
        this.raiz = insertar(this.raiz, dato);
    }

    private Nodo<T> insertar(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            nodo = new Nodo<T>(dato);
            this.lista.insertarAlFinal(dato);
        } else {
            if (this.comparador.compare(dato, nodo.getDato()) < 0) {
                nodo.setIzquierdo_anterior(insertar(nodo.getIzquierdo_anterior(), dato));
            } else {
                nodo.setDerecho_siguiente(insertar(nodo.getDerecho_siguiente(), dato));
            }
        }
        return nodo;
    }

    // Recorridos

    // Inorden
    public ListaDoblePilaCola<T> inorden() {
        ListaDoblePilaCola<T> lista = new ListaDoblePilaCola<T>();
        inorden(this.raiz, lista);
        return lista;
    }
    private void inorden(Nodo<T> nodo, ListaDoblePilaCola<T> lista) {
        if (nodo != null) {
            inorden(nodo.getIzquierdo_anterior(), lista);
            lista.insertarAlFinal(nodo.getDato());
            inorden(nodo.getDerecho_siguiente(), lista);
        }
    }

}
