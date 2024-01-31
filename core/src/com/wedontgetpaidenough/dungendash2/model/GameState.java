/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.model;
 */
package com.wedontgetpaidenough.dungendash2.model;

import java.awt.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.wedontgetpaidenough.dungendash2.controller.SpecialEventController;
import com.wedontgetpaidenough.dungendash2.enums.State;
import com.wedontgetpaidenough.dungendash2.view.Renderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class GameState {
    public static final int TILE_SIZE = 128;
    private Double xMomentum=0d,yMomentum=0d;
    private Rectangle playerRectangle;
    private Map currentMap;
    private Dialauge currentDialauge;
    private Renderer renderer;
    private State gameState = State.Playing;
    private HashMap<String, Texture> talkingSprites;
    private SpecialEventController eventController;
    private HashMap<String, Map> maps = new HashMap<>();
    //region init
    public void init(){
        setupJson();
        setupTalkingSprites();
        playerRectangle = new Rectangle(0,0,TILE_SIZE,TILE_SIZE);
        eventController = new SpecialEventController();
    }

    private void setupTalkingSprites() {
        talkingSprites = new HashMap<>();
        File dir = new File("assets/Assets/talkingSprites/");
        for(String file: dir.list()){
            talkingSprites.put(file.replaceFirst(".png",""),new Texture("assets/Assets/talkingSprites/"+file));
        }
        System.out.println("hi");
    }

    private void setupJson(){
        String content = "wompwomp";
        JsonValue reader;
        try {
            content = Files.readString(Paths.get("assets/Data/master.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader = new JsonReader().parse(content);
        for(JsonValue map:reader.iterator()){
            maps.put(map.name,new Map(map,TILE_SIZE,this));
        }
    }
    //endregion
    public void switchMap(WarpZone warpZone){  //switch map, useful for the warp zones in map object
        currentMap = maps.get(warpZone.getDestanation());
        playerRectangle.x =(int) Math.round(warpZone.getSpawnLocation().getX());
        playerRectangle.y =(int) Math.round(warpZone.getSpawnLocation().getY());
        xMomentum = 0d;
        yMomentum = 0d;
        renderer.newMap();
    }
    //region getters/setters
    public Map getCurrentMap(){return currentMap;}
    public void addXMomentum(double amount){xMomentum += amount;}
    public void addYMomentum(double amount){yMomentum += amount;}
    public Double getxMomentum() {return xMomentum;}
    public Double getyMomentum() {return yMomentum;}
    public void setxMomentum(Double xMomentum) {this.xMomentum = xMomentum;}
    public void setyMomentum(Double yMomentum) {this.yMomentum = yMomentum;}
    public void setRenderer(Renderer renderer){this.renderer = renderer;}
    public Rectangle getPlayerRectangle(){return(Rectangle) playerRectangle.clone();}
    public void setPlayerRectangle(Rectangle playerRectangle){this.playerRectangle = playerRectangle;}
    public State getGameState() {return gameState;}
    public void setGameState(State gameState) {this.gameState = gameState;}
    public SpecialEventController getEventController(){return eventController;}
    public Renderer getRenderer() {return renderer;}
    public Dialauge getCurrentDialauge() {return currentDialauge;}
    public void setCurrentDialauge(Dialauge currentDialauge) {this.currentDialauge = currentDialauge;}
    public HashMap<String, Texture> getTalkingSprites() {return talkingSprites;}
    //endregion
}
