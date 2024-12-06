package com.quasar.operation.aplication.usecase;

import com.quasar.operation.core.domain.LocationCalculator;
import com.quasar.operation.core.domain.MessageDecoder;
import com.quasar.operation.core.domain.models.Satellite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DetermineMessageAndLocation {

    private static final Map<String, Satellite> satelliteData = new ConcurrentHashMap<>();

    public static DeterminationResult execute(List<Satellite> satellites) {
        double[] distances = satellites.stream().mapToDouble(Satellite::getDistance).toArray();
        String[][] messages = satellites.stream().map(Satellite::getMessage).map(list -> list.toArray(new String[0])).toArray(String[][]::new);

        Point location = LocationCalculator.getLocation(distances);
        String message = MessageDecoder.decodeMessage(messages);

        return new DeterminationResult(location, message);
    }

    public static Boolean storeSatelliteData(Satellite satellite, String satellite_name) {
        if (!satelliteData.containsKey(satellite_name.toLowerCase())) {
            satelliteData.put(satellite_name.toLowerCase(), satellite);
            return true;
        }
        return false;
    }

    public static Satellite getSatelliteData(String satellite_name) {
        return satelliteData.get(satellite_name.toLowerCase());
    }

    public static int satelliteDataSize() {
        return satelliteData.size();
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
