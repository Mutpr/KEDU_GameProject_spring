package com.kedu.game.controller;

import com.kedu.game.DTO.UserDTO;
import com.kedu.game.services.UserService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<UserDTO> userLogin(@RequestBody UserDTO userDTO){
        UserDTO username = userService.UserLogin(userDTO);
//        log.info("users::::: "+user);
        return ResponseEntity.ok(username);
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> userRegister(@RequestBody UserDTO userDTO) {
        int result = userService.UserRegister(userDTO);
        //친구 추가 리스트 추가하는 메소드 추가
        log.info("insert result::::: " + result);
        return ResponseEntity.ok(result);
    }
}
