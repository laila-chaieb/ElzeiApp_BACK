package com.example.operation.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.operation.RawOperation.RawOperation;
import com.example.operation.cfonbmessage.MessageCfonb;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/test")
public class MappingController {
	
	@Autowired
	MappingInterface MappingInterface;
	
	
	// Mapping from RawOperation
	@ResponseBody
	@PostMapping("/rawOperation")
	public void mapFromRawOperation(@RequestBody RawOperation rawOperation) {
		MappingInterface.mapFromRawOperation(rawOperation);
	}

	
	// Mapping from RawOperation
	@ResponseBody
	@PostMapping("/mapFromCfonb")
	public void mapFromCfonb(@RequestBody MessageCfonb messageCfonb) {
		MappingInterface.mapFromCfonb(messageCfonb);
	}

}
