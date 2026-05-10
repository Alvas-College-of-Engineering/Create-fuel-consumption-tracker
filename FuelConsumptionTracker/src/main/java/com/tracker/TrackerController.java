package com.tracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle web requests for the Fuel Consumption Tracker.
 */
@Controller
public class TrackerController {

    private final FuelTracker tracker;

    public TrackerController(FuelTracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Dashboard page showing all vehicles and the most efficient one.
     */
    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("summaries", tracker.getSummary());
        model.addAttribute("mostEfficient", tracker.findMostEfficientVehicle());
        return "dashboard";
    }

    /**
     * Page to add a new vehicle.
     */
    @GetMapping("/add-vehicle")
    public String showAddVehicleForm() {
        return "add-vehicle";
    }

    /**
     * Process new vehicle submission.
     */
    @PostMapping("/add-vehicle")
    public String addVehicle(@RequestParam String id, @RequestParam String name, @RequestParam String type) {
        tracker.addVehicle(new Vehicle(id, name, type));
        return "redirect:/";
    }

    /**
     * Page to add a fuel record.
     */
    @GetMapping("/add-record")
    public String showAddRecordForm(Model model) {
        model.addAttribute("vehicles", tracker.getAllVehicles());
        return "add-record";
    }

    /**
     * Process new fuel record submission.
     */
    @PostMapping("/add-record")
    public String addRecord(@RequestParam String vehicleId, 
                            @RequestParam double fuel, 
                            @RequestParam double distance, 
                            @RequestParam String date,
                            Model model) {
        try {
            tracker.addFuelRecord(vehicleId, fuel, distance, date);
            return "redirect:/vehicle/" + vehicleId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("vehicles", tracker.getAllVehicles());
            return "add-record";
        }
    }

    /**
     * View details and history for a specific vehicle.
     */
    @GetMapping("/vehicle/{id}")
    public String vehicleDetails(@PathVariable String id, Model model) {
        model.addAttribute("records", tracker.getRecordsByVehicle(id));
        model.addAttribute("avgEfficiency", tracker.getAverageEfficiency(id));
        // Find vehicle name for header
        String name = tracker.getAllVehicles().stream()
                .filter(v -> v.getVehicleId().equals(id))
                .map(Vehicle::getVehicleName)
                .findFirst()
                .orElse("Unknown Vehicle");
        model.addAttribute("vehicleName", name);
        return "vehicle-details";
    }
}
