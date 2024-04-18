public class TestLinkedList {
  public static void main(String args[]) {
    LinkedList<Integer> linkedList = new LinkedList<Integer>();

    linkedList.addAtIndex(0, 0);
    linkedList.addAtIndex(1, 1);
    linkedList.addAtIndex(5, 1);
    linkedList.addAtIndex(10, 3);
    System.out.println(linkedList.removeAtIndex(0));
    System.out.println(linkedList.remove(5));
    System.out.println(linkedList.getHead().getData());
    System.out.println(linkedList.getTail().getData());
    System.out.println(linkedList.size());
    System.out.println(linkedList.removeAtIndex(1));
    System.out.println(linkedList.getHead().getData());
    System.out.println(linkedList.getTail().getData());
    System.out.println(linkedList.removeAtIndex(0));
    System.out.println(linkedList.getHead());
    System.out.println(linkedList.getTail());
    System.out.println(linkedList.size());
    System.out.println(linkedList.remove(10));
  }
}
