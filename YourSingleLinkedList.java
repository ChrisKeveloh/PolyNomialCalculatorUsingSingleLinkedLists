/*
 * Created on: 01/27/22
 *
 * ULID: <CKEVELO>
 * Class: IT 168 
 */

package edu.ilstu;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class YourSingleLinkedList<E> implements Iterable<E>
{
	private Node head;
	private int size;
	private E data;

	private class Node<E>
	{
		private E data;
		public Term term;
		public Node<E> next;
		int coeff, power;

	}

	public void add(Term term) // adds a term object to linkedlist
	{
		Node<E> newNode = new Node();
		newNode.data = (E) term;

		if (head == null)
		{
			head = newNode;
		} else
		{
			Node current = head;

			while (current.next != null)
			{
				current = current.next;
			}
			current.next = newNode;
		}
		size++;
	}

	public E get(int index) // getter for current index of node
	{
		Node<E> theNode = getNode(index);
		return theNode.data;
	}

	private Node getNode(int index)
	{
		Node temp = head;
		for (int i = 0; i < index; ++i)
			temp = temp.next;
		return temp;
	}

	public int size() // size method
	{
		Node temp = head; // assigns a temporary node to the head
		int size = 0;

		while (temp != null) // loops through the current list and checks the size and if it's null then we
								// will know its the end
		{
			size++; // adding one to the counter
			temp = temp.next; // updating temp to the next node
		}

		return size;
	}

	public Iterator<E> iterator() // iterator
	{
		return new Iter();
	}

	public class Iter implements Iterator<E>
	{

		private Node<E> prevNode;
		private Node<E> seenNode;
		private Node<E> nextNode;

		private Iter()
		{
			nextNode = head;
		}

		public boolean hasNext()
		{

			return nextNode != null;
		}

		public E next()
		{
			if (hasNext())
			{

				prevNode = seenNode;
				seenNode = nextNode;
				nextNode = nextNode.next;
				return seenNode.data;

			}

			else
				throw new NoSuchElementException();

		}

		public void remove()
		{
			if (seenNode == null)
				throw new IllegalStateException();

			if (seenNode == head)
			{
				head = head.next;
			}

			else
			{
				prevNode.next = nextNode;
			}
			seenNode = null;
			size--;
		}

	}

}
