package com.example.operation.Justificatif;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.operation.Operation.OperationInterface;
import com.example.operation.Operation.Operation;

@Service
public class JustificatifService {

    @Autowired
    private JustificatifRepo repository;
    @Autowired
    private OperationInterface operationService; 


    private JustificatifInterface justificatifInterface = new JustificatifInterface();

    public String uploadFile(MultipartFile file, String description, Long operationId) {
        if (description == null) {
            // Si la description est null, assigner une valeur par défaut
            
            description = "Description par défaut";
        	}
        try {
            // Récupérer l'opération associée
            Operation operation = operationService.findById(operationId);

            if (operation != null) {
                byte[] compressedImage = justificatifInterface.compressImage(file.getBytes());

                
                Justificatif justificatif = repository.save(Justificatif.builder()
                        .description(description)
                        .fichier(compressedImage)
                        .operation(operation) // Associer le justificatif à l'opération
                        .build());

			                return "File uploaded successfully: " + file.getOriginalFilename();
			            } else {
			                return "Operation not found with ID: " + operationId;
			            }
			        } catch (IOException e) {
			            e.printStackTrace(); 
			            return "Error uploading file: " + e.getMessage();
			        }
			    }
    
    		//downloadFile
		    public byte[] downloadFile(long id) {
		        Optional<Justificatif> dbImageData = repository.findById(id);
		
		        return dbImageData.map(justificatif -> justificatifInterface.decompressImage(justificatif.getFichier()))
		                          .orElse(new byte[0]);
		    }


		    public Justificatif saveJustificatif(Justificatif justificatif) {
		        return repository.save(justificatif);
		    }
		
		    public Optional<Justificatif> getJustificatifById(long id) {
		        return repository.findById(id);
		    }
		
		    public List<Justificatif> getAllJustificatifs() {
		        return repository.findAll();
		    }
		
		    public void deleteJustificatifById(long id) {
		        repository.deleteById(id);
		    }
		  
}
