package vista;


import controlador.HotelControl;
import modelo.Habitacion;
import modelo.Hotel;

public class pruebas_hotel_habitaciones {

    public static void main(String[] args) {

        System.out.println(" ");

        HotelControl hotel = new HotelControl("Hotel Bellavista","La Perla", "Av. Siempre Viva 123", "123456789");
        hotel.agregarHabitacion(new Habitacion(1, 1, 2, 300));
        hotel.agregarHabitacion(new Habitacion(2, 1, 2, 330));
        hotel.agregarHabitacion(new Habitacion(3, 1, 2, 420));

        System.out.println(hotel.getHotel());

        System.out.println(" ");

        System.out.println(hotel.getCantidadHabitaciones());

        System.out.println(" ");

        System.out.println(hotel.buscarHabitacion(1));

        System.out.println(" ");

        hotel.eliminarHabitacion(hotel.buscarHabitacion(1));

        System.out.println(hotel.getHotel());

    }

}
