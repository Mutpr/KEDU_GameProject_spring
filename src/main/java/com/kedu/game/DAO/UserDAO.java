package com.kedu.game.DAO;

import com.kedu.game.DTO.UserDTO;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.MediaSize;

@Repository
public class UserDAO {
    private static final String NAMESPACE = "User";

    @Autowired
    SqlSession session;

    public UserDTO userLogin(UserDTO userDTO) {
        return session.selectOne(NAMESPACE + ".userLogin", userDTO);
    }

    public int userRegister(UserDTO userDTO) {
        return session.insert(NAMESPACE + ".userRegister", userDTO);
    }
}
