package com.domain.kafkastreams.consumer;

import java.util.function.Consumer;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainKafkaConsumer {

	
	@Bean
	  public Consumer<KStream<String, Domain>> domainService() {
	    return kstream -> kstream.foreach((key, domain) -> {
	      System.out.println(String.format("Domain consumed[%s] Status[%s]", domain.getDomain(), domain.isDead()));
	    });
	  }
}
