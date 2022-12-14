package com.errornotes.ErrorNotesApi.servicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.repository.ProblemeRepository;
import com.errornotes.ErrorNotesApi.services.ProblemeService;

@Service
public class ProblemeServiceImpl implements ProblemeService {

    @Autowired
    ProblemeRepository repos;

    @Override
    public Probleme createProbleme(Probleme probleme) {
        // TODO Auto-generated method stub
        return repos.save(probleme);
    }

    @Override
    public void deleteProbleme(Long idProbleme) {
        repos.deleteById(idProbleme);
    }

    @Override
    public Probleme modificationProbleme(Long idProbleme, Probleme probleme) {
        // TODO Auto-generated method stub
        return repos.findById(idProbleme)
                .map(p -> {
                    p.setTitre(probleme.getTitre());
                    p.setDescription(probleme.getDescription());
                    p.setTechnologies(probleme.getTechnologies());
                    return repos.save(p);
                }).orElseThrow(() -> new RuntimeException("Solution non trouvé !"));
    }

    /*
     * @Override
     * public void deleteProbleme(Probleme probleme) {
     * // TODO Auto-generated method stub
     * repos.delete(probleme);
     * }
     */
    @Override
    public List<Probleme> getAllProbleme() {
        // TODO Auto-generated method stub
        return repos.findAll();
    }

    @Override
    public Probleme retrouverParTitre(String titre) {
        // TODO Auto-generated method stub
        return repos.findByTitre(titre);
    }

    @Override
    public Probleme retrouverParId(Long id) {
        // TODO Auto-generated method stub
        try {
            return repos.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    @Override
    public List<Probleme> recherche(String mot) {
        // TODO Auto-generated method stub
        List<Probleme> list = repos.findByTitreContaining(mot);

        if (list != null) {
            return list;
        } else if (repos.findByDescriptionContaining(mot) != null) {
            return repos.findByDescriptionContaining(mot);
        } else {
            return repos.findByTechnologiesContaining(mot);
        }
    }
}
