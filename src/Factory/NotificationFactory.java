/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import DAO.NotificationDAO;
import Model.Notification;
import Model.Type;
import Model.User;
import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class NotificationFactory {
    
     public Type generateNotification(ResultSet result, Notification notification) throws SQLException {
      
        
        notification.setId(result.getInt("idNotific"));
        notification.setTitle(result.getString("titulo"));
        notification.setBody(result.getString("descricao"));
        //notification.setAttachment(new File(result.getString("anexo")));
        notification.setImage(result.getString("image"));
        notification.setMusic(new File(result.getString("musica")));
        notification.setScheduledDay(getDateIfNotNull(result));
        notification.setWarned(result.getBoolean("avisado"));
        User user = new User();
        java.util.Date userDate = null;
        if (result.getDate("dataNascimento") != null) {
            userDate = new java.util.Date(result.getDate("dataNascimento").getTime());
        }
        user.setId(result.getLong("id"));
        user.setNome(result.getString("nome"));
        user.setSexo(result.getString("sexo"));
        user.setNascimento(userDate);
        user.setTelefone(result.getString("telefone"));
        user.setEmail(result.getString("email"));
        user.setCPF(result.getString("cpf"));
        user.setSenha(result.getString("senha"));
        user.setImage(result.getString("imagePerfil"));
        notification.setUser(user);
        Type type = new Type();
        type.setId(result.getInt("id_tipo"));
        type.setName(result.getString("tipo"));
        type.setSecondaryColor(result.getString("detalhes_de_cores"));
        type.setPrimaryColor(result.getString("cor"));
        type.setImportance(result.getInt("importancia"));
        type.setUser(user);
        return type;
    }

    public java.util.Date getDateIfNotNull(ResultSet result) throws SQLException {
        
         SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
         SimpleDateFormat horary = new SimpleDateFormat("HH:mm");
         SimpleDateFormat complet = new SimpleDateFormat("dd/MM/yy HH:mm");
        
        java.util.Date notificationDate = new java.util.Date();
        
        if (result.getDate("horario") != null && result.getDate("marcado") != null) {
            
            Date time = new Date (result.getTime("horario").getTime());
            Date date = new Date (result.getDate("marcado").getTime());
            String timeS = horary.format(time);
            String dateS = day.format(date);
            String scheduled = dateS + " " + timeS;
            
            try {
                notificationDate = complet.parse(scheduled);
            } catch (ParseException ex) {
                Logger.getLogger(NotificationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return notificationDate;
    }
    
}
