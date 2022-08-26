package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Long, Role> {
}
