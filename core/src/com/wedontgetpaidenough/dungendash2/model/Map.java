/**
 * @author tkelly
 * @createdOn 1/24/2024 at 11:02 PM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.model;
 */
package com.wedontgetpaidenough.dungendash2.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.JsonValue;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private String mapName;
    private TiledMap map;
    private ArrayList<Rectangle> collisionRectangles = new ArrayList<>();
    private HashMap<String,Point> enemySpawns = new HashMap<>(); //todo implement enemySpawns
    private HashMap<Rectangle,WarpZone> warpZones = new HashMap<>();
    private HashMap<Rectangle,String> specialEvents = new HashMap<>();
    private HashMap<Rectangle,Dialauge> dialaugeMap = new HashMap<>(); //todo implement dialaugeMap
    public Map(JsonValue reader,int tileScale,GameState state){   //Json parser that scales everything with the tile size
        mapName = reader.getString("mapName");
        this.map = new TmxMapLoader().load(reader.getString("map"));
        for(JsonValue colisionRect:reader.get("collision").iterator()){
            collisionRectangles.add(
                    new Rectangle((int) Math.round(colisionRect.getDouble("x")*tileScale),(int) Math.round(colisionRect.getDouble("y")*tileScale),(int) Math.round(colisionRect.getDouble("l")*tileScale),(int) Math.round(colisionRect.getDouble("w")*tileScale)));
        }
        for(JsonValue enemySpawn:reader.get("enemySpawns")){
            enemySpawns.put(
                    enemySpawn.getString("enemyType"),
                    new Point((int) Math.round(enemySpawn.getDouble("x")*tileScale),(int) Math.round(enemySpawn.getDouble("y")*tileScale))
            );
        }
        for(JsonValue warpZone:reader.get("warpZones").iterator()){
            warpZones.put(
                    new Rectangle((int) Math.round(warpZone.getDouble("x")*tileScale),(int) Math.round(warpZone.getDouble("y")*tileScale),(int) Math.round(warpZone.getDouble("l")*tileScale),(int) Math.round(warpZone.getDouble("w")*tileScale)),
                    new WarpZone(
                            new Point((int) Math.round(warpZone.getDouble("spawnX")*tileScale),(int) Math.round(warpZone.getDouble("spawnY")*tileScale)),
                            warpZone.getString("destination")));
        }
        for(JsonValue event:reader.get("specialEvents")){
            specialEvents.put(
                    new Rectangle((int) Math.round(event.getDouble("x")*tileScale),(int) Math.round(event.getDouble("y")*tileScale),(int) Math.round(event.getDouble("l")*tileScale),(int) Math.round(event.getDouble("w")*tileScale)),
                    event.getString("eventFunction")
            );
        }
        for(JsonValue dialauge:reader.get("dialaugeZones")){
            dialaugeMap.put(
              new Rectangle((int) Math.round(dialauge.getDouble("x")*tileScale),(int) Math.round(dialauge.getDouble("y")*tileScale),(int) Math.round(dialauge.getDouble("l")*tileScale),(int) Math.round(dialauge.getDouble("w")*tileScale)),
              new Dialauge(dialauge.get("data"),state)
            );
        }
    }
    public TiledMap getMap(){return map;}
    public ArrayList<Rectangle> getCollisionRectangles(){return collisionRectangles;}
    public HashMap<String, Point> getEnemySpawns() {return enemySpawns;}
    public HashMap<Rectangle, WarpZone> getWarpZones() {return warpZones;}
    public HashMap<Rectangle,String> getSpecialEvents() {return specialEvents;}
    public HashMap<Rectangle, Dialauge> getDialaugeMap() {return dialaugeMap;}
}