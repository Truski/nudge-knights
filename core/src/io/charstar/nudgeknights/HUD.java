package io.charstar.nudgeknights;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HUD implements KnightListener {
  private float PADDING = 4;
  private float HORIZONTAL_BORDER = 22;
  private float VERTICAL_BORDER = 8;
  private float RADIUS = 4;
  private float SWORD_SPACING = RADIUS * 2 + PADDING;
  private float SWORD_LAT = 90 - VERTICAL_BORDER - RADIUS;

  private float BAR_HEIGHT = RADIUS;
  private float BAR_WIDTH = (RADIUS + PADDING) * 4;
  private float BAR_LAT = 90 - (VERTICAL_BORDER + RADIUS * 2 + PADDING) - BAR_HEIGHT;
  private int SEGMENTS = 20;

  private int[] swords;
  private float[] blockPower;


  public HUD(){
    swords = new int[2];
    blockPower = new float[2];
  }

  public void update(Knight knight){
    int changeIndex = knight.getColor() == Knight.RED ? 0 : 1;
    swords[changeIndex] = knight.getAttacks();
    blockPower[changeIndex] = knight.getBlockPower();
  }

  public void draw(SpriteBatch batch, ShapeRenderer renderer){
    renderer.set(ShapeRenderer.ShapeType.Filled);


    renderer.setColor(0, 0, 0, .35f);
    for(int i = 0; i < 3; i++){
      renderer.circle(HORIZONTAL_BORDER + RADIUS + i * SWORD_SPACING, SWORD_LAT, RADIUS, SEGMENTS);
    }
    renderer.rect(HORIZONTAL_BORDER, BAR_LAT, BAR_WIDTH, BAR_HEIGHT);

    for(int i = 0; i < 3; i++){
      renderer.circle(160 - HORIZONTAL_BORDER - RADIUS - i * SWORD_SPACING, SWORD_LAT, RADIUS, SEGMENTS);
    }
    renderer.rect(160 - HORIZONTAL_BORDER, BAR_LAT, -BAR_WIDTH, BAR_HEIGHT);


    renderer.setColor(Color.RED);
    for(int i = 0; i < swords[0]; i++){
      renderer.circle(HORIZONTAL_BORDER + RADIUS + i * SWORD_SPACING, SWORD_LAT, RADIUS, SEGMENTS);
    }
    renderer.rect(HORIZONTAL_BORDER, BAR_LAT, BAR_WIDTH * blockPower[0], BAR_HEIGHT);

    renderer.setColor(Color.BLUE);
    for(int i = 0; i < swords[1]; i++){
      renderer.circle(160 - HORIZONTAL_BORDER - RADIUS - i * SWORD_SPACING, SWORD_LAT, RADIUS, SEGMENTS);
    }
    renderer.rect(160 - HORIZONTAL_BORDER, BAR_LAT, -BAR_WIDTH * blockPower[1], BAR_HEIGHT);


    renderer.set(ShapeRenderer.ShapeType.Line);
    renderer.setColor(Color.BLACK);
    Gdx.gl.glLineWidth(6);
    for(int i = 0; i < 3; i++){
      renderer.circle(HORIZONTAL_BORDER + RADIUS + i * SWORD_SPACING, SWORD_LAT, RADIUS, SEGMENTS);
    }
    renderer.rect(HORIZONTAL_BORDER, BAR_LAT, BAR_WIDTH, BAR_HEIGHT);

    for(int i = 0; i < 3; i++){
      renderer.circle(160 - HORIZONTAL_BORDER - RADIUS - i * SWORD_SPACING, SWORD_LAT, RADIUS, SEGMENTS);
    }
    renderer.rect(160 - HORIZONTAL_BORDER, BAR_LAT, -BAR_WIDTH, BAR_HEIGHT);

  }
}
