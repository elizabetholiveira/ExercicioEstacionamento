package visual;

import service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualVerCarros extends JFrame {

    UsuarioService usuarioService = new UsuarioService();

    public VisualVerCarros(){

        this.setTitle("Todos os carros cadastrados");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        DefaultListModel<String> minhaLista = new DefaultListModel<>();

            String sql = "select * from tb_carro order by id ASC;";
            try{
                ResultSet resultSet = usuarioService.getStatement().executeQuery(sql);
                while (resultSet.next()){
                    String estado = null;
                    if (resultSet.getString("estado").contains("t")){
                        estado = "No estacionamento";
                    } else {
                        estado = "Fora do estacionamento";
                    }
                    String mensagem = "ID do carro: " + resultSet.getString("id") +
                            " | Dono: " + resultSet.getString("nomedono") +
                            " | Marca: " + resultSet.getString("marcacarro") +
                            " | Placa: " + resultSet.getString("placa") +
                            " | Status: " + estado +
                            " | Usuário: " + resultSet.getString("usuario");

                    minhaLista.addElement(mensagem);
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
