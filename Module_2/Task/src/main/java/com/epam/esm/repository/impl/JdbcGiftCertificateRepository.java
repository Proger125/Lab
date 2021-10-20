package com.epam.esm.repository.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.impl.mapper.GiftCertificateRowMapper;
import com.epam.esm.repository.impl.mapper.TagRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private static final String GET_TAGS_ID_BY_CERTIFICATE_ID_SQL = "SELECT tag_id FROM certificate_tag WHERE certificate_id = ?";
    private static final String GET_TAG_BY_ID = "SELECT id, name FROM tag WHERE id = ?";
    private static final String GET_ALL_CERTIFICATES = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM certificate";
    private static final String DELETE_ALL_CERTIFICATES = "DELETE FROM certificate";
    private static final String DELETE_ALL_CERTIFICATE_TAG_CONNECTION = "DELETE FROM certificate_tag";

    @Autowired
    private JdbcTemplate template;

    @Override
    @Transactional
    public int save(GiftCertificate certificate) {
        int insertCertificateResult = template.update(INSERT_CERTIFICATE_SQL, certificate.getName(),
                certificate.getDescription(), certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate(), certificate.getLastUpdateDate());
        long certificateId = template.queryForObject(GET_CERTIFICATE_ID_BY_INFO, (rs, rowNum) -> rs.getLong("id"), certificate.getName(),
                certificate.getDescription(), certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate(), certificate.getLastUpdateDate());

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

    }

    @Override
    public int deleteAll() {
        int deletedCertificateTagConnection = template.update(DELETE_ALL_CERTIFICATE_TAG_CONNECTION);
        int deletedCertificates = template.update(DELETE_ALL_CERTIFICATES);
        return deletedCertificates + deletedCertificateTagConnection;
    }
}
