/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Model.Notification;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class NotificationDAO {

    private Connection connection;
    private Date sqlDate;
    java.util.Date notificationDate;

    public NotificationDAO() {

    }

    public boolean insert(Notification notification) {
        connect();
        PreparedStatement statement = null;
        String sql = "INSERT INTO notificacao (titulo , descricao , image , horario , avisado, tipo , anexo , musica, corDoTipo) VALUES (?,?,?, ?, ?, ?, ?, ?, ?);";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario date 
                avisado tinyint(1) 
                tipo varchar(30) 
                anexo varchar(5000) 
                musica varchar(5000)
                corDoTipo varchar(7)
        
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            sqlDate = notification.getSQLScheduledDay();  // converting Date from java to Date from SQL  //  convertendo Date do java para o Date do SQL

            statement.setString(1, notification.getTitle());     // Filling in the camp "?"  //  Preenchendo os campos "?"
            statement.setString(2, notification.getDescription());
            statement.setString(3, notification.getImage());
            statement.setDate(4, sqlDate);
            statement.setBoolean(5, notification.isWarned());
            statement.setString(6, notification.getType());
            statement.setString(7, notification.getAttachment().getAbsolutePath());
            statement.setString(8, notification.getMusic().getAbsolutePath());
            statement.setString(9, notification.getTypeColor());
            
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
        String sql = "UPDATE notificacao SET titulo = ? , descricao = ?, image = ?, horario = ?, avisado = ?, tipo = ?, anexo = ?, musica = ?, corDoTipo = ? WHERE idNotific = ?;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario date 
                avisado tinyint(1) 
                tipo varchar(30) 
                anexo varchar(5000) 
                musica varchar(5000)
                corDoTipo varchar(7)
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            sqlDate = notification.getSQLScheduledDay();  // converting Date from java to Date from SQL  //  convertendo Date do java para o Date do SQL

            statement.setString(1, notification.getTitle());     // Filling in the camp "?"  //  Preenchendo os campos "?"
            statement.setString(2, notification.getDescription());
            statement.setString(3, notification.getImage());
            statement.setDate(4, sqlDate);
            statement.setBoolean(5, notification.isWarned());
            statement.setString(5, notification.getType());
            statement.setString(6, notification.getAttachment().getAbsolutePath());
            statement.setString(7, notification.getMusic().getAbsolutePath());
            statement.setString(9, notification.getTypeColor());
            
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
                horario date 
                avisado tinyint(1) 
                tipo varchar(30) 
                anexo varchar(5000) 
                musica varchar(5000)
                corDoTipo varchar(7)
            
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
        String sql = "SELECT * FROM notificacao ORDER BY horario;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario date 
                avisado tinyint(1) 
                tipo varchar(30) 
                anexo varchar(5000) 
                musica varchar(5000).
                corDoTipo varchar(7)
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                Notification notification = new Notification();     // create Notification with database data  // criando notificacao com dados do banco de dados

                if (result.getDate("horario") != null) {
                    notificationDate = new java.util.Date(result.getDate("horario").getTime());
                }

                notification.setId(result.getInt("id"));
                notification.setTitle(result.getString("titulo"));
                notification.setDescription(result.getString("descricao"));
                notification.setAttachment(new File(result.getString("anexo")));
                notification.setImage(result.getString("image"));
                notification.setMusic(new File(result.getString("musica")));
                notification.setScheduledDay(notificationDate);
                notification.setType(result.getString("tipo"));
                notification.setWarned(result.getBoolean("avisado"));
                notification.setTypeColor(result.getString("corDoTipo"));

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
        String sql = "SELECT * FROM notificacao WHERE titutlo LIKE ? OR descricao LIKE ?;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario date 
                avisado tinyint(1) 
                tipo varchar(30) 
                anexo varchar(5000) 
                musica varchar(5000)
                corDoTipo varchar(7)
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            statement.setString(1, "%" + search + "%");   // Filling in the camp "?"  //  Preenchendo os campos "?"
            statement.setString(2, "%" + search + "%");   // Filling in the camp "?"  //  Preenchendo os campos "?"

            result = statement.executeQuery();          //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                Notification notification = new Notification();     // create Notification with database data  // criando notificacao com dados do banco de dados

                if (result.getDate("horario") != null) {
                    notificationDate = new java.util.Date(result.getDate("horario").getTime());
                }

                notification.setId(result.getInt("id"));
                notification.setTitle(result.getString("titulo"));
                notification.setDescription(result.getString("descricao"));
                notification.setAttachment(new File(result.getString("anexo")));
                notification.setImage(result.getString("image"));
                notification.setMusic(new File(result.getString("musica")));
                notification.setScheduledDay(notificationDate);
                notification.setType(result.getString("tipo"));
                notification.setWarned(result.getBoolean("avisado"));
                notification.setTypeColor(result.getString("corDoTipo"));
                
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
        String sql = "SELECT * FROM notificacao WHERE idNotific = ?;";

        /*
            
            // Columns:  //  Colunas: //
            
                idNotific int(11) AI PK 
                titulo varchar(25) 
                descricao varchar(1500) 
                image varchar(5000) 
                horario date 
                avisado tinyint(1) 
                tipo varchar(30) 
                anexo varchar(5000) 
                musica varchar(5000)
                corDoTipo varchar(7)
            
         */
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado

            statement.setInt(1, notification.getId());   // Filling in the camp "?"  //  Preenchendo os campos "?"

            result = statement.executeQuery();          //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            if (result.next()) {

                if (result.getDate("horario") != null) {
                    notificationDate = new java.util.Date(result.getDate("horario").getTime());
                }

                notification.setId(result.getInt("id"));
                notification.setTitle(result.getString("titulo"));
                notification.setDescription(result.getString("descricao"));
                notification.setAttachment(new File(result.getString("anexo")));
                notification.setImage(result.getString("image"));
                notification.setMusic(new File(result.getString("musica")));
                notification.setScheduledDay(notificationDate);
                notification.setType(result.getString("tipo"));
                notification.setWarned(result.getBoolean("avisado"));
                notification.setTypeColor(result.getString("corDoTipo"));

                findNotification = notification;
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
        String sql = "SELECT * FROM notificacao WHERE idNotific = ?;";

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

}
