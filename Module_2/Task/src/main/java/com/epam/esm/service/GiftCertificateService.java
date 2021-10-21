package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ServiceException;

import java.util.List;

public interface GiftCertificateService extends CRDService<GiftCertificate>{
    GiftCertificate update(long id, GiftCertificate certificate) throws ServiceException;
    List<GiftCertificate> getByTagName(String tagName) throws ServiceException;
    List<GiftCertificate> getByPartOfName(String namePart) throws ServiceException;
    List<GiftCertificate> getByPartOfDesc(String descPart) throws ServiceException;
    List<GiftCertificate> getAllCertificatesSortedByParamASC(String param) throws ServiceException;
    List<GiftCertificate> getAllCertificatesSortedByParamDESC(String param) throws ServiceException;
}
