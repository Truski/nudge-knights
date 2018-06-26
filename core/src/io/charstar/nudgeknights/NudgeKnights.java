package io.charstar.nudgeknights;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NudgeKnights extends Game implements Screen {
  SpriteBatch batch;
  Knight knight;

  @Override
  public void create () {
    batch = new SpriteBatch();
    knight = new Knight(0, 0);
    setScreen(this);
  }

  @Override
  public void show() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void render(float delta) {

    // Process events
    if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
      knight.jump();
    }

    // Update Game
    knight.update(delta);

    // Render Game
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();

    knight.draw(batch);

    batch.end();
  }

  @Override
  public void dispose () {
    batch.dispose();
  }
}
