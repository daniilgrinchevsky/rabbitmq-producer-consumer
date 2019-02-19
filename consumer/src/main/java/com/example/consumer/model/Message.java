package com.example.consumer.model;

import java.io.Serializable;
import java.util.Random;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String value;

    public Message() {
        this.value = generateValue();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String generateValue() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + value + '\'' +
                '}';
    }
}

