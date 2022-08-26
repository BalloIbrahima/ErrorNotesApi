package com.errornotes.ErrorNotesApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    private Commentaire commentaire;

    @JsonIgnore
    @OneToMany(mappedBy = "commentaire")
    List<Commentaire> sousCommentaire = new ArrayList<>();
}
