package com.kedu.game.DAO;

import com.kedu.game.DTO.FriendDTO;
import com.kedu.game.DTO.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class FriendDAO {

    @Autowired
    SqlSession session;

    private final static String NAMESPACE="Friend.";

    public Map<String, Object> selectFriendList(int friend_owner_seq){
        return session.selectOne(NAMESPACE+"selectFriendListClob", friend_owner_seq);
    }
    public int insertFriendList(int friend_owner_seq){
        return session.insert(NAMESPACE+"insertFriendList", friend_owner_seq);
    }

    public List<UserDTO> findUserAsKeyword(String user_tag_id){
        return session.selectList(NAMESPACE+"findUserAsKeyword", user_tag_id);
    }
}
