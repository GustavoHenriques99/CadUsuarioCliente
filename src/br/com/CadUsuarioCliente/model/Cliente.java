package br.com.CadUsuarioCliente.model;

import java.time.LocalDateTime;

public class Cliente {
    // Atributos baseados na tbl_cliente do seu banco
    private int id;
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private LocalDateTime dataCadastro; // Importante para o histórico

    // Construtor vazio
    public Cliente() {}

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}