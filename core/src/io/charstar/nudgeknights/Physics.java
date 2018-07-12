package io.charstar.nudgeknights;

import java.util.ArrayList;

public class Physics {
  public ArrayList<Knight> knights;

  public Physics(){
    knights = new ArrayList<Knight>();
  }

  public void addKnight(Knight knight){
    knights.add(knight);
  }

  public void removeKnight(Knight knight){
    knights.remove(knight);
  }

  public void checkCollisions(){
    Knight knight1, knight2;
    for(int i = 0; i < knights.size() - 1; i++){
      knight1 = knights.get(i);
      for(int j = i + 1; j < knights.size(); j++){
        knight2 = knights.get(j);
        //boolean collisionExists = knight1.getBoundingBox().collidesWith(knight2.getBoundingBox());
      }
    }
  }
}
