/**
 * @auther Rakesh
 * @time Aug 15, 2016
 */

package com.rkumbhare.app.collections;

import java.util.Arrays;

public class ArrayListImpl<E> {
	private int DEFAULT_INITIAL_CAPACITY = 10;
	private int threshold = 5;
	private int size;
	private Object[] array;

	public ArrayListImpl() {
		array = new Object[DEFAULT_INITIAL_CAPACITY];
		threshold = 5;
		size = 0;
	}

	public int size() {
		return size;
	}

	public void clear() {
		for (Object obj : array) {
			obj = null;
		}
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(E value) {
		array[size] = value;
		size++;
		if ((array.length - size) == threshold) {
			resize();
		}
	}
	
	public E get(int index){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("index : " + index);
		}
		return (E) array[index];
	}

	public E remove(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("index : " + index);
		}
		
		E obj = (E) array[index];
		System.arraycopy(array, index+1, array, index, size-(index));
		size--;
		return obj;
	}
	
	private void resize() {
		array = Arrays.copyOf(array, array.length * 2);
	}
	
	

	public static void main(String[] args) {
		ArrayListImpl<String> list = new ArrayListImpl<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		
		System.out.println(list.remove(6));
		
		System.out.println(list.size());
	}

}
