package com.chandansingh16794.service;

import com.chandansingh16794.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto findUser(Long id);
    List<UserDto> findUsers();
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
}
