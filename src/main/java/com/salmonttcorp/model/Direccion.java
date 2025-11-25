package com.salmonttcorp.model;

/**
 * Representa la direccion f&iacute;sica de una sucursal o centro de cultivo de SalmonttCorp.
 * <p> <p>
 * Autor: Victor Valenzuela
 */
public class Direccion {

    private String calle;
    private String numero;
    private String comuna;
    private String region;

    /**
     * Constructor vacío para escenarios donde los datos
     * se asignarán posteriormente mediante setters.
     */
    public Direccion() {
    }

    /**
     * Constructor principal de la dirección.
     *
     * @param calle   nombre de la calle
     * @param numero  número de la propiedad
     * @param comuna  comuna; no puede estar vacía
     * @param region  región administrativa
     */
    public Direccion(String calle, String numero,
                     String comuna, String region) {
        setCalle(calle);
        setNumero(numero);
        setComuna(comuna);
        setRegion(region);
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = (calle == null) ? "" : calle.trim();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = (numero == null) ? "" : numero.trim();
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        if (comuna == null || comuna.isBlank()) {
            throw new IllegalArgumentException("La comuna no puede estar vacía.");
        }
        this.comuna = comuna.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = (region == null) ? "" : region.trim();
    }


    /**
     * Entrega una representación legible de la dirección.
     *
     * @return texto con calle, número, comuna, región y país
     */
    @Override
    public String toString() {
        return String.format("%s %s, %s, %s",
                calle, numero, comuna, region);
    }
}

