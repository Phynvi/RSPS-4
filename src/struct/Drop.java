package struct;
import root.misc;

	public class Drop {
		
		public int[] contents;
		private int percent;
		
		public Drop(int per, int ... stuff){
			this.contents = stuff;
			this.percent = per;
		}
		
		public Drop(int per, int[] ... stuff){
			int totalSize = 0;
			for (int i = 0; i < stuff.length; i++){
				totalSize += stuff[i].length;
			}
			this.contents = new int[totalSize];
			this.percent = per;
			int j = 0;
			for (int i = 0; i < stuff.length; i++){
				for(int b = 0; b < stuff[i].length; b++){
					this.contents[j] = stuff[i][b];
					j += 1;
				}
			}
		}
				
		public int getRandomDrop(){
			return contents[misc.random(contents.length-1)];
		}
		
		public int getPercent(){
			return this.percent;
		}
		
		@Override
		public String toString(){
			String finished = "";
			for(int i = 0; i < contents.length; i++)
				finished += contents[i]+", ";
			return finished;
		}
		
	}