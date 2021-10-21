package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.RepositoryException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TagRepositoryTest {

    @Inject
    TagRepository repository;

    @Test
    public void shouldSaveTag() throws RepositoryException {
        Tag tag = new Tag();
        tag.setName("summer");
        int result = repository.save(tag);
        assertEquals(1, result);
    }

    @Test
    public void shouldGetTagById() throws RepositoryException {
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("summer");
        Tag result = repository.getById(1);
        assertEquals(tag, result);
    }

    @Test
    public void shouldGetAllTags() throws RepositoryException {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));
        tags.add(new Tag(2, "winter"));
        tags.add(new Tag(3, "spring"));
        tags.add(new Tag(4, "autumn"));

        List<Tag> result = repository.getAll();

        assertEquals(tags, result);
    }

    @Test
    public void shouldDeleteTagById() throws RepositoryException {
        repository.delete(1);

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(2, "winter"));
        tags.add(new Tag(3, "spring"));
        tags.add(new Tag(4, "autumn"));

        List<Tag> resultList = repository.getAll();

        assertEquals(tags, resultList);
    }

    @Test
    public void shouldDeleteAllTags() throws RepositoryException {
        repository.deleteAll();

        List<Tag> tags = new ArrayList<>();
        List<Tag> resultList = repository.getAll();

        assertEquals(tags, resultList);
    }
}
