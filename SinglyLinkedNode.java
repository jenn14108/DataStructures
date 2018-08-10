/**
 * This is the class for Singly Linked Node 
 * @author Jennifer Lee
 * jelee14108@brandeis.edu
 */
public class SinglyLinkedNode<T> {
	private T data;
	private SinglyLinkedNode<T> next;
	
	/**
	 * This is the constructor to create a new node containing specified data 
	 * The new node is set to be pointing towards null as it creates only one single node
	 * This method has a constant running time 
	 * @param data The "information" that is contained in the newly constructed node
	 */
	public SinglyLinkedNode(T data) {
		this.data = data;
		this.next = null;
	}
	
	/**
	 * This method returns the data that is contained in the specific node 
	 * This method has a constant running time 
	 * @return data 
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * When an existing node calls on this method, a pointer is created to point at
	 * the node entered as the parameter 
	 * This method has a constant running time 
	 * @param nextNode the node that is to be the next node after the one that called the method
	 */
	public void setNext(SinglyLinkedNode<T> nextNode) {
		this.next = nextNode;
	}
	
	/**
	 * This method returns the node located after the one calling the method 
	 * This method has a constant running time 
	 * @return next node in the list
	 */
	public SinglyLinkedNode<T> getNext(){
		return next;
	}
	
	/**
	 * This method returns the string representation of the data in the node 
	 * This method has a constant running time 
	 * @return data located in the node
	 */
	public String toString() {
		return data + " ";
		
	}
}
