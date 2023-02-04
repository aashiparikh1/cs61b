import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;
        public Node(T i) {
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

    @Override
    public void addFirst(T x) {
        Node n = new Node(x);
        n.next = sentinel.next;
        sentinel.next.prev = n;
        n.prev = sentinel;
        sentinel.next = n;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node n = new Node(x);
        sentinel.prev.next = n;
        n.prev = sentinel.prev;
        sentinel.prev = n;
        n.next = sentinel;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node copy = sentinel.next;
        while(copy != sentinel) {
            returnList.add(copy.item);
            copy = copy.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
