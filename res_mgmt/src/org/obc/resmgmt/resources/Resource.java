package org.obc.resmgmt.resources;

public class Resource {
	private String name;
	private String type;
	
	private Resource(){};

	public Resource(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Resource)obj).getName() != null && this.getName() != null && this.getName().equals(((Resource)obj).getName());
	}
	
	
}
