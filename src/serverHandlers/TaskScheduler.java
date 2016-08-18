package serverHandlers;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.ListIterator;

import Resources.misc;

public class TaskScheduler {
	
	private static LinkedList<Task> _tasks = new LinkedList<Task>();

	public void process(){
		if(!_tasks.isEmpty()){
			try{
				ListIterator<Task> iterator = _tasks.listIterator();
				while(iterator.hasNext()){
					Task c = iterator.next();
					if(--c.counter == 0){
						misc.println_debug("[TaskScheduler] - process() : Task Executed.");
						c.execute();
						iterator.remove();
					}
				}
			}
			catch(ConcurrentModificationException ce){
				return;
			}
		}
	}
	
	public static void schedule(Task t){
		
		if(t == null){
			misc.println_debug("In TaskScheduler, void schedule : Given null task t");
			return;
		}
		_tasks.add(t);
		
	}
	
	public static void schedule(Task ... tasks){
		for(Task t : tasks){
			_tasks.add(t);
		}
	}
	
}
