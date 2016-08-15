/**
 * @auther Rakesh
 * @time Aug 15, 2016
 */

package com.rkumbhare.app.collection.impl;

import java.util.Arrays;

public class ArrayListImpl<E> {
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	private int threshold = 5;
	private int size;
	private Object[] array;

	public ArrayListImpl() {
		array = new Object[DEFAULT_INITIAL_CAPACITY];
		size = 0;
	}

	public int size() {
		return size;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			array[i] = null;
		}
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(E ele) {
		array[size] = ele;
		size++;
		if (array.length - size <= threshold) {
			resize();
		}
	}
	
	public E get(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("index : " + index);
		}
		
		return (E) array[index];
	}
	
	public E remove(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("index : " + index);
		}
		
		E ele = (E) array[index];
		System.arraycopy(array, index+1, array, index, array.length-size);
		size--;
		return ele;
	}
	
	public boolean remove(E e){
		int index = -1;
		for(int i=0; i<size(); i++){
			if(e==array[i] || e.equals(array[i])){
				index = i;
				break;
			}
		}
		if(index != -1){
			remove(index);
			return true;
		}
		return false;
	}
	
	public boolean contains(E value){
		for(Object e: array){
			if(e==value || e.equals(value)){
				return true;
			}
		}
		return false;
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
		
		System.out.println(list.remove("2"));
		System.out.println(list.size());
	}

}
