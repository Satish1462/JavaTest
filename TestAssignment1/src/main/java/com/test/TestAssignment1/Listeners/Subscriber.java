package com.test.TestAssignment1.Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import com.test.TestAssignment1.Pojo.SampleRequestMessage;
import com.test.TestAssignment1.Pojo.SampleResponseMessage;



@EnableRabbit
public class Subscriber {

	@Autowired
    private AsyncRabbitTemplate asyncRabbitTemplate;
	
	private final Logger app_logger =LogManager.getLogger(this.getClass());
    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainerFactory", queues ="SamplemsgrequestQueue")
    public SampleRequestMessage subscribeToRequestQueue(@Payload SampleRequestMessage sampleRequestMessage, Message message) throws InterruptedException {
    	
    	System.out.println("Received message :" + message);
    	app_logger.debug("Received message :" + message);
    	/*The below just added Rabbit before the msg received from requestQueue 
    	 * we can call Python scripts if you require by using java processbuilder as well its asynchronous and next msg will be in queue until it turn come
    	 * here just added "Rabit" before the message i received from request queue */
    	sampleRequestMessage.setRequestMsg("Rabbit" +sampleRequestMessage.getRequestMsg() );
    	System.out.println("Response message in Subscriber is:" + sampleRequestMessage.getRequestMsg());
    	app_logger.debug("Response message in Subscriber is:" + sampleRequestMessage.getRequestMsg());
    	/*asyncRabbitTemplate
        .convertSendAndReceive("spring-boot-rabbitmq-examples.async_rpc_Msg", "SamplemsgreplyQueue", sampleRequestMessage);*/
    	app_logger.debug("Sleeping the thread for 7 secs intentionally");
    	Thread.sleep(7000);
		return sampleRequestMessage;
    	
    }
	
}
