package com.wms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wms.models.* ;

@Repository
public interface SeatLocationDetailRepository extends JpaRepository<SeatLocationDetail, Long> {
	
	 @Query("SELECT new com.wms.models.SeatLocationDetailDTO(s.id, s.seatNo) " +
	           "FROM SeatLocationDetail s " +
	           "WHERE s.site = :site AND s.building = :building AND s.floor = :floor order by ID desc  ")
	    List<SeatLocationDetailDTO> findMaxIdAndSeatNoBySiteBuildingAndFloor(@Param("site") String site,
	                                                                   @Param("building") String building,
	                                                                   @Param("floor") String floor);
	 
	 @Query("SELECT new com.wms.models.SeatDetailFloorWiseDTO(s.id, s.seatNo, s.site, s.building, s.floor, " +
	           "s.enclosedRoom, s.openCub1, s.openCub2, s.openCub4, s.sStatus, s.jobGrade) " +
	           "FROM SeatLocationDetail s " +
	           "WHERE s.site = :site AND s.building = :building AND s.floor = :floor " +
	           "ORDER BY s.id DESC")
	    List<SeatDetailFloorWiseDTO> getSeatDetailsFloorWise(@Param("site") String site,
	                                                                          @Param("building") String building,
	                                                                          @Param("floor") String floor);
	 
	 @Query("SELECT new com.wms.models.SeatDetailFloorWiseDTO(s.id, s.seatNo, s.site, s.building, s.floor, " +
	           "s.enclosedRoom, s.openCub1, s.openCub2, s.openCub4, s.sStatus, s.jobGrade, s.dept, s.subDept) " +
	           "FROM SeatLocationDetail s " +
	           "WHERE s.site = :site AND s.dept = :dept " +
	           "ORDER BY s.id DESC")
	    List<SeatDetailFloorWiseDTO> getSeatDetailsDeptWise(@Param("site") String site,
	                                                                          @Param("dept") String dept
	                                                                          );
	 
	 @Query("SELECT new com.wms.models.SeatDetailFloorWiseDTO(s.id, s.seatNo, s.site, s.building, s.floor, " +
	           "s.enclosedRoom, s.openCub1, s.openCub2, s.openCub4, s.sStatus, s.jobGrade, s.dept, s.subDept) " +
	           "FROM SeatLocationDetail s " +
	           "WHERE s.site = :site AND s.dept = :dept  AND s.subDept = :subDept " +
	           "ORDER BY s.id DESC")
	    List<SeatDetailFloorWiseDTO> getSeatDetailsSubDeptWise(@Param("site") String site,
	                                                                          @Param("dept") String dept,
	                                                                          @Param("subDept") String subDept);
	 
	 @Query("SELECT new com.wms.models.SeatDetailFloorWiseDTO(s.id, s.seatNo, s.site, s.building, s.floor, " +
	           "s.enclosedRoom, s.openCub1, s.openCub2, s.openCub4, s.sStatus, s.jobGrade, s.dept, s.subDept) " +
	           "FROM SeatLocationDetail s " +
	           "WHERE s.site = :site " +
	           "ORDER BY s.id DESC")
	    List<SeatDetailFloorWiseDTO> getSeatDetailsSiteWise(@Param("site") String site);
	 
	 @Query("SELECT new com.wms.models.SeatDetailFloorWiseDTO(s.id, s.seatNo, s.site, s.building, s.floor, " +
	           "s.enclosedRoom, s.openCub1, s.openCub2, s.openCub4, s.sStatus, s.jobGrade) " +
	           "FROM SeatLocationDetail s " +
	           "WHERE s.site = :site AND s.building = :building " +
	           "ORDER BY s.id DESC")
	    List<SeatDetailFloorWiseDTO> getSeatDetailsBuildingWise(@Param("site") String site,
	                                                                          @Param("building") String building
	                                                                          );
	 @Query("SELECT new com.wms.models.SeatDetailFloorWiseDTO(s.id, s.seatNo, s.site, s.building, s.floor, " +
	           "s.enclosedRoom, s.openCub1, s.openCub2, s.openCub4, s.sStatus, s.jobGrade) " +
	           "FROM SeatLocationDetail s " +
	           "WHERE s.site = :site AND s.dept = :dept  AND s.subDept = :subDept " +
	           "ORDER BY s.id DESC")
	    List<SeatDetailFloorWiseDTO> getSeatProximityBySubDept(@Param("site") String site,
	    													   @Param("dept") String dept,
	    													   @Param("subDept") String subDept
	                                                                          );
	 
	 SeatLocationDetail findBySeatNo(String seatNo);
	 
	 
	 @Query("SELECT s FROM SeatLocationDetail s")
	  List<SeatLocationDetail> findAllSeatLocationDetails();

	  SeatLocationDetail findBysEmpId(int employeeID);
	 
}