package sample;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.security.MessageDigest;
public class Checksum {

    private static byte[] createChecksum(File file, String hash_function) throws Exception {
        InputStream fis = new FileInputStream(file);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance(hash_function);
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }


    @NotNull
    public static String getMD5Checksum(File file) throws Exception {
        byte[] b = createChecksum(file, "MD5");
        return checksumToString(b);
    }

    @NotNull
    public static  String getSHA256Checksum(File file) throws Exception {
        byte[] b = createChecksum(file,"SHA-256");
        return checksumToString(b);
    }


    @NotNull
    private static String checksumToString(@NotNull byte[] b) {
        StringBuilder result = new StringBuilder();
        for (byte b1 : b) {
            result.append(Integer.toString((b1 & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
