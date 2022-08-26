package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.Probleme;
import com.errornotes.ErrorNotesApi.models.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    Solution findByProbleme(Probleme probleme);
}
