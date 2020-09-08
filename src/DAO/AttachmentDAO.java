/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template attachment, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Factory.AttachmentFactory;
import Factory.UserFactory;
import JDBC.ConnectionFactory;
import Model.Attachment;
import Model.User;
import com.mysql.jdbc.Connection;
import java.io.Attachment;
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

    private static Attachment attachment;
    
    private Connection connection;

    public boolean insert(Attachment attachment) {
        
        connect();
         
        PreparedStatement statement = null;
        String sql = "INSERT INTO tipo (tipo, cor, detalhes_de_cores, importancia, id_usuario_tipo) VALUES (?,?,?,?,?);";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, attachment.getName());
            statement.setString(2, attachment.getPrimaryColor());
            statement.setString(3, attachment.getSecondaryColor());
            statement.setInt(4, attachment.getImportance().intValue());
            statement.setInt(5, attachment.getUser().getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    public boolean insertAll(ArrayList<Attachment> attachments) {
  
        connect();

        PreparedStatement statement = null;
        String sql = "INSERT INTO tipo (tipo, cor, detalhes_de_cores, importancia, id_usuario_tipo) VALUES (?,?,?,?,?);";

       
            
        try {

             for (Attachment attachment : attachments) {
            
            statement = connection.prepareStatement(sql);

            statement.setString(1, attachment.getName());
            statement.setString(2, attachment.getPrimaryColor());
            statement.setString(3, attachment.getSecondaryColor());
            statement.setInt(4, attachment.getImportance().intValue());
            statement.setInt(5, attachment.getUser().getId().intValue());

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

    public boolean update(Attachment attachment) {

        connect();
         
        PreparedStatement statement = null;
        String sql = "UPDATE tipo SET tipo = ?, cor = ?, detalhes_de_cores = ?, importancia = ?, id_usuario_tipo = ? WHERE id_tipo = ?;";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(0, attachment.getName());
            statement.setString(1, attachment.getSecondaryColor());
            statement.setString(2, attachment.getPrimaryColor());
            statement.setInt(3, attachment.getImportance().intValue());
            statement.setInt(4, attachment.getUser().getId().intValue());
            statement.setInt(5, attachment.getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar : " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public boolean delet(Attachment attachment) {

        connect();

        PreparedStatement statement = null;
        String sql = "DELETE FROM attachment WHERE id_attachment = ?;";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(0, attachment.getId().intValue());

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

    public List<Attachment> selectAllFromUser(int id) {

        connect();
     
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Attachment> attachments = new ArrayList<>();
        String sql = "SELECT * FROM attachments_from_user WHERE id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            result = statement.executeQuery();

            while (result.next()) {

                Attachment attachment = AttachmentFactory.genereteAttachment(result);
                
                attachments.add(attachment);
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return attachments;
    }
    
    public boolean exist(Attachment attachment) {  
        
        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM attachments_from_user WHERE id_tipo = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, attachment.getId().intValue());    
            
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
    
    public ArrayList<Boolean> exist(ArrayList<Attachment> attachments) {  
        
        ArrayList<Boolean> exist = new ArrayList<>();
        
        for(Attachment attachment: attachments){
            
         if(attachment.getId() == null){
            exist.clear();
            exist.add(false);
            return exist;
         }
         
        }

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM attachments_from_user WHERE id_tipo = ?;";
        

       
        
        try {

         for (Attachment attachment : attachments) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, attachment.getId().intValue());    
            
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

    public boolean existByName(Attachment attachment) {

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM attachments_from_user WHERE tipo = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, attachment.getName());     
            
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
    
    public ArrayList<Boolean> existByName(ArrayList<Attachment> attachments) {  
        
        ArrayList<Boolean> exist = new ArrayList<>();
        
        for(Attachment attachment: attachments){
            
         if(attachment.getId() == null){
            exist.clear();
            exist.add(false);
            return exist;
         }
         
        }

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM attachments_from_user WHERE tipo = ?;";
        

       
        
        try {

         for (Attachment attachment : attachments) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, attachment.getName());    
            
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
