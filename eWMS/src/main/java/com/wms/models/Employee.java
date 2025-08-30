package com.wms.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
 
@Entity
@Table(name = "emp")
public class Employee {
 
    @Id
    @Column(name = "emp_id")
    private int empId;
 
    @Column(name = "name", length = 100)
    private String name;
 
    @Column(name = "email", length = 100)
    private String email;
 
    @Column(name = "department", length = 100)
    private String department;
 
    @Column(name = "sub_department", length = 100)
    private String subDepartment;
 
    @Column(name = "department_manager", length = 100)
    private String departmentManager;
 
    @Column(name = "sub_department_manager", length = 100)
    private String subDepartmentManager;
 
    @Column(name = "department_administrator", length = 100)
    private String departmentAdministrator;
 
    @Column(name = "staff_category", length = 50)
    private String staffCategory;
 
    @Column(name = "job_grade", length = 50)
    private String jobGrade;
 
    @Column(name = "site", length = 50)
    private String site;
 
    @Column(name = "Staff_laptop_asset_number", length = 50)
    private String staffLaptopAssetNumber;
 
    @Column(name = "Staff_laptop_serial_number", length = 50)
    private String staffLaptopSerialNumber;
 
    @Column(name = "Monitor_asset_number", length = 50)
    private String monitorAssetNumber;
 
    @Column(name = "Monitor_serial_number", length = 50)
    private String monitorSerialNumber;
 
    @Column(name = "Desktop_asset_number", length = 50)
    private String desktopAssetNumber;
 
    @Column(name = "Desktop_serial_number", length = 50)
    private String desktopSerialNumber;
 
    // Getters and Setters
 
    public int getEmpId() {
        return empId;
    }
 
    public void setEmpId(int empId) {
        this.empId = empId;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getDepartment() {
        return department;
    }
 
    public void setDepartment(String department) {
        this.department = department;
    }
 
    public String getSubDepartment() {
        return subDepartment;
    }
 
    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }
 
    public String getDepartmentManager() {
        return departmentManager;
    }
 
    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }
 
    public String getSubDepartmentManager() {
        return subDepartmentManager;
    }
 
    public void setSubDepartmentManager(String subDepartmentManager) {
        this.subDepartmentManager = subDepartmentManager;
    }
 
    public String getDepartmentAdministrator() {
        return departmentAdministrator;
    }
 
    public void setDepartmentAdministrator(String departmentAdministrator) {
        this.departmentAdministrator = departmentAdministrator;
    }
 
    public String getStaffCategory() {
        return staffCategory;
    }
 
    public void setStaffCategory(String staffCategory) {
        this.staffCategory = staffCategory;
    }
 
    public String getJobGrade() {
        return jobGrade;
    }
 
    public void setJobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
    }
 
    public String getSite() {
        return site;
    }
 
    public void setSite(String site) {
        this.site = site;
    }
 
    public String getStaffLaptopAssetNumber() {
        return staffLaptopAssetNumber;
    }
 
    public void setStaffLaptopAssetNumber(String staffLaptopAssetNumber) {
        this.staffLaptopAssetNumber = staffLaptopAssetNumber;
    }
 
    public String getStaffLaptopSerialNumber() {
        return staffLaptopSerialNumber;
    }
 
    public void setStaffLaptopSerialNumber(String staffLaptopSerialNumber) {
        this.staffLaptopSerialNumber = staffLaptopSerialNumber;
    }
 
    public String getMonitorAssetNumber() {
        return monitorAssetNumber;
    }
 
    public void setMonitorAssetNumber(String monitorAssetNumber) {
        this.monitorAssetNumber = monitorAssetNumber;
    }
 
    public String getMonitorSerialNumber() {
        return monitorSerialNumber;
    }
 
    public void setMonitorSerialNumber(String monitorSerialNumber) {
        this.monitorSerialNumber = monitorSerialNumber;
    }
 
    public String getDesktopAssetNumber() {
        return desktopAssetNumber;
    }
 
    public void setDesktopAssetNumber(String desktopAssetNumber) {
        this.desktopAssetNumber = desktopAssetNumber;
    }
 
    public String getDesktopSerialNumber() {
        return desktopSerialNumber;
    }
 
    public void setDesktopSerialNumber(String desktopSerialNumber) {
        this.desktopSerialNumber = desktopSerialNumber;
    }
}