package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {
}
