package com.quasar.operation.core.domain;

import com.quasar.operation.utils.LocalizationHelper;

import java.awt.*;

public class LocationCalculator {

    private static final Point KENOBI = new Point(-500, -200);
    private static final Point SKYWALKER = new Point(100, -100);
    private static final Point SATO = new Point(500, 100);

    public static Point getLocation(double[] distances) {
        if (distances.length != 3) {
            String message = LocalizationHelper.getMessage("error.invalid.satellite.distances");
            throw new IllegalArgumentException(message);
        }

        double[] result = solveTrilateration(
                KENOBI.x, KENOBI.y, distances[0],
                SKYWALKER.x, SKYWALKER.y, distances[1],
                SATO.x, SATO.y, distances[2]
        );

        return new Point((int) Math.round(result[0]), (int) Math.round(result[1]));
    }

    private static double[] solveTrilateration(double x1, double y1, double r1,
                                               double x2, double y2, double r2,
                                               double x3, double y3, double r3) {
        double A = 2 * (x2 - x1);
        double B = 2 * (y2 - y1);
        double C = Math.pow(r1, 2) - Math.pow(r2, 2) - Math.pow(x1, 2) + Math.pow(x2, 2) - Math.pow(y1, 2) + Math.pow(y2, 2);
        double D = 2 * (x3 - x2);
        double E = 2 * (y3 - y2);
        double F = Math.pow(r2, 2) - Math.pow(r3, 2) - Math.pow(x2, 2) + Math.pow(x3, 2) - Math.pow(y2, 2) + Math.pow(y3, 2);

        double x = (C * E - F * B) / (E * A - B * D);
        double y = (C * D - A * F) / (B * D - A * E);

        return new double[]{x, y};
    }
}
