package com.example.operation.Mapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.operation.Operation.Operation;
import com.example.operation.Operation.OperationRepo;
import com.example.operation.RawOperation.RawOperation;
import com.example.operation.cfonbmessage.MessageCfonb;

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
       

	
	@Override
	 @Transactional
	    public void copyMessageCfonbsToOperations() {
	        // Récupérer toutes les lignes de la table raw_operation
	        List<MessageCfonb> messageCfonbs = CfonbRepo.findAll();

	        // Copier chaque RawOperation vers la table operation
	        for (MessageCfonb messageCfonb : messageCfonbs) {
	            Operation operation = new Operation();
	            // Copier les attributs de messageCfonb vers operation
	           
	            operation.setDescription(messageCfonb.getDescription());
	            operation.setMontant(25845);

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

	        // Copier chaque RawOperation vers la table operation
	        for (RawOperation rawOperation : rawOperations) {
	            Operation operation = new Operation();
	            // Copier les attributs de rawOperation vers operation
	            operation.setDateOP(rawOperation.getDate());
	            operation.setDateVal(rawOperation.getDateValeur());
	            operation.setMontant(rawOperation.getMontant());
	            operation.setType(rawOperation.getType());
	            operation.setDescription(rawOperation.getLibelle());
	            operation.setStatus("En Attente");

	            // Enregistrer l'opération dans la table operation
	            operationRepo.save(operation);
	        }
	    }
	
	
    }
   



