package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.impl.mapper.GiftCertificateRowMapper;
import com.epam.esm.repository.impl.mapper.TagRowMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcGiftCertificateRepository implements GiftCertificateRepository {

    private static final String INSERT_CERTIFICATE_SQL = "INSERT INTO certificate (name, description, " +
            "price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_CERTIFICATE_ID_BY_INFO = "SELECT id FROM certificate WHERE name = ? " +
            "AND description = ? AND price = ? AND duration = ? AND create_date = ? AND last_update_date = ?";
    private static final String INSERT_TAG_IF_NOT_EXISTS = "INSERT INTO tag (name) " +
            " SELECT ? WHERE NOT EXISTS (SELECT * FROM tag WHERE name = ?)";
    private static final String GET_TAG_ID_BY_NAME = "SELECT id FROM tag WHERE name = ?";
    private static final String INSERT_CERTIFICATE_TAG_CONNECTION_SQL = "INSERT INTO certificate_tag " +
            "(certificate_id, tag_id) VALUES (?, ?)";
    private static final String GET_CERTIFICATE_BY_ID_SQL = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM certificate WHERE id = ?";
    private static final String GET_CERTIFICATES_ID_BY_TAG_ID = "SELECT certificate_id " +
            "FROM certificate_tag WHERE tag_id = ?";
    private static final String GET_TAGS_ID_BY_CERTIFICATE_ID_SQL = "SELECT tag_id FROM certificate_tag WHERE certificate_id = ?";
    private static final String GET_TAG_BY_ID = "SELECT id, name FROM tag WHERE id = ?";
    private static final String GET_ALL_CERTIFICATES = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM certificate";
    private static final String DELETE_CERTIFICATE_BY_ID = "DELETE FROM certificate WHERE id = ?";
    private static final String DELETE_CERTIFICATE_TAG_CONNECTION_BY_CERTIFICATE_ID = "DELETE FROM " +
            "certificate_tag WHERE certificate_id = ?";
    private static final String DELETE_ALL_CERTIFICATES = "DELETE FROM certificate";
    private static final String DELETE_ALL_CERTIFICATE_TAG_CONNECTION = "DELETE FROM certificate_tag";
    private static final String UPDATE_CERTIFICATE_BY_ID = "UPDATE certificate SET name = ?, " +
            "description = ?, price = ?, duration = ?, create_date = ?, last_update_date = ?";

    @Autowired
    private JdbcTemplate template;

    private final DateTimeFormatter parser = ISODateTimeFormat.dateTime();

    @Override
    @Transactional
    public int save(GiftCertificate certificate) {
        int insertCertificateResult = template.update(INSERT_CERTIFICATE_SQL, certificate.getName(),
                certificate.getDescription(), certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate().toString(parser), certificate.getLastUpdateDate().toString(parser));
        long certificateId = template.queryForObject(GET_CERTIFICATE_ID_BY_INFO, (rs, rowNum) -> rs.getLong("id"), certificate.getName(),
                certificate.getDescription(), certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate().toString(parser), certificate.getLastUpdateDate().toString(parser));

        int insertTagsResult = 0;
        for (var tag : certificate.getTags()){
            insertTagsResult += template.update(INSERT_TAG_IF_NOT_EXISTS, tag.getName(), tag.getName());
            long tagId = template.queryForObject(GET_TAG_ID_BY_NAME, (rs, rowNum) -> rs.getLong("id"), tag.getName());
            insertTagsResult += template.update(INSERT_CERTIFICATE_TAG_CONNECTION_SQL, certificateId, tagId);
        }
        return insertCertificateResult + insertTagsResult;
    }

    @Override
    public GiftCertificate getById(long id) {
        GiftCertificate certificate = template.queryForObject(GET_CERTIFICATE_BY_ID_SQL, new GiftCertificateRowMapper(), id);
        List<Long> tagsId = template.query(GET_TAGS_ID_BY_CERTIFICATE_ID_SQL, (rs, rowNum) -> rs.getLong("tag_id"), id);
        List<Tag> tags = new ArrayList<>();
        for (var tagId : tagsId){
            Tag tag = template.queryForObject(GET_TAG_BY_ID, new TagRowMapper(), tagId);
            tags.add(tag);
        }

        certificate.setTags(tags);
        return certificate;
    }

    @Override
    public List<GiftCertificate> getAll() {
        List<GiftCertificate> certificates = template.query(GET_ALL_CERTIFICATES, new GiftCertificateRowMapper());
        for (var certificate : certificates){
            List<Long> tagsId = template.query(GET_TAGS_ID_BY_CERTIFICATE_ID_SQL,
                    (rs, rowNum) -> rs.getLong("tag_id"), certificate.getId());
            List<Tag> tags = new ArrayList<>();
            for (var tagId : tagsId){
                Tag tag = template.queryForObject(GET_TAG_BY_ID, new TagRowMapper(), tagId);
                tags.add(tag);
            }

            certificate.setTags(tags);
        }
        return certificates;
    }

    @Override
    public int delete(long id) {
        int deletedCertificateTagConnection = template.update(DELETE_CERTIFICATE_TAG_CONNECTION_BY_CERTIFICATE_ID, id);
        int deletedCertificate = template.update(DELETE_CERTIFICATE_BY_ID, id);
        return deletedCertificate + deletedCertificateTagConnection;
    }

    @Override
    public int deleteAll() {
        int deletedCertificateTagConnection = template.update(DELETE_ALL_CERTIFICATE_TAG_CONNECTION);
        int deletedCertificates = template.update(DELETE_ALL_CERTIFICATES);
        return deletedCertificates + deletedCertificateTagConnection;
    }

    @Override
    public GiftCertificate update(long id, GiftCertificate newCertificate) {
        GiftCertificate oldCertificate = getById(id);

        String oldName = oldCertificate.getName();
        String newName = newCertificate.getName();
        if (!Objects.equals(oldName, newName)){
            oldCertificate.setName(newName);
        }

        String oldDescription = oldCertificate.getDescription();
        String newDescription = newCertificate.getDescription();
        if (!Objects.equals(oldDescription, newDescription)){
            oldCertificate.setDescription(newDescription);
        }

        BigDecimal oldPrice = oldCertificate.getPrice();
        BigDecimal newPrice = newCertificate.getPrice();
        if (!Objects.equals(oldPrice, newPrice)){
            oldCertificate.setPrice(newPrice);
        }

        int oldDuration = oldCertificate.getDuration();
        int newDuration = newCertificate.getDuration();
        if (oldDuration != newDuration){
            oldCertificate.setDuration(newDuration);
        }

        DateTime oldCreateDate = oldCertificate.getCreateDate();
        DateTime newCreateDate = newCertificate.getCreateDate();
        if (oldCreateDate != newCreateDate){
            oldCertificate.setCreateDate(newCreateDate);
        }

        DateTime oldLastUpdateDate = oldCertificate.getLastUpdateDate();
        DateTime newLastUpdateDate = newCertificate.getLastUpdateDate();
        if (oldLastUpdateDate != newLastUpdateDate){
            oldCertificate.setLastUpdateDate(newLastUpdateDate);
        }

        List<Tag> oldTags = oldCertificate.getTags();
        List<Tag> newTags = newCertificate.getTags();
        for (var newTag : newTags){
            if (!oldTags.contains(newTag)){
                oldTags.add(newTag);
                template.update(INSERT_TAG_IF_NOT_EXISTS, newTag.getName(), newTag.getName());
                long tagId = template.queryForObject(GET_TAG_ID_BY_NAME, (rs, rowNum) -> rs.getLong("id"), newTag.getName());
                template.update(INSERT_CERTIFICATE_TAG_CONNECTION_SQL, id, tagId);
            }
        }
        oldCertificate.setTags(oldTags);

        template.update(UPDATE_CERTIFICATE_BY_ID, oldCertificate.getName(), oldCertificate.getDescription(),
                oldCertificate.getPrice(), oldCertificate.getDuration(), oldCertificate.getCreateDate().toString(parser),
                oldCertificate.getLastUpdateDate().toString(parser));
        return getById(id);
    }

    @Override
    public List<GiftCertificate> getByTagName(String tagName) {
        long tagId = template.queryForObject(GET_TAG_ID_BY_NAME,
                (rs, rowNum) -> rs.getLong("id"), tagName);
        List<Long> certificatesId = template.query(GET_CERTIFICATES_ID_BY_TAG_ID,
                (rs, rowNum) -> rs.getLong("certificate_id"), tagId);
        List<GiftCertificate> certificates = new ArrayList<>();
        for (var certificateId : certificatesId){
            certificates.add(getById(certificateId));
        }
        return certificates;
    }
}
