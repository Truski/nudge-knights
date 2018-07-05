package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Stand extends State {

  private static final TextureRegion[] redFrames = TextureRegion.split(
      new Texture("knight/red/stand.png"), Knight.WIDTH, Knight.HEIGHT)[0];
  private static final TextureRegion[] blueFrames = TextureRegion.split(
      new Texture("knight/blue/stand.png"), Knight.WIDTH, Knight.HEIGHT)[0];
  private static final TextureRegion[][] frames = {redFrames, blueFrames};
  private static final int NUM_FRAMES = 4;
  private static final int FPS = 5;

  private float animationTime;

  @Override
  public void start() {
    animationTime = 0;
  }

  @Override
  public void moveLeft() {
    knight.getVelocity().x = -Knight.SPEED;
    knight.setState(State.WALK);
  }

  @Override
  public void moveRight() {
    knight.getVelocity().x = Knight.SPEED;
    knight.setState(State.WALK);
  }

  @Override
  public void stopMoving() {
    // Does nothing
  }

  @Override
  public void attack() {
    knight.setState(State.ATTACK);
  }

  @Override
  public void block() {
    knight.setState(State.BLOCK);
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
    animationTime += delta * FPS;
    if(animationTime > NUM_FRAMES) animationTime = 0;
  }
}
