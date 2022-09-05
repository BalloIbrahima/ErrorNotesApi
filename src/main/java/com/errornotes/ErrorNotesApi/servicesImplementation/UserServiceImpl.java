package com.errornotes.ErrorNotesApi.servicesImplementation;

import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.repository.UserRepository;
import com.errornotes.ErrorNotesApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public User creerUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
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
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (passwordEncoder().matches(password, user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
        // TODO Auto-generated method stub

    }

    @Override
    public List<User> recupererParRole(Role role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User RecupererParId(Long id) {
        // TODO Auto-generated method stub
        try {
            return userRepository.findById(id).get();

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
