package algoritmos;

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
    }

    private Nodo<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public boolean estaVacio() {
        return this.raiz == null;
    }

    public void insertar(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (estaVacio()) {
            this.raiz = nuevo;
        } else {
            Nodo<T> aux = this.raiz;
            Nodo<T> padre;
            while (true) {
                padre = aux;
                if ((int) dato < (int) aux.dato) {
                    aux = aux.izquierdo;
                    if (aux == null) {
                        padre.izquierdo = nuevo;
                        return;
                    }
                } else {
                    aux = aux.derecho;
                    if (aux == null) {
                        padre.derecho = nuevo;
                        return;
                    }
                }
            }
        }
    }

    //en lista enlazada devolver el primer nodo

    //inOrden





}
