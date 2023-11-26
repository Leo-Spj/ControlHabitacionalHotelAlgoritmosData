package controlador;

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

    public int getCantidadHabitaciones() {
        return this.hotel.getHabitaciones().getCantidadHabitaciones();
    }

    //cantidad de habitaciones: disponible, ocupada, disponible-limpieza , en ocupada-limpieza
    public int getCantidadHabitacionesDisponibles() {
        int cantidad = 0;
        for (int i = 0; i < this.hotel.getHabitaciones().getListaHabitaciones().getTamanio(); i++) {
            if (this.hotel.getHabitaciones().getListaHabitaciones().obtener(i).getEstado().equals("disponible")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int getCantidadHabitacionesOcupadas() {
        int cantidad = 0;
        for (int i = 0; i < this.hotel.getHabitaciones().getListaHabitaciones().getTamanio(); i++) {
            if (this.hotel.getHabitaciones().getListaHabitaciones().obtener(i).getEstado().equals("ocupada")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public Habitacion buscarHabitacion(int id) {
        return this.hotel.getHabitaciones().buscarHabitacion(id);
    }

    public void agregarHabitacion(Habitacion habitacion) {
        this.hotel.getHabitaciones().agregarHabitacion(habitacion);
    }

    public void actualizarHabitacion(Habitacion habitacion, Habitacion nuevaHabitacion) {
        this.hotel.getHabitaciones().actualizarHabitacion(habitacion, nuevaHabitacion);
    }

    public Habitacion eliminarHabitacion(int id) {
        return  hotel.getHabitaciones().eliminarHabitacion(id);
    }

    public void estadoDisponible(Habitacion habitacion) {
        this.hotel.getHabitaciones().cambiarEstado(habitacion, "disponible");
    }

    public void estadoOcupada(Habitacion habitacion) {
        this.hotel.getHabitaciones().cambiarEstado(habitacion, "ocupada");
    }

    public void estadoDisponibleLimpieza(Habitacion habitacion) {
        this.hotel.getHabitaciones().cambiarEstado(habitacion, "disponible-limpieza");
    }

    public void estadoOcupadaLimpieza(Habitacion habitacion) {
        this.hotel.getHabitaciones().cambiarEstado(habitacion, "ocupada-limpieza");
    }


}
