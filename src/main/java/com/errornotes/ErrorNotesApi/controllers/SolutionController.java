package com.errornotes.ErrorNotesApi.controllers;

import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.Solution;
import com.errornotes.ErrorNotesApi.services.ProblemeService;
import com.errornotes.ErrorNotesApi.services.SolutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    //La fonction de ceation
    @ApiOperation(value = "Pour la création d'un user")
    @PostMapping("/create/{idProbleme}")
    public Object create(@RequestBody Solution solution, @PathVariable("idProbleme") Long idProbleme) {

        Solution s = solutionService.retrouverParProbleme(solution.getProbleme());
        Probleme probleme = problemeService.retrouverParId(idProbleme);

        if (s == null) {
            if (probleme != null) {
                solution.setProbleme(probleme);
                return solutionService.createSolution(solution);
            } else {
                return "Vous essayez d'attribuez un probleme qui n'exsite pas!";
            }
        } else {
            return "Cette solution existe déja!!";
        }
    }


    //La fonction modification
    @ApiOperation(value = "Update Solution")
    @PutMapping("/update/{id}")
    public Solution update(@PathVariable Long id, @RequestBody Solution solution) {
        return solutionService.modificationSolution(id, solution);
    }

//a fonction de suprression
    @ApiOperation(value = "Supression d'une solution")
    @DeleteMapping("/delete/{id}")
    public void delete(@RequestBody Solution solution) {
        solutionService.deleteSolution(solution);
    }

    //La fonction pour lister
    @ApiOperation(value = "Lister les solutions")
    @GetMapping("/read")
    public List<Solution> read(){
        return solutionService.getAllSolution();
    }


}
