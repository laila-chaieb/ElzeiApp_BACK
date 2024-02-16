package com.example.operation.Classe;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
@Entity
@Table(name = "classes")

public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "description")
    private String description;

    @Column(name = "Nom")
    private String nom;


    @Column(name = "numcl")
    private String numcl;





    public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumcl() {
		return numcl;
	}

	public void setNumcl(String numcl) {
		this.numcl = numcl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@JsonCreator
	public Classe(@JsonProperty("id") Long id) {
	    this.id = id;
	}
public Classe() {}

}