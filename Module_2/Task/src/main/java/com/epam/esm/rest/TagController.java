package com.epam.esm.rest;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService service;

    @GetMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> getAllTags(){
        try{
            List<Tag> tags = service.getAll();
            if (tags.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tags, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/tags/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> getTagById(@PathVariable("id") long id){
        try{
            Tag tag = service.getById(id);
            if (tag == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tag, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTag(@RequestBody Tag tag){
        try{
            service.save(tag);
            return new ResponseEntity<>("Tag was successfully added", HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAllTags(){
        try {
            service.deleteAll();
            return new ResponseEntity<>("All tags were deleted", HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/tags/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteTagById(@PathVariable("id") long id){
        try{
            service.delete(id);
            return new ResponseEntity<>("Tag was deleted", HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
