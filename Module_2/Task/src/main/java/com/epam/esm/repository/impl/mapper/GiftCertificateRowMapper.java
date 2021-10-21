package com.epam.esm.repository.impl.mapper;

import com.epam.esm.entity.GiftCertificate;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import static com.epam.esm.repository.impl.ColumnName.*;
public class GiftCertificateRowMapper implements RowMapper<GiftCertificate> {
    @SneakyThrows
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(rs.getLong(ID));
        certificate.setName(rs.getString(NAME));
        certificate.setDescription(rs.getString(DESCRIPTION));
        certificate.setPrice(rs.getBigDecimal(PRICE));
        certificate.setDuration(rs.getInt(DURATION));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

        certificate.setCreateDate(format.parse(rs.getString(CREATE_DATE)));
        certificate.setLastUpdateDate(format.parse(rs.getString(LAST_UPDATE_DATE)));

        return certificate;
    }
}
