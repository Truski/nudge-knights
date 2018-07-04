package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Knight {
  static final int WIDTH = 42;
  static final int HEIGHT = 42;
  static final int SPEED = 50;

  private Vector2 position;
  private Vector2 velocity;
  private Vector2 acceleration;
  private Rectangle box;

  // State information
  private int turnDirection;

  private State state;
  private State[] states;

  public Knight(float x, float y) {
    position = new Vector2(WIDTH / 2, HEIGHT / 2);
    velocity = new Vector2();
    acceleration = new Vector2();
    box = new Rectangle(0, 0, WIDTH, HEIGHT);

    turnDirection = 1;

    states = new State[State.NUM_STATES];
    states[State.STAND] = new Stand();
    states[State.WALK] = new Walk();
    states[State.ATTACK] = new Attack();
    states[State.BLOCK] = new Block();
    for(State s : states){
      s.setKnight(this);
    }
    setState(State.STAND);
  }

  public void draw(SpriteBatch batch){
    state.draw(batch);
  }

  public void draw(ShapeRenderer renderer){
    renderer.rect(box.x, box.y, box.width, box.height);
  }

  public void update(float delta){
    state.update(delta);
    Vector2 acceleration = new Vector2(this.acceleration).scl(delta);
    velocity.add(acceleration);

    Vector2 velocity = new Vector2(this.velocity).scl(delta);
    position.add(velocity);

    box.x = position.x - WIDTH / 2;
    box.y = position.y - HEIGHT / 2;
  }

  public void moveLeft(){
    state.moveLeft();
  }

  public void moveRight(){
    state.moveRight();
  }

  public void stopMovingLeft(){
    state.stopMovingLeft();
  }

  public void stopMovingRight(){
    state.stopMovingRight();
  }

  public void attack(){
    state.attack();
  }

  public void block(){
    state.block();
  }

  public void stopBlocking(){
    state.stopBlocking();
  }

  public Vector2 getPosition(){
    return position;
  }

  public Vector2 getVelocity(){
    return velocity;
  }

  public Vector2 getAcceleration() {
    return acceleration;
  }

  public void setState(int state){
    this.state = states[state];
    this.state.start();
  }

  public int getTurnDirection() {
    return turnDirection;
  }

  public void setTurnDirection(int turnDirection) {
    this.turnDirection = turnDirection;
  }
}
