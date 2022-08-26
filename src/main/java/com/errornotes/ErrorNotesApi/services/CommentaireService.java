package com.errornotes.ErrorNotesApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Commentaire;
import com.errornotes.ErrorNotesApi.models.Solution;
import com.errornotes.ErrorNotesApi.repository.CommentaireRepository;

@Service
public interface CommentaireService {

    // Creation d'un Commentaire
    Commentaire createCommentaire(Commentaire commentaire);

    // Modification d'un commentaire
    Commentaire modificationCommentaire(Commentaire commentaire);

    // Supression d'un commentaire
    void deleteCommentaire(Commentaire commentaire);

    // L'ensemble des etats
    List<Commentaire> getAllCommentaire();

    // retrouver etat par solution
    List<Commentaire> retrouverParSolution(Solution solution);

    // retrouver etat par Commentaire
    List<Commentaire> retrouverParCommentaire(Commentaire commentaire);

    // retrouver etat par id
    Commentaire retrouverParId(Long id);
}
