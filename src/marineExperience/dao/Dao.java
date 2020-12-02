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
import marineExperience.interfaces.IAis;
import marineExperience.interfaces.IUsuario;
import marineExperience.model.Ais;
import marineExperience.model.Usuario;

/**
 *
 * @author jonat
 * @param <T>
 */
public class Dao<T> implements IUsuario, IAis{

    private final DataSource dataSource;

    public Dao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void salvar(String nome, String senha) {
        String sql = "INSERT INTO USUARIO (NOME, SENHA) VALUES (?,md5(?))";
        try ( PreparedStatement pstm = dataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, nome);
            pstm.setString(2, senha);
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
    public List<T> findByDate(String dataInicial, String dataFinal) {
        List<T> aiss = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Ais WHERE dt >= ? AND dt <= ?";

            try ( PreparedStatement pstm = dataSource.getConnection().prepareStatement(sql)) {
                pstm.setString(1, dataInicial);
                pstm.setString(2, dataFinal);
                pstm.execute();
                
                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        aiss.add((T) new Ais(rst.getInt(1),rst.getString(2),rst.getDate(3)));
                    }
                }
            }
            return aiss;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
