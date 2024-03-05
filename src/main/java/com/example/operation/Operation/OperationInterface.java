package com.example.operation.Operation;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.operation.Classe.Classe;


public interface OperationInterface {
    List<Operation> findAll();
    List<Operation> findByStatus(String status);
    List<Operation> findByType(String type);
    Operation findById(Long id);
	Operation update(Long id, Operation updatedOperation);
	Operation save(Operation operation);


 	
}