/**
 * @auther Rakesh
 * @time Aug 14, 2016
 */

package com.rkumbhare.app.collections;

public class SinglyLinkedList_0<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public SinglyLinkedList_0() {
		head = null;
		tail = null;
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
		head = null;
		tail = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean add(E value) {
		Node<E> newNode = new Node<E>(value, null);
		size++;
		if (head == null) {
			head = tail = newNode;
			return true;
		} else {
			tail.next = newNode;
			tail = newNode;
			return true;
		}
	}

	public boolean addFirst(E value) {
		Node<E> newNode = new Node<E>(value, null);
		size++;
		if (head == null) {
			head = tail = newNode;
			return true;
		} else {
			newNode.next = head;
			head = newNode;
			return true;
		}
	}

	public boolean addLast(E value) {
		Node<E> newNode = new Node<E>(value, null);
		size++;
		if (head == null) {
			head = tail = newNode;
			return true;
		} else {
			tail.next = newNode;
			tail = newNode;
			return true;
		}
	}

	public boolean add(int index, E value) {
		if (index > size - 1) {
			throw new IllegalArgumentException("Index '" + index + "' is greater than size '" + size + "'");
		}

		Node<E> newNode = new Node<E>(value, null);

		if (index == 0) {
			newNode.next = head;
			head = newNode;
			size++;
			return true;
		}

		if (index == size - 1) {
			tail.next = newNode;
			tail = newNode;
			size++;
			return true;
		} else {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
			size++;
			return true;
		}
	}

	public E get(int index) {
		if (index > size - 1) {
			throw new IllegalArgumentException("Index '" + index + "' is greater than size '" + size + "'");
		}

		if (index == 0) {
			return head.value;
		}

		if (index == size - 1) {
			return tail.value;
		}

		Node<E> current = head;
		for (int i = 1; i < index; i++) {
			current = current.next;
		}
		return current.next.value;
	}

	public E getFirst() {
		return head.value;
	}

	public E getLast() {
		return tail.value;
	}

	public boolean removeFirst() {
		head = head.next;
		if (head == null) {
			tail = null;
		}
		size--;
		return true;
	}

	public boolean removeLast() {
		if(head == null){
			return false;
		}
		
		if (tail != null && tail == head) {
			head = tail = null;
			size--;
			return true;
		}
		
		Node<E> current = head;
		while(current != null){
			if(current.next==tail){
				current.next = null;
				tail = current;
				size--;
				return true;
			}else{
				current = current.next;
			}
		}
		
		return false;
	}

	public boolean remove(E value) {
		Node<E> current= head;
		Node<E> previous = null;
		
		while(current != null){
			if(current.value==value || current.value.equals(value)){
				if(previous==null){
					head = current.next;
					if(head == null){
						tail = null;
					}
					size--;
					return true;
				}else{
					if(current == tail){
						tail = previous;
					}
					previous.next = current.next;
					size--;
					return true;
				}
			}
			previous = current;
			current = current.next;
		}
		
		return false;
	}
	
	public boolean remove(int index){
		if(index == 0){
			head = head.next;
			if(head==null){
				tail = null;
			}
			size--;
			return true;
		}
		
		if(index == size-1){
			return removeLast();
		}
		
		Node<E> current = head;
		for(int i=1; i<index; i++){
			current = current.next;
		}
		Node<E> deleteNode = current.next;
		if(deleteNode==tail){
			current.next = null;
		}else{
			current.next = deleteNode.next;
		}
		size--;
		return true;
	}

	public static void main(String[] args) {
		SinglyLinkedList_0<String> linkedList = new SinglyLinkedList_0<String>();

		linkedList.add("A");
		linkedList.add("B");
		linkedList.add("C");

		linkedList.addFirst("3");
		linkedList.addFirst("2");
		linkedList.addFirst("1");

		linkedList.addLast("D");
		linkedList.addLast("E");
		linkedList.addLast("F");

		linkedList.add(0, "0");
		linkedList.add(4, "4");
		linkedList.add(5, "5");
		linkedList.add(11, "G");

		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(5));
		System.out.println(linkedList.get(11));
		System.out.println(linkedList.get(12));

		linkedList.clear();
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");
		

		linkedList.remove(2);

		System.out.println(linkedList.size);
	}
}
