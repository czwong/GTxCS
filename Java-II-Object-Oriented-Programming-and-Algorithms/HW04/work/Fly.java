package work;

public class Fly {
  private double mass;
  private double speed;
  private static final double DEFAULT_MASS = 5;
	private static final double DEFAULT_SPEED = 10;
  private static final double SPEED_DECREASE_RATE = 0.5;
  private static final int MAX_MASS = 20;

  public Fly() {
    this(DEFAULT_MASS, DEFAULT_SPEED);
  }

  public Fly(double mass) {
    this(mass, DEFAULT_SPEED);
  }

  public Fly(double mass, double speed) {
    this.mass = mass;
    this.speed = speed;
  }

  public double getMass() {
    return this.mass;
  }

  public void setMass(double mass) {
    this.mass = mass;
  }

  public double getSpeed() {
    return this.speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public String toString() {
    return mass == 0
      ? String.format("I'm dead, but I used to be a fly with a speed of %.2f.", this.speed)
      : String.format("I'm a speedy fly with %.2f speed and %.2f mass.", this.speed, this.mass);
  }

  public void grow(int addMass) {
    double remainingMass = this.mass < MAX_MASS ? Math.min(MAX_MASS - this.mass, addMass) : 0;

    this.mass += addMass;

    double overflowMass = this.mass >= MAX_MASS ? addMass - remainingMass : 0;

    speed += remainingMass - (SPEED_DECREASE_RATE * overflowMass);

    /** Another way to solve this
     *
    this.mass += addMass;
    if (this.mass - addMass < MAX_MASS && this.mass >= MAX_MASS) {
      double massDifference = MAX_MASS - (this.mass - addMass);

      this.speed += massDifference;
      addMass -= massDifference;
    }

    this.speed = this.mass < MAX_MASS ? this.speed + addMass : this.speed - SPEED_DECREASE_RATE * addMass;
    **/
  }

  public boolean isDead() {
    if (this.mass == 0) return true;
    return false;
  }
}
