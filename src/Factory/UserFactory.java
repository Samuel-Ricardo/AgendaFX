/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.User;
import Time.Time;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Samuel
 */
public class UserFactory {
    
    
        public static User generateUser(ResultSet result) throws SQLException {
        
        User user = new User();     // create user with database data  // criando usuario com dados do banco de dados
        java.util.Date userDate = null;
        
        if (result.getDate("dataNascimento") != null) {
       //   userDate = new java.util.Date(result.getDate("dataNascimento").getTime());
       
         user.setNascimento(new Time(result.getDate("dataNascimento")));
        }
        
        user.setId(result.getLong("id"));
        user.setNome(result.getString("nome"));
        user.setSexo(result.getString("sexo"));
        user.setTelefone(result.getString("telefone"));
        user.setEmail(result.getString("email"));
        user.setCPF(result.getString("cpf"));
        user.setSenha(result.getString("senha"));
        user.setImage(result.getString("imagePerfil"));
        
        return user;
    }
    
}
