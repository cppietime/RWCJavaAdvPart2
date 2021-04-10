package lesson7;

import java.util.NoSuchElementException;

/**
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class OpenHashTable<K, V> implements Iterable<K> {

    private static final int INITIAL_CAPACITY = 2;

    class Bucket {
        K key;
        V value;
        int hash;
        public Bucket(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    class Iterator implements java.util.Iterator<K> {

        private int index;

        public Iterator() {
            index = 0;
            while(index < buckets.length && emptyBucket((Bucket)buckets[index]))
                index++;
        }

        public boolean hasNext() {
            return index < buckets.length;
        }

        public K next() {
            if(!hasNext())
                throw new NoSuchElementException("Reached end of table");
            Bucket bucket = (Bucket)buckets[index];
            index++;
            while(index < buckets.length && emptyBucket((Bucket)buckets[index]))
                index++;
            return bucket.key;
        }
    }

    private Object[] buckets;
    private int size;
    private Bucket EMPTY = new Bucket(null, null, 0);

    public OpenHashTable() {
        buckets = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private boolean emptyBucket(Bucket bucket) {
        return bucket == null || bucket == EMPTY;
    }

    private void doubleArray() {
        Object[] oldArray = buckets;
        buckets = new Object[buckets.length * 2];
        for(Object obj : oldArray) {
            Bucket bucket = (Bucket) obj;
            if(!emptyBucket(bucket)) {
                put(bucket.key, bucket.value);
            }
        }
    }

    public V put(K key, V value) {
        Bucket bucket = getBucket(key);
        if(bucket != null) {
            V old = bucket.value;
            bucket.value = value;
            return old;
        }
        if(size == buckets.length)
            doubleArray();
        int hash = key.hashCode() & 0x7fffffff;
        int index;
        for(index = hash % buckets.length; !emptyBucket((Bucket)buckets[index]); index = (index + 1) % buckets.length);

        bucket = new Bucket(key, value, hash);
        buckets[index] = bucket;
        size++;
        return null;
    }

    private int getBucketIndex(K key) {
        int hash = key.hashCode() & 0x7fffffff;
        int initialIndex = hash % buckets.length;
        int index;
        for(index = hash % buckets.length; buckets[index] != null; index = (index + 1) % buckets.length) {
            Bucket bucket = (Bucket) buckets[index];
            if(bucket == EMPTY)
                continue;
            if(bucket.hash == hash) {
                if(bucket.key.equals(key)) {
                    return index;
                }
            }
            if((index + 1) % buckets.length == initialIndex)
                return -1;
        }
        return -1;
    }

    private Bucket getBucket(K key){
        int index = getBucketIndex(key);
        if(index == -1)
            return null;
        return (Bucket) buckets[index];
    }

    public V get(K key){
        Bucket bucket = getBucket(key);
        if(!emptyBucket(bucket))
            return bucket.value;
        return null;
    }

    public boolean containsKey(K key){
        Bucket bucket = getBucket(key);
        return !emptyBucket(bucket);
    }

    public V remove(K key){
        int index = getBucketIndex(key);
        if(index != -1) {
            V old = ((Bucket) buckets[index]).value;
            buckets[index] = EMPTY;
            return old;
        }
        return null;
    }

    public void printAll() {
        for(K key : this) {
            System.out.println(key + ": " + this.get(key));
        }
    }

    public java.util.Iterator<K> iterator() {
        return new Iterator();
    }

}
