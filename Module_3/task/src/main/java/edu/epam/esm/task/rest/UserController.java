package edu.epam.esm.task.rest;

import edu.epam.esm.task.entity.Order;
import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.entity.User;
import edu.epam.esm.task.service.UserService;
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
public class UserController {

    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public CollectionModel<User> getAll(
        @RequestParam(value = "page", defaultValue = "0", required = false) int page,
        @RequestParam(value = "size", defaultValue = "5", required = false) int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = service.getAll(pageable);

        Link link = linkTo(methodOn(UserController.class).getAll(page, size)).withSelfRel();

        createLinksForUsers(users);
        return CollectionModel.of(users, link);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable long id){
        Optional<User> optionalUser = service.getById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            Link selfLink = createUserSelfLink(user);
            user.add(selfLink);

            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user){
        user = service.save(user);
        Link selfLink = createUserSelfLink(user);
        user.add(selfLink);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/buy-certificate/{certificateId}")
    public ResponseEntity<String> buyCertificate(
            @PathVariable long userId,
            @PathVariable long certificateId){
        boolean result = service.buyCertificate(userId, certificateId);

        if (result){
            return new ResponseEntity<>("User bought certificate", HttpStatus.OK);
        }

        return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/most-widely-used-tag")
    public ResponseEntity<Tag> getMostWidelyUsedTag(){
        Optional<Tag> optionalTag = service.getMostWidelyUsedTag();

        if (optionalTag.isPresent()){
            Tag tag = optionalTag.get();
            Link selfLink = linkTo(methodOn(TagController.class).getById(tag.getId())).withSelfRel();
            tag.add(selfLink);
            tag.getCertificates().forEach(a -> a.getTags().forEach(b -> b.setCertificates(null)));

            return new ResponseEntity<>(optionalTag.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void createLinksForUsers(Page<User> users){
        for (var user : users){
            Link selfLink = createUserSelfLink(user);
            user.add(selfLink);
        }
    }

    private void deleteOrderUserConnection(User user){
        Set<Order> orders = user.getOrders();

        orders.forEach(a -> a.setUser(null));
        orders.forEach(a -> a.getCertificate().getTags().forEach(b -> b.setCertificates(null)));
    }

    private Link createUserSelfLink(User user){
        deleteOrderUserConnection(user);
        return linkTo(methodOn(UserController.class).getById(user.getId())).withSelfRel();
    }
}
