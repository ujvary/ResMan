package org.obc.resmgmt.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.obc.resmgmt.Engagement;
import org.obc.resmgmt.resources.Engagements;
import org.obc.resmgmt.resources.Queues;
import org.obc.resmgmt.resources.Resource;

public class QueueStatistics {
	
	Queues queues;
	
	private QueueStatistics() {
	}

	public QueueStatistics(Queues queues) {
		this.queues = queues;
	}
	
	public  Integer getResourceCount() {
		return queues.size();
	}
	
	public Map<String, Engagement> getListByStartTime() {
		TreeMap<String, Engagement> map = new TreeMap<String, Engagement>();
		
		for(Engagements engs : queues.values()) {
			for(Engagement e : engs) {
				map.put(e.getDateFrom() + e.getResource().getName(), e);
			}
		}
		
		return map;
	}
	
	public Map<String, Engagement> getListByResourceAndStartTime() {
		TreeMap<String, Engagement> map = new TreeMap<String, Engagement>();
		
		for(Engagements engs : queues.values()) {
			for(Engagement e : engs) {
				map.put( e.getLabel() + e.getDateFrom(), e);
			}
		}
		
		return map;
	}

	public ArrayList<Object[]> getResourceRequirementsProMinutes(Engagements engagements) {
		ArrayList<Object[]> ret = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		try {
			int count;
			for(long now = sdf.parse("2016.08.30 05:00").getTime(); now < sdf.parse("2016.08.30 23:00").getTime(); now += 60000) {
				Object[] o = {time.format(new Date(now)), countRequiredBuses(now, engagements)}; 
				ret.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private int countRequiredBuses(long now, Engagements engagements) {
		int count = 0;
		for(Engagement e : engagements) {
			if(now>=e.getFrom() && now<=e.getTo())
				count++;
		}
		return count;
	}

	public ArrayList<Object[]> countBusRunningTime(Queues queues) {
		ArrayList<Object[]> ret = new ArrayList<>();
		long duration;
		for(Resource res : queues.keySet()) {
			duration = 0;
			for(Engagement e : queues.get(res)) {
				duration += e.getTo()-e.getFrom();
			}
			Object[] o = {res, duration/60000/60}; 
			ret.add(o);
		}
		return ret;
	}

}
