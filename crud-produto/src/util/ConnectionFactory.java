package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static final String URL = "jdbc:mysql://localhost:3306/aulajdbc";
    private static final String USER = "root";
    private static final String PASS = "aluno";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    public static Connection getConnection() {
        try {
            // Verifica a instalação do Driver
            Class.forName(DRIVER);
            System.out.println("Tentando conectar ao banco de dados...");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch(ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado!");
            throw new RuntimeException("Erro: Driver JDBC ausente " + e);
        } catch(SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " );
            e.printStackTrace();
            throw new RuntimeException("Erro ao obter a conexão com banco de dados" + e);
        }
    }
}
