package com.errornotes.ErrorNotesApi.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
