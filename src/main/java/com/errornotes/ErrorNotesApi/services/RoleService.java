package com.errornotes.ErrorNotesApi.services;

import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;

import java.util.List;

public interface RoleService {

    // fonction creation role
    Role creerRole(Role role);

    // fonction de lister
    List<Role> listerRole();

    // fonction de modification de role
    Role ModifierRole(Long id, User user);

    Role ModifierRole(Long id, Role role);

    // fonction de suppression de role
    String supprimerRole(Long id);

    // fonction getLibelle
    Role getLibelleRole(String libelle);

    //
    Role getRoleParId(Long id);

}
