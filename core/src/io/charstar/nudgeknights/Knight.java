package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Knight {
  static final int RED = 0;
  static final int BLUE = 1;

  static final int WIDTH = 42;
  static final int HEIGHT = 42;
  static final int SPEED = 50;

  private int color;
  private Vector2 position;
  private Vector2 velocity;
  private Vector2 acceleration;
  private Rectangle box;

  // State information
  private Direction turnDirection;
  private State state;
  private State[] states;
  private int attacks;
  private float blockPower;
  private Knockback knockback;

  private ArrayList<KnightListener> observers;

  public Knight(int color) {
    observers = new ArrayList<KnightListener>();
    attacks = 3;
    blockPower = 1;

    this.color = color;
    if(color == RED){
      turnDirection = Direction.RIGHT;
    } else {
      turnDirection = Direction.LEFT;
    }

    position = new Vector2(WIDTH / 2, HEIGHT / 2);
    velocity = new Vector2();
    acceleration = new Vector2();
    box = new Rectangle(0, 0, WIDTH, HEIGHT);
    knockback = new Knockback();

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

  public void notifyListeners(){
    for(KnightListener observer : observers){
      observer.update(this);
    }
  }

  public void registerListener(KnightListener listener){
    observers.add(listener);
    listener.update(this);
  }

  public void unregisterListener(KnightListener listener){
    observers.remove(listener);
  }

  public void draw(SpriteBatch batch){
    state.draw(batch);
  }

  public void draw(ShapeRenderer renderer){
    renderer.set(ShapeRenderer.ShapeType.Filled);
    if(color == RED){
      renderer.setColor(1, 0, 0, .5f);
    } else if (color == BLUE){
      renderer.setColor(0, 0, 1, .5f);
    }
    renderer.rect(box.x, box.y, box.width, box.height);
  }

  public void update(float delta){
    state.update(delta);
    knockback.update(delta, this);

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

  public void stopMoving(){
    state.stopMoving();
  }

  public void attack(){
    if(attacks > 0){
      state.attack();
    }
  }

  public void block(){
    if(blockPower > 0){
      state.block();
    }
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

  public Direction getTurnDirection() {
    return turnDirection;
  }

  public int getTurnMultiplier(){
    if(turnDirection == Direction.RIGHT){
      return 1;
    } else {
      return -1;
    }
  }

  public void setTurnDirection(Direction turnDirection) {
    this.turnDirection = turnDirection;
  }

  public int getColor(){
    return color;
  }

  public int getAttacks() {
    return attacks;
  }

  public void setAttacks(int attacks) {
    this.attacks = attacks;
    notifyListeners();
  }

  public float getBlockPower() {
    return blockPower;
  }

  public void setBlockPower(float blockPower) {
    this.blockPower = blockPower;
    if(this.blockPower < 0){
      this.blockPower = 0;
    }
    notifyListeners();
  }

  public void getHit(){
    if(color == RED){
      knockback.set(-15, .5f);
    } else if (color == BLUE){
      knockback.set(15, .5f);
    }
    knockback.setActive(true);
  }
}
