import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    private Node root;             // root of BST
    private int size = 0;

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    private Node put(K key, V value, Node N) {
        if (N == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(N.key) == 0) {
            N.val = value;
        }
        if (key.compareTo(N.key) > 0) {
            N.right = put(key, value, N.right);
        }
        if (key.compareTo(N.key) < 0) {
            N.left = put(key, value, N.left);
        }
        return N;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public K remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BSTMap<Integer, Integer> bstmap = new BSTMap<>();
        bstmap.put(5, 5);
        bstmap.put(2, 2);
        bstmap.put(3, 3);
        bstmap.put(6, 6);
        bstmap.put(7, 7);
        bstmap.put(3, 4);
        bstmap.put(7, 8);
        bstmap.put(5, 5);
    }

}
