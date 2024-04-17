public class Dog extends Pet {
  private double droolRate;
  private static final double DEFAULT_DROOLRATE = 5.0;

  public Dog(String name, double health, int painLevel) {
    this(name, health, painLevel, DEFAULT_DROOLRATE);
  }

  public Dog(String name, double health, int painLevel, double droolRate) {
    super(name, health, painLevel);
    this.droolRate = droolRate <= 0 ? 0.5 : droolRate;
  }

  public double getDroolRate() {
    return this.droolRate;
  }

  @Override
  public int treat() {
    int treamentTime = (int) Math.ceil(this.droolRate < 3.5
    ? this.getPainLevel() * 2 / this.getHealth()
    : this.droolRate > 7.5 ? this.getPainLevel() / (this.getHealth() * 2) : this.getPainLevel() / this.getHealth());

    this.heal();
    return treamentTime;
  }

  public void speak() {
    super.speak();
    String barkString = "bark" + " bark".repeat(Math.max(0, this.getPainLevel() - 1));
    System.out.println(this.getPainLevel() > 5 ? barkString.toUpperCase() : barkString);
  }

  public boolean equals(Object o) {
    if (o instanceof Dog) {
      Dog dog = (Dog) o;
      return super.equals(o) && this.getDroolRate() == dog.getDroolRate();
    }

    return false;
  }
}
