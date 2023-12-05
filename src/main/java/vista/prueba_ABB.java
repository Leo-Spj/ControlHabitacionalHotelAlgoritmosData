package vista;

import algoritmos.ListaDoblePilaCola;
import controlador.HotelControl;
import modelo.Habitacion;

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


        // Cambio estado, no usar de esta manera, es solo para la prueba
        hotel.cambiarEstado(1, "Ocupada");
        hotel.cambiarEstado(2, "Ocupada");
        hotel.cambiarEstado(8, "Ocupada");

        System.out.println("Las habitaciones se ingresan ordenadamente por piso (por defecto):");
        ListaDoblePilaCola<Habitacion> habitaciones = hotel.primerNododeListaEnlazada();
        for (int i = 0; i < habitaciones.getTamanio(); i++) {
            System.out.println(habitaciones.obtenerIndice(i).getPiso()+"-"+habitaciones.obtenerIndice(i).getNumero() + " " + habitaciones.obtenerIndice(i).getEstado());
        }

        // Por el metodo de atender por Cola
        hotel.atenderHabitacionPorCola();
        hotel.atenderHabitacionPorCola();
        // Por el metodo de atender por Pila
        hotel.atenderHabitacionPorPila();
        hotel.atenderHabitacionPorPila();


        // Reordeno por Estado
        System.out.println("\nOrdenado por estado:");
        ListaDoblePilaCola<Habitacion> habitacionesPorEstado = hotel.ordanadoPorEstado();
        for (int i = 0; i < habitacionesPorEstado.getTamanio(); i++) {
            System.out.println(habitacionesPorEstado.obtenerIndice(i).getPiso()+"-"+habitacionesPorEstado.obtenerIndice(i).getNumero() + " " + habitacionesPorEstado.obtenerIndice(i).getEstado());
        }

    }
}
