package edu.epam.esm.task.generating;

import edu.epam.esm.task.entity.User;
import edu.epam.esm.task.exception.ServiceException;
import edu.epam.esm.task.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class UserGenerating {
    @Autowired
    private UserService service;

    private final Random random = new Random();

    @Test
    public void generateThousandUsers() throws ServiceException {
        for (int i = 0; i < 1000; i++){
            User user = new User();
            user.setFirstName("first_name_" + i);
            user.setLastName("last_name_" + i);
            user.setEmail("email_" + i);
            user = service.save(user);
            for (int j = 0; j < 10; j++){
                int randomCertificateId = random.nextInt(10000);
                service.buyCertificate(user.getId(), randomCertificateId);
            }
        }
    }
}
