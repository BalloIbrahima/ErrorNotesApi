package com.errornotes.ErrorNotesApi.services;

import com.errornotes.ErrorNotesApi.models.User;

import java.util.List;

public interface UserService {
    //fonction creer user
    User creerUser (User user);
    //Fonction lister
    List<User> listerUser();
    //Fonction modifier
    User modifierUser(Long id, User user);
    //Fonction supprimer
    String supprimerUser(long id);

}
