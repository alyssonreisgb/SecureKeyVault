package storage;

import model.Credential;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VaultDatabase {

    private static final String DB_URL = "jdbc:sqlite:data/securekeyvault.db";

    public VaultDatabase() {
        // Garante que o diretório "data" existe
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
                CREATE TABLE IF NOT EXISTS credentials (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    service TEXT NOT NULL,
                    username TEXT NOT NULL,
                    password TEXT NOT NULL
                );
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void insert(Credential credential) {
        String sql = "INSERT INTO credentials (service, username, password) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, credential.getService());
            pstmt.setString(2, credential.getUsername());
            pstmt.setString(3, credential.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir credencial: " + e.getMessage());
        }
    }

    public List<Credential> findAll() {
        List<Credential> list = new ArrayList<>();
        String sql = "SELECT * FROM credentials";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Credential(
                        rs.getInt("id"),
                        rs.getString("service"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar credenciais: " + e.getMessage());
        }

        return list;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM credentials WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover credencial: " + e.getMessage());
            return false;
        }
    }
}
