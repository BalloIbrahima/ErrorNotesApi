package com.errornotes.ErrorNotesApi.models;

import lombok.Data;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String libelle;

}
