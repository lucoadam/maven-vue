package com.lucoadam.hms.roles;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_SCHOOL;

    public String getAuthority() {
        return name();
    }

}
