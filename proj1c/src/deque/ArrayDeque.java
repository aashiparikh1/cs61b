package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        ArrayDeque<Integer> lld = new ArrayDeque<>();
        lld.addFirst(0);
        lld.addFirst(1);
        lld.addFirst(2);
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addFirst(0);
        lld2.addFirst(1);
        lld2.addFirst(2);
        System.out.println(lld.equals(lld2));
    }

    private int size;
    private T[] aList;
    private int firstIndex;
    private int lastIndex;

    public static final int MIN_RESIZE_DOWN_LENGTH = 16;
    public static final double RESIZE_FACTOR = 1.5;
    public ArrayDeque() {
        size = 0;
        aList = (T[]) new Object[8];
        firstIndex = 0;
        lastIndex = 1;
    }

    public boolean isFull() {
        if (size() == aList.length) {
            return true;
        }
        return false;
    }

    public void resizeUp(double factor) {
        int i = 0;
        int newLastIndex = lastIndex;
        T[] tempList = (T[]) new Object[(int) (size() * factor)];
        while (true) {
            tempList[i] = aList[newLastIndex];
            i++;
            if (newLastIndex == size() - 1) {
                newLastIndex = 0;
            } else {
                newLastIndex++;
            }
            if (newLastIndex == lastIndex && tempList[0] != null) {
                break;
            }
        }
        aList = tempList;
        lastIndex = i;
        firstIndex = aList.length - 1;
    }

    public void resizeDown() {
        double usageFactor = (double) size() / aList.length;
        if (usageFactor < 0.25 && aList.length >= MIN_RESIZE_DOWN_LENGTH) {
            int i = 0;
            int newFirstIndex;
            if (firstIndex == aList.length - 1) {
                newFirstIndex = 0;
            } else {
                newFirstIndex = firstIndex + 1;
            }
            T[] tempList = (T[]) new Object[(int) (size() * RESIZE_FACTOR)];
            while (true) {
                tempList[i] = aList[newFirstIndex];
                i++;
                if (newFirstIndex == size() - 1 || newFirstIndex == aList.length - 1) {
                    newFirstIndex = 0;
                } else {
                    newFirstIndex++;
                }
                if (i == size()) {
                    break;
                }
            }
            aList = tempList;
            lastIndex = i;
            firstIndex = aList.length - 1;
        }
    }

    @Override
    public void addFirst(T x) {
        if (isFull()) {
            resizeUp(RESIZE_FACTOR);
        }
        aList[firstIndex] = x;
        size++;
        if (firstIndex > 0) {
            firstIndex--;
        } else {
            firstIndex = aList.length - 1;
        }
    }

    @Override
    public void addLast(T x) {
        if (isFull()) {
            resizeUp(RESIZE_FACTOR);
        }
        aList[lastIndex] = x;
        size++;
        if (lastIndex == aList.length - 1) {
            lastIndex = 0;
        } else {
            lastIndex++;
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (isEmpty()) {
            return returnList;
        }
        int curr = firstIndex + 1;
        if ((!(firstIndex == size()) && curr == size() + 1) || curr == aList.length) {
            curr = 0;
        }
        while (true) {
            returnList.add(aList[curr]);
            curr++;
            if (curr == aList.length) {
                curr = 0;
            }
            if (aList[curr] == null || curr == lastIndex || returnList.size() == size()) {
                break;
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T temp;
        if (isEmpty()) {
            return null;
        }
        if (firstIndex < aList.length - 1) {
            temp = aList[firstIndex + 1];
            aList[firstIndex + 1] = null;
            firstIndex++;
        } else {
            temp = aList[0];
            aList[0] = null;
            firstIndex = 0;
        }
        size--;
        resizeDown();
        return temp;
    }

    @Override
    public T removeLast() {
        T temp;
        if (isEmpty()) {
            return null;
        }
        if (lastIndex > 0) {
            temp = aList[lastIndex - 1];
            aList[lastIndex - 1] = null;
            lastIndex--;
        } else {
            temp = aList[aList.length - 1];
            aList[aList.length - 1] = null;
            lastIndex = aList.length - 1;
        }
        size--;
        resizeDown();
        return temp;
    }

    @Override
    public T get(int index) {
        if (index > aList.length - 1 || index < 0 || isEmpty()) {
            return null;
        }
        int indexZero;
        indexZero = (firstIndex + index + 1) % aList.length;
        return aList[indexZero];
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int position;

        public ArrayDequeIterator() {
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
        if (o instanceof ArrayDeque ad) {
            if (ad.size != this.size) {
                return false;
            }
            for (T x : this) {
                if (!ad.contains(x)) {
                    return false;
                }
            }
            return true;
//        } else if (o instanceof LinkedListDeque lld) {
//            if (lld.size() != this.size) {
//                return false;
//            }
//            for (T x : this) {
//                if (!lld.contains(x)) {
//                    return false;
//                }
//            }
//            return true;
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

