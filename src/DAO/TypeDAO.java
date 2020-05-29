/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Model.Type;
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
public class TypeDAO {

    private static Type type;

    public static ArrayList<Type> getDefaultTypes() {
        
        ArrayList<Type> defaultTypes = new ArrayList<>();
        
        Type urgente = new Type();
        
        urgente.setName("Urgente");
        urgente.setColor("#ff7373");
        urgente.setColorDetails("#ff0000");
        urgente.setImportance(Type.MAX_PRIORITY);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(urgente);
        
        Type meta = new Type();
        
        urgente.setName("Meta");
        urgente.setColor("#ff7373");
        urgente.setColorDetails("#7f7df1");
        urgente.setImportance(Type.HIGH_PRIORITY);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(meta);
        
        Type trabalho = new Type();
        
        urgente.setName("Trabalho");
        urgente.setColor("#9370db");
        urgente.setColorDetails("#8a2be2");
        urgente.setImportance(Type.MAX_PRIORITY);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(trabalho);
        
        Type atividade = new Type();
        
        urgente.setName("Atividade");
        urgente.setColor("#a457a4");
        urgente.setColorDetails("#8b008b");
        urgente.setImportance(4);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(atividade);
        
        Type exercicio = new Type();
        
        urgente.setName("Exercicio");
        urgente.setColor("#7fff7f");
        urgente.setColorDetails("#00ff00");
        urgente.setImportance(Type.MEDIUN_PRIORITY);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(exercicio);
        
        Type evento = new Type();
        
        urgente.setName("Evento");
        urgente.setColor("#e1f481");
        urgente.setColorDetails("#d4ff00");
        urgente.setImportance(Type.MEDIUN_PRIORITY);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(evento);
        
        Type especial = new Type();
        
        urgente.setName("Especial");
        urgente.setColor("#ffe97e");
        urgente.setColorDetails("#ffd700");
        urgente.setImportance(6);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(especial);
        
        Type postIt = new Type();
        
        urgente.setName("Post-It");
        urgente.setColor("#ee71ee");
        urgente.setColorDetails("#ff00ff");
        urgente.setImportance(Type.MEDIUN_PRIORITY);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(postIt);
        
        Type banal = new Type();
        
        urgente.setName("Banal");
        urgente.setColor("#5b5ba4");
        urgente.setColorDetails("#000080");
        urgente.setImportance(Type.MIN_PRIORITY);
        urgente.setUser(UserDAO.getUser());
        
        defaultTypes.add(banal);
        
        Type escola = new Type();
        
        urgente.setName("Urgente");
        urgente.setColor("#aef3f4");
        urgente.setColorDetails("#00faff");
        urgente.setImportance(Type.HIGH_PRIORITY);
        urgente.setUser(UserDAO.getUser());
    
        defaultTypes.add(escola);
        
        return defaultTypes;   
    }
    
    
    private Connection connection;

    public boolean Insert(Type type) {

        if (connection == null) {
            connect();
        }
        PreparedStatement statement = null;
        String sql = "INSERT INTO tipo (tipo, cor, detalhes_de_cores, importancia, id_usuario_tipo) VALUES (?,?,?,?,?);";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(0, type.getName());
            statement.setString(1, type.getColor());
            statement.setString(2, type.getColorDetails());
            statement.setInt(3, type.getImportance().intValue());
            statement.setInt(4, type.getUser().getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Inserir: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public boolean update(Type type) {

        if (connection == null) {
            connect();
        }
        PreparedStatement statement = null;
        String sql = "UPDATE tipo SET tipo = ?, cor = ?, detalhes_de_cores = ?, importancia = ?, id_usuario_tipo = ? WHERE id_tipo = ?;";

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(0, type.getName());
            statement.setString(1, type.getColor());
            statement.setString(2, type.getColorDetails());
            statement.setInt(3, type.getImportance().intValue());
            statement.setInt(4, type.getUser().getId().intValue());
            statement.setInt(5, type.getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar : " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public boolean delet(Type type) {

        if (connection == null) {
            connect();
        }
        PreparedStatement statement = null;
        String sql = "DELETE FROM type WHERE id_type = ?;";

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(0, type.getId().intValue());

            statement.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostItDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Type> selectAllFromUser(int id) {

        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Type> types = new ArrayList<>();
        String sql = "SELECT * FROM types_from_user WHERE id = ?;";
        Date userDate = null;

        try {

            statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            result = statement.executeQuery();

            while (result.next()) {

                Type type = new Type();
                
                type.setId(result.getInt("id_tipo"));
                type.setName(result.getString("tipo"));
                type.setColor(result.getString("cor"));
                type.setColorDetails("detalhes_de_cores");
                type.setImportance(result.getInt("importancia"));
                
                User user = new User();     // create user with database data  // criando usuario com dados do banco de dados

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

                type.setUser(user);
                
                types.add(type);
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco: " + ex);  // error message if it occurs // mensagem de erro se ocorrer /
        } finally {
            ConnectionFactory.closeConnection(connection, statement);  // closes all connections regardless of success  // fecha todas as conexoes independente de sucesso
        }
        
        return types;
    } 
         

    public boolean exist(Type type) {  
        
     
        connect();
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM type_from_user WHERE id_tipo = ?;";
        boolean exist = false;

        try {

            statement = connection.prepareStatement(sql);    
            
            statement.setInt(1, type.getId().intValue());    
            
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

    private void connect() {
        connection = ConnectionFactory.getConnection();
    }

}
