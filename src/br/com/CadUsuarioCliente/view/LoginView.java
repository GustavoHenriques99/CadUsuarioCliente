package br.com.neybateria.view;

import br.com.neybateria.view.usuario.UsuarioView;
import br.com.CadUsuarioCliente.dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;

    public LoginView() {

        setTitle("Login - Sistema NeyBateria");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel("Sistema NeyBateria", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel central
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Login:"));
        txtLogin = new JTextField();
        panel.add(txtLogin);

        panel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        panel.add(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        panel.add(btnEntrar);

        JButton btnCriarUsuario = new JButton("Criar Usuário");
        panel.add(btnCriarUsuario);

        add(panel, BorderLayout.CENTER);

        // Ações
        btnEntrar.addActionListener(e -> autenticar());
        btnCriarUsuario.addActionListener(e -> {
            new UsuarioView().setVisible(true);
        });


    }

    private void autenticar() {

        UsuarioDAO dao = new UsuarioDAO();
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());

        var usuario = dao.autenticar(login, senha);

        if (usuario != null) {
            dispose();
            new MainView().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Login inválido!");
        }
    }
}