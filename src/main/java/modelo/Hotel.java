package modelo;

import controlador.HabitacionControl;

import java.util.Comparator;

public class Hotel {

    private int id;
    private String nombre;
    private String distrito;
    private String direccion;
    private String telefono;
    private HabitacionControl Habitaciones = new HabitacionControl();

    public Hotel(String nombre, String distrito, String direccion, String telefono) {
        this.nombre = nombre;
        this.distrito = distrito;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Hotel() {
        this.nombre = "";
        this.distrito = "";
        this.direccion = "";
        this.telefono = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public HabitacionControl getHabitaciones() {
        return Habitaciones;
    }


    public static class ComparadorPorNombre implements Comparator<Hotel> {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    }

    public static class ComparadorPorId implements Comparator<Hotel> {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            int id1 = o1.getId();
            int id2 = o2.getId();

            return id1 - id2;
        }
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nombre='" + nombre + '\'' +
                ", distrito='" + distrito + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", Habitaciones=" + Habitaciones.getCantidadHabitaciones() +
                '}';
    }

}
