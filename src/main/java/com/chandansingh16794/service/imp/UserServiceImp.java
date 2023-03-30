package com.chandansingh16794.service.imp;

import com.chandansingh16794.dto.UserDto;
import com.chandansingh16794.entity.User;
import com.chandansingh16794.exception.HttpException;
import com.chandansingh16794.mapper.UserMapper;
import com.chandansingh16794.repository.UserRepository;
import com.chandansingh16794.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> checkEmail = this.userRepository.findByEmail(userDto.getEmail());
        if(checkEmail.isPresent()) throw new HttpException("Email already exist", HttpStatus.BAD_REQUEST, "USER_EMAIL_EXIST");
        Optional<User> checkPhone = this.userRepository.findByEmail(userDto.getPhone());
        if(checkPhone.isPresent()) throw new HttpException("Phone already exist", HttpStatus.BAD_REQUEST, "USER_PHONE_EXIST");
        User user = UserMapper.userDtoToUser(userDto);
        User newUser = this.userRepository.save(user);
        return UserMapper.userToUserDto(newUser);
    }

    @Override
    public UserDto findUser(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())  throw new HttpException("User not found", HttpStatus.NOT_FOUND, "USER_NOT_FOUND");
        return UserMapper.userToUserDto(optionalUser.get());
    }

    @Override
    public List<UserDto> findUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(UserMapper::userToUserDto).toList();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        Optional<User> checkEmail = this.userRepository.findByEmailAndIdNot(userDto.getEmail(), userDto.getId());
        if(checkEmail.isPresent()) throw new HttpException("Email already exist", HttpStatus.BAD_REQUEST, "USER_EMAIL_EXIST");

        Optional<User> checkPhone = this.userRepository.findByPhoneAndIdNot(userDto.getPhone(), userDto.getId());
        if(checkPhone.isPresent()) throw new HttpException("Phone already exist", HttpStatus.BAD_REQUEST, "PHONE_EMAIL_EXIST");

        Optional<User> optionalUser = this.userRepository.findById(userDto.getId());
        if(optionalUser.isEmpty()) throw new HttpException("User not found", HttpStatus.NOT_FOUND, "USER_NOT_FOUND");

        User user = optionalUser.get();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        User updatedUser = this.userRepository.save(user);
        return UserMapper.userToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
