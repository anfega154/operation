package com.quasar.operation.core.domain;

import com.quasar.operation.utils.LocalizationHelper;

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

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            for (String[] satelliteMessage : messages) {
                if (i < satelliteMessage.length && satelliteMessage[i] != null && !satelliteMessage[i].isEmpty()) {
                    result.append(satelliteMessage[i]).append(" ");
                    break;
                }
            }
        }

        return result.toString().trim();
    }
}
