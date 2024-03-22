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


    @Scheduled(cron = "0 0 7 * * ?") // Exécuter tous les jours à 7h00
    public void performMapping() {
        // Appel des méthodes de mapping
        mappingService.copyMessageCfonbsToOperations();
        mappingService.copyRawOperationsToOperations();
      
    }
}
