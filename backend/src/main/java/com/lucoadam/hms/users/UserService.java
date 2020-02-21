package com.lucoadam.hms.users;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public interface UserService {
    String loginAndCreateToken(String username,String password);

    User whoami(HttpServletRequest req);

    String refreshToken(HttpServletRequest req);

    Integer usernameCount(String username);

    Map<Integer, UserResponseDTO> getMappedUsers(Set<Integer> users);

    void createNewUser(User user);

    void deleteById(Integer userId);

    void changePasswordForCurrentlyLoggedUser(ChangePasswordDTO dto);

    void changePasswordByAdmin(ChangePasswordAdminDTO dto);

}
