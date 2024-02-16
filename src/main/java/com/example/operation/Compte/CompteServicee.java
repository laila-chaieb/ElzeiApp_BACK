package com.example.operation.Compte;

import jakarta.transaction.Transactional;
import java.util.List;

import com.example.operation.Classe.Classe;

public interface CompteServicee {

    @Transactional
    List<Compte> findAll();

    @Transactional
    Compte save(Compte compte);

    Compte findById(Long id);

    void delete(Long id);

    Compte update(Long id, Compte updatedCompte);

    CompteResponseDTO mapCompteToResponseDTO(Compte compte);

    List<CompteResponseDTO> findAllResponseDTO(); // Nouvelle méthode pour récupérer une liste de CompteResponseDTO
    List<Compte> getComptesByClasseId(Long classeId);
    Classe getClassById(Long id);
}