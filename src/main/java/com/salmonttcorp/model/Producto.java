package com.salmonttcorp.model;

/**
 * Representa un producto comercializado por SalmonttCorp,
 * por ejemplo "Salmón Atlántico Premium".
 *
 * Incluye nombre, tipo de producto y precio por unidad.
 *
 * Autor: Víctor Valenzuela
 */
public class Producto {

    private String nombreProducto;
    private String tipoProducto;
    private double precioProducto;

    /**
     * Constructor vacío para permitir la creación del objeto
     * sin inicializar todos los atributos.
     */
    public Producto() {
    }

    /**
     * Constructor principal del producto.
     *
     * @param nombreProducto nombre del producto; no puede estar vacío
     * @param tipoProducto   tipo o categoría del producto; no puede estar vacío
     * @param precioProducto precio por unidad; no puede ser negativo
     * @throws IllegalArgumentException si algún dato es inválido
     */
    public Producto(String nombreProducto, String tipoProducto, double precioProducto) {
        setNombreProducto(nombreProducto);
        setTipoProducto(tipoProducto);
        setPrecioProducto(precioProducto);
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        this.nombreProducto = nombreProducto.trim();
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        if (tipoProducto == null || tipoProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de producto no puede estar vacío.");
        }
        this.tipoProducto = tipoProducto.trim();
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        if (precioProducto < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        this.precioProducto = precioProducto;
    }

    /**
     * Entrega una representación textual del producto,
     * útil para impresión en consola o registros.
     *
     * @return texto descriptivo del producto
     */
    @Override
    public String toString() {
        return "Producto [nombreProducto=" + nombreProducto +
                ", tipoProducto=" + tipoProducto +
                ", precioProducto=" + precioProducto + "]";
    }
}


