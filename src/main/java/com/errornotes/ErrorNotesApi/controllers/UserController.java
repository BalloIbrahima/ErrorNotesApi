package com.errornotes.ErrorNotesApi.controllers;

import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.RoleService;
import com.errornotes.ErrorNotesApi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "user", description = "Pour la gestion des users")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @ApiOperation(value = "Pour la création d'un user")
    @PostMapping("/create/{idRole}")
    public Object create(@RequestBody User user, @PathVariable("idRole") Long idRole) {
        User u = userService.getEmailUser(user.getEmail());
        Role role = roleService.getRoleParId(idRole);
        if (u == null) {
            if (role != null) {
                // role.getListUser().add(user);
                user.setRole(role);
                return userService.creerUser(user);
            } else {
                return "Vous essayez d'attribuez un role qui n'exsite pas!";
            }
        } else {
            return "Cet utilisateur existe déja!!";
        }

    }

    @ApiOperation(value = "Mettre à jour un user")
    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.modifierUser(id, user);
    }

    @ApiOperation(value = "Supression d'un utilisateur")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        return userService.supprimerUser(id);
    }

    @ApiOperation(value = "Recuperer la liste des utilisateurs simples")
    @GetMapping("/readusers")
    public List<User> read() {
        // recuperation du role USER
        Role role = roleService.getLibelleRole("USER");

        // recuperation des users avec ce role
        return userService.recupererParRole(role);
    }

}
