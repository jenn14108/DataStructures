/**
 * @author Jennifer Lee
 * jelee14108@brandeis.edu
 * This is the class for heaps 
 * Note to self:  Hashmap → gives index for the entry object to be stored in the hashmap array of entries
 * THE VALUE OF THE ENTRY OBJECT IS THE INDEX INTO THE HEAP ARRAY OF GRAPHNODES
 * access min and max element in O(1)
 */
public class Heap {

	public GraphNode[] heap;
	private int maxCapacity; 
	private HashMap map;
	private int capacity; //keeps track of number of elements in the array
	
	/**
	 * This is the constructor for the heap 
	 * It creates a heap and at the same time initializes a new hashmap
	 * This method has a constant running time 
	 */
	public Heap() {
		this.maxCapacity = 200000;
		heap = new GraphNode[maxCapacity];
		map = new HashMap();
		this.capacity= 0;
	}
	
	/**
	 * This method returns the index of the parent node of a child node
	 * This method has a constant running time 
	 * @param index of the child node 
	 * @return the index of the parent node 
	 */
	public int getParent(int index) {
		return (index-1)/2;
	}

	/**
	 * This method returns the index of the left child of a parent node 
	 * This method has a constant running time 
	 * @param index of the parent node 
	 * @return the index of the left child
	 */
	public int getLeftChild(int index) {
		return 2*index+1;
	}

	/**
	 * This method returns the index of the right child of a parent node 
	 * This method has a constant running time 
	 * @param index of the parent node 
	 * @return the index of the right child
	 */
	public int getRightChild(int index) {
		return 2*index+2;
	}
	
	/**
	 * This method returns the left child of a node 
	 * This method has a constant running time 
	 * @param index of the parent node 
	 * @return the left child graphNode object of the parent 
	 */
	public GraphNode getLeftNode (int index) {
		if (index == 0) {
			return heap[1];
		}
		return heap[index*2+1];
	}
	
	/**
	 * This method returns the right child of a node 
	 * This method has a constant running time 
	 * @param index of the parent node 
	 * @return the right child graphNode object of the parent 
	 */
	public GraphNode getRightNode (int index) {
		if (index == 0) {
			return heap[2];
		}
		return heap[index*2+2];
	}
	
	/**
	 * This method returns the parent graphNode of a node 
	 * This method has a constant running time 
	 * @param index of the child node
	 * @return the parent graphNode object of the child
	 */
	public GraphNode getParentNode (int index) {
		if (index == 0) { 
			return null;
		}
		if (index % 2 == 0) { 
			return heap[(int) Math.floor(index/2) -1];
		}
		return heap[(int) Math.floor(index/2)];
	}
	
	/**
	 * This method returns a boolean value indicating whether the heap contains 
	 * a certain graphNode object 
	 * This method has a constant running time 
	 * @param g the graphNode that is being searched for 
	 * @return true if the map contains the key, false otherwise. 
	 */
	public boolean hasKey (GraphNode g) {
		return map.hasKey(g);
	}
	
	/**
	 * This method determines whether the heap is empty 
	 * This method has a constant running time 
	 * @return true if the heap is empty, false otherwise. 
	 */
	public boolean isEmpty() {
		if (capacity == 0 && heap[capacity] == null) {
			return true;
		}
		return false; 
	}
	
	/**
	 * This method inserts an element into the heap 
	 * This method has a O(logn) running time 
	 * @param g the graphNode that is being inserted into the heap 
	 * @throws IllegalStateException an exception is thrown if one tries to insert 
	 * a new graphNode into an already full heap 
	 */
	public void insert(GraphNode g) throws IllegalStateException {
		if (capacity != maxCapacity) {
			heap[capacity]=g;
			map.set(g, capacity);
			capacity++;
			heapifyUp(g, map.getValue(g));
		} else {
			throw new IllegalStateException ("heap capacity reached");
		}
	}
	
	/**
	 * This method is called to determine whether to heapify up or heapify down
	 * This method has a O(logn) running time 
	 * after a remove, or after the priority of a graphNode is changed 
	 * @param g the graphNode either being removed or having its priority changed 
	 */
	public void heapifyDirection(GraphNode g) {
		int index = map.getValue(g);
		if(heap[index].priority<heap[getParent(index)].priority) {
			heapifyUp(g,index);
		} else {
			heapifyDown(g,index);
		}
	}

	/**
	 * This method moves a graphNode  up the heap based on its priority (if necessary)
	 * This method has an O(logn) running time 
	 * @param g the graphNode being heapified up 
	 * @param index the current index of the graphNode 
	 */
	public void heapifyUp(GraphNode g,int index) {
		while(index>0 && heap[index].priority<heap[getParent(index)].priority) {
			map.set(g, getParent(index));
			map.set(heap[getParent(index)], index);
			GraphNode temp = heap[index];
			heap[index]=heap[getParent(index)];
			heap[getParent(index)]=temp;
			index= getParent(index);
		}
	}

	/**
	 * This method moves a graphNode down the heap based on its priority (if necessary)
	 * This method has a O(logn) running time 
	 * @param g the graphNode being heapified down 
	 * @param index the current index of the graphNode 
	 */
	public void heapifyDown(GraphNode g,int index) {
		int leftChild = getLeftChild(index);
		int rightChild = getRightChild(index);
		int smallest = index;
		if (leftChild <capacity && heap[leftChild].priority<heap[index].priority) {
			smallest = leftChild;
		}
		if (rightChild <capacity&&heap[rightChild ].priority<heap[smallest].priority) {
			smallest = rightChild;
		}
		if (smallest != index) {
			GraphNode temp = heap[index];
			map.set(g , smallest);
			map.set(heap[smallest] , index);
			heap[index]=heap[smallest];
			heap[smallest] = temp;
			heapifyDown(g ,smallest);
		}
	}

	/**
	 * This method is called when the highest priority element (the graphNode with the smallest int 
	 * as its priority) is to be removed from the heap.
	 * This method has a O(logn) running time 
	 */
	public void remove(){
		if(capacity==1) {
			heap[0]=null;
			capacity--;
		} else {
			heap[0] = heap[capacity-1];
			heap[capacity-1]=null;
			map.remove(heap[0]);
			capacity--;
			heapifyDown(heap[0],0);
		}
	}
}
