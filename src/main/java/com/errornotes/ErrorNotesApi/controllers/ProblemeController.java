package com.errornotes.ErrorNotesApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.errornotes.ErrorNotesApi.configuration.ResponseMessage;
import com.errornotes.ErrorNotesApi.models.Etat;
import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.EtatService;
import com.errornotes.ErrorNotesApi.services.ProblemeService;
import com.errornotes.ErrorNotesApi.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "probleme", description = "Pour la gestion d'un problème")
@RequestMapping("/probleme")
@Controller
public class ProblemeController {

    @Autowired
    ProblemeService problemeService;

    @Autowired
    UserService userService;

    @Autowired
    EtatService etatService;

    @ApiOperation(value = "Pour la création d'un problème")
    @PostMapping("/create/{idUser}/{idEtat}")
    public ResponseEntity<Object> create(@RequestBody Probleme probleme, @PathVariable(value = "idUser") Long idUser,
            @PathVariable(value = "idEtat") Long idEtat) {

        try {
            User user = userService.RecupererParId(idUser);
            Etat etat = etatService.retrouverParId(idEtat);

            // etat.getProblemeList().add(probleme);
            probleme.setEtat(etat);
            probleme.setUser(user);

            return ResponseMessage.generateResponse("Ok", HttpStatus.OK, problemeService.createProbleme(probleme));

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseMessage.generateResponse(e.getMessage(), HttpStatus.OK, null);

        }

    }

    @ApiOperation(value = "Rechercher problème par mot clé")
    @GetMapping("/recherche/{mot}")
    public ResponseEntity<Object> recherche(@PathVariable(value = "mot") String mot) {
        if (problemeService.recherche(mot).size() == 0) {
            return ResponseMessage.generateResponse("Aucun resultat trouvé", HttpStatus.NOT_FOUND, null);
        } else {
            return ResponseMessage.generateResponse("Ok", HttpStatus.OK,
                    problemeService.recherche(mot));
        }
    }

}
