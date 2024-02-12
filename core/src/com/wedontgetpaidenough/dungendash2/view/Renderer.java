/**
 * @author tkelly
 * @createdOn 1/18/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.view;
 */
package com.wedontgetpaidenough.dungendash2.view;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.wedontgetpaidenough.dungendash2.Main;
import com.wedontgetpaidenough.dungendash2.model.Dialauge;
import com.wedontgetpaidenough.dungendash2.model.GameState;
import java.awt.*;
import java.util.Iterator;

public class Renderer {
    public static final int xOffset = 896,yOffset = 476, WIDTH = 1920, HEIGHT = 1080;
   OrthogonalTiledMapRenderer mapRenderer;
   BitmapFont font;
    Texture cooldude;
    GameState state;
    SpriteBatch batch;
    OrthographicCamera camera;
    private Texture dialaugeFrame;
    int[] paralaxValues = {0,0,0,0};
    Texture[] paralaxImages = new Texture[4];
    Texture[] titleSelector = new Texture[4];
    Texture logo;
    public Renderer(GameState state, SpriteBatch batch, OrthographicCamera camera){
        this.state = state;
        this.batch = batch;
        this.camera = camera;
        dialaugeFrame = new Texture("assets/Assets/dialaugeframe2.png");
        mapRenderer = new OrthogonalTiledMapRenderer(new TmxMapLoader().load("assets/TileMaps/empty.tmx"));
        mapRenderer.getViewBounds().setWidth(Main.VIEWPORT_WIDTH).setHeight(Main.VIEWPORT_HEIGHT);
        mapRenderer.setView(camera);
        font = new BitmapFont();
        font.getData().setScale(3);
        font.setColor(Color.BLACK);
        cooldude = new Texture("assets/Assets/coolasslildude.png");
        logo = new Texture("assets/Assets/titlescreen/logo.png");
        paralaxImages[0] = new Texture("assets/Assets/titlescreen/1.png");
        paralaxImages[1] = new Texture("assets/Assets/titlescreen/2.png");
        paralaxImages[2] = new Texture("assets/Assets/titlescreen/3.png");
        paralaxImages[3] = new Texture("assets/Assets/titlescreen/4.png");
        titleSelector[0] = new Texture("assets/Assets/titlescreen/selector0.png");
        titleSelector[1] = new Texture("assets/Assets/titlescreen/selector1.png");
        titleSelector[2] = new Texture("assets/Assets/titlescreen/selector2.png");
        titleSelector[3] = new Texture("assets/Assets/titlescreen/selector3.png");
    }
    public void newMap(){
        mapRenderer.setMap(state.getCurrentMap());
        mapRenderer.setView(camera);
    }
    public void render(){
        mapRenderer.setView(camera);
        mapRenderer.render(new int[]{0,1,2,3,4});
        batch.begin();
        batch.draw(cooldude,state.getPlayerRectangle().x,state.getPlayerRectangle().y);   //todo add animation logic
        batch.end();
        mapRenderer.render(new int[]{5,6,7,8,9});
    }
    public void doDialauge(Dialauge dialauge) {
        if (dialauge.getText().size() == dialauge.getSprites().size()){
                batch.begin();
                batch.draw(dialaugeFrame,Math.round(state.getPlayerRectangle().getX()-xOffset),Math.round(state.getPlayerRectangle().getY()-yOffset));
                font.draw(batch,dialauge.getText().get(dialauge.getIterator()),Math.round(state.getPlayerRectangle().getX()-xOffset+70),Math.round(state.getPlayerRectangle().getY()-yOffset+180),0,dialauge.getText().get(dialauge.getIterator()).length(),1740,0,true);
                batch.draw(state.getTalkingSprites().get(dialauge.getSprites().get(dialauge.getIterator())),Math.round(state.getPlayerRectangle().getX()-xOffset+70),Math.round(state.getPlayerRectangle().getY()-yOffset+250),GameState.TILE_SIZE*3,GameState.TILE_SIZE*3);
                batch.end();
        }
    }
    public void title() {
        batch.begin();
        for (int i = 0; i < paralaxImages.length; i++) {
            paralaxValues[i]-=(i+1);
            if (paralaxValues[i] <= -WIDTH){
            paralaxValues[i] += WIDTH;
            }
            batch.draw(paralaxImages[i],paralaxValues[i],0,WIDTH,HEIGHT);
            batch.draw(paralaxImages[i],paralaxValues[i]+WIDTH,0,WIDTH,HEIGHT);
        }
        batch.draw(titleSelector[state.getSelection()],16,-750,1024,1024);
        batch.draw(logo, 540,520);
        batch.end();
    }
}