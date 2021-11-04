package edu.epam.esm.task.service;

import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.entity.User;
import edu.epam.esm.task.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Gets all.
     *
     * @param pageable the pageable
     * @return the all
     * @throws ServiceException the service exception
     */
    Page<User> getAll(Pageable pageable) throws ServiceException;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws ServiceException the service exception
     */
    Optional<User> getById(long id) throws ServiceException;

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    User save(User user) throws ServiceException;

    /**
     * Buy certificate boolean.
     *
     * @param userId        the user id
     * @param certificateId the certificate id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean buyCertificate(long userId, long certificateId) throws ServiceException;

    /**
     * Gets most widely used tag.
     *
     * @return the most widely used tag
     * @throws ServiceException the service exception
     */
    Optional<Tag> getMostWidelyUsedTag() throws ServiceException;
}
