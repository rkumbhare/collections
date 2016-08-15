/**
 * @auther Rakesh
 * @time Aug 15, 2016
 */

package com.rkumbhare.app.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements Iterable<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public SinglyLinkedList() {
		head = tail = null;
		size = 0;
	}

	static class Node<E> {
		E value;
		Node<E> next;
		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}
	}

	public int size() {
		return size;
	}

	public void clear() {
		head = tail = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean add(E value) {
		Node<E> newNode = new Node<E>(value, null);
		size++;
		// check list is empty with head==null
		if (head == null) {
			head = tail = newNode;
			return true;
		}

		// add obj to the end
		tail.next = newNode;
		tail = newNode;
		return true;
	}

	public boolean addFirst(E value) {
		Node<E> newNode = new Node<E>(value, null);
		size++;
		// check list is empty with head=null
		if (head == null) {
			head = tail = newNode;
			return true;
		}

		// add oobj to start of list
		newNode.next = head;
		head = newNode;
		return true;
	}

	public boolean addLast(E value) {
		Node<E> newNode = new Node<E>(value, null);
		size++;
		// check list is empty with head=null
		if (head == null) {
			head = tail = newNode;
			return true;
		}

		// add obj to the end of list
		tail.next = newNode;
		tail = newNode;
		return true;
	}

	public boolean add(int index, E value) {
		Node<E> newNode = new Node<E>(value, null);

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index : " + index);
		}

		if (index == 0) {
			return addFirst(value);
		}

		if (index == size) {
			return addLast(value);
		}

		if (index == size - 1) {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
			tail = newNode;
			size++;
			return true;
		}

		Node<E> current = head;
		for (int i = 1; i < index; i++) {
			current = current.next;
		}
		newNode.next = current.next;
		current.next = newNode;
		size++;
		return true;
	}

	public E getFirst() {
		if (size > 0)
			return head.value;
		return null;
	}

	public E getLast() {
		if (size > 0)
			return tail.value;
		return null;
	}

	public E get(int index) {
		if (index < 0 || index > size - 1) {
			return null;
		}

		if (index == 0) {
			return getFirst();
		}

		if (index == size - 1) {
			return getLast();
		}

		Node<E> current = head;
		for (int i = 1; i < index; i++) {
			current = current.next;
		}
		return current.next.value;
	}

	public E removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node<E> deleteNode = head;
		head = head.next;

		if (head == null) {
			tail = null;
		}
		size--;
		return deleteNode.value;
	}

	public E removeLast() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		// check there is only one element in list
		if (head == tail) {
			Node<E> deleteNode = tail;
			head = tail = null;
			size--;
			return deleteNode.value;
		}
		// go to second last element and remove last one
		Node<E> current = head;
		for (int i = 1; i < size - 1; i++) {
			current = current.next;
		}
		Node<E> deleteNode = current.next;
		tail = current;
		current.next = null;
		size--;
		return deleteNode.value;
	}

	public E remove(E value) {
		if (head == null) {
			throw new NoSuchElementException(value.toString());
		}

		Node<E> current = head;
		Node<E> previous = null;
		while (current != null) {
			if (current.value == value || current.value.equals(value)) {
				if (previous == null) {
					return removeFirst();
				}
				if (current == tail) {
					return removeLast();
				}
				previous.next = current.next;
				size--;
				return current.value;
			} else {
				previous = current;
				current = current.next;
			}
		}

		return null;
	}

	public E remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Index : " + index);
		}

		if (index == 0) {
			return removeFirst();
		}
		if (index == size - 1) {
			return removeLast();
		}

		Node<E> current = head;
		for (int i = 1; i < index; i++) {
			current = current.next;
		}
		Node<E> deleteNode = current.next;
		current.next = deleteNode.next;
		size--;
		return deleteNode.value;
	}

	public static void main(String[] args) {
		SinglyLinkedList<String> linkedList = new SinglyLinkedList<String>();

		linkedList.add(0, "1");
		linkedList.add(1, "2");
		linkedList.add(2, "3");
		linkedList.add(3, "5");

		System.out.println(linkedList.remove(1));
		
		System.out.println();

		for(String s : linkedList){
			System.out.println(s);
		}
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int cursor = 0;
			public boolean hasNext() {
				return cursor != size();
			}

			public E next() {
				E e = get(cursor);
				cursor++;
				return e;
			}
		};
	}

}
