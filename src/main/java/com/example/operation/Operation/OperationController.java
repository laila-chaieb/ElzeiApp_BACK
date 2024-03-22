package com.example.operation.Operation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.operation.Classe.Classe;



@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.PUT, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/v1/test")
public class OperationController {
    @Autowired
    OperationInterface opService;
    @Autowired
    OperationRepo operationRepo; // Ajoutez cette ligne pour injecter OperationRepo
    
    @ResponseBody
    @GetMapping("/operation")
    public List<Operation> getAll(@RequestParam(required = false) String status,
                                  @RequestParam(required = false) String type) {
       if (status != null) {
            // Filtrer  par le statut
            return opService.findByStatus(status);
        } else if (type != null) {
            // Filtrer par le type
            return opService.findByType(type);
        } else {
            // Aucun filtre, retourner toutes les opérations
            return opService.findAll();
        }
    }

    
  /*  
    @GetMapping("/operation/exist")
    public ResponseEntity<String> checkOperationExists(
     
            @RequestParam double montant,
            @RequestParam char type
   ) {
        
        // Utilisez la méthode existsByDateOPAndDateValAndMontantAndTypeAndDescription
        boolean operationExists = operationRepo.existsByMontantAndType( montant, type);

        if (operationExists) {
            return new ResponseEntity<>("L'opération existe", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("L'opération n'existe pas", HttpStatus.NOT_FOUND);
        }
    }
*/
    
    @ResponseBody
	@GetMapping("/operation/{id}")
	public Operation get(@PathVariable("id") Long id) {
		return opService.findById(id);

	}
    
    
    
    
    @ResponseBody
    @PutMapping("/operation/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Operation operationData) {
        Operation updatedOperation = opService.update(id, operationData);

        if (updatedOperation == null) {
            return new ResponseEntity<>("Operation not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Operation updated successfully", HttpStatus.OK);
    }
 
}
