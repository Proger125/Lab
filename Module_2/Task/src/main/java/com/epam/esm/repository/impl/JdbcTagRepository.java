package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.impl.mapper.TagRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTagRepository implements TagRepository {

    private static final String INSERT_TAG_SQL = "INSERT INTO tag (name) VALUES (?)";
    private static final String GET_TAG_BY_ID_SQL = "SELECT id, name FROM tag WHERE id = ?";
    private static final String GET_ALL_TAGS_SQL = "SELECT id, name FROM tag";
    private static final String DELETE_TAG_BY_ID_SQL = "DELETE FROM tag WHERE id = ?";
    private static final String DELETE_ALL_TAGS_SQL = "DELETE FROM tag";

    @Autowired
    private JdbcTemplate template;

    @Override
    public int save(Tag tag) {
        return template.update(INSERT_TAG_SQL, tag.getName());
    }

    @Override
    public Tag getById(long id) {
        return template.queryForObject(GET_TAG_BY_ID_SQL, new TagRowMapper(), id);
    }

    @Override
    public List<Tag> getAll() {
        return template.query(GET_ALL_TAGS_SQL, new TagRowMapper());
    }

    @Override
    public int delete(long id) {
        return template.update(DELETE_TAG_BY_ID_SQL, id);
    }

    @Override
    public int deleteAll() {
        return template.update(DELETE_ALL_TAGS_SQL);
    }
}
