package visual;

import service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualCadastrarCarro extends JFrame{
    private JPanel criarCarro;
    private JButton adicionarButton;
    private JButton cancelarButton;
    private JTextField textDono;
    private JTextField textMarca;
    private JTextField textPlaca;
    private JTextField textUsuario;

UsuarioService usuarioService = new UsuarioService();

public VisualCadastrarCarro() {

    this.setContentPane(criarCarro);
    this.setTitle("Cadastrar novo carro");
    this.setBounds(600, 200, 400, 300);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    cancelarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });

    adicionarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        usuarioService.criarCarro(textDono.getText(), textMarca.getText(), textPlaca.getText(), textUsuario.getText());
        JOptionPane.showMessageDialog(adicionarButton, "Novo carro cadastrado com sucesso!");
        dispose();
        }
    });
}
}
