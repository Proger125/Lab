package edu.epam.esm.task.rest;

import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class TagController {

    @Autowired
    private TagService service;

    @GetMapping(value = "/tags")
    public CollectionModel<Tag> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Tag> tags = service.getAll(pageable);
        if (tags.isEmpty()){
            return CollectionModel.empty();
        }

        for (var tag : tags){
            Link selfLink = linkTo(methodOn(TagController.class).getById(tag.getId())).withSelfRel();
            tag.add(selfLink);
        }
        Link link = linkTo(methodOn(TagController.class).getAll(page, size)).withSelfRel();
        return CollectionModel.of(tags, link);
    }

    @SuppressWarnings("OptionalIsPresent")
    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getById(@PathVariable long id){
        Optional<Tag> optionalTag = service.getById(id);
        if (optionalTag.isPresent()){
            return new ResponseEntity<>(optionalTag.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> save(@RequestBody Tag tag){
        return new ResponseEntity<>(service.save(tag), HttpStatus.OK);
    }
}
