import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        for(int i = 0; i < 10; i++) {
            ad.addLast(i);
        }
        ad.removeFirst();
        System.out.println(ad.toList());
        for(int i = 0; i < 5; i++) {
            ad.addFirst(i);
        }
        ad.removeFirst();
        System.out.println(ad.toList());
    }

    private int size;
    private T[] aList;
    private int firstIndex;
    private int lastIndex;
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
        firstIndex = aList.length-1;
    }

    public void resizeDown() {
        double usageFactor = (double) size()/aList.length;
        if (usageFactor < 0.25 && aList.length >= 16) {
            int i = 0;
            int newFirstIndex;
            if (firstIndex == aList.length - 1) {
                newFirstIndex = 0;
            } else {
                newFirstIndex = firstIndex + 1;
            }
            T[] tempList = (T[]) new Object[(int) (size() * 1.5)];
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
            resizeUp(10);
        }
        aList[firstIndex] = x;
        size ++;
        if (firstIndex > 0) {
            firstIndex--;
        } else {
            firstIndex = aList.length - 1;
        }
    }

    @Override
    public void addLast(T x) {
        if (isFull()) {
            resizeUp(10);
        }
        aList[lastIndex] = x;
        size ++;
        if (lastIndex == aList.length - 1) {
            lastIndex = 0;
        } else {
            lastIndex ++;
        }
    }

    @Override
    public List<T> toList() {
        if (isEmpty()) {
            return null;
        }
        List<T> returnList = new ArrayList<>();
        int curr = firstIndex + 1;
        if ((!(firstIndex == size()) && curr == size()+1) || curr == aList.length) {
            curr = 0;
        }
        while (true) {
            returnList.add(aList[curr]);
            curr ++;
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
        if(size() == 0) {
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
            firstIndex ++;
        } else {
            temp = aList[0];
            aList[0] = null;
            firstIndex = 0;
        }
        size --;
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
            lastIndex --;
        } else {
            temp = aList[aList.length - 1];
            aList[aList.length - 1] = null;
            lastIndex = aList.length - 1;
        }
        size --;
        resizeDown();
        return temp;
    }

    @Override
    public T get(int index) {
        if (index > size() - 1 || index < 0 || isEmpty()) {
            return null;
        }
        return aList[index];
    }
}
