import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
  private String skill;
  private static final int DEFAULT_SUSLEVEL = 15;
  private static final String DEFAULT_SKILL = "experienced";

  public RedAstronaut(String name) {
    this(name, DEFAULT_SUSLEVEL, DEFAULT_SKILL);
  }

  public RedAstronaut(String name, int susLevel, String skill) {
    super(name, susLevel);
    this.skill = skill;
  }

  @Override
  public void freeze(Player p) {
    if (this.isFrozen() || p instanceof Impostor || p.isFrozen()) return;

    if (this.getSusLevel() < p.getSusLevel()) {
      p.setFrozen(true);
    } else {
      this.setSusLevel(2 * this.getSusLevel());
    }

    this.gameOver();
  }

  @Override
  public void sabotage(Player p) {
    if (this.isFrozen() || p instanceof Impostor || p.isFrozen()) return;

    p.setSusLevel(this.getSusLevel() < 20 ? (int) (1.5 * p.getSusLevel()) : (int) (1.25 * p.getSusLevel()));
  }

  @Override
  public void emergencyMeeting() {
    if (this.isFrozen()) return;

    Player[] players = super.getPlayers();
    Arrays.sort(players);

    for (int i = players.length - 1; i >= 0; i--) {
      Player currPlayer = players[i];
      if (currPlayer == this || currPlayer.isFrozen()) continue;
      if (currPlayer.getSusLevel() > this.getSusLevel()) {
        currPlayer.setFrozen(true);
        break;
      }
    }

    this.gameOver();
  }

  public boolean equals(Object o) {
    if (o instanceof RedAstronaut) {
      RedAstronaut redPlayer = (RedAstronaut) o;
      return redPlayer.getName() == this.getName()
        && redPlayer.isFrozen() == this.isFrozen()
        && redPlayer.getSusLevel() == this.getSusLevel()
        && redPlayer.skill == this.skill;
    }

    return false;
  }

  public String toString() {
    String string = super.toString() + String.format(" I am an %s player!", skill);

    if (this.getSusLevel() > 15) return string.toUpperCase();

    return string;
  }
}
