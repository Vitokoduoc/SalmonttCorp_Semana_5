package com.salmonttcorp.util;

import com.salmonttcorp.model.CentroCultivo;
import com.salmonttcorp.model.Direccion;
import com.salmonttcorp.model.Producto;
import com.salmonttcorp.model.Ruta;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase responsable de la carga de datos de centros de cultivo desde archivos de texto.
 * <p>
 * Soporta archivos:
 * txt sin encabezado, separado por punto y coma.
 * csv con encabezado en la primera línea, separado por punto y coma.
 * <p>
 * Formatos admitidos por línea:
 * 1) Formato simple con la información de centro y producto (6 columnas):
 *    nombreCentro; comuna; produccion; nombreProducto; tipoProducto; precioProducto
 * <p>
 * 2) Formato completo con la información de centro, producto, direccion y ruta (12 columnas):
 *    nombreCentro; comuna; produccion; nombreProducto; tipoProducto; precioProducto;
 *    calle; número; region; nomRuta; responsableRuta; fechaRuta
 * <p>
 * Autor: Víctor Valenzuela
 */
public class GestorDatos {

    private static final int COLUMNAS_SIMPLE = 6;
    private static final int COLUMNAS_COMPLETAS = 12;

    /**
     * Carga los datos desde el archivo indicado, detectando el formato según la extensión.
     *
     * @param rutaArchivo nombre del archivo en la carpeta resources
     * @return lista de centros de cultivo cargados. Si ocurre un error, puede retornar una lista vacía.
     */
    public List<CentroCultivo> cargarDatos(String rutaArchivo) {
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacía.");
        }

