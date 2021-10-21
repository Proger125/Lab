package com.epam.esm.repository;

import com.epam.esm.entity.Entity;
import com.epam.esm.exception.RepositoryException;

import java.util.List;

/**
 * The interface which contains Create, Read and Delete functionality for Repository layer.
 *
 * @param <T> the type parameter
 */
public interface CRDRepository<T extends Entity> {
    /**
     * Saves the entity in repository
     *
     * @param entity - save object
     * @return number of changed rows
     * @throws RepositoryException the repository exception when problems occurred
     */
    int save(T entity) throws RepositoryException;

    /**
     * Gets the entity from repository by id
     *
     * @param id - id of the object to be searched for
     * @return entity by id
     * @throws RepositoryException the repository exception when problems occurred
     */
    T getById(long id) throws RepositoryException;

    /**
     * Gets all entities from repository
     *
     * @return all entities
     * @throws RepositoryException the repository exception when problems occurred
     */
    List<T> getAll() throws RepositoryException;

    /**
     * Delete entity by id
     *
     * @param id - id of the object to be deleted
     * @return amount of deleted rows
     * @throws RepositoryException the repository exception when problems occurred
     */
    int delete(long id) throws RepositoryException;

    /**
     * Delete all entities from repository
     *
     * @return amount of deleted rows
     * @throws RepositoryException the repository exception when problems occurred
     */
    int deleteAll() throws RepositoryException;
}
