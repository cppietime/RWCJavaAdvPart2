package lesson7;

import java.util.Iterator;

public class OpenHashSet<K> implements Iterable<K> {

    private OpenHashTable<K, Object> table;

    public OpenHashSet(){
        table = new OpenHashTable<>();
    }

    public boolean contains(K key) {
        return table.containsKey(key);
    }

    public void add(K key) {
        table.put(key, null);
    }

    public void remove(K key) {
        table.remove(key);
    }

    public Iterator<K> iterator() {
        return table.iterator();
    }

}
