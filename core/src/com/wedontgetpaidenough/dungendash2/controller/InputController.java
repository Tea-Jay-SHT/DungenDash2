/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:53 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.controller;
 */
package com.wedontgetpaidenough.dungendash2.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.wedontgetpaidenough.dungendash2.model.GameState;

import java.awt.*;
import java.util.ArrayList;

public class InputController {
    GameState state;
    private Double maxSpeed = 10d,movementSpeed = 50d,decayRate = 10d,minimumMomentum = 16d; //todo tweak until this feels good
    public InputController(GameState state){
        this.state = state;
    }
    public void doInput(){
        switch (state.getGameState()){
            case Playing:
                input();
            case MainMenu:
                mouseinput();
            case Inventory:
            case PauseMenu:
            case Dialauge:
                break;
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
        int oldx = playerRectangle.x;
        playerRectangle.x += state.getxMomentum();
        for(Rectangle colider :state.getCurrentMap().getCollisionRectangles()){
            if(playerRectangle.intersects(colider)){
                playerRectangle.x =oldx;
                state.setxMomentum(0d);
                break;
            }
        }
        int oldy = playerRectangle.y;
        playerRectangle.y += state.getyMomentum();
        for(Rectangle colider :state.getCurrentMap().getCollisionRectangles()){
            if(playerRectangle.intersects(colider)){
                playerRectangle.y = oldy;
                state.setyMomentum(0d);
                break;
            }
        }
        state.setPlayerRectangle(playerRectangle);
        state.setxMomentum(state.getxMomentum()-(state.getxMomentum()*delta*decayRate)); //apply friction/decay
        state.setyMomentum(state.getyMomentum()-(state.getyMomentum()*delta*decayRate));
        //todo check warp zones and event zones
        for(Rectangle colider:state.getCurrentMap().getWarpZones().keySet()){
            if(playerRectangle.intersects(colider)){
                state.switchMap(state.getCurrentMap().getWarpZones().get(colider));
                break;
            }
        }
        for(Rectangle colider:state.getCurrentMap().getSpecialEvents().keySet()){
            if(playerRectangle.intersects(colider)){
                state.getEventController().specialEvents(state.getCurrentMap().getSpecialEvents().get(colider));
                break;
            }
        }
    }
}
