package com.example.operation.Compte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.operation.Classe.Classe;
import com.example.operation.Classe.ClasseRepo;

import jakarta.transaction.Transactional;
@Service
public class CompteService implements CompteInterface {
	@Autowired
	 CompteRepo compRepo ;
	

	@Autowired
   private ClasseRepo classRepo;


	 @Transactional
	    public Compte getCompteById(long id) {
	        return compRepo.findById(id).orElse(null);
	    }

	 // Méthode pour récupérer la liste de comptes pour une classe donnée
	    public List<Compte> getComptesByClasseId(Long classeId) {
	        Classe classe = classRepo. findById(classeId).orElse(null);
	        if (classe != null) {
	            return compRepo.findComptesByClasse(classe);
	        }
	        return null;
	    }

		@Override
		@Transactional
		public Compte save(Compte compte) {
			compRepo.save(compte);
			return compte;
		}

		@Override
		public Compte findById(Long id) {
			if(compRepo.findById(id).isPresent()){
				return compRepo.findById(id).get();
			}
			return null;
		}
		@Override
	    public Classe getClassById(Long id) {

	        Optional<Classe> classeOptional = classRepo.findById(id);
	        if (classeOptional.isPresent()) {
	            return classeOptional.get();
	        } else {
	            return null;
	        }
	    }

		@Override
		public void delete(Long id) {
			Compte compte = findById(id);
			compRepo.delete(compte);
		}
		@Override
		@Transactional
	  public List<Compte> findAll(){

			return compRepo.findAll();
		}
		@Override
		@Transactional
		    public Compte update(Long id, Compte updatedCompte) {
		    	Compte existingCompte = findById(id);
		    	if (existingCompte != null) {
			        // Mettre à jour les propriétés de la classe existante avec les nouvelles valeurs
			    	existingCompte.setDescription(updatedCompte.getDescription());
			    	existingCompte.setLibele(updatedCompte.getLibele());
			    	existingCompte.setCode(updatedCompte.getCode());
			    	existingCompte.setClasse(updatedCompte.getClasse());

			        // Enregistrer la compte mise à jour dans la base de données
			    	return  compRepo.save(existingCompte);

		        }
		        return null;
		    }

		    @Transactional
		    public CompteDTO mapCompteToResponseDTO(Compte compte) {
		    	CompteDTO responseDTO = new CompteDTO();
		        responseDTO.setId(compte.getId());
		        responseDTO.setDescription(compte.getDescription());
		        responseDTO.setLibele(compte.getLibele());
		        responseDTO.setCode(compte.getCode());

		        // Inclure l'ID de la classe associée au compte
		        if (compte.getClasse() != null) {
		            responseDTO.setClasseId(compte.getClasse().getId());

		        }

		        return responseDTO;
		    }


		    @Override
		    @Transactional
		    public List<CompteDTO> findAllResponseDTO() {
		        List<Compte> comptes = compRepo.findAll();
		        List<CompteDTO> responseDTOs = new ArrayList<>();
		        for (Compte compte : comptes) {
		        	CompteDTO responseDTO = mapCompteToResponseDTO(compte);
		            responseDTOs.add(responseDTO);
		        }
		        return responseDTOs;
		    }

			@Override
			public Compte getCompteById(Long id) {
				if(compRepo.findById(id).isPresent()){
					return compRepo.findById(id).get();
				}
				return null;
			}

		
	}
