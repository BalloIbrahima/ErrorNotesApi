package com.errornotes.ErrorNotesApi.servicesImplementation;

import java.util.List;

import com.errornotes.ErrorNotesApi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.Solution;
import com.errornotes.ErrorNotesApi.repository.SolutionRepository;
import com.errornotes.ErrorNotesApi.services.SolutionService;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    SolutionRepository repos;

    @Override
    public Solution createSolution(Solution solution) {
        // TODO Auto-generated method stub
        return repos.save(solution);
    }

    @Override
    public Solution modificationSolution(Long id, Solution solution) {
        return repos.findById(id)
                .map(p -> {
                    p.setDescription(solution.getDescription());
                    p.setRessources(solution.getRessources());
                    p.setMethodologie(solution.getMethodologie());
                    p.setTemps(solution.getTemps());
                    return repos.save(p);
                }).orElseThrow(() -> new RuntimeException("Solution non trouvé !"));
    }

    @Override
    public void deleteSolution(Solution solution) {
        // TODO Auto-generated method stub
        repos.delete(solution);
    }

    @Override
    public List<Solution> getAllSolution() {
        // TODO Auto-generated method stub
        return repos.findAll();
    }

    @Override
    public Solution retrouverParProbleme(Probleme probleme) {
        // TODO Auto-generated method stub
        return repos.findByProbleme(probleme);
    }

    @Override
    public Solution retrouverParId(Long id) {
        // TODO Auto-generated method stub
        return repos.findById(id).get();
    }
}
