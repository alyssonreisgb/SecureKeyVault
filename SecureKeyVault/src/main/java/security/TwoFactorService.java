package security;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import java.util.Arrays;

public class TwoFactorService {

    private final GoogleAuthenticator gAuth;

    public TwoFactorService() {
        this.gAuth = new GoogleAuthenticator();
    }

    /**
     * Gera uma nova chave secreta para 2FA.
     * @return GoogleAuthenticatorKey com a chave secreta.
     */
    public GoogleAuthenticatorKey gerarChaveSecreta() {
        return gAuth.createCredentials();
    }

    /**
     * Gera a URL do QR Code para configurar o app autenticador (ex: Google Authenticator).
     * @param usuario nome ou email do usuário para identificação.
     * @param key chave secreta gerada.
     * @return URL do QR Code (otpauth://...)
     */
    public String gerarQRCode(String usuario, GoogleAuthenticatorKey key) {
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("SecureKeyVault", usuario, key);
    }

    /**
     * Valida o código 2FA digitado pelo usuário.
     * @param segredo chave secreta.
     * @param codigo código TOTP gerado pelo app autenticador.
     * @return true se o código estiver correto e válido.
     */
    public boolean validarCodigo(String segredo, int codigo) {
        return gAuth.authorize(Arrays.toString(secretToBytes(segredo)), codigo);
    }

    // Método auxiliar para converter segredo String para bytes (GoogleAuthenticator espera isso)
    private byte[] secretToBytes(String segredo) {
        return segredo.getBytes();
    }
}
