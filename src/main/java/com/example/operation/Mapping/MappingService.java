package com.example.operation.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.operation.Operation.Operation;
import com.example.operation.RawOperation.RawOperation;
import com.example.operation.cfonbmessage.MessageCfonb;

@Service
public class MappingService {


  
       

        public Operation mapFromCfonb(MessageCfonb messageCfonb) {
            Operation operation = new Operation();
            // Extraction des informations de messageCfonb et configuration de l'objet Operation
            // Par exemple :
            operation.setDescription(messageCfonb.getDescription());
            // Autres configurations...

            return operation;
        }

        public Operation mapFromRawOperation(RawOperation rawOperation) {
            Operation operation = new Operation();
            // Extraction des informations de rawOperation et configuration de l'objet Operation
            
            operation.setLibelle(rawOperation.getLibelle());
            operation.setDateVal(rawOperation.getDateValeur());
            operation.setDateOP(rawOperation.getDate());
            operation.setMontant(rawOperation.getMontant());
            operation.setType(rawOperation.getType());
            
            return operation;
        }
    }
   



