package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static connection.Conexao.getConnection;

public class UsuarioService {

    private Statement statement;

    public Statement getStatement() {
        return statement;
    }

    public UsuarioService(){
        try {
            statement = getConnection().createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

  // Ver os seguintes dados:
    // - Quais carros estão no estacionamento
    // - O tempo de permanência de cada
    // - O valor a ser pago pela permanência
    // - Dono do carro
    public void verCarrosAtivos(){
        String sql = "select * from tb_estacionamento full join tb_carro on tb_estacionamento.carroid = tb_carro.id;";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println("Placa do carro: " + resultSet.getString("placa") +
                        " | Tempo: " + resultSet.getFloat("permanencia") +
                        " | Valor: " + resultSet.getFloat("valorpago") +
                        " | Dono: " + resultSet.getString("nomedono") +
                        " | Usuário: " + resultSet.getString("usuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualizar tempo de permanência
    public void atualizarPermanencia(long id, int permanencia){
        String sql = "update tb_estacionamento set permanencia = '" + permanencia + "' where id = '" + id + "';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Calcular tempo de permanência
    public int calcularPermanencia(long id){
        String sql = "select * from tb_estacionamento where id = '" + id + "';";
        int permanencia = 0;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            int horaEntra = 0;
            int horaSai = 0;
            while (resultSet.next()){
                horaEntra = Integer.parseInt(resultSet.getString("entrada"));
                horaSai = Integer.parseInt(resultSet.getString("saida"));
            }

            int horas = (horaSai - (horaSai % 100)) - (horaEntra - (horaEntra % 100));
            int minutos = (horaSai % 100) - (horaEntra % 100);
            if (horas == 1 && minutos > 0){
                permanencia = 60 - minutos;
            } else if (horas >= 1 && minutos == 0){
                permanencia = horas * 100;
            } else if (horas >= 1 && minutos > 0){
                permanencia = ((horas - 1) * 100) + (60 - minutos);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return permanencia;
    }

    // Atualizar o valor a ser pago
    public void atualizarValor(long id, int permanencia){
        String sql = "select * from tb_estacionamento;";
        try {
            int valor = 10;
            if (permanencia > 100 && permanencia < 1200){
                System.out.println("permanencia inicial: " + permanencia);
                int subtracao = permanencia;
                while (subtracao > 100) {
                    valor += 2;
                    subtracao -= 30;
                    System.out.println("calculo: " + subtracao);
                }
            }
            if (permanencia >= 1200){
                valor = 90;
            }
            String sql1 = "update tb_estacionamento set valorpago = '" + valor + "' where id = '" + id + "';";
            statement.executeUpdate(sql1);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Remover carros do estacionamento
    public void carroSaiu(long id, long carroid, int horaSaiu){
        String sql = "update tb_carro set estado = 'false' where id = '" + carroid + "';";
        String sql2 = "update tb_estacionamento set saida = '" + horaSaiu + "' where id = '" + id + "';";
        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Criar carro
    public void criarCarro(String nome, String marca, String placa, String usuario){
        String sql = "insert into tb_carro (nomedono, marcacarro, placa, usuario, estado) values ('" + nome + "', '" +
                marca + "', '" + placa + "', '" + usuario + "', 'false');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Adicionar carro no estacionamento
    public void adicionarCarro(long id, String horaEntra){
        String sql = "insert into tb_estacionamento (carroid, entrada) values ('" + id + "', '" + horaEntra + "');";
        String sql1 = "update tb_carro set estado = true where id = " + id + ";";
        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql1);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Conferir se ID do carro existe
    public boolean conferirCarro(long id){
        boolean existe = false;
        String sql = "select * from tb_carro where id = " + id + ";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

    // Pegar ID do carro no estacionamento

    public long carroEstacionado (long id){
        long idcarro = 0;
        String sql = "select carroid from tb_estacionamento where id = " + id + ";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                idcarro = resultSet.getInt("carroid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idcarro;
    }

    // Conferir se ID da estadia existe
    public boolean conferirEstacionamento(long id){
        boolean existe = false;
        String sql = "select * from tb_estacionamento where id = " + id + ";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

    // Conversor de String para Long
    public long converterStringLong (String dado){
        long dadoFim = -1;
        if (dado.matches("[0-9]+")){
            dadoFim = Long.parseLong(dado);
        }
        return  dadoFim;
    }

    // Conversor de String para int no modelo hora
    public int converterStringIntHora (String dado){
        int dadoFim = -1;
        if (dado.matches("[0-9]+") && (dado.length() == 3 || dado.length() == 4)){
            dadoFim = Integer.parseInt(dado);
            if (dadoFim % 100 >= 60){
                dadoFim = -1;
            }
        }
        return dadoFim;
    }
}
