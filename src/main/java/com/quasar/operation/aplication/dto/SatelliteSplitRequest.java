package com.quasar.operation.aplication.dto;

import java.util.List;

public class SatelliteSplitRequest {

    private double distance;
    private List<String> message;

    // Getters y Setters
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}