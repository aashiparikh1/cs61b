import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;
        Node(T i) {
            item = i;
            prev = this;
            next = this;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new Node(null);
        size = 0;
    }

    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(2);
        lld.addLast(3);
        lld.addLast(5);
        System.out.println(lld.toList());
    }

    /**
     * This method adds x at the front of the LinkedListDeque.
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node n = new Node(x);
        n.next = sentinel.next;
        sentinel.next.prev = n;
        n.prev = sentinel;
        sentinel.next = n;
        size++;
    }

    /**
     * This method adds x to the end of the LinkedListDeque.
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node n = new Node(x);
        sentinel.prev.next = n;
        n.prev = sentinel.prev;
        sentinel.prev = n;
        n.next = sentinel;
        size++;
    }

    /**
     * This method returns ArrayList containing all items in the LinkedListDeque.
     * @return List<T>
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node copy = sentinel.next;
        while (copy != sentinel) {
            returnList.add(copy.item);
            copy = copy.next;
        }
        return returnList;
    }

    /**
     * @return
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return
     */
    @Override
    public T removeFirst() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public T removeLast() {
        return null;
    }

    /**
     * @param index index to get, assumes valid index
     * @return
     */
    @Override
    public T get(int index) {
        if (index > size() || index < 0 || size() == 0)
            return null;
        Node copy = sentinel.next;
        for (int i = 0; i < size(); i++) {
            if (i == index) {
                break;
            }
            copy = copy.next;
        }
        return copy.item;
    }

    /**
     * @param index index to get, assumes valid index
     * @return
     */
    @Override
    public T getRecursive(int index) {
        return null;
    }
}
