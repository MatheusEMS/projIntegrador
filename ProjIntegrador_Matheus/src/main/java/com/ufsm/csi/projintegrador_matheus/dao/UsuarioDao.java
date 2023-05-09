package com.ufsm.csi.projintegrador_matheus.dao;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.*;
import java.util.ArrayList;
import com.ufsm.csi.projintegrador_matheus.model.Usuario;

public class UsuarioDao {


    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;
    private String status;


    public Usuario getUsuario(String email) {
        Usuario usuario = null;

        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "SELECT * FROM usuario WHERE email = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1,email);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome").toUpperCase());
                usuario.setEmail(resultSet.getString("email"));
                //no momento pois no banco nao está encriptado
                usuario.setSenha(resultSet.getString("senha"));
                //usuario.setSenha(new BCryptPasswordEncoder().encode(resultSet.getString("senha")));
                usuario.setAdmin(resultSet.getString("admin"));

            }
        }catch (SQLException e){
            e.printStackTrace();
            usuario = null;
        }
        System.out.println(usuario.getAdmin());
        //return Adm(adm,new BCryptPasswordEncoder().encode(adm.getSenha),"USER");
        return usuario;

    }

    public Usuario UsuarioInsert(Usuario usuario){
        System.out.println("dao1");
        //String senhaEncrp = new BCryptPasswordEncoder().encode(usuario.getSenha());
        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "INSERT INTO usuario (nome,email,senha,admin)VALUES (?,?,?,'USER')";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1,usuario.getNome());
            this.preparedStatement.setString(2,usuario.getEmail());
            this.preparedStatement.setString(3,usuario.getSenha());
            preparedStatement.execute();

            System.out.println(this.sql);

        }catch (SQLException e)
        {
            e.printStackTrace();

        }
        System.out.println("dao2");
        return usuario;

    }

    public Usuario Editar(Usuario usuario){

        try(Connection connection = new ConexaoBD().getConexao()){
            connection.setAutoCommit(false);

            System.out.println("nomeConta:"+usuario.getNome());

            this.sql ="UPDATE usuario SET nome = ? WHERE id = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1,usuario.getNome());
            this.preparedStatement.setInt(2,usuario.getId());
            this.preparedStatement.executeUpdate();

            if (this.preparedStatement.getUpdateCount()>0){
                this.status="ok";
                connection.commit();
            }


        }catch (SQLException e){
            e.printStackTrace();
            this.status="error";
        }
        System.out.println("status"+status);
        return usuario;
    }

    public Usuario getUsuariobyId(int id) {
        Usuario usuario = null;

        try (Connection connection = new ConexaoBD().getConexao()){
            this.sql = "SELECT * FROM usuario WHERE id = ?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome").toUpperCase());
                usuario.setEmail(resultSet.getString("email"));
                //no momento pois no banco nao está encriptado
                usuario.setSenha(resultSet.getString("senha"));
                //usuario.setSenha(new BCryptPasswordEncoder().encode(resultSet.getString("senha")));
                usuario.setAdmin(resultSet.getString("admin"));

            }
        }catch (SQLException e){
            e.printStackTrace();
            usuario = null;
        }

        //return Adm(adm,new BCryptPasswordEncoder().encode(adm.getSenha),"USER");
        return usuario;

    }


    /*public Usuario excluir(int id) {

        try (Connection connection = new ConexaoBD().getConexao()) {
            this.sql = "DELETE FROM usuario WHERE id= ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.execute();
            this.status = "conta deletada";
        } catch (Exception e) {
            e.printStackTrace();
            this.status = "error";

        }

        System.out.println("status"+status);

        return null;
    }*/
}
