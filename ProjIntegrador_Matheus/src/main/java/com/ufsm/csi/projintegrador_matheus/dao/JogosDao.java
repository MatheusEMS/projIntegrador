package com.ufsm.csi.projintegrador_matheus.dao;

import com.ufsm.csi.projintegrador_matheus.model.Jogos;
import com.ufsm.csi.projintegrador_matheus.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class JogosDao {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;
    private String status;


    public ArrayList<Jogos> LerJogos(String console) {
        ArrayList<Jogos>ListaJogos = new ArrayList<>();

        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "select id_jogo,nome_jogo from jogos,console,console_jogo \n" +
                    "where jogos.id_jogo = console_jogo.idJogo\n" +
                    "and console.id_console = console_jogo.idConsole\n" +
                    "and console.nome_console = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1,console);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                Jogos jogos = new Jogos();
                jogos.setIdJogo(resultSet.getInt("id_jogo"));
                jogos.setNomeJogo(resultSet.getString("nome_jogo").toUpperCase());
                //jogos.setCapa(" ");

                System.out.println("id:"+ jogos.getIdJogo()+" nome: "+jogos.getNomeJogo());

                ListaJogos.add(jogos);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("erro no dao");
        }

        //return Adm(adm,new BCryptPasswordEncoder().encode(adm.getSenha),"USER");

        return ListaJogos;

    }


    public Jogos PegarNome(int idJogo) {
        Jogos jogos = null;

        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "select * from jogos\n" +
                    "where id_jogo = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1,idJogo);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                jogos = new Jogos();

                jogos.setIdJogo(resultSet.getInt("id_jogo"));
                jogos.setNomeJogo(resultSet.getString("nome_jogo").toUpperCase());
                jogos.setCapa(" ");

                //System.out.println(" nome: "+nome);

            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("erro no dao");
        }

        //return Adm(adm,new BCryptPasswordEncoder().encode(adm.getSenha),"USER");

        return jogos;

    }
}
