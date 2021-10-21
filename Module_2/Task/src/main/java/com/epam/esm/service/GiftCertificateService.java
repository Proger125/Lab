package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ServiceException;

import java.util.List;

public interface GiftCertificateService extends CRDService<GiftCertificate>{
    List<GiftCertificate> getAll(String tag, String search, String sort) throws ServiceException;
    GiftCertificate update(long id, GiftCertificate certificate) throws ServiceException;
}
