package com.epam.esm.service;

import com.epam.esm.entity.Entity;
import com.epam.esm.exception.ServiceException;


public interface CRDService <T extends Entity> {
    int save(T entity) throws ServiceException;
    T getById(long id) throws ServiceException;
    int delete(long id) throws ServiceException;
    int deleteAll() throws ServiceException;
}
