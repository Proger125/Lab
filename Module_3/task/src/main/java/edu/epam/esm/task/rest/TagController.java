package edu.epam.esm.task.rest;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.exception.ServiceException;
import edu.epam.esm.task.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping(value = "/tags")
    public CollectionModel<Tag> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size){
        Pageable pageable = PageRequest.of(page, size);
        try {
            Page<Tag> tags = service.getAll(pageable);

            Link link = linkTo(methodOn(TagController.class).getAll(page, size)).withSelfRel();

            if (tags.isEmpty()) {
                return CollectionModel.empty(link);
            }

            for (var tag : tags) {
                Link selfLink = linkTo(methodOn(TagController.class).getById(tag.getId())).withSelfRel();
                tag.add(selfLink);
                tag.setCertificates(null);
            }

            return CollectionModel.of(tags, link);
        } catch (ServiceException e){
            return CollectionModel.empty();
        }
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getById(@PathVariable long id){
        try {
            Optional<Tag> optionalTag = service.getById(id);
            if (optionalTag.isPresent()) {
                Tag tag = optionalTag.get();
                Link selfLink = linkTo(methodOn(TagController.class).getById(id)).withSelfRel();
                tag.add(selfLink);
                tag.setCertificates(null);
                return new ResponseEntity<>(tag, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> save(@RequestBody Tag tag){
        try {
            tag = service.save(tag);
            Link selfLink = linkTo(methodOn(TagController.class).save(tag)).withSelfRel();
            tag.add(selfLink);
            tag.setCertificates(null);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        try {
            service.deleteById(id);
            return new ResponseEntity<>("Tag was deleted", HttpStatus.OK);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tags")
    public ResponseEntity<String> deleteAll(){
        try {
            service.deleteAll();
            return new ResponseEntity<>("All tags were deleted", HttpStatus.OK);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
