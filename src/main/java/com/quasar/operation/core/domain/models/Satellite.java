package com.quasar.operation.core.domain.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Satellite {
    @NotNull
    private String name;

    @NotNull
    private Double distance;

    @NotEmpty
    private List<String> message;

    public Satellite(String name, double distance, List<String> message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}