package modelo;

import controlador.HabitacionControl;

public class Hotel {
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

    public void setHabitaciones(HabitacionControl habitaciones) {
        Habitaciones = habitaciones;
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
