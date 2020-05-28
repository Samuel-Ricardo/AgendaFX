/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Model.PostIt;
import Model.Type;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class TypeDAO {
    
    private static Type type;
    private static ArrayList<Type> defaultTypes = new ArrayList<>();
    private Connection connection;
    
    public boolean Insert(Type type){
        
        if(connection == null){
         connect();
        }
        PreparedStatement statement = null;
        String sql = "INSERT INTO tipo (tipo, cor, detalhes_de_cores) VALUES (?,?,?);";
        
        try {
            
            statement = connection.prepareStatement(sql);
            
            statement.setString(0,type.getName());
            statement.setString(1, type.getColor());
            statement.setString(2, type.getColorDetails());
            
            statement.execute();
            
            return true;
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
     public boolean update(Type type){
        
          if(connection == null){
         connect();
        }
        PreparedStatement statement = null;
        String sql = "UPDATE tipo SET tipo = ?, cor = ?, detalhes_de_cores = ? WHERE id_tipo = ?;";
        
          
        try {
            
            statement = connection.prepareStatement(sql);
            
            statement.setString(0,type.getName());
            statement.setString(1, type.getColor());
            statement.setString(2, type.getColorDetails());
            statement.setInt(4, type.getId().intValue());
            
            statement.execute();
            
            return true;
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
     }

    private void connect() {
        connection = ConnectionFactory.getConnection();
    }
    
}
