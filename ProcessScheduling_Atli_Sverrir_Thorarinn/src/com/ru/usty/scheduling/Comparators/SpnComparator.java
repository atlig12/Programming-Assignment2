package com.ru.usty.scheduling.Comparators;

import java.util.Comparator;

import com.ru.usty.scheduling.ProcessHelper;

public class SpnComparator implements Comparator<ProcessHelper> {

	@Override
	public int compare(ProcessHelper o1, ProcessHelper o2) {
		if(o1.getServiceTime() > o2.getServiceTime()){
			return 1;
		}
		else{
			return -1;
		}
	}

}
