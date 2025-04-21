/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "FileYpfb.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 7/12/2023 12:01 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.utils.archivo;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class FileYpfb {

    private MultipartFile file;
    private byte[] bytesFile;
    private String vFileB64;

    public FileYpfb(MultipartFile file) throws IOException {

        InputStream inputStream = null;
        this.file = file;
        inputStream = file.getInputStream();
        bytesFile = IOUtils.toByteArray(inputStream);
        this.vFileB64 =  Base64.getEncoder().encodeToString(bytesFile);

    }

    public byte[] obtenerSha256(byte[] bytesFile) throws NoSuchAlgorithmException {
        MessageDigest digest = null;

        digest = MessageDigest.getInstance("SHA-256");
        byte[] archivoSha256Hash = digest.digest(bytesFile);
        return archivoSha256Hash;


    }

    public String obtenerBase64ArchivoFromMultipart() {
        return vFileB64;
    }

    public  String obtenerHash() throws NoSuchAlgorithmException {
        byte[] archivoSha256 = obtenerSha256(bytesFile);
        return String.format("%064x", new BigInteger(1, archivoSha256));
    }


}
