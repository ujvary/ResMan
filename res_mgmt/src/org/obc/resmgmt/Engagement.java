package org.obc.resmgmt;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.obc.resmgmt.resources.Resource;

public class Engagement implements Cloneable {
	private long from;
	private long to;
	private Resource resource;
	private String label; //pl "kalapacs" keszites vagy "8"-as jarat, ami megmutatja, hogy mihez kell majd a hozzarendelt eroforras. Ha egy fele dolgot egy eroforras peldanyon akarunk megcsinaltatni vegig
	
	private Engagement() {}
	
	public Engagement(Date from, Date to, String label) {
		setDateFrom(from);
		setDateTo(to);
		setLabel(label);
	}
	public long getTo() {
		return to;
	}
	public void setDuration(long to) {
		this.to = to;
	}
	public long getFrom() {
		return from;
	}
	public void setFrom(long from) {
		this.from = from;
	}
	public Date getDateFrom() {
		return new Date(from);
	}
	public void setDateFrom(Date from) {
		this.from = from.getTime();
	}
	public Date getDateTo() {
		return new Date(to);
	}
	public void setDateTo(Date to) {
		this.to = to.getTime();
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(getDateFrom()) + " - " + sdf.format(getDateTo()) + " - " + label + " - " + (resource == null ? "" : resource.getName());
	}

	public Engagement clone() {
		try {
			return (Engagement) super.clone();
		} catch (CloneNotSupportedException e) {		
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
