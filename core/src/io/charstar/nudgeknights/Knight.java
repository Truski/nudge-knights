package io.charstar.nudgeknights;

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
    acceleration.y = inAir ? -9.8f : 0;

    velocity.add(acceleration.scl(delta));
    position.add(velocity.scl(delta));
  }

  public void jump(){
    if(inAir) return;

    velocity.y = 15f;
    inAir = true;
  }
}
