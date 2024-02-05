/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.model;
 */
package com.wedontgetpaidenough.dungendash2.model;

import java.awt.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.wedontgetpaidenough.dungendash2.controller.AudioController;
import com.wedontgetpaidenough.dungendash2.controller.SpecialEventController;
import com.wedontgetpaidenough.dungendash2.enums.State;
import com.wedontgetpaidenough.dungendash2.view.Renderer;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class GameState {
    public static final int TILE_SIZE = 128;
    private Double xMomentum=0d,yMomentum=0d;
    private Rectangle playerRectangle;
    private Dialauge currentDialauge;
    private Renderer renderer;
    private State gameState = State.Playing;
    private HashMap<String, Texture> talkingSprites;
    private SpecialEventController eventController;
    private AudioController audioController;
    private HashMap<String, TiledMap> maps = new HashMap<>();
    private TiledMap currentMap;
    //region init
    public void init(){
        setupMaps();
        setupTalkingSprites();
        playerRectangle = new Rectangle(0,0,TILE_SIZE,TILE_SIZE);
        eventController = new SpecialEventController(this);
        audioController = new AudioController(this);
    }

    private void setupTalkingSprites() {
        talkingSprites = new HashMap<>();
        File dir = new File("assets/Assets/talkingSprites/");
        for(String file: dir.list()){
            talkingSprites.put(file.replaceFirst(".png",""),new Texture("assets/Assets/talkingSprites/"+file));
        }
    }

    private void setupMaps(){
        for (File dirs : Gdx.files.internal("assets/TileMaps").file().listFiles(File::isDirectory)) {
            if(dirs.listFiles() != null){
                for (File file : dirs.listFiles()){
                    if (file.getName().contains(".tmx")) {
                        maps.put((String) new TmxMapLoader().load(file.getPath()).getProperties().get("mapName"), new TmxMapLoader().load(file.getPath()));
                    }
                }
            }
        }
    }
    //endregion
    public void switchMap(WarpZone warpZone){
        currentMap = maps.get(warpZone.getDestanation());
        playerRectangle.x =(int) Math.round(warpZone.getSpawnLocation().getX());
        playerRectangle.y =(int) Math.round(warpZone.getSpawnLocation().getY());
        xMomentum = 0d;
        yMomentum = 0d;
        renderer.newMap();
        audioController.swapMusic();
    }
    //region getters/setters
    public void addXMomentum(double amount){xMomentum += amount;}
    public void addYMomentum(double amount){yMomentum += amount;}
    public Double getxMomentum() {return xMomentum;}
    public Double getyMomentum() {return yMomentum;}
    public void setxMomentum(Double xMomentum) {this.xMomentum = xMomentum;}
    public void setyMomentum(Double yMomentum) {this.yMomentum = yMomentum;}
    public void setRenderer(Renderer renderer){this.renderer = renderer;}
    public Rectangle getPlayerRectangle(){return(Rectangle) playerRectangle;}
    public void setPlayerRectangle(Rectangle playerRectangle){this.playerRectangle = playerRectangle;}
    public State getGameState() {return gameState;}
    public void setGameState(State gameState) {this.gameState = gameState;}
    public SpecialEventController getEventController(){return eventController;}
    public Renderer getRenderer() {return renderer;}
    public Dialauge getCurrentDialauge() {return currentDialauge;}
    public void setCurrentDialauge(Dialauge currentDialauge) {this.currentDialauge = currentDialauge;}
    public HashMap<String, Texture> getTalkingSprites() {return talkingSprites;}
    public AudioController getAudioController() {return audioController;}
    public TiledMap getCurrentMap(){return currentMap;}
//endregion
}