import java.util.*;

/**
 * This class utilizes the SinglyLinkedNode class to create a singly linked list 
 * @author Jennifer Lee 
 * jelee14108@brandeis.edu
 */
public class SinglyLinkedList<T> {
	private SinglyLinkedNode<T> head;
	private int size; 
	
	/**
	 * The constructor creates the head of the list and sets it to null
	 * This method has a constant running time 
	 * @param no parameter. Automatically constructs a single, empty node
	 */
	public SinglyLinkedList(){
		this.head = null;
		this.size = 0; 
	}
	
	/**
	 * This method returns the head, or first node, of the list
	 * This method has a constant running time 
	 * @return head first node of the list 
	 */
	public SinglyLinkedNode<T> getHead(){
		return head;
	}
	
	/**
	 * This method inserts a new node at the end of the list
	 * This method has a running time of O(n)
	 * @param data to be inserted into a node and added to the list
	 */
	public void regularInsert(T data) {
		SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(data);
		//if head is null then newNode = head
		if (head == null) {
			head = newNode;
			size ++;
		} else {
			SinglyLinkedNode<T> pointer = getHead();
			while (pointer.getNext() != null) {
				pointer = pointer.getNext();
			}
			
			pointer.setNext(newNode);
			size++;
		}
	}
	
	/**
	 * This method randomly inserts a new node containing data in a list
	 * This method has a running time of O(n)
	 * @param data to be inserted into a node and added to the list 
	 */
	public void randomInsert(T data) {
		SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(data);
		SinglyLinkedNode<T> pointer = getHead();
		
		//If the list is empty, make the first insert the head of the list
		if (this.head == null) { 
			head = newNode;
			newNode.setNext(pointer);
			size ++;
		} else {
			Random rand = new Random(); 
			int insertAt = rand.nextInt(size)+1;	
			int currNode = 1;
		
			if (insertAt == 1) {
				this.head = newNode;
				newNode.setNext(pointer);
				size ++;
			} else { 
				while (currNode != insertAt) {
					pointer = pointer.getNext();
					currNode++;
				}
				if (currNode < size) {
					newNode.setNext(pointer.getNext());
				}
				pointer.setNext(newNode);
				size++;
			}
		}
	}
	
	/**
	 * This method removes the node containing the specified data
	 * @param data to be removed from the list 
	 * This method has a running time of O(n)
	 */
	public void remove(T data) {
		SinglyLinkedNode<T> pointer1 = getHead().getNext();
		SinglyLinkedNode<T> pointer2 = getHead();
		
		if (pointer2.getData() == data) {
			this.head = pointer1;
			size--;
		} else {
		
			while (pointer1.getData() != data && pointer1.getNext() != null) {
				pointer1 = pointer1.getNext();
				pointer2 = pointer2.getNext();
			}
		
			if (pointer1.getData() != data) {
				System.out.println("There is no such element. Please try again.");
			} else { 
				pointer2.setNext(pointer1.getNext());
				size--;
			}
		}
	}
	
	/**
	 * This method returns the number of nodes in the list so far
	 * This method has a constant running time 
	 * @return size (number of nodes) of the list
	 */
	public int size() {
		return size; 
	}
	
	/**
	 * This is the toString method of the singly linked list class. It returns, as a string,
	 * all the nodes and the data stored in them in a list. 
	 * This method has a running time of O(n)
	 * @return allNodes all the nodes and their data currently in the list
	 */
	public String toString() {
		SinglyLinkedNode<T> current = getHead();
		String allNodes = "";
		while (current.getNext() != null) {
			allNodes += current.toString() + " ";
			current = current.getNext();
		}
		allNodes += current.toString() + " ";
		return allNodes;
	}
	
}
