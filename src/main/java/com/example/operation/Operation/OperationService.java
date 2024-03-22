package com.example.operation.Operation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.operation.Compte.Compte;
import com.example.operation.Compte.CompteRepo;
import com.jayway.jsonpath.Predicate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OperationService implements OperationInterface {
	
	   @Autowired
	    private OperationRepo operationRepo;
	    @Autowired
	    private CompteRepo compteRepository ;
	

 

    @Override
    public List<Operation> findAll() {
        return operationRepo.findAll();
    }

    @Override
    public List<Operation> findByStatus(String status) {
        return operationRepo.findByStatus(status);
    }

	@Override
	public List<Operation> findByType(String type) {
        return operationRepo.findByType(type);

	}

	@Override
	public Operation findById(Long id) {
		 if (operationRepo.findById(id).isPresent()) {
	            return operationRepo.findById(id).get();
	        }
	        return null;
	}
    private static final Logger logger = LoggerFactory.getLogger(OperationService.class);

    @Override
    public Operation update(Long id, Operation updatedOperation) {
        logger.debug("Début de la méthode update pour l'opération avec l'ID {}", id);
        
        Operation existingOperation = findById(id);
        if (existingOperation != null) {
            logger.debug("Opération trouvée dans la base de données avec l'ID {}", id);
            
            // Mise à jour des champs de l'opération
            if (updatedOperation.getDescription() != null) {
                existingOperation.setDescription(updatedOperation.getDescription());
                logger.debug("Description mise à jour avec succès : {}", updatedOperation.getDescription());
            }
            if (updatedOperation.getTauxTVA() != 0.0) {
                existingOperation.setTauxTVA(updatedOperation.getTauxTVA());
                logger.debug("Taux TVA mis à jour avec succès : {}", updatedOperation.getTauxTVA());
            }
            if (updatedOperation.getTVAdeductible() != null) {
                existingOperation.setTVAdeductible(updatedOperation.getTVAdeductible());
                logger.debug("TVA déductible mise à jour avec succès : {}", updatedOperation.getTVAdeductible());
            }
            
            // Mise à jour du compte de l'opération
            if (updatedOperation.getCompte() != null) {
                Long compteId = updatedOperation.getCompte().getId();
                Compte updatedCompte = compteRepository.findById(compteId).orElse(null);
                
                if (updatedCompte != null) {
                    // Si le compte existe, le mettre à jour pour l'opération
                    existingOperation.setCompte(updatedCompte);
                    existingOperation.setStatus("Validée");
                    logger.debug("Compte mis à jour avec succès : {}", updatedCompte);
                } else {
                    // Si le compte n'existe pas, gérer l'erreur
                    logger.error("Le compte avec l'ID {} n'a pas été trouvé dans la base de données.", compteId);
                    throw new IllegalArgumentException("Le compte avec l'ID " + compteId + " n'existe pas.");
                }
            }
            
            // Sauvegarder l'opération mise à jour dans la base de données
            return operationRepo.save(existingOperation);
        } else {
            logger.warn("Aucune opération trouvée avec l'ID {}", id);
            return null;
        }
    }


	@Override
	public Operation save(Operation operation) {
		operationRepo.save(operation);
        return operation;
	}

	
	
}




    

