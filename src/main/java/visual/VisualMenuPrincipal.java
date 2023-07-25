package visual;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualMenuPrincipal extends JFrame{
    private JPanel menuPrincipal;
    private JButton verTodosOsCarrosButton;
    private JButton verCarrosNoEstacionamentoButton;
    private JButton adicionarCarroButton;
    private JButton adicionarCarroNoEstacionamentoButton;
    private JButton atualizarTempoDePermanênciaButton;
    private JButton retirarCarroDoEstacionamentoButton;
    private JButton históricoButton;

    public VisualMenuPrincipal(){

        setContentPane(menuPrincipal);
        setTitle("Menu Principal");
        setBounds(600, 200, 400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        verTodosOsCarrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualVerCarros vvc = new VisualVerCarros();
            }
        });

        verCarrosNoEstacionamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualVerEstacionamento vvc = new VisualVerEstacionamento();
            }
        });

        históricoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualVerHistorico vvh = new VisualVerHistorico();
            }
        });

        adicionarCarroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            VisualCadastrarCarro vcc = new VisualCadastrarCarro();
            }
        });

        adicionarCarroNoEstacionamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualCadastrarEstacionamento vce = new VisualCadastrarEstacionamento();
            }
        });

        atualizarTempoDePermanênciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualAtualizarPermanencia vap = new VisualAtualizarPermanencia();
            }
        });

        retirarCarroDoEstacionamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualRetirarCarros vrc = new VisualRetirarCarros();
            }
        });
    }
}
