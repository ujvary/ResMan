package org.obc.resmgmt.managers;

import org.obc.resmgmt.exceptions.ResourcesExhaustedException;
import org.obc.resmgmt.resources.Engagements;
import org.obc.resmgmt.resources.Queues;
import org.obc.resmgmt.resources.ResourcePool;

public interface ResourceManager {
	public Queues process() throws ResourcesExhaustedException;

	void init(Engagements engagements, ResourcePool resourcePool);
}
