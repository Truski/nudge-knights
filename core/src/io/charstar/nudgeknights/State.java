package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
  static final int NUM_STATES = 4;
  static final int STAND = 0;
  static final int WALK = 1;
  static final int ATTACK = 2;
  static final int BLOCK = 3;

  Knight knight;

  public abstract void start();

  public abstract void moveLeft();
  public abstract void moveRight();
  public abstract void stopMovingLeft();
  public abstract void stopMovingRight();
  public abstract void attack();
  public abstract void block();
  public abstract void stopBlocking();

  public abstract void draw(SpriteBatch batch);

  public abstract void update(float delta);

  public void setKnight(Knight knight){
    this.knight = knight;
  }
}
