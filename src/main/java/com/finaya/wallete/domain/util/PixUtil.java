package com.finaya.wallete.domain.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class PixUtil {

    private static final String ispb = "00000001";
    private static final String prefix = "E";

    private static final Random RANDOM = new Random();

    public static String generateEndToEnd() {

        String timestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmm")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now());

        String sequencial = randomAlphaNumeric();

        return prefix + ispb + timestamp + sequencial;

    }

    private static String randomAlphaNumeric() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(11);
        for (int i = 0; i < 11; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String generateEVPKey() {
        return UUID.randomUUID().toString();
    }
}
