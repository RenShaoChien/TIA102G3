package com.tia102g3.adminlogin.service;

import com.tia102g3.adminlogin.model.AdminLogin;

public interface AdminLoginService {
    boolean accountExists(String adminUsername); // 檢查帳號是否存在
    void registerAdmin(String adminName, String adminUsername, String adminPassword, String adminEmail);
    boolean validateAdmin(String adminUsername, String adminPassword); // 驗證帳號密碼
    Long getAdminId(String adminUsername);
    String getAdminName(String adminUsername); // 修改為根據用戶名獲取管理者名稱
    AdminLogin findByAdminUsername(String adminUsername);
    AdminLogin getAdminDetails(String adminUsername); // 根據用戶名獲取管理員資料
}
