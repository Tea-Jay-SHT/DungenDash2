/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:53 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.controller;
 */
package com.wedontgetpaidenough.dungendash2.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.JsonReader;
import com.wedontgetpaidenough.dungendash2.enums.State;
import com.wedontgetpaidenough.dungendash2.model.Dialauge;
import com.wedontgetpaidenough.dungendash2.model.GameState;
import com.badlogic.gdx.math.Rectangle;
import com.wedontgetpaidenough.dungendash2.model.WarpZone;

import java.awt.*;

public class InputController {
    GameState state;
    private Double maxSpeed = 12d,movementSpeed = 100d,decayRate = 10d,minimumMomentum = 16d; //todo tweak until this feels good
    public InputController(GameState state){
        this.state = state;
    }
    public void doInput(){
        switch (state.getGameState()){
            case Playing:
                input();
            case MainMenu:
                mouseinput();
                break;
            case Inventory:
            case PauseMenu:
            case Dialauge:
                dialaugeinput();
                break;
        }
    }

    private void dialaugeinput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            state.getCurrentDialauge().next();
        }
    }

    private void mouseinput() {//todo localized mouse input

    }

    public void input(){ //todo check for 2 directional movements
        double delta = Gdx.graphics.getDeltaTime(); //todo controller input
        if (Gdx.input.isKeyPressed(Input.Keys.W)){           //take raw inputs and add them to the momentum
            state.addYMomentum(movementSpeed*delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            state.addYMomentum((0d-movementSpeed)*delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            state.addXMomentum((0d-movementSpeed)*delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            state.addXMomentum(movementSpeed*delta);
        }
        if (state.getxMomentum()<(minimumMomentum*delta) && state.getxMomentum()>(0d-minimumMomentum)*delta){  // if momentum is too low, make it 0
            state.setxMomentum(0d);
        }
        if (state.getyMomentum()<(minimumMomentum*delta) && state.getyMomentum()>(0d-minimumMomentum)*delta){
            state.setyMomentum(0d);
        }
        if (state.getxMomentum() > maxSpeed){ // if its too fast, then make it the max speed
            state.setxMomentum(maxSpeed);
        }
        if (state.getxMomentum() < 0d-maxSpeed){
            state.setxMomentum(0d-maxSpeed);
        }
        if (state.getyMomentum() > maxSpeed){
            state.setyMomentum(maxSpeed);
        }
        if (state.getyMomentum() < 0d-maxSpeed){
            state.setyMomentum(0d-maxSpeed);
        }
        Rectangle playerRectangle = state.getPlayerRectangle();                     //check if it colides on x first then y
        float oldx = playerRectangle.x;
        playerRectangle.x += state.getxMomentum();
        for(MapObject colider :state.getCurrentMap().getLayers().get("coliders").getObjects()){
            if(playerRectangle.overlaps(((RectangleMapObject) colider).getRectangle())){
                playerRectangle.x =oldx;
                state.setxMomentum(0d);
                break;
            }
        }
        float oldy = playerRectangle.y;
        playerRectangle.y += state.getyMomentum();
        for(MapObject colider :state.getCurrentMap().getLayers().get("coliders").getObjects()){
            if(playerRectangle.overlaps(((RectangleMapObject) colider).getRectangle())){
                playerRectangle.y = oldy;
                state.setyMomentum(0d);
                break;
            }
        }
        state.setPlayerRectangle(playerRectangle);
        state.setxMomentum(state.getxMomentum()-(state.getxMomentum()*delta*decayRate)); //apply friction/decay
        state.setyMomentum(state.getyMomentum()-(state.getyMomentum()*delta*decayRate));
        for(MapObject colider :state.getCurrentMap().getLayers().get("warpZones").getObjects()){
            if(playerRectangle.overlaps(((RectangleMapObject) colider).getRectangle())){
                state.switchMap(
                        new WarpZone(
                                new Point((int)colider.getProperties().get("spawnX"),(int)colider.getProperties().get("spawnY")),
                                (String) colider.getProperties().get("spawnLocation")));
                break;
            }
        }
        for(MapObject colider :state.getCurrentMap().getLayers().get("specialEvents").getObjects()){
            if(playerRectangle.overlaps(((RectangleMapObject) colider).getRectangle())){
                state.getEventController().specialEvents((String)colider.getProperties().get("eventFunction"));
                break;
            }
        }
    }

    public void lookForDialauge() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            for(MapObject colider :state.getCurrentMap().getLayers().get("dialauge").getObjects()){
                if(state.getPlayerRectangle().overlaps(((RectangleMapObject) colider).getRectangle())){
                    state.setGameState(State.Dialauge);
                    state.setCurrentDialauge(new Dialauge(new JsonReader().parse((String)colider.getProperties().get("dialaugeData")),state));
                    break;
                }
            }
        }
    }
}
