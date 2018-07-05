package io.charstar.nudgeknights;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class KeyboardController implements InputProcessor {
  private Knight knight;
  private Camera camera;
  private boolean leftDown;
  private boolean rightDown;

  public KeyboardController() {
    Gdx.input.setInputProcessor(this);
  }

  public void setCharacter(Knight knight){
    this.knight = knight;
  }

  public void setCamera(Camera camera){
    this.camera = camera;
  }

  @Override
  public boolean keyDown(int keycode) {
    switch(keycode){
      case Input.Keys.A:
        leftDown = true;
        knight.moveLeft();
        break;
      case Input.Keys.D:
        rightDown = true;
        knight.moveRight();
        break;
      case Input.Keys.S:
        knight.block();
        break;
      case Input.Keys.UP:
        camera.startMoving(Direction.UP);
        break;
      case Input.Keys.DOWN:
        camera.startMoving(Direction.DOWN);
        break;
      case Input.Keys.LEFT:
        camera.startMoving(Direction.LEFT);
        break;
      case Input.Keys.RIGHT:
        camera.startMoving(Direction.RIGHT);
        break;
      case Input.Keys.Y:
        camera.toggleLock();
        break;
      case Input.Keys.GRAVE:
        NudgeKnights.debug = !NudgeKnights.debug;
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
        leftDown = false;
        if (rightDown) {
          knight.moveRight();
        } else {
          knight.stopMovingLeft();
        }
        break;
      case Input.Keys.D:
        rightDown = false;
        if (leftDown) {
          knight.moveLeft();
        } else {
          knight.stopMovingRight();
        }
        break;
      case Input.Keys.S:
        knight.stopBlocking();
        break;
      case Input.Keys.UP:
        camera.stopMoving(Direction.UP);
        break;
      case Input.Keys.DOWN:
        camera.stopMoving(Direction.DOWN);
        break;
      case Input.Keys.LEFT:
        camera.stopMoving(Direction.LEFT);
        break;
      case Input.Keys.RIGHT:
        camera.stopMoving(Direction.RIGHT);
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
