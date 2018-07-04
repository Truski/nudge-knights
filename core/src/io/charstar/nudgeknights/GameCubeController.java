package io.charstar.nudgeknights;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

public class GameCubeController extends ControllerAdapter {
  public static final int BUTTON_X = 0;
  public static final int BUTTON_A = 1;
  public static final int BUTTON_B = 2;
  public static final int BUTTON_Y = 3;
  public static final int BUTTON_L = 4;
  public static final int BUTTON_R = 5;
  public static final int BUTTON_Z = 6;
  public static final int BUTTON_START = 9;
  public static final int BUTTON_UP = 12;
  public static final int BUTTON_RIGHT = 13;
  public static final int BUTTON_DOWN = 14;
  public static final int BUTTON_LEFT = 15;
  public static final int C_STICK_Y = 0;
  public static final int C_STICK_X = 1;
  public static final int CONTROL_STICK_Y= 2;
  public static final int CONTROL_STICK_X = 3;
  public static final int R_TRIGGER = 4;
  public static final int L_TRIGGER = 5;

  private Controller controller;
  private Knight knight;
  private Camera camera;

  public GameCubeController(Controller controller){
    this.controller = controller;
    controller.addListener(this);
  }

  public void setCharacter(Knight knight){
    System.out.println("Set character");
    this.knight = knight;
  }

  public void setCamera(Camera camera){
    this.camera = camera;
  }

  public void disconnect(){
    controller.removeListener(this);
  }

  @Override
  public boolean buttonDown(Controller controller, int buttonIndex) {
    switch(buttonIndex){
      case BUTTON_A:
        knight.attack();
        break;
      case BUTTON_B:
        knight.block();
        break;
      case BUTTON_Y:
        camera.toggleLock();
        break;
      case BUTTON_UP:
        camera.startMoving(Direction.UP);
        break;
      case BUTTON_DOWN:
        camera.startMoving(Direction.DOWN);
        break;
      case BUTTON_LEFT:
        camera.startMoving(Direction.LEFT);
        break;
      case BUTTON_RIGHT:
        camera.startMoving(Direction.RIGHT);
        break;
    }
    return super.buttonDown(controller, buttonIndex);
  }

  @Override
  public boolean buttonUp(Controller controller, int buttonIndex) {
    switch(buttonIndex){
      case BUTTON_B:
        knight.stopBlocking();
        break;
      case BUTTON_UP:
        camera.stopMoving(Direction.UP);
        break;
      case BUTTON_DOWN:
        camera.stopMoving(Direction.DOWN);
        break;
      case BUTTON_LEFT:
        camera.stopMoving(Direction.LEFT);
        break;
      case BUTTON_RIGHT:
        camera.stopMoving(Direction.RIGHT);
        break;
    }
    return super.buttonUp(controller, buttonIndex);
  }

  @Override
  public boolean axisMoved(Controller controller, int axisIndex, float value) {
    switch(axisIndex){
      case CONTROL_STICK_X:
        if(value > .5){
          knight.moveRight();
        } else if (value < -.5){
          knight.moveLeft();
        } else if(knight.getVelocity().x < 0 && value >-.5){
          knight.stopMovingLeft();
        } else if(knight.getVelocity().x > 0 && value <.5){
          knight.stopMovingRight();
        }
    }
    return super.axisMoved(controller, axisIndex, value);
  }

  @Override
  public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
    return super.povMoved(controller, povIndex, value);
  }

  @Override
  public boolean xSliderMoved(Controller controller, int sliderIndex, boolean value) {
    return super.xSliderMoved(controller, sliderIndex, value);
  }

  @Override
  public boolean ySliderMoved(Controller controller, int sliderIndex, boolean value) {
    return super.ySliderMoved(controller, sliderIndex, value);
  }

  @Override
  public boolean accelerometerMoved(Controller controller, int accelerometerIndex, Vector3 value) {
    return super.accelerometerMoved(controller, accelerometerIndex, value);
  }

  @Override
  public void connected(Controller controller) {
    super.connected(controller);
  }

  @Override
  public void disconnected(Controller controller) {
    super.disconnected(controller);
  }
}
