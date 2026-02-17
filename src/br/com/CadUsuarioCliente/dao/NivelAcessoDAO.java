package br.com.neybateria.dao;

import br.com.CadUsuarioCliente.model.NivelAcesso;
import br.com.CadUsuarioCliente.util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NivelAcessoDAO {

    public List<NivelAcesso> listar() {
        List<NivelAcesso> niveis = new ArrayList<>();
        String sql = "SELECT * FROM tbl_niveldeacesso";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NivelAcesso n = new NivelAcesso();
                n.setIdNivelAcesso(rs.getInt("id_niveldeacesso"));
                n.setDescricao(rs.getString("descricao_niveldeacesso"));
                niveis.add(n);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar níveis de acesso: " + e.getMessage());
        }
        return niveis;
    }
}