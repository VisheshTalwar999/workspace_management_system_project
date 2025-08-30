package com.wms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.dto.SiteDetailDTO;
import com.wms.models.Admin;
import com.wms.models.BuildingDTO;
import com.wms.models.Employee;
import com.wms.models.SeatDetailFloorWiseDTO;
import com.wms.models.SeatLocationDetail;
import com.wms.models.SeatLocationDetailDTO;
import com.wms.models.SiteDTO;
import com.wms.repo.AdminRepo;
import com.wms.repo.SeatLocationDetailRepository;
import com.wms.repo.WMSRepo;

@Service
public class WMSService {
	
	@Autowired
	private WMSRepo wmsRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private SeatLocationDetailRepository seatLocationDetailRepo;
	
	@Autowired
    private AdminRepo adminRepository;

    public String login(String username, String password) {
        Admin admin = adminRepository.findByUsernameAndPassword(username, password);
        if (admin != null) {
            return admin.getUsername();
        }
        return null;
    }
	
	
	public List<SiteDetailDTO> getSiteDetails() {
		//return wmsRepo.getSiteDetails();
		return wmsRepo.getTransformedSiteDetails() ;
	}
	
	public SeatLocationDetail saveSeatLocationDetail(SeatLocationDetail detail) {
        return seatLocationDetailRepo.save(detail);
    }
	
	public SeatLocationDetailDTO getMaxIdAndSeatNo(String site, String building, String floor) {
		SeatLocationDetailDTO seatLocationDetailDTO= new SeatLocationDetailDTO() ;
        List<SeatLocationDetailDTO> results = seatLocationDetailRepo.findMaxIdAndSeatNoBySiteBuildingAndFloor(site, building, floor);
        if (results != null && !results.isEmpty()) {
            return results.get(0); // Return the first result
        }
        return seatLocationDetailDTO; // Or handle the case where no results are found
    }

    public String saveSeatLocationDetails(SeatLocationDetail details, int number) {
        String site = details.getSite();
        String building = details.getBuilding();
        String floor = details.getFloor();
        String recentSeatNoCount = "0";
        String recentSeatNo = "";
        SeatLocationDetailDTO seatLocationDetailDTO= new SeatLocationDetailDTO() ;

        try {
            // SeatLocationDetailDTO seatLocationDetailDTO = seatLocationDetailRepo.findMaxIdAndSeatNoBySiteBuildingAndFloor(site, building, floor);
        	List<SeatLocationDetailDTO> results = seatLocationDetailRepo.findMaxIdAndSeatNoBySiteBuildingAndFloor(site, building, floor);
            if (results != null && !results.isEmpty()) {
            	seatLocationDetailDTO = results.get(0); // Return the first result
            }
            
            if (seatLocationDetailDTO != null && seatLocationDetailDTO.getSeatNo() != null) {
                recentSeatNo = seatLocationDetailDTO.getSeatNo();
                recentSeatNoCount = recentSeatNo.substring(3, recentSeatNo.length()); // check indexes
            }

            for (int i = 0; i < number; i++) {
                int countToBeAdded = Integer.parseInt(recentSeatNoCount) + (i + 1);
                String seatNumber = site.substring(0, 1) + building.substring(0, 1) + floor.substring(0, 1) + countToBeAdded;
                
                // Create a new instance of SeatLocationDetail for each iteration
                SeatLocationDetail newDetail = new SeatLocationDetail();
                newDetail.setSite(site);
                newDetail.setBuilding(building);
                newDetail.setFloor(floor);
                newDetail.setEnclosedRoom(details.getEnclosedRoom());
                newDetail.setOpenCub1(details.getOpenCub1());
                newDetail.setOpenCub2(details.getOpenCub2());
                newDetail.setOpenCub4(details.getOpenCub4());
                newDetail.setsStatus(details.getsStatus());
                newDetail.setsEmpId(details.getsEmpId());
                newDetail.setJobGrade(details.getJobGrade());
                newDetail.setSeatNo(seatNumber);
                newDetail.setDept(details.getDept());
                newDetail.setSubDept(details.getSubDept());

                seatLocationDetailRepo.save(newDetail);
            }

            return "Success";
        } catch (NumberFormatException e) {
            // Handle the case where recentSeatNoCount is not a valid integer
            System.err.println("Error parsing recentSeatNoCount: " + recentSeatNoCount);
            e.printStackTrace();
            return "Failure: Invalid seat number format";
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return "Failure: An unexpected error occurred";
        }
    }
    
