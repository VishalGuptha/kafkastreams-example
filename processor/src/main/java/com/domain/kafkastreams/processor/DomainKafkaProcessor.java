package com.domain.kafkastreams.processor;

import java.util.function.Function;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.domain.kafkastreams.processor.model.Domain;

@Configuration
public class DomainKafkaProcessor {

	
	@Bean
	  public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {

	    return kstream -> kstream.filter((key, domain) -> {
	      if (domain.isDead()) {
	        System.out.println("Inactive Domain: " + domain.getDomain());
	      } else {
	        System.out.println("Active Domain: " + domain.getDomain());
	      }
	      return !domain.isDead();
	    });

	  }
}
