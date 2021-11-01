package edu.epam.esm.task.service;

import edu.epam.esm.task.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Page<Tag> getAll(Pageable pageable);
    Optional<Tag> getById(long id);
    Tag save(Tag tag);

}
