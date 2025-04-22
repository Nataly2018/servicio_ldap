package gob.mefp.reportes.security.properties;

import javax.annotation.PostConstruct;
import java.util.Base64;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hmac.key")
public class HMACKeyConfigProperties {
    private String secretKey;
    private byte[] secretKeyBytes;

    @PostConstruct
    void createHMACKey() {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("La clave secreta HMAC no puede ser nula o vacía. Verifica application.properties.");
        }
        // Convertimos la clave secreta a bytes (asegurar compatibilidad con HMAC)
        this.secretKeyBytes = Base64.getDecoder().decode(secretKey);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public byte[] getSecretKeyBytes() {
        return secretKeyBytes;
    }
}