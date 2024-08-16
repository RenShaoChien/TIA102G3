package com.tia102g3.member.model;

import lombok.Data;

@Data
public class PasswordChangeDTO {
    private String oldPassword;
    private String newPassword;
    private String newPasswordAgain;
}

