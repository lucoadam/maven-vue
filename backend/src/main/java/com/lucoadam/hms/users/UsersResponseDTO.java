package com.lucoadam.hms.users;

public interface UsersResponseDTO {
    String getUsername();

    Integer getId();

    String getRole();

    String getName();

    void setUsername(String username);

    void setId(Integer id);

    void setRole(String role);

    void setName(String name);
}
