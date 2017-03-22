package com.ru.usty.scheduling.Comparators;

import java.util.Comparator;

import com.ru.usty.scheduling.ProcessHelper;

public class HrrnComparator implements Comparator<ProcessHelper> {

	@Override
	public int compare(ProcessHelper o1, ProcessHelper o2) {
		long o1Time = (o1.getWaitingTime() + o1.getServiceTime()) / o1.getServiceTime();
		long o2Time = (o2.getWaitingTime() + o2.getServiceTime()) / o2.getServiceTime();
		
		if(o1Time < o2Time){
			return 1;
		}
		else{
			return -1;
		}
	}

}
