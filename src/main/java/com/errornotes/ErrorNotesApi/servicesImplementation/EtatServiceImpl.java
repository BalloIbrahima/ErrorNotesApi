package com.errornotes.ErrorNotesApi.servicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Etat;
import com.errornotes.ErrorNotesApi.repository.EtatRepository;
import com.errornotes.ErrorNotesApi.services.EtatService;

@Service
public class EtatServiceImpl implements EtatService {

    @Autowired
    EtatRepository repos;

    @Override
    public Etat createEtat(Etat etat) {
        // TODO Auto-generated method stub
        return repos.save(etat);
    }

    @Override
    public Etat modificationEtat(Etat etat) {
        // TODO Auto-generated method stub
        return repos.save(etat);
    }

    @Override
    public void deleteEtat(Etat etat) {
        // TODO Auto-generated method stub
        repos.delete(etat);
    }

    @Override
    public List<Etat> getAllEtat() {
        // TODO Auto-generated method stub
        return repos.findAll();
    }

    @Override
    public Etat retrouverParLibelle(String libelle) {
        // TODO Auto-generated method stub
        return repos.findByLibelle(libelle);
    }

    @Override
    public Etat retrouverParId(Long id) {
        // TODO Auto-generated method stub
        try {
            return repos.findById(id).get();

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
