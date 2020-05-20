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
    private File file;
    private boolean playing = false;

    public SoundPlayer(String file) {
        
        this.file = new File (file);
        this.media = new Media(this.file.toURI().toString());
        System.out.println(media.getSource());
        SoundPlayer.player = new MediaPlayer(media);
        
    }
    public SoundPlayer(File file) {
        
        this.file = file;
        this.media = new Media(file.toURI().toString());
        System.out.println(media.getSource());
        SoundPlayer.player = new MediaPlayer(media);
        
    }
    
    @Override
    public void run (){
        
        player.setOnPlaying(() -> {
            playing = true;
        });
        
        player.setOnStopped(() -> {
            playing = false;
        });
        
        player.setOnPaused(() -> {
            playing = false;
        });
        
        player.setOnEndOfMedia(() -> {
            playing = false;
        });
    }
    
    public static void justPlaySound(){

        player.play();

        
        
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }  

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
    
    
    
}
