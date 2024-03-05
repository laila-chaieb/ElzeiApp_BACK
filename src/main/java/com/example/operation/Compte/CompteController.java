package com.example.operation.Compte;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.operation.Classe.Classe;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/test")
public class CompteController {
	
	
	
		@Autowired
		CompteInterface CompteInterface;
	
	  	@ResponseBody
	    @GetMapping("/comptes")
	    public List<CompteResponseDTO> getAll() {
	        List<Compte> comptes = CompteInterface.findAll();
	        List<CompteResponseDTO> responseDTOs = new ArrayList<CompteResponseDTO>();
	        for (Compte compte : comptes) {
	            CompteResponseDTO responseDTO = CompteInterface.mapCompteToResponseDTO(compte);
	            responseDTOs.add(responseDTO);
	        }
	        return responseDTOs;
	    }
	  
	  

	  	@PostMapping("/comptes")
	    public ResponseEntity<CompteResponseDTO> save(@RequestBody Compte compte) {
	        // Vérifiez si une classe est associée au compte
	        if (compte.getClasse() != null) {
	            Long classeId = compte.getClasse().getId();
	            Classe classe = CompteInterface.getClassById(classeId);
	            compte.setClasse(classe); // Associez la classe au compte
	        }

	        Compte savedCompte = CompteInterface.save(compte);
	        CompteResponseDTO responseDTO = CompteInterface.mapCompteToResponseDTO(savedCompte);
	        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	    }

	    @ResponseBody
	    @GetMapping("/comptes/{id}")
	    public CompteResponseDTO get(@PathVariable("id") Long id) {
	        Compte compte = CompteInterface.findById(id);
	        return CompteInterface.mapCompteToResponseDTO(compte);
	    }
	    
	    
	    @ResponseBody
	    @DeleteMapping("/comptes/{id}")
	    public void delete(@PathVariable("id") Long id) {
		CompteInterface.delete(id);
	    }
	
	    @PutMapping("/comptes/{id}")
	    	public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Compte updatedCompte) {
	    	Compte existingCompte = CompteInterface.findById(id);
	    	if (existingCompte != null) {
	        // Mettre à jour les propriétés de la classe existante avec les nouvelles valeurs
	    	existingCompte.setDescription(updatedCompte.getDescription());
	    	existingCompte.setLibele(updatedCompte.getLibele());
	    	existingCompte.setCode(updatedCompte.getCode());
	    	

	        // Enregistrer le compte mise à jour dans la base de données
	    	CompteInterface.save(existingCompte);

	        return ResponseEntity.ok("Compte updated successfully");
	    	} else {
	        return ResponseEntity.notFound().build();
	    	}
	    	}
	
	
	 
	    	//afficher la liste des comptes classé par chaque classe
			@GetMapping("/byClasse/{classeId}")
		    public List<Compte> getComptesByClasse(@PathVariable Long classeId) {
		        return CompteInterface.getComptesByClasseId(classeId);
		    }
			
			
			//afficher la liste des comptes de chaque classe 
		    @GetMapping("/comptes/byClasse/{classeId}")
		    public ResponseEntity<List<CompteResponseDTO>> getComptesByClasseWithDTO(@PathVariable Long classeId) {
		        List<Compte> comptes = CompteInterface.getComptesByClasseId(classeId);
		        List<CompteResponseDTO> responseDTOs = new ArrayList<>();
		
		        for (Compte compte : comptes) {
		            CompteResponseDTO responseDTO = CompteInterface.mapCompteToResponseDTO(compte);
		            responseDTOs.add(responseDTO);
		        }
		
				        return ResponseEntity.ok(responseDTOs);
				    }
				}
