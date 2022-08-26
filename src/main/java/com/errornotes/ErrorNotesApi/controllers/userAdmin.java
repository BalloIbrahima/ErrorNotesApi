package com.errornotes.ErrorNotesApi.controllers;

import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "hello", description = "Sample hello world application")
@RestController
@RequestMapping("/useradmin")
@AllArgsConstructor
public class userAdmin {

    @Autowired
    private final UserService userService;


    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/create/user")
    public User create(@RequestBody User user){
        return userService.creerUser(user);
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @GetMapping("/read/user")
    public List<User> read(){
        return userService.listerUser();
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PutMapping("/update/user/{id}")
    public User update(@PathVariable Long id, @RequestBody User user){
        return userService.modifierUser(id, user);
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @DeleteMapping("/delete/user/{id_pays}")
    public String delete(@PathVariable long id){
        return userService.supprimerUser(id);
    }

}
