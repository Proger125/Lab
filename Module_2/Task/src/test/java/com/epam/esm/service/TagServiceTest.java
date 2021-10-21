package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.RepositoryException;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class TagServiceTest {
    @Mock
    @Inject
    private TagRepository repository;

    @InjectMocks
    @Inject
    private TagService service;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTagTest() throws RepositoryException, ServiceException {
        Tag tag = new Tag(1, "summer");
        when(repository.save(any(Tag.class))).thenReturn(1);

        service.save(tag);

        when(repository.getById(1)).thenReturn(tag);

        Tag newTag = service.getById(1);

        assertEquals(tag, newTag);
    }

    @Test
    public void getByIdTest() throws RepositoryException, ServiceException {
        Tag tag = new Tag(1, "summer");
        when(repository.getById(1)).thenReturn(tag);

        Tag newTag = service.getById(1);

        assertEquals(tag, newTag);
    }

    @Test
    public void getAllTest() throws RepositoryException, ServiceException {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));
        tags.add(new Tag(2, "winter"));

        when(repository.getAll()).thenReturn(tags);

        List<Tag> newTags = service.getAll();

        assertEquals(tags, newTags);
    }

    @Test
    public void deleteByIdTest() throws RepositoryException, ServiceException {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));
        tags.add(new Tag(2, "winter"));

        when(repository.delete(1)).then(invocationOnMock -> {
            tags.remove(0);
            return 1;
        });

        service.delete(1);

        when(repository.getAll()).thenReturn(tags);

        List<Tag> newTags = service.getAll();

        assertEquals(tags, newTags);
    }

    @Test
    public void deleteAllTest() throws RepositoryException, ServiceException {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));
        tags.add(new Tag(2, "winter"));

        when(repository.deleteAll()).then(invocationOnMock -> {
            tags.clear();
            return 2;
        });

        service.deleteAll();

        when(repository.getAll()).thenReturn(tags);

        List<Tag> newTags = service.getAll();

        assertEquals(tags, newTags);
    }
}
