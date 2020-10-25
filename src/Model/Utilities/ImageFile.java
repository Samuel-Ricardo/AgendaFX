/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utilities;

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
    private int lengthKB;
    private int lengthMB;
    private FileInputStream inputStream;
    private FileOutputStream outputStream;
    private ImageIcon imageSwing;
    private Image ImageFX;
    private static final String PCuser = System.getProperty("user.name");

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
            
            file = new File("\"C:\\Users\\"+PCuser+"\\Documents\\AgendaFX\\images\\"+name);
            
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(file);
            
                System.out.println("Começou o download");
                
                while(inputS.read(bytes) != -1){
                    
                    outputStream.write(bytes);
                    
                }
                
            outputStream.close();
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
            this.lengthKB = length / 1024;
            this.lengthMB = lengthKB / 1024;
            
            this.bytes = new byte[length];
            this.inputStream = new FileInputStream(file);
            this.outputStream = new FileOutputStream(file);

          //  input.read(bytes, 0, length);

            this.imageSwing = new ImageIcon(file.getAbsolutePath());
            this.ImageFX = new Image(file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static boolean exist(String imageName) {
        
      
       
       File search = new File("C:\\Users\\"+PCuser+"\\Documents\\AgendaFX\\images\\"+imageName);
       
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

    public FileInputStream getInputStream() {
        
        try {
            
            inputStream = new FileInputStream(file);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return inputStream;
    }

    public void setInputStream(FileInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public FileOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(FileOutputStream outputStream) {
        this.outputStream = outputStream;
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
