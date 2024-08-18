package com.tia102g3.adminlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g3.adminlogin.model.AdminLogin;
import com.tia102g3.adminlogin.model.AdminLoginRepository;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AdminLoginRepository adminRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Override
    public boolean accountExists(String adminUsername) {
        return adminRepository.findByAdminUsername(adminUsername) != null;
    }

    @Override
    public void registerAdmin(String adminName, String adminUsername, String adminPassword, String adminEmail) {
        if (accountExists(adminUsername)) {
            throw new IllegalArgumentException("帳號已存在");
        }
        AdminLogin admin = new AdminLogin();
        admin.setAdminName(adminName);
        admin.setAdminUsername(adminUsername);
        admin.setAdminPassword(adminPassword);
        admin.setAdminEmail(adminEmail);
        adminRepository.save(admin);
    }

    @Override
    public boolean validateAdmin(String adminUsername, String adminPassword) {
        AdminLogin admin = adminRepository.findByAdminUsername(adminUsername);
        if (admin == null) {
            return false;
        }
        // @Override
        // return passwordEncoder.matches(adminPassword, admin.getAdminPassword()); // 密碼比對
        return adminPassword.equals(admin.getAdminPassword()); // 暫時不比對加密密碼
    }

    @Override
    public Long getAdminId(String adminUsername) {
        AdminLogin admin = adminRepository.findByAdminUsername(adminUsername);
        return (admin != null) ? admin.getAdmin_ID() : null;
    }

    @Override
    public String getAdminName(String adminUsername) {
        AdminLogin admin = adminRepository.findByAdminUsername(adminUsername);
        return (admin != null) ? admin.getAdminName() : null;
    }

    @Override
    public AdminLogin findByAdminUsername(String adminUsername) {
        return adminRepository.findByAdminUsername(adminUsername);
    }

    @Override
    public AdminLogin getAdminDetails(String adminUsername) {
        return findByAdminUsername(adminUsername); // 使用已有的方法
    }
}
