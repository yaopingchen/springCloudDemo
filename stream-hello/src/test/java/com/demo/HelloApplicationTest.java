package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.biz.SinkSender;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class HelloApplicationTest {
	@Autowired
	private SinkSender.SinkOutput sinkSender;
	@Autowired @Qualifier("output-1")
	private MessageChannel input;
	@Test
	public void testSend(){
		sinkSender.outPut1().send(MessageBuilder.withPayload("from SinkSender").build());
	}
	@Test
	public void testSend2(){
		input.send(MessageBuilder.withPayload("from SinkSender").build());
	}
}
