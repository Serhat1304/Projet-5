package com.safety.alerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tinylog.Logger;

@SpringBootApplication
public class AlertsApplication {

	public static void main(String[] args) {
		Logger.info("Lancement de l'application");
		SpringApplication.run(AlertsApplication.class, args);
	}

}
