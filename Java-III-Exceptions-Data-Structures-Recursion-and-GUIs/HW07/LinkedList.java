import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public Node<T> getHead() {
    return this.head;
  }

  public Node<T> getTail() {
    return this.tail;
  }

  @Override
  public void addAtIndex(T data, int index) {
    if (index > this.size ||  index < 0) throw new IllegalArgumentException("Your index is out of the list bounds");
    if (data == null) throw new IllegalArgumentException("You cannot add null data to the list");

    Node<T> node = new Node<T>(data);

    if (index == 0) {
      node.setNext(this.head);
      this.head = node;
      this.tail = this.size == 0 ? node : this.tail;
    } else if (index == this.size) {
      this.tail.setNext(node);
      this.tail = node;
    } else {
      Node<T> prevNode = head;

      while (index - 1 > 0) {
        prevNode = prevNode.getNext();
        index--;
      }

      node.setNext(prevNode.getNext());
      prevNode.setNext(node);
    }

    this.size++;
  }

  @Override
  public T getAtIndex(int index) {
    if (index > this.size - 1 ||  index < 0) throw new IllegalArgumentException("Your index is out of the list bounds");

    Node<T> currNode = head;

    while (index > 0) {
      currNode = currNode.getNext();
      index--;
    }

    return currNode.getData();
  }

  @Override
  public T removeAtIndex(int index) {
    if (index > this.size - 1 ||  index < 0) throw new IllegalArgumentException("Your index is out of the list bounds");

    Node<T> prevNode = null;
    Node<T> removedNode = this.head;

    if (index == 0) {
      this.head = removedNode.getNext();
      if (this.size == 1) this.tail = this.head;
    } else {
      int prevCounter = index;

      while (prevCounter > 0) {
        prevNode = removedNode;
        removedNode = removedNode.getNext();
        prevCounter--;
      }

      prevNode.setNext(removedNode.getNext());
      if (index == this.size - 1) this.tail = prevNode;
    }

    this.size--;
    return removedNode.getData();
  }

  @Override
  public T remove(T data) {
    if (data == null) throw new IllegalArgumentException("You cannot remove null data from the list");

    Node<T> prevNode = null;
    Node<T> currNode = this.head;

    while (currNode != null) {
      if (currNode.getData().equals(data)) {
        if (currNode != this.head && currNode != this.tail) prevNode.setNext(currNode.getNext());
        if (currNode == this.head) this.head = currNode.getNext();
        if (currNode == this.tail) this.tail = prevNode;

        this.size--;
        return currNode.getData();
      }

      prevNode = currNode;
      currNode = currNode.getNext();
    }

    throw new NoSuchElementException("The data is not present in the list.");
  }

  @Override
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public int size() {
    return this.size;
  }

}
