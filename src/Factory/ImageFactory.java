/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Model.Utilities.ImageFile;
import Services.Downloader;
import Services.FileManager;
import java.io.File;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class ImageFactory {

    private final Downloader downloader;

    public ImageFactory() {
        this.downloader = new Downloader();
    }

    public ImageFactory(Downloader downloader) {
        this.downloader = downloader;
    }
    
    public  ArrayList<ImageFile> generateImageByFile(ResultSet result) throws SQLException {
        
        ArrayList<ImageFile> images = new ArrayList<>();
        
        downloader.start();
        
        while (result.next()) {
            
            File localImage = new File(FileManager.getDefaultFolderWay() + result.getString("image_name"));
            
            File downloaded = downloader.download(result.getBinaryStream("image_bytes"), localImage);
            
            ImageFile image = new ImageFile(result.getString("file_way"));
            
             images.add(image);
        }
        
        return images;
    }
    
    public  ArrayList<ImageFile> generateImageByImage(ResultSet result) throws SQLException {
    
        ArrayList<ImageFile> images = new ArrayList<>();
        
        while (result.next()) {
            
            
            
            ImageFile image = new ImageFile(result.getString("file_way"));
            
             images.add(image);
        }
        
        return images;
    }
    
    public  ArrayList<ImageFile> generateImagesByPath(ResultSet result, String field) throws SQLException {
    
        ArrayList<ImageFile> images = new ArrayList<>();
        
        while (result.next()) {
            
            ImageFile image = new ImageFile(result.getString(field));
            
             images.add(image);
        }
        
        return images;
    }
    
}
