package com.example.operation.Justificatif;

import org.springframework.web.multipart.MultipartFile;

import com.example.operation.Operation.Operation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "justificatif")
public class Justificatif {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		 @Column(name = "Fichier")
		 private String fichier;

	    @Column(name = "description")
	    private String description;

	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "operation_id") // Nom de la colonne de clé étrangère dans la table "justificatif"
	   
	    private Operation operation;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Operation getOperation() {
			return operation;
		}

		public void setOperation(Operation operation) {
			this.operation = operation;
		}

	    
	    
}