   /* public List<SeatDetailFloorWiseDTO> getSeatDetailsFloorWise2(SeatLocationDetail details) {
    	String site = details.getSite();
        String building = details.getBuilding();
        String floor = details.getFloor();
        List<SeatDetailFloorWiseDTO> results = seatLocationDetailRepo.getSeatDetailsFloorWise(site, building, floor);
        return results; 
    }*/
    
    /*public Map<String, Map<String, Map<String, Map<String, List<SeatDetailFloorWiseDTO>>>>> getSeatDetailsFloorWise1(SeatLocationDetail details) {
    	String site = details.getSite();
        String building = details.getBuilding();
        String floor = details.getFloor();
        List<SeatDetailFloorWiseDTO> results = seatLocationDetailRepo.getSeatDetailsFloorWise(site, building, floor);

        return results.stream().collect(Collectors.groupingBy(
                SeatDetailFloorWiseDTO::getEnclosed_room,
                Collectors.groupingBy(
                        SeatDetailFloorWiseDTO::getOpen_cub_1,
                        Collectors.groupingBy(
                                SeatDetailFloorWiseDTO::getOpen_cub_2,
                                Collectors.groupingBy(SeatDetailFloorWiseDTO::getOpen_cub_4)
                        )
                )
        ));
    }*/
    
    public Map<String, List<SeatDetailFloorWiseDTO>> getSeatDetailsFloorWise(SeatLocationDetail details) {
    	String site = details.getSite();
        String building = details.getBuilding();
        String floor = details.getFloor();
        List<SeatDetailFloorWiseDTO> results = seatLocationDetailRepo.getSeatDetailsFloorWise(site, building, floor);

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByEnclosedRoom = results.stream()
                .filter(seat -> "Y".equals(seat.getEnclosed_room()))
                .collect(Collectors.groupingBy(seat -> "enclosed_room"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub1 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_1()))
                .collect(Collectors.groupingBy(seat -> "open_cub_1"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub2 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_2()))
                .collect(Collectors.groupingBy(seat -> "open_cub_2"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub4 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_4()))
                .collect(Collectors.groupingBy(seat -> "open_cub_4"));

        // Combine all groups into a single map
        Map<String, List<SeatDetailFloorWiseDTO>> combinedGroups = new java.util.HashMap<>();
        combinedGroups.putAll(groupedByEnclosedRoom);
        combinedGroups.putAll(groupedByOpenCub1);
        combinedGroups.putAll(groupedByOpenCub2);
        combinedGroups.putAll(groupedByOpenCub4);

        return combinedGroups;
    }
    
