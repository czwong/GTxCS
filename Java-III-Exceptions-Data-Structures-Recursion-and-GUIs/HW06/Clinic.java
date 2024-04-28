import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Clinic {
  private File patientFile;
  private int day;

  public Clinic(String fileName) {
    this(new File(fileName));
  }

  public Clinic(File file) {
    this.patientFile = file;
    this.day = 1;
  }

  @SuppressWarnings("unchecked")
  private <T> T takeUserInput(String inputType, Scanner input, String name) {
    boolean success = false;
    String prompt = "";
    T output = null;

    switch(inputType) {
      case "health":
        prompt = String.format("What is the health of %s?", name);
        break;
      case "painLevel":
        prompt = String.format("On a scale of 1 to 10, how much pain is %s in right now?", name);
        break;
    }

    while (!success) {
      System.out.println(prompt);
      try {
        if (inputType.equals("health")) {
          output = (T) Double.valueOf(input.nextDouble());
        } else if (inputType.equals("painLevel")) {
          output = (T) Integer.valueOf(input.nextInt());
        }
        success = true;
      } catch(InputMismatchException e) {
        System.out.println("Please enter a number.");
      } finally {
        input.nextLine();
      }
    }

    return output;
  }

  private String addTime(String timeIn, int treatmentTime) {
    int totalMinutes = Integer.parseInt(timeIn);

    int hours = totalMinutes / 100;
    int minutes = totalMinutes % 100;

    minutes += treatmentTime;

    // Check if the minutes exceed 60
    if (minutes >= 60) {
        hours += minutes / 60;  // Calculate how many hours to add
        minutes %= 60;  // Reduce minutes to less than 60
    }

    // Formatting the hours and minutes to military time format
    String hoursFormatted = (hours < 10) ? "0" + hours : Integer.toString(hours);
    String minutesFormatted = (minutes < 10) ? "0" + minutes : Integer.toString(minutes);

    return hoursFormatted + minutesFormatted;
  }

  public boolean addToFile(String patientInfo) {
    PrintWriter filePrint = null;
    Scanner scanner = null;
    StringBuilder newData = new StringBuilder();

    try {
      scanner = new Scanner(patientFile);
      String[] infoParts = patientInfo.split(",");
      String name = infoParts[0];
      boolean patientExist = false;

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.startsWith(name)) {
          patientExist = true;
          line += "," + infoParts[3] + "," + infoParts[4] + "," +
            infoParts[5] + "," + infoParts[6] + "," + infoParts[7];
        }

        newData.append(line + "\n");
      }

      if (!patientExist) newData.append(patientInfo);

      scanner.close();
      filePrint = new PrintWriter(patientFile);
      filePrint.print(newData);
      return true;
    } catch(IOException e) {
      return false;
    } finally {
      if (filePrint != null) filePrint.close();
      if (scanner != null) scanner.close();
    }
  }

  public String nextDay(File f) throws FileNotFoundException {
    String patientInfo = "";
    @SuppressWarnings("resource")
    Scanner fileIn = new Scanner(f);
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);

    while (fileIn.hasNextLine()) {
      String[] parts = fileIn.nextLine().split(",");
      String name = parts[0];
      String typeOfPet = parts[1];
      String petAttribute = parts[2];
      String timeIn = parts[3];

      if (!typeOfPet.equals("Dog") && !typeOfPet.equals("Cat")) throw new InvalidPetException();

      System.out.println(String.format("Consultation for %s the %s at %s.",
        name, typeOfPet, timeIn));

      Pet pet = null;
      double health = (double) takeUserInput("health", input, name);
      int painLevel = (int) takeUserInput("painLevel", input, name);

      switch(typeOfPet) {
        case "Dog":
          pet = new Dog(name, health, painLevel, Double.parseDouble(petAttribute));
          break;
        case "Cat":
          pet = new Cat(name, health, painLevel, Integer.parseInt(petAttribute));
          break;
        default:
          throw new InvalidPetException();
      }

      pet.speak();
      int treamentTime = pet.treat();
      String timeOut = addTime(timeIn, treamentTime);

      patientInfo += String.format("%s,%s,%s,Day %s,%s,%s,%s,%s\n",
        name, typeOfPet, petAttribute, this.day, timeIn, timeOut, health, painLevel);
    }

    this.day++;
    fileIn.close();
    return patientInfo;
  }

  public String nextDay(String fileName) throws FileNotFoundException {
    return nextDay(new File(fileName));
  }
}
