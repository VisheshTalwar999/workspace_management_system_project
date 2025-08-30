package com.wms.models;

public class SeatDetailFloorWiseDTO {
	
	private Long id;
    private String seatNo;
    private String site;
    private String building;
    private String floor;
    private String enclosed_room;
    private String open_cub_1;
    private String open_cub_2;
    private String open_cub_4;
    private String s_status;
    private String job_grade;
    private String s_emp_id;
    private String dept;
    private String sub_dept;
    
    

	public SeatDetailFloorWiseDTO(Long id, String seatNo, String site, String building, String floor,
			String enclosed_room, String open_cub_1, String open_cub_2, String open_cub_4, String s_status,
			String job_grade, String dept, String sub_dept) {
		super();
		this.id = id;
		this.seatNo = seatNo;
		this.site = site;
		this.building = building;
		this.floor = floor;
		this.enclosed_room = enclosed_room;
		this.open_cub_1 = open_cub_1;
		this.open_cub_2 = open_cub_2;
		this.open_cub_4 = open_cub_4;
		this.s_status = s_status;
		this.job_grade = job_grade;
		//this.s_emp_id = s_emp_id;
		this.dept = dept;
		this.sub_dept = sub_dept;
	}


	public SeatDetailFloorWiseDTO(Long id, String seatNo, String site, String building, String floor,
			String enclosed_room, String open_cub_1, String open_cub_2, String open_cub_4, String s_status,
			String job_grade) {
		super();
		this.id = id;
		this.seatNo = seatNo;
		this.site = site;
		this.building = building;
		this.floor = floor;
		this.enclosed_room = enclosed_room;
		this.open_cub_1 = open_cub_1;
		this.open_cub_2 = open_cub_2;
		this.open_cub_4 = open_cub_4;
		this.s_status = s_status;
		this.job_grade = job_grade;
	}
	
	
	public SeatDetailFloorWiseDTO() {
		super();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getEnclosed_room() {
		return enclosed_room;
	}
	public void setEnclosed_room(String enclosed_room) {
		this.enclosed_room = enclosed_room;
	}
	public String getOpen_cub_1() {
		return open_cub_1;
	}
	public void setOpen_cub_1(String open_cub_1) {
		this.open_cub_1 = open_cub_1;
	}
	public String getOpen_cub_2() {
		return open_cub_2;
	}
	public void setOpen_cub_2(String open_cub_2) {
		this.open_cub_2 = open_cub_2;
	}
	public String getOpen_cub_4() {
		return open_cub_4;
	}
	public void setOpen_cub_4(String open_cub_4) {
		this.open_cub_4 = open_cub_4;
	}
	public String getS_status() {
		return s_status;
	}
	public void setS_status(String s_status) {
		this.s_status = s_status;
	}
	public String getJob_grade() {
		return job_grade;
	}
	public void setJob_grade(String job_grade) {
		this.job_grade = job_grade;
	}
	public String getS_emp_id() {
		return s_emp_id;
	}
	public void setS_emp_id(String s_emp_id) {
		this.s_emp_id = s_emp_id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getSub_dept() {
		return sub_dept;
	}
	public void setSub_dept(String sub_dept) {
		this.sub_dept = sub_dept;
	}

}
