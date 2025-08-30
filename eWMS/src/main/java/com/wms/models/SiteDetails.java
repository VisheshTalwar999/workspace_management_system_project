package com.wms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "st_site")
public class SiteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "site", length = 255)
    private String site;

    @Column(name = "dept", length = 255)
    private String dept;

    @Column(name = "sub_dept", length = 255)
    private String subDept;

    @Column(name = "dept_mgr", length = 255)
    private String deptMgr;

    @Column(name = "dept_admin", length = 255)
    private String deptAdmin;

    @Column(name = "sub_dept_mgr", length = 255)
    private String subDeptMgr;
    
    
    
    public SiteDetails(String site, String dept, String subDept) {
		super();
		this.site = site;
		this.dept = dept;
		this.subDept = subDept;
	}

	// Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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

    public String getDeptMgr() {
        return deptMgr;
    }

    public void setDeptMgr(String deptMgr) {
        this.deptMgr = deptMgr;
    }

    public String getDeptAdmin() {
        return deptAdmin;
    }

    public void setDeptAdmin(String deptAdmin) {
        this.deptAdmin = deptAdmin;
    }

    public String getSubDeptMgr() {
        return subDeptMgr;
    }

    public void setSubDeptMgr(String subDeptMgr) {
        this.subDeptMgr = subDeptMgr;
    }
}