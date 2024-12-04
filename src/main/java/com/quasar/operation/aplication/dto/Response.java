package com.quasar.operation.aplication.dto;

import java.awt.*;

public class Response {
    private final Point position;
    private final String message;

    public Response(Point position, String message) {
        this.position = position;
        this.message = message;
    }

    public Point getPosition() {
        return position;
    }

    public String getMessage() {
        return message;
    }

}
