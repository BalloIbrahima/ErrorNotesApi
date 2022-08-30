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
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;

    @JsonIgnore
    @ManyToOne
    User user;

    @JsonIgnore
    @ManyToOne
    Solution solution;

    @ManyToOne(cascade = CascadeType.ALL)
    private Commentaire commentaire;

    @JsonIgnore
    @OneToMany(mappedBy = "commentaire")
    List<Commentaire> sousCommentaire = new ArrayList<>();
}
