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
    public Probleme modificationProbleme(Probleme probleme) {
        // TODO Auto-generated method stub
        return repos.save(probleme);
    }

    @Override
    public void deleteProbleme(Probleme probleme) {
        // TODO Auto-generated method stub
        repos.delete(probleme);
    }

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
        return repos.findById(id).get();
    }
}
