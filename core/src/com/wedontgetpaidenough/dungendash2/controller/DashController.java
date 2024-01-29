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

public class DashController {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    public DashController(SpriteBatch batch, OrthographicCamera camera, FitViewport viewport) {
        this.batch =batch;
        this.camera = camera;
        this.viewport = viewport;
    }
}
