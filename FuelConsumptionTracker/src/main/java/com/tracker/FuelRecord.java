package com.tracker;

/**
 * Represents a fuel record for a vehicle, tracking fuel added and distance traveled.
 */
public class FuelRecord {
    private String vehicleId;
    private double fuelAdded;
    private double distanceTraveled;
    private String date;

    /**
     * Constructs a new FuelRecord with the specified details.
     *
     * @param vehicleId        The unique identifier for the vehicle.
     * @param fuelAdded        The amount of fuel added in liters.
     * @param distanceTraveled The distance traveled in kilometers.
     * @param date             The date of the record in dd-MM-yyyy format.
     * @throws IllegalArgumentException if fuelAdded or distanceTraveled is <= 0.
     */
    public FuelRecord(String vehicleId, double fuelAdded, double distanceTraveled, String date) {
        if (fuelAdded <= 0) {
            throw new IllegalArgumentException("Fuel must be positive");
        }
        if (distanceTraveled <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }
        this.vehicleId = vehicleId;
        this.fuelAdded = fuelAdded;
        this.distanceTraveled = distanceTraveled;
        this.date = date;
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
     * Gets the fuel added.
     *
     * @return The amount of fuel added in liters.
     */
    public double getFuelAdded() {
        return fuelAdded;
    }

    /**
     * Gets the distance traveled.
     *
     * @return The distance traveled in kilometers.
     */
    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    /**
     * Gets the date of the record.
     *
     * @return The date string (dd-MM-yyyy).
     */
    public String getDate() {
        return date;
    }

    /**
     * Calculates the fuel efficiency in kilometers per liter.
     *
     * @return The efficiency (distance / fuel). Returns 0 if fuelAdded is 0.
     */
    public double calculateEfficiency() {
        if (fuelAdded == 0) {
            return 0;
        }
        return distanceTraveled / fuelAdded;
    }

    /**
     * Returns a string representation of the fuel record.
     *
     * @return A formatted string containing record details and efficiency.
     */
    @Override
    public String toString() {
        return String.format("FuelRecord [Vehicle ID: %s, Date: %s, Fuel: %.2f L, Distance: %.2f km, Efficiency: %.2f km/L]",
                vehicleId, date, fuelAdded, distanceTraveled, calculateEfficiency());
    }
}
