package com.wms.models;
 
import java.util.List;
 
public class SiteDTO {
    private String site;
    private List<BuildingDTO> building;
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public List<BuildingDTO> getBuilding() {
		return building;
	}
	public void setBuilding(List<BuildingDTO> building) {
		this.building = building;
	}
 
}