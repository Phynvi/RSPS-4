
	public class itemListBST{
		
		private int elements = 0;
		private int[] array;
		private int[] firstArray;
		private int arrayIndex = 0;
		private Node _current;
		
		private Node root;
		private class Node{
			private int data;
			private ItemList e;
			Node left;
			Node right;
			public Node(ItemList e){
				this.data = e.itemId;
				this.e = e;
			}
			
		}
		
		public ItemList getCurrentItem(){
			return _current.e;
		}
				
		public int size(){
			return elements;
		}
		
		public ItemList findItem(int id){
			return findValue(id, root).e;
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
		
		public void buildBalancedTree(ItemList[] items, int start, int end){
			if(start > end)
				return;
			int mid = (start+end)/2;
			this.add(items[mid]);
			buildBalancedTree(items, start, mid-1);
			buildBalancedTree(items, mid+1,end);
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
		public void add(ItemList e){
			if(e == null) return;
			if(root == null){
				root = new Node(e);
				elements += 1;
				return;
			}			
			Node temp = findNode(e.itemId, root); //will get the correct node to insert
			if(temp == null){ //means a duplicate was found
				System.out.println("[ERROR] - While adding to itemListBST, duplicate found of "+e.itemId);
				return;
			}
			if(e.itemId < temp.data){ //place to the left
				temp.left = new Node(e);
			}
			if(e.itemId > temp.data){ //place to the right
				temp.right = new Node(e);
			}
			elements += 1;
		}
		
	}