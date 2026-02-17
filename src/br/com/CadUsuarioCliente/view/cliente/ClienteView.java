package br.com.CadUsuarioCliente.view.cliente;

import br.com.neybateria.dao.ClienteDAO;
import br.com.neybateria.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClienteView extends JInternalFrame {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtEmail;

    private JTable tabela;
    private DefaultTableModel modelo;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private int idSelecionado = 0;

    public ClienteView() {

        super("Cadastro de Cliente", true, true, true, true);
        setSize(900, 600);
        setLayout(new BorderLayout());

        // ===== FORMULÁRIO =====
        JPanel painelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        painelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("CPF/CNPJ:"));
        txtCpf = new JTextField();
        painelForm.add(txtCpf);

        painelForm.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelForm.add(txtTelefone);

        painelForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelForm.add(txtEmail);

        add(painelForm, BorderLayout.NORTH);

        // ===== TABELA =====
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nome", "CPF/CNPJ", "Telefone", "Email"}, 0);

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
                selecionarCliente();
            }
        });

        carregarTabela();
    }

    private void salvar() {

        Cliente cliente = new Cliente();

        cliente.setNome(txtNome.getText());
        cliente.setCpfCnpj(txtCpf.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());

        if (idSelecionado == 0) {
            clienteDAO.inserir(cliente);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado!");
        } else {
            cliente.setId(idSelecionado);
            clienteDAO.atualizar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente atualizado!");
            idSelecionado = 0;
        }

        carregarTabela();
        limparCampos();
    }

    private void excluir() {

        if (idSelecionado != 0) {

            int opcao = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.YES_OPTION) {
                clienteDAO.deletar(idSelecionado);
                JOptionPane.showMessageDialog(this, "Cliente excluído!");
                carregarTabela();
                limparCampos();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente!");
        }
    }

    private void carregarTabela() {

        modelo.setRowCount(0);

        for (Cliente c : clienteDAO.listar()) {
            modelo.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getCpfCnpj(),
                    c.getTelefone(),
                    c.getEmail()
            });
        }
    }

    private void selecionarCliente() {

        int linha = tabela.getSelectedRow();

        if (linha != -1) {
            idSelecionado = (int) modelo.getValueAt(linha, 0);

            txtNome.setText(modelo.getValueAt(linha, 1).toString());
            txtCpf.setText(modelo.getValueAt(linha, 2).toString());
            txtTelefone.setText(modelo.getValueAt(linha, 3).toString());
            txtEmail.setText(modelo.getValueAt(linha, 4).toString());
        }
    }

    private void limparCampos() {

        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");

        idSelecionado = 0;
        tabela.clearSelection();
    }
}
