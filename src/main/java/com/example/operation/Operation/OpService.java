package com.example.operation.Operation;

import java.util.List;

import com.example.operation.Classe.Classe;


public interface OpService {
    List<Operation> findAll();
    List<Operation> findByStatus(String status);
    List<Operation> findByType(String type);
    Operation findById(Long id);
 	
}