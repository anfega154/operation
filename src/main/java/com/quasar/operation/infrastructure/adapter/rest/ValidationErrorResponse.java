package com.quasar.operation.infrastructure.adapter.rest;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private List<String> details;

    public ValidationErrorResponse(LocalDateTime timestamp, int status, String error, String path, List<String> details) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}