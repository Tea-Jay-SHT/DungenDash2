package com.wedontgetpaidenough.dungendash2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.wedontgetpaidenough.dungendash2.controller.DashController;
import com.wedontgetpaidenough.dungendash2.model.GameState;

public class Main extends ApplicationAdapter {
	public static final int VIEWPORT_WIDTH = 1920,VIEWPORT_HEIGHT = 1080;
	DashController controller;
	SpriteBatch batch;
	OrthographicCamera camera;
	FitViewport viewport;
	GameState state;
	@Override
	public void resize(int width,int height){
		viewport.update(width, height, true);
	}
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		viewport = new FitViewport(VIEWPORT_WIDTH,VIEWPORT_HEIGHT,camera);
		state = new GameState();
		state.init();
		controller = new DashController(batch,camera,viewport,state);
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		controller.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		controller.dispose();
	}
}
//Take Input
//Check Colision
//Apply Momentum
//Render map layer 1
//Render player
//render map layer 2
//render UI