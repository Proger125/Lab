package com.epam.esm.repository.impl.mapper;

import com.epam.esm.entity.GiftCertificate;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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

        DateTimeFormatter parser = ISODateTimeFormat.dateTime();

        DateTime createDate = parser.parseDateTime(rs.getString(CREATE_DATE));
        certificate.setCreateDate(createDate);

        DateTime lastUpdateDate = parser.parseDateTime(rs.getString(LAST_UPDATE_DATE));
        certificate.setLastUpdateDate(lastUpdateDate);

        return certificate;
    }
}
