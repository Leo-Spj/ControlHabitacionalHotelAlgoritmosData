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

    public T obtener(int indice) {
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

    // Eliminar:
    public void eliminar(T dato) {
        if (!estaVacia()) {
            this.primero = eliminarRecursivo(this.primero, dato);
        }
    }
    private Nodo<T> eliminarRecursivo(Nodo<T> raizActual, T dato) {
        if (raizActual == null) {
            return null;
        } else if (dato.equals(raizActual.getDato())) {
            if (raizActual.getIzquierdo_anterior() == null && raizActual.getDerecho_siguiente() == null) {
                return null;
            } else if (raizActual.getIzquierdo_anterior() == null) {
                raizActual.getDerecho_siguiente().setIzquierdo_anterior(null);
                return raizActual.getDerecho_siguiente();
            } else if (raizActual.getDerecho_siguiente() == null) {
                raizActual.getIzquierdo_anterior().setDerecho_siguiente(null);
                return raizActual.getIzquierdo_anterior();
            } else {
                Nodo<T> aux = raizActual.getDerecho_siguiente();
                while (aux.getIzquierdo_anterior() != null) {
                    aux = aux.getIzquierdo_anterior();
                }
                raizActual.setDato(aux.getDato());
                raizActual.setDerecho_siguiente(eliminarRecursivo(raizActual.getDerecho_siguiente(), aux.getDato()));
            }
        } else if (dato.hashCode() < raizActual.getDato().hashCode()) {
            raizActual.setIzquierdo_anterior(eliminarRecursivo(raizActual.getIzquierdo_anterior(), dato));
        } else {
            raizActual.setDerecho_siguiente(eliminarRecursivo(raizActual.getDerecho_siguiente(), dato));
        }
        return raizActual;
    }

}
