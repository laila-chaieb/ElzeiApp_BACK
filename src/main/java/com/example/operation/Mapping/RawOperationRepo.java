package com.example.operation.Mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.operation.RawOperation.RawOperation;


@Repository
public interface RawOperationRepo extends JpaRepository<RawOperation, Long> {
 
}
