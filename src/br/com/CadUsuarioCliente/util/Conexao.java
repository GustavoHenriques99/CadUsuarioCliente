package br.com.CadUsuarioCliente.util;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

    //dados de Acesso ao banco - (XAMPP)
    private static final String link = "jdbc:mysql://localhost:3306/CadUsuarioCliente_db";
    private static final String user = "root";
    private static final String password = "";

    //Abrindo a conexão com o banco
    public static Connection getConnection(){

        try {
            return DriverManager.getConnection(link, user, password);
        } catch (SQLException e) {
            // Caso ocorra erro, lança uma exceção informando o problema
            throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage());
        }
    }
}

