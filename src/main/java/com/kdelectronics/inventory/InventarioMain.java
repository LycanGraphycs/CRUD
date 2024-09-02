//Andres Mauricio Moreno Garavito - Uniminuto 2024
// Todos los derechos reservados. No se permite la distribución ni el uso de este código sin permiso explícito.

package main.java.com.kdelectronics.inventory;

import java.util.Scanner;

public class InventarioMain {

    public static void main(String[] args) {
        ProductoDAO productoDAO = new ProductoDAO();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
    System.out.println("\n===== MENÚ DE GESTIÓN DE INVENTARIO =====");
    System.out.println("1. Agregar Producto");
    System.out.println("2. Eliminar Producto");
    System.out.println("3. Consultar Producto");
    System.out.println("4. Actualizar Producto"); // Nueva opción para actualizar
    System.out.println("5. Salir");
    System.out.print("Seleccione una opción: ");

    int opcion;
    try {
        opcion = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Error: Debe ingresar un número válido.");
        continue;
    }

    switch (opcion) {
        case 1:
            agregarProducto(scanner, productoDAO);
            break;

        case 2:
            eliminarProducto(scanner, productoDAO);
            break;

        case 3:
            consultarProducto(scanner, productoDAO);
            break;

        case 4: // Opción para actualizar producto
            actualizarProducto(scanner, productoDAO);
            break;

        case 5:
            salir = true;
            System.out.println("Saliendo del sistema...");
            break;

        default:
            System.out.println("Opción no válida. Intente de nuevo.");
            break;
    }
}


