package server.handlers.task;

public abstract class Task {
	public Task(int c, Object[] objects, boolean repeats){
		this.counter = c;
		this.objects = objects;
		this.repeats = repeats;
	}
	public abstract void execute();
	public int counter = 1;
	public Object[] objects;
	public boolean repeats;
	
	
	public String toString(){
		return "Task Information: Counter: "+counter+", Repeats: "+repeats;
	}
	
}
