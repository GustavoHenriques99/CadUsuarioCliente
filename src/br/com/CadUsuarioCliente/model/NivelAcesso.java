package br.com.CadUsuarioCliente.model;

public class NivelAcesso {
    private int idNivelAcesso; // id_niveldeacesso
    private String descricao;   // descricao_niveldeacesso

    public NivelAcesso() {}

    // Getters e Setters
    public int getIdNivelAcesso() { return idNivelAcesso; }
    public void setIdNivelAcesso(int idNivelAcesso) { this.idNivelAcesso = idNivelAcesso; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}