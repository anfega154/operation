package com.quasar.operation.aplication.usecase;

import com.quasar.operation.core.domain.LocationCalculator;
import com.quasar.operation.core.domain.MessageDecoder;
import com.quasar.operation.core.domain.models.Satellite;
import java.util.List;
import java.awt.*;

public class DetermineMessageAndLocation {


    public static DeterminationResult execute(List<Satellite> satellites) {
        double[] distances = satellites.stream().mapToDouble(Satellite::getDistance).toArray();
        String[][] messages = satellites.stream().map(Satellite::getMessage).map(list -> list.toArray(new String[0])).toArray(String[][]::new);

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
