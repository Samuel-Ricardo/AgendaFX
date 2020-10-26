/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template backupImage, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Factory.BackupImageFactory;
import JDBC.ConnectionFactory;
import Model.BackupImage;
import Model.Notification;
import Model.PostIt;
import Services.Dialoger;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class ImageDAO {

    private static BackupImage backupImage;
    
    private Connection connection;
    private BackupImageFactory backupImageFactory; 
    private Dialoger dialoger;

    public ImageDAO() {
        
      backupImageFactory = new BackupImageFactory();
      dialoger = new Dialoger();
    }
    
    public ImageDAO(BackupImageFactory backupImageFactory) {
        
        this.backupImageFactory = backupImageFactory;
        dialoger = new Dialoger();
    }

    public boolean insert(BackupImage backupImage) {
        
        connect();
         
        PreparedStatement statement = null;
        String sql = "INSERT INTO image (image_name, image_way, image_bytes, image_postIt_id, image_notification_id, image_user_id,image_size) VALUES (?,?,?,?,?,?,?);";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, backupImage.getImage().getFile().getName());
            statement.setString(2, backupImage.getImage().getFile().getAbsolutePath());
            statement.setBinaryStream(3, backupImage.getImage().getInputStream());
            if(backupImage.getPostIt() != null){
             statement.setInt(4, backupImage.getPostIt().getId());
            }else{
             statement.setInt(4, 0);
            }
            if(backupImage.getNotification() != null){
             statement.setInt(5, backupImage.getNotification().getId());
            }else{
             statement.setInt(5, 0);
            }
            if(backupImage.getUser() != null){
             statement.setInt(6, backupImage.getUser().getId().intValue());
            }else{
             statement.setInt(6, 0);
            }
            statement.setString(7, backupImage.getImage().getLengthKB()+"");

            statement.execute();

            return true;
        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao Inserir: ", ex);  // error message if it occurs // mensagem de erro se ocorrer /
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
//        String sql = "INSERT INTO image (image, cor, detalhes_de_cores, importancia, id_usuario_image) VALUES (?,?,?,?,?);";
//
//       
//            
//        try {
//
//             for (BackupImage backupImage : backupImages) {
//            
//            statement = connection.prepareStatement(sql);
//
//            statement.setString(1, getImgetNameage()ggetNameetFile()..getName());
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
//            dialoger.errorMessage("Erro ao Inserir: ", ex);  // error message if it occurs // mensagem de erro se ocorrer /
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
        String sql = "UPDATE image SET image_name = ? , image_way = ? , image_bytes = ? , image_postIt_id = ? , image_notification_id = ?, image_user_id = ? , image_size = ? WHERE id_image = ?;";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, backupImage.getImage().getFile().getName());
            statement.setString(2, backupImage.getImage().getFile().getAbsolutePath());
            statement.setBinaryStream(3, backupImage.getImage().getInputStream());
             if(backupImage.getPostIt() != null){
             statement.setInt(4, backupImage.getPostIt().getId());
            }else{
             statement.setInt(4, 0);
            }
            if(backupImage.getNotification() != null){
             statement.setInt(5, backupImage.getNotification().getId());
            }else{
             statement.setInt(5, 0);
            }
            if(backupImage.getUser() != null){
             statement.setInt(6, backupImage.getUser().getId().intValue());
            }else{
             statement.setInt(6, 0);
            }
            statement.setString(7, backupImage.getImage().getLengthKB()+"");

            statement.setInt(8, backupImage.getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao Atualizar : ", ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public boolean delet(BackupImage backupImage) {

        connect();

        PreparedStatement statement = null;
        String sql = "DELETE FROM image WHERE id_image = ?;";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(0, backupImage.getId());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
            dialoger.errorMessage("Erro ao Deletar: ", ex);  // error message if it occurs // mensagem de erro se ocorrer /
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
        String sql = "SELECT * FROM image WHERE image_postIt_id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, postIt.getId());

            result = statement.executeQuery();

            while (result.next()) {

                BackupImage backupImage = backupImageFactory.genereteBackupImage(result);
                
                backupImages.add(backupImage);
                
            }

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex);  // error message if it occurs // mensagem de erro se ocorrer /
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
        String sql = "SELECT * FROM image WHERE image_notification_id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, notification.getId());

            result = statement.executeQuery();

            while (result.next()) {

                BackupImage backupImage = backupImageFactory.genereteBackupImage(result);
                
                backupImages.add(backupImage);
                
            }

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return backupImages;
    }
    
    public List<BackupImage> searchByName (String name) {

        connect();
     
        PreparedStatement statement = null;
        ResultSet result = null;
        List<BackupImage> backupImages = new ArrayList<>();
        String sql = "SELECT * FROM image WHERE image_name = ?;";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, name);

            result = statement.executeQuery();

            while (result.next()) {

                BackupImage backupImage = backupImageFactory.genereteBackupImage(result);
                
                backupImages.add(backupImage);
                
            }

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return backupImages;
    }
    
    public boolean exist(BackupImage backupImage) {  
        
        connect();
        
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM image WHERE id_image = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, backupImage.getId().intValue());    
            
            result = statement.executeQuery();           
            
            exist = result.next();

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex); 
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
        String sql = "SELECT * FROM image WHERE id_image = ?;";
        

       
        
        try {

         for (BackupImage backupImage : backupImages) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, backupImage.getId().intValue());    
            
            result = statement.executeQuery();           
            
            exist.add(result.next());
        }

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex);
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
        String sql = "SELECT * FROM image WHERE image_name = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, backupImage.getImage().getFile().getName());     
            
            result = statement.executeQuery();           
            
            exist = result.next();

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex); 
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
        String sql = "SELECT * FROM image WHERE image_name = ?;";
        

       
        
        try {

         for (BackupImage backupImage : backupImages) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, backupImage.getImage().getFile().getName());    
            
            result = statement.executeQuery();           
            
            exist.add(result.next());
        }

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex);
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
        String sql = "SELECT * FROM image WHERE image_way = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, backupImage.getImage().getFile().getName());     
            
            result = statement.executeQuery();           
            
            exist = result.next();

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex); 
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
        String sql = "SELECT * FROM image WHERE image_way = ?;";
        

       
        
        try {

         for (BackupImage backupImage : backupImages) {
                
            statement = connection.prepareStatement(sql);    
            
            statement.setString(1, backupImage.getImage().getFile().getName());    
            
            result = statement.executeQuery();           
            
            exist.add(result.next());
        }

        } catch (SQLException ex) {
            dialoger.errorMessage("Erro ao consultar o banco: ", ex);
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
