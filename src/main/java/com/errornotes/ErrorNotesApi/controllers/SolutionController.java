package com.errornotes.ErrorNotesApi.controllers;

import com.errornotes.ErrorNotesApi.configuration.ResponseMessage;
import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.Solution;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.services.ProblemeService;
import com.errornotes.ErrorNotesApi.services.RoleService;
import com.errornotes.ErrorNotesApi.services.SolutionService;
import com.errornotes.ErrorNotesApi.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Solution", description = "Pour la gestion d'un solution")
@RestController
@RequestMapping("/solution")
public class SolutionController {

    @Autowired
    SolutionService solutionService;

    @Autowired
    ProblemeService problemeService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    // La fonction de creation
    @ApiOperation(value = "Pour la création d'une solution")
    @PostMapping("/create/{idUser}/{idProbleme}")
    public ResponseEntity<Object> create(@RequestBody Solution solution, @PathVariable("idProbleme") Long idProbleme,
            @PathVariable("idUser") Long idUser) {

        User user = userService.RecupererParId(idUser);
        Solution s = solutionService.retrouverParProbleme(solution.getProbleme());
        Probleme probleme = problemeService.retrouverParId(idProbleme);
        Role admin = roleService.getLibelleRole("ADMIN");

        if (s == null) {
            if (probleme != null && user != null) {
                if (probleme.getUser() == user || user.getRole() == admin) {
                    solution.setProbleme(probleme);
                    return ResponseMessage.generateResponse("ok", HttpStatus.OK,
                            solutionService.createSolution(solution));
                } else {
                    return ResponseMessage.generateResponse("Erreur", HttpStatus.UNAUTHORIZED,
                            "Impossible de modifier un problème qui ne vous appartient pas !");
                }

            } else {
                return ResponseMessage.generateResponse("ok", HttpStatus.OK,
                        "Vous essayez d'attribuez un probleme ou un utilisateur qui n'exsite pas !");
            }
        } else {
            return ResponseMessage.generateResponse("Erreur", HttpStatus.UNAUTHORIZED,
                    "Cette solution existe déja !");
        }
    }

    // La fonction modification
    @ApiOperation(value = "Update Solution")
    @PutMapping("/update/{id}")
    public Solution update(@PathVariable Long id, @RequestBody Solution solution) {
        return solutionService.modificationSolution(id, solution);
    }

    // a fonction de suprression
    @ApiOperation(value = "Supression d'une solution")
    @DeleteMapping("/delete/{idAdmin}/{idSolutrion)")
    public ResponseEntity<Object> delete(@PathVariable(value = "idAdmin") Long idAdmin,
            @PathVariable(value = "idSolution") Long idSolution) {

        User user = userService.RecupererParId(idAdmin);
        Role admin = roleService.getLibelleRole("ADMIN");
        Solution solution = solutionService.retrouverParId(idSolution);

        if (user != null && solution != null) {

            if (user.getRole() == admin) {
                solutionService.deleteSolution(solution);

                return ResponseMessage.generateResponse("ok", HttpStatus.OK,
                        "Probleme suprimé !");
            } else {
                return ResponseMessage.generateResponse("Erreur", HttpStatus.UNAUTHORIZED,
                        "Vous n'avez pas cet droit !");
            }
        } else {

            return ResponseMessage.generateResponse("Erreur", HttpStatus.NOT_FOUND,
                    "Admin ou solution inexistant !");
        }

    }

    // La fonction pour lister
    @ApiOperation(value = "Lister les solutions")
    @GetMapping("/read")
    public List<Solution> read() {
        return solutionService.getAllSolution();
    }

}
