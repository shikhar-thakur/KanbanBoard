package com.niit.stackroute;

import com.niit.stackroute.services.EmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class KanbanServiceApplication
{
	//private static final Logger logger = LoggerFactory.getLogger(KanbanServiceApplication.class);

	@Autowired
	private EmailService emailService;

	public static void main(String[] args)
	{
		SpringApplication.run(KanbanServiceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception
//	{
//		logger.info("Sending an email initiated");
//
//		emailService.sendTextEmail();
//	}
}
