public class Gameplay {
  public static void main(String[] args) {
    BlueAstronaut blueBob = new BlueAstronaut("Bob", 20, 6, 30);
    BlueAstronaut blueHeath = new BlueAstronaut("Heath", 30, 3, 21);
    BlueAstronaut blueAlbert = new BlueAstronaut("Albert", 44, 2, 0);
    BlueAstronaut blueAngel = new BlueAstronaut("Angel", 0, 1, 0);
    RedAstronaut redLiam = new RedAstronaut("Liam", 19, "experienced");
    RedAstronaut redSuspicious = new RedAstronaut("Suspicious Person", 100, "expert");

    //1
    System.out.println("/** 1 **/");
    redLiam.sabotage(blueBob);
    System.out.println(blueBob.getSusLevel());
    System.out.println(blueBob.isFrozen());

    //2
    System.out.println("/** 2 **/");
    redLiam.freeze(redSuspicious);
    System.out.println(redSuspicious.isFrozen());

    //3
    System.out.println("/** 3 **/");
    redLiam.freeze(blueAlbert);
    System.out.println(redLiam.getSusLevel());
    System.out.println(blueAlbert.isFrozen());

    //4
    System.out.println("/** 4 **/");
    blueAlbert.emergencyMeeting();

}
}
