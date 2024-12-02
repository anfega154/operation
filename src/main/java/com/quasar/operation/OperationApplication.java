package com.quasar.operation;

import com.quasar.operation.aplication.usecase.DeterminemessageAndLocation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

import static com.quasar.operation.aplication.usecase.DeterminemessageAndLocation.execute;

@SpringBootApplication
public class OperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationApplication.class, args);

		double[] distances = {100.0, 115.5, 142.7};

		String[][] messages = {
				{"", "este", "es", "", "mensaje"},
				{"este", "", "un", "", ""},
				{"", "", "", "mensaje", "secreto"}
		};

		DeterminemessageAndLocation.DeterminationResult result = execute(distances, messages);

		Point location = result.getLocation();
		String message = result.getMessage();

		System.out.println("Location: " + location);
		System.out.println("Message: " + message);
	}
}
