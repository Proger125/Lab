package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.RepositoryException;

/**
 * The interface that contains functionality for GiftCertificateRepository
 */
public interface GiftCertificateRepository extends CRDRepository<GiftCertificate> {
    /**
     * Updates gift certificate.
     *
     * @param id - the id of updatable item
     * @param certificate - new value of item
     * @return updated item
     * @throws RepositoryException the repository exception when problems occurred
     */
    GiftCertificate update(long id, GiftCertificate certificate) throws RepositoryException;
}
