package server.tests;

import server.handlers.task.Task;
import server.handlers.task.TaskScheduler;
import server.root.server;

public class TaskSchedulerTests {

	public TaskSchedulerTests(){
		server.tests.add(new TimeTests("TaskScheduler Time Tests"));
	}
	
	
	private class TimeTests extends Test{

		public TimeTests(String testName) {
			super(testName);
		}

		@Override
		String runTest() throws Exception {
			scheduleTimeTask(10);
			scheduleTimeTask(30);
			scheduleTimeTask(60);
			return null;
		}
		
		private void scheduleTimeTask(int seconds){
			
			server.test("Scheduling test time task with delay: "+seconds+" seconds.");
			TaskScheduler.schedule(getTimeTestTask(seconds));
		}
		
		private Task getTimeTestTask(int seconds){
			
			long oldTime = System.currentTimeMillis();
			final Object[] args = new Object[]{oldTime, seconds};
			
			return new Task(seconds*2, args, false){
				long oldTime = (long)args[0];
				int seconds = (int)args[1];
				
				@Override
				public void execute() {
					long newTime = System.currentTimeMillis();
					long timeDiff = newTime - oldTime;
					server.test("REPORT : Task scheduled at "+seconds+" seconds, took "+timeDiff+" to execute.");
				}
				
			};			
		}
		
	}
	
}
