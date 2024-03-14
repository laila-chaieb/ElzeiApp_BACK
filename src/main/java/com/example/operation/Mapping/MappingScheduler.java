package com.example.operation.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MappingScheduler {

    @Autowired
    private MappingService mappingService; // Service qui effectue le mapping des données

    @Scheduled(fixedRate = 200) 
    public String performMapping() {
        // Appel du service qui effectue le mapping
        mappingService.performMapping();
        System.out.println("Tâche planifiée exécutée !");
        return ("Classe updated successfully");
    }
}
