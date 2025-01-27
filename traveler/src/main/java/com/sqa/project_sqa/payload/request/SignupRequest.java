package com.sqa.project_sqa.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SignupRequest {
    private String name;

    private String userName;

    private String password;

    private String email;

    private String phone;
}
