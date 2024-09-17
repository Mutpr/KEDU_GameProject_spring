package com.kedu.game.services;

import com.kedu.game.DAO.UserDAO;
import com.kedu.game.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public UserDTO UserLogin(UserDTO userDTO){
        return userDAO.userLogin(userDTO);
    }

    public int UserRegister(UserDTO userDTO) {
        return userDAO.userRegister(userDTO);
    }
    public UserDTO findUserAsUserSeq(int user_seq){
        return userDAO.findUserAsUserSeq(user_seq);
    }
}
