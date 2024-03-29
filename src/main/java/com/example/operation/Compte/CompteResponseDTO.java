package com.example.operation.Compte;

public class CompteResponseDTO {
	 private long id;
	    private String libele;
	    private int code;
	    private String description;
	    private Long classe_id ; 
	    private Compte parentCompte;
		
		public Compte getParentCompte() {
			return parentCompte;
		}
		public void setParentCompte(Compte parentCompte) {
			this.parentCompte = parentCompte;
		}
		public Long getClasse_id() {
			return classe_id;
		}
		public void setClasse_id(Long classe_id) {
			this.classe_id = classe_id;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
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
	
	
		 public CompteResponseDTO()
			{}

}
