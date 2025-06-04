package core;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import java.util.Scanner;

public class Authenticator {
    private final String MASTER_PASSWORD = "chaveSegura";
    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();
    private GoogleAuthenticatorKey key;

    public Authenticator() {
        key = gAuth.createCredentials();
    }

    public boolean authenticate(Scanner scanner) {
        System.out.print("Informe a senha mestre: ");
        String inputPass = scanner.nextLine();

        if (!MASTER_PASSWORD.equals(inputPass)) {
            System.out.println("Senha incorreta.");
            return false;
        }

        if (key == null) {
            key = gAuth.createCredentials();
        }
        String secret = key.getKey();

        System.out.println("Configure seu app autenticador com este segredo: " + secret);
        System.out.println("Ou use este QR Code: " + getQRCodeURL("usuario@dominio.com", "SecureKeyVault", secret));

        System.out.print("Digite o código 2FA gerado no app: ");
        String codeStr = scanner.nextLine();

        try {
            int code = Integer.parseInt(codeStr);
            boolean valid = gAuth.authorize(secret, code);
            if (!valid) {
                System.out.println("Código 2FA inválido.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Código 2FA inválido.");
            return false;
        }

        return true;
    }

    public String getTOTPSecret() {
        return key.getKey();
    }

    public String getQRCodeURL(String accountName, String issuer, String secret) {
        return String.format(
                "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                issuer, accountName, secret, issuer
        );
    }
}
