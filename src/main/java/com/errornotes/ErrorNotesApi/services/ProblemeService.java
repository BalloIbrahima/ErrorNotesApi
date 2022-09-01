package com.errornotes.ErrorNotesApi.services;

import java.util.List;

import com.errornotes.ErrorNotesApi.models.Commentaire;
import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Probleme;

@Service
public interface ProblemeService {

    // Creation d'un problème
    Probleme createProbleme(Probleme probleme);

    // Modification d'un probleme
    Probleme modificationProbleme(Long idProbleme, Probleme probleme);

    // Supression d'un probleme
    void deleteProbleme(Long idProbleme);

    // L'ensemble des problèmes
    List<Probleme> getAllProbleme();

    // retrouver probleme par titre
    Probleme retrouverParTitre(String titre);

    // retrouver probleme par id
    Probleme retrouverParId(Long id);

    // recherche par mots clé
    List<Probleme> recherche(String mot);
}
