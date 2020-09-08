/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Factory.FileFactory;
import Factory.UserFactory;
import JDBC.ConnectionFactory;
import Model.File;
import Model.User;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class AttachmentDAO {

    private static File file;
    
    private Connection connection;

    public boolean insert(File file) {
        
        connect();
         
        PreparedStatement statement = null;
        String sql = "INSERT INTO tipo (tipo, cor, detalhes_de_cores, importancia, id_usuario_tipo) VALUES (?,?,?,?,?);";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, file.getName());
            statement.setString(2, file.getPrimaryColor());
            statement.setString(3, file.getSecondaryColor());
            statement.setInt(4, file.getImportance().intValue());
            statement.setInt(5, file.getUser().getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    public boolean insertAll(ArrayList<File> files) {
  
        connect();

        PreparedStatement statement = null;
        String sql = "INSERT INTO tipo (tipo, cor, detalhes_de_cores, importancia, id_usuario_tipo) VALUES (?,?,?,?,?);";

       
            
        try {

             for (File file : files) {
            
            statement = connection.prepareStatement(sql);

            statement.setString(1, file.getName());
            statement.setString(2, file.getPrimaryColor());
            statement.setString(3, file.getSecondaryColor());
            statement.setInt(4, file.getImportance().intValue());
            statement.setInt(5, file.getUser().getId().intValue());

            statement.execute();
            
             }
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
        
    }

    public boolean update(File file) {

        connect();
         
        PreparedStatement statement = null;
        String sql = "UPDATE tipo SET tipo = ?, cor = ?, detalhes_de_cores = ?, importancia = ?, id_usuario_tipo = ? WHERE id_tipo = ?;";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(0, file.getName());
            statement.setString(1, file.getSecondaryColor());
            statement.setString(2, file.getPrimaryColor());
            statement.setInt(3, file.getImportance().intValue());
            statement.setInt(4, file.getUser().getId().intValue());
            statement.setInt(5, file.getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar : " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public boolean delet(File file) {

        connect();

        PreparedStatement statement = null;
        String sql = "DELETE FROM file WHERE id_file = ?;";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(0, file.getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostItDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<File> selectAllFromUser(int id) {

        connect();
     
        PreparedStatement statement = null;
        ResultSet result = null;
        List<File> files = new ArrayList<>();
        String sql = "SELECT * FROM files_from_user WHERE id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            result = statement.executeQuery();

            while (result.next()) {

                File file = FileFactory.genereteFile(result);
                
                files.add(file);
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return files;
    }
    
    public boolean exist(File file) {  
        
        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM files_from_user WHERE id_tipo = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, file.getId().intValue());    
            
            result = statement.executeQuery();           
            
            exist = result.next();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex); 
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement); 
        }

        return exist;
        
    }
    
    public ArrayList<Boolean> exist(ArrayList<File> files) {  
        
        ArrayList<Boolean> exist = new ArrayList<>();
        
        for(File file: files){
            
         if(file.getId() == null){
            exist.clear();
            exist.add(false);
            return exist;
         }
         
        }

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM files_from_user WHERE id_tipo = ?;";
        

       
        
        try {

         for (File file : files) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, file.getId().intValue());    
            
            result = statement.executeQuery();           
            
            exist.add(result.next());
        }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);
            exist.clear();
            exist.add(false);
            return exist;
        } finally {
            ConnectionFactory.closeConnection(connection, statement); 
        }

        return exist;
    }

    public boolean existByName(File file) {

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM files_from_user WHERE tipo = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, file.getName());     
            
            result = statement.executeQuery();           
            
            exist = result.next();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex); 
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement); 
        }

        return exist;
        
    }
    
    public ArrayList<Boolean> existByName(ArrayList<File> files) {  
        
        ArrayList<Boolean> exist = new ArrayList<>();
        
        for(File file: files){
            
         if(file.getId() == null){
            exist.clear();
            exist.add(false);
            return exist;
         }
         
        }

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM files_from_user WHERE tipo = ?;";
        

       
        
        try {

         for (File file : files) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, file.getName());    
            
            result = statement.executeQuery();           
            
            exist.add(result.next());
        }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);
            exist.clear();
            exist.add(false);
            return exist;
        } finally {
            ConnectionFactory.closeConnection(connection, statement); 
        }

        return exist;
    }

    private void connect() {
        connection = ConnectionFactory.getConnection();
    }

}
