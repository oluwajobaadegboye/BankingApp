package edu.mum.cs.bankingapp.util;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordUtil {
    public static String encodePassword(String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), "saltvalue".getBytes(), 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            return enc.encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
            return "";
        }
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
