package com.lucoadam.hms.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordAdminDTO {
    private Integer userId;
    private String password;
}
