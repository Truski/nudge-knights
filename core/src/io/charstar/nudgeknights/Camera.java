package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class Camera extends OrthographicCamera{
  private static final int SPEED = 35;

  private Vector3 velocity;
  private Knight knight;
  private boolean locked;

  public Camera(float viewportWidth, float viewportHeight){
    super(viewportWidth, viewportHeight);

    velocity = new Vector3();
    position.set(viewportWidth / 2f, viewportHeight / 2f, 0);
    locked = false;
    update();
  }

  public void follow(Knight knight){
    this.knight = knight;
  }

  public void update(float delta){
    if(locked){
      position.set(knight.getPosition().x, viewportHeight / 2, 0);
    } else {
      position.add(new Vector3(velocity).scl(delta));
    }
    position.set(position);
    super.update();
  }

  public void startMoving(Direction direction){
    switch(direction){
      case UP:
        velocity.y += SPEED;
        break;
      case DOWN:
        velocity.y -= SPEED;
        break;
      case LEFT:
        velocity.x -= SPEED;
        break;
      case RIGHT:
        velocity.x += SPEED;
        break;
    }
  }

  public void stopMoving(Direction direction){
    switch(direction){
      case UP:
        velocity.y -= SPEED;
        break;
      case DOWN:
        velocity.y += SPEED;
        break;
      case LEFT:
        velocity.x += SPEED;
        break;
      case RIGHT:
        velocity.x -= SPEED;
        break;
    }
  }

  public void toggleLock(){
    locked = !locked;
  }
}
