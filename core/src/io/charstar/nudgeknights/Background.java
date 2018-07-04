package io.charstar.nudgeknights;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Background {
  private static final Texture BACK_TREES =
      new Texture("stages/forest/parallax-forest-back-trees.png");
  private static final Texture MIDDLE_TREES =
      new Texture("stages/forest/parallax-forest-middle-trees.png");
  private static final Texture FRONT_TREES =
      new Texture("stages/forest/parallax-forest-front-trees.png");
  private static final Texture LIGHTS =
      new Texture("stages/forest/parallax-forest-lights.png");
  private static final float RATIO = 272 / 160f;
  private static final int HEIGHT= 90;
  private static final int WIDTH = (int) (HEIGHT * RATIO);

  private float scroll = 0;
  private float lightBonus;
  private float middleBonus;
  private float frontBonus;
  private int locX;

  public void draw(SpriteBatch batch){
    batch.draw(BACK_TREES, WIDTH * (-1 + locX / WIDTH), 0, WIDTH, HEIGHT);
    batch.draw(BACK_TREES, WIDTH * (locX / WIDTH), 0, WIDTH, HEIGHT);
    batch.draw(BACK_TREES, WIDTH * (1 + locX / WIDTH), 0, WIDTH, HEIGHT);

    batch.draw(LIGHTS, WIDTH * (-1 + locX / WIDTH) + lightBonus, 0, WIDTH, HEIGHT);
    batch.draw(LIGHTS, WIDTH * (locX / WIDTH) + lightBonus, 0, WIDTH, HEIGHT);
    batch.draw(LIGHTS, WIDTH * (1 + locX / WIDTH) + lightBonus, 0, WIDTH, HEIGHT);

    batch.draw(MIDDLE_TREES, WIDTH * (-1 + locX / WIDTH) + middleBonus, 0, WIDTH, HEIGHT);
    batch.draw(MIDDLE_TREES, WIDTH * (locX / WIDTH) + middleBonus, 0, WIDTH, HEIGHT);
    batch.draw(MIDDLE_TREES, WIDTH * (1 + locX / WIDTH) + middleBonus, 0, WIDTH, HEIGHT);

    batch.draw(FRONT_TREES, WIDTH * (-1 + locX / WIDTH) + frontBonus, 0, WIDTH, HEIGHT);
    batch.draw(FRONT_TREES, WIDTH * (locX / WIDTH) + frontBonus, 0, WIDTH, HEIGHT);
    batch.draw(FRONT_TREES, WIDTH * (1 + locX / WIDTH) + frontBonus, 0, WIDTH, HEIGHT);
  }

  public void update(Vector3 cameraPosition){
    scroll = cameraPosition.x / 500;
    locX = (int) cameraPosition.x;
    lightBonus = (scroll * 35) % WIDTH;
    middleBonus = (scroll * 25) % WIDTH;
    frontBonus = (scroll * 50) % WIDTH;
  }
}
