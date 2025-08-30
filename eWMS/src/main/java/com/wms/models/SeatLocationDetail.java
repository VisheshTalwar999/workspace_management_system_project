package com.wms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seat_location_detail")
public class SeatLocationDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_no")
    private String seatNo;

    @Column(name = "site")
    private String site;

    @Column(name = "building")
    private String building;

    @Column(name = "floor")
    private String floor;

    @Column(name = "enclosed_room")
    private String enclosedRoom;

    @Column(name = "open_cub_1")
    private String openCub1;

    @Column(name = "open_cub_2")
    private String openCub2;

    @Column(name = "open_cub_4")
    private String openCub4;

    @Column(name = "s_status")
    private String sStatus;

    @Column(name = "job_grade")
    private String jobGrade;

   /* @Column(name = "s_emp_id")
    private String sEmpId;*/
    
    @Column(name = "s_emp_id")
    private int sEmpId;
    
    @Column(name = "dept")
    private String dept;

    @Column(name = "sub_dept")
    private String subDept;

	
	public SeatLocationDetail() {
		super();
	}
	
	

	public SeatLocationDetail(Long id, String seatNo, String site, String building, String floor, String enclosedRoom,
			String openCub1, String openCub2, String openCub4, String sStatus, String jobGrade,
			String dept, String subDept) {
		super();
		this.id = id;
		this.seatNo = seatNo;
		this.site = site;
		this.building = building;
		this.floor = floor;
		this.enclosedRoom = enclosedRoom;
		this.openCub1 = openCub1;
		this.openCub2 = openCub2;
		this.openCub4 = openCub4;
		this.sStatus = sStatus;
		this.jobGrade = jobGrade;
		//this.sEmpId = sEmpId;
		this.dept = dept;
		this.subDept = subDept;
	}



	public SeatLocationDetail(Long id, String seatNo, String site, String building, String floor, String enclosedRoom,
			String openCub1, String openCub2, String openCub4, String sStatus, String jobGrade) {
		super();
		this.id = id;
		this.seatNo = seatNo;
		this.site = site;
		this.building = building;
		this.floor = floor;
		this.enclosedRoom = enclosedRoom;
		this.openCub1 = openCub1;
		this.openCub2 = openCub2;
		this.openCub4 = openCub4;
		this.sStatus = sStatus;
		this.jobGrade = jobGrade;
	}

	public SeatLocationDetail(Long id, String seatNo, String site, String building, String floor, String enclosedRoom,
			String openCub1, String openCub2, String openCub4, String sStatus, String jobGrade, int sEmpId) {
		super();
		this.id = id;
		this.seatNo = seatNo;
		this.site = site;
		this.building = building;
		this.floor = floor;
		this.enclosedRoom = enclosedRoom;
		this.openCub1 = openCub1;
		this.openCub2 = openCub2;
		this.openCub4 = openCub4;
		this.sStatus = sStatus;
		this.jobGrade = jobGrade;
		this.sEmpId = sEmpId;
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

	public String getEnclosedRoom() {
		return enclosedRoom;
	}

	public void setEnclosedRoom(String enclosedRoom) {
		this.enclosedRoom = enclosedRoom;
	}

	public String getOpenCub1() {
		return openCub1;
	}

	public void setOpenCub1(String openCub1) {
		this.openCub1 = openCub1;
	}

	public String getOpenCub2() {
		return openCub2;
	}

	public void setOpenCub2(String openCub2) {
		this.openCub2 = openCub2;
	}

	public String getOpenCub4() {
		return openCub4;
	}

	public void setOpenCub4(String openCub4) {
		this.openCub4 = openCub4;
	}

	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public int getsEmpId() {
		return sEmpId;
	}

	public void setsEmpId(int sEmpId) {
		this.sEmpId = sEmpId;
	}
	
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getSubDept() {
		return subDept;
	}

	public void setSubDept(String subDept) {
		this.subDept = subDept;
	}

    
    
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNo;
    private String site;
    private String building;
    private String floor;
    private String enclosedRoom;
    @Column(name = "open_cub_1")
    private String openCub1;
    @Column(name = "open_cub_2")
    private String openCub2;
    @Column(name = "open_cub_4")
    private String openCub4;
    private String sStatus;
    @Column(name = "s_empId")
    private Integer sEmpId;
    @Column(name = "job_grade")
    private String jobGrade;
    
    
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
	public String getEnclosedRoom() {
		return enclosedRoom;
	}
	public void setEnclosedRoom(String enclosedRoom) {
		this.enclosedRoom = enclosedRoom;
	}
	public String getOpenCub1() {
		return openCub1;
	}
	public void setOpenCub1(String openCub1) {
		this.openCub1 = openCub1;
	}
	public String getOpenCub2() {
		return openCub2;
	}
	public void setOpenCub2(String openCub2) {
		this.openCub2 = openCub2;
	}
	public String getOpenCub4() {
		return openCub4;
	}
	public void setOpenCub4(String openCub4) {
		this.openCub4 = openCub4;
	}
	public String getsStatus() {
		return sStatus;
	}
	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
	public Integer getsEmpId() {
		return sEmpId;
	}
	public void setsEmpId(Integer sEmpId) {
		this.sEmpId = sEmpId;
	}
	public String getJobGrade() {
		return jobGrade;
	}
	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}*/

    
}