/**
 * @auther Rakesh
 * @time Aug 22, 2016
 */

package com.rkumbhare.app.collection.impl;

import java.util.Arrays;
import java.util.Map;

public class HashMapImpl<K, V> {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private float loadFactor;
	private int threshold;
	private int size;
	private int modCount;
	private Entry<K, V>[] table;

	public HashMapImpl() {
		table = new Entry[DEFAULT_INITIAL_CAPACITY];
		threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
		loadFactor = DEFAULT_LOAD_FACTOR;
	}

	static class Entry<K, V> implements Map.Entry<K, V> {
		int hash;
		K key;
		V value;
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
			return value;
		}
	}

	private int hash(int h) {
		return h ^ (h >>> 16);
	}

	private int indexFor(int hash, int length) {
		return hash & (length - 1);
	}

	public V put(K key, V value) {
		if (key == null) {
			// put into 0th bucket
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

	public V remove(K key) {
		if (key == null) {
			return removeForNullKey();
		} else {
			int hash = hash(key.hashCode());
			int bucketIndex = indexFor(hash, table.length);
			Entry<K, V> e = table[bucketIndex];
			Entry<K, V> previous = null;
			while (e != null) {
				if (e.hash == hash && (e.key == key || e.key.equals(key))) {
					if (previous == null) {
						table[bucketIndex] = e.next;
					} else {
						previous.next = e.next;
					}
					size--;
					return e.value;
				}
				previous = e;
				e = e.next;
			}
		}
		return null;
	}

	private V removeForNullKey() {
		Entry<K, V> e = table[0];
		Entry<K, V> previous = null;

		for (; e != null;) {
			if (e.hash == 0 && e.key == null) {
				if (previous == null) {
					table[0] = e.next;
				} else {
					previous.next = e.next;
				}
				size--;
				return e.value;
			}
			previous = e;
			e = e.next;
		}

		return null;
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

	private void createEntry(int hash, K key, V value, int bucketIndex) {
		Entry<K, V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
		if (size++ >= threshold) {
			table = Arrays.copyOf(table, table.length * 2);
			threshold = (int) (table.length * loadFactor);
		}
	}

	public static void main(String[] args) {
		HashMapImpl<String, String> map = new HashMapImpl<String, String>();
		map.put("A", "A");
		map.put("B", "B");
		map.put("C", "C");
		map.put("P", "P");
		map.put("Q", "Q");
		map.put("R", "R");
		map.put("Z", "Z");
		
		
		System.out.println(map.get("A"));
		System.out.println(map.get("B"));
		System.out.println(map.get("C"));
		System.out.println(map.get("P"));
		System.out.println(map.get("Q"));
		System.out.println(map.get("R"));
		System.out.println(map.get("Z"));
		
		System.out.println(map.remove("R"));
		
		System.out.println(map.size);
	}

}
