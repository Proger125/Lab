package com.epam.esm.repository;

import com.epam.esm.entity.Entity;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.RepositoryException;

import java.util.List;

public interface CRDRepository<T extends Entity> {
    int save(T tag) throws RepositoryException;
    T getById(long id) throws RepositoryException;
    List<T> getAll() throws RepositoryException;
    int delete(long id) throws RepositoryException;
    int deleteAll() throws RepositoryException;
}
