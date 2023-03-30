package com.chandansingh16794.controller;

import com.chandansingh16794.dto.UserDto;
import com.chandansingh16794.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto newUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos =  this.userService.findUsers();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("{id}")
    ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId) {
        UserDto user = this.userService.findUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                       @Valid @RequestBody UserDto userDto) {
        userDto.setId(userId);
        UserDto updatedUserDto = this.userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successful", HttpStatus.OK);
    }

}
