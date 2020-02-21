package com.lucoadam.hms.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChangePasswordDTO {
    private String currentPassword;
    private String password;
}
