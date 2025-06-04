package core;

import crypto.CryptoEngine;
import security.BreachScanner;
import storage.VaultDatabase;
import utils.Generator;
import model.Credential;

import java.util.List;
import java.util.Scanner;

public class PasswordManager {

    private final Authenticator authenticator = new Authenticator();
    private final CryptoEngine cryptoEngine = new CryptoEngine();
    private final Generator passwordGenerator = new Generator();
    private final BreachScanner breachScanner = new BreachScanner();
    private final VaultDatabase vaultDatabase = new VaultDatabase();

    public boolean authenticate(Scanner scanner) {
        // Passa o scanner para o Authenticator que faz a leitura
        return authenticator.authenticate(scanner);
    }

    // Os outros métodos permanecem iguais
    public void addPassword(Scanner scanner) {
        System.out.print("Serviço: ");
        String service = scanner.nextLine();

        System.out.print("Usuário: ");
        String user = scanner.nextLine();

        System.out.print("Senha: ");
        String rawPassword = scanner.nextLine();

        String encryptedPassword = cryptoEngine.encrypt(rawPassword);

        Credential credential = new Credential(service, user, encryptedPassword);

        vaultDatabase.insert(credential);
        System.out.println("✅ Credencial salva com sucesso.");
    }

    public void listPasswords() {
        List<Credential> credentials = vaultDatabase.findAll();

        if (credentials.isEmpty()) {
            System.out.println("Nenhuma credencial salva.");
            return;
        }

        for (Credential c : credentials) {
            System.out.println(c);
        }
    }

    public void removePassword(Scanner scanner) {
        System.out.print("ID da credencial a excluir: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            boolean removed = vaultDatabase.delete(id);
            if (removed) {
                System.out.println("✅ Credencial removida.");
            } else {
                System.out.println("⚠️ Nenhuma credencial com esse ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida.");
        }
    }

    public void generateSecurePassword(Scanner scanner) {
        System.out.print("Comprimento da senha: ");
        try {
            int length = Integer.parseInt(scanner.nextLine());
            String password = passwordGenerator.create(length);
            System.out.println("🔐 Senha gerada: " + password);
        } catch (NumberFormatException e) {
            System.out.println("❌ Número inválido.");
        }
    }

    public void checkPasswordLeak(Scanner scanner) {
        System.out.print("Senha a verificar: ");
        String input = scanner.nextLine();

        boolean leaked = breachScanner.isLeaked(input);
        if (leaked) {
            System.out.println("⚠️ Senha exposta em vazamentos.");
        } else {
            System.out.println("✅ Senha segura.");
        }
    }
}
