package org.skipper.store;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class AdminLogin {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String DB_USERNAME = System.getenv("DB_USERNAME");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    public void adminLogin() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        LibraryCLI libraryCLI = new LibraryCLI();

        // Perform authentication
        if (authenticateAdmin(username, password)) {
            System.out.println("Admin login successful.");
            libraryCLI.run();
        } else {
            System.out.println("Invalid username or password. Admin login failed.");
        }
    }

    private boolean authenticateAdmin(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT password FROM admins WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return storedPassword.equals(password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.adminLogin();
    }

}
