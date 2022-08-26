package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemeRepository extends JpaRepository<Long, Probleme> {
}
