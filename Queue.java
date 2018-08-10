/**
 * This is an array implementation of a Queue
 * @author Jennifer Lee 
 * jelee14108@brandeis.edu
 */
public class Queue<T> { 
	private Object[] Q;
	private T data;
	private int front;
	private int rear;
	private int currSize; 
	
	/**
	 * The constructor creates the internal array to the specified size 
	 * and sets the variable currSize (indicating the number of elements currently in the Queue) to 0
	 * The running time of the constructor method is constant. 
	 * @param size
	 */ 
	public Queue(int size) {
		Q = (T[]) new Object[size]; //initialize an Object array and then convert it to a generic type 
		                            //then assign it to Q
		this.front = 0;  // front and rear are both pointing towards the same index ([0]) upon initialization 
		this.rear = 0;
		this.currSize = 0; 
		
	}
	
	/**
	 * This method adds a new element to the end of the queue 
	 * This method has a constant running time 
	 * @param data to be inserted into the queue
	 */
	public void enqueue(T data) {
		if (Q[rear] == null) {
			Q[rear] = data;
			currSize ++; 
			if (rear == Q.length - 1) {
				rear = 0; 
			} else {
				rear++;
			}
		} else {
			System.out.println("Error: No more space in the queue"); 
		}
	}
	
	/**
	 * This method takes off the first item of the queue and returns it 
	 * This method has a constant running time
	 * @return element at the front of the queue 
	 */
	public T dequeue() {
		if (Q[front] != null) {
			T firstItem = (T) Q[front];
			Q[front] = null;
			currSize --; 
			if (front == Q.length - 1 ) {
				front = 0;
			} else { 
				front++; 
			}
		    return firstItem;
		} else {
			System.out.println("Error: There are no more elements to dequeue");
			return null;
		}
	}
	
	/**
	 * This method returns the current number of elements in the queue
	 * This method has a constant running time 
	 * @return currSize current size of the queue 
	 */
	public int getSize() {
		return currSize; 
	}
	
	/**
	 * This method returns true if the queue is empty
	 * This method has a constant running time 
	 * @return boolean value
	 */
	public boolean isEmpty() {
		if (Q[front] == null) {
			return true;
		}
		return false; 
	}

	/**
	 * This method returns true if the queue is full 
	 * This method has a constant running time 
	 * @return boolean value 
	 */
	public boolean isFull() {
		if (Q[rear] != null) {
			return true;
		}
		return false; 
	}
	
	/**
	 * This is the toString method of the Queue class. It prints out all the items stored in the queue.
	 * This method has a running time of O(n)
	 */
	public String toString() {
		String allQ = "";
		for (int i = 0; i < Q.length; i++) {
			if (Q[i] != null) {
				allQ += Q[i] + " ";
			}
		}
		if (!allQ.equals("")) {
			return allQ;
		}
		return "none";
	}
}
