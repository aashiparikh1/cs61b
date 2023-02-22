package deque;

import java.util.Iterator;
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
     * This method returns a boolean indicating whether the
     * LinkedListDeque is empty or not.
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method returns the size of
     * the LinkedListDeque.
     * @return int
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * This method removes the first element
     * in the LinkedListDeque and returns
     * that element.
     * @return T
     */
    @Override
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        Node n = sentinel.next;
        T itemCopy = n.item;
        n.item = null;
        if (size() == 1) {
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
            size = 0;
            return itemCopy;
        }
        sentinel.next = n.next;
        n.next.prev = sentinel;
        n.next = null;
        n.prev = null;
        size--;

        return itemCopy;
    }

    /**
     * This method removes the last element
     * in the LinkedListDeque and returns
     * that element.
     * @return T
     */
    @Override
    public T removeLast() {
        if (size() == 0) {
            return null;
        }
        Node n = sentinel.prev;
        T itemCopy = n.item;
        sentinel.prev = n.prev;
        n.prev.next = sentinel;
        n.prev = null;
        n.next = null;
        n.item = null;
        size--;
        return itemCopy;
    }

    /**
     * This method returns the element
     * found at the index "index."
     * @param index index to get, assumes valid index
     * @return T
     */
    @Override
    public T get(int index) {
        if (index > size() || index < 0 || size() == 0) {
            return null;
        }
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
     * This method also returns the
     * item of LinkedListDeque at
     * index "index," but does so
     * using recursion.
     * @param index index to get, assumes valid index
     * @return T
     */
    @Override
    public T getRecursive(int index) {
        if (index > size() || index < 0 || size() == 0) {
            return null;
        }
        return (T) getRecursive(sentinel.next, index);
    }

    /**
     * This is a helper method for
     * the above, to get the item
     * at index "index."
     * @param n the current node
     * @param index the current index
     * @return T
     */
    public T getRecursive(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getRecursive(n.next, index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int position;

        public LinkedListDequeIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size();
        }

        public T next() {
            T returnItem = get(position);
            position += 1;
            return returnItem;
        }
    }
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (get(i).equals(x)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque lld) {
            if (lld.size != this.size) {
                return false;
            }
            for (T x : this) {
                if (!lld.contains(x)) {
                    return false;
                }
            }
            for (Object x : lld) {
                if (!this.contains((T)x)) {
                    return false;
                }
            }
            return true;
        } else if (o instanceof ArrayDeque ad) {
            if (ad.size() != this.size) {
                return false;
            }
            for (T x : this) {
                if (!ad.contains(x)) {
                    return false;
                }
            }
            for (Object x : ad) {
                if (!this.contains((T)x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("[");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(get(i).toString());
            returnSB.append(", ");
        }
        returnSB.append(get(size - 1));
        returnSB.append("]");
        return returnSB.toString();
    }
}

