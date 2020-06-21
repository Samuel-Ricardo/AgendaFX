/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Samuel
 */
public class ImageFile {

    private byte[] bytes;
    private File file;
    private int length ;
    private int lengthKB = length / 1024;
    private int lengthMB = lengthKB / 1024;
    private FileInputStream input;
    private FileOutputStream output;
    private ImageIcon imageSwing;
    private Image ImageFX;

    public ImageFile(String absolutePath) {
       
        this.file = new File(absolutePath);

        start();
    }

    public ImageFile(String string, String string1) {
        this.file = new File(string, string1);

        start();
    }

    public ImageFile(File file, String string) {
        this.file = new File(file,string);

        start();
    }

    public ImageFile(URI uri) {
        this.file = new File(uri);

        start();
    }    

    public ImageFile(File file) {
        this.file = file;
    }
    
   public ImageFile(InputStream inputS, String name) {
        
        try {
            this.bytes = new byte[1024];
            
            String PCuser = System.getProperty("user.name");
            
            file = new File("\"C:\\Users\\"+PCuser+"\\Documents\\SAGAL\\images\\"+name+".png");
            
            input = new FileInputStream(file);
            output = new FileOutputStream(file);
            
                System.out.println("Começou o download");
                
                while(inputS.read(bytes) != -1){
                    
                    output.write(bytes);
                    
                }
                
            output.close();
            inputS.close();
            
            start();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    private void start() {
        try {
            
            this.length = (int) file.length();

            this.bytes = new byte[length];
            this.input = new FileInputStream(file);
            this.output = new FileOutputStream(file);

            input.read(bytes, 0, length);

            this.imageSwing = new ImageIcon(file.getAbsolutePath());
            this.ImageFX = new Image(file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static boolean exist(String imageName) {
        
       String PCuser = System.getProperty("user.name");
       
       File search = new File("C:\\Users\\"+PCuser+"\\Documents\\SAGAL\\images\\"+imageName);
       
       return search.exists();
    }
    

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLengthKB() {
        return lengthKB;
    }

    public void setLengthKB(int lengthKB) {
        this.lengthKB = lengthKB;
    }

    public int getLengthMB() {
        return lengthMB;
    }

    public void setLengthMB(int lengthMB) {
        this.lengthMB = lengthMB;
    }

    public FileInputStream getInput() {
        return input;
    }

    public void setInput(FileInputStream input) {
        this.input = input;
    }

    public FileOutputStream getOutput() {
        return output;
    }

    public void setOutput(FileOutputStream output) {
        this.output = output;
    }

    public ImageIcon getImageSwing() {
        return imageSwing;
    }

    public void setImageSwing(ImageIcon imageSwing) {
        this.imageSwing = imageSwing;
    }

    public Image getImageFX() {
        return ImageFX;
    }

    public void setImageFX(Image ImageFX) {
        this.ImageFX = ImageFX;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    

}
