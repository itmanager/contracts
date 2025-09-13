package com.contract.utils.storage;



public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	public StorageProperties(String location){this.location = location;}
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
