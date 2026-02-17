package br.com.CadUsuarioCliente.model;

import java.time.LocalDateTime;

public class Usuario {

    private int idUsuario;
    private String nomeUsuario;
    private String sobrenomeUsuario;
    private String emailUsuario;
    private String telefoneUsuario;
    private String loginUsuario;
    private String senhaUsuario;
    private int idNivelAcesso;
    private LocalDateTime dataCadastroUsuario;

    // 🔹 Construtor vazio (OBRIGATÓRIO para DAO)
    public Usuario() {
    }

    // 🔹 Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSobrenomeUsuario() {
        return sobrenomeUsuario;
    }

    public void setSobrenomeUsuario(String sobrenomeUsuario) {
        this.sobrenomeUsuario = sobrenomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public int getIdNivelAcesso() {
        return idNivelAcesso;
    }

    public void setIdNivelAcesso(int idNivelAcesso) {
        this.idNivelAcesso = idNivelAcesso;
    }

    public LocalDateTime getDataCadastroUsuario() {
        return dataCadastroUsuario;
    }

    public void setDataCadastroUsuario(LocalDateTime dataCadastroUsuario) {
        this.dataCadastroUsuario = dataCadastroUsuario;
    }
}
