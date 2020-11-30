/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marineExperience.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import marineExperience.interfaces.IAis;
import marineExperience.model.Ais;

/**
 *
 * @author jonat
 */
public class AisDao implements IAis{

    private DataSource dataSource;

    public AisDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public Ais findUser(int id) {
        try {
            String sql = "SELECT * FROM Ais WHERE id = ?";
            Ais ais = null;
            try ( PreparedStatement pstm = dataSource.getConnection().prepareStatement(sql)) {
                pstm.setInt(1, id);
                pstm.execute();
                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        ais = new Ais(rst.getInt(1), rst.getString(2), rst.getDate(3));
                    }
                }
            }
            return ais;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<Ais> findAll() {
//        List<Ais> aiss = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM Ais";
//
//            try ( PreparedStatement pstm = dataSource.getConnection().prepareStatement(sql)) {
//                pstm.execute();
//                
//                try ( ResultSet rst = pstm.getResultSet()) {
//                    while (rst.next()) {
//                        aiss.add(new Ais(rst.getInt(1),rst.getString(2),rst.getDate(3)));
//                    }
//                }
//            }
//            return aiss;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    
    @Override
    public List<Ais> findByDate(String dataInicial, String dataFinal) {
        List<Ais> aiss = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Ais WHERE dt >= ? AND dt <= ?";

            try ( PreparedStatement pstm = dataSource.getConnection().prepareStatement(sql)) {
                pstm.setString(1, dataInicial);
                pstm.setString(2, dataFinal);
                pstm.execute();
                
                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        aiss.add(new Ais(rst.getInt(1),rst.getString(2),rst.getDate(3)));
                    }
                }
            }
            return aiss;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
