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

    public void agregarHabitacion(int numero, int piso, int cantidadCamas, double precioDia) {
        hotel.getHabitaciones().agregarHabitacion(numero, piso, cantidadCamas, precioDia);
    }

    public void cambiarEstado(int id, String estado) {
        for (int i = 0; i < hotel.getHabitaciones().getListaHabitaciones().getTamanio(); i++) {
            if (hotel.getHabitaciones().getListaHabitaciones().obtenerIndice(i).getId() == id) {
                hotel.getHabitaciones().getListaHabitaciones().obtenerIndice(i).setEstado(estado);
                break;
            }
        }
    }

    public ListaDoblePilaCola<Habitacion> imprimirListaEnlazada() { // Esta imprime tal cual como se ingreso, sin ordenar
        return hotel.getHabitaciones().getListaHabitaciones();
    }

    public ListaDoblePilaCola<Habitacion> ordanadoPorPiso() {
        return hotel.getHabitaciones().getHabitacionesOrdenadasPorPiso();
    }

    public ListaDoblePilaCola<Habitacion> ordanadoPorEstado() {
        return hotel.getHabitaciones().getHabitacionesOrdenadasPorEstado();
    }


    @Override
    public String toString() {
        return "HotelControl{" +
                "hotel=" + hotel.toString() +
                '}';
    }





}
