package com.errornotes.ErrorNotesApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.errornotes.ErrorNotesApi.configuration.ResponseMessage;
import com.errornotes.ErrorNotesApi.models.Etat;
import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.EtatService;
import com.errornotes.ErrorNotesApi.services.ProblemeService;
import com.errornotes.ErrorNotesApi.services.RoleService;
import com.errornotes.ErrorNotesApi.services.SolutionService;
import com.errornotes.ErrorNotesApi.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

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

    @Autowired
    SolutionService solutionService;

    @Autowired
    RoleService roleService;

    @ApiOperation(value = "Pour la création d'un problème")
    @PostMapping("/create/{idUser}/{idEtat}")
    public ResponseEntity<Object> create(@RequestBody Probleme probleme, @PathVariable(value = "idUser") Long idUser) {

        try {
            User user = userService.RecupererParId(idUser);
            Etat etat = etatService.retrouverParLibelle("ENCOUR");

            // creation de la solution dans la bse de donné
            // Solution solution = solutionService.createSolution(probleme.getSolution());
            // probleme.setSolution(solution);
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

    // Modification de problème
    @ApiOperation(value = "La modification d'un problème")
    @PutMapping("/update/{idUser}/{idProbleme}")
    public ResponseEntity<Object> update(@RequestBody Probleme probleme, @PathVariable("idUser") Long idUser,
            @PathVariable("idProbleme") Long idProbleme) {
        User user = userService.RecupererParId(idUser);
        Probleme p = problemeService.retrouverParId(idProbleme);
        Role admin = roleService.getLibelleRole("ADMIN");

        if (user != null) {
            if (p != null) {
                if (probleme.getUser() == user || user.getRole() == admin) {
                    problemeService.modificationProbleme(idProbleme, probleme);
                    return ResponseMessage.generateResponse("Ok", HttpStatus.OK,
                            "Mise à jour du problème avec succes!");
                } else {
                    return ResponseMessage.generateResponse("Erreur", HttpStatus.UNAUTHORIZED,
                            "Vous avez pas le droit de modifier le problème!");
                }
            } else {
                return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND,
                        "Le probleme n'existe pas!'");
            }
        } else {
            return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND,
                    "Cet user n'existe pas");
        }

    }

    @ApiOperation(value = "Pour la supression d'un problème")
    @DeleteMapping("/delete/{idAdmin}/{idProbleme}")
    public ResponseEntity<Object> delete(@PathVariable(value = "idAdmin") Long idAdmin,
            @PathVariable(value = "idProbleme") Long idProbleme) {

        try {
            User user = userService.RecupererParId(idAdmin);
            Probleme probleme = problemeService.retrouverParId(idProbleme);
            Role admin = roleService.getLibelleRole("ADMIN");

            if (user != null) {
                if (probleme != null) {
                    if (user.getRole() == admin) {
                        problemeService.deleteProbleme(idProbleme);
                        return ResponseMessage.generateResponse("Ok", HttpStatus.OK, "Problème suprimé!");
                    } else {
                        return ResponseMessage.generateResponse("Erreur", HttpStatus.UNAUTHORIZED,
                                "Vous avez pas cet droit!");
                    }
                } else {
                    return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND,
                            "Cet problème n'existe pas!");
                }
            } else {
                return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND,
                        "Cet Administarteur n'existe pas!");
            }
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseMessage.generateResponse(e.getMessage(), HttpStatus.OK, null);

        }

    }

    @ApiOperation(value = "Pour la voir les problèmes")
    @GetMapping("/read")
    public List<Probleme> getAllProbleme(@RequestBody Probleme probleme) {
        return problemeService.getAllProbleme();
    }

}
