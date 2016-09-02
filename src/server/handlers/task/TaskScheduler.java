package server.handlers.task;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import server.root.server;

public class TaskScheduler {
	
	/**
	 * This method will schedule a task through a timer, and start that timer.
	 * @return If task was successfully scheduled, will return the timer associated with its execution.
	 */
	public static Timer schedule(Task t){
		
		if(t == null){
			server.KernelStream.println("In TaskScheduler, void schedule : Given null task t");
			return null;
		}
		final Task task = t;
		
		server.debug("Task t scheduled: "+task.toString());				
		
		int delay = task.counter*500; //t counter is in 500ms ticks
	  ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
      	try{
      		server.debug("Task Executed.");		
          task.execute();
      	}
      	catch(Exception e){
      		server.KernelStream.println(e.toString());
      	}
      }
  };
  
  Timer timer = new Timer(delay, taskPerformer);
  timer.setRepeats(task.repeats);
  timer.start();
  return timer;
	}
	
}
