package ru.geekbrains.wnteredshop.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.wnteredshop.api.AppError;
import ru.geekbrains.wnteredshop.api.JwtRequest;
import ru.geekbrains.wnteredshop.api.JwtResponse;
import ru.geekbrains.wnteredshop.api.RegistrationUserDto;
import ru.geekbrains.wnteredshop.auth.entities.User;
import ru.geekbrains.wnteredshop.auth.services.UserService;
import ru.geekbrains.wnteredshop.auth.utils.JwtTokenUtil;


@RestController
@RequiredArgsConstructor
//@CrossOrigin("*")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;



    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError( HttpStatus.UNAUTHORIZED.value(),"Incorrect username or password"),HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
        String token =jwtTokenUtil.generateToken(userDetails);
        int a;
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody RegistrationUserDto registrationUserDto){
        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пароли не совпадают"),HttpStatus.BAD_REQUEST);
        }
        if(userService.findByUsername(registrationUserDto.getUsername()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователь с таким именем уже существует"),HttpStatus.BAD_REQUEST);
        }
        String token =jwtTokenUtil.generateToken(userService.registration(registrationUserDto));
        return ResponseEntity.ok(new JwtResponse(token));
    }



}
