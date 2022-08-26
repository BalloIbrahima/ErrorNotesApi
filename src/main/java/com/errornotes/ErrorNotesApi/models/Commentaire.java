package com.errornotes.ErrorNotesApi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String description;

    @ManyToOne
    User user;

    @ManyToOne
    Solution solution;
}
