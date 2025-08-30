package com.wms.models;

public class SeatLocationDetailDTO {
	
	private Long id;
    private String seatNo;
    
	public SeatLocationDetailDTO(Long id, String seatNo) {
		super();
		this.id = id;
		this.seatNo = seatNo;
	}
	
	public SeatLocationDetailDTO() {
		// TODO Auto-generated constructor stub
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

    

}
