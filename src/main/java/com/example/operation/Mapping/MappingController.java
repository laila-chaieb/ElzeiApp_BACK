package com.example.operation.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.operation.RawOperation.RawOperation;
import com.example.operation.cfonbmessage.MessageCfonb;


@CrossOrigin(origins = "localhost:4200")
@RestController
@RequestMapping("/api/v1/test/mapping")
public class MappingController {
	
	@Autowired
	MappingInterface MappingInterface;
	
	
	// Mapping from RawOperation
	@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@PostMapping("/rawOperation")
	public void mapFromRawOperation() {
	
		MappingInterface.copyRawOperationsToOperations();
	}

	
	// Mapping from RawOperation
	@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@PostMapping("/mapFromCfonb")
	public void mapFromCfonb() {
		MappingInterface.copyMessageCfonbsToOperations();
	}

}
