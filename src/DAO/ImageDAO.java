/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template backupImage, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Factory.ImageFactory;
import JDBC.ConnectionFactory;
import Model.BackupImage;
import Model.Notification;
import Model.PostIt;
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
public class ImageDAO {

    private static BackupImage backupImage;
    
    private Connection connection;
    private BackupImage backupImageFactory = new BackupImage();

    public ImageDAO() {
        
        BackupImage BackupImage = new BackupImage();
    }
    
    public ImageDAO(BackupImage backupImageFactory) {
        
        this.backupImageFactory = backupImageFactory;
    }

    public boolean insert(BackupImage backupImage) {
        
        connect();
         
        PreparedStatement statement = null;
        String sql = "INSERT INTO tipo (file_name, file_way, file_bytes, file_postIt_id, file_notificaton_id, file_size) VALUES (?,?,?,?,?,?);";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, backupImage.getName());
            statement.setString(2, backupImage.getAbsolutPath());
            statement.setBytes(3, backupImage.getArrayBytes());
            statement.setInt(4, backupImage.getPostIt().getId());
            statement.setInt(5, backupImage.getNotification().getId());
            statement.setString(6, backupImage.getSize());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
//    public boolean insertAll(ArrayList<BackupImage> backupImages) {
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
//             for (BackupImage backupImage : backupImages) {
//            
//            statement = connection.prepareStatement(sql);
//
//            statement.setString(1, backupImage.getName());
//            statement.setString(2, backupImage.getPrimaryColor());
//            statement.setString(3, backupImage.getSecondaryColor());
//            statement.setInt(4, backupImage.getImportance().intValue());
//            statement.setInt(5, backupImage.getUser().getId().intValue());
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

    public boolean update(BackupImage backupImage) {

        connect();
         
        PreparedStatement statement = null;
        String sql = "UPDATE tipo SET file_name = ? , file_way = ? , file_bytes = ? , file_postIt_id = ? , file_notificaton_id = ? , file_size = ? WHERE id_file = ?;";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, backupImage.getName());
            statement.setString(2, backupImage.getAbsolutPath());
            statement.setBytes(3, backupImage.getArrayBytes());
            statement.setInt(4, backupImage.getPostIt().getId());
            statement.setInt(5, backupImage.getNotification().getId());
            statement.setString(6, backupImage.getSize());
            statement.setInt(7, backupImage.getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar : " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public boolean delet(BackupImage backupImage) {

        connect();

        PreparedStatement statement = null;
        String sql = "DELETE FROM backupImage WHERE id_file = ?;";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(0, backupImage.getId());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<BackupImage> selectAllFromPostIt(PostIt postIt) {

        connect();
     
        PreparedStatement statement = null;
        ResultSet result = null;
        List<BackupImage> backupImages = new ArrayList<>();
        String sql = "SELECT * FROM file_from_postIt WHERE file_postIt_id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, postIt.getId());

            result = statement.executeQuery();

            while (result.next()) {

                BackupImage backupImage = BackupImage(result);
                
                backupImages.add(backupImage);
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return backupImages;
    }
    public List<BackupImage> selectAllFromNotification(Notification notification) {

        connect();
     
        PreparedStatement statement = null;
        ResultSet result = null;
        List<BackupImage> backupImages = new ArrayList<>();
        String sql = "SELECT * FROM file_from_notification WHERE id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, notification.getId());

            result = statement.executeQuery();

            while (result.next()) {

                BackupImage backupImage = BackupImage(result);
                
                backupImages.add(backupImage);
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return backupImages;
    }
    
    public boolean exist(BackupImage backupImage) {  
        
        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM file WHERE id_file = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, backupImage.getId().intValue());    
            
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
    
    public ArrayList<Boolean> exist(ArrayList<BackupImage> backupImages) {  
        
        ArrayList<Boolean> exist = new ArrayList<>();
        
        for(BackupImage backupImage: backupImages){
            
         if(backupImage.getId() == null){
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

         for (BackupImage backupImage : backupImages) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, backupImage.getId().intValue());    
            
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

    public boolean existByName(BackupImage backupImage) {

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM file WHERE file_name = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, backupImage.getName());     
            
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
    
    public ArrayList<Boolean> existByName(ArrayList<BackupImage> backupImages) {  
        
        ArrayList<Boolean> exist = new ArrayList<>();
        
        for(BackupImage backupImage: backupImages){
            
         if(backupImage.getId() == null){
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

         for (BackupImage backupImage : backupImages) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, backupImage.getName());    
            
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
    
    public boolean existByPath(BackupImage backupImage) {

        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM file WHERE file_way = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, backupImage.getName());     
            
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
    
    public ArrayList<Boolean> existByPath(ArrayList<BackupImage> backupImages) {  
        
        ArrayList<Boolean> exist = new ArrayList<>();
        
        for(BackupImage backupImage: backupImages){
            
         if(backupImage.getId() == null){
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

         for (BackupImage backupImage : backupImages) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, backupImage.getName());    
            
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
