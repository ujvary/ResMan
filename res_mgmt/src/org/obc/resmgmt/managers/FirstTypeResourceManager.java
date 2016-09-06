package org.obc.resmgmt.managers;

import org.obc.resmgmt.Engagement;
import org.obc.resmgmt.exceptions.ResourcesExhaustedException;
import org.obc.resmgmt.resources.Engagements;
import org.obc.resmgmt.resources.Queues;
import org.obc.resmgmt.resources.Resource;
import org.obc.resmgmt.resources.ResourcePool;

public class FirstTypeResourceManager extends AbstractResourceManager {
	
	private FirstTypeResourceManager() {
	}
	public FirstTypeResourceManager(Engagements engagements, ResourcePool resourcePool) {
		init(engagements, resourcePool);
	}

	@Override
	public Queues process() throws ResourcesExhaustedException {
		for(Engagement e : unmadeEngagements) {
			boolean isScheduled = false;
			for(Resource r : queues.keySet()) {
				if(queues.get(r).isAdded(e)) {
					e.setResource(r);
					isScheduled = true;
					break;
				}
			}
			//Ha nem sikerult beutemezni egy uj eroforrast kerunk neki
			if(!isScheduled) {
				Resource res = resourcePool.getNewResource(queues);
				queues.createQueue(res);
				queues.get(res).add(e);
				e.setResource(res);
			}
		}
		return queues;
	}

}
