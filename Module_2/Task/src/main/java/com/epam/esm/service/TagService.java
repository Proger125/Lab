package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ServiceException;

import java.util.List;

/**
 * Interface that provides functionality for TagService
 */
public interface TagService extends CRDService<Tag>{
    /**
     * Gets all tags
     *
     * @return all tags
     * @throws ServiceException the service exception when problems occurred
     */
    List<Tag> getAll() throws ServiceException;
}
