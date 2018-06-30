package io.charstar.nudgeknights;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import static com.badlogic.gdx.graphics.GL20.GL_BLEND;

public class NudgeKnights extends Game implements Screen, InputProcessor {

  // Constants
  static final int WORLD_WIDTH = 1600;
  static final int WORLD_HEIGHT = 900;

  // World
  private Knight knight;

  // Camera
  private OrthographicCamera camera;
  private Vector3 cameraPosition;
  private int cameraLocked;

  // Rendering
  private SpriteBatch batch;
  private ShapeRenderer shapeRenderer;

  @Override
  public void create () {
    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();
    knight = new Knight(0, 0);

    camera = new OrthographicCamera(160, 90);
    camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    camera.update();

    batch.setProjectionMatrix(camera.combined);
    shapeRenderer.setProjectionMatrix(camera.combined);

    setScreen(this);
    Gdx.input.setInputProcessor(this);

    cameraPosition = new Vector3();
    cameraLocked = 1;
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
  public void render(float delta) {

    // Update World
    knight.update(delta);

    // Update Camera
    if(cameraLocked == 1){
      cameraPosition.set(knight.getPosition(), 0);
      camera.position.set(cameraPosition);
    }
    camera.update();
    batch.setProjectionMatrix(camera.combined);
    shapeRenderer.setProjectionMatrix(camera.combined);

    // Render Game
    Gdx.gl.glClearColor(cameraLocked, 1 - cameraLocked, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();

    knight.draw(batch);

    batch.end();

    Gdx.gl.glEnable(GL_BLEND);

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setColor(0, 0, 1, .5f);

    knight.draw(shapeRenderer);

    shapeRenderer.end();

    Gdx.gl.glDisable(GL_BLEND);
  }

  @Override
  public void dispose () {
    batch.dispose();
  }

  @Override
  public boolean keyDown(int keycode) {
    switch(keycode){
      case Input.Keys.SPACE:
        knight.jump();
        break;
      case Input.Keys.A:
        knight.moveLeft();
        break;
      case Input.Keys.D:
        knight.moveRight();
        break;
      case Input.Keys.LEFT:
        camera.translate(-5, 0);
        break;
      case Input.Keys.RIGHT:
        camera.translate(5, 0);
        break;
      case Input.Keys.UP:
        camera.translate(0, 5);
        break;
      case Input.Keys.DOWN:
        camera.translate(0, -5);
        break;
      case Input.Keys.Y:
        cameraLocked = 1 - cameraLocked;
      default:
        System.out.println("Unknown key pressed: " + keycode);
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    switch(keycode){
      case Input.Keys.A:
        knight.moveRight();
        break;
      case Input.Keys.D:
        knight.moveLeft();
        break;
      default:
        System.out.println("Unknown key released: " + keycode);
    }
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }
}
