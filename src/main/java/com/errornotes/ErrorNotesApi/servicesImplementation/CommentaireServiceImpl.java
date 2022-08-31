package com.errornotes.ErrorNotesApi.servicesImplementation;

import java.util.List;
import com.errornotes.ErrorNotesApi.services.CommentaireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.errornotes.ErrorNotesApi.models.Commentaire;
import com.errornotes.ErrorNotesApi.models.Solution;
import com.errornotes.ErrorNotesApi.repository.CommentaireRepository;

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
    public Object modificationCommentaire(Commentaire commentaire, Long id) {
        // TODO Auto-generated method stub
        return repos.findById(id)
                .map(p -> {
                    p.setDescription(commentaire.getDescription());
                    p.setSolution(commentaire.getSolution());
                    p.setUser(commentaire.getUser());
                    return repos.save(p);
                }).orElseThrow(() -> new RuntimeException("User non trouvé !"));
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
        try {
            return repos.findById(id).get();

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
