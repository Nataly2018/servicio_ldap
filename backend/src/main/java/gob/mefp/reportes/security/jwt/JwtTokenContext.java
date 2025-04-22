package gob.mefp.reportes.security.jwt;


import lombok.Data;

@Data
public class JwtTokenContext {

    private String displayName;
    private String username;
    private String email;
    private String accessToken;

    @Override
    public String toString() {
        return "JwtTokenContext{" +
                "displayName='" + displayName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

    public void setUsername(String pNombre) {
    this.username=pNombre;
    }
}
