package com.ufsm.csi.projintegrador_matheus.dao;


import com.ufsm.csi.projintegrador_matheus.model.Jogos;
import com.ufsm.csi.projintegrador_matheus.model.Saves;
import com.ufsm.csi.projintegrador_matheus.model.SavesReq;

import java.sql.*;
import java.util.ArrayList;

public class SavesDao {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;
    private String status;

    public ArrayList<Saves> LerSaves(int id,String console) {
        ArrayList<Saves>ListaSaves = new ArrayList<>();

        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "select * from arquivos_save,console,jogos\n" +
                    "where jogos.id_jogo = arquivos_save.idJogo\n" +
                    "and console.id_console = arquivos_save.idconsole\n" +
                    "and Jogos.id_jogo = ?\n" +
                    "and console.nome_console = ?\n" +
                    "and habilitado = 'true'";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1,id);
            this.preparedStatement.setString(2,console);
            this.resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next()){
                Saves saves = new Saves();
                saves.setId_save(resultSet.getInt("id_save"));
                saves.setResumo(resultSet.getString("resumo").toUpperCase());
                saves.setDescricao(resultSet.getString("descricao").toUpperCase());
                saves.setEmuladores(resultSet.getString("emuladores").toUpperCase());
                saves.setArquivo(" ");
                //saves.setArquivo(resultSet.getString("arquivo").toUpperCase());
                saves.setHabilitado(resultSet.getString("habilitado").toUpperCase());
                saves.setData_Hora(resultSet.getDate("data_Hora"));
                saves.setIdJogo(resultSet.getInt("idjogo"));
                saves.setIdConsole(resultSet.getInt("idConsole"));
                saves.setIdUsuario(resultSet.getInt("idusuario"));

                System.out.println("id:"+ saves.getId_save()+" descricao: "+saves.getDescricao());

                ListaSaves.add(saves);
            }
        }catch (
                SQLException e){
            e.printStackTrace();
            System.out.println("erro no dao");
        }



        return ListaSaves;

    }



    public Saves LerUmSave(int id) {
        Saves save = null;

        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "select * from arquivos_save\n" +
                    "where id_save = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next()){
                save = new Saves();
                save.setId_save(resultSet.getInt("id_save"));
                save.setResumo(resultSet.getString("resumo").toUpperCase());
                save.setDescricao(resultSet.getString("descricao").toUpperCase());
                save.setEmuladores(resultSet.getString("emuladores").toUpperCase());
                save.setArquivo(" ");
                //saves.setArquivo(resultSet.getString("arquivo").toUpperCase());
                save.setHabilitado(resultSet.getString("habilitado").toUpperCase());
                save.setData_Hora(resultSet.getDate("data_Hora"));
                save.setIdJogo(resultSet.getInt("idjogo"));
                save.setIdConsole(resultSet.getInt("idConsole"));
                save.setIdUsuario(resultSet.getInt("idusuario"));

            }
        }catch (
                SQLException e){
            e.printStackTrace();
            System.out.println("erro no dao");
        }



        return save;

    }


    public ArrayList<Saves> LerSavesHome() {
        ArrayList<Saves>ListaSaves = new ArrayList<>();

        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "SELECT * FROM arquivos_save\n" +
                    "WHERE habilitado = 'true'\n" +
                    "ORDER BY data_hora DESC\n" +
                    "LIMIT 3\n";

            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while (resultSet.next()){
                Saves saves = new Saves();
                saves.setId_save(resultSet.getInt("id_save"));
                saves.setResumo(resultSet.getString("resumo").toUpperCase());
                saves.setDescricao(resultSet.getString("descricao").toUpperCase());
                saves.setEmuladores(resultSet.getString("emuladores").toUpperCase());
                saves.setArquivo(" ");
                //saves.setArquivo(resultSet.getString("arquivo").toUpperCase());
                saves.setHabilitado(resultSet.getString("habilitado").toUpperCase());
                saves.setData_Hora(resultSet.getDate("data_Hora"));
                saves.setIdJogo(resultSet.getInt("idjogo"));
                saves.setIdConsole(resultSet.getInt("idConsole"));
                saves.setIdUsuario(resultSet.getInt("idusuario"));

                System.out.println("id:"+ saves.getId_save()+" descricao: "+saves.getDescricao());

                ListaSaves.add(saves);
            }
        }catch (
                SQLException e){
            e.printStackTrace();
            System.out.println("erro no dao");
        }



        return ListaSaves;

    }

    public Boolean cadastrarSave(Saves saves){
        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "insert into arquivos_save(descricao,resumo,emuladores,arquivo,requisicao_nomejogo,habilitado,data_Hora,idUsuario,idJogo,idConsole)\n" +
                    "VALUES (?,?,\n" +
                    "?,?,?,'false',CURRENT_TIMESTAMP,?,?,?);";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1,saves.getDescricao());
            this.preparedStatement.setString(2,saves.getResumo());
            this.preparedStatement.setString(3,saves.getEmuladores());
            this.preparedStatement.setString(4,saves.getArquivo());
            this.preparedStatement.setString(5,saves.getRequisicao_nomeJogo());
            this.preparedStatement.setInt(6,saves.getIdUsuario());
            this.preparedStatement.setInt(7,saves.getIdJogo());
            this.preparedStatement.setInt(8,saves.getIdConsole());
            preparedStatement.execute();

            System.out.println(this.sql);

        }catch (SQLException e)
        {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public ArrayList<SavesReq> LerReqSaves() {
        ArrayList<SavesReq>ListaSaves = new ArrayList<>();

        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "SELECT arquivos_save.*, console.nome_console, jogos.nome_jogo,usuario.nome\n" +
                    "FROM arquivos_save,console,jogos,usuario\n" +
                    "WHERE habilitado = 'false'\n" +
                    "AND arquivos_save.idjogo = jogos.id_jogo\n" +
                    "AND arquivos_save.idconsole = console.id_console\n" +
                    "AND arquivos_save.idusuario = usuario.id\n" +
                    "ORDER BY data_hora ASC";

            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while (resultSet.next()){
                SavesReq saves = new SavesReq();
                saves.setId_save(resultSet.getInt("id_save"));
                saves.setResumo(resultSet.getString("resumo").toUpperCase());
                saves.setDescricao(resultSet.getString("descricao").toUpperCase());
                saves.setEmuladores(resultSet.getString("emuladores").toUpperCase());
                saves.setArquivo(" ");
                //saves.setArquivo(resultSet.getString("arquivo").toUpperCase());
                saves.setHabilitado(resultSet.getString("habilitado").toUpperCase());
                saves.setData_Hora(resultSet.getDate("data_Hora"));
                saves.setIdJogo(resultSet.getInt("idjogo"));
                saves.setIdConsole(resultSet.getInt("idConsole"));
                saves.setIdUsuario(resultSet.getInt("idusuario"));
                saves.setRequisicao_nomeJogo(resultSet.getString("requisicao_nomejogo"));
                saves.setNomeConsole(resultSet.getString("nome_console"));
                saves.setNomeJogo(resultSet.getString("nome_jogo"));
                saves.setNomeUsuario(resultSet.getString("nome"));

                System.out.println("id:"+ saves.getId_save()+" descricao: "+saves.getDescricao());

                ListaSaves.add(saves);
            }
        }catch (
                SQLException e){
            e.printStackTrace();
            System.out.println("erro no dao");
        }
        return ListaSaves;

    }

    public Boolean DeletarReqSaves(int id){
        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "Delete from arquivos_save where id_save = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1,id);
            preparedStatement.execute();

            System.out.println(this.sql);

        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
