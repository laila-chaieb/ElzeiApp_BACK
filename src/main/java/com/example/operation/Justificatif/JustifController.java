package com.example.operation.Justificatif;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/test/justificatif")
@CrossOrigin(origins = "http://localhost:4200")

public class JustifController {

    @Autowired
    private JustificatifService justificatifService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadJustificatif(@RequestParam("file") MultipartFile file,
                                                    @RequestParam(required = false) String description) throws IOException {
        // Traitez le fichier individuellement (enregistrement en base de données, compression, etc.)
        String response = justificatifService.uploadImage(file, description);
        // Vous pouvez logguer la réponse ou la stocker pour un traitement ultérieur

        return ResponseEntity.ok("{\"message\": \"File uploaded successfully\"}");
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadJustificatif(@PathVariable long id) {
        try {
            byte[] image = justificatifService.downloadImage(id);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new byte[0]);
        }
    }

    // Ajoutez d'autres méthodes CRUD au besoin

    // Exemple de méthode CRUD
    @GetMapping("/get/{id}")
    public ResponseEntity<Justificatif> getJustificatif(@PathVariable long id) {
        try {
            Optional<Justificatif> justificatif = justificatifService.getJustificatifById(id);
            return justificatif.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Exemple de méthode CRUD
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJustificatif(@PathVariable long id) {
        try {
            justificatifService.deleteJustificatifById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
