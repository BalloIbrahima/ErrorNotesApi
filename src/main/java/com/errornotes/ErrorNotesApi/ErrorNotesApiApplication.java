package com.errornotes.ErrorNotesApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.errornotes.ErrorNotesApi.models.Etat;
import com.errornotes.ErrorNotesApi.models.Role;
import com.errornotes.ErrorNotesApi.models.User;
import com.errornotes.ErrorNotesApi.repository.EtatRepository;
import com.errornotes.ErrorNotesApi.repository.RoleRepository;
import com.errornotes.ErrorNotesApi.services.UserService;

@SpringBootApplication
public class ErrorNotesApiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ErrorNotesApiApplication.class, args);

		RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
		EtatRepository etatRepository = ctx.getBean(EtatRepository.class);

		UserService userService = ctx.getBean(UserService.class);
		// Création des deux roles
		Role role1 = new Role();
		role1.setId((long) 1);
		role1.setLibelle("USER");
		Role role2 = new Role();
		role2.setId((long) 2);
		role2.setLibelle("ADMIN");

		roleRepository.save(role1);
		roleRepository.save(role2);

		// Création des trois etats
		Etat etat1 = new Etat();
		etat1.setId((long) 1);
		etat1.setLibelle("ENCOUR");
		Etat etat2 = new Etat();
		etat2.setId((long) 2);
		etat2.setLibelle("RESOLU");
		Etat etat3 = new Etat();
		etat3.setId((long) 3);
		etat3.setLibelle("FERME");

		etatRepository.save(etat1);
		etatRepository.save(etat2);
		etatRepository.save(etat3);

		// créattion d'un administrateur
		User ballo = new User();
		ballo.setEmail("ibrahimaballo01@gmail.com");
		ballo.setId((long) 1);
		ballo.setNom("BALLO");
		ballo.setPrenom("Ibrahima");
		ballo.setNumero((long) 77786028);
		ballo.setRole(role2);
		ballo.setPassword("ballo#123#");

		User mary = new User();
		mary.setEmail("mary@gmail.com");
		mary.setId((long) 2);
		mary.setNom("Traore");
		ballo.setPrenom("Mary");
		mary.setNumero((long) 77667766);
		mary.setRole(role2);
		mary.setPassword("mary#123#");

		userService.creerUser(ballo);
		userService.creerUser(mary);

	}

}
