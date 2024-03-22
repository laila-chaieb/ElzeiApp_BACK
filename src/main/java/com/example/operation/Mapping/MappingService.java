package com.example.operation.Mapping;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.operation.MVT.MVT;
import com.example.operation.Operation.Operation;
import com.example.operation.Operation.OperationRepo;
import com.example.operation.RawOperation.RawOperation;
import com.example.operation.cfonbmessage.MessageCfonb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class MappingService implements MappingInterface {

	@Autowired
    private MappingRepo MappingRepo;
	 @Autowired
	    private OperationRepo operationRepo;
	 @Autowired
	 private RawOperationRepo RawOperationRepo;
	 @Autowired
	 private CfonbRepo CfonbRepo;
       
	 @PersistenceContext
	    private EntityManager entityManager;

	
	@Override
	 @Transactional
	    public void copyMessageCfonbsToOperations() {
	        // Récupérer toutes les lignes de la table messageCfonb
	        List<MessageCfonb> messageCfonbs = CfonbRepo.findAll();

	        // Copier chaque messageCfonb vers la table operation
	        for (MessageCfonb messageCfonb : messageCfonbs) {
	        	
	        	  for (MVT mvt : messageCfonb.getMvt()) {
	                  Operation operation = new Operation();
	                  // Copier les attributs de MVT vers Operation
	                  operation.setDateVal(mvt.getDatevaleur());
	                  operation.setDateOP(mvt.getDateComptabilisation());
	                  operation.setMontant(mvt.getMontantMouvment());


	                  operation.setStatus("En Attente");
	                  // Enregistrer l'opération dans la table operation
	                  operationRepo.save(operation);
	              }
	            Operation operation = new Operation();
	            // Copier les attributs de messageCfonb vers operation
	           
	            operation.setDescription(messageCfonb.getDescription());
	           
	            operation.setStatus("En Attente");

	            // Enregistrer l'opération dans la table operation
	            operationRepo.save(operation);
	        }
	        
	    
	    }
	@Override
	@Transactional
	public void copyRawOperationsToOperations() {
	    // Récupérer toutes les lignes de la table raw_operation
	    List<RawOperation> rawOperations = RawOperationRepo.findAll();

	    // Parcourir chaque RawOperation
	    for (RawOperation rawOperation : rawOperations) {
	        // Vérifier si une opération similaire existe dans la table des opérations
	        boolean operationExists = operationRepo.existsByMontantAndTypeAndDescription(rawOperation.getMontant(),
	        		rawOperation.getType(),rawOperation.getLibelle());

	        // Si aucune opération similaire n'existe, copier l'opération dans la table des opérations
	        if (!operationExists) {
	            Operation operation = new Operation();
	            // Copier les attributs de rawOperation vers operation
	            operation.setDateOP(rawOperation.getDate());
	            operation.setDateVal(rawOperation.getDateValeur());
	            operation.setMontant(rawOperation.getMontant());
	            operation.setType(rawOperation.getType());
	            operation.setDescription(rawOperation.getLibelle());
	            operation.setStatus("En Attente");
	            operation.setTauxTVA(0);


	            // Enregistrer l'opération dans la table des opérations
	            operationRepo.save(operation);
	        }
	    }
	}



	   
	 }
   