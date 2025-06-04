package security;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class BreachScanner {

    /**
     * Verifica se a senha fornecida já foi exposta em vazamentos conhecidos usando a API Have I Been Pwned.
     *
     * @param password A senha a ser verificada.
     * @return true se a senha foi encontrada em vazamentos, false caso contrário.
     */
    public boolean isCompromised(String password) {
        try {
            String sha1 = sha1Hex(password).toUpperCase();
            String prefix = sha1.substring(0, 5);
            String suffix = sha1.substring(5);

            URL url = new URL("https://api.pwnedpasswords.com/range/" + prefix);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts[0].equalsIgnoreCase(suffix)) {
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao verificar vazamento de senha: " + e.getMessage());
        }

        return false;
    }

    private String sha1Hex(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] bytes = md.digest(input.getBytes("UTF-8"));

        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }

    public boolean isLeaked(String password) {
        // TODO: implementar consulta real a API HaveIBeenPwned
        return false; // Por enquanto sempre retorna false (não exposto)
    }
}
