/**
 * @auther Rakesh
 * @time Aug 17, 2016
 */

package com.rkumbhare.app.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Iterable<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public DoublyLinkedList() {
		head = tail = null;
		size = 0;
	}

	static class Node<E> {
		E value;
		Node<E> next;
		Node<E> prev;

		public Node(E value, Node<E> next, Node<E> prev) {
			this.value = value;
			this.next = next;
			this.prev = prev;
		}
	}

	public int size() {
		return size;
	}

	public boolean addFirst(E value) {
		Node<E> newNode = new Node<E>(value, null, null);
		size++;
		// chek list is empty
		if (head == null) {
			head = tail = newNode;
			return true;
		}

		// otherwise add add element to the start of the list
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
		return true;
	}

	public boolean addLast(E value) {
		Node<E> newNode = new Node<E>(value, null, null);
		size++;
		// chek list is empty
		if (head == null) {
			head = tail = newNode;
			return true;
		}

		// otherwise add E to the end of the list
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		return true;
	}

	public boolean add(E value) {
		return addLast(value);
	}

	public boolean add(int index, E value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index : " + index);
		}

		if (index == 0) {
			return addFirst(value);
		}

		if (index == size) {
			return addLast(value);
		}

		Node<E> newNode = new Node<E>(value, null, null);
		size++;
		Node<E> current = head;
		for (int i = 1; i <= index; i++) {
			current = current.next;
		}
		newNode.next = current;
		newNode.prev = current.prev;

		current.prev = newNode;
		newNode.prev.next = newNode;
		return true;
	}

	public E getFirst() {
		if (head == null) {
			return null;
		}
		return head.value;
	}

	public E getLast() {
		if (head == null) {
			return null;
		}
		return tail.value;
	}

	public E get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index : " + index);
		}

		Node<E> current = head;
		for (int i = 1; i <= index; i++) {
			current = current.next;
		}
		return current.value;
	}

	public E removeFirst(){
		if(head == null){
			throw new NoSuchElementException("list is emtpy");
		}
		Node<E> deleteNode = head;
		head = deleteNode.next;
		head.prev = null;
		size--;
		return deleteNode.value;
	}
	
	public E removeLast(){
		if(head == null){
			throw new NoSuchElementException("list is empty");
		}
		
		Node<E> deleteNode = tail;
		
		tail = deleteNode.prev;
		tail.next = null;
		size--;
		return deleteNode.value;
	}
	
	public E remove(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("index : + " + index);
		}
		
		if(index == 0){
			return removeFirst();
		}
		
		if(index == size-1)
			return removeLast();
		
		Node<E> current = head;
		for(int i=1; i<=index; i++){
			current = current.next;
		}
		
		Node<E> prevNode = current.prev;
		prevNode.next= current.next;
		current.next.prev = prevNode;
		size--;
		return current.value;
	}
	
	
	public boolean remove(E value){
		Node<E> current = head;
		Node<E> prev = null;
		
		while(current != null){
			if(current.value == value || current.value.equals(value)){
				if(prev==null){
					removeFirst();
					return true;
				}
				if(current==tail){
					removeLast();
					return true;
				}
				prev.next = current.next;
				current.next.prev = prev;
				size--;
				return true;
			}
			prev = current;
			current = current.next;
		}
		return false;
	}
	
	public static void main(String[] args) {
		DoublyLinkedList<String> linkedList = new DoublyLinkedList<String>();
		linkedList.add(0, "A");
		linkedList.add(1, "B");
		linkedList.add(2, "C");
		linkedList.add(3, "D");
		linkedList.add(1, "AA");
		
		for (String string : linkedList) {
			System.out.println(string);
		}

		System.out.println("Size :" + linkedList.size());
		
		System.out.println(linkedList.remove("D"));
		
		System.out.println("Size :" + linkedList.size() );

	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int cursor = 0;
			public boolean hasNext() {
				return cursor != size;
			}

			public E next() {
				E e = get(cursor);
				cursor++;
				return e;
			}
		};
	}

}
