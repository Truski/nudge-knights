package io.charstar.nudgeknights;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Knight {
  private static final Texture texture = new Texture("badlogic.jpg");
  private Vector2 position;
  private Vector2 velocity;
  private Vector2 acceleration;
  private boolean inAir;

  public Knight(float x, float y) {
    position = new Vector2(x, y);
    velocity = new Vector2();
    acceleration = new Vector2();
    inAir = false;
  }

  public void draw(SpriteBatch batch){
    batch.draw(texture, position.x, position.y);
  }

  public void update(float delta){
    if(position.y < 0){
      position.y = 0;
      velocity.y = 0;
      inAir = false;
    }
    acceleration.y = inAir ? -9.8f : 0;
    Vector2 acceleration = new Vector2(this.acceleration).scl(delta);
    velocity.add(acceleration);

    Vector2 velocity = new Vector2(this.velocity).scl(delta);
    position.add(velocity);
  }

  public void jump(){
    if(inAir) return;

    velocity.y = 35;
    inAir = true;
  }
}
