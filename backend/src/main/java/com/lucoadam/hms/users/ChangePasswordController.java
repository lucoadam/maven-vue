package com.lucoadam.hms.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/change-password")
public class ChangePasswordController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public void changePassword(@RequestBody ChangePasswordDTO dto) {
        userService.changePasswordForCurrentlyLoggedUser(dto);
    }

}