package org.example.Servicio;

import org.example.Modelo.Compra;
import org.example.Repositorio.CompraRepositorio;
import org.example.Dto.ComparacionPrecioDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CompraServicio {
    private final CompraRepositorio repositorio;

    // Inyección de dependencias a través del constructor
    public CompraServicio(CompraRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Valida y registra una nueva compra en el sistema.
     * @param compra Objeto con los datos de la transacción.
     * @throws Exception Si alguna regla de negocio no se cumple.
     */
    public void registrarCompra(Compra compra) throws Exception {
        // 1. Validar que los IDs sean válidos (referencias a otras tablas)
        if (compra.getIdUsuario() <= 0) {
            throw new Exception("Error: Usuario no identificado.");
        }
        if (compra.getIdProducto() <= 0) {
            throw new Exception("Error: Debe seleccionar un producto válido.");
        }
        if (compra.getIdSupermercado() <= 0) {
            throw new Exception("Error: Debe seleccionar un supermercado válido.");
        }

        // 2. Validar consistencia de datos numéricos
        if (compra.getPrecio() <= 0) {
            throw new Exception("Error: El precio debe ser un valor positivo mayor a 0.");
        }
        if (compra.getCantidad() < 1) {
            throw new Exception("Error: La cantidad mínima de compra es 1.");
        }

        // 3. Validar consistencia temporal (No permitir fechas futuras)
        if (compra.getFechaCompra().isAfter(LocalDate.now())) {
            throw new Exception("Error: La fecha de compra no puede estar en el futuro.");
        }

        // Si pasa todas las validaciones, se envía al repositorio
        repositorio.guardar(compra);
    }

    /**
     * Analiza los precios registrados y devuelve la mejor opción disponible.
     * @param idProducto El ID del producto a consultar.
     * @return DTO con el nombre del supermercado y el precio más bajo.
     * @throws Exception Si no hay datos suficientes.
     */
    public ComparacionPrecioDTO obtenerMejorOpcion(int idProducto) throws Exception {
        if (idProducto <= 0) {
            throw new Exception("Error: ID de producto inválido.");
        }

        // Llamada al metodo especializado del repositorio
        Optional<ComparacionPrecioDTO> mejorOpcion = repositorio.obtenerMejorOpcion(idProducto);

        if (mejorOpcion.isEmpty()) {
            throw new Exception("No se encontraron registros de precios para el producto con ID: " + idProducto);
        }

        return mejorOpcion.get();
    }

    /**
     * Obtiene todo el historial de precios de un producto para comparativa detallada.
     * @param idProducto El ID del producto.
     * @return Lista de precios por supermercado.
     * @throws Exception Si no hay registros.
     */
    public List<ComparacionPrecioDTO> obtenerHistorialComparativo(int idProducto) throws Exception {
        if (idProducto <= 0) {
            throw new Exception("Error: ID de producto inválido.");
        }

        List<ComparacionPrecioDTO> historial = repositorio.obtenerHistorialPrecios(idProducto);

        if (historial.isEmpty()) {
            throw new Exception("Aún no hay historial de compras para este producto.");
        }

        return historial;
    }
}