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
import Model.Notification;
import Model.PostIt;
import Model.User;
import com.mysql.jdbc.Connection;
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
    private AttachmentFactory attachmentFactory = new AttachmentFactory();

    public AttachmentDAO() {
        
        AttachmentFactory AttachmentFactory = new AttachmentFactory();
    }
    
    public AttachmentDAO(AttachmentFactory attachmentFactory) {
        
        this.attachmentFactory = attachmentFactory;
    }

    public boolean insert(Attachment attachment) {
        
        connect();
         
        PreparedStatement statement = null;
        String sql = "INSERT INTO tipo (file_name, file_way, file_bytes, file_postIt_id, file_notificaton_id, file_size) VALUES (?,?,?,?,?,?);";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, attachment.getName());
            statement.setString(2, attachment.getAbsolutPath());
            statement.setBytes(3, attachment.getArrayBytes());
            statement.setInt(4, attachment.getPostIt().getId());
            statement.setInt(5, attachment.getNotification().getId());
            statement.setString(6, attachment.getSize());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
//    public boolean insertAll(ArrayList<Attachment> attachments) {
//  
//        connect();
//
//        PreparedStatement statement = null;
//        String sql = "INSERT INTO tipo (tipo, cor, detalhes_de_cores, importancia, id_usuario_tipo) VALUES (?,?,?,?,?);";
//
//       
//            
//        try {
//
//             for (Attachment attachment : attachments) {
//            
//            statement = connection.prepareStatement(sql);
//
//            statement.setString(1, attachment.getName());
//            statement.setString(2, attachment.getPrimaryColor());
//            statement.setString(3, attachment.getSecondaryColor());
//            statement.setInt(4, attachment.getImportance().intValue());
//            statement.setInt(5, attachment.getUser().getId().intValue());
//
//            statement.execute();
//            
//             }
//            return true;
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
//            return false;
//        } finally {
//            ConnectionFactory.closeConnection(connection, statement);
//        }
//        
//    }
//        

    public boolean update(Attachment attachment) {

        connect();
         
        PreparedStatement statement = null;
        String sql = "UPDATE tipo SET file_name = ? , file_way = ? , file_bytes = ? , file_postIt_id = ? , file_notificaton_id = ? , file_size = ? WHERE id_file = ?;";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, attachment.getName());
            statement.setString(2, attachment.getAbsolutPath());
            statement.setBytes(3, attachment.getArrayBytes());
            statement.setInt(4, attachment.getPostIt().getId());
            statement.setInt(5, attachment.getNotification().getId());
            statement.setString(6, attachment.getSize());
            statement.setInt(7, attachment.getId().intValue());

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
        String sql = "DELETE FROM attachment WHERE id_file = ?;";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(0, attachment.getId());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AttachmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Attachment> selectAllFromPostIt(PostIt postIt) {

        connect();
     
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Attachment> attachments = new ArrayList<>();
        String sql = "SELECT * FROM file_from_postIt WHERE file_postIt_id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, postIt.getId());

            result = statement.executeQuery();

            while (result.next()) {

                Attachment attachment = attachmentFactory.genereteAttachment(result);
                
                attachments.add(attachment);
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return attachments;
    }
    public List<Attachment> selectAllFromNotification(Notification notification) {

        connect();
     
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Attachment> attachments = new ArrayList<>();
        String sql = "SELECT * FROM file_from_notification WHERE id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, notification.getId());

            result = statement.executeQuery();

            while (result.next()) {

                Attachment attachment = attachmentFactory.genereteAttachment(result);
                
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
        String sql = "SELECT * FROM file WHERE id_file = ?;";
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
        String sql = "SELECT * FROM file WHERE id_file = ?;";
        

       
        
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
        String sql = "SELECT * FROM file WHERE file_name = ?;";
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
        String sql = "SELECT * FROM file WHERE file_name = ?;";
        

       
        
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
    
    public boolean existByPath(Attachment attachment) {

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM file WHERE file_way = ?;";
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
    
    public ArrayList<Boolean> existByPath(ArrayList<Attachment> attachments) {  
        
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
        String sql = "SELECT * FROM file WHERE file_way = ?;";
        

       
        
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
