/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class Downloader extends Thread {

    private URL link;
    private File localFile;
    private InputStream input;
    private FileOutputStream output;
    private int bytes = 0;
    private Double kiloByte = new Double(0);
    private Double megaByte = new Double(0);
    private boolean downloading = false;
    private Dialoger dialoger;
    private static Downloader downloader = new Downloader();

    
    ////Constructors/////
    
    public Downloader(String link, String localFile) {

        try {
            this.link = new URL(link);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showConfirmDialog(null, "Nao foi possivel encontrar o Link");
        }

        this.localFile = new File(localFile);
        dialoger = new Dialoger();
    }

    public Downloader() {

        dialoger = new Dialoger();
    }
    

    public void run() {

    }

    
    public void download() {    // does the download of file // faz o download do arquivo
        
        try {
            
            downloading = true;
            
            input = link.openStream(); 
            output = new FileOutputStream(localFile);
            
            while ((bytes = input.read()) != -1) {  // as long as there are bytes they will be copied to the local machine // enquanto houver bytes eles serao copiados para maquina local

                output.write(bytes);

                System.out.println("Tamanho: " + sizeInMegaBytes(localFile) + " MB");

            }
            
            downloading = false;
            JOptionPane.showMessageDialog(null, "Download do arquivo: "+ localFile.getName() +" Finalizado");
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double sizeInMegaBytes(File file) {
        megaByte = sizeInkiloBytes(file) / 1024;
        return megaByte;
    }

    public double sizeInkiloBytes(File file) {
        kiloByte = (double) file.length() / 1024;
        return kiloByte;
    }

    public void download(URL link, File localFile) {  // does the download of file // faz o download do arquivo

        try {
            downloading = true;
            
            input = link.openStream();
            output = new FileOutputStream(localFile);

            while ((bytes = input.read()) != -1) {

                output.write(bytes);

                System.out.println("Tamanho: " + sizeInMegaBytes(localFile) + " MB");

            }
            
            downloading = false;
            JOptionPane.showMessageDialog(null, "Download do arquivo: "+ localFile.getName() +" Finalizado");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public File download(InputStream input,File localFile ){
        
        if(localFile.exists()){
            dialoger.message("Ops ;-;", "O arquivo: "+localFile.getName()+" ja existe;"
                                                  + "\n"
                                                  + "\n Ele está localizado em: "+localFile.getAbsolutePath(), Alert.AlertType.WARNING);
            return localFile;
        
          }else{
            
            FileOutputStream output = null;
            try {
                downloading = true;

                output = new FileOutputStream(localFile);

                int byt = 0;

                byte [] buffer = new byte [1024];

                while ((byt = input.read()) != -1) {                

                    output.write(buffer, 0, byt);

                    System.out.println("Tamanho: " + sizeInMegaBytes(localFile) + " MB");
                    
                    System.out.println("to aki_______: "+localFile.getAbsolutePath());
                }

                downloading = false;

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
          }finally{
                try {
                    input.close();
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }      
        }
        
        return localFile;
    }
    
    public ArrayList<Byte> downloadBytes(InputStream input) {
     
        ByteArrayOutputStream output = new ByteArrayOutputStream();
                
        byte[] downloadedBytes = null;
        
        if(input != null){
            
            try {
                
                byte[] bytes = new byte[1024];
                
                int chek = 0;
                
                while((chek = input.read(bytes)) != -1){
                    
                    output.write(bytes, 0, chek);
                    
                }
                
             downloadedBytes = output.toByteArray();
            } catch (IOException ex) {
                Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    input.close();
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
        if(downloadedBytes !=  null){
            
        ArrayList<Byte> array = new ArrayList<Byte>();
        
            for (byte downloadedByte : downloadedBytes) {

                array.add(downloadedByte);
            }
            
        return array;
        }else{
         
         return null;
        }
    }
    
    
    public void createFile(byte[] bytes, File file){
        
        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            
            fileOutput.write(bytes);
            
            fileOutput.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //////Getters and Setters////////
    
    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public void setLink(String link) {
        try {

            this.link = new URL(link);

        } catch (MalformedURLException ex) {

            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showConfirmDialog(null, "Nao foi possivel encontrar o Link");
        }
    }

    public File getLocalFile() {
        return localFile;
    }

    public void setLocalFile(File localFile) {
        this.localFile = localFile;
    }

    public void setLocalFile(String localFile) {
        this.localFile = new File(localFile);
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public FileOutputStream getOutput() {
        return output;
    }

    public void setOutput(FileOutputStream output) {
        this.output = output;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public Double getKiloBytes() {
        return kiloByte;
    }

    public void setKiloBytes(Double kiloBytes) {
        this.kiloByte = kiloBytes;
    }

    public Double getMegaBytes() {
        return megaByte;
    }

    public void setMegaBytes(Double megaBytes) {
        this.megaByte = megaBytes;
    }

    public Double getKiloByte() {
        return kiloByte;
    }

    public void setKiloByte(Double kiloByte) {
        this.kiloByte = kiloByte;
    }

    public Double getMegaByte() {
        return megaByte;
    }

    public void setMegaByte(Double megaByte) {
        this.megaByte = megaByte;
    }

    public boolean isDownloading() {
        return downloading;
    }

    public void setDownloading(boolean downloading) {
        this.downloading = downloading;
    }

    public Dialoger getDialoger() {
        return dialoger;
    }

    public void setDialoger(Dialoger dialoger) {
        this.dialoger = dialoger;
    }

    public static Downloader getDownloader() {
        return downloader;
    }

    public static void setDownloader(Downloader downloader) {
        Downloader.downloader = downloader;
    }
    
    
}
