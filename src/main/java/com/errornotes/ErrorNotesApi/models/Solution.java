package com.errornotes.ErrorNotesApi.models;

import lombok.Data;

import javax.persistence.*;

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

    @OneToOne
    Probleme probleme;
}
