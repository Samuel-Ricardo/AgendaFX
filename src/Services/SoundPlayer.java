/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.InputStream;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Samuel
 */
public class SoundPlayer extends Thread{
   
    private MediaPlayer player;
    private Media media;
    private String file;
    /*    private InputStream input;*/

    public SoundPlayer(String file) {
        this.file = file;
        this.media = new Media(file);
        this.player = new MediaPlayer(media);
        
    }
    
    @Override
    public void run (){
        
        
        
    }
    
    public void justPlaySound(){
            
        player.play();
        
    }
    
    public void stopSound(){
        
        player.stop();
        
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    
    
}
