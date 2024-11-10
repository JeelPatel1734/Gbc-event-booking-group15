package ca.gbc.gbc_event_service;

import org.springframework.boot.SpringApplication;

public class TestGbcEventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(GbcEventServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
