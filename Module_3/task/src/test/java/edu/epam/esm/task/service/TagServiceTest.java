package edu.epam.esm.task.service;

import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.exception.ServiceException;
import edu.epam.esm.task.repository.TagRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TagServiceTest {
    @Mock
    @Autowired
    private TagRepository repository;

    @InjectMocks
    @Autowired
    private TagService service;

    @BeforeAll
    static void setUp(){
        MockitoAnnotations.openMocks(TagServiceTest.class);
    }

    @Test
    public void saveTagTest() throws ServiceException {
        Tag tag = new Tag(1, "summer");
        when(repository.save(any(Tag.class))).thenReturn(tag);

        service.save(tag);

        Optional<Tag> optionalTag = Optional.of(tag);

        when(repository.findById(1L)).thenReturn(optionalTag);


        assertEquals(tag, optionalTag.get());
    }

    @Test
    public void getByIdTest() {
        Tag tag = new Tag(1, "summer");

        Optional<Tag> optionalTag = Optional.of(tag);

        when(repository.findById(1L)).thenReturn(optionalTag);


        assertEquals(tag, optionalTag.get());
    }
}
