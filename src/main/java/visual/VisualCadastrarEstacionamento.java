package visual;

import service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualCadastrarEstacionamento extends JFrame{
    private JPanel cadastrarEstacionamento;
    private JTextField textIdCarro;
    private JTextField textHoraEntra;
    private JButton cadastrarButton;
    private JButton cancelarButton;

    UsuarioService usuarioService = new UsuarioService();

public VisualCadastrarEstacionamento() {

    setContentPane(cadastrarEstacionamento);
    setTitle("Cadastrar carro no estacionamento");
    setBounds(600, 200, 400, 300);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    cancelarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
    cadastrarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            long id = usuarioService.converterStringLong(textIdCarro.getText());
            int hora = usuarioService.converterStringIntHora(textHoraEntra.getText());
            System.out.println(id);
            System.out.println(hora);
            if (id <= 0 || hora <= 0){
                JOptionPane.showMessageDialog(cadastrarButton, "Entrada inválida!");
            } else {
                System.out.println(id);
                if (usuarioService.conferirCarro(id)){
                    usuarioService.adicionarCarro(id, textHoraEntra.getText());
                    JOptionPane.showMessageDialog(cadastrarButton,"Carro inserido no estacionamento com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(cadastrarButton, "O ID não existe!");
                }
            }
        }
    });
}
}
