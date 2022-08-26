package com.errornotes.ErrorNotesApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String description;
    String ressources;
    String methodologie;
    String temps;

    @JsonIgnore
    @OneToMany(mappedBy = "solution")
    List<Commentaire> commentaireList = new ArrayList<>();

    @OneToOne
    Probleme probleme;
}
