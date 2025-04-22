package gob.mefp.reportes.security.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class HMACUtils {
    public static byte[] getSecretKey(String filename) throws IOException {
        byte[] bytes = readFile(filename);
        return decodeKey(bytes);
    }

    private static byte[] readFile(String fileName) throws IOException {
        return Files.readAllBytes(new File(fileName).toPath());
    }

    private static byte[] decodeKey(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }
}
