import java.io.*;
import java.util.Properties;

public class Credentials {

    private String token;

    private static Credentials credentials;

    public static Credentials getCredentials() {
        if (credentials == null) {
            credentials = new Credentials();
        }
        return credentials;
    }

    private Credentials() {
        try (InputStream input = new FileInputStream("credentials.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            this.token = properties.getProperty("token");
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            System.out.println("Please populate the credentials.properties file");
        }
    }

    public String getToken() {
        return this.token;
    }
}
