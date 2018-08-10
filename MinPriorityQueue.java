/**
 * @author Jennifer Lee 
 * jelee14108@brandeis.edu
 * This is the class for the Min-Priority Queue 
 *  Note to self: when you touch the priority queue, it should touch the heap, and then it touches the hashmap
 * MIN HEAP - value in node is smaller than its children! 
 */

public class MinPriorityQueue {
	public Heap heap;
	private int capacity; 

	/**
	 * This is the constructor of the min-priority queue 
	 * This method has a constant running time 
	 */
	public MinPriorityQueue(){
		heap= new Heap();
		capacity = 0;
	}
	
	/**
	 * This method returns a boolean value that indicates whether the queue is empty 
	 * This method has a constant running time 
	 * @return true of the priority queue is empty, false if the queue is not empty 
	 */
	public boolean isEmpty() {
		if (capacity == 0) {
			return true;
		} 
		return false;
	}

	/**
	 * This method returns a boolean value indicating whether a particular graphNode 
	 * is in the queue by calling the hasKey method in the heap class. 
	 * This method has a constant running time 
	 * @param g
	 * @return true if the graphNode is in the queue. false otherwise. 
	 */
	public boolean hasKey(GraphNode g) {
		return heap.hasKey(g);
	}

	/**
	 * This method allows an insert to be made into the min priority queue by 
	 * calling the insert method in the heap class. 
	 * This method has a O(logn) running time 
	 * @param g the graphNode to be inserted 
	 */
	public void insert(GraphNode g) {
		heap.insert(g);
		capacity ++;
	}

	/**
	 * This method extracts the highest priority element (in this case, the graphNode
	 * with the lowest integer as its priority) from the queue by calling the heap 
	 * "remove" method 
	 * This method has a O(logn) running time 
	 * @return the graphNode with the highest priority 
	 */
	public GraphNode pullHighestPriorityElement() throws Exception{
		GraphNode priorityElement = heap.heap[0];
		heap.remove();
		capacity--;
		return priorityElement;
	}
	
	/**
	 * This method rebalances the priority queue by shifting graphNodes either 
	 * by calling heapify down or heapify up depending on how the priority of the graphNode
	 * was changed 
	 * This method has a O(logn) running time 
	 * @param g
	 */
	public void rebalance(GraphNode g) {
		heap.heapifyDirection(g);
	}
}
