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
	private final int VIEWPORT_WIDTH = 1920,VIEWPORT_HEIGHT = 1080;
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
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
		batch.setProjectionMatrix(camera.combined);
		viewport = new FitViewport(VIEWPORT_WIDTH,VIEWPORT_HEIGHT,camera);
		controller = new DashController(batch,camera,viewport);
		state = new GameState();
		state.init();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		//silly stuff happening here :3
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
