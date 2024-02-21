package com.example.operation.Justificatif;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class JustificatifService {

    @Autowired
    private JustificatifRepo repository;

    private JustificatifInterface justificatifInterface = new JustificatifInterface();

    public String uploadImage(MultipartFile file, String description) {
    	 if (description == null) {
 	        // Si la description est null, vous pouvez choisir de la traiter d'une manière spécifique
 	        // Par exemple, assigner une valeur par défaut ou l'ignorer selon vos besoins.
 	        description = "Description par défaut";
 	    }
    	try {
        	  
            byte[] compressedImage = justificatifInterface.compressImage(file.getBytes());

            Justificatif justificatif = repository.save(Justificatif.builder()
                    .description(description)
                    .fichier(compressedImage)
                    .build());

            if (justificatif != null) {
                return "File uploaded successfully: " + file.getOriginalFilename();
            } else {
                return "Failed to upload file: " + file.getOriginalFilename();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Ajoutez un logging approprié
            return "Error uploading file: " + e.getMessage();
        }
    }

    public byte[] downloadImage(long id) {
        Optional<Justificatif> dbImageData = repository.findById(id);

        if (dbImageData.isPresent()) {
            return justificatifInterface.decompressImage(dbImageData.get().getFichier());
        } else {
            // Gérer le cas où l'ID n'existe pas
            return new byte[0];
        }
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
