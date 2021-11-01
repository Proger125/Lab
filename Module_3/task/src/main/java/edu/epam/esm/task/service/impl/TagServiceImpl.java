package edu.epam.esm.task.service.impl;

import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.repository.TagRepository;
import edu.epam.esm.task.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    final TagRepository repository;

    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Tag> getAll(Pageable pageable) {
        return  repository.findAll(pageable);
    }

    @Override
    public Optional<Tag> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Tag save(Tag tag) {
        Optional<Tag> optionalTag = repository.findTagByName(tag.getName());
        if (optionalTag.isEmpty()){
            return repository.save(tag);
        }
        return optionalTag.get();
    }
}
