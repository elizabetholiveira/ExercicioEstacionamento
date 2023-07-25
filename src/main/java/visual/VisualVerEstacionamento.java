package visual;

import service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualVerEstacionamento extends JFrame {

    UsuarioService usuarioService = new UsuarioService();

    public VisualVerEstacionamento(){

        this.setTitle("Todos os carros no estacionamento");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        DefaultListModel<String> minhaLista = new DefaultListModel<>();

        String sql = "select * from tb_estacionamento left join tb_carro on tb_estacionamento.carroid = tb_carro.id order by tb_estacionamento.id ASC;";
        try{
            ResultSet resultSet = usuarioService.getStatement().executeQuery(sql);
            while (resultSet.next()){
                if (resultSet.getString("estado").contains("t")){
                    String mensagem = "ID da estadia: " + resultSet.getString("id") +
                    " | ID do carro: " + resultSet.getString("carroid") +
                    " | Dono: " + resultSet.getString("nomedono") +
                    " | Entrada: " + resultSet.getString("entrada") +
                    " | Permanência: " + resultSet.getString("permanencia") +
                    " | Valor: " + resultSet.getString("valorpago");

                    minhaLista.addElement(mensagem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JList<String> lista = new JList<>(minhaLista);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(lista);
        scrollPane.setBounds(10, 10, 760, 500);
        lista.setLayoutOrientation(JList.VERTICAL);
        this.add(scrollPane);

        JButton btnVoltar = new JButton("Fechar Aba");
        btnVoltar.setBounds(10, 520, 760, 20);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(btnVoltar);

        this.setVisible(true);
    }
}