        scanner.close();
    }

    private static void agregarProducto(Scanner scanner, ProductoDAO productoDAO) {
        try {
            System.out.print("Ingrese el código del producto: ");
            String codigo = scanner.nextLine().trim();
            if (codigo.isEmpty()) {
                System.out.println("Error: El código del producto no puede estar vacío.");
                return;
            }

            if (productoDAO.obtenerProductoPorCodigo(codigo) != null) {
                System.out.println("Error: Ya existe un producto con este código.");
                return;
            }

            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre del producto no puede estar vacío.");
                return;
            }

            System.out.print("Ingrese la descripción del producto: ");
            String descripcion = scanner.nextLine().trim();

            System.out.print("Ingrese el precio base del producto: ");
            double precioBase = Double.parseDouble(scanner.nextLine());
            if (precioBase < 0) {
                System.out.println("Error: El precio base no puede ser negativo.");
                return;
            }

            System.out.print("Ingrese el precio de venta del producto: ");
            double precioVenta = Double.parseDouble(scanner.nextLine());
            if (precioVenta < 0) {
                System.out.println("Error: El precio de venta no puede ser negativo.");
                return;
            }

            System.out.print("Ingrese la categoría del producto: ");
            String categoria = scanner.nextLine().trim();
            if (categoria.isEmpty()) {
                System.out.println("Error: La categoría del producto no puede estar vacía.");
                return;
            }

            System.out.print("Ingrese la cantidad disponible del producto: ");
            int cantidadDisponible = Integer.parseInt(scanner.nextLine());
            if (cantidadDisponible < 0) {
                System.out.println("Error: La cantidad disponible no puede ser negativa.");
                return;
            }

            Producto nuevoProducto = new Producto(codigo, nombre, descripcion, precioBase, precioVenta, categoria, cantidadDisponible);
            productoDAO.agregarProducto(nuevoProducto);
            System.out.println("Producto agregado con éxito.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private static void eliminarProducto(Scanner scanner, ProductoDAO productoDAO) {
        try {
            System.out.print("Ingrese el código del producto a eliminar: ");
            String codigoEliminar = scanner.nextLine().trim();
            if (codigoEliminar.isEmpty()) {
                System.out.println("Error: El código del producto no puede estar vacío.");
                return;
            }

            Producto producto = productoDAO.obtenerProductoPorCodigo(codigoEliminar);
            if (producto == null) {
                System.out.println("Error: Producto no encontrado o ya eliminado.");
                return;
            }

            productoDAO.eliminarProductoLogicamente(codigoEliminar);
            System.out.println("Producto eliminado (lógicamente) con éxito.");

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private static void consultarProducto(Scanner scanner, ProductoDAO productoDAO) {
        try {
            System.out.print("Ingrese el código del producto a consultar: ");
            String codigoConsultar = scanner.nextLine().trim();
            if (codigoConsultar.isEmpty()) {
                System.out.println("Error: El código del producto no puede estar vacío.");
                return;
            }

            Producto productoConsultado = productoDAO.obtenerProductoPorCodigo(codigoConsultar);
            if (productoConsultado != null) {
                System.out.println("\n--- Detalles del Producto ---");
                System.out.println("Código: " + productoConsultado.getCodigo());
                System.out.println("Nombre: " + productoConsultado.getNombre());
                System.out.println("Descripción: " + productoConsultado.getDescripcion());
                System.out.println("Precio Base: " + productoConsultado.getPrecioBase());
                System.out.println("Precio Venta: " + productoConsultado.getPrecioVenta());
                System.out.println("Categoría: " + productoConsultado.getCategoria());
                System.out.println("Cantidad Disponible: " + productoConsultado.getCantidadDisponible());
                System.out.println("------------------------------");
            } else {
                System.out.println("Producto no encontrado o eliminado.");
            }

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    private static void actualizarProducto(Scanner scanner, ProductoDAO productoDAO) {
    try {
        System.out.print("Ingrese el código del producto a actualizar: ");
        String codigo = scanner.nextLine().trim();
        if (codigo.isEmpty()) {
            System.out.println("Error: El código del producto no puede estar vacío.");
            return;
        }

        Producto productoExistente = productoDAO.obtenerProductoPorCodigo(codigo);
        if (productoExistente == null) {
            System.out.println("Error: Producto no encontrado.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del producto (actual: " + productoExistente.getNombre() + "): ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) nombre = productoExistente.getNombre();

        System.out.print("Ingrese la nueva descripción del producto (actual: " + productoExistente.getDescripcion() + "): ");
        String descripcion = scanner.nextLine().trim();
        if (descripcion.isEmpty()) descripcion = productoExistente.getDescripcion();

        System.out.print("Ingrese el nuevo precio base del producto (actual: " + productoExistente.getPrecioBase() + "): ");
        double precioBase;
        try {
            String input = scanner.nextLine().trim();
            precioBase = input.isEmpty() ? productoExistente.getPrecioBase() : Double.parseDouble(input);
            if (precioBase < 0) {
                System.out.println("Error: El precio base no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
            return;
        }

        System.out.print("Ingrese el nuevo precio de venta del producto (actual: " + productoExistente.getPrecioVenta() + "): ");
        double precioVenta;
        try {
            String input = scanner.nextLine().trim();
            precioVenta = input.isEmpty() ? productoExistente.getPrecioVenta() : Double.parseDouble(input);
            if (precioVenta < 0) {
                System.out.println("Error: El precio de venta no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
            return;
        }

        System.out.print("Ingrese la nueva categoría del producto (actual: " + productoExistente.getCategoria() + "): ");
        String categoria = scanner.nextLine().trim();
        if (categoria.isEmpty()) categoria = productoExistente.getCategoria();

        System.out.print("Ingrese la nueva cantidad disponible del producto (actual: " + productoExistente.getCantidadDisponible() + "): ");
        int cantidadDisponible;
        try {
            String input = scanner.nextLine().trim();
            cantidadDisponible = input.isEmpty() ? productoExistente.getCantidadDisponible() : Integer.parseInt(input);
            if (cantidadDisponible < 0) {
                System.out.println("Error: La cantidad disponible no puede ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
            return;
        }

        Producto productoActualizado = new Producto(codigo, nombre, descripcion, precioBase, precioVenta, categoria, cantidadDisponible);
        productoDAO.actualizarProducto(codigo, productoActualizado);
        System.out.println("Producto actualizado con éxito.");

    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
    }
}
}
