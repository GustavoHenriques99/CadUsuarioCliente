package br.com.CadUsuarioCliente.dao;

import br.com.CadUsuarioCliente.Usuario;
import br.com.CadUsuarioCliente.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // AUTENTICAR
    public Usuario autenticar(String login, String senha) {

        String sql = """
                SELECT * FROM tbl_usuario 
                WHERE login_usuario = ? AND senha_usuario = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setSobrenomeUsuario(rs.getString("sobrenome_usuario"));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                usuario.setTelefoneUsuario(rs.getString("telefone_usuario"));
                usuario.setLoginUsuario(rs.getString("login_usuario"));
                usuario.setIdNivelAcesso(rs.getInt("id_niveldeacesso"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao autenticar: " + e.getMessage());
        }

        return null;
    }

    // INSERIR
    public void inserir(Usuario usuario) {

        String sql = """
                INSERT INTO tbl_usuario 
                (nome_usuario, sobrenome_usuario, email_usuario,
                 telefone_usuario, login_usuario, senha_usuario,
                 id_niveldeacesso)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getSobrenomeUsuario());
            stmt.setString(3, usuario.getEmailUsuario());
            stmt.setString(4, usuario.getTelefoneUsuario());
            stmt.setString(5, usuario.getLoginUsuario());
            stmt.setString(6, usuario.getSenhaUsuario());
            stmt.setInt(7, usuario.getIdNivelAcesso());

            stmt.executeUpdate();
            System.out.println("✅ Usuário cadastrado!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir usuário: " + e.getMessage());
        }
    }

    // LISTAR
    public List<Usuario> listar() {

        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM tbl_usuario";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setSobrenomeUsuario(rs.getString("sobrenome_usuario"));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                usuario.setTelefoneUsuario(rs.getString("telefone_usuario"));
                usuario.setLoginUsuario(rs.getString("login_usuario"));
                usuario.setSenhaUsuario(rs.getString("senha_usuario"));
                usuario.setIdNivelAcesso(rs.getInt("id_niveldeacesso"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar usuários: " + e.getMessage());
        }

        return usuarios;
    }

    // ATUALIZAR
    public void atualizar(Usuario usuario) {

        String sql = """
                UPDATE tbl_usuario SET
                nome_usuario = ?, sobrenome_usuario = ?, email_usuario = ?,
                telefone_usuario = ?, login_usuario = ?, senha_usuario = ?,
                id_niveldeacesso = ?
                WHERE id_usuario = ?
                """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getSobrenomeUsuario());
            stmt.setString(3, usuario.getEmailUsuario());
            stmt.setString(4, usuario.getTelefoneUsuario());
            stmt.setString(5, usuario.getLoginUsuario());
            stmt.setString(6, usuario.getSenhaUsuario());
            stmt.setInt(7, usuario.getIdNivelAcesso());
            stmt.setInt(8, usuario.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("✏ Usuário atualizado!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // DELETAR
    public void deletar(int id) {

        String sql = "DELETE FROM tbl_usuario WHERE id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("🗑 Usuário removido!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
