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
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    String ressources;
    String methodologie;
    String temps;

    @JsonIgnore
    @OneToMany(mappedBy = "solution")
    List<Commentaire> commentaireList = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    Probleme probleme;
}
