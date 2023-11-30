package algoritmos;

// Haciendola generica podremos usarla para cualquier tipo de dato, nos conviene
public class ListaEnlazadaDoble<T> {

    // NodoLista
    private NodoLista<T> primero;
    private NodoLista<T> ultimo;
    private int tamanio;

    public ListaEnlazadaDoble() {
        this.primero = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return this.primero == null;
    }

    public void insertarAlInicio(T dato) {
        NodoLista<T> nuevo = new NodoLista<T>(dato);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setSiguiente(this.primero);
            this.primero.setAnterior(nuevo);
            this.primero = nuevo;
        }
        this.tamanio++;
    }

    public void insertarAlFinal(T dato) {
        NodoLista<T> nuevo = new NodoLista<T>(dato);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }
        this.tamanio++;
    }

    public void eliminarAlInicio() {
        if (!estaVacia()) {
            if (this.primero.getSiguiente() == null) {
                this.primero = null;
                this.ultimo = null;
            } else {
                this.primero = this.primero.getSiguiente();
                this.primero.setAnterior(null);
            }
            this.tamanio--;
        }
    }

    public void eliminarAlFinal() {
        if (!estaVacia()) {
            if (this.primero.getSiguiente() == null) {
                this.primero = null;
                this.ultimo = null;
            } else {
                this.ultimo = this.ultimo.getAnterior();
                this.ultimo.setSiguiente(null);
            }
            this.tamanio--;
        }
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

    public T obtener(int indice) {
        if (indice >= 0 && indice < this.tamanio) {
            NodoLista<T> aux = this.primero;
            int contador = 0;
            while (contador < indice) {
                aux = aux.getSiguiente();
                contador++;
            }
            return aux.getDato();
        }
        return null;
    }

}
