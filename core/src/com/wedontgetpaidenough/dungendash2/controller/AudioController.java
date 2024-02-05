/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:53 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.controller;
 */
package com.wedontgetpaidenough.dungendash2.controller;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.wedontgetpaidenough.dungendash2.model.GameState;

import java.io.File;
import java.util.HashMap;

public class AudioController {
    GameState state;
    Music musicMixer;
    HashMap<String, Sound> sounds;
    public AudioController(GameState state) {
        this.state = state;
    }
    public void swapMusic(){
        playMusic((String) state.getCurrentMap().getProperties().get("mapMusic"));
    }
    public void playMusic(String path){
        stopMusic();
        musicMixer = Gdx.audio.newMusic(Gdx.files.internal(path));
        musicMixer.setLooping(true);
        musicMixer.play();
    }
    private void stopMusic(){
        if(!(musicMixer ==null)){
            musicMixer.stop();
            musicMixer.dispose();
            musicMixer = null;
        }
    }
}