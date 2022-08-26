package com.errornotes.ErrorNotesApi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Probleme {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String titre;
    String description;
    String technologies;

    @ManyToOne
    Etat etat;

    @ManyToOne
    User user;

}
