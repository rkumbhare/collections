/**
 * 
 * @auther Rakesh
 * @time Aug 11, 2016
 */

package com.rkumbhare.app.collections;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapImpl<K, V> {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private float loadFactor;
	private int threshold;
	private int size;
	private int modCount;
	private Entry<K, V>[] table;

	static class Entry<K, V> implements Map.Entry<K, V> {
		K key;
		V value;
		int hash;
		Entry<K, V> next;

		public Entry(int hash, K key, V value, Entry<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
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

	public HashMapImpl() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
		table = new Entry[DEFAULT_INITIAL_CAPACITY];
	}

	public int hash(int h) {
		h = h ^ (h >>> 20) ^ (h >>> 12);
		h = h ^ (h >>> 10) ^ (h >>> 7);
		return h;
	}

	public int indexFor(int hash, int length) {
		return hash & (length - 1);
	}

	private void createEntry(int hash, K key, V value, int bucketIndex) {
		Entry<K, V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
		if (size++ >= threshold) {
			table = Arrays.copyOf(table, 2 * table.length);
			threshold = (int) (table.length * loadFactor);
		}
	}

	public V put(K key, V value) {
		if (key == null) {
			return putForNullKey(value);
		} else {
			int hash = hash(key.hashCode());
			int bucketIndex = indexFor(hash, table.length);
			Entry<K, V> e = table[bucketIndex];
			for (; e != null; e = e.next) {
				if (e.hash == hash && (e.key == key || e.key.equals(key))) {
					V oldValue = e.value;
					e.value = value;
					return oldValue;
				}
			}
			createEntry(hash, key, value, bucketIndex);
			modCount++;
			return null;
		}
	}

	private V putForNullKey(V value) {
		Entry<K, V> e = table[0];
		for (; e != null; e = e.next) {
			if (e.hash == 0 && e.key == null) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		}

		createEntry(0, null, value, 0);
		modCount++;
		return null;
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
			return null;
		}
	}

	private V getForNullKey() {
		Entry<K, V> e = table[0];
		for (; e != null; e = e.next) {
			if (e.hash == 0 && e.key == null) {
				return e.value;
			}
		}
		return null;
	}

	public int size() {
		return size;
	}

	public void clear() {
		for (Entry e : table) {
			e = null;
		}
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V remove(K key) {
		if (key == null) {
			return removeForNullKey();
		} else {
			int hash = hash(key.hashCode());
			int bucketIndex = indexFor(hash, table.length);
			Entry<K, V> current = table[bucketIndex];
			Entry<K, V> previous = null;
			while (current != null) {
				if (current.hash == hash && (current.key == key || current.key.equals(key))) {
					if (previous == null) {
						table[bucketIndex] = current.next;
					} else {
						previous.next = current.next;
					}
					size--;
					return current.value;
				}
				previous = current;
				current = current.next;
			}
			return null;
		}
	}

	private V removeForNullKey() {
		Entry<K, V> current = table[0];
		Entry<K, V> previous = null;

		for (; current != null; previous = current, current = current.next) {
			if (current.hash == 0 && current.key == null) {
				if (previous == null) {
					table[0] = current.next;
				} else {
					previous.next = current.next;
				}
				size--;
				return current.value;
			}
		}
		return null;
	}
}
