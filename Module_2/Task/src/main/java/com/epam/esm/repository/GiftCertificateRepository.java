package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.RepositoryException;

import java.util.List;

public interface GiftCertificateRepository extends CRDRepository<GiftCertificate> {
    GiftCertificate update(long id, GiftCertificate certificate) throws RepositoryException;
    List<GiftCertificate> getByTagName(String tagName) throws RepositoryException;
    List<GiftCertificate> getByPartOfName(String namePart) throws RepositoryException;
    List<GiftCertificate> getByPartOfDescription(String descPart) throws RepositoryException;
}
