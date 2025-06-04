package app;

import core.Authenticator;
import core.PasswordManager;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordManager passwordManager = new PasswordManager();

        System.out.println("==== Bem-vindo ao SecureKeyVault ====");

        if (!passwordManager.authenticate(scanner)) {
            System.out.println("Acesso negado.");
            scanner.close();
            return;
        }

        PasswordManager manager = new PasswordManager();
        boolean running = true;

        while (running) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar senha");
            System.out.println("2. Listar senhas");
            System.out.println("3. Remover senha");
            System.out.println("4. Gerar senha segura");
            System.out.println("5. Verificar vazamento");
            System.out.println("0. Sair");
            System.out.print("> ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> manager.addPassword(scanner);
                case "2" -> manager.listPasswords();
                case "3" -> manager.removePassword(scanner);
                case "4" -> manager.generateSecurePassword(scanner);
                case "5" -> manager.checkPasswordLeak(scanner);
                case "0" -> running = false;
                default -> System.out.println("Opção inválida.");
            }
        }

        System.out.println("Encerrando... Obrigado por usar o SecureKeyVault!");
        scanner.close();
    }
}
