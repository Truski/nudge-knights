package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Block extends State {

  private static final TextureRegion[] redFrames = TextureRegion.split(
      new Texture("knight/red/block.png"), Knight.WIDTH, Knight.HEIGHT)[0];
  private static final TextureRegion[] blueFrames = TextureRegion.split(
      new Texture("knight/blue/block.png"), Knight.WIDTH, Knight.HEIGHT)[0];
  private static final TextureRegion[][] frames = {redFrames, blueFrames};
  private static final int NUM_FRAMES = 7;
  private static final int FPS = 15;
  private static final float REWIND_MULTIPLIER = 1.5f;
  private static final float DECAY_RATE = .25f;

  private float animationTime;
  private boolean rewinding;
  private int changeState;

  @Override
  public void start() {
    animationTime = 0;
    rewinding = false;
  }

  @Override
  public void moveLeft() {

  }

  @Override
  public void moveRight() {

  }

  @Override
  public void stopMoving() {

  }

  @Override
  public void attack() {
    rewinding = true;
    changeState = State.ATTACK;
  }

  @Override
  public void block() {
    if(rewinding){
      rewinding = false;
    }
  }

  @Override
  public void stopBlocking() {
    if(!rewinding){
      rewinding = true;
      changeState = State.STAND;
    }
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
    if(!rewinding){
      animationTime += delta * FPS;
      if (animationTime > NUM_FRAMES) animationTime -= delta * FPS;
    } else {
      animationTime -= delta * FPS * REWIND_MULTIPLIER;
      if(animationTime <= 0){
        knight.setState(changeState);
      }
    }
    knight.setBlockPower(knight.getBlockPower() - delta * DECAY_RATE);
    if(knight.getBlockPower() == 0){
      knight.stopBlocking();
    }
  }
}