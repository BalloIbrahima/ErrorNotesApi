package com.errornotes.ErrorNotesApi.repository;

import com.errornotes.ErrorNotesApi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
