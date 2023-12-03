package algoritmos;

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

    public void intercambiar(int i, int minimo) {
        Nodo<T> aux = this.primero;
        Nodo<T> aux2 = this.primero;
        int contador = 0;
        while (contador < i) {
            aux = aux.getDerecho_siguiente();
            contador++;
        }
        contador = 0;
        while (contador < minimo) {
            aux2 = aux2.getDerecho_siguiente();
            contador++;
        }
        T dato = aux.getDato();
        aux.setDato(aux2.getDato());
        aux2.setDato(dato);
    }
}
