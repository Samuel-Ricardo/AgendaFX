/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Factory.NotificationFactory;
import JDBC.ConnectionFactory;
import Model.Notification;
import Model.Type;
import Model.User;
import Time.Time;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class NotificationDAO {



    private Connection connection;
    private static Notification notification;
    private final NotificationFactory notificationFactory;

    public NotificationDAO() {
        this.notificationFactory = new NotificationFactory();
    }

    public NotificationDAO(Connection connection, NotificationFactory notificationFactory) {
        this.connection = connection;
        this.notificationFactory = notificationFactory;
    }

    public boolean insert(Notification notification) {
        connect();
        PreparedStatement statement = null;
        String sql = "INSERT INTO notificacao (titulo , descricao, horario , avisado, tipo_notificacao  , musica, userNotification , marcado ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario time 
                avisado tinyint(1) 
                tipo_notificacao int(11) 
                musica varchar(5000)
                varchar(20)
                userNotification int(11)
                marcado date
        
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            statement.setString(1, notification.getTitle());     // Filling in the camp "?"  //  Preenchendo os campos "?"
            statement.setString(2, notification.getBody());
            statement.setTime(3, notification.getScheduledDay().toSQLTime());
            statement.setBoolean(4, notification.isWarned());
            statement.setInt(5, notification.getType().getId().intValue());
            if (notification.getMusic() != null) {
                statement.setString(6, notification.getMusic().getAbsolutePath());
            }else{
                statement.setString(6, "");
            }
            statement.setInt(7, notification.getUser().getId().intValue());
            statement.setDate(8, notification.getScheduledDay().toSQLDate());

            statement.execute();    // executing sql instruction   //  executando instruçao sql

            return true;    //returns true if successful // retorna verdadeiro se for bem sucedido
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;   //returns false if unsuccessful // retorna falso se nao for bem sucedido
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
    }

    public boolean update(Notification notification) {

        connect();
        PreparedStatement statement = null;
        String sql = "UPDATE notificacao SET titulo = ? , descricao = ?, horario = ?, avisado = ?, tipo_notificacao = ?, musica = ?, userNotification = ?, marcado = ? WHERE idNotific = ?;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario time 
                avisado tinyint(1) 
                tipo_notificacao int(11) 
                musica varchar(5000)
                varchar(20)
                userNotification int(11)
                marcado date
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            
            statement.setString(1, notification.getTitle());     // Filling in the camp "?"  //  Preenchendo os campos "?"
            statement.setString(2, notification.getBody());
            statement.setTime(3, notification.getScheduledDay().toSQLTime());
            statement.setBoolean(4, notification.isWarned());
            statement.setInt(5, notification.getType().getId().intValue());                
            statement.setString(6, notification.getMusic().getAbsolutePath());
            statement.setInt(7, notification.getUser().getId().intValue());
            statement.setDate(8, notification.getScheduledDay().toSQLDate());
            statement.setInt(9, notification.getId());

            System.out.println(notification.getId()+"   id dao");
            
            statement.execute();    // executing sql instruction   //  executando instruçao sql

            return true;    //returns true if successful // retorna verdadeiro se for bem sucedido
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;   //returns false if unsuccessful // retorna falso se nao for bem sucedido
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

    }

    public boolean delete(Notification notification) {

        connect();
        PreparedStatement statement = null;
        String sql = "DELETE FROM notificacao WHERE idNotific = ?;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario time 
                avisado tinyint(1) 
                tipo_notificacao int(11) 
                musica varchar(5000)
                varchar(20)
                userNotification int(11)
                marcado date
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            statement.setLong(1, notification.getId());       // Filling in the camp "?"  //  Preenchendo os campos "?"

            statement.execute();    // executing sql instruction   //  executando instruçao sql

            return true;    //returns true if successful // retorna verdadeiro se for bem sucedido
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;   //returns false if unsuccessful // retorna falso se nao for bem sucedido
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

    }

    public List<Notification> selectAll() {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notification_from_user ORDER BY marcado DESC;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario time 
                avisado tinyint(1) 
                tipo_notificacao int(11) 
                musica varchar(5000).
                varchar(20)
                userNotification int(11)
                marcado date
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                Notification notification = notificationFactory.generateNotification(result);
                
                notifications.add(notification);    // add Notification created in List Notifications  //  adiciona o notificacao criado no List notificacaos
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return notifications;
    }
    
    public List<Notification> selectAllFromUser(int id) {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notification_from_user WHERE id = ? ORDER BY horario;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario time 
                avisado tinyint(1) 
                tipo_notificacao int(11) 
                musica varchar(5000).
                varchar(20)
                userNotification int(11)
                marcado date
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            statement.setInt(1, id);
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                Notification notification = notificationFactory.generateNotification(result);     // create Notification with database data  // criando notificacao com dados do banco de dados
                
                notifications.add(notification);    // add Notification created in List Notifications  //  adiciona o notificacao criado no List notificacaos
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return notifications;
    }

    public List<Notification> search(String search) {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notification_from_user WHERE titutlo LIKE ? OR descricao LIKE ?;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario time 
                avisado tinyint(1) 
                tipo_notificacao int(11) 
                musica varchar(5000)
                varchar(20)
                userNotification int(11)
                marcado date
        
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            statement.setString(1, "%" + search + "%");   // Filling in the camp "?"  //  Preenchendo os campos "?"
            statement.setString(2, "%" + search + "%");   // Filling in the camp "?"  //  Preenchendo os campos "?"

            result = statement.executeQuery();          //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                Notification notification = notificationFactory.generateNotification(result);     // create Notification with database data  // criando notificacao com dados do banco de dados
                
                notifications.add(notification);    // add Notification created in List Notifications  //  adiciona o notificacao criado no List notificacaos
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return notifications;
    }

    public Notification search(Notification notification) {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        Notification findNotification = new Notification();     // create Notification with database data  // criando notificacao com dados do banco de dados
        String sql = "SELECT * FROM notification_from_user WHERE idNotific = ?;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario date 
                avisado tinyint(1) 
                tipo_notificacao int(11) 
                musica varchar(5000)
                varchar(20)
                userNotification int(11)
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            statement.setInt(1, notification.getId());   // Filling in the camp "?"  //  Preenchendo os campos "?"

            result = statement.executeQuery();          //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            if (result.next()) {
                
                findNotification = notificationFactory.generateNotification(result);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return findNotification;
    }
    public Notification searchById(int id) {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        Notification findNotification = new Notification();     // create Notification with database data  // criando notificacao com dados do banco de dados
        String sql = "SELECT * FROM notification_from_user WHERE idNotific = ?;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario date 
                avisado tinyint(1) 
                tipo_notificacao int(11) 
                musica varchar(5000)
                varchar(20)
                userNotification int(11)
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            statement.setInt(1, id);   // Filling in the camp "?"  //  Preenchendo os campos "?"

            result = statement.executeQuery();          //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            if (result.next()) {
                
                findNotification = notificationFactory.generateNotification(result);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return findNotification;
    }

    private void connect() {

        connection = ConnectionFactory.getConnection();

    }

    public boolean exist(Notification Notification) {     // look for the Notification in the bank and return true if it exists  // procura o notificacao no banco e retorna verdadeiro se exisstir

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM notification_from_user WHERE idNotific = ?;";

        try {

            statement = connection.prepareStatement(sql);      // prepares the command to be executed  // prepara o comando para ser executado

            statement.setInt(1, Notification.getId());      // Filling in the camp "?"  //  Preenchendo os campos "?"

            result = statement.executeQuery();            //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            if (result.next()) {  // if there is a result it returns true if it does not return false  //se houver resultado ele retorna verdadeiro se nao retorna false
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

    }    

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public static Notification getNotification() {
        return notification;
    }

    public static void setNotification(Notification notification) {
        NotificationDAO.notification = notification;
    }

}
