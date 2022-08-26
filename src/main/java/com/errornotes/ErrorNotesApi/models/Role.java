package com.errornotes.ErrorNotesApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String libelle;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<User> listUser = new ArrayList<>();
}
