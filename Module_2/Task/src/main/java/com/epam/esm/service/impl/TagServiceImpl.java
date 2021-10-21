package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.RepositoryException;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository repository;

    @Override
    public int save(Tag tag) throws ServiceException {
        try{
            return repository.save(tag);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute save() request in TagService", e);
        }
    }

    @Override
    @Nullable
    public Tag getById(long id) throws ServiceException {
        try{
            return repository.getById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getById() request in TagService", e);
        }
    }

    @Override
    public List<Tag> getAll() throws ServiceException {
        try {
            return repository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getAll() request in TagService", e);
        }
    }

    @Override
    public int delete(long id) throws ServiceException {
        try{
            return repository.delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute delete() request in TagService", e);
        }
    }

    @Override
    public int deleteAll() throws ServiceException {
        try{
            return repository.deleteAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute deleteAll() request in TagService", e);
        }
    }
}
