package controlador;

import algoritmos.ListaDoblePilaCola;
import modelo.Habitacion;
import modelo.Hotel;

public class HotelControl {

    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public HotelControl(String nombre, String distrito, String direccion, String telefono) {
        this.hotel = new Hotel(nombre, distrito, direccion, telefono);
    }

    public void agregarHabitacion(int piso, int numero, int cantidadCamas, double precioDia) {
        hotel.getHabitaciones().agregarHabitacion(piso, numero, cantidadCamas, precioDia);
    }

    // rudimentario, no usar:
    public void cambiarEstado(int id, String estado) {
        for (int i = 0; i < hotel.getHabitaciones().getListaHabitaciones().getTamanio(); i++) {
            if (hotel.getHabitaciones().getListaHabitaciones().obtenerIndice(i).getId() == id) {
                hotel.getHabitaciones().getListaHabitaciones().obtenerIndice(i).setEstado(estado);
                break;
            }
        }
    }

    public ListaDoblePilaCola<Habitacion> primerNododeListaEnlazada() { // Esta imprime tal cual como se ingreso, sin ordenar
        return hotel.getHabitaciones().getListaHabitaciones();
    }

    public ListaDoblePilaCola<Habitacion> ordanadoPorPiso() {
        return hotel.getHabitaciones().getHabitacionesOrdenadasPorPiso();
    }

    public ListaDoblePilaCola<Habitacion> ordanadoPorEstado() {
        return hotel.getHabitaciones().getHabitacionesOrdenadasPorEstado();
    }

    public void atenderHabitacionPorCola(){
        hotel.getHabitaciones().atenderHabitacionPorCola();
    }

    public void atenderHabitacionPorPila() {
        hotel.getHabitaciones().atenderHabitacionPorPila();
    }



    // Reportes
    public int reporteHabitacionesOcupadas(){
        return hotel.getHabitaciones().reporteHabitacionesOcupadas();
    }

    public int reporteHabitacionesDisponibles(){
        return hotel.getHabitaciones().reporteHabitacionesDisponibles();
    }


    @Override
    public String toString() {
        return "HotelControl{" +
                "hotel=" + hotel.toString() +
                '}';
    }

}
