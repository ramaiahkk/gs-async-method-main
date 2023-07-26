package com.example.asyncmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ApiCall {

	private static final Logger logger = LoggerFactory.getLogger(ApiCall.class);

	private final RestTemplate restTemplate;

	public ApiCall(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	@Async
	public List<CompletableFuture<ResponseEntity<String>>> exucteRPS(String url, String payload, int rps) throws Exception{
		ArrayList <CompletableFuture> list = new ArrayList()
		for(int i=0;i<rps;i++){
			//update payload()
			list.add(executeTask(url,payload));
		}
		return list;
	}

	@Async
	public CompletableFuture<String> executeTask(String url, String payload) throws InterruptedException {
		// add header
		String response = restTemplate.postForObject(url, payload, String.class);
		return CompletableFuture.completedFuture(response);
	}

}
