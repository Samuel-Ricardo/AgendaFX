/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Samuel
 */
public class TypeFactory {
    
    private final UserFactory userFactory;

    public TypeFactory() {
        this.userFactory = new UserFactory();
    }
    
        public Type genereteType(ResultSet result) throws SQLException {
        
        Type type = new Type();
        
        type.setId(result.getInt("id_tipo"));
        type.setName(result.getString("tipo"));
        type.setSecondaryColor(result.getString("detalhes_de_cores"));
        type.setPrimaryColor(result.getString("cor"));
        type.setImportance(result.getInt("importancia"));
        type.setUser(userFactory.generateUser(result));
        
        return type;
    }
    
}
