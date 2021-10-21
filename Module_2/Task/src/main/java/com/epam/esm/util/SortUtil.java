package com.epam.esm.util;

import com.epam.esm.entity.Entity;

import java.util.List;

/**
 * Interface that provides functionality for sorting
 *
 * @param <T> the type parameter
 */
public interface SortUtil<T extends Entity> {
    /**
     * Sorts entities ascending
     *
     * @param list - list of entities
     * @param param - sorting param
     */
    void sortASC(List<T> list, String param);

    /**
     * Sorts entities descending
     *
     * @param list - list of entities
     * @param param - sorting param
     */
    void sortDESC(List<T> list, String param);
}
