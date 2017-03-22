package com.ru.usty.scheduling;

import com.ru.usty.scheduling.Scheduler;
import com.ru.usty.scheduling.process.ProcessExecution;



public class RR {
	
	
	Scheduler scheduler;
	ProcessExecution processEx;
	int elapsedWaitingTime;
	int elapsedExecutionTime;
	int totalServiceTime;
	long timeLimit;
	int processID;
	
	public RR(Scheduler scheduler, ProcessExecution processEx){
		this.processEx = processEx;
		this.scheduler = scheduler;
		this.processID = scheduler.processQueueRR.element();
	}
	
	public int popAndDrop(){
		
		processEx.switchToProcess(this.processID); //Switch to process
		if(manageRunningTime(this.processID)){
			this.scheduler.processQueueRR.remove(); //remove process from queue
			this.scheduler.processQueueRR.add(this.processID); //add first element back to queue
		}
		
		//for(Object item : this.scheduler.processQueueRR){
		  //  System.out.println(item.toString());
		//}
		return 0;
	}
	
	public boolean manageRunningTime(int processID) {//Should be set to true when process has gotten 500ms in service time
		boolean timer = false;
		while(timer){
			this.timeLimit = this.processEx.getProcessInfo(processID).totalServiceTime;//gets service time
			if(timeLimit == 500){ //quit's loop after 500 mill, 
				timer = true;
			}
		}
		return true;
	}
}