package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordHasher {
    public static String sha256(String plain) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(plain.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
