package io.charstar.nudgeknights;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class NudgeKnights extends Game implements Screen, InputProcessor {

  // Constants
  private static final int WORLD_WIDTH = 1600;
  private static final int WORLD_HEIGHT = 900;
  private static final int CAMERA_SPEED = 35;
  private static final int VIEWPORT_WIDTH = 160;
  private static final int VIEWPORT_HEIGHT = 90;

  // World
  private Background background;
  private Knight knight;

  // Camera
  private OrthographicCamera camera;
  private Vector3 cameraVelocity;
  private Vector3 cameraPosition;
  private int cameraLocked;

  // Rendering
  private SpriteBatch batch;
  private ShapeRenderer shapeRenderer;

  // Debug
  private boolean debug;

  @Override
  public void create () {
    batch = new SpriteBatch();
    shapeRenderer = new ShapeRenderer();
    background = new Background();
    knight = new Knight(0, 0);

    camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    camera.update();

    batch.setProjectionMatrix(camera.combined);
    shapeRenderer.setProjectionMatrix(camera.combined);

    setScreen(this);
    Gdx.input.setInputProcessor(this);

    cameraVelocity = new Vector3();
    cameraPosition = new Vector3();
    cameraLocked = 1;

    debug = false;
  }

  @Override
  public void render(float delta) {

    // Update World
    knight.update(delta);
    background.update(cameraPosition);

    // Update Camera
    if(cameraLocked == 1){
      cameraPosition.set(knight.getPosition().x, VIEWPORT_HEIGHT / 2, 0);
    } else if(cameraLocked == 0){
      cameraPosition.add(new Vector3(cameraVelocity).scl(delta));
    }
    camera.position.set(cameraPosition);
    camera.update();
    batch.setProjectionMatrix(camera.combined);
    shapeRenderer.setProjectionMatrix(camera.combined);

    // Render Game
    Gdx.gl.glClearColor(cameraLocked, 1 - cameraLocked, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();

    background.draw(batch);
    knight.draw(batch);

    batch.end();

    if(debug){
      Gdx.gl.glEnable(GL20.GL_BLEND);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
      shapeRenderer.setColor(0, 0, 1, .5f);

      knight.draw(shapeRenderer);

      shapeRenderer.end();
      Gdx.gl.glDisable(GL20.GL_BLEND);
    }
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

  @Override
  public boolean keyDown(int keycode) {
    switch(keycode){
      case Input.Keys.A:
        knight.moveLeft();
        break;
      case Input.Keys.D:
        knight.moveRight();
        break;
      case Input.Keys.S:
        knight.block();
        break;
      case Input.Keys.LEFT:
        cameraVelocity.x -= CAMERA_SPEED;
        break;
      case Input.Keys.RIGHT:
        cameraVelocity.x += CAMERA_SPEED;
        break;
      case Input.Keys.UP:
        cameraVelocity.y += CAMERA_SPEED;
        break;
      case Input.Keys.DOWN:
        cameraVelocity.y -= CAMERA_SPEED;
        break;
      case Input.Keys.Y:
        cameraLocked = 1 - cameraLocked;
        break;
      case Input.Keys.GRAVE:
        debug = !debug;
        break;
      default:
        System.out.println("Unknown key pressed: " + keycode);
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    switch(keycode){
      case Input.Keys.A:
        knight.stopMovingLeft();
        break;
      case Input.Keys.D:
        knight.stopMovingRight();
        break;
      case Input.Keys.S:
        knight.stopBlocking();
        break;
      case Input.Keys.LEFT:
        cameraVelocity.x += CAMERA_SPEED;
        break;
      case Input.Keys.RIGHT:
        cameraVelocity.x -= CAMERA_SPEED;
        break;
      case Input.Keys.UP:
        cameraVelocity.y -= CAMERA_SPEED;
        break;
      case Input.Keys.DOWN:
        cameraVelocity.y += CAMERA_SPEED;
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
    knight.attack();
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
