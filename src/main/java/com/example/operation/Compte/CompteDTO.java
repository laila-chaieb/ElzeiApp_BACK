package com.example.operation.Compte;

public class CompteDTO {
    private Long id;
    private String libele;
    private int code;
    private String description;
    private Long parentCompteID; 
	private Long classeId;
    
    // Getters et Setters
    public Long getParentCompteID() {
		return parentCompteID;
	}

	public void setParentCompteID(Long parentCompteID) {
		this.parentCompteID = parentCompteID;
	}



  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  


	public Long getClasseId() {
        return classeId;
    }

    public void setClasseId(Long classeId) {
        this.classeId = classeId;
    }

    public CompteDTO() {}
}
