package com.errornotes.ErrorNotesApi.services;

import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;

import java.util.List;

public interface UserService {
    // fonction creer user
    User creerUser(User user);

    // Fonction lister
    List<User> listerUser();

    // Fonction modifier
    User modifierUser(Long id, User user);

    // Fonction supprimer
    void supprimerUser(long id);

    User getEmailUser(String eamil);

    User Login(String email, String password);

    List<User> recupererParRole(Role role);

    User RecupererParId(Long id);

}
