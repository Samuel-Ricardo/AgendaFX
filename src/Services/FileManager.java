/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class FileManager extends Thread{
    
    public static final String pcUser = System.getProperty("user.");
    public static final String defaultFolderWay = System.getProperty("user.home") + "/Documents/Oasis";
    private Dialoger dialoger = new Dialoger();
    
    public File createFolder(String name){
     
        File folder = new File (defaultFolderWay +"/"+name); 
        
        if(folder.exists()){
            dialoger.succesMessage("A pasta: "+name+" Já existe. \n\n Caminho: "+folder.getAbsolutePath());
        }else{
            if(folder.mkdirs()){
            dialoger.succesMessage("A pasta: "+name+" foi criado com sucesso. \n\n Caminho: "+folder.getAbsolutePath());
            }else{
                 dialoger.succesMessage("A pasta: "+name+" nao pode ser criado. \n\n Caminho: "+folder.getAbsolutePath());
             }
        }
        
        return folder;
    }
    
    public File createFolder(File folder){
        
        if(folder.exists()){
            dialoger.succesMessage("A pasta: "+folder.getName()+" Já existe. \n\n Caminho: "+folder.getAbsolutePath());
        }else{
            folder.mkdir();
            dialoger.succesMessage("A pasta: "+folder.getName()+" foi criado com sucesso. \n\n Caminho: "+folder.getAbsolutePath());
        }
        
        return folder;
    }
    
    public File copyFileTo(File originalFile, File destinyFolder){
        
        destinyFolder = new File(destinyFolder.getAbsolutePath()+"/"+originalFile.getName());
        
        if(destinyFolder.exists()){
            dialoger.succesMessage("O arquivo: "+destinyFolder.getName()+" Já existe. \n\n Caminho: "+destinyFolder.getAbsolutePath());
        }else{

            
            try {
               
                FileInputStream input = new FileInputStream(originalFile);
                FileOutputStream output = new FileOutputStream(destinyFolder);
              
                FileChannel fcOrigin = input.getChannel();
                FileChannel fcDestiny = output.getChannel();
               
                fcOrigin.transferTo(0, fcOrigin.size(), fcDestiny);
                
                dialoger.succesMessage("O arquivo: "+destinyFolder.getName()+" foi copiado com sucesso. \n\n Caminho: "+destinyFolder.getAbsolutePath());
                
                input.close();
                output.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
           
            } 
        }
        
        return destinyFolder;
    }
    
    public File copyFile(File originalFile, File destinyFile){
          
        if(destinyFile.exists()){
            dialoger.succesMessage("O arquivo: "+destinyFile.getName()+" Já existe. \n\n Caminho: "+destinyFile.getAbsolutePath());
        }else{

            
            try {
               
                FileInputStream input = new FileInputStream(originalFile);
                FileOutputStream output = new FileOutputStream(destinyFile);
              
                FileChannel fcOrigin = input.getChannel();
                FileChannel fcDestiny = output.getChannel();
               
                fcOrigin.transferTo(0, fcOrigin.size(), fcDestiny);
                
                dialoger.succesMessage("O arquivo: "+destinyFile.getName()+" foi copiado com sucesso. \n\n Caminho: "+destinyFile.getAbsolutePath());
                
                input.close();
                output.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
           
            } 
        }
        
        return destinyFile;
    }
    
    public File getFile(String name){
        
         File file = new File (defaultFolderWay +"/"+name); 
         
         return file;
    }
    
     public void run() {

    }
    
}
