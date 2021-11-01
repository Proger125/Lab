package edu.epam.esm.task.generating;

import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagGenerating {

    @Autowired
    private TagRepository repository;

    @Test
    public void generateThousandTags(){
        for (int i = 0; i < 1000; i++){
            Tag tag = new Tag();
            tag.setName("tag_" + (i + 1));
            repository.save(tag);
        }
    }
}
