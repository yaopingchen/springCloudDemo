package com.demo.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

//@EnableBinding(value={Sink.class,SinkSender.class})
@EnableBinding(Sink.class)
public class SinkReceiver {
	private static Logger log=LoggerFactory.getLogger(SinkReceiver.class);
	@StreamListener("output-1")
//	@ServiceActivator(inputChannel="output-1")
	public void receive(Object payload){
		log.info("payload:"+payload);
	}
//	@Transformer(inputChannel="output-1",outputChannel="output-1")
//	public User Transform(String message) throws JsonParseException, JsonMappingException, IOException{
//		ObjectMapper objectMapper=new ObjectMapper();
//		User user=objectMapper.readValue(new String(message.getBytes("UTF-8")), User.class);
//		return user;
//		
//	}
}