    public Map<String, List<SeatDetailFloorWiseDTO>> getSeatDetailsDeptWise(SeatLocationDetail details) {
    	String site = details.getSite();
        String building = details.getBuilding();
        String floor = details.getFloor();
        String dept = details.getDept();
        String subDept = details.getSubDept();
        
        List<SeatDetailFloorWiseDTO> results = null ;
        
        if(null!=details.getSubDept() && !(details.getSubDept().isEmpty())) {
        	 results = seatLocationDetailRepo.getSeatDetailsSubDeptWise(site, dept, subDept);
        }
        else if(null!=details.getDept()&&!(details.getDept().isEmpty())) {
        	results = seatLocationDetailRepo.getSeatDetailsDeptWise(site, dept);
        }
        else if(null!=details.getSite()&&!(details.getSite().isEmpty())) {
        	results = seatLocationDetailRepo.getSeatDetailsSiteWise(site);
        }
        

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByEnclosedRoom = results.stream()
                .filter(seat -> "Y".equals(seat.getEnclosed_room()))
                .collect(Collectors.groupingBy(seat -> "enclosed_room"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub1 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_1()))
                .collect(Collectors.groupingBy(seat -> "open_cub_1"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub2 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_2()))
                .collect(Collectors.groupingBy(seat -> "open_cub_2"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub4 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_4()))
                .collect(Collectors.groupingBy(seat -> "open_cub_4"));

        // Combine all groups into a single map
        Map<String, List<SeatDetailFloorWiseDTO>> combinedGroups = new java.util.HashMap<>();
        combinedGroups.putAll(groupedByEnclosedRoom);
        combinedGroups.putAll(groupedByOpenCub1);
        combinedGroups.putAll(groupedByOpenCub2);
        combinedGroups.putAll(groupedByOpenCub4);

        return combinedGroups;
    }
    
    public String saveEmployee(Employee employee) {
		return wmsRepo.saveEmployee(employee) ;
	}
    
    public Employee getEmployeeById(int id) {
        return wmsRepo.getEmployeeById(id);
    }
    
    public Map<String, List<SeatDetailFloorWiseDTO>> getSeatDetailsBuildingWise(SeatLocationDetail details) {
    	String site = details.getSite();
        String building = details.getBuilding();
        List<SeatDetailFloorWiseDTO> results = seatLocationDetailRepo.getSeatDetailsBuildingWise(site, building);

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByEnclosedRoom = results.stream()
                .filter(seat -> "Y".equals(seat.getEnclosed_room()))
                .collect(Collectors.groupingBy(seat -> "enclosed_room"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub1 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_1()))
                .collect(Collectors.groupingBy(seat -> "open_cub_1"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub2 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_2()))
                .collect(Collectors.groupingBy(seat -> "open_cub_2"));

        Map<String, List<SeatDetailFloorWiseDTO>> groupedByOpenCub4 = results.stream()
                .filter(seat -> "Y".equals(seat.getOpen_cub_4()))
                .collect(Collectors.groupingBy(seat -> "open_cub_4"));

        // Combine all groups into a single map
        Map<String, List<SeatDetailFloorWiseDTO>> combinedGroups = new java.util.HashMap<>();
        combinedGroups.putAll(groupedByEnclosedRoom);
        combinedGroups.putAll(groupedByOpenCub1);
        combinedGroups.putAll(groupedByOpenCub2);
        combinedGroups.putAll(groupedByOpenCub4);

        return combinedGroups;
    }
    
    public List<String> getSeatProximityBySubDept(SeatLocationDetail details) {
    	String site = details.getSite();
        String dept = details.getDept();
        String subDept = details.getSubDept();
        List<String> listOfSeatsSubDeptWise = new ArrayList<>()  ;
        List<SeatDetailFloorWiseDTO> results = seatLocationDetailRepo.getSeatProximityBySubDept(site, dept, subDept);
        if (results != null && !results.isEmpty()) {
        	for(SeatDetailFloorWiseDTO seatDetailFloorWiseDTO : results) {
        		listOfSeatsSubDeptWise.add(seatDetailFloorWiseDTO.getSeatNo());
        	}
            return listOfSeatsSubDeptWise; // Return the first result
        }
        return listOfSeatsSubDeptWise; // Or handle the case where no results are found
    }
    
