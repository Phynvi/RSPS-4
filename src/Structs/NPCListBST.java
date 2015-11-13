	public class NPCListBST{
		
		private int elements = 0;
		private int[] array;
		private int[] firstArray;
		private int arrayIndex = 0;
		private Node _current;
		
		private Node root;
		private class Node{
			private String npcName;
			private int npcCombat;
			private int npcHealth;
			private int data;
			Node left;
			Node right;
			public Node(int i, String name, int combat, int health){
				this.data = i;
				this.npcName = name;
				this.npcCombat = combat;
				this.npcHealth = health;
			}
			
		}
				
		public int size(){
			return elements;
		}
		

		/**
		 * Will return a current node's NPC Name.
		 * To set a current node : call the method exists
		 */
		public String getName(){
			return _current.npcName;
		}
		
		/**
		 * Will return a current node's NPC Combat.
		 * To set a current node : call the method exists
		 */
		public int getCombat(){
			return _current.npcCombat;
		}
		
		/**
		 * Will return a current node's NPC Health.
		 * To set a current node : call the method exists
		 */
		public int getHealth(){
			return _current.npcHealth;
		}		
		
		
		/**
		 * Will find a value and set that node to the current node if it exists
		 * @param value Number to search for 
		 * @return true if the number exists in the tree
		 */
		public boolean exists(int value){
			Node temp = findValue(value, root);
			if(temp != null){
				_current = temp;
				return true;
			}
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
		 * Adds a single element to the BST
		 * @param numb Number to add to the structure
		 */
		public void add(int npcID, String name, int combat, int health){
			if(root == null){
				root = new Node(npcID, name, combat, health);
				elements += 1;
				return;
			}			
			Node temp = findNode(npcID, root); //will get the correct node to insert
			if(temp == null){ //means a duplicate was found
				System.out.println("[ERROR] - While adding to NPCListBST, duplicate found of "+npcID);
				return;
			}
			if(npcID < temp.data){ //place to the left
				temp.left = new Node(npcID, name, combat, health);
			}
			if(npcID > temp.data){ //place to the right
				temp.right = new Node(npcID, name, combat, health);
			}
			elements += 1;
		}
		
	}