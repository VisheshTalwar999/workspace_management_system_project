package com.wms.dto;

import java.util.List;

public class DepartmentDTO {
    private String name;
    private List<String> subDepartments;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(List<String> subDepartments) {
        this.subDepartments = subDepartments;
    }
}	