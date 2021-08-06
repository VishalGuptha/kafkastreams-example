package com.domain.kafkastreams.producer.service;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.domain.kafkastreams.producer.model.Domain;
import com.domain.kafkastreams.producer.model.DomainList;

@Service
public class DomainCrawlerService {
	private KafkaTemplate<String, Domain> kafkaTemplate;
	  private final String KAFKA_TOPIC = "web-domains";

	  public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
	    this.kafkaTemplate = kafkaTemplate;
	  }

	  public void crawl(String name) {

		  RestTemplate restTemplate = new RestTemplate();
		  ResponseEntity<DomainList> domainList = restTemplate.exchange("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=com", HttpMethod.POST, null, DomainList.class);
		  DomainList list = domainList.getBody();

	    for(Domain domain : list.getDomains())
	    {
	    	kafkaTemplate.send(KAFKA_TOPIC, domain);
            System.out.println("Domain message" + domain.getDomain());
	    }

	  }
}
