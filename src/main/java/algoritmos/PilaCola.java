package algoritmos;

public class PilaCola <T> {

    private ListaEnlazadaDoble<T> lista;

    public PilaCola() {
        this.lista = new ListaEnlazadaDoble<T>();
    }

    public void incertarInicio (T dato) {
        this.lista.insertarAlInicio(dato);
    }

    public T eliminarInicio () {
        T dato = (T) this.lista.getPrimero();
        this.lista.eliminarAlInicio();
        return dato;
    }

    public void incertarFinal (T dato) {
        this.lista.insertarAlFinal(dato);
    }

    public T eliminarFinal () {
        T dato = (T) this.lista.getUltimo();
        this.lista.eliminarAlFinal();
        return dato;
    }
}
