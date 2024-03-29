package com.example.operation.Compte;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.operation.Classe.Classe;

@CrossOrigin(origins = "localhost:4200")
@RestController
@RequestMapping("/api/v1/test")
public class CompteController {
	
	
	@Autowired
	CompteInterface CompteInterface;

  	@ResponseBody
    @GetMapping("/comptes")
    public List<CompteDTO> getAll() {
        List<Compte> comptes = CompteInterface.findAll();
        List<CompteDTO> responseDTOs = new ArrayList<CompteDTO>();
        for (Compte compte : comptes) {
        	CompteDTO responseDTO = CompteInterface.mapCompteToResponseDTO(compte);
            responseDTOs.add(responseDTO);
        }
        return responseDTOs;
    }
  
  

  	@PostMapping("/comptes")
  	public ResponseEntity<CompteDTO> save(@RequestBody Compte compte) {
  	    

  	 

  	    // Vérifiez si parentCompte est null dans le JSON
  	    if (compte.getParentCompte() != null) {
  	        Long parentCompteId = compte.getParentCompte().getId();
  	        if (parentCompteId != null) {
  	            Compte parentCompte = CompteInterface.getCompteById(parentCompteId);
  	            // Log pour vérifier le compte parent récupéré
  	            System.out.println("Compte parent récupéré : " + parentCompte);
  	            if (parentCompte != null) {
  	                compte.setParentCompte(parentCompte);
  	            } else {
  	                // Gérer le cas où le compte parent n'existe pas
  	  	            System.out.println("Compte parent n'est pas  récupéré : " + parentCompte);

  	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  	            }
  	        } else {
	  	            System.out.println("Compte parent n'est pas  récupéré : " );

  	            // Gérer le cas où l'ID du compte parent est nul
  	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  	        }
  	    }
  	   // Vérifiez si une classe est associée au compte
  	    if (compte.getClasse() != null) {
  	        Long classeId = compte.getClasse().getId();
  	        if (classeId != null) {
  	            Classe classe = CompteInterface.getClassById(classeId);
  	            if (classe != null) {
  	                compte.setClasse(classe);
  	            } else {
  	                // Gérer le cas où la classe n'existe pas
  	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  	            }
  	        } else {
  	            // Gérer le cas où l'ID de la classe est nul
  	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  	        }
  	    }

  	    Compte savedCompte = CompteInterface.save(compte);
  	    CompteDTO responseDTO = CompteInterface.mapCompteToResponseDTO(savedCompte);
  	    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  	}





    @ResponseBody
    @GetMapping("/comptes/{id}")
    public CompteDTO get(@PathVariable("id") Long id) {
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
    //Pour les cards chaue classe a ces comptes
    @GetMapping("/byClasse/{classeId}")
    public List<CompteDTO> getComptesByClasse(@PathVariable Long classeId) {
        List<Compte> comptes = CompteInterface.getComptesByClasseId(classeId);
        List<CompteDTO> comptesDTO = new ArrayList<>();

        for (Compte compte : comptes) {
            comptesDTO.addAll(mapCompteAndChildrenToDTO(compte));
        }

        return comptesDTO;
    }

    private List<CompteDTO> mapCompteAndChildrenToDTO(Compte compte) {
        List<CompteDTO> comptesDTO = new ArrayList<>();
        CompteDTO compteDTO = mapCompteToDTO(compte);
        comptesDTO.add(compteDTO);

       

        return comptesDTO;
    }

    private CompteDTO mapCompteToDTO(Compte compte) {
        // Code de mapping d'un Compte vers un CompteDTO
        // Assurez-vous d'ajuster cela en fonction des champs que vous souhaitez inclure dans le DTO
        CompteDTO compteDTO = new CompteDTO();
        compteDTO.setId(compte.getId());
        compteDTO.setLibele(compte.getLibele());
        compteDTO.setCode(compte.getCode());
        compteDTO.setDescription(compte.getDescription());
        // Autres champs à mapper si nécessaire
        return compteDTO;
    }
		
		
		//afficher la liste des comptes de chaque classe 
	    @GetMapping("/comptes/byClasse/{classeId}")
	    public ResponseEntity<List<CompteDTO>> getComptesByClasseWithDTO(@PathVariable Long classeId) {
	        List<Compte> comptes = CompteInterface.getComptesByClasseId(classeId);
	        List<CompteDTO> responseDTOs = new ArrayList<>();
	
	        for (Compte compte : comptes) {
	        	CompteDTO responseDTO = CompteInterface.mapCompteToResponseDTO(compte);
	            responseDTOs.add(responseDTO);
	        }
	
			        return ResponseEntity.ok(responseDTOs);
			    }
	    
	    
	    
	 
			}
