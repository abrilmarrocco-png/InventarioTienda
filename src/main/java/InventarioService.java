
import java.util.ArrayList;
import java.util.List;

public class InventarioService {

    private final ArrayList<Producto> productos =
            new ArrayList<>();

    // Agregar un producto
    public void agregarProducto(Producto producto) {

        if (producto == null) {
            throw new IllegalArgumentException(
                "El producto no puede ser nulo"
            );
        }

        if (producto.getCodigo() <= 0 ||
            producto.getNombre() == null ||
            producto.getNombre().isBlank() ||
            producto.getPrecio() < 0 ||
            !Double.isFinite(producto.getPrecio()) ||
            producto.getCantidad() < 0) {

            throw new IllegalArgumentException(
                "Los datos del producto no son válidos"
            );
        }

        if (buscarProducto(producto.getCodigo()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un producto con ese código"
            );
        }

        productos.add(producto);
    }

    // Buscar un producto por código
    public Producto buscarProducto(int codigo) {

        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }

        return null;
    }

    // Listar productos
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    // Eliminar un producto
    public boolean eliminarProducto(int codigo) {

        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            return false;
        }

        return productos.remove(producto);
    }

    // Modificar stock
    public void modificarStock(int codigo, int cantidad) {

        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            throw new IllegalArgumentException(
                "Producto no encontrado"
            );
        }

        if (cantidad < 0) {
            throw new IllegalArgumentException(
                "La cantidad no puede ser negativa"
            );
        }

        producto.setCantidad(cantidad);
    }

public void cargarProductos(List<Producto> productosGuardados) {
    for (Producto producto : productosGuardados) {
        agregarProducto(producto);
    }
}

}
