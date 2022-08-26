package com.errornotes.ErrorNotesApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Etat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String libelle;

    @JsonIgnore
    @OneToMany(mappedBy = "etat")
    List<Probleme> problemeList = new ArrayList<>();
}
