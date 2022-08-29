package com.errornotes.ErrorNotesApi.controllers;

import com.errornotes.ErrorNotesApi.models.Commentaire;
import com.errornotes.ErrorNotesApi.models.Solution;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.CommentaireService;
import com.errornotes.ErrorNotesApi.services.SolutionService;
import com.errornotes.ErrorNotesApi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "commentaire", description = "Pour la gestion d'un commentaire")
@RestController
@RequestMapping("/commentaire")
public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;

    @Autowired
    UserService userService;

    @Autowired
    SolutionService solutionService;

    //La fonction de creation
    @ApiOperation(value = "Pour la création d'un commentaire")
    @PostMapping("/create/{idUser}/{idSolution}")
    public Object create(@RequestBody Commentaire commentaire, @PathVariable ("idUser") Long idUser, @PathVariable
            ("idSolution") Long idSolution){

        User user = userService.RecupererParId(idUser);
        Solution solution = solutionService.retrouverParId(idSolution);
        //Commentaire c = commentaireService.retrouverParCommentaire(commentaire.getCommentaire());

            if (user != null && solution != null){
                commentaire.setUser(user);
                commentaire.setSolution(solution);
                //commentaire.setCommentaire(commentaire);
                return commentaireService.createCommentaire(commentaire);
            }else{
                return "Le user ou le probleme, n'existe pas";
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

