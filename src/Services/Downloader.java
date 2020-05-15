/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Double kiloBytes = new Double(0);
    private Double megaBytes = new Double(0);
    private boolean downloading = false;

    
    ////Constructors/////
    
    public Downloader(String link, String localFile) {

        try {
            this.link = new URL(link);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showConfirmDialog(null, "Nao foi possivel encontrar o Link");
        }

        this.localFile = new File(localFile);
    }

    public Downloader() {

    }
    

    public void run() {

    }

    
    public void download() {    // does the download of file // faz o download do arquivo

        
        
        try {
            downloading = true;
            
            input = link.openStream();
            output = new FileOutputStream(localFile);
            
            while ((bytes = input.read()) != -1) {

                output.write(bytes);

                kiloBytes = (double) localFile.length() / 1024;
                megaBytes = kiloBytes / 1024;

                System.out.println("Tamanho: " + megaBytes + " MB");

            }
            
            downloading = false;
            JOptionPane.showMessageDialog(null, "Download Finalizado");
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void download(URL link, File localFile) {  // does the download of file // faz o download do arquivo

        try {
            downloading = true;
            
            input = link.openStream();
            output = new FileOutputStream(localFile);

            while ((bytes = input.read()) != -1) {

                output.write(bytes);

                kiloBytes = (double) localFile.length() / 1024;
                megaBytes = kiloBytes / 1024;

                System.out.println("Tamanho: " + megaBytes + " MB");

            }
            
            downloading = false;
            
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
        return kiloBytes;
    }

    public void setKiloBytes(Double kiloBytes) {
        this.kiloBytes = kiloBytes;
    }

    public Double getMegaBytes() {
        return megaBytes;
    }

    public void setMegaBytes(Double megaBytes) {
        this.megaBytes = megaBytes;
    }

}
