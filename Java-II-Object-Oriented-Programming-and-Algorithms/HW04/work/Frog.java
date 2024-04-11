package work;

public class Frog {
  private String name;
  private int age;
  private double tongueSpeed;
  private boolean isFroglet;
  private static String species = "Rare Pepe";
  public static final int DEFAULT_AGE = 5;
	public static final double DEFAULT_TONGUESPEED = 5;
  public static final double MIN_AGE = 12;
  public static final double MAX_AGE = 30;


  public Frog(String name) {
    this(name, DEFAULT_AGE, DEFAULT_TONGUESPEED);
  }

  public Frog(String name, double ageInYears, double tongueSpeed) {
    this(name, (int) (ageInYears * 12), tongueSpeed);
  }

  public Frog(String name, int age, double tongueSpeed) {
    this.name = name;
    this.age = age;
    this.tongueSpeed = tongueSpeed;
    this.isFroglet = this.age > 1 && this.age < 7;
  }

  public void grow(int months) {
    double remainingAge = this.age < MIN_AGE ? Math.min(MIN_AGE - this.age, months) : 0;

    this.age += months;
    this.isFroglet = this.age > 1 && this.age < 7;

    double overflowAge = this.age > MAX_AGE ? Math.min(this.age - MAX_AGE, months) : 0;

    tongueSpeed = Math.max(DEFAULT_TONGUESPEED, tongueSpeed + (remainingAge - overflowAge));
  }

  public void grow() {
    this.age++;
    this.isFroglet = this.age > 1 && this.age < 7;

    if (this.age < 12) this.tongueSpeed++;
    else if (this.age >= 30 && this.tongueSpeed > 5) this.tongueSpeed--;
  }

  public void eat(Fly fly) {
    if (fly.isDead()) return;

    if (this.tongueSpeed > fly.getSpeed()) {
      if (fly.getMass() > 0.5 * this.age) grow();
      fly.setMass(0);
    } else {
      fly.grow(1);
    }
  }

  public String getSpecies () {
		return species;
	}

	public void setSpecies (String species) {
    this.species = species;
	}

  public String toString() {
    return String.format("My name is %s and I'm a rare %s I'm %s months old and my tongue has a speed of %.2f.",
      this.name, this.isFroglet ? "froglet!" : "frog.", this.age, this.tongueSpeed);
  }
}
