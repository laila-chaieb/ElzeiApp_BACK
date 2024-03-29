package com.example.operation.Classe;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "localhost:4200")
@RestController
@RequestMapping("/api/v1/test")
public class ClasseController {
	@Autowired
	ClassInterface ClassService;
	
	//liste des classes
	@ResponseBody
	@GetMapping("/classes")
	  public List<Classe> getAll()  {
		return   ClassService.findAll();
	     }
	
	// Ajouter une classe
	@ResponseBody
	@PostMapping("/classes")
	public void save(@RequestBody Classe classe) {
	    ClassService.save(classe);
	}

		
	
	@ResponseBody
	@GetMapping("/classes/{id}")
	public Classe get(@PathVariable("id") Long id) {
		return ClassService.findById(id);

	}
	
	//Supprimer une classe
	@ResponseBody
	@DeleteMapping("/classes/{id}")
	public void delete(@PathVariable("id") Long id) {
		ClassService.delete(id);
	}
	
	
	//Modifier une classe
	 @ResponseBody
	    @PutMapping("/classes/{id}")
	    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Classe classeData) {
	        Classe existingClasse = ClassService.findById(id);
	        
	        if (existingClasse == null) {
	            return new ResponseEntity<>("Classe not found", HttpStatus.NOT_FOUND);
	        }
	        
	        existingClasse.setNom(classeData.getNom()); 
	        existingClasse.setDescription(classeData.getDescription()); 
	        existingClasse.setNumcl(classeData.getNumcl()); 

	        ClassService.save(existingClasse);

	        return new ResponseEntity<>("Classe updated successfully", HttpStatus.OK);
	    }
	

}
