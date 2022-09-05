package com.errornotes.ErrorNotesApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.errornotes.ErrorNotesApi.configuration.ResponseMessage;
import com.errornotes.ErrorNotesApi.models.Commentaire;
import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.Solution;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.CommentaireService;
import com.errornotes.ErrorNotesApi.services.ProblemeService;
import com.errornotes.ErrorNotesApi.services.RoleService;
import com.errornotes.ErrorNotesApi.services.SolutionService;
import com.errornotes.ErrorNotesApi.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "commentaire", description = "Pour la gestion des commentaires")
@RestController
@RequestMapping("/commentaire")
public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;

    @Autowired
    UserService userService;

    @Autowired
    ProblemeService problemeService;

    @Autowired
    SolutionService solutionService;

    @Autowired
    RoleService roleService;

    // La fonction de creation
    @ApiOperation(value = "Pour la création d'un commentaire")
    @PostMapping(value = "/add/{idUser}/{idSolution}")
    public ResponseEntity<Object> AddCommentaire(@PathVariable(value = "idUser") Long idUser,
            @PathVariable(value = "idSolution") Long idSolution, @RequestBody Commentaire commentaire) {
        // TODO: process POST request
        User user = userService.RecupererParId(idUser);
        Solution solution = solutionService.retrouverParId(idSolution);

        if (user != null && solution != null) {
            commentaire.setUser(user);
            commentaire.setSolution(solution);
            return ResponseMessage.generateResponse("Ok", HttpStatus.OK,
                    commentaireService.createCommentaire(commentaire));
        } else {
            return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND,
                    "Utilisateur ou solution inexistant !");
        }

    }

    // La fonction de modifier
    @ApiOperation(value = "Pour la modifier d'un commentaire")
    @PutMapping("/update/{idUser}/{idCommentaire}")
    public ResponseEntity<Object> update(@RequestBody Commentaire commentaire,
            @PathVariable(value = "idUser") Long idUser,
            @PathVariable(value = "idCommentaire") Long idCommentaire) {
        User user = userService.RecupererParId(idUser);
        Commentaire commentaireRecuperer = commentaireService.retrouverParId(idCommentaire);

        if (user != null && commentaireRecuperer != null) {
            return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND,
                    commentaireService.modificationCommentaire(commentaire, idCommentaire));
        } else {
            return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND,
                    "Utilisateur ou commentaire inexistant !");
        }

    }

    // La fonction de lister les commentaires
    @ApiOperation(value = "Pour lister les commentaires")
    @GetMapping("/getAll")
    public List<Commentaire> read() {
        return commentaireService.getAllCommentaire();
    }

    // Voir les commentaires associés à un problème
    @ApiOperation(value = "Pour lister les commentaires associer à un proble")
    @GetMapping("/get/{idProbleme}")
    public ResponseEntity<Object> getCommentaire(@PathVariable Long idProbleme) {
        Probleme p = problemeService.retrouverParId(idProbleme);
        if (p != null) {
            return ResponseMessage.generateResponse("Ok", HttpStatus.INTERNAL_SERVER_ERROR,
                    solutionService.retrouverParProbleme(p).getCommentaireList());

        } else {
            return ResponseMessage.generateResponse("Erreur", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    // La fonction de suppression
    @ApiOperation(value = "Pour supprimer les commentaires")
    @DeleteMapping("/delete/{idUser}/{idCommentaire}")
    public String delete(@PathVariable(value = "idUser") Long idUser,
            @PathVariable(value = "idCommentaire") Long idCommentaire) {
        User user = userService.RecupererParId(idUser);
        Commentaire commentaire = commentaireService.retrouverParId(idCommentaire);
        Role admin = roleService.getLibelleRole("ADMIN");

        if (user != null && commentaire != null) {

            if (user.getRole() == admin) {
                commentaireService.deleteCommentaire(commentaire);
                return "commentaire suprimé !";
            } else
                return "Vous n'avez pas ce droit !";

        } else {
            return "Supression impossible, utilisateur ou commentaire inexistant !";
        }

    }

}
