package edu.epam.esm.task.repository;

import edu.epam.esm.task.entity.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>, PagingAndSortingRepository<Tag, Long> {
    Optional<Tag> findTagByName(String name);
}
