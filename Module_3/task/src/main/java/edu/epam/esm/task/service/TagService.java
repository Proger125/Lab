package edu.epam.esm.task.service;

import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * The interface Tag service.
 */
public interface TagService {
    /**
     * Gets all.
     *
     * @param pageable the pageable
     * @return the all
     * @throws ServiceException the service exception
     */
    Page<Tag> getAll(Pageable pageable) throws ServiceException;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws ServiceException the service exception
     */
    Optional<Tag> getById(long id) throws ServiceException;

    /**
     * Save tag.
     *
     * @param tag the tag
     * @return the tag
     * @throws ServiceException the service exception
     */
    Tag save(Tag tag) throws ServiceException;

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    boolean deleteById(long id) throws ServiceException;

    /**
     * Delete all.
     *
     * @throws ServiceException the service exception
     */
    void deleteAll() throws ServiceException;
}
