package org.obc.resmgmt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.obc.resmgmt.Engagement;
import org.obc.resmgmt.resources.BusResource;
import org.obc.resmgmt.resources.Engagements;
import org.obc.resmgmt.resources.ResourcePool;

public class ResMgmtHelper {
	
	public static ResourcePool fillResourcePool() {
		ResourcePool pool = new ResourcePool();
		pool.put("TTOUR-001", new BusResource("TTOUR-001"));
		pool.put("TTOUR-002", new BusResource("TTOUR-002"));
		pool.put("TTOUR-003", new BusResource("TTOUR-003"));
		pool.put("TTOUR-004", new BusResource("TTOUR-004"));
		pool.put("TTOUR-005", new BusResource("TTOUR-005"));
		pool.put("TTOUR-006", new BusResource("TTOUR-006"));
		pool.put("TTOUR-007", new BusResource("TTOUR-007"));
		pool.put("TTOUR-008", new BusResource("TTOUR-008"));
		pool.put("TTOUR-009", new BusResource("TTOUR-009"));
		
		return pool;
	}

	public static Engagements fillEngagements() {
		Engagements engagements = new Engagements();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		try {
			long duration = 35 * 60000l;
			long suruseg = 15 * 60000l;
			long to = sdf.parse("2016.08.30 23:00").getTime();
			for(long from = sdf.parse("2016.08.30 05:00").getTime(); from < to; from += suruseg) {
				engagements.add(getEngagement(from, duration, "33"));
			}
			duration = 50 * 60000l;
			suruseg = 30 * 60000l;
			for(long from = sdf.parse("2016.08.30 06:00").getTime(); from < sdf.parse("2016.08.30 23:00").getTime(); from = from + suruseg) {
				engagements.add(getEngagement(from, duration, "11"));
			}

			duration = 40 * 60000l;
			suruseg = 30 * 60000l;
			for(long from = sdf.parse("2016.08.30 10:00").getTime(); from < sdf.parse("2016.08.30 12:00").getTime(); from = from + suruseg) {
				engagements.add(getEngagement(from, duration, "12"));
			}
			duration = 40 * 60000l;
			suruseg = 30 * 60000l;
			for(long from = sdf.parse("2016.08.30 17:00").getTime(); from < sdf.parse("2016.08.30 21:00").getTime(); from = from + suruseg) {
				engagements.add(getEngagement(from, duration, "12"));
			}
			
			duration = 15 * 60000l;
			suruseg = 10 * 60000l;
			for(long from = sdf.parse("2016.08.30 10:00").getTime(); from < sdf.parse("2016.08.30 15:00").getTime(); from = from + suruseg) {
				engagements.add(getEngagement(from, duration, "15"));
			}


			 duration = 35 * 60000l;
			 suruseg = 15 * 60000l;
			 for(long from = sdf.parse("2016.08.30 04:40").getTime(); from <
			 sdf.parse("2016.08.30 23:00").getTime(); from = from + suruseg) {
			 engagements.add(getEngagement(from, duration, "6"));
			 }
			
			 duration = 65 * 60000l;
			 suruseg = 30 * 60000l;
			 for(long from = sdf.parse("2016.08.30 04:20").getTime(); from <
			 sdf.parse("2016.08.30 23:00").getTime(); from = from + suruseg) {
			 engagements.add(getEngagement(from, duration, "86"));
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return engagements;
	}
	
	
	/************* Toooteleek ********/
	private static Engagement getEngagement(long from, long duration, String label) {
		Engagement e = new Engagement(new Date(from), new Date(from + duration), label);
		System.out.println("Toooteleek: " + e);
		return e;
	}
	
	
}
