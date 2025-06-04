package model;

public class Credential {
    private int id;
    private String service;
    private String username;
    private String password; // criptografada

    public Credential(String service, String username, String password) {
        this.service = service;
        this.username = username;
        this.password = password;
    }

    public Credential(int id, String service, String username, String password) {
        this.id = id;
        this.service = service;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Serviço: %s | Usuário: %s | Senha: %s",
                id, service, username, password);
    }
}
