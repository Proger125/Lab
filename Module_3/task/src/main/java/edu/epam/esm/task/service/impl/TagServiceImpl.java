package edu.epam.esm.task.service.impl;

import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.exception.ServiceException;
import edu.epam.esm.task.repository.TagRepository;
import edu.epam.esm.task.service.TagService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    final TagRepository repository;

    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Tag> getAll(Pageable pageable) throws ServiceException {
        try {
            return repository.findAll(pageable);
        } catch (DataAccessException e){
            throw new ServiceException("Unable to execute TagService.getAll() request", e);
        }
    }

    @Override
    public Optional<Tag> getById(long id) throws ServiceException {
        try {
            return repository.findById(id);
        } catch (DataAccessException e){
            throw new ServiceException("Unable to execute TagService.getById() request", e);
        }
    }

    @Transactional
    @Override
    public Tag save(Tag tag) throws ServiceException {
        try {
            Optional<Tag> optionalTag = repository.findTagByName(tag.getName());
            if (optionalTag.isEmpty()) {
                return repository.save(tag);
            }
            return optionalTag.get();
        } catch (DataAccessException e){
            throw new ServiceException("Unable to execute TagService.save() request", e);
        }
    }

    @Override
    public boolean deleteById(long id) throws ServiceException {
        boolean result = false;
        try {
            if (repository.existsById(id)){
                repository.deleteById(id);
                result = true;
            }

        } catch (DataAccessException e){
            throw new ServiceException("Unable to execute TagService.deleteById() request", e);
        }
        return result;
    }

    @Override
    public void deleteAll() throws ServiceException {
        try {
            repository.deleteAll();
        } catch (DataAccessException e){
            throw new ServiceException("Unable to execute TagService.deleteAll() request", e);
        }
    }
}
