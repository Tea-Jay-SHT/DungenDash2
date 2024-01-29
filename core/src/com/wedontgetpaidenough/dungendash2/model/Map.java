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
        //this.map = new OrthogonalTiledMapRenderer(new TmxMapLoader().load(reader.getString("map")));
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
                    event.getString("eventFunction"),
                    new Rectangle((int) Math.round(event.getDouble("x")*tileScale),(int) Math.round(event.getDouble("y")*tileScale),(int) Math.round(event.getDouble("l")*tileScale),(int) Math.round(event.getDouble("w")*tileScale))
            );
        }
        for(JsonValue dialauge:reader.get("dialaugeZones")){
            dialaugeMap.put(
              new Rectangle((int) Math.round(dialauge.getDouble("x")*tileScale),(int) Math.round(dialauge.getDouble("y")*tileScale),(int) Math.round(dialauge.getDouble("l")*tileScale),(int) Math.round(dialauge.getDouble("w")*tileScale)),
              new Dialauge(dialauge.get("data"))
            );
        }
        System.out.println("Hi");
    }
}
