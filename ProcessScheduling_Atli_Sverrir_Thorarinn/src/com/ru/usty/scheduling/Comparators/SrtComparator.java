package com.ru.usty.scheduling.Comparators;

import java.util.Comparator;

import com.ru.usty.scheduling.ProcessHelper;

public class SrtComparator implements Comparator<ProcessHelper> {

	@Override
	public int compare(ProcessHelper arg0, ProcessHelper arg1) {
		long arg0Time = arg0.getServiceTime() - arg0.getExecutionTime();
		long arg1Time = arg1.getServiceTime() - arg1.getExecutionTime();
		
		if(arg0Time > arg1Time){
			return 1;
		}
		else{
			return -1;
		}
	}

}
