package vista;

import controlador.HotelControl;

public class prueba_ABB {

    public static void main(String[] args) {

        // creo un hotel y sus respectivas habitaciones, que seran los nodos del arbol

        HotelControl hotel = new HotelControl("Hotel", "Lima", "Av. Los Alamos", "123456789");
        hotel.agregarHabitacion(1, 1, 1, 100);
        hotel.agregarHabitacion(2, 1, 3, 150);
        hotel.agregarHabitacion(3, 3, 2, 120);
        hotel.agregarHabitacion(4, 3, 1, 100);
        hotel.agregarHabitacion(3, 2, 2, 120);
        hotel.agregarHabitacion(4, 2, 1, 100);
        hotel.agregarHabitacion(3, 1, 2, 120);
        hotel.agregarHabitacion(4, 1, 1, 100);
        hotel.agregarHabitacion(1, 2, 1, 100);
        hotel.agregarHabitacion(2, 2, 3, 150);
        hotel.agregarHabitacion(1, 3, 1, 100);
        hotel.agregarHabitacion(2, 3, 3, 150);

        // cambiar estado
        hotel.cambiarEstado(1, "Ocupada");
        hotel.cambiarEstado(2, "Ocupada");
        hotel.cambiarEstado(8, "Ocupada");



        //ListaDoblePilaCola<Habitacion> listaHabitaciones = hotel.imprimirArbolInorden_Piso();
        for (int i = 0; i < hotel.ordanadoPorPiso().getTamanio(); i++) {
            System.out.println(hotel.ordanadoPorPiso().obtenerIndice(i).getPiso()+"-"+hotel.ordanadoPorPiso().obtenerIndice(i).getNumero());
            //System.out.println(hotel.ordanadoPorPiso().toString());
        }

        //ordanadoPorEstado
        System.out.println("Ordenado por estado:");
        for (int i = 0; i < hotel.ordanadoPorEstado().getTamanio(); i++) {
            System.out.println(hotel.ordanadoPorEstado().obtenerIndice(i).getEstado());
        }

    }
}
