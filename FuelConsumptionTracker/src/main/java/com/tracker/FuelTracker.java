package com.tracker;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service to manage vehicles and their fuel records using optimized data structures.
 */
@Service
public class FuelTracker {
    private final Map<String, Vehicle> vehicles = new HashMap<>();
    private final Map<String, List<FuelRecord>> recordsByVehicle = new HashMap<>();

    /**
     * Adds a vehicle to the tracker.
     *
     * @param v The vehicle to add.
     */
    public void addVehicle(Vehicle v) {
        vehicles.put(v.getVehicleId(), v);
        recordsByVehicle.putIfAbsent(v.getVehicleId(), new ArrayList<>());
    }

    /**
     * Adds a fuel record to the tracker. Validates vehicle existence.
     *
     * @param vehicleId        The ID of the vehicle.
     * @param fuelAdded        The amount of fuel added.
     * @param distanceTraveled The distance traveled.
     * @param date             The date of the record.
     * @throws IllegalArgumentException if vehicle does not exist or validation fails.
     */
    public void addFuelRecord(String vehicleId, double fuelAdded, double distanceTraveled, String date) {
        if (!vehicles.containsKey(vehicleId)) {
            throw new IllegalArgumentException("Vehicle ID " + vehicleId + " does not exist.");
        }
        
        FuelRecord record = new FuelRecord(vehicleId, fuelAdded, distanceTraveled, date);
        recordsByVehicle.get(vehicleId).add(record);
    }

    /**
     * Returns all registered vehicles.
     *
     * @return A list of all vehicles.
     */
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles.values());
    }

    /**
     * Returns all records for a specific vehicle.
     *
     * @param vehicleId The ID of the vehicle.
     * @return A list of fuel records.
     */
    public List<FuelRecord> getRecordsByVehicle(String vehicleId) {
        return recordsByVehicle.getOrDefault(vehicleId, Collections.emptyList());
    }

    /**
     * Calculates the average fuel efficiency for a specific vehicle using Streams.
     *
     * @param vehicleId The ID of the vehicle.
     * @return The average efficiency. Returns 0 if no records exist.
     */
    public double getAverageEfficiency(String vehicleId) {
        List<FuelRecord> records = recordsByVehicle.getOrDefault(vehicleId, Collections.emptyList());
        return records.stream()
                .mapToDouble(FuelRecord::calculateEfficiency)
                .average()
                .orElse(0.0);
    }

    /**
     * Finds the vehicle with the highest average fuel efficiency.
     *
     * @return The most efficient Vehicle object, or null if no records exist.
     */
    public Vehicle findMostEfficientVehicle() {
        return vehicles.values().stream()
                .filter(v -> !getRecordsByVehicle(v.getVehicleId()).isEmpty())
                .max(Comparator.comparingDouble(v -> getAverageEfficiency(v.getVehicleId())))
                .orElse(null);
    }

    /**
     * Returns a map containing summary statistics for each vehicle.
     */
    public List<VehicleSummary> getSummary() {
        return vehicles.values().stream()
                .map(v -> new VehicleSummary(
                        v.getVehicleName(),
                        v.getVehicleId(),
                        getRecordsByVehicle(v.getVehicleId()).size(),
                        getAverageEfficiency(v.getVehicleId())
                ))
                .collect(Collectors.toList());
    }

    /**
     * Helper class for summary data.
     */
    public static record VehicleSummary(String name, String id, int totalRecords, double avgEfficiency) {}
}
