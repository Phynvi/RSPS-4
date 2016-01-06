
	public class BST{
		
		private int elements = 0;
		private int[] array;
		private int[] firstArray;
		private int arrayIndex = 0;
		
		private Node root;
		private class Node{
			int data;
			Node left;
			Node right;
			public Node(int i){
				data = i;
			}
		}
		
		/**
		 * Will add all integers INCLUSIVE to the two values given
		 * I.E. - addrange(8151,8155) will add 8151,8152,8153,8154, and 8155.
		 */
		public void addRange(int start, int end){
			if(start >= end){
				System.out.println("[ERROR] - In addRange function of BST, start >= end");
				return;
			}
			for(int i = start; i <= end; i++){
				add(i);
			}
				
		}
		
		public int size(){
			return elements;
		}
		
		/**
		 * Generates an int array from existing list
		 * Numbers are not sorted
		 */
		public int[] toArray(){
			array = new int[elements];
			generateArray(root);			
			arrayIndex = 0;
			return array;
		}
		
		private void generateArray(Node r){
			if(r == null) return;
			if(r.right != null) generateArray(r.right);
			if(r.left != null) generateArray(r.left);
			array[arrayIndex] = r.data;
			arrayIndex += 1;
		}
		
		public int[] getInitialArray(){
			return firstArray;
		}
		
		/**
		 * Constructor which adds all int array data elements
		 * @param data int array of elements to add to BST
		 */
		public BST(int ... data){
			firstArray = data;
			for(int i = 0; i < data.length; i++)
				add(data[i]);
		}
		
		/**
		 * Will add all values into the BST structure
		 * @param data int array of elements to add
		 */
		public void addAll(int ... data){
			for(int i = 0; i < data.length; i++){
				add(data[i]);
			}
		}
		
		/**
		 * Will find a value 
		 * @param value Number to search for 
		 * @return true if the number exists in the tree
		 */
		public boolean exists(int value){
			if(findValue(value, root) != null)
				return true;
			return false;
		}
		
		/**
		 * Private recursive search method
		 * @param numb Number to search for
		 * @param r Root to start searching from
		 * @return The node containing the value, or Null if the value was not found
		 */
		private Node findValue(int numb, Node r){
			if(r == null) return null;
			if(numb < r.data){ //search left
				if(r.left != null)
					return findValue(numb, r.left);
				return null; //means it was not found
			}
			if(numb > r.data){ //search right
				if(r.right != null)
					return findValue(numb, r.right);
				return null; //means it was not found
			}
			if(numb == r.data)
				return r;
			return null;
		}
		
		/**
		 * Private recursive helper method
		 * Finds the correct Node that the numb should be inserted after
		 */
		public Node findNode(int numb, Node r){
			if(numb < r.data){ //search left
				if(r.left != null)
					return findNode(numb, r.left);
				else return r;
			}
			if(numb > r.data){ //search right
				if(r.right != null)
					return findNode(numb, r.right);
				else return r;
			}
			
			return null; //if same entry found
			
		}
		
		/**
		 * Will add multiple arrays to a BST
		 */
		public void add(int[] ... lists){
			for(int i = 0; i < lists.length; i++)
				this.add(lists[i]);
		}
		
		/**
		 * Adds multiple elements
		 * @param numbs Formatted array of numbers to add
		 */
		public void add(int ... numbs){
			for(int i = 0; i < numbs.length; i++)
				add(numbs[i]);
		}
		
		/**
		 * Adds a single element to the BST
		 * @param numb Number to add to the structure
		 */
		public void add(int numb){
			if(root == null){
				root = new Node(numb);
				elements += 1;
				return;
			}			
			Node temp = findNode(numb, root); //will get the correct node to insert
			if(temp == null){ //means a duplicate was found
				System.out.println("[ERROR] - While adding to BST, duplicate found of "+numb);
				return;
			}
			if(numb < temp.data){ //place to the left
				temp.left = new Node(numb);
			}
			if(numb > temp.data){ //place to the right
				temp.right = new Node(numb);
			}
			elements += 1;
		}
		
	}