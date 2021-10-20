package com.epam.esm.repository;

import com.epam.esm.entity.Entity;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface CRD<T extends Entity> {
    int save(T tag);
    T getById(long id);
    List<T> getAll();
    int delete(long id);
    int deleteAll();
}
