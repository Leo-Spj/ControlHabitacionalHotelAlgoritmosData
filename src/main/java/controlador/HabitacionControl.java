package controlador;

import modelo.Habitacion;
import controlador.algoritmos.ListaEnlazada;

/*
Ingreso y actualización de datos de las habitaciones del hotel.

- Habitacion
   - Numero
   - Piso
   - CantidadCamas
   - PrecioDia
   - Estado
 */
public class HabitacionControl {

    private ListaEnlazada<Habitacion> listaHabitaciones = new ListaEnlazada<Habitacion>();


    public ListaEnlazada<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public int getCantidadHabitaciones() {
        return this.listaHabitaciones.getTamanio();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        this.listaHabitaciones.insertarAlFinal(habitacion);
    }

    public Habitacion buscarHabitacion(int numero, int piso) {
        Habitacion habitacion = null;
        for (int i = 0; i < this.listaHabitaciones.getTamanio(); i++) {
            if (this.listaHabitaciones.obtener(i).getNumero() == numero && this.listaHabitaciones.obtener(i).getPiso() == piso) {
                habitacion = this.listaHabitaciones.obtener(i);
            }
        }
        return habitacion;
    }

    public void eliminarHabitacion(Habitacion habitacion) {
        this.listaHabitaciones.eliminar(habitacion);
    }

    @Override
    public String toString() {
        return "HabitacionControl{" +
                "listaHabitaciones=" + listaHabitaciones +
                '}';
    }

}
