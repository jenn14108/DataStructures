/**
 * @author Jennifer Lee
 * jelee14108@brandeis.edu
 * This is the AVLNode class 
 */

public class AVLNode<T> {

	private T data;
	private double value;
	private AVLNode<T> parent;
	private AVLNode<T> leftChild;
	private AVLNode<T> rightChild;
	private int rightWeight; //number of nodes in the right subtree
	private int balanceFactor;


	/**
	 * This is the constructor which takes in a generic data type as data, and a value of type double, which is 
	 * the value used to determine where new nodes should be inserted in the AVL tree 
	 * This method has a constant running time 
	 * @param data generic data type 
	 * @param value any double 
	 */
	public AVLNode(T data, double value){
		this.data = data;
		this.value = value;
		this.balanceFactor = 0; 
		this.rightWeight = 0; 
	}

	/**
	 * This method sets the parent of a node 
	 * This method has a constant running time 
	 * @param parent 
	 */
	public void setParent(AVLNode<T> parent) {
		this.parent = parent;
	}

	/**
	 * This method sets the left child of a node
	 * This method has a constant running time 
	 * @param leftChild left node
	 */
	public void setLeftChild(AVLNode<T> leftChild){
		this.leftChild = leftChild;
	}

	/**
	 * This method sets the right child of a node
	 * This method has a constant running time 
	 * @param rightChild right node
	 */
	public void setRightChild(AVLNode<T> rightChild){
		this.rightChild = rightChild;
	}

	/**
	 * This method returns the parent of a node (hash code, needs to call singleTreeString() to print in string format)
	 * This method has a constant running time 
	 * @return parent hashcode 
	 */
	protected AVLNode<T> getParent() {
		return parent;
	}

	/**
	 * This method returns the left child of a node (hash code, needs to call toString() method to print in string format)
	 * This method has a constant running time 
	 * @return leftChild hashcode
	 */
	protected AVLNode<T >getLeftChild(){ 
		return leftChild;
	}

	/**
	 * This method returns the right child of a node (hash code, needs to call toString() method to print in string format)
	 * This method has a constant running time 
	 * @return right child hashcode 
	 */
	protected AVLNode<T> getRightChild() { 
		return rightChild;
	}
	
	/**
	 * This method returns the value stored inside the node (The value used to determine where to insert new nodes in the AVL tree) 
	 * This method has a constant running time 
	 * @return a value of type double 
	 */
	protected double getValue() {
		return value;
	}
	
	
	/**
	 * This method returns the root of a tree 
	 * This method has a running time proportional to the height of the tree O(h)
	 * @param node the node from which the method starts traversing up the tree to find the root 
	 * @return the root of the tree (the node with no parent) 
	 */
	protected AVLNode<T> getRoot(AVLNode<T> node) {
		if (node.getParent() == null) {
			return node;
		} else {
			return getRoot(node.getParent());
		}
	}
	
	/**
	 * This method calls the helper method checkHeight()
	 * Since the running time of checkHeight() is proportional to the height of the tree,
	 * this method also has a running time of O(h)
	 * @return height
	 */
	protected int getHeight () {
		return checkHeight(this);
	}
	
	/**
	 * This method travels up the tree in order to find balance factor of each node
	 * This running time would be proportional to the height of the tree
	 */
	protected void checkUp() {
		AVLNode<T> current = this;
		while (current != null) {
			current.checkBF();
			current = current.getParent();
		}
	}
	
	public void travelDown(){
		this.validRight();
		
		if (this.getLeftChild() != null) {
			this.getLeftChild().validRight();
			this.getLeftChild().travelDown();
		}
		if (this.getRightChild() != null) {
			this.getRightChild().validRight();
			this.getRightChild().travelDown();
		}
	}
	
	/**
	 * This method checks that there is a node to be called on to find the right Weight
	 * Since this method calls on the updateRightWeight() method the running time 
	 * is proportional to the number of nodes rooted in the rightSubtree of the node 
	 * that called the method
	 */
	public void validRight() { 
		if (this.getRightChild() != null) {
			this.rightWeight = this.getRightChild().updateRightWeight();
		//System.out.println("THIS IS THE RIGHT WEIGHT OF " + this.singleTreeString() + " : " + this.rightWeight);
		}
	}
	
