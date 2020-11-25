/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marineExperience.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import marineExperience.interfaces.IUsuario;
import marineExperience.model.Usuario;

/**
 *
 * @author jonat
 */
public class UsuarioDao implements IUsuario{

    private DataSource dataSource;

    public UsuarioDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (NOME, SENHA) VALUES (?,md5(?))";
        try ( PreparedStatement pstm = dataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getSenha());
            pstm.execute();
            System.out.println("salvo com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Usuario findUser(String nome, String senha) {
        try {
            String sql = "SELECT * FROM USUARIO WHERE NOME = ? AND SENHA = md5(?)";
            Usuario usuario = null;
            try ( PreparedStatement pstm = dataSource.getConnection().prepareStatement(sql)) {
                pstm.setString(1, nome);
                pstm.setString(2, senha);
                pstm.execute();
                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        usuario = new Usuario(rst.getInt(1), rst.getString(2), rst.getString(3));
                    }
                }
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> users = new ArrayList<>();
        try {
            String sql = "SELECT id, Nome FROM USUARIO";
            try ( PreparedStatement pstm = dataSource.getConnection().prepareStatement(sql)) {
                pstm.execute();
                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        users.add(new Usuario(rst.getInt(1),rst.getString(2)));
                    }
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(int id, String senha, String nome) {
        try ( PreparedStatement stm = dataSource.getConnection()
                .prepareStatement("UPDATE USUARIO U SET U.SENHA = md5(?), U.Nome = ? WHERE id = ?")) {
            stm.setString(1, senha);
            stm.setString(2, nome);
            stm.setInt(3, id);
            stm.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
