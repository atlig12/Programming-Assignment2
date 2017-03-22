package com.ru.usty.scheduling;

import java.util.Iterator;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.ru.usty.scheduling.Comparators.HrrnComparator;
import com.ru.usty.scheduling.Comparators.SpnComparator;
import com.ru.usty.scheduling.Comparators.SrtComparator;
import com.ru.usty.scheduling.process.ProcessExecution;

public class Scheduler {

	ProcessExecution processExecution;
	Policy policy;
	int quantum;
	boolean processRunning = false;
	Queue<Integer> processQueue;
	PriorityQueue<ProcessHelper> sQueue;
	int runningProcess;
	
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
			processQueue = new LinkedList<Integer>();
			System.out.println("Starting new scheduling task: Round robin, quantum = " + quantum);
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN:	//Shortest process next
			Comparator<ProcessHelper> spnComparator = new SpnComparator();
			sQueue = new PriorityQueue<ProcessHelper>(spnComparator);
			System.out.println("Starting new scheduling task: Shortest process next");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SRT:	//Shortest remaining time
			Comparator<ProcessHelper> srtComparator = new SrtComparator();
			sQueue = new PriorityQueue<ProcessHelper>(srtComparator);
			System.out.println("Starting new scheduling task: Shortest remaining time");
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN:	//Highest response ratio next
			Comparator<ProcessHelper> hrrnComparator = new HrrnComparator();
			sQueue = new PriorityQueue<ProcessHelper>(hrrnComparator);
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
	
	public Thread sleeper(int qnt) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(qnt);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return thread;
	}
	
	public void updateHighestResponse(){
		PriorityQueue<ProcessHelper> tempQ;
		Comparator<ProcessHelper> tempComp = new HrrnComparator();
		tempQ = new PriorityQueue<ProcessHelper>(tempComp);
		
		for(ProcessHelper item: this.sQueue){
			tempQ.add(new ProcessHelper(item.getId(), this.processExecution));
		}
		
		this.sQueue = tempQ;
		
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
		
		case RR:
			processQueue.add(processID);
			Thread thread = this.sleeper(quantum);
			thread.start();
			if(thread.getState() != Thread.State.TIMED_WAITING) {
				processExecution.switchToProcess(processID);
				//processRunning = true;
			}
			
			break;
		
		case SPN:
			System.out.println(processID);
			ProcessHelper sp = new ProcessHelper(processID, this.processExecution);
			sQueue.add(sp);
			if(processRunning == false) {
				ProcessHelper head = sQueue.poll();
				processExecution.switchToProcess(head.getId());
				processRunning = true;
			}
			
			break;
			
		case SRT:
			ProcessHelper sr = new ProcessHelper(processID, this.processExecution);
			sQueue.add(sr);
			
			if(processRunning == false) {
				ProcessHelper head = sQueue.poll();
				processExecution.switchToProcess(head.getId());
				this.runningProcess = head.getId();
				processRunning = true;
			}
			
			ProcessHelper current = new ProcessHelper(this.runningProcess, this.processExecution);
			if(sr.getServiceTime()-sr.getExecutionTime() < current.getServiceTime()-current.getExecutionTime()){
				processExecution.switchToProcess(sr.getId());
				sQueue.poll();
				sQueue.add(current);
				this.runningProcess = sr.getId();
			}
			
			break;
			
		case HRRN:
			ProcessHelper hr = new ProcessHelper(processID, this.processExecution);
			sQueue.add(hr);
			
			if(processRunning == false) {
				ProcessHelper head = sQueue.poll();
				processExecution.switchToProcess(head.getId());
				//this.runningProcess = head.getId();
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
			
		case RR:
			processQueue.remove();
			processRunning = false;
			if(!processQueue.isEmpty()) {
				int id = processQueue.element(); // retrieve the head of the queue
				processExecution.switchToProcess(id);
				processRunning = true;
			}
			
			break;
			
		case SPN:
			processRunning = false;
			if(!sQueue.isEmpty()) {
				ProcessHelper head = sQueue.poll();
				processExecution.switchToProcess(head.getId());
				processRunning = true;
			}
			
			break;
			
		case SRT:
			processRunning = false;
			if(!sQueue.isEmpty()) {
				ProcessHelper head = sQueue.poll();
				processExecution.switchToProcess(head.getId());
				this.runningProcess = head.getId();
				processRunning = true;
			}
			
			break;
			
		case HRRN:
			processRunning = false;
			if(!sQueue.isEmpty()) {
				ProcessHelper head = sQueue.poll();
				processExecution.switchToProcess(head.getId());
				this.updateHighestResponse();
				//this.runningProcess = head.getId();
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