	/**
	 * This method finds the weight of a node
	 * This method running time is proportional to the height of the tree
	 * @return int rightWeight
	 */
	public int updateRightWeight() { 
		if (this.getLeftChild() == null && this.getRightChild() == null) {
			return 1;
		} else {
			int countRight = 0; 
			int countLeft = 0;
			if (this.getRightChild() !=null) {
				countRight = this.getRightChild().updateRightWeight();
			} 
			if(this.getLeftChild() !=null) {
				countLeft = this.getLeftChild().updateRightWeight();
			}
			return countLeft + countRight+ 1;
		}
	}

	/**
	 * This should return the new root of the tree
	 * make sure to update the balance factor and right weight
	 * and use rotations to maintain AVL condition
	 */
	public AVLNode<T> insert(T newData,double value) {
		AVLNode<T> newNode = new AVLNode<>(newData, value);
		insert (this, newNode);
		getRoot(newNode).travelDown();
		return getRoot(newNode);
	}
	
	/**
	 * This is the helper method for the main insert method 
	 * This method, itself, has a running time of O(logn)
	 * @param v
	 * @param newNode
	 * @throws Exception
	 */
	protected void insert (AVLNode<T> v, AVLNode<T> newNode) {
		if (v.getValue() <= newNode.getValue() && v.getRightChild() == null) { 
				v.setRightChild(newNode);
				newNode.setParent(v);
		} else if (v.getValue() == newNode.getValue() && v.getRightChild() != null){ 
					minimum(v.getRightChild()).setLeftChild(newNode);
					newNode.setParent(v.getRightChild());
		} else if (v.getValue() > newNode.getValue() && v.getLeftChild() == null) { 
			v.setLeftChild(newNode);
			newNode.setParent(v);
		} else {
			// go to the right subtree if the value in v is less than the value in newNode
			if (v.getValue() < newNode.getValue()) {
				insert(v.getRightChild(), newNode);
			} else { //if the value in v is greater than the node being inserted then move towards the left subtree
				insert(v.getLeftChild(), newNode);
			}
		}
		
		newNode.checkUp();
	}

	
	/**
	 * This method checks the balance factor and updates it for all relevant nodes after an insert
	 * This method has a running time largely dictated by the method checkHeight, which has a running
	 * time proportional to the height of the tree or subtree
	 */
	protected void checkBF() {
		int leftHeight = checkHeight(this.getLeftChild());
		int rightHeight = checkHeight(this.getRightChild());
		this.balanceFactor = leftHeight - rightHeight;
		if (balanceFactor < -1) { //tree right heavy
			if (this.getRightChild().balanceFactor > 0) { //tree left heavy
				this.getRightChild().rotateRight(); //rotate right first
				this.rotateLeft(); //rotate left 
			} else {
				this.rotateLeft(); //rotate left 
			}
		}
		
		if (balanceFactor > 1) { //tree left heavy
			if (this.getLeftChild().balanceFactor < 0) { //tree right heavy
				this.getLeftChild().rotateLeft(); //rotate left first
				this.rotateRight(); //rotate right 
			} else {
				this.rotateRight(); //rotate right 
			}
		}
	}
	
	/**
	 * This method checks the height of a particular node
	 * This running time is proportional to the height of the node 
	 * @param node
	 * @return height
	 */
	protected int checkHeight(AVLNode<T> node) { 
		if ( node == null) {
			return -1;
		} else if (node.getLeftChild() == null && node.getRightChild() == null) {
			return 0;
		} else { 
			int h = 0;
			h = Math.max(h, checkHeight(node.getLeftChild()));
			h = Math.max(h, checkHeight(node.getRightChild()));
			return 1 + h;
		}
		
	}
	
	/**
	 * This should return the new root of the tree
	 * Runnign time is O(logn)
	 * @return the new root of the tree
	 */
	public AVLNode<T> delete(double value){
		return delete(this, value);
	}
	
	/**
	 * This is the helper method for the delete method 
	 * This method itself has a running time of O(logn). It also calls the methods successor() and getRoot(),
	 * but the running time of the main delete method is still greater; therefore, the running time is O(logn)
	 * @param v the root node
	 * @param value the value corresponding to the node that should be deleted
	 * @return the new root node 
	 */
	protected AVLNode<T> delete(AVLNode<T> v, double value) {
		if (v.getValue() == value) {
			AVLNode<T> temp = null;
			AVLNode<T> temp1 = null;
			if (v.getLeftChild() == null || v.getRightChild() == null) {
				temp = v;
			} else {
				temp = v.successor();
			}
			if (temp.getLeftChild() != null) {
				temp1 = temp.getLeftChild();
			} else {
				temp1 = temp.getRightChild();
			}
			if (temp1 != null) {
				temp1.setParent(temp.getParent());
			}
			if (temp == temp.getParent().getLeftChild()) {
				temp.getParent().setLeftChild(temp1);
			} else {
				temp.getParent().setRightChild(temp1);
			}
			if (temp.data != v.data) {
				v.data = temp.data;
				v.value = temp.getValue();
			}
			
			getRoot(v).travelDown();
			return getRoot(v);

		} else {
			if (v.value < value) {
				return delete(v.getRightChild(), value);
			} else { // v.value > value 
				return delete(v.getLeftChild(), value);
			}
		}
	}
	
