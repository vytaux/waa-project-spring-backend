package com.theateam.waaprojectspringbackend.entity.dto.request;

import com.theateam.waaprojectspringbackend.entity.RoleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private RoleType role;
}
