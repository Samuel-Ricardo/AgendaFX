/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.time.Duration;
import javafx.scene.media.Media;

/**
 *
 * @author Samuel
 */
public class Sound {
    
    private String name;
    private File soundFile;
    private Media media;
    private boolean tocando;
    private Duration totalDuration;

    public Sound(File music) {
     
        this.soundFile = music;
        this.media = new Media(soundFile.getAbsolutePath());
        this.name = soundFile.getName();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(File soundFile) {
        this.soundFile = soundFile;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public boolean isTocando() {
        return tocando;
    }

    public void setTocando(boolean tocando) {
        this.tocando = tocando;
    }

    public Duration getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Duration totalDuration) {
        this.totalDuration = totalDuration;
    }
    
    
    
    
}
