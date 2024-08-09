package com.tia102g3.adminlogin.service;

public interface AdminLoginService {
    boolean accountExists(String adminUsername);
    void registerAdmin(String adminName, String adminUsername, String adminPassword, String adminEmail);
    boolean validateAdmin(String adminUsername, String adminPassword);
}

