package com.epam.esm.service;

import com.epam.esm.entity.Entity;
import com.epam.esm.exception.ServiceException;


/**
 * Interface that provides Create, Read and Delete functionality for Service layer
 *
 * @param <T> the type parameter
 */
public interface CRDService <T extends Entity> {
    /**
     * Saves entity
     *
     * @param entity save object
     * @return number of changed rows
     * @throws ServiceException the service exception when problems occurred
     */
    int save(T entity) throws ServiceException;

    /**
     * Gets the entity from repository by id
     *
     * @param id id of the object to be searched for
     * @return entity by id
     * @throws ServiceException the service exception when problems occurred
     */
    T getById(long id) throws ServiceException;

    /**
     * Delete entity by id
     *
     * @param id - id of the object to be deleted
     * @return amount of deleted rows
     * @throws ServiceException the service exception when problems occurred
     */
    int delete(long id) throws ServiceException;

    /**
     * Delete all entities from repository
     *
     * @return amount of deleted rows
     * @throws ServiceException the service exception when problems occurred
     */
    int deleteAll() throws ServiceException;
}
