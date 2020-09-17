/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Factory.PostItFactory;
import Factory.TypeFactory;
import Factory.UserFactory;
import JDBC.ConnectionFactory;
import Model.PostIt;
import Model.Type;
import Model.User;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class PostItDAO {
    
    private Connection connection;
    private static PostIt postIt;
    private SimpleDateFormat complet = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    public boolean Insert(PostIt postIt){
    
      if(connection == null){
         connect();
        }
        PreparedStatement statement = null;
        String sql = "INSERT INTO postit (idPostIt, user_postit, title, body, scheduled, horary, warned, type_postit, sound) VALUES (?,?,?,?,?,?,?,?,?);";
        
        /*
        
        Columns:
                idPostIt int(11) AI PK 
                title varchar(45) 
                body tinytext 
                scheduled varchar(25) 
                horary varchar(10) 
                warned tinyint(1) 
                type_postit varchar(50)  
                user_postit int(11) 
                sound text

        */
        try {
            
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, postIt.getId());
            statement.setInt(2, postIt.getUser().getId().intValue());
            statement.setString(3, postIt.getTitle());
            statement.setString(4, postIt.getBody());
            statement.setInt(5, postIt.getType().getId().intValue());
            
            statement.execute();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
  }
    
    public boolean update(PostIt postIt){
        
          if(connection == null){
         connect();
        }
        PreparedStatement statement = null;
        String sql = "UPDATE postit SET user_postit = ?, title = ?, body = ?, scheduled = ?, horary = ?, warned = ?, type_postit = ?, sound = ? WHERE idPostIt = ?;";
        
        try {
            
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, postIt.getUser().getId().intValue());
            statement.setString(2, postIt.getTitle());
            statement.setString(3, postIt.getBody());
            statement.setInt(4, postIt.getType().getId().intValue());
            
            statement.execute();
            
            return true;
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;  
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public boolean delet(PostIt postIt){
        
        if(connection == null){
         connect();
        }
        PreparedStatement statement = null;
        String sql = "DELETE FROM postit WHERE idPostIt = ?;";
        
        try {
            
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, postIt.getId());
            
            statement.execute();
           
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostItDAO.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, "Erro ao Deletar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;  
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
     public List<PostIt> selectAll() {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<PostIt> postits = new ArrayList<>();
        String sql = "SELECT * FROM postit_from_user ORDER BY scheduled;";

        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {
                
                postits.add(PostItFactory.generatePostIt(result));    // add PostIt created in List PostIts  //  adiciona o notificacao criado no List notificacaos
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return postits;
    }
    
    public List<PostIt> selectAllFromUser(int id) {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<PostIt> postits = new ArrayList<>();
        String sql = "SELECT * FROM postit_from_user WHERE id = ? ORDER BY horary;";
        
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            statement.setInt(1, id);
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                postits.add(PostItFactory.generatePostIt(result));    // add PostIt created in List PostIts  //  adiciona o notificacao criado no List notificacaos
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return postits;
    }

    public List<PostIt> search(String search) {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<PostIt> postits = new ArrayList<>();
        String sql = "SELECT * FROM postit_from_user WHERE titutlo LIKE ? OR descricao LIKE ?;";
        
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {
                
                postits.add(PostItFactory.generatePostIt(result));    // add PostIt created in List PostIts  //  adiciona o notificacao criado no List notificacaos
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return postits;
    }


    public PostIt search(PostIt postIt) {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        PostIt findPostIt = new PostIt();     // create PostIt with database data  // criando notificacao com dados do banco de dados
        String sql = "SELECT * FROM postit_from_user WHERE idPostIt = ?;";
        
        try {

                statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {
                
                findPostIt = PostItFactory.generatePostIt(result);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }

        return findPostIt;
    }

    public boolean exist(PostIt postIt) {     // look for the PostIt in the bank and return true if it exists  // procura o notificacao no banco e retorna verdadeiro se exisstir

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM postit_from_user WHERE idPostIt = ?;";

        try {

            statement = connection.prepareStatement(sql);      // prepares the command to be executed  // prepara o comando para ser executado

            statement.setInt(1, postIt.getId());      // Filling in the camp "?"  //  Preenchendo os campos "?"

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

    private void connect() {
     connection = ConnectionFactory.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static PostIt getPostIt() {
        return postIt;
    }

    public static void setPostIt(PostIt postIt) {
        PostItDAO.postIt = postIt;
    }

    public SimpleDateFormat getComplet() {
        return complet;
    }

    public void setComplet(SimpleDateFormat complet) {
        this.complet = complet;
    }
    
    
    
}
