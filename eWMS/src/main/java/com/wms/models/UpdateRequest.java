package com.wms.models;

public class UpdateRequest {
	
	private String department;
    private String empId;
    private String empEmail;
    private String jobGrade;
    private String seatNo;
    
	public UpdateRequest() {
		super();
	}
	public UpdateRequest(String department, String empId, String empEmail, String jobGrade, String seatNo) {
		super();
		this.department = department;
		this.empId = empId;
		this.empEmail = empEmail;
		this.jobGrade = jobGrade;
		this.seatNo = seatNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getJobGrade() {
		return jobGrade;
	}
	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

}
