public class Cat extends Pet {
  private int miceCaught;
  private static final int DEFAULT_MICECAUGHT = 0;

  public Cat(String name, double health, int painLevel) {
    this(name, health,painLevel, DEFAULT_MICECAUGHT);
  }

  public Cat(String name, double health, int painLevel, int miceCaught) {
    super(name, health, painLevel);
    this.miceCaught = Math.max(0, miceCaught);
  }

  public int getMiceCaught() {
    return this.miceCaught;
  }

  @Override
  public int treat() {
    int treamentTime = (int) Math.ceil(this.miceCaught < 4
    ? this.getPainLevel() * 2 / this.getHealth()
    : this.miceCaught > 7 ? this.getPainLevel() / (this.getHealth() * 2) : this.getPainLevel() / this.getHealth());

    this.heal();
    return treamentTime;
  }

  public void speak() {
    super.speak();
    String meowString = "meow" + " meow".repeat(Math.max(0, this.getPainLevel() - 1));
    System.out.println(this.getPainLevel() > 5 ? meowString.toUpperCase() : meowString);
  }

  public boolean equals(Object o) {
    if (o instanceof Cat) {
      Cat cat = (Cat) o;
      return super.equals(o) && this.getMiceCaught() == cat.getMiceCaught();
    }

    return false;
  }
}
