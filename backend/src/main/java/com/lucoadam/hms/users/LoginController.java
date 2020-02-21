package com.lucoadam.hms.users;

import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(code=400,message = "Something went wrong"),
            @ApiResponse(code=422,message = "Invalid username/password supplied")
    })
    public Map<String,String> login(HttpServletResponse httpResponse, @RequestBody LoginRequestDTO loginRequest){
        httpResponse.setHeader("Authorization", "Bearer " + userService.loginAndCreateToken(loginRequest.getUsername(), loginRequest.getPassword()));

        Map<String, String> response = new HashMap<>();

        response.put("status", "success");

        return response;
    }

    @GetMapping(value = "/user")
    @ApiOperation(value = "Fetch currently logged user ", response = UserResponseDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }


    @GetMapping(value = "/refresh")
    @ApiOperation(value = "Refresh JWT token", response = String.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Map<String, String> refresh(HttpServletRequest req, HttpServletResponse servletResponse) {

        servletResponse.setHeader("Authorization", "Bearer " + userService.refreshToken(req));

        Map<String, String> response = new HashMap<>();

        response.put("status", "success");

        return response;
    }

}
