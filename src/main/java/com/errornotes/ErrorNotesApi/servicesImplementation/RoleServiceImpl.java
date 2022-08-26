package com.errornotes.ErrorNotesApi.servicesImplementation;

import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.repository.RoleRepository;
import com.errornotes.ErrorNotesApi.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role creerRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> listerRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role ModifierRole(Long id, User user) {
        return null;
    }

    @Override
    public String supprimerRole(Long id) {
        roleRepository.deleteById(id);
        return "User supprimé";
    }

    @Override
    public Role getLibelleRole(String libelle) {
        return roleRepository.findByLibelle(libelle);
    }
}
