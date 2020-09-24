/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javafx.scene.paint.Color;

/**
 *
 * @author Samuel
 */
public class HexadecimalFormater {

    private static String format(double val){
        
        String in = Integer.toHexString((int)Math.round(val*255));
        return in.length() == 1 ? "0" + in : in;
    }
    
    public static String getHexString(Color color) {
    
        return "#"+(format(color.getRed())+format(color.getGreen())+format(color.getBlue()))+format(color.getOpacity()).toUpperCase();
        
    }
    
}
