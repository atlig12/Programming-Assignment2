package com.ru.usty.scheduling;

import com.ru.usty.scheduling.Scheduler;
import com.ru.usty.scheduling.process.ProcessExecution;



public class RR {
	
	
	Scheduler scheduler;
	ProcessExecution processEx;
	int elapsedWaitingTime;
	int elapsedExecutionTime;
	int totalServiceTime;
	
	public RR(Scheduler scheduler, ProcessExecution processEx){
		this.processEx = processEx;
		this.scheduler = scheduler;
		
	}
	
	public int popAndDrop(){
		
		int id = this.scheduler.processQueueRR.element();//Get first element.
		processEx.switchToProcess(id); //Switch to process
		if(manageRunningTime(id)){
			this.scheduler.processQueueRR.remove(); //remove process from queue
			this.scheduler.processQueueRR.add(id); //add first element back to queue
		}
		
		
		
		//for(Object item : this.scheduler.processQueueRR){
		  //  System.out.println(item.toString());
		//}
		return 0;
	}
	
	public boolean manageRunningTime(int processID) {
		boolean timer = false;
		while(timer){
			long timeLimit = this.processEx.getProcessInfo(processID).totalServiceTime;//gets service time
			if(timeLimit == 500){ //quit's loop after 500 mill, 
				timer = true;
			}
		}
		return true;
	}
}