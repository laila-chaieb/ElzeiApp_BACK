package com.example.operation.Mapping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.operation.Operation.Operation;
import com.example.operation.RawOperation.RawOperation;


	@Repository
	public interface MappingRepo extends JpaRepository<Operation, Long> {
	 
	}
	
