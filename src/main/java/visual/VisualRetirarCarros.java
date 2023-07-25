package visual;

import service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualRetirarCarros extends JFrame {
    private JPanel retirarCarros;
    private JTextField texid;
    private JTextField textHora;
    private JButton retirarButton;
    private JButton cancelarButton;
    private JTextField textCarroid;

    UsuarioService usuarioService = new UsuarioService();

public VisualRetirarCarros() {

    setContentPane(retirarCarros);
    setTitle("Remover carro do estacionamento");
    setBounds(600, 200, 400, 300);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    cancelarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });

    retirarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            long carroid = usuarioService.converterStringLong(textCarroid.getText());
            long id = usuarioService.converterStringLong(texid.getText());
            int hora = usuarioService.converterStringIntHora(textHora.getText());
            if (usuarioService.conferirEstacionamento(id) && (hora > 0)){
                if (usuarioService.carroEstacionado(id) == carroid) {
                    usuarioService.carroSaiu(id, carroid, hora);
                    usuarioService.atualizarPermanencia(id, usuarioService.calcularPermanencia(id));
                    usuarioService.atualizarValor(id, usuarioService.calcularPermanencia(id));
                } else {
                    JOptionPane.showMessageDialog(retirarButton, "Carro não existe no estacionamento");
                }
            } else {
                JOptionPane.showMessageDialog(retirarButton, "Dado inválido!");
            }
        }
    });
}
}
