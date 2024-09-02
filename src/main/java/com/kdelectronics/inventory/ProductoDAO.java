package main.java.com.kdelectronics.inventory;



import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private final List<Producto> productos = new ArrayList<>();

    // Create
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Read
    public Producto obtenerProductoPorCodigo(String codigo) {
        return productos.stream()
                .filter(producto -> producto.getCodigo().equals(codigo) && producto.isActivo())
                .findFirst()
                .orElse(null);
    }

    // Update
    public void actualizarProducto(String codigo, Producto productoActualizado) {
        Producto producto = obtenerProductoPorCodigo(codigo);
        if (producto != null) {
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecioBase(productoActualizado.getPrecioBase());
            producto.setPrecioVenta(productoActualizado.getPrecioVenta());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setCantidadDisponible(productoActualizado.getCantidadDisponible());
        }
    }

    // Delete
    public void eliminarProductoLogicamente(String codigo) {
        Producto producto = obtenerProductoPorCodigo(codigo);
        if (producto != null) {
            producto.setActivo(false); // Marcar el producto como inactivo
        }
    }

    // Métodos adicionales, como listar todos los productos, etc.
    // ...
}
