/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.PostIt;
import Model.Utilities.ImageFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class PostItFactory {
    
        public static PostIt generatePostIt(ResultSet result) throws SQLException {
   
        PostIt postIt = new PostIt();     // create PostIt with database data  // criando notificacao com dados do banco de dados
     
        ArrayList<ImageFile> images = new ArrayList<>();
        
        
        postIt.setId(result.getInt("idPostIt"));
        postIt.setTitle(result.getString("title"));
        postIt.setBody(result.getString("body"));
        postIt.setUser(UserFactory.generateUser(result));
        postIt.setType(TypeFactory.genereteType(result));
        postIt.setAttachments(FileFactory.generateAttachment(result));
        
        
      
        return postIt;
    }
    
}
