package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    Comparator<T> myComparator;
    public MaxArrayDeque(Comparator<T> c) {
        myComparator = c;
    }

    public T max() {
        if (this == null) {
            return null;
        }
        int maxDex = 0;
        for (int i = 0; i < size(); i++) {
            if (myComparator.compare(get(i), get(maxDex)) > 0) {
                maxDex = i;
            }
        }
        return get(maxDex);
    }

    public T max(Comparator<T> c) {
        if (this == null) {
            return null;
        }
        int maxDex = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(maxDex)) > 0) {
                maxDex = i;
            }
        }
        return get(maxDex);
    }

    public static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        StringComparator sc = new StringComparator();
        MaxArrayDeque<String> lld = new MaxArrayDeque<String>(sc);
        lld.addFirst("aashi");
        lld.addFirst("Khushi");
        lld.addFirst("chaitu");
        System.out.println(lld.max());
        IntComparator it = new IntComparator();
        MaxArrayDeque<Integer> imad = new MaxArrayDeque<>(it);
        imad.addFirst(1);
        imad.addFirst(2);
        imad.addFirst(3);
        System.out.println(imad.max(it));
    }
}
