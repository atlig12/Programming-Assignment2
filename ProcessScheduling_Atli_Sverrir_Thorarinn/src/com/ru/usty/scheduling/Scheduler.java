package com.ru.usty.scheduling;

import java.util.LinkedList;
import java.util.Queue;

import com.ru.usty.scheduling.process.ProcessExecution;

public class Scheduler {

	ProcessExecution processExecution;
	Policy policy;
	int quantum;
	boolean processRunning = false;
	Queue<Integer> processQueue;
	
	/**
	 * Add any objects and variables here (if needed)
	 */


	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public Scheduler(ProcessExecution processExecution) {
		this.processExecution = processExecution;
		
		
		
		/**
		 * Add general initialization code here (if needed)
		 */
	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void startScheduling(Policy policy, int quantum) {

		this.policy = policy;
		this.quantum = quantum;
		
		/**
		 * Add general initialization code here (if needed)
		 */

		switch(policy) {
		case FCFS:	//First-come-first-served
			System.out.println("Starting new scheduling task: First-come-first-served");
			processQueue = new LinkedList<Integer>();
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case RR:	//Round robin
			System.out.println("Starting new scheduling task: Round robin, quantum = " + quantum);
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN:	//Shortest process next
			System.out.println("Starting new scheduling task: Shortest process next");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SRT:	//Shortest remaining time
			System.out.println("Starting new scheduling task: Shortest remaining time");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN:	//Highest response ratio next
			System.out.println("Starting new scheduling task: Highest response ratio next");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case FB:	//Feedback
			System.out.println("Starting new scheduling task: Feedback, quantum = " + quantum);
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		}

		/**
		 * Add general scheduling or initialization code here (if needed)
		 */

	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void processAdded(int processID) {
		
		switch (policy) {
		case FCFS:
			processQueue.add(processID);
			if(processRunning == false) {
				processExecution.switchToProcess(processID);
				processRunning = true;
			}
				
			
			break;

		default:
			break;
		}
		
//		System.out.println("pid info: " + processExecution.getProcessInfo(processID));
//		
//		System.out.println("pid: " + processID);
		/**
		 * Add scheduling code here
		 */

	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void processFinished(int processID) {
		
		
		switch (policy) {
		case FCFS:
			processQueue.remove();
			processRunning = false;
			if(!processQueue.isEmpty()) {
				int id = processQueue.element(); // retrieve the head of the queue
				processExecution.switchToProcess(id);
				processRunning = true;
			}
			break;

		default:
			break;
		}
		
		
		System.out.println("pid nr: " + processID + " finished");
		/**
		 * Add scheduling code here
		 */

	}
}
