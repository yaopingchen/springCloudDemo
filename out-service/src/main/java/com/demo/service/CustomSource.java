package com.demo.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomSource {
	String MYOUTPUT="myTopic";
	@Output(MYOUTPUT)
	MessageChannel myOutput();
	
}
