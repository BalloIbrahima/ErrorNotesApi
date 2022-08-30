package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.Probleme;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemeRepository extends JpaRepository<Probleme, Long> {
    Probleme findByTitre(String titre);

    @Query(value = "SELECT * FROM probleme WHERE probleme.titre LIKE %?1% ", nativeQuery = true)
    List<Probleme> recherche(String mot);

    List<Probleme> findByTitreContaining(String mot);
}
