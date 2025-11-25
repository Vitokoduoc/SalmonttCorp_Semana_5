package com.salmonttcorp.model;

/**
 * Clase que representa una ruta interna de SalmonttCorp, por ejemplo
 * una visita programada, inspección o recorrido entre áreas de una sucursal.
 * Registra el nombre de la ruta, el responsable y la fecha programada,
 * y puede asociarse a la dirección de una sucursal específica.
 * Autor: Víctor Valenzuela
 */
public class Ruta {


    private String nomRuta;
    private String responsable;
    private String fecha;
    private Direccion sucursal;

    /**
     * Constructor vacío, pensado para escenarios donde los datos
     * se asignarán posteriormente mediante los métodos setter.
     */
    public Ruta() {
    }

    /**
     * Constructor principal de la ruta (sin sucursal asociada).
     *
     * @param nomRuta     nombre de la ruta; no puede ser nulo ni vacío
     * @param responsable responsable de la actividad; no puede estar vacío
     * @param fecha       fecha programada; no debe ser nula ni vacía
     * @throws IllegalArgumentException cuando alguno de los campos es inválido
     */
    public Ruta(String nomRuta, String responsable, String fecha) {
        this(nomRuta, responsable, fecha, null);
    }

    /**
     * Constructor completo de la ruta, incluye sucursal asociada.
     *
     * @param nomRuta     nombre de la ruta
     * @param responsable responsable de la actividad
     * @param fecha       fecha programada
     * @param sucursal    dirección de la sucursal donde se realiza la ruta (puede ser null)
     */
    public Ruta(String nomRuta, String responsable, String fecha, Direccion sucursal) {
        setNomRuta(nomRuta);
        setResponsable(responsable);
        setFecha(fecha);
        setSucursal(sucursal);
    }

    public String getNomRuta() {
        return nomRuta;
    }

    public void setNomRuta(String nomRuta) {
        if (nomRuta == null || nomRuta.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ruta no puede estar vacío.");
        }
        this.nomRuta = nomRuta.trim();
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        if (responsable == null || responsable.trim().isEmpty()) {
            throw new IllegalArgumentException("El responsable no puede estar vacío.");
        }
        this.responsable = responsable.trim();
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        this.fecha = fecha.trim();
    }

    public Direccion getSucursal() {
        return sucursal;
    }

    public void setSucursal(Direccion sucursal) {
        this.sucursal = sucursal; // puede ser null
    }

    /**
     * Devuelve una representación en texto de la ruta,
     * mostrando la información más relevante.
     *
     * @return texto con los detalles de la ruta
     */
    @Override
    public String toString() {
        return "Ruta [nomRuta=" + nomRuta +
                ", responsable=" + responsable +
                ", fecha=" + fecha +
                (sucursal != null ? ", sucursal=" + sucursal : "") +
                "]";
    }
}
