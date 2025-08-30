package com.wms.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wms.dto.SiteDetailDTO;
import com.wms.models.Admin;
import com.wms.models.Employee;
import com.wms.models.SeatDetailFloorWiseDTO;
import com.wms.models.SeatLocationDetail;
import com.wms.models.SeatLocationDetailRequest;
import com.wms.models.SiteDTO;
import com.wms.models.UpdateRequest;
import com.wms.services.WMSService;


@RestController
public class WMSController {
	@Autowired
	WMSService wmsService;
	
	 @PostMapping("/wms/login")
	    public String login(@RequestBody Admin admin) {
	        String username = wmsService.login(admin.getUsername(), admin.getPassword());
	        if (username != null) {
	            return username;
	        } 
	        return "user not found";
	    }
	
	@GetMapping("/wms/getSiteDetails/")
	public List<SiteDetailDTO> getSiteDetails() {
		return wmsService.getSiteDetails();
	}
	
	@GetMapping("/wms/home/")
	public String getAppLevelMsg() {
		return "Hi App started well !! ";
	}
	
	@PostMapping("/wms/setSiteDetails/")
	public String setSeatDetails() {
		return "Hi App started well !! ";
	}
	
	@PostMapping("/wms/addSingleSeat")
	public SeatLocationDetail addSingleSeatLocationDetail(@RequestBody SeatLocationDetail detail) {
		return wmsService.saveSeatLocationDetail(detail);
	}

	@PostMapping("/wms/addMultipleSeat")
	public String addMultipleSeatLocationDetails(@RequestBody SeatLocationDetailRequest request) {
		int number = request.getNumber();
        SeatLocationDetail details = request.getDetails();
        return wmsService.saveSeatLocationDetails(details, number);
	}
	
	@PostMapping("/wms/getSeatDetailsFloorWise")
    public Map<String, List<SeatDetailFloorWiseDTO>> getSeatDetailsFloorWise(@RequestBody SeatLocationDetailRequest request) {
        SeatLocationDetail details = request.getDetails();
        return wmsService.getSeatDetailsFloorWise(details);
    }
	
	@PostMapping("/wms/getSeatDetailsDeptWise")
    public Map<String, List<SeatDetailFloorWiseDTO>> getSeatDetailsDeptWise(@RequestBody SeatLocationDetailRequest request) {
        SeatLocationDetail details = request.getDetails();
        return wmsService.getSeatDetailsDeptWise(details);
    }
	
	@PostMapping("/wms/getSeatDetailsBuildingWise")
    public Map<String, List<SeatDetailFloorWiseDTO>> getSeatDetailsBuildingWise(@RequestBody SeatLocationDetailRequest request) {
        SeatLocationDetail details = request.getDetails();
        return wmsService.getSeatDetailsBuildingWise(details);
    }
	
	@PostMapping("/wms/setEmployee/")
    public String createEmployee(@RequestBody Employee employee) {
        return wmsService.saveEmployee(employee);
    }
	
	@GetMapping("/wms/getSingleEmployee/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return wmsService.getEmployeeById(id);
    }
	
	@PostMapping("/wms/getSeatProximityBySubDept")
    public List<String> getSeatProximityBySubDept(@RequestBody SeatLocationDetailRequest request) {
        SeatLocationDetail details = request.getDetails();
        return wmsService.getSeatProximityBySubDept(details);
    }
	
	@GetMapping("/wms/getSiteBuildingFloorDetails")
    public List<SiteDTO> getSiteBuildingFloorDetails() {
        return wmsService.getSiteBuildingFloorDetails();
    }
	
	@PostMapping("/wms/assignSeatToEmployee")
    public ResponseEntity<SeatLocationDetail> updateSEmpId(@RequestBody UpdateRequest updateRequest) throws Exception {
        SeatLocationDetail updatedDetail = wmsService.updateSEmpId(
                updateRequest.getSeatNo(),
                updateRequest.getEmpEmail(),
                updateRequest.getDepartment(),
                updateRequest.getJobGrade()
        );
        return updatedDetail != null ? ResponseEntity.ok(updatedDetail) : ResponseEntity.notFound().build();
    }
	
	@PutMapping("/wms/deleteEmpId")
    public ResponseEntity<SeatLocationDetail> deleteEmpId(@RequestBody UpdateRequest updateRequest) {
        SeatLocationDetail updatedDetail = wmsService.deleteEmpId(
                updateRequest.getSeatNo(),
                updateRequest.getEmpEmail(),
                updateRequest.getDepartment(),
                updateRequest.getJobGrade()
        );
        return updatedDetail != null ? ResponseEntity.ok(updatedDetail) : ResponseEntity.notFound().build();
    }
	
//	@DeleteMapping("/wms/deleteEmployee/{id}")
//    public String deleteEmployeeAndNullifySEmpId(@PathVariable int id) {
//        wmsService.deleteEmployeeAndNullifySEmpId(id);
//        return "Employee deleted and sEmpId set to null";
//    }
	@DeleteMapping("/wms/deleteEmployee/{id}")
	public String deleteEmployeeAndNullifySEmpId(@PathVariable int id) {     
		wmsService.deleteEmployeeAndNullifySEmpId(id);     
		return "Employee deleted and sEmpId set to null"; 
	}
	
	@PutMapping("/wms/updateEmployee")
    public String updateEmployee(@RequestBody Employee employee) {
        return wmsService.updateEmployee(employee);
    }
	
}
