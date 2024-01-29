/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.model;
 */
package com.wedontgetpaidenough.dungendash2.model;

import java.awt.*;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    private Double playerX,playerY,xMomentum,yMomentum;
    private Rectangle playerRectangle;
    private String currentMap;
    private HashMap<String, ArrayList<Rectangle>> coliderMap= new HashMap<>();

    public void init(){
        setupJson();
    }
    public void setupJson(){
        String content = "wompwomp";
        JsonValue reader;
        try {
            content = Files.readString(Paths.get("assets/jason/master.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader = new JsonReader().parse(content);
    }
}
