/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Model.User;
import com.mysql.jdbc.Connection;
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
public class UserDAO {
    
    Connection connection;

    public UserDAO() {
        
    }
    
    public boolean insert(User user){
        connect();
        PreparedStatement statement = null;
        String sql = "INSERT INTO usuario (nome , sexo , dataNascimento , telefone , email , cpf , senha) VALUES (?, ?, ?, ?, ?, ?, ?);";
        
            /*
            
            // Columns:  //  Colunas: //
            
            id int(11) AI PK
            nome varchar(50)
            sexo enum('Masculino','Feminino')
            dataNascimento date
            telefone varchar(20)
            email varchar(1000)
            cpf char(11)
            senha varchar(50)
            
            */
            
        try {
            
            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
                statement.setString(1, user.getNome());     // Filling in the camp "?"  //  Preenchendo os campos "?"
                statement.setString(2, user.getSexo());
                statement.setDate(3, (Date) user.getNascimento());
                statement.setString(4, user.getTelefone());
                statement.setString(5, user.getEmail());
                statement.setString(6, user.getCPF());
                statement.setString(7, user.getSenha());
                
            statement.execute();    // executing sql instruction   //  executando instruçao sql
                
            return true;    //returns true if successful // retorna verdadeiro se for bem sucedido
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar: "+ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;   //returns false if unsuccessful // retorna falso se nao for bem sucedido
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
    }  
        
    public boolean update(User user){
        
          connect();
        PreparedStatement statement = null;
        String sql = "UPDATE usuario set nome = ? , sexo = ?, dataNascimento = ?, telefone = ?, email = ?, cpf = ?, senha = ? WHERE id = ?;";
        
            /*
            
            // Columns:  //  Colunas: //
            
            id int(11) AI PK
            nome varchar(50)
            sexo enum('Masculino','Feminino')
            dataNascimento date
            telefone varchar(20)
            email varchar(1000)
            cpf char(11)
            senha varchar(50)
            
            */
            
        try {
            
            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
                statement.setString(1, user.getNome());     // Filling in the camp "?"  //  Preenchendo os campos "?"
                statement.setString(2, user.getSexo());
                statement.setDate(3, (Date) user.getNascimento());
                statement.setString(4, user.getTelefone());
                statement.setString(5, user.getEmail());
                statement.setString(6, user.getCPF());
                statement.setString(7, user.getSenha());
                statement.setLong(8, user.getId());
                
            statement.execute();    // executing sql instruction   //  executando instruçao sql
                
            return true;    //returns true if successful // retorna verdadeiro se for bem sucedido
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Atualizar: "+ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;   //returns false if unsuccessful // retorna falso se nao for bem sucedido
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
    }
    
    public boolean delete(User user){
        
        connect();
        PreparedStatement statement = null;
        String sql = "UPDATE usuario set nome = ? , sexo = ?, dataNascimento = ?, telefone = ?, email = ?, cpf = ?, senha = ? WHERE id = ?;";
        
            /*
            
            // Columns:  //  Colunas: //
            
            id int(11) AI PK
            nome varchar(50)
            sexo enum('Masculino','Feminino')
            dataNascimento date
            telefone varchar(20)
            email varchar(1000)
            cpf char(11)
            senha varchar(50)
            
            */
            
        try {
            
            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
            
                statement.setLong(8, user.getId());       // Filling in the camp "?"  //  Preenchendo os campos "?"
                
            statement.execute();    // executing sql instruction   //  executando instruçao sql
                
            return true;    //returns true if successful // retorna verdadeiro se for bem sucedido
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Atualizar: "+ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;   //returns false if unsuccessful // retorna falso se nao for bem sucedido
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        
    }
    
    public List<User> selectAll (){
        
        connect();
        PreparedStatement statement = null;
        ResultSet result= null;
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuario;";
        
        
            /*
            
            // Columns:  //  Colunas: //
            
            id int(11) AI PK
            nome varchar(50)
            sexo enum('Masculino','Feminino')
            dataNascimento date
            telefone varchar(20)
            email varchar(1000)
            cpf char(11)
            senha varchar(50)
            
            */
            
        try {
            
            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
                
            result = statement.executeQuery();    //  execute sql statement returning result  //  executa instruçao sql retornando resultado
            
            while(result.next()){
                
                User user = new User();     // create user with database data  // criando usuario com dados do banco de dados
                
                user.setId(result.getLong("id"));
                user.setNome(result.getString("nome"));
                user.setSexo(result.getString("sexo"));
                user.setNascimento(result.getDate("dataNascimento"));
                user.setTelefone(result.getString("telefone"));
                user.setEmail(result.getString("email"));
                user.setCPF(result.getString("cpf"));
                user.setSenha(result.getString("senha"));
                
                users.add(user);    // add user created in List users  //  adiciona o usuario criado no List usuarios
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao consultar o banco: "+ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return users;
    }
    
        
    public List<User> search(String search){
        
        connect();
        PreparedStatement statement = null;
        ResultSet result= null;
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE nome LIKE ?;";
        
        
            /*
            
            // Columns:  //  Colunas: //
            
            id int(11) AI PK
            nome varchar(50)
            sexo enum('Masculino','Feminino')
            dataNascimento date
            telefone varchar(20)
            email varchar(1000)
            cpf char(11)
            senha varchar(50)
            
            */
            
        try {
            
            statement = connection.prepareStatement(sql);   // prepares the command to be executed  // prepara o comando para ser executado
                
                statement.setString(1, "%"+search+"%");   // Filling in the camp "?"  //  Preenchendo os campos "?"
            
            result = statement.executeQuery();          //  execute sql statement returning result  //  executa instruçao sql retornando resultado
            
            while(result.next()){
                
                User user = new User();     // create user with database data  // criando usuario com dados do banco de dados
                
                user.setId(result.getLong("id"));
                user.setNome(result.getString("nome"));
                user.setSexo(result.getString("sexo"));
                user.setNascimento(result.getDate("dataNascimento"));
                user.setTelefone(result.getString("telefone"));
                user.setEmail(result.getString("email"));
                user.setCPF(result.getString("cpf"));
                user.setSenha(result.getString("senha"));
                
                users.add(user);    // add user created in List users  //  adiciona o usuario criado no List usuarios
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao consultar o banco: "+ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return users;
    }    
         
           
         
    

    private void connect() {
    
        connection = ConnectionFactory.getConnection();
    
    }
    
}
