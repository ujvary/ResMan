package org.obc.resmgmt.managers;

import java.util.Queue;

import org.obc.resmgmt.Engagement;
import org.obc.resmgmt.exceptions.ResourcesExhaustedException;
import org.obc.resmgmt.resources.Engagements;
import org.obc.resmgmt.resources.Queues;
import org.obc.resmgmt.resources.ResourcePool;

public abstract class AbstractResourceManager implements ResourceManager {
protected Engagements unmadeEngagements;
protected Queues queues;
protected ResourcePool resourcePool;

	@Override
	public void init(Engagements engagements, ResourcePool resourcePool) {
		separateEngagements(engagements);
		this.resourcePool = resourcePool;
	}
	
	/*
	 * Szetbontja a mar berendezetteket a berendezetlenektol
	 */
	private void separateEngagements(Engagements engagements) {
		unmadeEngagements = new Engagements();
		queues = new Queues();
		//Ha mar be van osztva -- resource nem null, megy a queuek-ba, ha nem, akkor a beosztandokhoz
		for(Engagement e : engagements) {
			if(e.getResource() != null) {
				Engagements engs = queues.get(e.getResource());
				if(engs == null) {
					engs = new Engagements();
					engs.add(e);
					queues.put(e.getResource(), engs);
				} else {
					engs.add(e);
				}
			} else {
				//unmadeEngagements.add(e.clone());
				unmadeEngagements.add(e);
			} 
		}
	}
	
	@Override
	abstract public Queues process() throws ResourcesExhaustedException;

}