        if (rutaArchivo.toLowerCase().endsWith(".csv")) {
            return cargarDesdeCsv(rutaArchivo);
        } else {
            // Por defecto se considera TXT
            return cargarDesdeTxt(rutaArchivo);
        }
    }

    // ===================== TXT ======================

    private List<CentroCultivo> cargarDesdeTxt(String rutaArchivo) {
        List<CentroCultivo> datos = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(rutaArchivo)) {

            if (inputStream == null) {
                throw new IllegalArgumentException("No se encontró el archivo TXT: " + rutaArchivo);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                String linea;
                int numeroLinea = 0;

                while ((linea = reader.readLine()) != null) {
                    numeroLinea++;

                    if (linea.trim().isEmpty()) {
                        continue;
                    }

                    procesarLinea(datos, linea, numeroLinea, false);
                }
            }

        } catch (Exception e) {
            System.out.println("Error general al cargar TXT: " + e.getMessage());
        }

        return datos;
    }

    // ===================== CSV ======================

    private List<CentroCultivo> cargarDesdeCsv(String rutaArchivo) {
        List<CentroCultivo> datos = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(rutaArchivo)) {

            if (inputStream == null) {
                throw new IllegalArgumentException("No se encontró el archivo CSV: " + rutaArchivo);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                String linea;
                int numeroLinea = 0;
                boolean esPrimeraLinea = true;

                while ((linea = reader.readLine()) != null) {
                    numeroLinea++;

                    if (esPrimeraLinea) {
                        // Se asume encabezado y se omite
                        esPrimeraLinea = false;
                        continue;
                    }

                    if (linea.trim().isEmpty()) {
                        continue;
                    }

                    procesarLinea(datos, linea, numeroLinea, true);
                }
            }

        } catch (Exception e) {
            System.out.println("Error general al cargar CSV: " + e.getMessage());
        }

        return datos;
    }

    // ===================== PROCESAMIENTO COMÚN ======================

    private void procesarLinea(List<CentroCultivo> datos,
                               String linea,
                               int numeroLinea,
                               boolean desdeCsv) {

        String tipoArchivo = desdeCsv ? "CSV" : "TXT";
        String[] partes = linea.split(";");

        if (partes.length == COLUMNAS_SIMPLE) {
            crearCentroBasico(datos, partes, numeroLinea, tipoArchivo);
        } else if (partes.length == COLUMNAS_COMPLETAS) {
            crearCentroCompleto(datos, partes, numeroLinea, tipoArchivo);
        } else {
            System.out.println("[" + tipoArchivo + "] Línea " + numeroLinea +
                    " tiene " + partes.length + " columnas. " +
                    "Solo se aceptan " + COLUMNAS_SIMPLE + " o " + COLUMNAS_COMPLETAS +
                    ". Línea omitida.");
        }
    }

    /**
     * Crea un centro usando solo la información básica (sin dirección ni ruta).
     */
    private void crearCentroBasico(List<CentroCultivo> datos,
                                   String[] partes,
                                   int numeroLinea,
                                   String tipoArchivo) {
        try {
            String nombreCentro = partes[0].trim();
            String comuna = partes[1].trim();
            int produccion = Integer.parseInt(partes[2].trim());
            String nombreProducto = partes[3].trim();
            String tipoProducto = partes[4].trim();
            double precioProducto = Double.parseDouble(partes[5].trim());

            Producto producto = new Producto(nombreProducto, tipoProducto, precioProducto);
            CentroCultivo centro = new CentroCultivo(nombreCentro, comuna, producto, produccion);

            datos.add(centro);

        } catch (NumberFormatException nfe) {
            System.out.println("[" + tipoArchivo + "] (BÁSICO) Error de formato numérico en línea "
                    + numeroLinea + ": " + nfe.getMessage() + ". Línea omitida.");
        } catch (IllegalArgumentException iae) {
            System.out.println("[" + tipoArchivo + "] (BÁSICO) Datos inválidos en línea "
                    + numeroLinea + ": " + iae.getMessage() + ". Línea omitida.");
        } catch (Exception e) {
            System.out.println("[" + tipoArchivo + "] (BÁSICO) Error inesperado en línea "
                    + numeroLinea + ": " + e.getMessage() + ". Línea omitida.");
        }
    }

    /**
     * Crea un centro usando información completa: producto, dirección y ruta asociada.
     */
    private void crearCentroCompleto(List<CentroCultivo> datos,
                                     String[] partes,
                                     int numeroLinea,
                                     String tipoArchivo) {
        try {
            // Básico
            String nombreCentro = partes[0].trim();
            String comuna = partes[1].trim();
            int produccion = Integer.parseInt(partes[2].trim());
            String nombreProducto = partes[3].trim();
            String tipoProducto = partes[4].trim();
            double precioProducto = Double.parseDouble(partes[5].trim());

            // Dirección (usa comuna de columna [1])
            String calle = partes[6].trim();
            String numero = partes[7].trim();
            String region = partes[8].trim();

            Direccion direccion = new Direccion(calle, numero, comuna, region);

            // Ruta asociada
            String nomRuta = partes[9].trim();
            String responsableRuta = partes[10].trim();
            String fechaRuta = partes[11].trim();

            Ruta ruta = new Ruta(nomRuta, responsableRuta, fechaRuta, direccion);

            Producto producto = new Producto(nombreProducto, tipoProducto, precioProducto);
            CentroCultivo centro = new CentroCultivo(
                    nombreCentro, producto, produccion, direccion, ruta
            );

            datos.add(centro);

        } catch (NumberFormatException nfe) {
            System.out.println("[" + tipoArchivo + "] (COMPLETO) Error de formato numérico en línea "
                    + numeroLinea + ": " + nfe.getMessage() +
                    ". Revise producción y precio. Línea omitida.");
        } catch (IllegalArgumentException iae) {
            System.out.println("[" + tipoArchivo + "] (COMPLETO) Datos inválidos en línea "
                    + numeroLinea + ": " + iae.getMessage() + ". Línea omitida.");
        } catch (Exception e) {
            System.out.println("[" + tipoArchivo + "] (COMPLETO) Error inesperado en línea "
                    + numeroLinea + ": " + e.getMessage() + ". Línea omitida.");
        }
    }
}

