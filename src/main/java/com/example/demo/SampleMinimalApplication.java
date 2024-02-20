package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Function;

@SpringBootApplication
public class SampleMinimalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleMinimalApplication.class, args);
	}

	@Bean
	public Function<Message<?>, Message<?>> myFunction() {
		return msg -> msg;
	}

}
