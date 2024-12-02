package com.quasar.operation.aplication.usecase;

import com.quasar.operation.core.domain.LocationCalculator;
import com.quasar.operation.core.domain.MessageDecoder;

import java.awt.*;

public class DetermineMessageAndLocation {


    public static DeterminationResult execute(double[] distances, String[][] messages) {
        Point location = LocationCalculator.getLocation(distances);
        String message = MessageDecoder.decodeMessage(messages);

        return new DeterminationResult(location, message);
    }

    public static class DeterminationResult {
        private final Point location;
        private final String message;

        public DeterminationResult(Point location, String message) {
            this.location = location;
            this.message = message;
        }

        public Point getLocation() {
            return location;
        }

        public String getMessage() {
            return message;
        }
    }
}
