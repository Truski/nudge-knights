package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Walk extends State {

  private static final TextureRegion[] frames = TextureRegion.split(
      new Texture("walk.png"), Knight.WIDTH, Knight.HEIGHT)[0];
  private static final int NUM_FRAMES = 8;
  private static final int FPS = 5;

  private float animationTime;

  @Override
  public void start() {
    animationTime = 0;
  }

  @Override
  public void moveLeft() {
    knight.getVelocity().x -= Knight.SPEED;
  }

  @Override
  public void moveRight() {
    knight.getVelocity().x += Knight.SPEED;
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
    animationTime += delta * FPS;
    if(animationTime > NUM_FRAMES) animationTime = 0;
  }
}