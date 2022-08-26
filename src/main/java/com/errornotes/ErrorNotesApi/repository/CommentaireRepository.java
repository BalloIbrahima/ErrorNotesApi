package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.Commentaire;
import com.errornotes.ErrorNotesApi.models.Solution;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    List<Commentaire> findByCommentaire(Commentaire commentaire);

    List<Commentaire> findBySolution(Solution solution);
}
