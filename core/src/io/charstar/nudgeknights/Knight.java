package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Knight {
  static final int WIDTH = 42;
  static final int HEIGHT = 42;
  static final int SPEED = 50;
  private static final float GRAVITY = -20f;
  private static final int ANIMATION_FRAMERATE = 15;
  private static final int ANIMATION_FRAMES = 4;
  private static final int FLOOR = 0;

  private Vector2 position;
  private Vector2 velocity;
  private Vector2 acceleration;
  private Rectangle box;

  // State information
  private boolean inAir;
  private float animation = 0;
  private int turnDirection;

  private State state;
  private State[] states;
  private int returnState;

  public Knight(float x, float y) {
    position = new Vector2(WIDTH / 2, HEIGHT / 2);
    velocity = new Vector2();
    acceleration = new Vector2();
    box = new Rectangle(0, 0, WIDTH, HEIGHT);

    inAir = false;
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
    returnState = -1;
  }

  public void draw(SpriteBatch batch){
    state.draw(batch);
  }

  public void draw(ShapeRenderer renderer){
    renderer.rect(box.x, box.y, box.width, box.height);
  }

  public void update(float delta){
    state.update(delta);
    animation += delta * ANIMATION_FRAMERATE;
    if(animation >= ANIMATION_FRAMES) animation = 0;

    if(box.y < 0){
      position.y = HEIGHT / 2;
      velocity.y = 0;
      inAir = false;
    }
    acceleration.y = inAir ? GRAVITY : 0;
    Vector2 acceleration = new Vector2(this.acceleration).scl(delta);
    velocity.add(acceleration);

    Vector2 velocity = new Vector2(this.velocity).scl(delta);
    position.add(velocity);

    box.x = position.x - WIDTH / 2;
    box.y = position.y - HEIGHT / 2;
  }

  public void jump(){
    if(inAir) return;

    velocity.y = 35;
    inAir = true;
  }

  public void moveLeft(){
    returnState = State.WALK;
    state.moveLeft();
  }

  public void moveRight(){
    returnState = State.WALK;
    state.moveRight();
  }

  public void stopMovingLeft(){
    returnState = -1;
    state.stopMovingLeft();
  }

  public void stopMovingRight(){
    returnState = -1;
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
    if(state == State.STAND){
      if(returnState != -1){
        state = returnState;
      }
    }
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
