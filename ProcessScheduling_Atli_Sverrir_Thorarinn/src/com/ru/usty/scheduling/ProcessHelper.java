package com.ru.usty.scheduling;
import com.ru.usty.scheduling.process.ProcessExecution;

public class ProcessHelper {
	//private ProcessExecution processExecution;
	private int id;
	private long executionTime;
	private long waitingTime;
	private long serviceTime;
	
	public ProcessHelper(int n, ProcessExecution p){
		this.id = n;
		//System.out.println(id*5);
		this.executionTime = p.getProcessInfo(n).elapsedExecutionTime;
		this.waitingTime = p.getProcessInfo(n).elapsedWaitingTime;
		this.serviceTime = p.getProcessInfo(n).totalServiceTime;
		//System.out.println(id/2);
	}
	
	public long getExecutionTime() {
		return this.executionTime;
	}
	
	public long getWaitingTime() {
		return this.waitingTime;
	}
	
	public long getServiceTime() {
		return this.serviceTime;
	}
	
	public int getId() {
		return this.id;
	}
}
