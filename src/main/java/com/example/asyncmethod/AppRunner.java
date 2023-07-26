package com.example.asyncmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final ApiCall gitHubLookupService;

	public AppRunner(ApiCall gitHubLookupService) {
		this.gitHubLookupService = gitHubLookupService;
	}


	private List<ResponseEntity<String>> responseCollection = new ArrayList<>();
	@Scheduled(fixedDelay = 1sec )
	public void scheduleRPS(){
		List<ResponseEntity<String>> requests= 		gitHubLookupService.executeTask("https://bevan1p0d8.execute-api.us-east-1.amazonaws.com/Live"," { \"name\": \"YOUR_NAME\", \"date\": \"1/1/2022 12:00:00 AM\", \"requests_sent\": 123 }",1000);
		CompletableFuture.allOf(requests).join();
))
	}
	@Override
	public void run(String... args) throws Exception {
		// Start the clock
		long start = System.currentTimeMillis();

		// Kick of multiple, asynchronous lookups
		CompletableFuture<String> page1 = gitHubLookupService.executeTask("https://bevan1p0d8.execute-api.us-east-1.amazonaws.com/Live"," { \"name\": \"YOUR_NAME\", \"date\": \"1/1/2022 12:00:00 AM\", \"requests_sent\": 123 }");
		CompletableFuture<String> page2 = gitHubLookupService.executeTask("https://bevan1p0d8.execute-api.us-east-1.amazonaws.com/Live"," { \"name\": \"YOUR_NAME\", \"date\": \"1/1/2022 12:00:00 AM\", \"requests_sent\": 123 }");


		// Wait until they are all done
		CompletableFuture.allOf(page1,page2).join();

		// Print results, including elapsed time
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
		logger.info("--> " + page1.get());
		logger.info("--> " + page2.get());

	}

}
