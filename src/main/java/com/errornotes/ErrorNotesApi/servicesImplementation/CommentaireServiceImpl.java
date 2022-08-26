package com.errornotes.ErrorNotesApi.servicesImplementation;

import java.util.List;
import com.errornotes.ErrorNotesApi.services.CommentaireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Commentaire;
import com.errornotes.ErrorNotesApi.models.Solution;
import com.errornotes.ErrorNotesApi.repository.CommentaireRepository;
import com.errornotes.ErrorNotesApi.services.CommentaireService;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    CommentaireRepository repos;

    @Override
    public Commentaire createCommentaire(Commentaire commentaire) {
        // TODO Auto-generated method stub
        return repos.save(commentaire);
    }

    @Override
    public Commentaire modificationCommentaire(Commentaire commentaire) {
        // TODO Auto-generated method stub
        return repos.save(commentaire);
    }

    @Override
    public void deleteCommentaire(Commentaire commentaire) {
        // TODO Auto-generated method stub
        repos.delete(commentaire);
    }

    @Override
    public List<Commentaire> getAllCommentaire() {
        // TODO Auto-generated method stub
        return repos.findAll();
    }

    @Override
    public List<Commentaire> retrouverParSolution(Solution solution) {
        // TODO Auto-generated method stub
        return repos.findBySolution(solution);
    }

    @Override
    public List<Commentaire> retrouverParCommentaire(Commentaire commentaire) {
        // TODO Auto-generated method stub
        return repos.findByCommentaire(commentaire);
    }

    @Override
    public Commentaire retrouverParId(Long id) {
        // TODO Auto-generated method stub
        return repos.findById(id).get();
    }
}
