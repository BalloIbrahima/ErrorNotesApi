package com.errornotes.ErrorNotesApi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.Solution;

@Service
public interface SolutionService {

    // Creation d'une solution
    Solution createSolution(Solution solution);

    // Modification d'une solution
    Solution modificationSolution(Solution solution);

    // Supression d'une solution
    void deleteSolution(Solution solution);

    // L'ensemble des solutions
    List<Solution> getAllSolution();

    // retrouver probleme par titre
    Solution retrouverParProbleme(Probleme probleme);

    // retrouver solution par id
    Solution retrouverParId(Long id);
}
