package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ServiceException;

import java.util.List;

/**
 * Interface that provides functionality for GiftCertificateService
 */
public interface GiftCertificateService extends CRDService<GiftCertificate>{
    /**
     * Gets all gift certificates
     *
     * @param tag - certificates should contain that
     * @param search - certificates names should contain that
     * @param sort - certificates should be sorted by that
     * @return all certificates that satisfy all conditions
     * @throws ServiceException the service exception when problems occurred
     */
    List<GiftCertificate> getAll(String tag, String search, String sort) throws ServiceException;

    /**
     * Updatez gift certificate.
     *
     * @param id - the id of updatable item
     * @param certificate new value of item
     * @return updated gift certificate
     * @throws ServiceException the service exception when problems occurred
     */
    GiftCertificate update(long id, GiftCertificate certificate) throws ServiceException;
}
