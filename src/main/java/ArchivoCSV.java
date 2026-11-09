
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class ArchivoCSV {

    private static final Path ARCHIVO =
            Paths.get("data", "productos.csv");

    public static void guardar(ArrayList<Producto> productos)
            throws IOException {

        Files.createDirectories(ARCHIVO.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(
                ARCHIVO, StandardCharsets.UTF_8)) {

            writer.write("codigo,nombre,precio,cantidad");
            writer.newLine();

            for (Producto p : productos) {
                writer.write(
                    p.getCodigo() + "," +
                    p.getNombre() + "," +
                    p.getPrecio() + "," +
                    p.getCantidad()
                );
                writer.newLine();
            }
        }
    }

    public static ArrayList<Producto> cargar()
            throws IOException {

        ArrayList<Producto> productos = new ArrayList<>();

        if (!Files.exists(ARCHIVO)) {
            return productos;
        }

        try (BufferedReader reader = Files.newBufferedReader(
                ARCHIVO, StandardCharsets.UTF_8)) {

            String linea = reader.readLine();
            int numeroLinea = 1;

            if (!"codigo,nombre,precio,cantidad".equals(linea)) {
                throw new IOException("Encabezado CSV inválido.");
            }

            while ((linea = reader.readLine()) != null) {
                numeroLinea++;

                if (linea.isBlank()) {
                    continue;
                }

                String[] datos = linea.split(",", -1);

                if (datos.length != 4) {
                    throw new IOException(
                        "Formato inválido en línea " + numeroLinea
                    );
                }

                try {
                    int codigo = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    double precio = Double.parseDouble(datos[2]);
                    int cantidad = Integer.parseInt(datos[3]);

                    if (codigo <= 0 || nombre.isBlank()
                            || nombre.contains(",")
                            || precio < 0 || !Double.isFinite(precio)
                            || cantidad < 0) {
                        throw new IllegalArgumentException();
                    }

                    for (Producto existente : productos) {
                        if (existente.getCodigo() == codigo) {
                            throw new IllegalArgumentException();
                        }
                    }

                    productos.add(new Producto(
                        codigo, nombre, precio, cantidad
                    ));

                } catch (IllegalArgumentException e) {
                    throw new IOException(
                        "Datos inválidos en línea " + numeroLinea, e
                    );
                }
            }
        }

        return productos;
    }
}
