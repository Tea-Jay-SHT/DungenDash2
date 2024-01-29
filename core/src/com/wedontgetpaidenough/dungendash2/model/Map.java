/**
 * @author tkelly
 * @createdOn 1/24/2024 at 11:02 PM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.model;
 */
package com.wedontgetpaidenough.dungendash2.model;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.JsonValue;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private String mapName;
    private OrthogonalTiledMapRenderer map;
    private ArrayList<Rectangle> collisionRectangles = new ArrayList<>();
    private HashMap<String,Point> enemySpawns = new HashMap<>();
    private HashMap<Rectangle,WarpZone> warpZones = new HashMap<>();
    private HashMap<String,Rectangle> specialEvents = new HashMap<>();
    private HashMap<Rectangle,Dialauge> dialaugeMap = new HashMap<>();

    public Map(JsonValue reader,int tileScale){
        mapName = reader.getString("mapName");
        this.map = new OrthogonalTiledMapRenderer(new TmxMapLoader().load(reader.getString("map")));
        for(JsonValue colisionRect:reader.get("collision").iterator()){
            collisionRectangles.add(
                    new Rectangle(colisionRect.getInt("x"),colisionRect.getInt("y"),colisionRect.getInt("l"),colisionRect.getInt("w")));
        }
        for(JsonValue enemySpawn:reader.get("enemySpawns")){
            enemySpawns.put(
                    enemySpawn.getString("enemyType"),
                    new Point(enemySpawn.getInt("x"),enemySpawn.getInt("y"))
            );
        }
        for(JsonValue warpZone:reader.get("warpZones").iterator()){
            warpZones.put(
                    new Rectangle(warpZone.getInt("x"),warpZone.getInt("y"),warpZone.getInt("l"),warpZone.getInt("w")),
                    new WarpZone(
                            new Point(warpZone.getInt("spawnX"),warpZone.getInt("spawnY")),
                            warpZone.getString("destination")));
        }
        for(JsonValue event:reader.get("specialEvents")){
            specialEvents.put(
                    event.getString("eventFunction"),
                    new Rectangle(event.getInt("x"),event.getInt("y"),event.getInt("l"),event.getInt("w"))
            );
        }
        for(JsonValue dialauge:reader.get("dialaugeZones")){
            dialaugeMap.put(
              new Rectangle(dialauge.getInt("x"),dialauge.getInt("y"),dialauge.getInt("l"),dialauge.getInt("w")),
              new Dialauge(dialauge.get("data"))
            );
        }
        System.out.println("Hi");
    }
    void run(){

    }
}
