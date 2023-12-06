package com.techacademy.repository;

import com.techacademy.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository repository) {
        this.userRepository = repository;
    }

    @Test
    void testFindById() {
        User user = userRepository.findById(1).get();
        assertEquals(user.getId(), 1);
        assertEquals(user.getName(), "キラメキ太郎");
    }

}
