import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate {
  private int numTasks;
  private int taskSpeed;
  private boolean completedTask = false;
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
    if (this.isFrozen() || this.completedTask) return;

    this.numTasks = Math.max(0, this.taskSpeed > 20 ? this.numTasks - 2 : this.numTasks - 1);

    if (this.numTasks == 0) {
      System.out.println("I have completed all my tasks");
      this.setSusLevel((int) (0.5 * this.getSusLevel()));
      this.completedTask = true;
    }
  }

  @Override
  public void emergencyMeeting() {
    if (this.isFrozen()) return;

    Player[] players = super.getPlayers();
    Arrays.sort(players);

    int i = players.length - 2;
    int j = players.length - 1;

    for (; i >= 0; i--) {
      Player playerI = players[i];
      Player playerJ = players[j];

      if (playerJ.isFrozen()) {
        j--;
        continue;
      };
      if (playerI.getSusLevel() < playerJ.getSusLevel()) {
        playerJ.setFrozen(true);
        break;
      }
      if (playerI.isFrozen()) continue;
      if (playerI.getSusLevel() == playerJ.getSusLevel()) return;
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