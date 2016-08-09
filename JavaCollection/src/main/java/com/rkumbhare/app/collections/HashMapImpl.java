/**
 * @auther Rakesh
 * @time Aug 10, 2016
 */

package com.rkumbhare.app.collections;

import java.util.Arrays;
import java.util.Map;

public class HashMapImpl<K, V> {

	static final int DEFAULT_INITIAL_CAPACITY = 16;
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	transient Entry[] table;
	transient int size;
	int threshold;
	float loadFactor;
	transient volatile int modCount;

	public HashMapImpl() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
		table = new Entry[DEFAULT_INITIAL_CAPACITY];
	}

	static int hash(int h) {
		// This function ensures that hashCodes that differ only by
		// constant multiples at each bit position have a bounded
		// number of collisions (approximately 8 at default load factor).
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	static int indexFor(int hash, int length) {
		return hash & (length - 1);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void createEntry(int hash, K key, V value, int bucketIndex) {
		Entry<K, V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K, V>(key, value, hash, e);
		if (size++ >= threshold) {
			// System.arraycopy(table, 0, table, 0, 2 * table.length);
			table = Arrays.copyOf(table, 2 * table.length);
			threshold = (int) (table.length * DEFAULT_LOAD_FACTOR);
		}
	}

	public V putForNullKey(V value) {
		Entry<K, V> e = table[0];
		for (; e != null; e = e.next) {
			if (e.key == null) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		}

		createEntry(0, null, value, 0);
		modCount++;
		return null;
	}

	public V put(K key, V value) {
		if (key == null) {
			return putForNullKey(value);
		} else {
			int hash = hash(key.hashCode());
			int bucketIndex = indexFor(hash, table.length);
			Entry<K, V> entry = table[bucketIndex];
			for (; entry != null; entry = entry.next) {
				if (entry.hash == hash && (entry.key == key || entry.key.equals(key))) {
					V oldValue = entry.value;
					entry.value = value;
					return oldValue;
				}
			}
			createEntry(hash, key, value, bucketIndex);
			modCount++;
			return null;
		}
	}

	public V get(K key) {
		if (key == null) {
			return getForNullKey();
		} else {
			int hash = hash(key.hashCode());
			int bucketIndex = indexFor(hash, table.length);
			Entry<K, V> e = table[bucketIndex];
			for (; e != null; e = e.next) {
				if (e.hash == hash && (e.key == key || e.key.equals(key))) {
					return e.value;
				}
			}
		}
		return null;
	}

	public V getForNullKey() {
		Entry<K, V> e = table[0];
		for (; e != null; e = e.next) {
			if (e.key == null) {
				return e.value;
			}
		}
		return null;
	}

	/**
	 * Entry Class Impl
	 * 
	 * @author Rakesh
	 */
	static class Entry<K, V> implements Map.Entry<K, V> {
		final K key;
		V value;
		final int hash;
		Entry<K, V> next;

		public Entry(K key, V value, int hash, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.hash = hash;
			this.next = next;
		}

		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
	}

}
