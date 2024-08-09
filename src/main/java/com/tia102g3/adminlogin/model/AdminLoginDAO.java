package com.tia102g3.adminlogin.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginDAO extends JpaRepository<AdminLogin, Long> {
    // 根據 adminUsername 和 adminPassword 查找管理員
    AdminLogin findByAdminUsernameAndAdminPassword(String adminUsername, String adminPassword);
}
