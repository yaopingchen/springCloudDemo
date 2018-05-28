package com.business.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomSink {
	String MYINPUT ="myTopic";
	@Input(MYINPUT)
    SubscribableChannel myInput();

}
