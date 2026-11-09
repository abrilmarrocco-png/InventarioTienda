import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;


public class Main {

static InventarioService inventario = new InventarioService();    
static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
      try {
          inventario.cargarProductos(ArchivoCSV.cargar());
          System.out.println("Inventario cargado correctamente.");
          } catch (IOException e) {
                System.out.println("Error al cargar los datos: "
                + e.getMessage());
                System.out.println("No se modificó el archivo.");
               return;
}

        int opcion;

        do {
            System.out.println("\n=== STOCK MANAGER ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Modificar stock");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> agregarProducto();
                    case 2 -> listarProductos();
                    case 3 -> buscarProducto();
                    case 4 -> eliminarProducto();
                    case 5 -> modificarStock();
                    case 0 -> { try { ArchivoCSV.guardar(new ArrayList<>(inventario.listarProductos()));
                    System.out.println("Datos guardados correctamente.");
                    System.out.println("¡Hasta luego!");
                   } catch (IOException e) {
                   System.out.println("Error al guardar: "+ e.getMessage());
                   System.out.println("Revisá el archivo antes de salir.");
                   opcion = -1;
                                           }
                              }

                    default -> System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número válido.");
                opcion = -1;
            }

        } while (opcion != 0);

        scanner.close();
    }

    static Producto encontrarProducto(int codigo) {
    return inventario.buscarProducto(codigo);
}

    static void agregarProducto() {
        try {
            System.out.print("Código: ");
            int codigo = Integer.parseInt(scanner.nextLine());

            if (encontrarProducto(codigo) != null) {
                System.out.println("Ese código ya existe.");
                return;
            }

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            if (codigo <= 0 || nombre.isBlank()
                    || nombre.contains(",")
                    || nombre.contains("\n")
                    || nombre.contains("\r")
                    || precio < 0 || !Double.isFinite(precio)
                    || cantidad < 0) {
                System.out.println("Los datos ingresados no son válidos.");
                return;
            }
            try {
            inventario.agregarProducto(
            new Producto(codigo, nombre, precio, cantidad)
            );

               System.out.println("Producto agregado correctamente.");

    } catch (IllegalArgumentException e) {
             System.out.println("Error: " + e.getMessage());
}

        } catch (NumberFormatException e) {
            System.out.println("Error: ingresá valores numéricos válidos.");
        }
    }

    static void listarProductos() {
    if (inventario.listarProductos().isEmpty()) {
        System.out.println("No hay productos registrados.");
        return;
    }

    for (Producto p : inventario.listarProductos()) {
        p.mostrarInformacion();
    }
}
    static void buscarProducto() {
        try {
            System.out.print("Código a buscar: ");
            int codigo = Integer.parseInt(scanner.nextLine());

            Producto p = encontrarProducto(codigo);

            if (p != null) {
                p.mostrarInformacion();
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Código inválido.");
        }
    }

    static void eliminarProducto() {
        try {
            System.out.print("Código a eliminar: ");
            int codigo = Integer.parseInt(scanner.nextLine());

            Producto p = encontrarProducto(codigo);

            
    if (inventario.eliminarProducto(codigo)) {
        System.out.println("Producto eliminado correctamente.");
    } else {
        System.out.println("Producto no encontrado.");
    }

        } catch (NumberFormatException e) {
            System.out.println("Código inválido.");
        }
    }

    static void modificarStock() {
        try {
            System.out.print("Código del producto: ");
            int codigo = Integer.parseInt(scanner.nextLine());

            Producto p = encontrarProducto(codigo);

            if (p == null) {
                System.out.println("Producto no encontrado.");
                return;
            }

            System.out.print("Nueva cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            if (cantidad < 0) {
                System.out.println("La cantidad no puede ser negativa.");
                return;
            }

            
            try {
                inventario.modificarStock(codigo, cantidad);
                System.out.println("Stock actualizado correctamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }


        } catch (NumberFormatException e) {
            System.out.println("Ingresá un número válido.");
        }
    }



}