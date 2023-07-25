package visual;

import service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualAtualizarPermanencia extends JFrame{
    private JPanel atualizarPermanencia;
    private JTextField textEstadia;
    private JTextField textPermanencia;
    private JButton atualizarButton;
    private JButton cancelarButton;

    UsuarioService usuarioService = new UsuarioService();

public VisualAtualizarPermanencia() {

    setContentPane(atualizarPermanencia);
    setTitle("Atualizar permanência no estacionamento");
    setBounds(600, 200, 400, 300);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    cancelarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });

    atualizarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            long id = usuarioService.converterStringLong(textEstadia.getText());
            if (id > 0 || usuarioService.conferirEstacionamento(id)){
                long permanencia = usuarioService.converterStringInt(textPermanencia.getText());
                if (permanencia > 0) {
                    usuarioService.atualizarPermanencia(id, permanencia);
                    usuarioService.atualizarValor(id, permanencia);
                    JOptionPane.showMessageDialog(atualizarButton, "Tempo de permanência atualizado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(atualizarButton,"Dado inválido!");
                }
            } else {
                JOptionPane.showMessageDialog(atualizarButton, "ID inválido!");
            }
        }
    });
}
}
