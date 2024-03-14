package com.example.operation.Mapping;

import com.example.operation.Operation.Operation;
import com.example.operation.RawOperation.RawOperation;
import com.example.operation.cfonbmessage.MessageCfonb;

public interface MappingInterface {
	void mapFromCfonb(MessageCfonb messageCfonb);
	void mapFromRawOperation(RawOperation rawOperation);

}
