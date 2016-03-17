package struct;

public class NumberPair{

	private int elements = 0;
	private int DEFAULTSIZE = 100;
	private int keyArray[] = new int[DEFAULTSIZE];
	private int valueArray[] = new int[DEFAULTSIZE];

	private Node root;
	private class Node{
		int key;
		int value;
		Node left;
		Node right;
		public Node(int i, int j){
			key = i;
			value = j;
		}
	}

	/**
	 * @return Current amount of elements in structure
	 */
	public int size(){
		return elements;
	}

	public int[] getKeyArray(){
		return this.keyArray;
	}
	
	public int[] getValueArray(){
		return this.valueArray;
	}
	
	/**
	 * Will return the value associated with the given key
	 * @param key Key value to search for
	 * @return Value that is paired with the key, will return -1 if not found
	 */
	public int getValue(int key){ 
		Node temp = this.findValue(key, this.root);
		if(temp == null) return -1;
		else return temp.value;
	}

	/**
	 * Private recursive search method
	 * @param numb Number to search for
	 * @param key Root to start searching from
	 * @return The node containing the value, or Null if the value was not found
	 */
	private Node findValue(int key, Node r){
		if(r == null) return null;
		if(key < r.key){ //search left
			if(r.left != null)
				return this.findValue(key, r.left);
			return null; //means it was not found
		}
		if(key > r.key){ //search right
			if(r.right != null)
				return this.findValue(key, r.right);
			return null; //means it was not found
		}
		if(key == r.key)
			return r;
		return null;
	}

	/**
	 * Private recursive helper method, is used by add method
	 * Finds the correct Node that the numb should be inserted after
	 */
	private Node findNode(int key, Node r){
		if(key < r.key){ //search left
			if(r.left != null)
				return this.findNode(key, r.left);
			else return r;
		}
		if(key > r.key){ //search right
			if(r.right != null)
				return this.findNode(key, r.right);
			else return r;
		}
		return null; //if same entry found
	}

	/**
	 * Private helper method associated with add method
	 * Concerns resizing array if elements will exceed capacity
	 */
	private void checkArraySizes(){
		if(this.elements+10 <= this.keyArray.length)
			return;
		
		int[] tempKeyArray = new int[elements*2];
		int[] tempValueArray = new int[elements*2];
		
		for(int i = 0; i < this.keyArray.length; i++){
			int tempKey = this.keyArray[i];
			int tempValue = this.valueArray[i];
			tempKeyArray[i] = tempKey;
			tempValueArray[i] = tempValue;
		}
		this.keyArray = tempKeyArray;
		this.valueArray = tempValueArray;	
	}

	public boolean add(int key, int value){
		if(this.root == null){
			this.root = new Node(key, value);
			this.checkArraySizes();
			this.keyArray[elements] = key;
			this.valueArray[elements] = value;
			this.elements += 1;
			return true;
		}			
		Node temp = this.findNode(key, this.root); //will get the correct node to insert
		if(temp == null){ //means a duplicate was found
			System.out.println("[ERROR] - While adding key to NumberPair, duplicate found of "+key);
			return false;
		}
		if(key < temp.key){ //place to the left
			temp.left = new Node(key,value);
		}
		if(key > temp.key){ //place to the right
			temp.right = new Node(key,value);
		}
		this.checkArraySizes();
		this.keyArray[elements] = key;
		this.valueArray[elements] = value;
		this.elements += 1;
		return true;
	}

}