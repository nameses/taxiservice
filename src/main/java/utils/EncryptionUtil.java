package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

    public static String getEncrypted(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] messageDigestByte = messageDigest.digest(input.getBytes());
            BigInteger result = new BigInteger(1, messageDigestByte);
            final int HEX = 16;
            return result.toString(HEX);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
