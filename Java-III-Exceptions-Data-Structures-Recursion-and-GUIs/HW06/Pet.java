public abstract class Pet {
  private String name;
  private double health;
  private int painLevel;

  public Pet(String name, double health, int painLevel) {
    this.name = name;
    this.health = Math.max(0.0, Math.min(health, 1.0));
    this.painLevel = Math.max(1, Math.min(painLevel, 10));
  }

  public String getName() {
    return this.name;
  }

  public double getHealth() {
    return this.health;
  }

  public int getPainLevel() {
    return this.painLevel;
  }

  public abstract int treat();

  public void speak() {
    String speak = String.format("Hello! My name is %s", this.name);
    System.out.println(this.painLevel > 5 ? speak.toUpperCase() : speak);
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Pet) {
      Pet pet = (Pet) o;
      return this.getName().equals(pet.getName());
    }

    return false;
  }

  protected void heal() {
    this.health = 1.0;
    this.painLevel = 1;
  }
}
