package com.errornotes.ErrorNotesApi.controllers;

import com.errornotes.ErrorNotesApi.configuration.ResponseMessage;
import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.RoleService;
import com.errornotes.ErrorNotesApi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @DeleteMapping("/delete/{idAdmin}/{idUser}")
    public ResponseEntity<Object> delete(@PathVariable(value = "idAdmin") long idAdmin,
            @PathVariable(value = "idUser") long idUser) {
        User Admin = userService.RecupererParId(idAdmin);
        User SimpleUser = userService.RecupererParId(idUser);

        if (Admin != null) {
            if (SimpleUser != null) {
                userService.supprimerUser(idUser);
                return ResponseMessage.generateResponse("ok", HttpStatus.OK, "Utilisateur suprimé!");
            } else {
                return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas");
            }

        } else {
            return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND, "Cet administrateur n'existe pas");
        }
    }

    @ApiOperation(value = "Recuperer la liste des utilisateurs simples")
    @GetMapping("/readusers/{idAdmin}")
    public ResponseEntity<Object> read(@PathVariable(value = "idAdmin") Long id) {
        User user = userService.RecupererParId(id);
        if (user != null) {
            if (user.getRole() == roleService.getLibelleRole("ADMIN")) {
                // recuperation du role USER
                Role role = roleService.getLibelleRole("USER");

                // recuperation des users avec ce role
                return ResponseMessage.generateResponse("ok", HttpStatus.OK, userService.recupererParRole(role));

            } else {
                return ResponseMessage.generateResponse("Vous n'etes pas autorisé a effectuer cette action!",
                        HttpStatus.UNAUTHORIZED, "Non autorisé");

            }

        } else {
            return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas!");

        }

    }

    @ApiOperation(value = "Connexion d'un utilisateur ou d'un admin")
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<Object> read(@PathVariable(value = "email") String email,
            @PathVariable(value = "password") String password) {

        User user = userService.Login(email, password);

        if (user != null) {
            return ResponseMessage.generateResponse("Ok", HttpStatus.OK, user);
        } else {
            return ResponseMessage.generateResponse("Email ou Mot de passe incorrect !", HttpStatus.NOT_FOUND, null);
        }
    }

}
