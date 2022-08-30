package com.errornotes.ErrorNotesApi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.errornotes.ErrorNotesApi.configuration.ResponseMessage;
import com.errornotes.ErrorNotesApi.models.Commentaire;
import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.CommentaireService;
import com.errornotes.ErrorNotesApi.services.ProblemeService;
import com.errornotes.ErrorNotesApi.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    //La fonction de creation
    @ApiOperation(value = "Pour la création d'un commentaire")
    @PostMapping(value = "add/{idUser}/{idProblème}")
    public ResponseEntity<Object> AddCommentaire(@RequestBody Commentaire commentaire,
            @PathVariable(value = "idUser") Long idUser, @PathVariable(value = "idProbleme") Long idProbleme) {
        // TODO: process POST request
        User user = userService.RecupererParId(idUser);
        Probleme probleme = problemeService.retrouverParId(idProbleme);

        if (user != null) {
            commentaire.setUser(user);
            return ResponseMessage.generateResponse("Ok", HttpStatus.OK,
                    commentaireService.createCommentaire(commentaire));
        } else {
            return ResponseMessage.generateResponse("Ok", HttpStatus.NOT_FOUND,
                    "Utilisateur ou problème inexistant !");
        }

    }    
    
    //La fonction de modifier
    @ApiOperation(value = "Pour la modifier d'un commentaire")
    @PutMapping("/update/{idUser}/{idProbleme}/{idCommentaire}")
    public Object update(Commentaire commentaire) {
        return commentaireService.modificationCommentaire(commentaire);
    }

    //La fonction de lister les commentaires
    @ApiOperation(value = "Pour lister les commentaires")
    public List<Commentaire> read(){
        return commentaireService.getAllCommentaire();
    }

    //La fonction de suppression
    @ApiOperation(value = "Pour supprimer les commentaires")
    @DeleteMapping("/delete/{id}")
    public void delete(Commentaire commentaire){
        commentaireService.deleteCommentaire(commentaire);
    }
    
}
