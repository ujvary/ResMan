package org.obc.resmgmt.resources;

import java.util.ArrayList;

import javax.annotation.Generated;

import org.obc.resmgmt.Engagement;

public class Engagements extends ArrayList<Engagement> {

	/***
	 *  EL tudja-e vegezni az eroforras a munkat? 
	 ***/
	public boolean isAdded(Engagement e) {
		boolean free = true;
		for(Engagement _e : this) {
			if(isOverlapped(e, _e)) {
				free = false;
			}
		}
		if(free) add(e);
		return free;
	}

	public boolean isOverlapped(Engagement e, Engagement _e) {
	return e.getFrom() <= _e.getTo() && _e.getFrom() <= e.getTo();
}
	
}
