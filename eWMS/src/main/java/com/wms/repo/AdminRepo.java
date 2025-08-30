package com.wms.repo;

import com.wms.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    @Query("SELECT a FROM Admin a WHERE a.username = :username AND a.password = :password")
    Admin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}