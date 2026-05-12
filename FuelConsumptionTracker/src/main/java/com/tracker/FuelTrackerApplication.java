package com.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

/**
 * Main entry point for the Fuel Consumption Tracker Spring Boot application.
 */
@SpringBootApplication
public class FuelTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuelTrackerApplication.class, args);
    }

    /**
     * Pre-populates the tracker with some demo data on startup.
     */
    @Bean
    public CommandLineRunner demoData(FuelTracker tracker) {
        return args -> {
            tracker.addVehicle(new Vehicle("V001", "Toyota Camry", "Car"));
            tracker.addVehicle(new Vehicle("V002", "Honda CB500", "Bike"));
            
            tracker.addFuelRecord("V001", 40.0, 480.0, "01-05-2026");
            tracker.addFuelRecord("V001", 35.5, 420.0, "15-05-2026");
            tracker.addFuelRecord("V002", 12.0, 300.0, "05-05-2026");
        };
    }
}
