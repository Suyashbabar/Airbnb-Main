package com.airbnb.controller;

import com.airbnb.dto.LoginDto;
import com.airbnb.dto.PropertyUserdto;
import com.airbnb.dto.TokenResponse;
import com.airbnb.entity.PropertyUser;
import com.airbnb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController // which will make rest api
@RequestMapping("/api/v1/users")   // version 1 of code indicates
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserdto propertyUserdto){

        logger.info("/adduser method started");

        PropertyUser propertyUser = userService.addUser(propertyUserdto);

        if (propertyUser!=null){
            return new ResponseEntity<>("registration is successfull" , HttpStatus.CREATED);
        }
        logger.info("/adduse executed ");
        return new ResponseEntity<>("something went wrong" , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
       String token= userService.verifyLogin(loginDto);
       if (token!=null){
           TokenResponse tokenResponse = new TokenResponse();
           tokenResponse.setToken(token);
           return new ResponseEntity<>(tokenResponse , HttpStatus.OK);
       }
       return new ResponseEntity<>("invalid credential" , HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/profile")
    public PropertyUser getCurrentUserProfile(@AuthenticationPrincipal PropertyUser propertyUser){

        return propertyUser;
    }

}
