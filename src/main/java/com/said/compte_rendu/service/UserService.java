package com.said.compte_rendu.service;

import com.said.compte_rendu.dto.UserRequestDto;
import com.said.compte_rendu.dto.UserResponseDto;
import com.said.compte_rendu.exception.AccountNotFoundException;
import com.said.compte_rendu.exception.EmailAlreadyInUseException;
import com.said.compte_rendu.exception.InvalidEmailOrPasswordException;
import com.said.compte_rendu.filter.JwtUtils;
import com.said.compte_rendu.model.User;
import com.said.compte_rendu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private  final JwtUtils jwtUtils;


    public UserResponseDto registerUser(User registerUser){

        if(userRepository.existsByEmail(registerUser.getEmail())){
            throw new EmailAlreadyInUseException("Email already in use ");
        }
        User user = new User();
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());
        user.setPassword(encoder.encode(registerUser.getPassword()));
        userRepository.save(user);
        return convertToDto(user);
    }

    public UserResponseDto findUserByEmail(String email) {
        User user =  userRepository.findByEmail(email).orElseThrow(()->  new AccountNotFoundException(String.format("No account matches this email ",email)));
        return convertToDto(user);
    }

    public UserResponseDto login(UserRequestDto userRequestDto) {
        User user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(()->new AccountNotFoundException("Account Not Found"));
        if(!encoder.matches(userRequestDto.getPassword(), user.getPassword())) throw new InvalidEmailOrPasswordException("Invalid Email Or Password");
        UserResponseDto dto =  convertToDto(user);
        dto.setJwt(jwtUtils.createToken(user));
        return dto;
    }
    public UserResponseDto convertToDto(User user){
        UserResponseDto userRequestDto = new UserResponseDto();
        userRequestDto.setFirstName(user.getFirstName());
        userRequestDto.setLastName(user.getLastName());
        userRequestDto.setEmail(user.getEmail());
        return userRequestDto;
    }





}
