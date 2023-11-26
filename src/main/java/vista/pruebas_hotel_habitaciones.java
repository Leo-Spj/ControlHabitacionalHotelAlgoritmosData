package vista;


import modelo.Habitacion;
import modelo.Hotel;

public class pruebas_hotel_habitaciones {

    public static void main(String[] args) {

        //creo un hotel y le grego habitaciones
        Hotel hotel = new Hotel("Hotel Bellavista", "Av. Siempre Viva 123", "123456789");
        hotel.getHabitaciones().agregarHabitacion(new Habitacion(1, 1, 2, 300));
        hotel.getHabitaciones().agregarHabitacion(new Habitacion(2, 1, 2, 330));
        hotel.getHabitaciones().agregarHabitacion(new Habitacion(3, 1, 2, 420));

        System.out.println(" ");
        //imprimo el hotel
        System.out.println("Hotel: ");
        System.out.println(hotel);

        System.out.println(" ");

        System.out.println("Busco una habitacion: ");
        //Buscar habitacion por numero y piso
        Habitacion habitacion = hotel.getHabitaciones().buscarHabitacion(1, 1);
        System.out.println(habitacion);

        System.out.println(" ");

        System.out.println("Imprimo la lista de habitaciones: ");
        // imprimir la cantidad de habitaciones
        for (int i = 0; i < hotel.getHabitaciones().getCantidadHabitaciones(); i++) {
            System.out.println(hotel.getHabitaciones().getListaHabitaciones().obtener(i));
        }

        System.out.println(" ");
        System.out.println("Elimino una habitacion: ");
        //Eliminar habitacion
        hotel.getHabitaciones().eliminarHabitacion(habitacion);
        System.out.println(hotel);

        System.out.println(" ");
        System.out.println("Imprimo la lista de habitaciones: ");
        // imprimir la cantidad de habitaciones
        for (int i = 0; i < hotel.getHabitaciones().getCantidadHabitaciones(); i++) {
            System.out.println(hotel.getHabitaciones().getListaHabitaciones().obtener(i));
        }

    }

}
