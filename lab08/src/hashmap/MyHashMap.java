package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final double DEFAULT_LOAD_FACTOR = 0.75;
    public static final double RESIZE_FACTOR = 1.5;
    private int initialCapacity;
    private int currentCapacity;
    private double loadFactor;
    private int size;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this.initialCapacity = DEFAULT_INITIAL_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        buckets = new Collection[initialCapacity];
        this.currentCapacity = initialCapacity;
        this.size = 0;
    }

    public MyHashMap(int initialCapacity) {
        buckets = new Collection[initialCapacity];
        this.initialCapacity = initialCapacity;
        this.size = 0;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        buckets = new Collection[initialCapacity];
        this.initialCapacity = initialCapacity;
        this.currentCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if ((double) size / (double) currentCapacity > loadFactor || currentCapacity == 0) {
            int newCapacity;
            if (currentCapacity == 0) {
                newCapacity = DEFAULT_INITIAL_CAPACITY;
            } else {
                newCapacity = (int) (currentCapacity * RESIZE_FACTOR);
            }
            Collection<Node>[] temp = new Collection[newCapacity];
            currentCapacity = newCapacity;
            for (Collection<Node> bucket : buckets) {
                if (bucket == null) {
                    bucket = createBucket();
                }
                for (Node item : bucket) {
                    int hash = item.key.hashCode();
                    int bucketNum = Math.floorMod(hash, currentCapacity);
                    if (temp[bucketNum] == null) {
                        temp[bucketNum] = createBucket();
                    }
                    temp[bucketNum].add(item);
                }
            }
            buckets = temp;
        }
        int hash = key.hashCode();
        int bucket = Math.floorMod(hash, currentCapacity);
        if (buckets[bucket] == null) {
            buckets[bucket] = createBucket();
        }
        for (Node item : buckets[bucket]) {
            if (item.key.equals(key)) {
                item.value = value;
                return;
            }
        }
        buckets[bucket].add(createNode(key, value));
        size++;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int bucket = Math.floorMod(hash, currentCapacity);
        if (bucket >= buckets.length) {
            return null;
        }
        if (buckets[bucket] == null) {
            buckets[bucket] = createBucket();
        }
        for (Node item : buckets[bucket]) {
            if (item.key.equals(key)) {
                return item.value;
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int hash = key.hashCode();
        int bucket = Math.floorMod(hash, currentCapacity);
        if (bucket >= buckets.length) {
            return false;
        }
        if (buckets[bucket] == null) {
            buckets[bucket] = createBucket();
        }
        for (Node item : buckets[bucket]) {
            if (item.key.equals(key)) {
                return true;
            }
        }
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
        buckets = new Collection[initialCapacity];
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 8.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
