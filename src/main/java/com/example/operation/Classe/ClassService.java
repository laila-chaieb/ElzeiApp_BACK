package com.example.operation.Classe;

import java.util.List;

public interface ClassService {
	 List<Classe> findAll();
		
	 Classe save(Classe classe);
	 	
	 Classe findById(Long id);
	 	
	 	void delete(Long id);

	    Classe update(Long id, Classe updatedClasse);
	    Classe getClassById(Long id); // Ajouter cette méthode pour récupérer une classe par son ID

}
