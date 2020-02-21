package com.lucoadam.hms.users;

import com.lucoadam.hms.exceptions.AuthenticationException;
import com.lucoadam.hms.security.JwtTokenProvider;
import com.lucoadam.hms.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public String loginAndCreateToken(String username, String password) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            return jwtTokenProvider.createToken(username,userRepository.findByUsername(username).getRoles());
        }catch (AuthenticationException e){
            throw new AuthenticationException("Invalid Username/Password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    @Override
    public String refreshToken(HttpServletRequest req) {
        String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req));
        System.out.println("username"+username);
        return jwtTokenProvider.createToken(username,userRepository.findByUsername(username).getRoles());
    }

    @Override
    public Integer usernameCount(String username) {
        return userRepository.usernameCount(username);
    }

    @Override
    public Map<Integer, UserResponseDTO> getMappedUsers(Set<Integer> users) {
        List<UserResponseDTO> dtos = userRepository.customFindById(users).stream().map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.getRole(), user.getForcePasswordChange())).collect(Collectors.toList());
        Map<Integer, UserResponseDTO> map = new HashMap<>();
        dtos.forEach(item -> map.put(item.getId(), item));

        return map;
    }

    @Override
    public void createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void changePasswordForCurrentlyLoggedUser(ChangePasswordDTO dto) {
        User user = SecurityHelper.getCurrentlyLoggedUser();
        if (passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            changePassword(user, dto.getPassword());
        } else {
            throw new AuthenticationException("Invalid Password", HttpStatus.BAD_REQUEST);
        }
    }

    private void changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        user.setForcePasswordChange(false);
        userRepository.save(user);
    }

    @Override
    public void changePasswordByAdmin(ChangePasswordAdminDTO dto) {
        changePassword(userRepository.findById(dto.getUserId()).get(), dto.getPassword());
    }
}
