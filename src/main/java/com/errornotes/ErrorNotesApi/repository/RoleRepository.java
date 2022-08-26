package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
