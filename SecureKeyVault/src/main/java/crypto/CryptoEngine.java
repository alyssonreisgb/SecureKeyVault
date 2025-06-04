package crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoEngine {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890abcdef"; // Deve ter 16 bytes (128 bits)

    public String encrypt(String input) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(input.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            System.err.println("Erro ao criptografar: " + e.getMessage());
            return null;
        }
    }

    public String decrypt(String encryptedText) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(encryptedText);
            byte[] decrypted = cipher.doFinal(decoded);

            return new String(decrypted);
        } catch (Exception e) {
            System.err.println("Erro ao descriptografar: " + e.getMessage());
            return null;
        }
    }
}
