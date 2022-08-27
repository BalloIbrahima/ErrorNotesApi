package com.errornotes.ErrorNotesApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public Object create(@RequestBody Probleme probleme, @PathVariable("idUser") Long idUser,
            @PathVariable("idEtat") Long idEtat) {

        User user = userService.RecupererParId(idUser);
        Etat etat = etatService.retrouverParId(idEtat);
        System.out.println(user);
        if (user != null) {

            if (etat != null) {
                // etat.getProblemeList().add(probleme);
                probleme.setEtat(etat);
                probleme.setUser(user);
                return problemeService.createProbleme(probleme);
            } else {
                return "Vous ne pouvez pas créer un problème sans preciser son etat: 1 pour en cour; 2:pour resolu  et 3 pour ferme!!";
            }

        } else {
            return "Vous essayer de créer un probleme par un utilisateur qui n'existe pas!!";
        }

    }

    @ApiOperation(value = "Rechercher problème par mot clé")
    @GetMapping("/recherche/{mot}")
    public Object recherche(@PathVariable("mot") String mot) {
        if (problemeService.recherche(mot).size() == 0) {
            return "Aucun resultat trouvé!!";
        } else {
            return problemeService.recherche(mot);

        }
    }

}
