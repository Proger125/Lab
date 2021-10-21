package com.epam.esm.util;

import com.epam.esm.entity.Entity;

import java.util.List;

/**
 * Util interface that provides functionality for searching
 *
 * @param <T> the type parameter
 */
public interface SearchUtil <T extends Entity>{
    /**
     * Gets entities by tag name.
     *
     * @param list - list of entities
     * @param tag - searchable tag
     * @return list of entities that satisfy condition
     */
    List<T> getEntityByTagName(List<T> list, String tag);

    /**
     * Gets entities by name part.
     *
     * @param list - list of entities
     * @param namePart - searchable name part
     * @return list of entities that satisfy condition
     */
    List<T> getEntityByNamePart(List<T> list, String namePart);
}
