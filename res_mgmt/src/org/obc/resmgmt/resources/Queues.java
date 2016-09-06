package org.obc.resmgmt.resources;

import java.util.HashMap;

/*
 * Itt vannak az eroforrasok foglaltsagi sorai
 */
public class Queues extends HashMap<Resource, Engagements>{

	public void createQueue(Resource res) {
		this.put(res, new Engagements());
	}
	

}
