package edu.epam.esm.task.repository;

import edu.epam.esm.task.entity.Tag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TagRepositoryTest {

    @Autowired
    private TagRepository repository;

    private static List<Tag> tags;
    @BeforeAll
    static void setUp(){
        tags = new ArrayList<>();

        Tag carTag = new Tag(1, "car");
        Tag giftTag = new Tag(2, "gift");
        Tag birthdayTag = new Tag(3, "birthday");

        tags.add(carTag);
        tags.add(giftTag);
        tags.add(birthdayTag);
    }

    @BeforeEach
    public void saveData(){
        repository.saveAll(tags);
    }

    @AfterAll
    static void tearDown(){
        tags = null;
    }

    @Test
    public void saveMethodTestTrue(){
        Tag tag = new Tag(4, "supercar");
        Tag result = repository.save(tag);

        assertEquals(tag, result);
    }

    @Test
    public void getByIdTestTrue(){
        Tag result = repository.findById(2L).get();

        assertEquals(tags.get(1), result);
    }

    @Test
    public void getAllTestTrue(){
        Pageable pageable = PageRequest.of(0, 5);
        Page<Tag> result = repository.findAll(pageable);
        List<Tag> resultList = result.stream().collect(Collectors.toList());

        assertEquals(resultList, tags);
    }

    @Test
    public void deleteByIdTestTrue(){
        repository.deleteById(1L);

        Optional<Tag> optionalTag = repository.findById(1L);

        assertEquals(optionalTag, Optional.empty());
    }

    @Test
    public void deleteAllTestTrue(){
        repository.deleteAll();
        List<Tag> result = (List<Tag>) repository.findAll();

        assertTrue(result.isEmpty());
    }
}
