package br.com.CadUsuarioCliente.view;

import javax.swing.*;

public class MainView extends JFrame {

    private JDesktopPane desktop;

    public MainView() {

        setTitle("Sistema NeyBateria");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        setContentPane(desktop);

        criarMenu();
    }

    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastro");

        JMenuItem menuUsuario = new JMenuItem("Usuário");
        JMenuItem menuCliente = new JMenuItem("Cliente");

        menuCadastro.add(menuUsuario);
        menuCadastro.add(menuCliente);

        menuBar.add(menuCadastro);

        setJMenuBar(menuBar);

        menuUsuario.addActionListener(e ->
                abrirTela(new UsuarioView())
        );

        menuCliente.addActionListener(e ->
                abrirTela(new br.com.CadUsuarioCliente.view.cliente.ClienteView())
        );
    }


    private void abrirTela(JInternalFrame tela) {
        desktop.add(tela);
        tela.setVisible(true);
    }

}
