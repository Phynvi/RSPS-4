package server.handlers.processes;

import java.util.LinkedList;

public abstract class ServerProcess {

	private String ProcessName;
	
	public ServerProcess(String name){
		this.ProcessName = name;
	}
	
	public String GetProcessName(){
		return this.ProcessName;
	}
	
	public abstract void process();
	
	private LinkedList<Long> historyMS = new LinkedList<Long>();
	private int totalAveragesTaken = 0;
	
	public void storeTimeToProcess(long ms){
		this.historyMS.add(ms);
	}
	
	public int getTotalAverages(){
		return this.totalAveragesTaken;
	}
	
	public long getAverageMS(){
		long average = 0;
		for(long l : this.historyMS)
			average += l;
		this.totalAveragesTaken += 1;
		return average/this.historyMS.size();
	}
	
}
