package org.obc.resmgmt.resources;

import java.util.HashMap;

import org.obc.resmgmt.exceptions.ResourcesExhaustedException;

public class ResourcePool extends HashMap<String, Resource> {

	public Resource getNewResource(Queues queues) throws ResourcesExhaustedException {
		
		for(Resource res : this.values()) {
			if(!queues.containsKey(res))
				return res;
		}
		throw new ResourcesExhaustedException();
	}
}
