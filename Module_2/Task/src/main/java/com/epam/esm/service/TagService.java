package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ServiceException;

import java.util.List;

public interface TagService extends CRDService<Tag>{
    List<Tag> getAll() throws ServiceException;
}
