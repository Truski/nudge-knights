package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Knight {
  private static final int WIDTH = 42;
  private static final int HEIGHT = 42;
  private static final int SPEED = 10;
  private static final float GRAVITY = -9.8f;
  private static final int ANIMATION_FRAMERATE = 15;
  private static final int ANIMATION_FRAMES = 4;

  private static TextureRegion[] idle = TextureRegion.split(
      new Texture("idle.png"), WIDTH, HEIGHT)[0];

  private Vector2 position;
  private Vector2 velocity;
  private Vector2 acceleration;
  private Rectangle box;

  // State information
  private boolean inAir;
  private float animation = 0;
  private byte turnDirection;

  public Knight(float x, float y) {
    position = new Vector2(x, y);
    velocity = new Vector2();
    acceleration = new Vector2();
    box = new Rectangle(-WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT);

    inAir = false;
    turnDirection = 1;
  }

  public void draw(SpriteBatch batch){
    batch.draw(idle[(int)animation], position.x - WIDTH / 2, position.y - HEIGHT / 2,
        WIDTH / 2, HEIGHT / 2, WIDTH, HEIGHT, turnDirection, 1, 0);
  }

  public void draw(ShapeRenderer renderer){
    renderer.rect(box.x - box.width / 2, box.y - box.height / 2, box.width, box.height);
  }

  public void update(float delta){
    animation += delta * ANIMATION_FRAMERATE;
    if(animation >= ANIMATION_FRAMES) animation = 0;

    if(position.y < 0){
      position.y = 0;
      velocity.y = 0;
      inAir = false;
    }
    acceleration.y = inAir ? GRAVITY : 0;
    Vector2 acceleration = new Vector2(this.acceleration).scl(delta);
    velocity.add(acceleration);

    Vector2 velocity = new Vector2(this.velocity).scl(delta);
    position.add(velocity);

    box.x = position.x;
    box.y = position.y;
  }

  public void jump(){
    if(inAir) return;

    velocity.y = 35;
    inAir = true;
  }

  public void moveLeft(){
    velocity.x -= SPEED;
    if(velocity.x != 0) turnDirection = -1;
  }

  public void moveRight(){
    velocity.x += SPEED;
    if(velocity.x != 0) turnDirection = 1;
  }

  public Vector2 getPosition(){
    return position;
  }
}
