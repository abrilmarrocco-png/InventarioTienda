
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioServiceTest {

    private InventarioService inventario;

    @BeforeEach
    void preparar() {
        inventario = new InventarioService();
    }

    @Test
    void agregarProductoCorrectamente() {

        Producto producto =
                new Producto(101, "Mouse", 25000, 15);

        inventario.agregarProducto(producto);

        assertEquals(1, inventario.listarProductos().size());
    }

    @Test
    void buscarProductoExistente() {

        Producto producto =
                new Producto(101, "Mouse", 25000, 15);

        inventario.agregarProducto(producto);

        Producto encontrado =
                inventario.buscarProducto(101);

        assertNotNull(encontrado);
        assertEquals("Mouse", encontrado.getNombre());
    }

    @Test
    void evitarProductosDuplicados() {

        inventario.agregarProducto(
            new Producto(101, "Mouse", 25000, 15)
        );

        assertThrows(IllegalArgumentException.class, () -> {
            inventario.agregarProducto(
                new Producto(101, "Teclado", 30000, 5)
            );
        });
    }

    @Test
    void eliminarProductoCorrectamente() {

        inventario.agregarProducto(
            new Producto(101, "Mouse", 25000, 15)
        );

        boolean eliminado = inventario.eliminarProducto(101);

        assertTrue(eliminado);
        assertNull(inventario.buscarProducto(101));
    }

    @Test
    void modificarStockCorrectamente() {

        inventario.agregarProducto(
            new Producto(101, "Mouse", 25000, 15)
        );

        inventario.modificarStock(101, 30);

        assertEquals(
            30,
            inventario.buscarProducto(101).getCantidad()
        );
    }

    @Test
    void rechazarStockNegativo() {

        inventario.agregarProducto(
            new Producto(101, "Mouse", 25000, 15)
        );

        assertThrows(IllegalArgumentException.class, () -> {
            inventario.modificarStock(101, -5);
        });
    }
}
