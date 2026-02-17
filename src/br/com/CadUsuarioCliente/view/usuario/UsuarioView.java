package br.com.neybateria.view.usuario;

import br.com.CadUsuarioCliente.dao.UsuarioDAO;
import br.com.CadUsuarioCliente.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UsuarioView extends JInternalFrame {

    private JTextField txtNome;
    private JTextField txtSobrenome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JTextField txtNivel;

    private JTable tabela;
    private DefaultTableModel modelo;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private int idSelecionado = 0;

    public UsuarioView() {

        super("Cadastro de Usuário", true, true, true, true);
        setSize(900, 600);
        setLayout(new BorderLayout());

        // ===== PAINEL FORMULÁRIO =====
        JPanel painelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        painelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Sobrenome:"));
        txtSobrenome = new JTextField();
        painelForm.add(txtSobrenome);

        painelForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelForm.add(txtEmail);

        painelForm.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelForm.add(txtTelefone);

        painelForm.add(new JLabel("Login:"));
        txtLogin = new JTextField();
        painelForm.add(txtLogin);

        painelForm.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        painelForm.add(txtSenha);

        painelForm.add(new JLabel("Nível de Acesso (ID):"));
        txtNivel = new JTextField();
        painelForm.add(txtNivel);

        add(painelForm, BorderLayout.NORTH);

        // ===== TABELA =====
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Login", "Email"}, 0);

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // ===== BOTÕES =====
        JPanel painelBotoes = new JPanel();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        add(painelBotoes, BorderLayout.SOUTH);

        // ===== EVENTOS =====

        btnSalvar.addActionListener(e -> salvar());

        btnExcluir.addActionListener(e -> excluir());

        btnLimpar.addActionListener(e -> limparCampos());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selecionarUsuario();
            }
        });

        carregarTabela();
    }

    // ==============================
    // MÉTODO SALVAR
    // ==============================
    private void salvar() {

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(txtNome.getText());
        usuario.setSobrenomeUsuario(txtSobrenome.getText());
        usuario.setEmailUsuario(txtEmail.getText());
        usuario.setTelefoneUsuario(txtTelefone.getText());
        usuario.setLoginUsuario(txtLogin.getText());
        usuario.setSenhaUsuario(new String(txtSenha.getPassword()));
        usuario.setIdNivelAcesso(Integer.parseInt(txtNivel.getText()));

        if (idSelecionado == 0) {
            usuarioDAO.inserir(usuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
        } else {
            usuario.setIdUsuario(idSelecionado);
            usuarioDAO.atualizar(usuario);
            JOptionPane.showMessageDialog(this, "Usuário atualizado!");
            idSelecionado = 0;
        }

        carregarTabela();
        limparCampos();
    }

    // ==============================
    // MÉTODO EXCLUIR
    // ==============================
    private void excluir() {

        if (idSelecionado != 0) {

            int opcao = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.YES_OPTION) {
                usuarioDAO.deletar(idSelecionado);
                JOptionPane.showMessageDialog(this, "Usuário excluído!");
                carregarTabela();
                limparCampos();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela!");
        }
    }

    // ==============================
    // MÉTODO CARREGAR TABELA
    // ==============================
    private void carregarTabela() {

        modelo.setRowCount(0);

        for (Usuario u : usuarioDAO.listar()) {
            modelo.addRow(new Object[]{
                    u.getIdUsuario(),
                    u.getNomeUsuario(),
                    u.getLoginUsuario(),
                    u.getEmailUsuario()
            });
        }
    }

    // ==============================
    // SELECIONAR USUÁRIO
    // ==============================
    private void selecionarUsuario() {

        int linha = tabela.getSelectedRow();

        idSelecionado = (int) modelo.getValueAt(linha, 0);

        txtNome.setText(modelo.getValueAt(linha, 1).toString());
        txtLogin.setText(modelo.getValueAt(linha, 2).toString());
        txtEmail.setText(modelo.getValueAt(linha, 3).toString());
    }

    // ==============================
    // LIMPAR CAMPOS
    // ==============================
    private void limparCampos() {

        txtNome.setText("");
        txtSobrenome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        txtNivel.setText("");

        idSelecionado = 0;
        tabela.clearSelection();
    }
}
