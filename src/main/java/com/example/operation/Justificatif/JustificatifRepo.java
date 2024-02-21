package com.example.operation.Justificatif;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JustificatifRepo extends JpaRepository<Justificatif,Long> {


	Optional<Justificatif> findByDescription(String description);
	}

