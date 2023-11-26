package controlador.algoritmos;

import modelo.Habitacion;

// Haciendola generica podremos usarla para cualquier tipo de dato, nos conviene
public class ListaEnlazada <T> {

    // Creé esta clase estatica Nodo que nos servirá para crear los nodos de la lista sin tener que crear una clase Nodo afuera
    // y es generica para que pueda ser usada por cualquier tipo de dato
    private static class Nodo<T> {
        private T dato;
        private Nodo<T> siguiente;
        private Nodo<T> anterior;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamanio;

    public ListaEnlazada() {
        this.primero = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return this.primero == null;
    }

    public int getTamanio() {
        return this.tamanio;
    }

    public void insertarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.siguiente = this.primero;
            this.primero.anterior = nuevo;
            this.primero = nuevo;
        }
        this.tamanio++;
    }

    public void insertarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.anterior = this.ultimo;
            this.ultimo.siguiente = nuevo;
            this.ultimo = nuevo;
        }
        this.tamanio++;
    }

    public void eliminarAlInicio() {
        if (!estaVacia()) {
            this.primero = this.primero.siguiente;
            this.primero.anterior = null;
            this.tamanio--;
        }
    }

    public void eliminarAlFinal() {
        if (!estaVacia()) {
            this.ultimo = this.ultimo.anterior;
            this.ultimo.siguiente = null;
            this.tamanio--;
        }
    }

    public void eliminar(T dato) {
        if (!estaVacia()) {
            if (this.primero.dato.equals(dato)) {
                eliminarAlInicio();
            } else if (this.ultimo.dato.equals(dato)) {
                eliminarAlFinal();
            } else {
                Nodo<T> aux = this.primero;
                while (aux != null && !aux.dato.equals(dato)) {
                    aux = aux.siguiente;
                }
                if (aux != null) {
                    aux.anterior.siguiente = aux.siguiente;
                    aux.siguiente.anterior = aux.anterior;
                    this.tamanio--;
                }
            }
        }
    }

    public T obtener(int indice) {
        if (!estaVacia()) {
            Nodo<T> aux = this.primero;
            int i = 0;
            while (aux != null && i < indice) {
                aux = aux.siguiente;
                i++;
            }
            if (aux != null) {
                return aux.dato;
            }
        }
        return null;
    }

}
