package algoritmos;

import java.util.Comparator;

public class ArbolBinario<T> {

    private static class Nodo<T> {
        private T dato;
        private Nodo<T> izquierdo;
        private Nodo<T> derecho;

        public Nodo(T dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public Nodo<T> getIzquierdo() {
            return izquierdo;
        }

        public void setIzquierdo(Nodo<T> izquierdo) {
            this.izquierdo = izquierdo;
        }

        public Nodo<T> getDerecho() {
            return derecho;
        }

        public void setDerecho(Nodo<T> derecho) {
            this.derecho = derecho;
        }

        public T getDato() {
            return dato;
        }
    }

    private Nodo<T> raiz;
    private Comparator<T> comparador;

    public ArbolBinario(Comparator<T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }
    public boolean estaVacio(){
        return this.raiz == null;
    }

    public void insertar(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (estaVacio()) {
            this.raiz = nuevo;
        } else {
            insertarRecursivo(this.raiz, nuevo);
        }
    }

    private void insertarRecursivo(Nodo<T> raizActual, Nodo<T> nuevo) {
        if (comparador.compare(nuevo.getDato(), raizActual.getDato()) < 0) {
            if (raizActual.getIzquierdo() == null) {
                raizActual.setIzquierdo(nuevo);
            } else {
                insertarRecursivo(raizActual.getIzquierdo(), nuevo);
            }
        } else {
            if (raizActual.getDerecho() == null) {
                raizActual.setDerecho(nuevo);
            } else {
                insertarRecursivo(raizActual.getDerecho(), nuevo);
            }
        }
    }


    // obteniendo listas enlazadas doble de:

    // preorden:
    public ListaEnlazadaDoble<T> preorden(){
        ListaEnlazadaDoble<T> lista = new ListaEnlazadaDoble<T>();
        preorden(this.raiz, lista);
        return lista;
    }
    private void preorden(Nodo<T> nodo, ListaEnlazadaDoble<T> lista){
        if(nodo != null){
            lista.insertarAlFinal((T) nodo.getDato());
            preorden(nodo.getIzquierdo(), lista);
            preorden(nodo.getDerecho(), lista);
        }
    }

    // inorden:
    public ListaEnlazadaDoble<T> inorden(){
        ListaEnlazadaDoble<T> lista = new ListaEnlazadaDoble<T>();
        inorden(this.raiz, lista);
        return lista;
    }
    private void inorden(Nodo<T> nodo, ListaEnlazadaDoble<T> lista){
        if(nodo != null){
            inorden(nodo.getIzquierdo(), lista);
            lista.insertarAlFinal((T) nodo.getDato());
            inorden(nodo.getDerecho(), lista);
        }
    }

    // postorden:
    public ListaEnlazadaDoble<T> postorden(){
        ListaEnlazadaDoble<T> lista = new ListaEnlazadaDoble<T>();
        postorden(this.raiz, lista);
        return lista;
    }
    private void postorden(Nodo<T> nodo, ListaEnlazadaDoble<T> lista){
        if(nodo != null){
            postorden(nodo.getIzquierdo(), lista);
            postorden(nodo.getDerecho(), lista);
            lista.insertarAlFinal((T) nodo.getDato());
        }
    }





}
