package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.RepositoryException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.repository.impl.mapper.TagRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcTagRepository implements TagRepository {

    private static final String INSERT_TAG_SQL = "INSERT INTO tag (name) VALUES (?)";
    private static final String GET_TAG_BY_ID_SQL = "SELECT id, name FROM tag WHERE id = ?";
    private static final String GET_ALL_TAGS_SQL = "SELECT id, name FROM tag";
    private static final String DELETE_TAG_BY_ID_SQL = "DELETE FROM tag WHERE id = ?";
    private static final String DELETE_CERTIFICATE_TAG_CONNECTION_BY_TAG_ID = "DELETE FROM certificate_tag " +
            "WHERE tag_id = ?";
    private static final String DELETE_ALL_TAGS_SQL = "DELETE FROM tag";
    private static final String DELETE_ALL_CERTIFICATE_TAG_CONNECTION = "DELETE FROM certificate_tag";

    @Autowired
    private JdbcTemplate template;

    @Override
    public int save(Tag tag) throws RepositoryException {
        try{
            return template.update(INSERT_TAG_SQL, tag.getName());
        }catch (DataAccessException e){
            throw new RepositoryException("Unable to handle save() request in TagRepository", e);
        }

    }

    @Override
    @Nullable
    public Tag getById(long id) throws RepositoryException {
        try{
            return template.queryForObject(GET_TAG_BY_ID_SQL, new TagRowMapper(), id);
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (DataAccessException e){
            throw new RepositoryException("Unable to handle getById() request in TagRepository", e);
        }

    }

    @Override
    public List<Tag> getAll() throws RepositoryException {
        List<Tag> tags;
        try{
            tags = template.query(GET_ALL_TAGS_SQL, new TagRowMapper());
        }catch (DataAccessException e){
            throw new RepositoryException("Unable to handle getAll() request in TagRepository", e);
        }
        return tags;
    }

    @Override
    public int delete(long id) throws RepositoryException {
        try{
            int deletedCertificateTagConnections = template.update(DELETE_CERTIFICATE_TAG_CONNECTION_BY_TAG_ID, id);
            int deletedTag =  template.update(DELETE_TAG_BY_ID_SQL, id);
            return deletedCertificateTagConnections + deletedTag;
        }catch (DataAccessException e){
            throw new RepositoryException("Unable to handle delete() request in TagRepository", e);
        }

    }

    @Override
    public int deleteAll() throws RepositoryException {
        try{
            int deletedCertificateTagConnection = template.update(DELETE_ALL_CERTIFICATE_TAG_CONNECTION);
            int deletedTag =  template.update(DELETE_ALL_TAGS_SQL);
            return deletedCertificateTagConnection + deletedTag;
        } catch (DataAccessException e){
            throw new RepositoryException("Unable to handle deleteAll() request in TagRepository", e);
        }

    }
}
