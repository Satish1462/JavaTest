package com.test.TestAssignment1.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.test.TestAssignment1.Listeners.Publisher;
import com.test.TestAssignment1.Listeners.Subscriber;

@Configuration("asyncRPCConfig")
/*@Profile("async_rpc")*/
@EnableScheduling
@EnableRabbit
public class Config {
	
public static String requestQueue;
	
	public static String replyQueue;
	
	public static String directExchange;
	
	public static String requestRoutingKey;
	
	public static String replyRoutingKey;
	
	@Bean
	public Publisher publisher(){
		return new Publisher();
	}
	@Bean
	public Subscriber subscriber(){
		return new Subscriber();
	}
	 @Bean
	    public Executor taskExecutor() {
	        return Executors.newCachedThreadPool();
	    }

	    @Bean
	    public SimpleRabbitListenerContainerFactory simpleMessageListenerContainerFactory(ConnectionFactory connectionFactory,
	                                                                                      SimpleRabbitListenerContainerFactoryConfigurer configurer) {
	        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	        factory.setTaskExecutor(taskExecutor());
	        configurer.configure(factory, connectionFactory);
	        return factory;
	    }

	    @Bean
	    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	        RabbitTemplate template = new RabbitTemplate(connectionFactory);
	        template.setMessageConverter(jsonMessageConverter());
	        return template;
	    }

	    @Bean
	    public MessageConverter jsonMessageConverter() {
	        return new Jackson2JsonMessageConverter();
	    }

	    @Bean
	    public Queue replyQueueRPC() {
	        return new Queue("SamplemsgreplyQueue");
	    }

	    @Bean
	    public Queue requestQueueRPC() {
	        return new Queue("SamplemsgrequestQueue");
	    }

	    
	    
	    @Bean
	    public SimpleMessageListenerContainer rpcReplyMessageListenerContainer(ConnectionFactory connectionFactory) {
	        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
	        simpleMessageListenerContainer.setQueues(replyQueueRPC());
	        simpleMessageListenerContainer.setTaskExecutor(taskExecutor());
	        return simpleMessageListenerContainer;
	    }


	    @Bean
	    public AsyncRabbitTemplate asyncRabbitTemplate(ConnectionFactory connectionFactory) {

	        AsyncRabbitTemplate asyncRabbitTemplate = new AsyncRabbitTemplate(rabbitTemplate(connectionFactory),
	                        rpcReplyMessageListenerContainer(connectionFactory),
	                        "spring-boot-rabbitmq-examples.async_rpc_Msg" + "/" + "SamplemsgreplyQueue");
	        
	       /* AsyncRabbitTemplate at = new AsyncRabbitTemplate(connectionFactory, "spring-boot-rabbitmq-examples.async_rpc", "rpc_request", "replyQueueRPC","replyQueueRPC");*/
	        return asyncRabbitTemplate;
	    }

	    @Bean
	    public DirectExchange directExchange() {
	        return new DirectExchange("spring-boot-rabbitmq-examples.async_rpc_Msg");
	    }
	  /*Added new exchange*/
	    /*@Bean
	    public DirectExchange directExchange1() {
	        return new DirectExchange("Secondexchange.async_rpc");
	    }*/
	    @Bean
	    public List<Binding> bindings() {
	        return Arrays.asList(
	                        BindingBuilder.bind(requestQueueRPC()).to(directExchange()).with("SamplemsgrequestQueue"),
	                        BindingBuilder.bind(replyQueueRPC()).to(directExchange()).with("SamplemsgreplyQueue"));
	           
	                        
	    }

	

}
