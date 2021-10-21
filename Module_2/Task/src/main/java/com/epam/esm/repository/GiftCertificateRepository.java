package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.RepositoryException;

import java.util.List;

public interface GiftCertificateRepository extends CRDRepository<GiftCertificate> {
    GiftCertificate update(long id, GiftCertificate certificate) throws RepositoryException;
}
