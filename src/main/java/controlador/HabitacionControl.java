package controlador;

import algoritmos.Ordenamiento;
import modelo.Habitacion;
import algoritmos.ListaDoblePilaCola;

public class HabitacionControl {

    private int id = 1;

    private ListaDoblePilaCola<Habitacion> listaHabitaciones = new ListaDoblePilaCola<>();

    public int getCantidadHabitaciones() {
        return listaHabitaciones.getTamanio();
    }

    public void agregarHabitacion(int numero, int piso, int cantidadCamas, double precioDia) {
        Habitacion habitacion = new Habitacion(numero, piso, cantidadCamas, precioDia);
        habitacion.setId(id);
        id++;
        listaHabitaciones.insertarAlFinal(habitacion);
    }

    public ListaDoblePilaCola<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public ListaDoblePilaCola<Habitacion> getHabitacionesOrdenadasPorPiso() {
        Ordenamiento<Habitacion> ordenamiento = new Ordenamiento<>();
        return ordenamiento.ordenarListaPorInsercion(listaHabitaciones, new Habitacion.ComparadorPorPiso());
    }

    public ListaDoblePilaCola<Habitacion> getHabitacionesOrdenadasPorEstado() {
        Ordenamiento<Habitacion> ordenamiento = new Ordenamiento<>();
        return ordenamiento.ordenarListaPorInsercion(listaHabitaciones, new Habitacion.ComparadorPorEstado());
    }



    @Override
    public String toString() {
        return "HabitacionControl{" +
                "listaHabitaciones=" + listaHabitaciones.getPrimero().toString() +
                '}';
    }
}
