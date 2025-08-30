package com.wms.dto;

import java.util.List;

public class SiteDetailDTO {
    private String site;
    private List<DepartmentDTO> departments;

    // Getters and Setters

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<DepartmentDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentDTO> departments) {
        this.departments = departments;
    }
}