	/**
	 * This method returns the successor of the node that calls the method 
	 * This method has a running time of O(h)
	 * @return the successor of the node calling the method
	 */
	protected AVLNode<T> successor(){
		AVLNode<T> v = this;
		if (v.getRightChild() != null) {
			return minimum(v.getRightChild());
		} else {
			AVLNode<T> w = v.getParent();
			while (w != null && v== w.getRightChild()) {
				v = w;
				w = w.getParent();
			}
			return w; 
		}
	}
	
	/**
	 * This method finds the minimum from the passed in node 
	 * This method has a running time proportional to the height of the subtree; therefore,
	 * the running time of this method is O(h)
	 * @param node passed in to find the minimum value after the passed in node  
	 * @return the minimum node 
	 */
	protected AVLNode<T> minimum(AVLNode<T> node){
		while (node.getLeftChild() != null) {
			node = node.getLeftChild();
		}
		return node;
	}
	
	/**
	 * This is the method that allows a node to rotate right in order to maintain 
	 * the balance of the AVL tree 
	 * This method has a constant running time 
	 */
	private void rotateRight(){
		AVLNode<T> temp1 = this.getLeftChild();
		this.setLeftChild(temp1.getRightChild());

		if (temp1.getRightChild() != null){
			temp1.getRightChild().setParent(this);
		}

		temp1.setParent(this.getParent());

		if (this.getParent() == null){
			temp1.setParent(this.getParent());
		} else if (this == this.getParent().getRightChild()){
			this.getParent().setRightChild(temp1);
		} else {
			this.getParent().setLeftChild(temp1);
		}

		temp1.setRightChild(this);
		this.setParent(temp1);
	}

	/**
	 * This method allows a node to rotate left in order to maintain
	 * the balance of the AVL tree 
	 * This method has a constant running time 
	 */
	private void rotateLeft(){
		AVLNode<T> temp1 = this.getRightChild();
		this.setRightChild(temp1.getLeftChild());

		if (temp1.getLeftChild() != null){
			temp1.getLeftChild().setParent(this);
		}

		temp1.setParent(this.getParent());
		
		if (this.getParent() != null) {
			if (this == this.getParent().getLeftChild()){
				this.getParent().setLeftChild(temp1);
			} else {
				this.getParent().setRightChild(temp1);
			}
		}

		temp1.setLeftChild(this);
		this.setParent(temp1);
	}

	/**
	 * This method should return the data stored in the node with this.value == value
	 * This method has a running time of O(logn)
	 */
	public T getData(double value){
		if (this.value == value) {
			return this.data;
		} else if (this.value > value && this.getLeftChild() != null) {
			return this.getLeftChild().getData(value);
		} else if (this.value < value && this.getRightChild() != null) {
			return this.getRightChild().getData(value);
		} else {
			return null;
		}
	} 
		
	/**
	 * This is the toString() method for a single node which returns both the data and the value of the node
	 * This method has a constant running time 
	 * @return data stored inside a single node 
	 */
	public String singleTreeString() {
		return "" + this.data + ", " + this.getValue();
	}

	/**
	 * This is the toString method for the entire AVL tree 
	 * Since this method calls the method inOrder(), it has a running time of O(n)
	 */
	public String treeString(){
		String tree = "";
		tree = inOrder(this);
		return tree;
	}
	
	/**
	 * This method does the inOrder traversal necessary to return the AVL tree in string format
	 * This method has a O(n) running time 
	 * @param v root 
	 * @return AVL tree in string representation 
	 */
	protected String inOrder(AVLNode<T> v) {
		String t = "(";
		if (v.getLeftChild() == null && v.getRightChild() == null) {
			return t + v.data + ')';
		}
		else {
			if (v.getLeftChild() != null) {
				t += inOrder(v.getLeftChild());
			}
				t += v.data;
			if (v.getRightChild() != null) {
				t += inOrder(v.getRightChild());
			}
			return t += ')';
		}
	}
}