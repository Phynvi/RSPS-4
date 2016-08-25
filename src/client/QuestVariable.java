package client;

public class QuestVariable {
	private int val;
	
	public QuestVariable(int i){
		this.val = i;
	}
	
	public void increment(){
		this.val += 1;
	}
	
	public int getValue(){
		return this.val;
	}
	
	public void setValue(int i){
		this.val = i;
	}
	
}


