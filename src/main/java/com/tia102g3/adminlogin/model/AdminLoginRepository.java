package com.tia102g3.adminlogin.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginRepository extends JpaRepository<AdminLogin, Long> {
    AdminLogin findByAdminUsername(String adminUsername);
}
