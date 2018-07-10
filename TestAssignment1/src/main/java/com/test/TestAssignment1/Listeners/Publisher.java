package com.test.TestAssignment1.Listeners;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import com.test.TestAssignment1.Configuration.Config;
import com.test.TestAssignment1.Controller.ApplicationController;
import com.test.TestAssignment1.Pojo.SampleRequestMessage;
import com.test.TestAssignment1.Pojo.SampleResponseMessage;



@EnableRabbit
public class Publisher {
	
	private DirectExchange directExchange;
	
	private String requestRoutingKey;
	
	private final Logger app_logger =LogManager.getLogger(this.getClass());
	 @Autowired
	    private AsyncRabbitTemplate asyncRabbitTemplate;
	 
	 public  AsyncRabbitTemplate.RabbitConverterFuture<SampleResponseMessage> sampleResponseMessageRabbitConverterFuture;

	   /* @Scheduled(fixedDelay = 100 * 1)*/
	    public void publishToDirectExchangeRPCStyle(SampleRequestMessage msg) throws InterruptedException, ExecutionException {
	    	requestRoutingKey = Config.requestRoutingKey;
	        /*Integer integer = SECURE_RANDOM.nextInt();*/
	        /*SampleRequestMessage sampleRequestMessage = new SampleRequestMessage(msg);*/
	       /* System.out.println("Sending out message on direct directExchange:" + msg);*/
	        /*sampleRequestMessage.setScriptNumber("script1");
	        sampleRequestMessage.setStatusFlag("");*/
	    	app_logger.debug("Inside publisher");
	         sampleResponseMessageRabbitConverterFuture = asyncRabbitTemplate
	                        .convertSendAndReceive("spring-boot-rabbitmq-examples.async_rpc_Msg", "SamplemsgrequestQueue", msg);
			
	       
	        
	        
	        /*sampleResponseMessageRabbitConverterFuture.addCallback(
	                        sampleResponseMessage ->{
	                        	
	                                        System.out.println("Response for request message:" + sampleRequestMessage + " is:" + sampleResponseMessage);
	                                        try {
												mailClient.sendMail("Script Sucess",sampleResponseMessage.getMessage(), "");
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
	                        }
	                        , failure ->{
	                                        System.out.println(failure.getMessage()+"Failure section");
	                                        
	                                        try {
												mailClient.sendMail("Script Failure","1", "");
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
	                        }
	        );*/
	        
	        

	    }

	    /*@RabbitHandler
	    @RabbitListener(containerFactory = "simpleMessageListenerContainerFactory", queues = "SamplemsgreplyQueue")
	    public void subscribeToResponseQueue(@Payload SampleRequestMessage sampleRequestMessage, Message message)throws InterruptedException, IOException {
	    	
	    	System.out.println("I am inside replyQuee listening:");
	    	System.out.println("Final output is:" + sampleRequestMessage.getRequestMsg() );
	    	
	    }*/

}
