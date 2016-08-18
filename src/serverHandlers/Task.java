package serverHandlers;

public abstract class Task {
	public Task(int c, Object[] objects){
		this.counter = c;
		this.objects = objects;
	}
	public abstract void execute();
	public int counter = 1;
	public Object[] objects;
}
