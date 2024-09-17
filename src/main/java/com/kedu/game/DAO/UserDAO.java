package com.kedu.game.DAO;

import com.kedu.game.DTO.UserDTO;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.MediaSize;
import java.util.Map;

@Repository
public class UserDAO {
    private static final String NAMESPACE = "User.";

    @Autowired
    SqlSession session;

    public UserDTO userLogin(UserDTO userDTO) {
        return session.selectOne(NAMESPACE + "userLogin", userDTO);
    }

    public int userRegister(UserDTO userDTO) {
        return session.insert(NAMESPACE + "userRegister", userDTO);
    }
    public UserDTO findUserAsUserSeq(int user_seq){
        return session.selectOne(NAMESPACE+"findUserAsUserSeq", user_seq);
    }
    public int friendListUpdate(Map<String, Object> friendList){
        return session.update(NAMESPACE+"friendListUpdate", friendList);
    }

    public String getFriendList(int user_seq){
        return session.selectOne(NAMESPACE+"getFriendList", user_seq);
    }
}
