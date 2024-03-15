package com.example.operation.Mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.operation.cfonbmessage.MessageCfonb;


@Repository
public interface CfonbRepo extends JpaRepository<MessageCfonb, Long> {
 
}
