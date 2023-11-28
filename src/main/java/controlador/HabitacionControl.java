package controlador;

import modelo.Habitacion;
import algoritmos.ListaEnlazada;

public class HabitacionControl {

    private int id = 1;

    private ListaEnlazada<Habitacion> listaHabitaciones = new ListaEnlazada<Habitacion>();

    public ListaEnlazada<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public int getCantidadHabitaciones() {
        return this.listaHabitaciones.getTamanio();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        this.listaHabitaciones.insertarAlFinal(habitacion);
        habitacion.setId(id);
        id++;
    }

    public Habitacion buscarHabitacion(int id) {
        Habitacion habitacion = null;
        for (int i = 0; i < this.listaHabitaciones.getTamanio(); i++) {
            if (this.listaHabitaciones.obtener(i).getId() == id) {
                habitacion = this.listaHabitaciones.obtener(i);
            }
        }
        return habitacion;
    }

    public void actualizarHabitacion(Habitacion habitacion, Habitacion nuevaHabitacion) {
        habitacion.setNumero(nuevaHabitacion.getNumero());
        habitacion.setPiso(nuevaHabitacion.getPiso());
        habitacion.setCantidadCamas(nuevaHabitacion.getCantidadCamas());
        habitacion.setPrecioDia(nuevaHabitacion.getPrecioDia());
        habitacion.setEstado(nuevaHabitacion.getEstado());
    }

    public Habitacion eliminarHabitacion(int id) {
        Habitacion habitacion = buscarHabitacion(id);
        this.listaHabitaciones.eliminar(habitacion);
        return habitacion;
    }

    public void cambiarEstado(Habitacion habitacion, String estado) {
        habitacion.setEstado(estado);
    }

    public void ocuparHabitacion(Habitacion habitacion) {
        habitacion.setEstado("Ocupada");
    }

    @Override
    public String toString() {
        return "HabitacionControl{" +
                "listaHabitaciones=" + listaHabitaciones +
                '}';
    }

}
