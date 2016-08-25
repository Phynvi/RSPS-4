package server.handlers.NPC;
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
			public Node(NPCList n){
				this.data = n.npcId;
				this.npcName = n.npcName;
				this.npcCombat = n.npcCombat;
				this.npcHealth = n.npcHealth;
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
		
		public void buildBalancedTree(NPCList[] npcs, int start, int end){
			if(start > end)
				return;
			int mid = (start+end)/2;
			this.add(npcs[mid]);
			buildBalancedTree(npcs, start, mid-1);
			buildBalancedTree(npcs, mid+1,end);
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
		public void add(NPCList n){
			if(n == null) return;
			if(root == null){
				root = new Node(n);
				elements += 1;
				return;
			}			
			Node temp = findNode(n.npcId, root); //will get the correct node to insert
			if(temp == null){ //means a duplicate was found
				System.out.println("[ERROR] - While adding to NPCListBST, duplicate found of "+n.npcId);
				return;
			}
			if(n.npcId < temp.data){ //place to the left
				temp.left = new Node(n);
			}
			if(n.npcId > temp.data){ //place to the right
				temp.right = new Node(n);
			}
			elements += 1;
		}
		
	}