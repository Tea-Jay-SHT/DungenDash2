/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.view;
 */
package com.wedontgetpaidenough.dungendash2.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.wedontgetpaidenough.dungendash2.Main;
import com.wedontgetpaidenough.dungendash2.model.GameState;

public class Renderer {
   OrthogonalTiledMapRenderer mapRenderer;
    Texture cooldude;
    GameState state;
    SpriteBatch batch;
    OrthographicCamera camera;
    public Renderer(GameState state, SpriteBatch batch, OrthographicCamera camera){
        this.state = state;
        this.batch = batch;
        this.camera = camera;
        mapRenderer = new OrthogonalTiledMapRenderer(new TmxMapLoader().load("assets/TileMaps/empty.tmx"));
        mapRenderer.getViewBounds().setWidth(Main.VIEWPORT_WIDTH).setHeight(Main.VIEWPORT_HEIGHT);
        mapRenderer.setView(camera);
        cooldude = new Texture("assets/Assets/coolasslildude.png");
    }
    public void newMap(){
        mapRenderer.setMap(state.getCurrentMap().getMap());
        mapRenderer.setView(camera);
    }
    public void render(){
        mapRenderer.setView(camera);
        mapRenderer.render(new int[]{0});
        batch.begin();
        batch.draw(cooldude,state.getPlayerRectangle().x,state.getPlayerRectangle().y);   //todo add animation logic
        batch.end();
        mapRenderer.render(new int[]{1});
    }
}
