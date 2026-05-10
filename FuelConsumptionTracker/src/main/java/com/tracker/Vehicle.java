package com.tracker;

/**
 * Represents a vehicle in the Fuel Consumption Tracker application.
 */
public class Vehicle {
    private String vehicleId;
    private String vehicleName;
    private String vehicleType;

    /**
     * Constructs a new Vehicle with the specified details.
     *
     * @param vehicleId   The unique identifier for the vehicle.
     * @param vehicleName The name or model of the vehicle.
     * @param vehicleType The type of vehicle (e.g., Car, Bike, Truck).
     */
    public Vehicle(String vehicleId, String vehicleName, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the vehicle ID.
     *
     * @return The vehicle's unique identifier.
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * Gets the vehicle name.
     *
     * @return The name of the vehicle.
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * Gets the vehicle type.
     *
     * @return The type of the vehicle (Car, Bike, Truck).
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Returns a string representation of the vehicle.
     *
     * @return A formatted string containing vehicle details.
     */
    @Override
    public String toString() {
        return String.format("Vehicle [ID: %s, Name: %s, Type: %s]", vehicleId, vehicleName, vehicleType);
    }
}
