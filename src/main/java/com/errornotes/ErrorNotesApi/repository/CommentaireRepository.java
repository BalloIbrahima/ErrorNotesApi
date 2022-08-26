package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
}
