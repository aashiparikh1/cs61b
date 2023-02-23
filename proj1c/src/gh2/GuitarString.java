package gh2;

import deque.ArrayDeque;

import java.util.Iterator;

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private ArrayDeque<Double> buffer;

    public GuitarString(double frequency) {

        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.0);
        }
    }


    public void pluck() {
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
            buffer.addLast(Math.random() - 0.5);
        }
    }

    public void tic() {
        double frontVal = buffer.removeFirst();
        double newDouble = ((frontVal + buffer.get(0)) / 2) * DECAY;
        buffer.addLast(newDouble);
    }

    public double sample() {
        Iterator bufferIterator = buffer.iterator();
        return (double) bufferIterator.next();
    }
}
