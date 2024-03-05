package com.example.operation.Classe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClasseService implements ClassInterface {
    @Autowired
    private ClasseRepo classRepo;

    
    //service d'ajout
    @Override
    public Classe save(Classe classe) {
        classRepo.save(classe);
        return classe;
    }

    @Override
    public Classe findById(Long id) {
        if (classRepo.findById(id).isPresent()) {
            return classRepo.findById(id).get();
        }
        return null;
    }
    @Override
    public Classe getClassById(Long id) {
        return classRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Classe Classe = findById(id);
        classRepo.delete(Classe);
    }

    @Override
    public List<Classe> findAll() {
        return classRepo.findAll();
    }
    public Classe update(Long id, Classe updatedClasse) {
        Classe existingClasse = findById(id);
        if (existingClasse != null) {
            existingClasse.setDescription(updatedClasse.getDescription());
            existingClasse.setNom(updatedClasse.getNom());
            existingClasse.setNumcl(updatedClasse.getNumcl());
            return classRepo.save(existingClasse);
        }
        return null;
    }
}
