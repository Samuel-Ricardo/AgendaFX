/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Samuel
 */
public class SoundPlayer extends Thread{
   
    private static MediaPlayer player;
    private Media media;
    private String file;
    /*    private InputStream input;*/

    public SoundPlayer(String file) {
        this.file = file;
        this.media = new Media(new File(file).toURI().toString());
        System.out.println(media.getSource());
        SoundPlayer.player = new MediaPlayer(media);
        
    }
    
    @Override
    public void run (){
        
        
        
    }
    
    public static void justPlaySound(){
           
        System.out.println(player.getVolume()+" V2");
        player.play();
        System.out.println(player.getVolume()+" V");
        
    }
    
    public static void stopSound(){
        
        player.stop();
        
    }

    public static MediaPlayer getPlayer() {
        return player;
    }

    public static void setPlayer(MediaPlayer player) {
        SoundPlayer.player = player;
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
