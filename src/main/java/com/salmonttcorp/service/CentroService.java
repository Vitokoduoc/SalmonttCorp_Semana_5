package com.salmonttcorp.service;

import com.salmonttcorp.model.CentroCultivo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que encapsula las operaciones sobre la colección de {@link CentroCultivo}.
 * Provee métodos para:
 * Listar todos los centros
 * Buscar por comuna
 * Filtrar por producción mínima
 * Ordenar por nombre de centro
 * Autor: Víctor Valenzuela
 */
public record CentroService(List<CentroCultivo> centros) {

    /**
     * Crea una instancia del servicio a partir de una lista de centros.
     *
     * @param centros lista inicial de centros de cultivo
     */
    public CentroService(List<CentroCultivo> centros) {
        // Se utiliza una nueva lista mutable para evitar modificar la referencia externa
        this.centros = (centros == null) ? new ArrayList<>() : new ArrayList<>(centros);
    }

    /**
     * Retorna una copia de la lista completa de centros.
     *
     * @return lista de centros
     */
    public List<CentroCultivo> listarTodos() {
        return new ArrayList<>(centros);
    }

    /**
     * Busca centros que pertenezcan a una comuna específica.
     *
     * @param comuna nombre de la comuna a buscar (no sensible a mayúsculas)
     * @return lista de centros que pertenecen a la comuna indicada
     */
    public List<CentroCultivo> buscarPorComuna(String comuna) {
        if (comuna == null || comuna.isBlank()) {
            return List.of();
        }

        String comunaBuscada = comuna.trim();

        return centros.stream()
                .filter(c -> c.getDireccion() != null &&
                        c.getDireccion().getComuna().equalsIgnoreCase(comunaBuscada))
                .collect(Collectors.toList());
    }

    /**
     * Filtra los centros cuya producción es mayor o igual a la producción mínima indicada.
     *
     * @param produccionMinima producción mínima requerida
     * @return lista de centros con producción mayor o igual a la mínima
     */
    public List<CentroCultivo> filtrarPorProduccionMinima(int produccionMinima) {
        return centros.stream()
                .filter(c -> c.getProduccion() >= produccionMinima)
                .collect(Collectors.toList());
    }

    /**
     * Retorna la lista de centros ordenada alfabéticamente por nombre.
     *
     * @return lista ordenada de centros
     */
    public List<CentroCultivo> ordenarPorNombre() {
        return centros.stream()
                .sorted(Comparator.comparing(CentroCultivo::getNombreCentro,
                        String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
}
