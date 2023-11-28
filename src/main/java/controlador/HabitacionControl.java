package controlador;

import algoritmos.ArbolBinario;
import modelo.Habitacion;
import algoritmos.ListaEnlazadaDoble;

public class HabitacionControl {

    private int id = 1;

    private ListaEnlazadaDoble<Habitacion> listaHabitaciones = new ListaEnlazadaDoble<Habitacion>();

    public int getCantidadHabitaciones() {
        return this.listaHabitaciones.getTamanio();
    }


    // ========= Metodos para el ABB ===========

    ArbolBinario<Habitacion> arbol = new ArbolBinario<Habitacion>(new Habitacion.ComparadorPorPiso());

    public void agregarHabitacionABB(Habitacion habitacion) {
        arbol.insertar(habitacion);
        habitacion.setId(id);
        id++;
    }

    public ListaEnlazadaDoble<Habitacion> obtenerHabitacionesInOrden() {
        return arbol.inorden();
    }


    @Override
    public String toString() {
        return "HabitacionControl{" +
                "listaHabitaciones=" + listaHabitaciones +
                '}';
    }
}
