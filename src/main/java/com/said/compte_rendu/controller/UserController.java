package com.said.compte_rendu.controller;

import com.said.compte_rendu.dto.UserRequestDto;
import com.said.compte_rendu.dto.UserResponseDto;
import com.said.compte_rendu.model.User;
import com.said.compte_rendu.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public UserResponseDto registerUser(@RequestBody @Valid User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/get-user")
    public UserResponseDto findUserByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("login")
    public UserResponseDto login(@RequestBody UserRequestDto userRequestDto){
       return userService.login(userRequestDto);

    }



}
