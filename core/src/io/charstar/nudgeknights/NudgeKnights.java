package io.charstar.nudgeknights;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class NudgeKnights extends Game implements Screen {

  // Constants
  private static final int WORLD_WIDTH = 1600;
  private static final int WORLD_HEIGHT = 900;
  private static final int VIEWPORT_WIDTH = 160;
  private static final int VIEWPORT_HEIGHT = 90;

  // World
  private Background background;
  private Knight redKnight;
  private Knight blueKnight;

  // Interface
  private HUD hud;

  // Camera
  private Camera camera;
  private OrthographicCamera hudCamera;

  // Rendering
  private SpriteBatch batch;
  private ShapeRenderer shapeRenderer;

  // Input
  private GameCubeController gameCubeController;
  private KeyboardController keyboardController;

  // Debug
  static boolean debug;

  @Override
  public void create () {

    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setAutoShapeType(true);
    background = new Background();

    redKnight = new Knight(Knight.RED);
    blueKnight = new Knight(Knight.BLUE);

    camera = new Camera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    camera.follow(redKnight);
    camera.toggleLock();
    hudCamera = new Camera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

    batch.setProjectionMatrix(camera.combined);
    shapeRenderer.setProjectionMatrix(camera.combined);

    setScreen(this);

    debug = false;

    Array<Controller> controllers = Controllers.getControllers();
    gameCubeController = new GameCubeController(controllers.get(0));
    gameCubeController.setCharacter(redKnight);
    gameCubeController.setCamera(camera);

    keyboardController = new KeyboardController();
    keyboardController.setCharacter(blueKnight);
    keyboardController.setCamera(camera);

    hud = new HUD();
    blueKnight.registerListener(hud);
    redKnight.registerListener(hud);
  }

  @Override
  public void render(float delta) {

    // Update World
    redKnight.update(delta);
    blueKnight.update(delta);
    background.update(camera.position);

    // Update Camera
    camera.update(delta);
    batch.setProjectionMatrix(camera.combined);
    shapeRenderer.setProjectionMatrix(camera.combined);

    // Render Game
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();

    background.draw(batch);
    redKnight.draw(batch);
    blueKnight.draw(batch);

    batch.end();

    batch.setProjectionMatrix(hudCamera.combined);
    shapeRenderer.setProjectionMatrix(hudCamera.combined);

    Gdx.gl.glEnable(GL20.GL_BLEND);
    shapeRenderer.begin();

    if(debug){
      redKnight.draw(shapeRenderer);
      blueKnight.draw(shapeRenderer);
    }
    hud.draw(batch, shapeRenderer);

    shapeRenderer.end();
    Gdx.gl.glDisable(GL20.GL_BLEND);
  }

  @Override
  public void show() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose () {
    batch.dispose();
    shapeRenderer.dispose();
  }
}
