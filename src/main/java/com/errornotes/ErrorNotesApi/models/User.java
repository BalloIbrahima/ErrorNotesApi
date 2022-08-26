package com.errornotes.ErrorNotesApi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String nom;
    String prenom;
    String numero;
    String email;
    String password;

    @ManyToOne
    Role role;
}
