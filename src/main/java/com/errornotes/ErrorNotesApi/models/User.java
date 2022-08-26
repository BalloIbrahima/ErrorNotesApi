package com.errornotes.ErrorNotesApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String nom;
    String prenom;
    Long numero;
    String email;
    String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Probleme> listProbleme = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Commentaire> listCommentaire = new ArrayList<>();

    @ManyToOne
    Role role;
}
