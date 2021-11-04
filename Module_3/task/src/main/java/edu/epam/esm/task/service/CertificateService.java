package edu.epam.esm.task.service;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.entity.dto.CertificateUpdateDto;
import edu.epam.esm.task.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * The interface Certificate service.
 */
public interface CertificateService {
    /**
     * Gets all.
     *
     * @param pageable the pageable
     * @return the all
     * @throws ServiceException the service exception
     */
    Page<Certificate> getAll(Pageable pageable) throws ServiceException;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws ServiceException the service exception
     */
    Optional<Certificate> getById(long id) throws ServiceException;

    /**
     * Save certificate.
     *
     * @param certificate the certificate
     * @return the certificate
     * @throws ServiceException the service exception
     */
    Certificate save(Certificate certificate) throws ServiceException;

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteById(long id) throws ServiceException;

    /**
     * Delete all.
     *
     * @throws ServiceException the service exception
     */
    void deleteAll() throws ServiceException;

    /**
     * Update certificate boolean.
     *
     * @param id  the id
     * @param dto the dto
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateCertificate(long id, CertificateUpdateDto dto) throws ServiceException;

    /**
     * Gets certificates by tags.
     *
     * @param tags     the tags
     * @param pageable the pageable
     * @return the certificates by tags
     * @throws ServiceException the service exception
     */
    Page<Certificate> getCertificatesByTags(String[] tags, Pageable pageable) throws ServiceException;
}
