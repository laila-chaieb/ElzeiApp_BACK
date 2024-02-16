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
	CompteServicee CompteService;
	
	  @ResponseBody
	    @GetMapping("/comptes")
	    public List<CompteResponseDTO> getAll() {
	        List<Compte> comptes = CompteService.findAll();
	        List<CompteResponseDTO> responseDTOs = new ArrayList<CompteResponseDTO>();
	        for (Compte compte : comptes) {
	            CompteResponseDTO responseDTO = CompteService.mapCompteToResponseDTO(compte);
	            responseDTOs.add(responseDTO);
	        }
	        return responseDTOs;
	    }
	  
	  

	  @PostMapping("/comptes")
	    public ResponseEntity<CompteResponseDTO> save(@RequestBody Compte compte) {
	        // Vérifiez si une classe est associée au compte
	        if (compte.getClasse() != null) {
	            Long classeId = compte.getClasse().getId();
	            Classe classe = CompteService.getClassById(classeId);
	            compte.setClasse(classe); // Associez la classe au compte
	        }

	        Compte savedCompte = CompteService.save(compte);
	        CompteResponseDTO responseDTO = CompteService.mapCompteToResponseDTO(savedCompte);
	        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	    }

	    @ResponseBody
	    @GetMapping("/comptes/{id}")
	    public CompteResponseDTO get(@PathVariable("id") Long id) {
	        Compte compte = CompteService.findById(id);
	        return CompteService.mapCompteToResponseDTO(compte);
	    }
	@ResponseBody
	@DeleteMapping("/comptes/{id}")
	public void delete(@PathVariable("id") Long id) {
		CompteService.delete(id);
	}
	
	@PutMapping("/comptes/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Compte updatedCompte) {
		Compte existingCompte = CompteService.findById(id);
	    if (existingCompte != null) {
	        // Mettre à jour les propriétés de la classe existante avec les nouvelles valeurs
	    	existingCompte.setDescription(updatedCompte.getDescription());
	    	existingCompte.setLibele(updatedCompte.getLibele());
	    	existingCompte.setCode(updatedCompte.getCode());
	    	

	        // Enregistrer la compte mise à jour dans la base de données
	        CompteService.save(existingCompte);

	        return ResponseEntity.ok("Compte updated successfully");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	 
	
	@GetMapping("/byClasse/{classeId}")
    public List<Compte> getComptesByClasse(@PathVariable Long classeId) {
        return CompteService.getComptesByClasseId(classeId);
    }

    @GetMapping("/comptes/byClasse/{classeId}")
    public ResponseEntity<List<CompteResponseDTO>> getComptesByClasseWithDTO(@PathVariable Long classeId) {
        List<Compte> comptes = CompteService.getComptesByClasseId(classeId);
        List<CompteResponseDTO> responseDTOs = new ArrayList<>();

        for (Compte compte : comptes) {
            CompteResponseDTO responseDTO = CompteService.mapCompteToResponseDTO(compte);
            responseDTOs.add(responseDTO);
        }

        return ResponseEntity.ok(responseDTOs);
    }
}
