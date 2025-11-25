package com.salmonttcorp.app;

import com.salmonttcorp.model.CentroCultivo;
import com.salmonttcorp.service.CentroService;
import com.salmonttcorp.util.GestorDatos;

import java.util.List;

/**
 * Clase principal del proyecto SalmonttApp.
 * Orquesta el flujo de la aplicación:
 *     Carga los datos de centros de cultivo desde un archivo de recursos
 *     Crea el servicio de centros ({@link CentroService})
 *     Muestra la lista completa en formato tabular
 *     Realiza una búsqueda por comuna
 *     Aplica un filtro por producción mínima
 *     Muestra la lista ordenada por nombre de centro

 * Autor: Víctor Valenzuela
 */
public class Main {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args parámetros de la línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        GestorDatos gestorDatos = new GestorDatos();

        // cambiar el archivo según lo que desees probar:
        // - "datosCentros.txt"(TXT sin encabezado)
        // - "datosCentros.csv"(CSV con encabezado)
        // - "datosCentrosVacios.txt"(TXT vacío)
        String archivoDatos = "datosCentros.csv";

        // 1. Carga de datos
        List<CentroCultivo> centros = gestorDatos.cargarDatos(archivoDatos);

        if (centros == null || centros.isEmpty()) {
            System.out.println("No se cargaron centros de cultivo. Revise el archivo de datos: " + archivoDatos);
            return;
        }

        // 2. Creación del servicio de centros
        CentroService service = new CentroService(centros);

        // 3. Lista completa
        System.out.println("\n=== LISTA COMPLETA DE CENTROS DE CULTIVO ===\n");
        imprimirTabla(service.listarTodos());

        // 4. Búsqueda por comuna
        System.out.println("\n=== BÚSQUEDA: Centros de la comuna 'Calbuco' ===\n");
        imprimirTabla(service.buscarPorComuna("Calbuco"));

        // 5. Filtro por producción mínima
        System.out.println("\n=== FILTRO: Producción >= 1500 ===\n");
        imprimirTabla(service.filtrarPorProduccionMinima(1500));

        // 6. Lista ordenada por nombre de centro
        System.out.println("\n=== LISTA ORDENADA POR NOMBRE DE CENTRO ===\n");
        imprimirTabla(service.ordenarPorNombre());

        // 7. Tabla completa mostrando la composición total del dominio
        System.out.println("\n=== COMPOSICIÓN COMPLETA: CENTRO + PRODUCTO + DIRECCIÓN + RUTA ===\n");
        imprimirTablaCompleta(centros);

    }

    /**
     * Imprime una colección de centros de cultivo en formato tabular.
     *
     * @param lista lista de centros a mostrar
     */
    private static void imprimirTabla(List<CentroCultivo> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Sin resultados para mostrar.\n");
            return;
        }

        System.out.printf("%-20s | %-15s | %-11s | %-27s | %-12s | %-10s%n",
                "CENTRO", "COMUNA", "PRODUCCIÓN", "PRODUCTO", "TIPO", "PRECIO");
        System.out.println("---------------------------------------------------------------------------------------------");

        lista.forEach(Main::imprimirFila);
        System.out.println(); // línea en blanco final
    }

    /**
     * Imprime una fila correspondiente a un centro de cultivo.
     *
     * @param c centro de cultivo a mostrar
     */
    private static void imprimirFila(CentroCultivo c) {
        System.out.printf("%-20s | %-15s | %-11d | %-27s | %-12s | %-10.1f%n",
                c.getNombreCentro(),
                c.getDireccion().getComuna(),
                c.getProduccion(),
                c.getProducto().getNombreProducto(),
                c.getProducto().getTipoProducto(),
                c.getProducto().getPrecioProducto());
    }


    /**
     * Imprime una tabla mostrando la composición total de cada centro:
     * CentroCultivo -> Producto -> Dirección -> Ruta.
     *
     * @param lista lista completa de centros a mostrar
     */
    private static void imprimirTablaCompleta(List<CentroCultivo> lista) {

        System.out.printf("%-20s | %-25s | %-40s | %-25s | %-25s | %-15s%n",
                "CENTRO", "PRODUCTO", "DIRECCIÓN", "RUTA", "RESPONSABLE", "FECHA");

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        for (CentroCultivo c : lista) {
            imprimirFilaCompleta(c);
        }

        System.out.println();
    }

    /**
     * Imprime una fila mostrando todo el contenido del dominio.
     *
     * @param c centro a mostrar
     */
    private static void imprimirFilaCompleta(CentroCultivo c) {

        String producto = c.getProducto().getNombreProducto();
        String direccion = (c.getDireccion() != null)
                ? c.getDireccion().toString()
                : "Sin dirección";

        String ruta = (c.getRutaAsociada() != null)
                ? c.getRutaAsociada().getNomRuta()
                : "Sin ruta";
        String responsable = (c.getRutaAsociada() != null)
                ? c.getRutaAsociada().getResponsable()
                : "-";

        String fecha = (c.getRutaAsociada() != null)
                ? c.getRutaAsociada().getFecha()
                : "-";

        System.out.printf("%-20s | %-25s | %-40s | %-25s | %-25s | %-15s%n",
                c.getNombreCentro(),
                producto,
                direccion,
                ruta,
                responsable,
                fecha
        );
    }


}
