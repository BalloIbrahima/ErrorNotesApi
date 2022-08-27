package com.errornotes.ErrorNotesApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonIgnore
    @ManyToOne
    Role role;
}
