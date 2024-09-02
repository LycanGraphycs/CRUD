package main.java.com.kdelectronics.inventory;

public class Producto {
    private final String codigo;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private double precioVenta;
    private String categoria;
    private int cantidadDisponible;
    private boolean activo; // Nuevo atributo

    public Producto(String codigo, String nombre, String descripcion, double precioBase, double precioVenta, String categoria, int cantidadDisponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
        this.cantidadDisponible = cantidadDisponible;
        this.activo = true; // Por defecto, el producto está activo
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
