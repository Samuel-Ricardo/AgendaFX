/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Utilities.ImageFile;
import Model.Interfaces.Reminder;
import java.io.File;

/**
 *
 * @author Samuel
 */
public class PostIt extends Reminder{

    protected ImageFile image;
    

    public PostIt() {
    }

    public PostIt(User user) {
        super(user);
    }
}
