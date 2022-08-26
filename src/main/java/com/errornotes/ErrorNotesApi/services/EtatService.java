package com.errornotes.ErrorNotesApi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Etat;

@Service
public interface EtatService {

    // Creation d'un etat
    Etat createEtat(Etat etat);

    // Modification d'un etat
    Etat modificationEtat(Etat etat);

    // Supression d'un etat
    void deleteEtat(Etat etat);

    // L'ensemble des etats
    List<Etat> getAllEtat();

    // retrouver etat par libelle
    Etat retrouverParLibelle(String libelle);

    // retrouver etat par id
    Etat retrouverParId(Long id);
}
