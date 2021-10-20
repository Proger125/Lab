package com.epam.esm.repository.impl.mapper;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import static com.epam.esm.repository.impl.ColumnName.*;
public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(rs.getLong(ID));
        certificate.setName(rs.getString(NAME));
        certificate.setDescription(rs.getString(DESCRIPTION));
        certificate.setPrice(rs.getBigDecimal(PRICE));
        certificate.setDuration(rs.getInt(DURATION));

        TemporalAccessor accessor1 = DateTimeFormatter.ISO_INSTANT.parse(rs.getString(CREATE_DATE));
        Instant instant1 = Instant.from(accessor1);
        certificate.setCreateDate(Date.from(instant1));

        TemporalAccessor accessor2 = DateTimeFormatter.ISO_INSTANT.parse(rs.getString(LAST_UPDATE_DATE));
        Instant instant2 = Instant.from(accessor2);
        certificate.setLastUpdateDate(Date.from(instant2));

        return certificate;
    }
}