    public List<SiteDTO> getSiteBuildingFloorDetails() {
        List<SeatLocationDetail> seatLocationDetails = seatLocationDetailRepo.findAllSeatLocationDetails();
 
        // Group by site, then by building, and collect unique floors
        Map<String, Map<String, Set<String>>> groupedBySiteAndBuilding = seatLocationDetails.stream()
                .collect(Collectors.groupingBy(SeatLocationDetail::getSite,
                        Collectors.groupingBy(SeatLocationDetail::getBuilding,
                                Collectors.mapping(SeatLocationDetail::getFloor, Collectors.toSet()))));
 
        // Convert the grouped data into the desired DTO structure
        return groupedBySiteAndBuilding.entrySet().stream().map(siteEntry -> {
            SiteDTO siteDTO = new SiteDTO();
            siteDTO.setSite(siteEntry.getKey());
 
            List<BuildingDTO> buildings = siteEntry.getValue().entrySet().stream().map(buildingEntry -> {
                BuildingDTO buildingDTO = new BuildingDTO();
                buildingDTO.setName(buildingEntry.getKey());
                buildingDTO.setFloor(buildingEntry.getValue().stream().sorted().collect(Collectors.toList()));
                return buildingDTO;
            }).collect(Collectors.toList());
 
            siteDTO.setBuilding(buildings);
            return siteDTO;
        }).collect(Collectors.toList());
    }
    
    public SeatLocationDetail updateSEmpId(String seatNo, String empEmail,String dept, String jobGrade ) throws Exception {
    	SeatLocationDetail seatLocationDetail = null ;
    	String previousSeatNo = "" ;
    	Employee employee = wmsRepo.findByEmail(empEmail); 
    	int employeeID  = employee.getEmpId();
    	String subDeptOfCurrentEmployee = null ;
    	//String employeeID  = employee.getEmpId();
    	
    	try
    	{
    		seatLocationDetail = seatLocationDetailRepo.findBysEmpId(employeeID);
    	}
    	catch(Exception e) 
    	{
    		System.out.print(""+ e);
    	}
    	if(null!=seatLocationDetail) {
    		previousSeatNo = seatLocationDetail.getSeatNo();
    		System.out.println("previous seat no was " + previousSeatNo );
    		seatLocationDetail = seatLocationDetailRepo.findBySeatNo(previousSeatNo);
    		seatLocationDetail.setsEmpId(0);
    		subDeptOfCurrentEmployee = seatLocationDetail.getSubDept();
    	}
    	
    	// Now calculate seat proximity of calculated department 
    	List<String> seatsInProximity= getSeatProximityBySubDept(seatLocationDetail);
    	if(seatsInProximity.contains(seatNo)) {
    		System.out.println("Seat is well in proximity");
    	}
    	else
    	{
    		System.out.println("Selected Seat is not in good proximity, so change accordingly !");
    		throw new Exception( "Selected Seat is not in good proximity, so change accordingly !" ) ;
    	}
        seatLocationDetail = seatLocationDetailRepo.findBySeatNo(seatNo);
        	if(0!=seatLocationDetail.getsEmpId()) {
        			System.out.println("There is already a seat assigned to this emp, seat will be updated !");
        	}
            seatLocationDetail.setsEmpId(employeeID);
            seatLocationDetail.setsStatus("FILLED");
            return seatLocationDetailRepo.save(seatLocationDetail);
    }
    
    public SeatLocationDetail deleteEmpId(String seatNo, String empEmail,String dept, String jobGrade ) {
    	//Employee employee = wmsRepo.findByEmail(empEmail); 
    	
        SeatLocationDetail seatLocationDetail = seatLocationDetailRepo.findBySeatNo(seatNo);
        if (seatLocationDetail != null) {
            seatLocationDetail.setsEmpId(0);
            return seatLocationDetailRepo.save(seatLocationDetail);
        }
        return null;
    }
    
//    public void deleteEmployeeAndNullifySEmpId(int id) {
//        wmsRepo.updateSEmpIdToNullByEmployeeId(id);
//        wmsRepo.deleteEmployeeById(id);
//    }
    
    public void deleteEmployeeAndNullifySEmpId(int id) {     
    	wmsRepo.updateSEmpIdToNullByEmployeeId(id);     
    	wmsRepo.deleteEmployeeById(id); 
    }
    
    public String updateEmployee(Employee employee) {
        return wmsRepo.updateEmployee(employee);
    }
    

}
