/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Model.PostIt;
import Model.PostIt;
import Model.User;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.Date;
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
        String sql = "INSERT INTO postit (idPostIt, postItUser, title, body, scheduled, horary, warned, typeP, typeColor, sound) VALUES (?,?,?,?,?,?,?,?,?,?);";
        
        /*
        
        Columns:
                idPostIt int(11) AI PK 
                title varchar(45) 
                body tinytext 
                scheduled varchar(25) 
                horary varchar(10) 
                warned tinyint(1) 
                typeP varchar(50) 
                typeColor varchar(10) 
                postItUser int(11) 
                sound text

        */
        try {
            
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, postIt.getId());
            statement.setInt(2, postIt.getUser().getId().intValue());
            statement.setString(3, postIt.getTitle());
            statement.setString(4, postIt.getDescription());
            statement.setString(5, postIt.getScheduledDate());
            statement.setString(6, postIt.getScheduledHour());
            statement.setBoolean(7, postIt.isWarned());
            statement.setString(8, postIt.getType());
            statement.setString(9, postIt.getTypeColor());
            statement.setString(10, postIt.getMusic().getAbsolutePath());
            
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
        String sql = "UPDATE postit SET postItUser = ?, title = ?, body = ?, scheduled = ?, horary = ?, warned = ?, typeP = ?, typeColor = ?, sound = ? WHERE idPostIt = ?;";
        
        try {
            
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, postIt.getUser().getId().intValue());
            statement.setString(2, postIt.getTitle());
            statement.setString(3, postIt.getDescription());
            statement.setString(4, postIt.getScheduledDate());
            statement.setString(5, postIt.getScheduledHour());
            statement.setBoolean(6, postIt.isWarned());
            statement.setString(7, postIt.getType());
            statement.setString(8, postIt.getTypeColor());
            statement.setString(9, postIt.getMusic().getAbsolutePath());
            
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
           JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
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
        String sql = "SELECT * FROM postit_views ORDER BY horario;";

        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                PostIt PostIt = new PostIt();     // create PostIt with database data  // criando notificacao com dados do banco de dados

                PostIt.setId(result.getInt("idNotific"));
                PostIt.setTitle(result.getString("title"));
                PostIt.setDescription(result.getString("body"));
                PostIt.setMusic(new File(result.getString("sound")));
                try {
                    PostIt.setScheduledDay(complet.parse(result.getString("scheduled")+" "+result.getString("horary")));
                } catch (ParseException ex) {
                    Logger.getLogger(PostItDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                PostIt.setType(result.getString("tipo"));
                PostIt.setWarned(result.getBoolean("avisado"));
                PostIt.setTypeColor(result.getString("corDoTipo"));

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

                PostIt.setUser(user);

                postits.add(PostIt);    // add PostIt created in List PostIts  //  adiciona o notificacao criado no List notificacaos
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
        String sql = "SELECT * FROM postit_views WHERE id = ? ORDER BY horario;";
        
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                PostIt PostIt = new PostIt();     // create PostIt with database data  // criando notificacao com dados do banco de dados

                PostIt.setId(result.getInt("idNotific"));
                PostIt.setTitle(result.getString("title"));
                PostIt.setDescription(result.getString("body"));
                PostIt.setMusic(new File(result.getString("sound")));
                try {
                    PostIt.setScheduledDay(complet.parse(result.getString("scheduled")+" "+result.getString("horary")));
                } catch (ParseException ex) {
                    Logger.getLogger(PostItDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                PostIt.setType(result.getString("tipo"));
                PostIt.setWarned(result.getBoolean("avisado"));
                PostIt.setTypeColor(result.getString("corDoTipo"));

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

                PostIt.setUser(user);

                postits.add(PostIt);    // add PostIt created in List PostIts  //  adiciona o notificacao criado no List notificacaos
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
        String sql = "SELECT * FROM PostItView WHERE titutlo LIKE ? OR descricao LIKE ?;";
        
        try {

            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                PostIt PostIt = new PostIt();     // create PostIt with database data  // criando notificacao com dados do banco de dados

                PostIt.setId(result.getInt("idNotific"));
                PostIt.setTitle(result.getString("title"));
                PostIt.setDescription(result.getString("body"));
                PostIt.setMusic(new File(result.getString("sound")));
                try {
                    PostIt.setScheduledDay(complet.parse(result.getString("scheduled")+" "+result.getString("horary")));
                } catch (ParseException ex) {
                    Logger.getLogger(PostItDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                PostIt.setType(result.getString("tipo"));
                PostIt.setWarned(result.getBoolean("avisado"));
                PostIt.setTypeColor(result.getString("corDoTipo"));

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

                PostIt.setUser(user);

                postits.add(PostIt);    // add PostIt created in List PostIts  //  adiciona o notificacao criado no List notificacaos
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
        String sql = "SELECT * FROM PostItView WHERE idNotific = ?;";
        
        try {

                statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado

            while (result.next()) {

                PostIt postIt2 = new PostIt();     // create PostIt with database data  // criando notificacao com dados do banco de dados

                postIt2.setId(result.getInt("idNotific"));
                postIt2.setTitle(result.getString("title"));
                postIt2.setDescription(result.getString("body"));
                postIt2.setMusic(new File(result.getString("sound")));
                try {
                    postIt2.setScheduledDay(complet.parse(result.getString("scheduled")+" "+result.getString("horary")));
                } catch (ParseException ex) {
                    Logger.getLogger(PostItDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                postIt2.setType(result.getString("tipo"));
                postIt2.setWarned(result.getBoolean("avisado"));
                postIt2.setTypeColor(result.getString("corDoTipo"));

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

                postIt2.setUser(user);
                
                findPostIt = postIt2;
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
        String sql = "SELECT * FROM postit_views WHERE idPostIt = ?;";

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
