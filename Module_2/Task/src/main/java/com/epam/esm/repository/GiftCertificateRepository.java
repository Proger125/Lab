package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateRepository extends CRD<GiftCertificate> {
    GiftCertificate update(long id, GiftCertificate certificate);
    List<GiftCertificate> getByTagName(String tagName);
}
