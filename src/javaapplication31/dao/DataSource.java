/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jonat
 */
public class DataSource {
    private String host;
    private int porta;
    private String database;
    private String user;
    private String senha;
    
    private Connection connection;
    
    public DataSource(){
        try{
            host="localhost";
            porta=3306;
            database="trabalho";
            user="root";
            senha="";
            String url="jdbc:mysql://"+host+":"+porta+"/"+database;
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url, user, senha);
            System.out.println("Sucesso da conexão!");
            String db = connection.getCatalog();
            System.out.println("nome do banco=" + db);
        }catch(SQLException ex){
            System.out.println("Erro na conexão! "+ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public void closeDataSource(){
        try{
            connection.close();
        }catch(SQLException ex){
            System.out.println("Erro ao desconectar! "+ex.getMessage());
        }
    }
}
