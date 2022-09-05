package com.errornotes.ErrorNotesApi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.UserService;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    void CreateTest() {

        User u1 = new User();
        u1.setEmail("ib@gmail.com");
        u1.setPassword("password");
        u1.setNom("test");

        userService.creerUser(u1);
    }

    @Test
    void ReadTest() {

        assertNotNull(userService.getEmailUser("ib@gmail.com"));
    }

    @Test
    void UpdateTest() {

        User u1 = userService.getEmailUser("ib@gmail.com");

        u1.setEmail("ibedit@gmail.com");
        u1.setPassword("password");
        u1.setNom("testEdit");

        userService.modifierUser(u1.getId(), u1);
    }

    @Test
    void DeleteTest() {
        User u1 = userService.getEmailUser("ibedit@gmail.com");

        userService.supprimerUser(u1.getId());
        assertNull(userService.getEmailUser(u1.getEmail()));
    }

    // @Test
    // void FindTest() {
    // List list = userService.listerUser();
    // assertNotNull(list.size());
    // }
}
