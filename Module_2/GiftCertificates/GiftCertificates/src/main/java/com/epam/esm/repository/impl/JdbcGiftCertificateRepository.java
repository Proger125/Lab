package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.repository.GiftCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcGiftCertificateRepository implements GiftCertificateRepository {

    private static final String INSERT_CERTIFICATE_SQL = "INSERT INTO certificate (name, description, price, duration, create_date, last_update_date)" +
            "VALUES (?, ?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate template;

    @Override
    public int save(GiftCertificate certificate) {
        return template.update(INSERT_CERTIFICATE_SQL, certificate.getName(),
                                certificate.getDescription(), certificate.getPrice(),
                                certificate.getDuration(), certificate.getCreateDate(),
                                certificate.getLastUpdateDate());
    }
}
