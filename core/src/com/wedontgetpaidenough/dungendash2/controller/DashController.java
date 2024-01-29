/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:48 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.dashcontroller;
 */
package com.wedontgetpaidenough.dungendash2.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.wedontgetpaidenough.dungendash2.enums.State;
import com.wedontgetpaidenough.dungendash2.model.GameState;
import com.wedontgetpaidenough.dungendash2.model.WarpZone;
import com.wedontgetpaidenough.dungendash2.view.Renderer;

import java.awt.*;

public class DashController {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private GameState state;
    private State currentState = State.MainMenu;
    private InputController inputController;
    private Renderer renderer;
    public DashController(SpriteBatch batch, OrthographicCamera camera, FitViewport viewport, GameState state) { //Setup all the internal stuff, setup the rest of stuff too.
        this.batch =batch;
        this.camera = camera;
        this.viewport = viewport;
        this.state = state;
        inputController = new InputController(state);
        renderer = new Renderer(state,batch,camera);
        state.setRenderer(renderer);
        state.switchMap(new WarpZone(new Point(128,128),"stage"));
    }
    public void render(){  //Render order go here :3
        inputController.doInput();   //todo make a non-jank solution to display the title screen probably use state.
        renderer.render();
        camera.position.x = state.getPlayerRectangle().x+(GameState.TILE_SIZE/2);
        camera.position.y = state.getPlayerRectangle().y+(GameState.TILE_SIZE/2);
        System.out.println(state.getPlayerRectangle().x+", "+state.getPlayerRectangle().y+" Momentum: "+state.getxMomentum()+", "+state.getyMomentum())    ;
    }
    public void dispose(){
        //todo: Work down the tree and dispose of all assets.
    }
}
//Take Input
//Check Colision
//Apply Momentum
//Render map layer 1
//Render player
//render map layer 2
//render UI