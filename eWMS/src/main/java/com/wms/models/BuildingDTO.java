package com.wms.models;
 
import java.util.List;
 
public class BuildingDTO {
    private String name;
    private List<String> floor;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getFloor() {
		return floor;
	}
	public void setFloor(List<String> floor) {
		this.floor = floor;
	}
 
}