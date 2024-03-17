package com.commerce.backendserver.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {

	@Bean
	public LoggingExecuteListener loggingExecuteListener() {
		return new LoggingExecuteListener();
	}
}
