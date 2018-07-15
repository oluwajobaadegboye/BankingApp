package edu.mum.cs.bankingapp.util;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Hex;

public class PasswordUtil {
    public static String encodePassword(String password) {
        return Hex.encodeHexString("joba123".getBytes(Charsets.UTF_8));
    }

    public static String decodePassword(String encodedPassword) {
        try {
            return new String(new Hex().decode(encodedPassword.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
