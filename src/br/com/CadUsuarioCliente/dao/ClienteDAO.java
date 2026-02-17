package br.com.CadUsuarioCliente.dao;

import br.com.CadUsuarioCliente.model.Cliente; // Importe corrigido para o pacote model
import br.com.CadUsuarioCliente.util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por todas as operações de banco de dados para a entidade Cliente.
 */
public class ClienteDAO { // Alterado para 'C' maiúsculo (Padrão Java)

    // Inserir um novo cliente
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO tbl_cliente (nome_cliente, cpf_cnpj_cliente, telefone_cliente, email_cliente) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpfCnpj());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());

            stmt.executeUpdate();
            System.out.println("✅ Cliente " + cliente.getNome() + " cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir cliente: " + e.getMessage());
        }
    }

    // Listar todos os clientes
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM tbl_cliente ORDER BY nome_cliente ASC";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome_cliente"));
                c.setCpfCnpj(rs.getString("cpf_cnpj_cliente"));
                c.setTelefone(rs.getString("telefone_cliente"));
                c.setEmail(rs.getString("email_cliente"));

                Timestamp dataBanco = rs.getTimestamp("datacadastro_cliente");
                if (dataBanco != null) {
                    c.setDataCadastro(dataBanco.toLocalDateTime());
                }
                clientes.add(c);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Deletar cliente por ID
    public void deletar(int id) {
        String sql = "DELETE FROM tbl_cliente WHERE id_cliente = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("🗑 Cliente ID " + id + " removido!");
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao deletar: " + e.getMessage());
        }
    }

    // Atualizar dados do cliente
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE tbl_cliente SET nome_cliente = ?, cpf_cnpj_cliente = ?, " +
                "telefone_cliente = ?, email_cliente = ? WHERE id_cliente = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpfCnpj());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getId());

            stmt.executeUpdate();
            System.out.println("✏ Cliente " + cliente.getNome() + " atualizado!");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar cliente: " + e.getMessage());
        }
    }
}