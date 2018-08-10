/**
 * @author Jennifer Lee
 * jelee14108@brandeis.edu
 * This is the the class for the implementation of the HashMap data structure 
 * KEYS --> GraphNodes 
 * VALUES --> index of the graph node in the heap array
 * HASH MAP STORES ENTRY OBJECTS
 **/

public class HashMap {
	private Entry[] map; //initialize an array to store Entry objects 
	private int capacity; //the capacity of the hashmap 
	private int load; //load factor 
	private int hashFactor; //a constant integer that gets progressively larger as the map rehashes 

	
	/**
	 * This is the constructor for the hashmap, I set the default capacity of the hashmap
	 * to 20000 because I got many arrayIndexOutOfBounds exceptions with a capacity less than that. 
	 * This method has a constant running time
	 */
	public HashMap() {
		this.capacity = 20000;
		map = new Entry[capacity];
		this.load = 0;
		this.hashFactor = 1; 
	}
	
	/**
	 * This method determines whether a particular graphNode (key) is in the hashmap 
	 * This method has a constant running time 
	 * @param g graphNode key 
	 * @return true if the graphNode is in the map, and false if the graphNode is not in the map 
	 */
	public boolean hasKey(GraphNode g) {
		if (map[hashFunction(g)]==null || map[hashFunction(g)].getValue()==-1){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * This method returns the value associated with a graphNode (key) if it is found 
	 * This method has a constant running time 
	 * @param g the graphNode that you would like to get the value of 
	 * @return the value associated with a graphNode key. -1 is returned if the key is not found 
	 */
	public int getValue(GraphNode g) {
		if(this.hasKey(g)) {
			return map[hashFunction(g)].getValue();
		} else {
			return -1; //key not found 
		}
	}

	/**
	 * This method does two things: 1. it inserts a new entry object into the hashmap 
	 * 2. It allows the value associated to a graphNode key to be changed when the method is called
	 * by "remove". 
	 * This method has a constant running time 
	 * @param key the graphNode 
	 * @param value the index of the graphNode in the heap array 
	 */
	public void set(GraphNode key, int value) {
		int index = hashFunction(key);
		if(map[index]==null) {
			map[index]= new Entry(key,value);
			load++;
		} else if(map[index].getKey().getId().equals(key.getId())){
				map[index].setValue(value);
		} 
		
		//Rehash if the number of elements exceeds 60% of the capacity 
		if(((double)load/(double)capacity)>0.6) {
			rehash();
		}
	}
	
	/**
	 * This method is called when we want to remove an entry object from the hashMap 
	 * This method has a constant running time 
	 * @param key the graphNode 
	 */
	public void remove (GraphNode key) {
		//setting value to 0 indicates that the entry object is no longer in the map
		this.set(key, 0);
	}

	/**
	 * This is the method used to set the entry objects into the math. The ID of the graphNode
	 * is first parsed into integers and added up, then it is used in a simple modulus function 
	 * to obtain an index. Simple probing is done to find the next available slot. 
	 * This method has a constant running time 
	 * @param key the graphNode with its ID 
	 * @return an index that is vacant
	 */
	public int hashFunction(GraphNode key) {
		int k = 0;
		for (int i = 0; i < key.getId().length(); i++) {
			if (key.getId().charAt(i) != '-') {
				int c = key.getId().charAt(i);
				if (Character.isLetter(c)) {
					k += (int) c;
				} else { 
					k += c - 48;
				}
			}
		}
		int index = (k*hashFactor)%capacity;
		
		//start the probing process
		boolean notHashed =true;
		while(notHashed == true) { 
			if(map[index]==null) {
				return index;
			} else if (map[index].getKey().getId().equals(key.getId())){
				return index;
			} else { 
				index++;//simple probing to find the next index that is vacant
				if (index >= capacity) { //ensure the index doesn't go out of bounds
					notHashed = false;
				}
			}
		}
		return index;
	}
	
	/**
	 * This method is used to rehash the hashmap when it reaches its capacity and is starting
	 * to slow down the normal hashmap functions 
	 * This method has a running time of O(n) as it is doubling the size of the hashMap
	 */
	public void rehash() {
		load = 0;
		capacity = capacity * 2;
		Entry[] newMap = map;
		map = new Entry[capacity];
		//for loop to rehash everything into the new, larger map
		for (int i=0; i< newMap.length ; i++) {
			if (newMap[i]!=null) {
				set(newMap[i].getKey(),newMap[i].getValue());
			}
		}
		
		hashFactor = hashFactor * 2;
	}

	/**
	 * This is a simple toString method that helped with debugging 
	 * This method has an O(n) running time as it goes through the whole array. 
	 */
	public String toString() {
		String content = ""; 
		for (int i = 0; i < map.length; i++) {
			if (map[i] != null && map[i].getValue() != -1) {
				content += "Index: " + i + "\t" + "Entry Object: " + map[i].toString() + "\n";
			}
		}
		return content;
	}
}
