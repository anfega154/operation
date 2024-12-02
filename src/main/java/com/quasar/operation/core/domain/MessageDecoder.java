package com.quasar.operation.core.domain;

import com.quasar.operation.helpers.LocalizationHelper;

public class MessageDecoder {

    public static String decodeMessage(String[][] messages) {
        if (messages.length != 3) {
            String message = LocalizationHelper.getMessage("error.invalid.satellite.distances");
            throw new IllegalArgumentException(message);
        }

        int maxLength = 0;
        for (String[] message : messages) {
            maxLength = Math.max(maxLength, message.length);
        }

        String[] result = new String[maxLength];

        for (String[] satelliteMessage : messages) {
            for (int i = 0; i < satelliteMessage.length; i++) {
                if (satelliteMessage[i] != null && !satelliteMessage[i].isEmpty()) {
                    result[i] = satelliteMessage[i];
                }
            }
        }

        return String.join(" ", result).trim();
    }
}
