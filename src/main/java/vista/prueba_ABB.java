package vista;

import algoritmos.ListaEnlazadaDoble;
import controlador.HabitacionControl;
import controlador.HotelControl;
import modelo.Habitacion;

public class prueba_ABB {

    public static void main(String[] args) {

        // creo un hotel y sus respectivas habitaciones, que seran los nodos del arbol

        HotelControl hotel = new HotelControl("Hotel", "Lima", "Av. Los Alamos", "123456789");
        hotel.agregarHabitacionABB(1, 1, 1, 100);
        hotel.agregarHabitacionABB(2, 1, 3, 150);
        hotel.agregarHabitacionABB(3, 3, 2, 120);
        hotel.agregarHabitacionABB(4, 3, 1, 100);
        hotel.agregarHabitacionABB(3, 2, 2, 120);
        hotel.agregarHabitacionABB(4, 2, 1, 100);
        hotel.agregarHabitacionABB(3, 1, 2, 120);
        hotel.agregarHabitacionABB(4, 1, 1, 100);
        hotel.agregarHabitacionABB(1, 2, 1, 100);
        hotel.agregarHabitacionABB(2, 2, 3, 150);
        hotel.agregarHabitacionABB(1, 3, 1, 100);
        hotel.agregarHabitacionABB(2, 3, 3, 150);

        // imprimo el arbol en preorden

        ListaEnlazadaDoble<Habitacion> listaHabitaciones = hotel.imprimirArbolPreorden();
        for (int i = 0; i < listaHabitaciones.getTamanio(); i++) {
            System.out.println(listaHabitaciones.obtener(i).getPiso()+"-"+listaHabitaciones.obtener(i).getNumero());
            System.out.println(listaHabitaciones.obtener(i).toString());
            System.out.println(" ");
        }





    }
}
