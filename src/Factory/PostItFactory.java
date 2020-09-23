/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.PostIt;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Samuel
 */
public class PostItFactory {
    
    private final ImageFactory imageFactory;
    private final UserFactory userFactory;
    private final TypeFactory typeFactory;
    
    public PostItFactory() {
        
        this.userFactory = new UserFactory();
        this.typeFactory = new TypeFactory();
        this.imageFactory = new ImageFactory();
    }
    
        public PostIt generatePostIt(ResultSet result) throws SQLException {
   
        PostIt postIt = new PostIt();     // create PostIt with database data  // criando notificacao com dados do banco de dados
        
        postIt.setId(result.getInt("idPostIt"));
        postIt.setTitle(result.getString("title"));
        postIt.setBody(result.getString("body"));
        postIt.setUser(userFactory.generateUser(result));
        postIt.setType(typeFactory.genereteType(result));
//        postIt.setAttachments(FileFactory.generateAttachment(result));
        postIt.setImages(imageFactory.generateImage(result));
        
        return postIt;
    }
    
}
