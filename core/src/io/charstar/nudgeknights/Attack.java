package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Attack extends State {

  private static final TextureRegion[] frames = TextureRegion.split(
      new Texture("attack.png"), Knight.WIDTH, Knight.HEIGHT)[0];
  private static final int NUM_FRAMES = 10;
  private static final int FPS = 15;
  private static final int[] keyFrames = {0, 7};
  private static final int[] speeds = {FPS, FPS / 2};

  private float animationTime;
  private int keyFrameIndex;

  @Override
  public void start() {
    animationTime = 0;
    keyFrameIndex = 0;
  }

  @Override
  public void moveLeft() {

  }

  @Override
  public void moveRight() {

  }

  @Override
  public void stopMovingLeft() {

  }

  @Override
  public void stopMovingRight() {

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
    batch.draw(frames[(int)animationTime],
        knight.getPosition().x - Knight.WIDTH / 2,
        knight.getPosition().y - Knight.HEIGHT / 2,
        Knight.WIDTH / 2,
        Knight.HEIGHT / 2,
        Knight.WIDTH,
        Knight.HEIGHT,
        knight.getTurnDirection(),
        1,
        0);
  }

  @Override
  public void update(float delta) {
    if(keyFrameIndex != keyFrames.length - 1 && animationTime >= keyFrames[keyFrameIndex + 1]){
      keyFrameIndex++;
    }
    animationTime += delta * speeds[keyFrameIndex];
    if(animationTime > NUM_FRAMES) knight.setState(State.STAND);
  }
}
