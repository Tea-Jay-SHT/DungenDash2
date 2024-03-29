/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:48 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.dashcontroller;
 */
package com.wedontgetpaidenough.dungendash2.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.wedontgetpaidenough.dungendash2.model.GameState;
import com.wedontgetpaidenough.dungendash2.model.WarpZone;
import com.wedontgetpaidenough.dungendash2.view.Renderer;

import java.awt.*;

public class DashController {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private GameState state;
    private InputController inputController;
    private SpecialEventController eventController;
    private Renderer renderer;
    public DashController(SpriteBatch batch, OrthographicCamera camera, FitViewport viewport, GameState state) { //Setup all the internal stuff, setup the rest of stuff too.
        this.batch =batch;
        this.camera = camera;
        this.viewport = viewport;
        this.state = state;
        inputController = new InputController(state);
        renderer = new Renderer(state,batch,camera);
        state.setRenderer(renderer);
    }
    public void render(){
        switch(state.getGameState()){
            case Playing:
                inputController.doInput();
                renderer.render();
                camera.position.x = state.getPlayerRectangle().x+((float) GameState.TILE_SIZE /2);
                camera.position.y = state.getPlayerRectangle().y+((float) GameState.TILE_SIZE /2);
                inputController.lookForDialauge();
                break;
            case Dialauge:
                inputController.doInput();
                renderer.render();
                renderer.doDialauge(state.getCurrentDialauge());
                break;
            case MainMenu:
                inputController.doInput();
                renderer.title();
        }
        //System.out.println(state.getGameState().toString());
        //System.out.println(state.getPlayerRectangle().x+", "+state.getPlayerRectangle().y+" Momentum: "+state.getxMomentum()+", "+state.getyMomentum());
    }
    public void dispose(){
        //todo: Work down the tree and dispose of all assets.
    }
}