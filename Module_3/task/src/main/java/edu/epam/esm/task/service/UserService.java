package edu.epam.esm.task.service;

import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> getAll(Pageable pageable);
    Optional<User> getById(long id);
    User save(User user);
    boolean buyCertificate(long userId, long certificateId);
    Optional<Tag> getMostWidelyUsedTag();
}
