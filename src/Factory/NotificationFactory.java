/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import DAO.ImageDAO;
import Model.BackupImage;
import Model.Notification;
import Time.Time;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class NotificationFactory {
    
    private final UserFactory userFactory;
    private final TypeFactory typeFactory;

    
    public NotificationFactory() {
        
        this.userFactory = new UserFactory();
        this.typeFactory = new TypeFactory();

    }
    
     public Notification generateNotification(ResultSet result) { // create Notification with database data  // criando notificacao com dados do banco de dados
       
         Notification notification = new Notification();   
         ImageDAO imageDAO = new ImageDAO();
         
         try {
                 
             
             if (result.getTime("horario") != null && result.getDate("marcado") != null) {
                 
                 notification.setScheduledDay(new Time(result.getDate("marcado"), result.getTime("horario")));
             }
                notification.setId(result.getInt("idNotific"));
                notification.setTitle(result.getString("titulo"));
                notification.setBody(result.getString("descricao"));
                //notification.setAttachment(new File(result.getString("anexo")));
                //notification.setImage(result.getString("image"));
                notification.setMusic(new File(result.getString("musica")));
                notification.setWarned(result.getBoolean("avisado"));
                notification.setUser(userFactory.generateUser(result));
                notification.setType(typeFactory.genereteType(result));
                
                ArrayList<BackupImage> images = (ArrayList<BackupImage>) imageDAO.selectAllFromNotification(notification);
                
                if(images.isEmpty()){
                    
                    System.out.println("vAAZIO");
                }else{
                    
                    notification.setImage(images.get(0));
                }
                
                
                
         } catch (SQLException ex) {
             Logger.getLogger(NotificationFactory.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return notification;
    }
    
}
