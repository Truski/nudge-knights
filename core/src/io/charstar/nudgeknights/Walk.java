package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Walk extends State {

  private static final TextureRegion[] redFrames = TextureRegion.split(
      new Texture("knight/red/walk.png"), Knight.WIDTH, Knight.HEIGHT)[0];
  private static final TextureRegion[] blueFrames = TextureRegion.split(
      new Texture("knight/blue/walk.png"), Knight.WIDTH, Knight.HEIGHT)[0];
  private static final TextureRegion[][] frames = {redFrames, blueFrames};
  private static final int NUM_FRAMES = 8;
  private static final int FPS = 10;
  private static final float BACKWARDS_MULTIPLIER = .5f;

  private float animationTime;

  private boolean backwards;
  private Direction moveDirection;

  @Override
  public void start() {
    if(knight.getVelocity().x > 0){
      moveDirection = Direction.RIGHT;
    } else {
      moveDirection = Direction.LEFT;
    }
    backwards = moveDirection == Direction.LEFT && knight.getTurnDirection() == Direction.RIGHT
        || moveDirection == Direction.RIGHT && knight.getTurnDirection() == Direction.LEFT;
    if(backwards){
      knight.getVelocity().x *= BACKWARDS_MULTIPLIER;
      animationTime = NUM_FRAMES - .01f;
    } else {
      animationTime = 0;
    }
  }

  @Override
  public void moveLeft() {
    if(moveDirection == Direction.RIGHT){
      knight.stopMovingRight();
      knight.moveLeft();
    }
  }

  @Override
  public void moveRight() {
    if(moveDirection == Direction.LEFT){
      knight.stopMovingLeft();
      knight.moveRight();
    }
  }

  @Override
  public void stopMovingLeft() {
    knight.getVelocity().x = 0;
    knight.setState(State.STAND);
  }

  @Override
  public void stopMovingRight() {
    knight.getVelocity().x = 0;
    knight.setState(State.STAND);
  }

  @Override
  public void attack() {

  }

  @Override
  public void block() {

  }

  @Override
  public void stopBlocking() {

  }

  @Override
  public void draw(SpriteBatch batch) {
    batch.draw(frames[knight.getColor()][(int)animationTime],
        knight.getPosition().x - Knight.WIDTH / 2,
        knight.getPosition().y - Knight.HEIGHT / 2,
        Knight.WIDTH / 2,
        Knight.HEIGHT / 2,
        Knight.WIDTH,
        Knight.HEIGHT,
        knight.getTurnMultiplier(),
        1,
        0);
  }

  @Override
  public void update(float delta) {
    if(backwards){
      animationTime -= delta * FPS * BACKWARDS_MULTIPLIER;
      if(animationTime < 0) animationTime = NUM_FRAMES - .01f;
    } else {
      animationTime += delta * FPS;
      if(animationTime > NUM_FRAMES) animationTime = 0;
    }
  }
}
