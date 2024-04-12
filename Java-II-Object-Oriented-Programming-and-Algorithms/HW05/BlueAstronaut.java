import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate {
  private int numTasks;
  private int taskSpeed;
  private static final int DEFAULT_SUSLEVEL = 15;
  private static final int DEFAULT_NUMTASKS = 6;
  private static final int DEFAULT_TASKSPEED = 10;

  public BlueAstronaut(String name) {
    this(name, DEFAULT_SUSLEVEL, DEFAULT_NUMTASKS, DEFAULT_TASKSPEED);
  }

  public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
    super(name, susLevel);
    this.numTasks = numTasks;
    this.taskSpeed = taskSpeed;
  }

  @Override
  public void completeTask() {
    if (this.isFrozen() || this.numTasks < 0) return;

    this.numTasks = Math.max(0, this.taskSpeed > 20 ? this.numTasks - 2 : this.numTasks - 1);

    if (this.numTasks == 0) {
      System.out.println("I have completed all my tasks");
      this.setSusLevel((int) (0.5 * this.getSusLevel()));
      this.numTasks--;
    }
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
    if (o instanceof BlueAstronaut) {
      BlueAstronaut bluePlayer = (BlueAstronaut) o;
      return bluePlayer.getName() == this.getName()
        && bluePlayer.isFrozen() == this.isFrozen()
        && bluePlayer.getSusLevel() == this.getSusLevel()
        && bluePlayer.numTasks == this.numTasks
        && bluePlayer.taskSpeed == this.taskSpeed;
    }

    return false;
  }

  public String toString() {
    String string = super.toString() + String.format(" I have %s left over.", numTasks);

    if (this.getSusLevel() > 15) return string.toUpperCase();

    return string;
  }
}