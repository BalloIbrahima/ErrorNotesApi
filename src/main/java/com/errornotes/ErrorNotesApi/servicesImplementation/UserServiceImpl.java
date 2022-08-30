package com.errornotes.ErrorNotesApi.servicesImplementation;

import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.repository.UserRepository;
import com.errornotes.ErrorNotesApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User creerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> listerUser() {
        return userRepository.findAll();
    }

    @Override
    public User modifierUser(Long id, User user) {
        return userRepository.findById(id)
                .map(p -> {
                    p.setNom(user.getNom());
                    p.setPrenom(user.getPrenom());
                    p.setNumero(user.getNumero());
                    p.setEmail(user.getEmail());
                    p.setPassword(user.getPassword());
                    return userRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("User non trouvé !"));
    }

    @Override
    public void supprimerUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getEmailUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User Login(String email, String password) {
        // TODO Auto-generated method stub
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> recupererParRole(Role role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User RecupererParId(Long id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id).get();
    }
}
