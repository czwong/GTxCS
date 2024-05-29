public class Test {
  public static void main(String[] args) {
    ArrayList<Integer> arrayList = new ArrayList<>();

    arrayList.addToFront(5);
    arrayList.addToFront(10);
    arrayList.addToFront(12);
    arrayList.addToBack(15);
    arrayList.addToBack(22);

    System.out.println(arrayList.size());
    System.out.println(arrayList.removeFromFront());
    System.out.println(arrayList.removeFromFront());
    System.out.println(arrayList.removeFromFront());
    System.out.println(arrayList.removeFromFront());
    System.out.println(arrayList.size());
    System.out.println(arrayList.removeFromBack());
    System.out.println(arrayList.size());
    System.out.println(arrayList.removeFromFront());
  }
}
