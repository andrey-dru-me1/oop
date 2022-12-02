package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a pair for to objects like (k, v).
 *
 * @param <K> key class type
 * @param <V> value class type
 */
public class Pair<K, V> {
    private final K key;
    private V value;

    @JsonCreator
    public Pair(@JsonProperty("key") K key, @JsonProperty("value") V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " " + value + "\n";
    }
}
