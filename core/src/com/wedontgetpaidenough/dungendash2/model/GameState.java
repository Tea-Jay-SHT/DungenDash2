/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.model;
 */
package com.wedontgetpaidenough.dungendash2.model;

import java.awt.*;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class GameState {
    public static final int TILE_SIZE = 128;
    private Double playerX,playerY,xMomentum,yMomentum;
    private Rectangle playerRectangle;
    private Map currentMap;
    private HashMap<String, Map> maps = new HashMap<>();

    public void init(){
        setupJson();
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
            maps.put(map.name,new Map(map,TILE_SIZE));
        }
    }
}
