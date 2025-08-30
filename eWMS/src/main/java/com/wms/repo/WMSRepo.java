package com.wms.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wms.dto.SiteDetailDTO;
import com.wms.models.Admin;
import com.wms.models.Employee;
import com.wms.models.SiteDetails;
import com.wms.dto.*;

@Repository
public class WMSRepo {
	@Autowired
	private EntityManager entityManager;

//	public String login(String id,String password) {
//		Session session= entityManager.unwrap(Session.class);
//		Admin a=(Admin) session.get(Admin.class,id);
//		if(a==null || !password.equals(a.getPassword())) {
//			return null;
//		}
//		return a.getUsername();
//	}
	
	public List<SiteDetails> getSiteDetails() {
		Session session= entityManager.unwrap(Session.class);
		List<SiteDetails> list = session.createQuery(
		        "select new com.wms.models.SiteDetails(s.site, s.dept, s.subDept) from SiteDetails s", SiteDetails.class)
				.list();
		return list;
	}
	
	public List<SiteDetails> getSiteDetails1() {
	       String jpql = "SELECT s FROM ewms.st_site s";
	       TypedQuery<SiteDetails> query = entityManager.createQuery(jpql, SiteDetails.class);
	       return query.getResultList();
	   }
	
	public List<SiteDetailDTO> getTransformedSiteDetails() {
        
        Session session= entityManager.unwrap(Session.class);
		List<SiteDetails> siteDetailsList = session.createQuery(
		        "select new com.wms.models.SiteDetails(s.site, s.dept, s.subDept) from SiteDetails s", SiteDetails.class)
				.list();

        Map<String, Map<String, List<SiteDetails>>> groupedBySiteAndDept = siteDetailsList.stream()
                .collect(Collectors.groupingBy(SiteDetails::getSite, 
                        Collectors.groupingBy(SiteDetails::getDept)));

        List<SiteDetailDTO> siteDetails = new ArrayList<>();

        for (Map.Entry<String, Map<String, List<SiteDetails>>> siteEntry : groupedBySiteAndDept.entrySet()) {
            SiteDetailDTO siteDetailDTO = new SiteDetailDTO();
            siteDetailDTO.setSite(siteEntry.getKey());

            List<DepartmentDTO> departments = new ArrayList<>();
            for (Map.Entry<String, List<SiteDetails>> deptEntry : siteEntry.getValue().entrySet()) {
                DepartmentDTO departmentDTO = new DepartmentDTO();
                departmentDTO.setName(deptEntry.getKey());

                List<String> subDepartments = deptEntry.getValue().stream()
                        .map(SiteDetails::getSubDept)
                        .collect(Collectors.toList());

                departmentDTO.setSubDepartments(subDepartments);
                departments.add(departmentDTO);
            }

            siteDetailDTO.setDepartments(departments);
            siteDetails.add(siteDetailDTO);
        }
        System.out.println("exiting getTransformedSiteDetails() method ");
        return siteDetails;
	}
	
	@Transactional
	public String saveEmployee(Employee employee) {
		Session session = entityManager.unwrap(Session.class);
	    try {
	        Employee existingEmployee = session.createQuery("SELECT e FROM Employee e WHERE e.email = :email", Employee.class)
	                .setParameter("email", employee.getEmail())
	                .getSingleResult();
	        if (existingEmployee != null) {
	            return "User already exists";
	        }
	    } catch (NoResultException e) {
	    	System.out.println("Exception while adding employee in repo layer");
	    }
	    session.saveOrUpdate(employee);
	    session.flush(); // Ensure the session is flushed to synchronize with the database
	    return "Success";
    }
	
	public Employee findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT e FROM Employee e WHERE e.email = :email", Employee.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
	
	@Transactional
    public Employee getEmployeeById(int id) {
        Session session = entityManager.unwrap(Session.class);
        try {
            Employee employee = session.createQuery("SELECT e FROM Employee e WHERE e.empId = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return employee;
        } catch (NoResultException e) {
            System.out.println("Employee not found with id: " + id);
            return null;
        }
    }
	
//	@Transactional
//    public void updateSEmpIdToNullByEmployeeId(int empId) {
//        Session session = entityManager.unwrap(Session.class);
//        session.createQuery("UPDATE SeatLocationDetail s SET s.sEmpId = null WHERE s.sEmpId = :empId")
//                .setParameter("empId", String.valueOf(empId))
//                .executeUpdate();
//    }
//	
//	@Transactional
//    public void deleteEmployeeById(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        try {
//            Employee employee = session.createQuery("SELECT e FROM Employee e WHERE e.empId = :id", Employee.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//            if (employee != null) {
//                session.delete(employee);
//                session.flush(); // Ensure the session is flushed to synchronize with the database
//            }
//        } catch (NoResultException e) {
//            System.out.println("Employee not found with id: " + id);
//        }
//    }
	
	@Transactional
    public void updateSEmpIdToNullByEmployeeId(int empId) {
        Session session = entityManager.unwrap(Session.class);
        int updatedRows = session.createQuery("UPDATE SeatLocationDetail s SET s.sEmpId = 0 WHERE s.sEmpId = :empId")
                .setParameter("empId", empId)
                .executeUpdate();
        if (updatedRows == 0) {
            System.out.println("No SeatLocationDetail records found with sEmpId: " + empId);
        }
    }

    @Transactional
    public void deleteEmployeeById(int id) {
        Session session = entityManager.unwrap(Session.class);
        try {
            Employee employee = session.createQuery("SELECT e FROM Employee e WHERE e.empId = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            if (employee != null) {
                session.delete(employee);
                session.flush(); // Ensure the session is flushed to synchronize with the database
            }
        } catch (NoResultException e) {
            System.out.println("Employee not found with id: " + id);
        }
    }

    @Transactional
    public String updateEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        try {
            Employee existingEmployee = session.createQuery("SELECT e FROM Employee e WHERE e.empId = :id", Employee.class)
                    .setParameter("id", employee.getEmpId())
                    .getSingleResult();
            if (existingEmployee != null) {
                existingEmployee.setName(employee.getName());
                existingEmployee.setEmail(employee.getEmail());
                existingEmployee.setDepartment(employee.getDepartment());
                existingEmployee.setSubDepartment(employee.getSubDepartment());
                existingEmployee.setDepartmentManager(employee.getDepartmentManager());
                existingEmployee.setSubDepartmentManager(employee.getSubDepartmentManager());
                existingEmployee.setDepartmentAdministrator(employee.getDepartmentAdministrator());
                existingEmployee.setStaffCategory(employee.getStaffCategory());
                existingEmployee.setJobGrade(employee.getJobGrade());
                existingEmployee.setSite(employee.getSite());
                existingEmployee.setStaffLaptopAssetNumber(employee.getStaffLaptopAssetNumber());
                existingEmployee.setStaffLaptopSerialNumber(employee.getStaffLaptopSerialNumber());
                existingEmployee.setMonitorAssetNumber(employee.getMonitorAssetNumber());
                existingEmployee.setMonitorSerialNumber(employee.getMonitorSerialNumber());
                existingEmployee.setDesktopAssetNumber(employee.getDesktopAssetNumber());
                existingEmployee.setDesktopSerialNumber(employee.getDesktopSerialNumber());
                session.saveOrUpdate(existingEmployee);
                session.flush(); // Ensure the session is flushed to synchronize with the database
                return "Employee updated successfully";
            }
        } catch (NoResultException e) {
            System.out.println("Employee not found with id: " + employee.getEmpId());
        }
        return "Employee not found";
    }
	
}
