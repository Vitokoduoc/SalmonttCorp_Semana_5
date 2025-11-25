package com.salmonttcorp.model;

/**
 * Clase que representa un centro de cultivo de SalmonttCorp y sus atributos principales.
 * Mantiene información del centro (nombre, comuna, producción) y asocia:

 * - Un {@link Producto} principal mediante composición.
 * - Una {@link Direccion} física de la sucursal.
 * - Una {@link Ruta} opcional que puede representar una visita o inspección programada.

 * Autor: Víctor Valenzuela
 */
public class CentroCultivo {

    private String nombreCentro;
    private int produccion;
    private Producto producto;
    private Direccion direccion;
    private Ruta rutaAsociada; // sí cuenta con entrega o recepción de productos.

    /**
     * Constructor vacío. Permite crear el objeto sin inicializar todos los campos.
     */
    public CentroCultivo() {
    }

    /**
     * Constructor principal que inicializa los datos fundamentales del centro
     * y el producto asociado.
     *
     * @param nombreCentro nombre del centro; no debe estar vacío
     * @param comuna       comuna donde se ubica; no debe estar vacía
     * @param producto     producto asociado; no puede ser nulo
     * @param produccion   cantidad de producción; no puede ser negativa
     */
    public CentroCultivo(String nombreCentro, String comuna,
                         Producto producto, int produccion) {
        this(nombreCentro, producto, produccion, null, null);
    }

    /**
     * Constructor completo del centro de cultivo, incluyendo dirección y ruta asociada.
     *
     * @param nombreCentro nombre del centro
     * @param producto     producto principal
     * @param produccion   producción anual/mensual (según el contexto)
     * @param direccion    dirección física de la sucursal
     * @param rutaAsociada ruta o visita programada relacionada al centro
     */
    public CentroCultivo(String nombreCentro,
                         Producto producto, int produccion,
                         Direccion direccion, Ruta rutaAsociada) {
        setNombreCentro(nombreCentro);
        setProducto(producto);
        setProduccion(produccion);
        setDireccion(direccion);
        setRutaAsociada(rutaAsociada);
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        if (nombreCentro == null || nombreCentro.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del centro no puede estar vacío.");
        }
        this.nombreCentro = nombreCentro.trim();
    }


    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        if (produccion < 0) {
            throw new IllegalArgumentException("La producción no puede ser negativa.");
        }
        this.produccion = produccion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto asociado no puede ser nulo.");
        }
        this.producto = producto;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion; // puede ser null si no tienes el dato aún
    }

    public Ruta getRutaAsociada() {
        return rutaAsociada;
    }

    public void setRutaAsociada(Ruta rutaAsociada) {
        this.rutaAsociada = rutaAsociada;
    }

    /**
     * Entrega una representación en texto del centro de cultivo,
     * incluyendo sus atributos y el producto asociado.
     *
     * @return cadena con los datos estructurados del centro
     */
    @Override
    public String toString() {
        return "CentroCultivo [nombreCentro=" + nombreCentro +
                ", producción=" + produccion +
                ", producto=" + producto +
                (direccion != null ? ", dirección=" + direccion : "") +
                (rutaAsociada != null ? ", rutaAsociada=" + rutaAsociada : "") +
                "]";
    }
}
