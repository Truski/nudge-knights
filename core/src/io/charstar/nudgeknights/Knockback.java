package io.charstar.nudgeknights;

public class Knockback {
  private float velocity;
  private float timeRemaining;
  private boolean active;

  public void set(float velocity, float time){
    this.velocity = velocity;
    this.timeRemaining = time;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isActive() {
    return active;
  }

  public void update(float delta){
    if(active){
      timeRemaining -= delta;
      if(timeRemaining <= 0){
        active = false;
      }
    }
  }

  public void apply(Knight knight){
    knight.getVelocity().x = velocity;
  }
}
